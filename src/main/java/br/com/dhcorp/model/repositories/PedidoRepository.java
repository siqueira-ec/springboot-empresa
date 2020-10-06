package br.com.dhcorp.model.repositories;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.dhcorp.model.entities.Pedido;

@Repository
public interface PedidoRepository extends CrudRepository<Pedido, String> {
	Page<Pedido> findByClienteId(Integer clienteId, Pageable pageable);
    Optional<Pedido> findByNfeAndClienteId(String nfe, Integer clienteId);
}
