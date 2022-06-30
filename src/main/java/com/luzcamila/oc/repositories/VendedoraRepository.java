package com.luzcamila.oc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.luzcamila.oc.domain.Vendedora;

@Repository
public interface VendedoraRepository extends JpaRepository<Vendedora, Integer>{

	@Query("Select obj from Vendedora obj where obj.cpf =:cpf")
	Vendedora findByCPF(@Param("cpf") String cpf);
	
	
}
