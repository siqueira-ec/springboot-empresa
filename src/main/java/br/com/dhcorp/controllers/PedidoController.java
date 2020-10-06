package br.com.dhcorp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.dhcorp.exceptions.ResourceNotFoundException;
import br.com.dhcorp.model.entities.Pedido;
import br.com.dhcorp.model.repositories.ClienteRepository;
import br.com.dhcorp.model.repositories.PedidoRepository;

@RestController
public class PedidoController {
	@Autowired
	private PedidoRepository pedidoRepository;

	@Autowired
	private ClienteRepository clienteRepository;

	@GetMapping("/cliente/{idCliente}/pedido")
	public Page<Pedido> getAllPedidosByClientId(@PathVariable int idCliente, Pageable pageable) {
		return pedidoRepository.findByClienteId(idCliente, pageable);
	}

	@GetMapping("/pedido")
	public Iterable<Pedido> getAllPedidos() {
		return pedidoRepository.findAll();
	}

	@GetMapping("/pedido/{nfe}")
	public Pedido getPedidoByNfe(@PathVariable String nfe) throws Exception {
		return pedidoRepository.findById(nfe).orElseThrow(() -> new ResourceNotFoundException("Nfe " + nfe + " não encontrada"));
	}

	@PostMapping("/cliente/{idCliente}/pedido")
	public void savePedido(@PathVariable int idCliente, @RequestBody Pedido pedido) throws Exception {
		clienteRepository.findById(idCliente).map(cliente -> {
			pedido.setCliente(cliente);
			return pedidoRepository.save(pedido);
		}).orElseThrow(() -> new ResourceNotFoundException("idCliente " + idCliente + " não encontrado"));
	}

	@PutMapping("/cliente/{idCliente}/pedido/{nfe}")
	public void updatePedido(@PathVariable int idCliente, @PathVariable String nfe, @RequestBody Pedido pedido)
			throws Exception {
		if (!clienteRepository.existsById(idCliente)) {
			throw new ResourceNotFoundException("idCliente " + idCliente + " não encontrado");
		}
		
		pedidoRepository.findById(nfe).map(p -> {
			p.setStatus_pedido(pedido.getStatus_pedido());
			return pedidoRepository.save(p);
		}).orElseThrow(() -> new ResourceNotFoundException("Nfe " + nfe + " não encontrada"));
	}
	
	 @DeleteMapping("/cliente/{idCliente}/pedido/{nfe}")
	 public void deletePedido(@PathVariable int idCliente, @PathVariable String nfe) {
		 pedidoRepository.findByNfeAndClienteId(nfe, idCliente).map(pedido -> {
			 pedidoRepository.deleteById(pedido.getNfe());
			 return ResponseEntity.ok().build();
		 }).orElseThrow(() -> new ResourceNotFoundException("Pedido não encontrado com a nfe " + nfe + " e idCliente " + idCliente));
	 }
}
