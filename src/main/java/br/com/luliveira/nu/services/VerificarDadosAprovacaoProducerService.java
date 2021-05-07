package br.com.luliveira.nu.services;

import br.com.luliveira.nu.entities.PedidoConta;

public interface VerificarDadosAprovacaoProducerService {
    void executar(PedidoConta pedidoConta);
}
