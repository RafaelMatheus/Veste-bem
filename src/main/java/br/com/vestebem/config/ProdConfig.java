package br.com.vestebem.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import br.com.vestebem.service.SmtpEmailService;

@Configuration
@Profile("prod")
public class ProdConfig {

	
	@Bean
	public SmtpEmailService emailService() {
		return new SmtpEmailService();
	}
}