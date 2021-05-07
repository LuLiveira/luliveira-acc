package br.com.luliveira.nu.repositories.entidade;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "pedidos")
@Getter
@Setter
public class PedidoContaEntidade {

    @Id
    private String uuid;

    private String nome;

    @Indexed(unique = true)
    private String email;
    private String logradouro;
    private String numero;
    private String bairro;
    private String cep;
    private String cidade;
    private String estado;

    @Indexed(unique = true)
    private String cpf;
    private String rg;
    private String estadoCivil;
    private String dataNascimento;
    private String sexo;

}
