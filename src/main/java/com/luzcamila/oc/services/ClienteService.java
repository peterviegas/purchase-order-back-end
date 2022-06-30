package com.luzcamila.oc.services;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luzcamila.oc.domain.Cliente;
import com.luzcamila.oc.domain.Pessoa;
import com.luzcamila.oc.dtos.ClienteDTO;
import com.luzcamila.oc.repositories.ClienteRepository;
import com.luzcamila.oc.repositories.PessoaRepository;
import com.luzcamila.oc.services.exceptions.DataIntegratyViolationException;
import com.luzcamila.oc.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {
	@Autowired
	private ClienteRepository repository;
	
	@Autowired
	private PessoaRepository pessoaRepository;
	
	public Cliente findById(Integer id) {
		Optional<Cliente> obj= repository.findById(id);
		
		return obj.orElseThrow(()->new ObjectNotFoundException(
				"Objeto não encontrado! ID: " + id + ", Tipo: " + Cliente.class.getName()));
	}

	public List<Cliente> findAll() {
		return repository.findAll();
	}
	
	public Cliente create(ClienteDTO objDTO) {
		if(findByCPF(objDTO)!= null) {
			throw new DataIntegratyViolationException("CPF já cadastrado na base de dados!");
		}
		
		Cliente newObj = new Cliente(null, objDTO.getNome(),objDTO.getCpf(), objDTO.getTelefone());
		return repository.save(newObj);
		
		//Outra forma
		//return repository.save(new Cliente(null, objDTO.getNome(),objDTO.getCpf(), objDTO.getTelefone()));
	}
	
	public Cliente update(Integer id, @Valid ClienteDTO objDTO) {
		Cliente oldObj = findById(id);
		
		if(findByCPF(objDTO)!= null && findByCPF(objDTO).getId() != id) {
			throw new DataIntegratyViolationException("CPF já cadastrado na base de dados!");
		}
		
		oldObj.setNome(objDTO.getNome());
		oldObj.setCpf(objDTO.getCpf());
		oldObj.setTelefone(objDTO.getTelefone());
		
		return repository.save(oldObj);
	}
	
	public void delete(Integer id) {
		Cliente obj = findById(id);
		if(obj.getList().size()>0) {
			throw new DataIntegratyViolationException("Cliente possui Ordem de Compra, não pode ser deletado(a)!");
		}
		repository.deleteById(id);
	}
	/*
	 * Busca Cliente / Cliente por CPF
	 */
	private Pessoa findByCPF(ClienteDTO objDTO) {
		Pessoa obj = pessoaRepository.findByCPF(objDTO.getCpf());
		
		if(obj!=null) {
			return obj;
		}
		return null;
	}



	
}
