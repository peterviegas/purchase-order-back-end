package com.luzcamila.oc.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity 
public class Vendedora extends Pessoa implements Serializable{
	private static final long serialVersionUID = 1L;

	@JsonIgnore
	@OneToMany(mappedBy = "vendedora")
	private List<OC> list = new ArrayList<>();

	public Vendedora() {
		super();		
	}

	public List<OC> getList() {
		return list;
	}

	public void setList(List<OC> list) {
		this.list = list;
	}

	public Vendedora(Integer id, String nome, String cpf, String telefone) {
		super(id, nome, cpf, telefone);
	}
	
	
}
