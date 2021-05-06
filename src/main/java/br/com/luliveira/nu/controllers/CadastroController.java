package br.com.luliveira.nu.controllers;

import br.com.luliveira.nu.controllers.dto.CadastroForm;
import br.com.luliveira.nu.entities.PedidoConta;
import br.com.luliveira.nu.service.CadastroNovoPedidoService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/cadastros")
public record CadastroController(CadastroNovoPedidoService cadastroNovoPedido, ModelMapper modelMapper) {

    @PostMapping
    public ResponseEntity<Void> cadastrar(@RequestBody @Valid CadastroForm cadastroForm){

        var pedidoConta = modelMapper.map(cadastroForm, PedidoConta.class);

        cadastroNovoPedido.executar(pedidoConta);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
