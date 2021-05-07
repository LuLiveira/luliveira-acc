package br.com.luliveira.nu.services.impl.producer;

import br.com.luliveira.nu.entities.PedidoConta;
import br.com.luliveira.nu.services.VerificarDadosAprovacaoProducerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;

@RequiredArgsConstructor
@Service
@Log4j2
public class VerificarDadosAprovacaoProducerServiceImpl implements VerificarDadosAprovacaoProducerService {

    @Value("${spring.kafka.producer.topic.verificar-dados}")
    private String topicoVerificarDados;

    private final KafkaTemplate<String, Object> kafkaTemplate;

    @Override
    public void executar(PedidoConta pedidoConta) {
        log.info("Enviando mensagem para fila de verificação de dados.");
        kafkaTemplate.send(topicoVerificarDados, UUID.randomUUID().toString(), pedidoConta);
    }
}
