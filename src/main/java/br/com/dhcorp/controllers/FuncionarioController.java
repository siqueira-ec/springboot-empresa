package br.com.dhcorp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.dhcorp.model.entities.Funcionario;
import br.com.dhcorp.model.repositories.FuncionarioRepository;

@RestController
@RequestMapping(path = "/funcionario")
public class FuncionarioController {
	@Autowired
	private FuncionarioRepository funcionarioRepository;
	
	@GetMapping
	public Iterable<Funcionario> getAllFuncionarios() {
		return funcionarioRepository.findAll();
	}
	
	@PostMapping("/cadastro")
	public Funcionario addFuncionario(@RequestBody Funcionario funcionario) {
		funcionarioRepository.save(funcionario);
		return funcionario;
	}
	
	@PutMapping("/reajuste/{idFuncionario}")
	public void changeSalary(@PathVariable int idFuncionario, @RequestBody Double newSalary) throws Exception{
		Funcionario func = funcionarioRepository.findById(idFuncionario)
				.orElseThrow(() -> new IllegalAccessException());
				
		if(newSalary > 0 && newSalary != func.getSalario()) {
			func.setSalario(newSalary);
		}
		
		funcionarioRepository.save(func);
		
	}
	
	@GetMapping("/demissao/{idFuncionario}")
	public void demissFunc(@PathVariable int idFuncionario) throws Exception{
		Funcionario func = funcionarioRepository.findById(idFuncionario)
				.orElseThrow(() -> new IllegalAccessException());
				
		if(func.getHabilitado() == 1) {
			func.setHabilitado(0);
		}
		
		funcionarioRepository.save(func);
	}
}
