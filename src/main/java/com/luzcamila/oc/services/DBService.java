package com.luzcamila.oc.services;

import java.util.Arrays;
import java.util.Optional;

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
		Cliente c2 = new Cliente(null, "Isabella Andrade", "975.079.490-79", "(88) 98888-7777");
		
		OC oc1 = new OC(null, Prioridade.ALTA, "Teste create OC", Status.ANDAMENTO, v1, c1);
		OC oc2 = new OC(null, Prioridade.BAIXA, "Teste create OC", Status.ABERTO, v1, c1);
		
		//Save to database
		v1.getList().add(oc1);
		v1.getList().add(oc2);
		c1.getList().add(oc1);
		c1.getList().add(oc2);
		
		vendedoraRepository.saveAll(Arrays.asList(v1,v2));
		clienteRepository.saveAll(Arrays.asList(c1, c2));
		ocRepository.saveAll(Arrays.asList(oc1, oc2));
		
		//Teste de update
		// 1. Buscar a entidade existente (pelo ID)
		Optional<Vendedora> optionalVendedora = vendedoraRepository.findById(1);
		if (optionalVendedora.isPresent()) {
		    Vendedora upV1 = optionalVendedora.get();

		    // 2. Modificar os valores
		    upV1.setNome("Peter Viegas update");
		    upV1.setCpf("753.358.170-97");
		    upV1.setTelefone("(88) 98888-8888");

		    // 3. Salvar a entidade atualizada
		    vendedoraRepository.save(upV1);
		}

		// Atualização do cliente
		Optional<Cliente> optionalCliente = clienteRepository.findById(c1.getId());
		if (optionalCliente.isPresent()) {
		    Cliente upC1 = optionalCliente.get();
		    
		    // 2. Modificar os valores do cliente
		    upC1.setNome("Betina Campos update");
		    upC1.setCpf("841.963.660-66");
		    upC1.setTelefone("(88) 98888-7777");

		    // 3. Salvar a entidade atualizada
		    clienteRepository.save(upC1);
		}

		// Atualização da OC (Ordem de Compra)
		Optional<OC> optionalOC = ocRepository.findById(oc2.getId());
		if (optionalOC.isPresent()) {
		    OC upOc2 = optionalOC.get();
		    
		    // 2. Modificar os valores da OC
		    upOc2.setPrioridade(Prioridade.BAIXA);
		    upOc2.setObservacoes("Teste create OC Update");
		    upOc2.setStatus(Status.ABERTO);
		    upOc2.setVendedora(v1);
		    upOc2.setCliente(c1);

		    // 3. Salvar a entidade atualizada
		    ocRepository.save(upOc2);
		}

		
		//Teste de deleção
		vendedoraRepository.delete(v2);
		clienteRepository.delete(c2);
	}
}
