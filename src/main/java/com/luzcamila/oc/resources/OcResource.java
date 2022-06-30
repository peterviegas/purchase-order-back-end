package com.luzcamila.oc.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.luzcamila.oc.dtos.OCDTO;
import com.luzcamila.oc.services.OcService;

@CrossOrigin("*")
@RestController
@RequestMapping(value = "/oc")
public class OcResource {
	
	@Autowired
	private OcService service;
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<OCDTO> findById(@PathVariable Integer id){
		OCDTO obj = new OCDTO(service.findById(id));
		return ResponseEntity.ok().body(obj);
		
	}
	@GetMapping
	public ResponseEntity<List<OCDTO>> findAll(){
		List<OCDTO> list = service.findAll().stream()
				.map(obj -> new OCDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(list);
	}
	
	@PostMapping
	public ResponseEntity<OCDTO> create(@Valid @RequestBody OCDTO obj){
		obj = new OCDTO(service.create(obj));
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@PutMapping
	public ResponseEntity<OCDTO> update(@Valid @RequestBody OCDTO obj){
		obj = new OCDTO(service.update(obj));
		return ResponseEntity.ok().body(obj);
	}
}
