package com.luzcamila.oc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.luzcamila.oc.domain.Pessoa;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Integer>{

	@Query("Select obj from Pessoa obj where obj.cpf =:cpf")
	Pessoa findByCPF(@Param("cpf") String cpf);
	
	
}
