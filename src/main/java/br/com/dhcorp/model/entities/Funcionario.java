package br.com.dhcorp.model.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Funcionario {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String nome;
	private String cpf;
	private String email;
	private String senha;
	private Integer habilitado;
	private Double salario;
	
	public Funcionario() {
	}

	public Double getSalario() {
		return salario;
	}

	public Funcionario(String nome, String cpf, String email, String senha, Integer habilitado, Double salario) {
		this.nome = nome;
		this.cpf = cpf;
		this.email = email;
		this.senha = senha;
		this.habilitado = habilitado;
		this.salario = salario;
	}

	public void setSalario(Double salario) {
		this.salario = salario;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getHabilitado() {
		return habilitado;
	}

	public void setHabilitado(int habilitado) {
		this.habilitado = habilitado;
	}

	public Integer getId() {
		return id;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
}
