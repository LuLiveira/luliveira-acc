package br.com.luliveira.nu.service.impl.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class EmailConsumerServiceImpl {

    private final Logger LOG = LoggerFactory.getLogger(EmailConsumerServiceImpl.class);

    @KafkaListener(topics = "SEND_EMAIL", groupId = "emails")
    public void executar(ConsumerRecord<String, Object> payload) {
        LOG.info("Mensagem recebida: " + payload.value());
    }
}
