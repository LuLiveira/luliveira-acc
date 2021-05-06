package br.com.luliveira.nu.service.impl.producer;

import br.com.luliveira.nu.service.EnviarEmailProducerService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EnviarEmailProducerServiceImpl implements EnviarEmailProducerService {

    @Value("${spring.kafka.producer.topic.enviar-email}")
    private String topicoEnviarEmail;

    private final KafkaTemplate<String, Object> kafkaTemplate;

    private final Logger LOG = LoggerFactory.getLogger(EnviarEmailProducerServiceImpl.class);

    @Override
    public void executar(String email, Object o) {

        LOG.info(o.toString());

        kafkaTemplate.send(topicoEnviarEmail, email, o);
    }
}
