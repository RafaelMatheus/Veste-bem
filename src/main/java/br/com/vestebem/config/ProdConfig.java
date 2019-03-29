package br.com.vestebem.config;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import br.com.vestebem.service.DbService;
import br.com.vestebem.service.SmtpEmailService;

@Configuration
@Profile("prod")
public class ProdConfig {
	@Autowired
	private DbService dbService;
	
	@Bean
	public boolean instantateDataBase() throws ParseException {
			dbService.instantiateTestDataBase();
		return false;
	}
	
	@Bean
	public SmtpEmailService emailService() {
		return new SmtpEmailService();
	}
}