package com.luzcamila.oc.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.apache.tomcat.jni.OS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luzcamila.oc.domain.Cliente;
import com.luzcamila.oc.domain.OC;
import com.luzcamila.oc.domain.Vendedora;
import com.luzcamila.oc.domain.enuns.Prioridade;
import com.luzcamila.oc.domain.enuns.Status;
import com.luzcamila.oc.dtos.OCDTO;
import com.luzcamila.oc.repositories.OCRepository;
import com.luzcamila.oc.services.exceptions.ObjectNotFoundException;

@Service
public class OcService {
	
	@Autowired
	private OCRepository repository;
	
	@Autowired
	private VendedoraService vendedoraService;
	
	@Autowired
	private ClienteService clienteService;
		
	public OC findById(Integer id) {
		Optional<OC> obj = repository.findById(id);
		return obj.orElseThrow(()->new ObjectNotFoundException(
				"Objeto não encontrado! Id: " +id +", Tipo: " + OS.class.getName()));
	}
	
	public List<OC> findAll(){
		return repository.findAll();
		
	}

	public OC create(@Valid OCDTO obj) {
		return fromDTO(obj);
	}
	
	public OC update(@Valid OCDTO obj) {
		findById(obj.getId());
		return fromDTO(obj);
	}
	
	public OC fromDTO(OCDTO obj) {
		OC newObj = new OC();
		newObj.setId(obj.getId());
		newObj.setObservacoes(obj.getObservacoes());
		newObj.setPrioridade(Prioridade.toEnum(obj.getPrioridade()));
		newObj.setStatus(Status.toEnum(obj.getStatus()));
		
		Vendedora vend = vendedoraService.findById(obj.getVendedora());
		Cliente cli = clienteService.findById(obj.getCliente());
		
		newObj.setVendedora(vend);
		newObj.setCliente(cli);
		
		if(newObj.getStatus().getCod().equals(2)) {
			newObj.setDataFechamento(LocalDateTime.now());
		}
		
		return repository.save(newObj);
	}
	
}
