package br.com.luliveira.nu.service.impl.producer;

import br.com.luliveira.nu.entities.PedidoConta;
import br.com.luliveira.nu.service.VerificarDadosAprovacaoProducerService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;

@RequiredArgsConstructor
@Service
public class VerificarDadosAprovacaoProducerServiceImpl implements VerificarDadosAprovacaoProducerService {

    @Value("${spring.kafka.producer.topic.verificar-dados}")
    private String topicoVerificarDados;

    private final KafkaTemplate<String, Object> kafkaTemplate;

    @Override
    public void executar(PedidoConta pedidoConta) {
        kafkaTemplate.send(topicoVerificarDados, UUID.randomUUID().toString(), pedidoConta);
    }
}
