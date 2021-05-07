package br.com.luliveira.nu.services;

public interface EnviarEmailProducerService {
    void executar(String email, Object t);
}
