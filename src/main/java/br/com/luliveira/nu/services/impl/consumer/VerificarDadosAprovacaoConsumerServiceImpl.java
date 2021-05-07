package br.com.luliveira.nu.services.impl.consumer;

import br.com.luliveira.nu.entities.PedidoConta;
import br.com.luliveira.nu.services.ValidarDadosAprovacaoService;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public record VerificarDadosAprovacaoConsumerServiceImpl(
        ValidarDadosAprovacaoService validarDadosAprovacaoService) {

    @KafkaListener(topics = "VERIFY_DATA", groupId = "novos_pedidos")
    public void executar(ConsumerRecord<String, PedidoConta> payload) {
        var key = payload.key();
        var value = payload.value();
        validarDadosAprovacaoService.executar(key, value);
    }
}
