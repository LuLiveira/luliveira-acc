package br.com.luliveira.nu.repository;


import br.com.luliveira.nu.repository.entidade.UsuarioEntidade;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CadastroNovoUsuarioRepository extends CrudRepository<UsuarioEntidade, String> {
}
