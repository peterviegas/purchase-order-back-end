package com.luzcamila.oc.services;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luzcamila.oc.domain.Pessoa;
import com.luzcamila.oc.domain.Vendedora;
import com.luzcamila.oc.dtos.VendedoraDTO;
import com.luzcamila.oc.repositories.PessoaRepository;
import com.luzcamila.oc.repositories.VendedoraRepository;
import com.luzcamila.oc.services.exceptions.DataIntegratyViolationException;
import com.luzcamila.oc.services.exceptions.ObjectNotFoundException;

@Service
public class VendedoraService {
	@Autowired
	private VendedoraRepository repository;
	
	@Autowired
	private PessoaRepository pessoaRepository;
	
	public Vendedora findById(Integer id) {
		Optional<Vendedora> obj= repository.findById(id);
		
		return obj.orElseThrow(()->new ObjectNotFoundException(
				"Objeto não encontrado! ID: " + id + ", Tipo: " + Vendedora.class.getName()));
	}

	public List<Vendedora> findAll() {
		return repository.findAll();
	}
	
	public Vendedora create(VendedoraDTO objDTO) {
		if(findByCPF(objDTO)!= null) {
			throw new DataIntegratyViolationException("CPF já cadastrado na base de dados!");
		}
		
		Vendedora newObj = new Vendedora(null, objDTO.getNome(),objDTO.getCpf(), objDTO.getTelefone());
		return repository.save(newObj);
		
		//Outra forma
		//return repository.save(new Vendedora(null, objDTO.getNome(),objDTO.getCpf(), objDTO.getTelefone()));
	}
	
	public Vendedora update(Integer id, @Valid VendedoraDTO objDTO) {
		Vendedora oldObj = findById(id);
		
		if(findByCPF(objDTO)!= null && findByCPF(objDTO).getId() != id) {
			throw new DataIntegratyViolationException("CPF já cadastrado na base de dados!");
		}
		
		oldObj.setNome(objDTO.getNome());
		oldObj.setCpf(objDTO.getCpf());
		oldObj.setTelefone(objDTO.getTelefone());
		
		return repository.save(oldObj);
	}
	
	public void delete(Integer id) {
		Vendedora obj = findById(id);
		if(obj.getList().size()>0) {
			throw new DataIntegratyViolationException("Vendedor(a) possui Ordem de Compra, não pode ser deletado(a)!");
		}
		repository.deleteById(id);
	}
	/*
	 * Busca Vendedora / Cliente por CPF
	 */
	private Pessoa findByCPF(VendedoraDTO objDTO) {
		Pessoa obj = pessoaRepository.findByCPF(objDTO.getCpf());
		
		if(obj!=null) {
			return obj;
		}
		return null;
	}



	
}
