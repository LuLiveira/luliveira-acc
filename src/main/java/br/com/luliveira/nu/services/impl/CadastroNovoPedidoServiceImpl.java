package br.com.luliveira.nu.services.impl;

import br.com.luliveira.nu.entities.PedidoConta;
import br.com.luliveira.nu.repositories.CadastroNovoPedidoRepository;
import br.com.luliveira.nu.repositories.entidade.PedidoContaEntidade;
import br.com.luliveira.nu.services.CadastroNovoPedidoService;
import br.com.luliveira.nu.services.EnviarEmailProducerService;
import br.com.luliveira.nu.services.VerificarDadosAprovacaoProducerService;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Log4j2
public record CadastroNovoPedidoServiceImpl(ModelMapper mapper, CadastroNovoPedidoRepository cadastroNovoPedidoRepository, EnviarEmailProducerService enviarEmailProducerService, VerificarDadosAprovacaoProducerService verificarDadosAprovacaoService) implements CadastroNovoPedidoService {

    @Override
    public void executar(PedidoConta pedidoConta) {
        var pedidoContaEntidade = mapper.map(pedidoConta, PedidoContaEntidade.class);
        pedidoContaEntidade.setUuid(UUID.randomUUID().toString());

        log.info("Cadastrando o pedido no MONGODB.");
        cadastroNovoPedidoRepository.save(pedidoContaEntidade);

        enviarEmailProducerService.executar(pedidoConta.getEmail(), "Enviando e-mail sobre a sua solicitação de um novo pedido de conta.");

        verificarDadosAprovacaoService.executar(pedidoConta);
    }
}
