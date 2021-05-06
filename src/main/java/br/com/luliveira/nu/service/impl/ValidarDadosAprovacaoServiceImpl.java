package br.com.luliveira.nu.service.impl;

import br.com.luliveira.nu.entities.PedidoConta;
import br.com.luliveira.nu.service.EnviarEmailProducerService;
import br.com.luliveira.nu.service.ValidarDadosAprovacaoService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ValidarDadosAprovacaoServiceImpl implements ValidarDadosAprovacaoService {

    @Value("${spring.kafka.producer.topic.enviar-email}")
    private String topicoEnviarEmail;

    @Value("${spring.kafka.producer.topic.pedido-aprovado}")
    private String topicoPedidoAprovado;

    private final Logger LOG = LoggerFactory.getLogger(ValidarDadosAprovacaoServiceImpl.class);

    private final KafkaTemplate<String, Object> kafkaTemplate;

    private final EnviarEmailProducerService enviarEmailProducerService;

    @Override
    public void executar(String key, PedidoConta pedidoConta) {
        var isAprovado = Integer.valueOf(key.charAt(key.length() - 1)) % 2 == 0;
        if (isAprovado) {
            enviarEmailProducerService.executar(pedidoConta.getEmail(), key + " - Aprovado " + pedidoConta);
            kafkaTemplate.send(topicoPedidoAprovado, pedidoConta);
            LOG.info(" PedidoConta aprovado ");
        }else {
            LOG.info(" PedidoConta reprovado ");
            enviarEmailProducerService.executar(pedidoConta.getEmail(), key + " - Reprovado " + pedidoConta);
        }
    }
}
