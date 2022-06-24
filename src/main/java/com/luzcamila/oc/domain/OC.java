package com.luzcamila.oc.domain;

import java.time.LocalDateTime;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.luzcamila.oc.domain.enuns.Prioridade;

import com.luzcamila.oc.domain.enuns.Status;

@Entity 
public class OC {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)	
	private Integer id;
	
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm")
	private LocalDateTime dataAbertura;
	
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm")
	private LocalDateTime dataFechamento;
	
	private Integer prioridade;
	private String observacoes;
	private Integer status;
	
	@ManyToOne
	@JoinColumn(name= "vendedora_id")
	private Vendedora vendedora;
	
	@ManyToOne
	@JoinColumn(name= "cliente_id")
	private Cliente cliente;
	
	public OC() {
		super();
		this.setDataAbertura(LocalDateTime.now());
		this.setPrioridade(Prioridade.BAIXA);
		this.setStatus(Status.ABERTO);
	}

	public OC(Integer id, Prioridade prioridade,
			String observacoes, Status status, Vendedora vendedora, Cliente cliente) {
		super();
		this.id = id;
		this.setDataAbertura(LocalDateTime.now());
		this.prioridade = (prioridade == null) ? 0 : prioridade.getCod();
		this.observacoes = observacoes;
		this.status = (status == null) ? 0 : status.getCod();
		this.vendedora = vendedora;
		this.cliente = cliente;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public LocalDateTime getDataAbertura() {
		return dataAbertura;
	}

	public void setDataAbertura(LocalDateTime dataAbertura) {
		this.dataAbertura = dataAbertura;
	}

	public LocalDateTime getDataFechamento() {
		return dataFechamento;
	}

	public void setDataFechamento(LocalDateTime dataFechamento) {
		this.dataFechamento = dataFechamento;
	}

	//Necessário devido estar esperando um tipo enumerado e o valor passado é inteiro
	public Prioridade getPrioridade() {
		return Prioridade.toEnum(this.prioridade);
	}

	//Alterado também devido a tipo enumerado
	public void setPrioridade(Prioridade prioridade) {
		this.prioridade = prioridade.getCod();
	}

	public String getObservacoes() {
		return observacoes;
	}

	public void setObservacoes(String observacoes) {
		this.observacoes = observacoes;
	}
	//Tratamento para o enumerado
	public Status getStatus() {
		return Status.toEnum(this.status);
	}
	//Tratamento para o enumerado
	public void setStatus(Status status) {
		this.status = status.getCod();
	}

	public Vendedora getVendedora() {
		return vendedora;
	}

	public void setVendedora(Vendedora vendedora) {
		this.vendedora = vendedora;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OC other = (OC) obj;
		return Objects.equals(id, other.id);
	}
	
}
