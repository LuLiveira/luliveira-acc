package br.com.luliveira.nu.services.impl.consumer;

import lombok.extern.log4j.Log4j2;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;

@Service
@Log4j2
public class EmailConsumerServiceImpl {

    @KafkaListener(topics = "SEND_EMAIL", groupId = "emails")
    public void executar(ConsumerRecord<String, Object> payload) {
        //TODO implementar envio de e-mail
        log.info(MessageFormat.format("Email: {0} e mensagem: {1} ", payload.key(), payload.value()));
    }
}
