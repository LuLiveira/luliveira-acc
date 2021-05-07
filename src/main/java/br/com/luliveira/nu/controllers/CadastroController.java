package br.com.luliveira.nu.controllers;

import br.com.luliveira.nu.controllers.dto.CadastroForm;
import br.com.luliveira.nu.entities.PedidoConta;
import br.com.luliveira.nu.services.CadastroNovoPedidoService;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/cadastros")
@Log4j2
public record CadastroController(CadastroNovoPedidoService cadastroNovoPedido, ModelMapper modelMapper) {

    @PostMapping
    public ResponseEntity<Void> cadastrar(@RequestBody @Valid CadastroForm cadastroForm){

        log.info("Iniciando um novo pedido de conta.");

        var pedidoConta = modelMapper.map(cadastroForm, PedidoConta.class);

        cadastroNovoPedido.executar(pedidoConta);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
