package com.luzcamila.oc.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.luzcamila.oc.services.DBService;

@Configuration	//Anotação
@Profile("dev")
public class DevConfig {
	
	//Fazer a injeção do Serviço
	@Autowired
	private DBService dbService;
	
	//Verifica se vai instanciar olhando o arquivo application-dev.properties
	@Value("${spring.jpa.hibernate.ddl-auto}")
	private String ddl;

	@Bean		//Anotação sempre quando for instaciado DB Server ele vai procurar
	public boolean instanciaDB(){
		if(ddl.equals("create")) {
			this.dbService.instanciaDB();
		}
		return false; 
	}
}
