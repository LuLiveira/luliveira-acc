package br.com.luliveira.nu.services.impl.producer;

import br.com.luliveira.nu.services.EnviarEmailProducerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Log4j2
public class EnviarEmailProducerServiceImpl implements EnviarEmailProducerService {

    @Value("${spring.kafka.producer.topic.enviar-email}")
    private String topicoEnviarEmail;

    private final KafkaTemplate<String, Object> kafkaTemplate;

    @Override
    public void executar(String email, Object o) {
        log.info("Enviando mensagem para fila de e-mail para informar sobre o cadastro de um novo pedido de conta.");
        kafkaTemplate.send(topicoEnviarEmail, email, o);
    }
}
