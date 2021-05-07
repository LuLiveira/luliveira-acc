package br.com.luliveira.nu.repositories;


import br.com.luliveira.nu.repositories.entidade.UsuarioEntidade;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CadastroNovoUsuarioRepository extends CrudRepository<UsuarioEntidade, String> {
}
