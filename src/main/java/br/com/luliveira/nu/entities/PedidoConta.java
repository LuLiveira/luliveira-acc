package br.com.luliveira.nu.entities;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PedidoConta implements Serializable {

    private String nome;
    private String email;
    private String logradouro;
    private String numero;
    private String bairro;
    private String cep;
    private String cidade;
    private String estado;
    private String cpf;
    private String rg;
    private String estadoCivil;
    private String dataNascimento;
    private String sexo;
}
