package com.luzcamila.oc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.luzcamila.oc.domain.OC;

@Repository
public interface OCRepository extends JpaRepository<OC, Integer>{
	
}
