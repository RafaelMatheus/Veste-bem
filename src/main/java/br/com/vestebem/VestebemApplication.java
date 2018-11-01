package br.com.vestebem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.vestebem.service.S3Service;

@SpringBootApplication
public class VestebemApplication implements CommandLineRunner {
	@Autowired
	private S3Service s3Service;
	public static void main(String[] args) {
		SpringApplication.run(VestebemApplication.class, args);

	}

	@Override
	public void run(String... args) throws Exception {
		s3Service.uploadFile("/home/rafael/Imagens/Wallpapers/imagem.jpg");
	}
}
