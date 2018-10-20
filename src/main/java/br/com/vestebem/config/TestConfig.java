package br.com.vestebem.config;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import br.com.vestebem.service.DbService;

@Configuration
@Profile("test")
public class TestConfig {
	@Autowired
	private DbService dbService;
	
	@Bean
	public boolean instantateDataBase() throws ParseException {
		dbService.instantiateTestDataBase();
		return true;
	}
}
