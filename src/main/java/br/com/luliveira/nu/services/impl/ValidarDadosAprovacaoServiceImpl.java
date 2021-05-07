package br.com.luliveira.nu.services.impl;

import br.com.luliveira.nu.entities.PedidoConta;
import br.com.luliveira.nu.services.EnviarEmailProducerService;
import br.com.luliveira.nu.services.ValidarDadosAprovacaoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Log4j2
public class ValidarDadosAprovacaoServiceImpl implements ValidarDadosAprovacaoService {

    @Value("${spring.kafka.producer.topic.enviar-email}")
    private String topicoEnviarEmail;

    @Value("${spring.kafka.producer.topic.pedido-aprovado}")
    private String topicoPedidoAprovado;

    private final KafkaTemplate<String, Object> kafkaTemplate;

    private final EnviarEmailProducerService enviarEmailProducerService;

    @Override
    public void executar(String key, PedidoConta pedidoConta) {
        var isAprovado = Integer.valueOf(key.charAt(key.length() - 1)) % 2 == 0;
        if (isAprovado) {
            log.info("Enviando mensagem para fila de e-mail de pedido aprovado.");
            enviarEmailProducerService.executar(pedidoConta.getEmail(), key + " - Aprovado " + pedidoConta);

            log.info("Enviando mensagem para fila de pedido aprovado.");
            kafkaTemplate.send(topicoPedidoAprovado, pedidoConta);
        }else {
            log.info("Enviando mensagem para fila de e-mail de pedido reprovadao.");
            enviarEmailProducerService.executar(pedidoConta.getEmail(), key + " - Reprovado " + pedidoConta);
        }
    }
}
