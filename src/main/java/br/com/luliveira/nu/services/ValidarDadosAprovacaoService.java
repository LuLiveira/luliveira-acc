package br.com.luliveira.nu.services;

import br.com.luliveira.nu.entities.PedidoConta;

public interface ValidarDadosAprovacaoService {
    void executar(String key, PedidoConta pedidoConta);
}
