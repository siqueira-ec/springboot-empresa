package br.com.dhcorp.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.dhcorp.model.entities.Cliente;
import br.com.dhcorp.model.repositories.ClienteRepository;

@RestController
@RequestMapping(path = "/cliente")
public class ClienteController {
	@Autowired
	private ClienteRepository clienteRepository;
	
	@GetMapping()
	public Iterable<Cliente> getClientes() {
		return clienteRepository.findAll();
	}
	
	@GetMapping("/cpf")
	public Iterable<Cliente> getClientesByPartialCpf(@RequestParam String numero) {
		return clienteRepository.findAllByCpfContaining(numero);
	}
	
	@GetMapping("/{id}")
	public Optional<Cliente> getClienteById(@PathVariable int id) {
		return clienteRepository.findById(id);
	}
	
	@PostMapping()
	public void saveCliente(@RequestBody Cliente cliente) {
		clienteRepository.save(cliente);
	}
	
	@PutMapping("/{id}")
	public void updateCliente(@PathVariable int id, @RequestBody Cliente cliente)  {
		Cliente c = clienteRepository.findById(id).orElseThrow(() -> new IllegalAccessError());
		
		if (!(cliente.getCpf().isEmpty())) {
			c.setCpf(cliente.getCpf());
		}
		
		if (!(cliente.getNome().isEmpty())) {
			c.setNome(cliente.getNome());
		}
		
		clienteRepository.save(c);
	}
	
	@DeleteMapping("/{id}")
	public void deleteCliente(@PathVariable int id) {
		clienteRepository.deleteById(id);
	}
}
