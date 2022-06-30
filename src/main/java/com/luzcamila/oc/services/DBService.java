package com.luzcamila.oc.services;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luzcamila.oc.domain.Cliente;
import com.luzcamila.oc.domain.OC;
import com.luzcamila.oc.domain.Vendedora;
import com.luzcamila.oc.domain.enuns.Prioridade;
import com.luzcamila.oc.domain.enuns.Status;
import com.luzcamila.oc.repositories.ClienteRepository;
import com.luzcamila.oc.repositories.OCRepository;
import com.luzcamila.oc.repositories.VendedoraRepository;

@Service
public class DBService {
	
	//Do o instantiation
	@Autowired
	private VendedoraRepository vendedoraRepository;
	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private OCRepository ocRepository;
	
	public void instanciaDB() {
		//Create Seller instance
		Vendedora v1 = new Vendedora(null, "Peter Viegas", "753.358.170-97", "(88) 98888-8888");
		Vendedora v2 = new Vendedora(null, "Linus Torvalds", "641.760.040-88", "(88) 94545-4545");
		
		//Create Customer instance
		Cliente c1 = new Cliente(null, "Betina Campos", "841.963.660-66", "(88) 98888-7777");
		
		OC oc1 = new OC(null, Prioridade.ALTA, "Teste create OC", Status.ANDAMENTO, v1, c1);
		
		//Save to database
		v1.getList().add(oc1);
		c1.getList().add(oc1);
		
		vendedoraRepository.saveAll(Arrays.asList(v1,v2));
		clienteRepository.saveAll(Arrays.asList(c1));
		ocRepository.saveAll(Arrays.asList(oc1));
	}
}
