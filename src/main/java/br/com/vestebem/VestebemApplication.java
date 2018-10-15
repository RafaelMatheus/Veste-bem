package br.com.vestebem;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.vestebem.model.Categoria;
import br.com.vestebem.model.Cidade;
import br.com.vestebem.model.Cliente;
import br.com.vestebem.model.Endereco;
import br.com.vestebem.model.Estado;
import br.com.vestebem.model.Produto;
import br.com.vestebem.model.enums.TipoCliente;
import br.com.vestebem.repositories.CategoriaRepository;
import br.com.vestebem.repositories.CidadeRepository;
import br.com.vestebem.repositories.ClienteRepository;
import br.com.vestebem.repositories.EnderecoRepository;
import br.com.vestebem.repositories.EstadoRepository;
import br.com.vestebem.repositories.ProdutoRepository;

@SpringBootApplication
public class VestebemApplication implements CommandLineRunner {
	@Autowired
	private CategoriaRepository categoriaRepository;
	@Autowired
	private ProdutoRepository produtoRepository;
	@Autowired
	private EstadoRepository estadoRepository;
	@Autowired
	private CidadeRepository cidadeRepository;
	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(VestebemApplication.class, args);

	}

	@Override
	public void run(String... args) throws Exception {
		Categoria cat1 = new Categoria(null, "Informatica");
		Categoria cat2 = new Categoria(null, "Escritório");

		Produto p1 = new Produto(null, "Computador", 2.000);
		Produto p2 = new Produto(null, "Impressora", 800.0);
		Produto p3 = new Produto(null, "Mouse", 30.00);

		cat1.getProdutos().addAll(Arrays.asList(p1, p2));
		cat1.getProdutos().addAll(Arrays.asList(p2));
		

		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));
		
		categoriaRepository.saveAll(Arrays.asList(cat1,cat2));
		produtoRepository.saveAll(Arrays.asList(p1,p2, p3));
		
		
		
		
		
		Estado est1 = new Estado(null, "Pernambuco");
		Estado est2 = new Estado(null, "Paraiba");
		
		Cidade c1 = new Cidade(null, "Itambé", est1);
		Cidade c2 = new Cidade(null, "Pedras de fogo", est2);
		Cidade c3 = new Cidade(null, "João Pessoa", est2);
		
		est1.getCidades().addAll(Arrays.asList(c1));
		est2.getCidades().addAll(Arrays.asList(c2,c3));
		
		estadoRepository.saveAll(Arrays.asList(est1,est2));
		cidadeRepository.saveAll(Arrays.asList(c1,c2,c3));
		
		
		Cliente cli1 = new Cliente(null, "Rafael", "rafael@hotmail.com.br","111.111.111-11", TipoCliente.PESSOAFISICA);
		cli1.getTelefones().addAll(Arrays.asList("2222-2222", "3333-3333"));
		
		Cliente cli2 = new Cliente(null, "Debora", "debora@hotmail.com.br","111.111.111-11", TipoCliente.PESSOAFISICA);
		cli2.getTelefones().addAll(Arrays.asList("2222-2222", "3333-3333"));
		
		Endereco e1 = new Endereco(null, "Eliud", "140", "", "Centro", "559200-000", cli1, c1);
		Endereco e2 = new Endereco(null, "Primeiro de janeiro", "283", "", "Centro", "58328-000", cli1, c2);
		Endereco e3 = new Endereco(null, "Não sei o nome", "Não sei", "Próximo ao waldecyr", "Centro", "58328-000", cli2, c2);
		cli1.getEnderecos().addAll(Arrays.asList(e1,e2));
		cli2.getEnderecos().addAll(Arrays.asList(e3));
		
		clienteRepository.saveAll(Arrays.asList(cli1, cli2));
		enderecoRepository.saveAll(Arrays.asList(e1,e2,e3));
		
	}
}
