package br.com.luliveira.nu.repository.entidade;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "usuario")
public class UsuarioEntidade {

    @Id
    private String email;
    private final String password = UUID.randomUUID().toString();
}
