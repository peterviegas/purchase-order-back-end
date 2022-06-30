package com.luzcamila.oc.dtos;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.br.CPF;

import com.luzcamila.oc.domain.Vendedora;

	public class VendedoraDTO implements Serializable{
	private static final long serialVersionUID = 1L;
		
	private Integer id;
	
	@NotEmpty(message = "O campo Nome é requerido")
	private String nome;
	
	//Valida CPF (automatizado)
	@CPF
	@NotEmpty(message = "O campo CPF é requerido")
	private String cpf;
	
	@NotEmpty(message = "O campo Telefone é requerido")
	private String telefone;
	
	public VendedoraDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public VendedoraDTO(Vendedora obj) {
		super();
		this.id = obj.getId();
		this.nome = obj.getNome();
		this.cpf = obj.getCpf();
		this.telefone = obj.getTelefone();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	
	
}
