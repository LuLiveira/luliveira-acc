package br.com.luliveira.nu.service;

public interface EnviarEmailProducerService {
    void executar(String email, Object t);
}
