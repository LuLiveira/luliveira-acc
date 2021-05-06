package br.com.luliveira.nu.controllers.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

@Getter
@Setter
@AllArgsConstructor
public class CadastroForm {

    @NotBlank
    private String nome;
//    private Endereco endereco;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    private String logradouro;

    @NotBlank
    @Positive
    private String numero;

    @NotBlank
    private String bairro;

    @NotBlank
    private String cep;

    @NotBlank
    private String cidade;

    @NotBlank
    private String estado;

    @CPF
    @NotBlank
    private String cpf;

    @NotBlank
    private String rg;

    @NotBlank
    private String estadoCivil;

    @NotBlank
    private String dataNascimento;

    @NotBlank
    private String sexo;

}
