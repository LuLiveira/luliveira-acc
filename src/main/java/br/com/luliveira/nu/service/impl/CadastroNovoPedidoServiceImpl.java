package br.com.luliveira.nu.service.impl;

import br.com.luliveira.nu.entities.PedidoConta;
import br.com.luliveira.nu.repository.CadastroNovoPedidoRepository;
import br.com.luliveira.nu.repository.entidade.PedidoContaEntidade;
import br.com.luliveira.nu.service.CadastroNovoPedidoService;
import br.com.luliveira.nu.service.EnviarEmailProducerService;
import br.com.luliveira.nu.service.VerificarDadosAprovacaoProducerService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public record CadastroNovoPedidoServiceImpl(ModelMapper mapper, CadastroNovoPedidoRepository cadastroNovoPedidoRepository, EnviarEmailProducerService enviarEmailProducerService, VerificarDadosAprovacaoProducerService verificarDadosAprovacaoService) implements CadastroNovoPedidoService {

    @Override
    public void executar(PedidoConta pedidoConta) {
        var pedidoContaEntidade = mapper.map(pedidoConta, PedidoContaEntidade.class);
        pedidoContaEntidade.setUuid(UUID.randomUUID().toString());
        cadastroNovoPedidoRepository.save(pedidoContaEntidade);
        enviarEmailProducerService.executar(pedidoConta.getEmail(), "Solicitação realizada com sucesso");
        verificarDadosAprovacaoService.executar(pedidoConta);
    }
}
