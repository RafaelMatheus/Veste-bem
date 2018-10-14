package br.com.vestebem;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.vestebem.model.Categoria;
import br.com.vestebem.repositories.CategoriaRepository;

@SpringBootApplication
public class VestebemApplication implements CommandLineRunner {
	@Autowired
	private CategoriaRepository categoriaService;
	
	public static void main(String[] args) {
		SpringApplication.run(VestebemApplication.class, args);
		
	}

	@Override
	public void run(String... args) throws Exception {
		Categoria cat1 = new Categoria(null, "Informatica");
		Categoria cat2 = new Categoria(null, "Escritorio");
		categoriaService.saveAll(Arrays.asList(cat1,cat2));
		
		
	}


}
