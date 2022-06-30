package com.luzcamila.oc.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.luzcamila.oc.services.DBService;

@Configuration	//Anotação
@Profile("test")
public class TestConfig {
	
	//Fazer a injeção do Serviço
	@Autowired
	private DBService dbService;
	
	@Bean		//Anotação sempre quando for instaciado DB Server ele vai procurar
	public void instanciaDB(){
		this.dbService.instanciaDB();
	}
}
