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
    private Endereco endereco;
    private String cpf;
    private String rg;
    private String estadoCivil;
    private String dataNascimento;
    private String sexo;
}
