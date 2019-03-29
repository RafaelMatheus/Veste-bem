package br.com.vestebem.config;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import br.com.vestebem.service.DbService;
import br.com.vestebem.service.SmtpEmailService;

@Configuration
@Profile("dev")
public class DevConfig {
	@Autowired
	private DbService dbService;
	
	@Value("$(spring.jpa.hibernate.ddl-auto)")
	private String strategy;

	@Bean
	public boolean instantateDataBase() throws ParseException {
		if("create".equals(strategy)) {
			dbService.instantiateTestDataBase();
			return true;
		}
		return false;
	}
	
	@Bean
	public SmtpEmailService emailService() {
		return new SmtpEmailService();
	}
}