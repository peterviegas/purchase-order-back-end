package com.luzcamila.oc.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.luzcamila.oc.domain.Vendedora;
import com.luzcamila.oc.dtos.VendedoraDTO;
import com.luzcamila.oc.services.VendedoraService;

@CrossOrigin("*")
@RestController
@RequestMapping(value = "/vendedoras")
public class VendedoraResource {
	// localhost:8080/vendedoras/1
	@Autowired
	private VendedoraService service;

	@GetMapping(value = "/{id}")
	public ResponseEntity<VendedoraDTO> findByID(@PathVariable Integer id){
		VendedoraDTO objDTO = new VendedoraDTO(service.findById(id));
		return ResponseEntity.ok().body(objDTO);
	}
	
	@GetMapping
	public ResponseEntity<List<VendedoraDTO>>findAll(){
		
		//Três formas de construir este código
		//Primeiro
		/*List<Vendedora> list = service.findAll();
		List<VendedoraDTO> listDTO = new ArrayList<>();
		
		for(Vendedora obj : list) {
			listDTO.add(new VendedoraDTO(obj));
		}*/
		
		//Segundo
		/*List<Vendedora> list = service.findAll();
		List<VendedoraDTO> listDTO = new ArrayList<>();
		
		list.forEach(obj ->listDTO.add(new VendedoraDTO(obj)));*/
		
		//Terceira
		List<VendedoraDTO> listDTO = service.findAll()
				.stream().map(obj -> new VendedoraDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDTO);
	}
	
	@PostMapping
	public ResponseEntity<VendedoraDTO>create(@Valid @RequestBody VendedoraDTO objDTO){
		Vendedora newObj = service.create(objDTO);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(newObj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	/*
	 * Atualiza Vendedor
	 */
	@PutMapping(value = "/{id}")
	public ResponseEntity<VendedoraDTO>update(@PathVariable Integer id, @Valid @RequestBody VendedoraDTO objDTO){
		VendedoraDTO newObj = new VendedoraDTO(service.update(id, objDTO));
		
		return ResponseEntity.ok().body(newObj);
	}
	
	/*
	 * Deleta Vededor
	 */
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void>delete(@PathVariable Integer id){
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
}
