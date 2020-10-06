package br.com.dhcorp.model.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.dhcorp.model.entities.Cliente;

@Repository
public interface ClienteRepository extends CrudRepository<Cliente, Integer> {
	Cliente findOneByNome(String name);
	List<Cliente> findAllByCpfContaining(String numero);
}
