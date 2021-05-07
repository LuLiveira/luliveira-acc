package br.com.luliveira.nu.repositories;


import br.com.luliveira.nu.repositories.entidade.PedidoContaEntidade;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CadastroNovoPedidoRepository extends MongoRepository<PedidoContaEntidade, String> {
}
