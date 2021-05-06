package br.com.luliveira.nu.service.impl.consumer;

import br.com.luliveira.nu.entities.PedidoConta;
import br.com.luliveira.nu.repository.CadastroNovoUsuarioRepository;
import br.com.luliveira.nu.repository.entidade.UsuarioEntidade;
import br.com.luliveira.nu.service.EnviarEmailProducerService;
import br.com.luliveira.nu.service.ValidarDadosAprovacaoService;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.modelmapper.ModelMapper;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public record NovoUsuarioConsumerServiceImpl(
        ValidarDadosAprovacaoService validarDadosAprovacaoService,
        ModelMapper mapper,
        CadastroNovoUsuarioRepository cadastroNovoUsuarioRepository,
        EnviarEmailProducerService enviarEmailProducerService) {

    @KafkaListener(topics = "PEDIDO_APROVADO", groupId = "pedidos_aprovado")
    public void executar(ConsumerRecord<String, PedidoConta> payload) {
        var pedidoConta = payload.value();

        var usuarioEntidade = mapper.map(pedidoConta, UsuarioEntidade.class);

        cadastroNovoUsuarioRepository.save(usuarioEntidade);

        enviarEmailProducerService.executar(usuarioEntidade.getEmail(), usuarioEntidade);
    }
}
