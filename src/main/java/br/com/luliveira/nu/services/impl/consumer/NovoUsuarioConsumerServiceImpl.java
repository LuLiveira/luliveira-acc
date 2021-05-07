package br.com.luliveira.nu.services.impl.consumer;

import br.com.luliveira.nu.entities.PedidoConta;
import br.com.luliveira.nu.repositories.CadastroNovoUsuarioRepository;
import br.com.luliveira.nu.repositories.entidade.UsuarioEntidade;
import br.com.luliveira.nu.services.EnviarEmailProducerService;
import br.com.luliveira.nu.services.ValidarDadosAprovacaoService;
import lombok.extern.log4j.Log4j2;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.modelmapper.ModelMapper;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public record NovoUsuarioConsumerServiceImpl(
        ValidarDadosAprovacaoService validarDadosAprovacaoService,
        ModelMapper mapper,
        CadastroNovoUsuarioRepository cadastroNovoUsuarioRepository,
        EnviarEmailProducerService enviarEmailProducerService) {

    @KafkaListener(topics = "PEDIDO_APROVADO", groupId = "pedidos_aprovado")
    public void executar(ConsumerRecord<String, PedidoConta> payload) {
        var pedidoConta = payload.value();

        var usuarioEntidade = mapper.map(pedidoConta, UsuarioEntidade.class);

        log.info("Enviando mensagem para fila de e-mail informando o usuário e senha do aprovado.");
        enviarEmailProducerService.executar(usuarioEntidade.getEmail(), usuarioEntidade);

        log.info("Cadastrando o novo usuário no branco de dados");
        usuarioEntidade.setPassword(new BCryptPasswordEncoder().encode(usuarioEntidade.getPassword()));
        cadastroNovoUsuarioRepository.save(usuarioEntidade);

        //TODO comunicar com API de cadastro de cartão de crédito
    }
}
