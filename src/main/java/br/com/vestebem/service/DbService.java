package br.com.vestebem.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.vestebem.model.Categoria;
import br.com.vestebem.model.Cidade;
import br.com.vestebem.model.Cliente;
import br.com.vestebem.model.Endereco;
import br.com.vestebem.model.Estado;
import br.com.vestebem.model.ItemPedido;
import br.com.vestebem.model.PagamentoBoleto;
import br.com.vestebem.model.PagamentoCartao;
import br.com.vestebem.model.Pedido;
import br.com.vestebem.model.Produto;
import br.com.vestebem.model.enums.EstadoPagamento;
import br.com.vestebem.model.enums.TipoCliente;
import br.com.vestebem.repositories.CategoriaRepository;
import br.com.vestebem.repositories.CidadeRepository;
import br.com.vestebem.repositories.ClienteRepository;
import br.com.vestebem.repositories.EnderecoRepository;
import br.com.vestebem.repositories.EstadoRepository;
import br.com.vestebem.repositories.ItemPedidoRepository;
import br.com.vestebem.repositories.PagamentoRepository;
import br.com.vestebem.repositories.PedidoRepository;
import br.com.vestebem.repositories.ProdutoRepository;

@Service
public class DbService {
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
	@Autowired
	private PedidoRepository pedidoRepository;
	@Autowired
	private PagamentoRepository pagamentoRepository;
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;

	public void instantiateTestDataBase() throws ParseException {
		
		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Escritório");
		Categoria cat3 = new Categoria(null, "Cama mesa e banho");
		Categoria cat4 = new Categoria(null, "Eletrônicos");
		Categoria cat5 = new Categoria(null, "Jardinagem");
		Categoria cat6 = new Categoria(null, "Decoração");
		Categoria cat7 = new Categoria(null, "Perfumaria");

		Produto p1 = new Produto(null, "Computador", 2.000);
		Produto p2 = new Produto(null, "Impressora", 800.0);
		Produto p3 = new Produto(null, "Mouse", 30.00);
		Produto p4 = new Produto(null, "Mesa de escritório", 3000.00);
		Produto p5 = new Produto(null, "Toalha", 3000.00);
		Produto p6 = new Produto(null, "Colcha", 200.00);
		Produto p7 = new Produto(null, "TV true color", 1200.00);
		Produto p8 = new Produto(null, "Roçadeira", 800.00);
		Produto p9 = new Produto(null, "Abajour", 100.00);
		Produto p10 = new Produto(null, "Pendente", 180.00);
		Produto p11 = new Produto(null, "Shampoo", 90.00);

		cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProdutos().addAll(Arrays.asList(p2, p4));
		cat3.getProdutos().addAll(Arrays.asList(p5, p6));
		cat4.getProdutos().addAll(Arrays.asList(p1, p2, p3, p7));
		cat5.getProdutos().addAll(Arrays.asList(p8));
		cat6.getProdutos().addAll(Arrays.asList(p9, p10));
		cat7.getProdutos().addAll(Arrays.asList(p11));

		p1.getCategorias().addAll(Arrays.asList(cat1, cat4));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2, cat4));
		p3.getCategorias().addAll(Arrays.asList(cat1, cat4));
		p4.getCategorias().addAll(Arrays.asList(cat2));
		p5.getCategorias().addAll(Arrays.asList(cat3));
		p6.getCategorias().addAll(Arrays.asList(cat3));
		p7.getCategorias().addAll(Arrays.asList(cat4));
		p8.getCategorias().addAll(Arrays.asList(cat5));
		p9.getCategorias().addAll(Arrays.asList(cat6));
		p10.getCategorias().addAll(Arrays.asList(cat6));
		p11.getCategorias().addAll(Arrays.asList(cat7));

		categoriaRepository.saveAll(Arrays.asList(cat1, cat2, cat3, cat4, cat5, cat6, cat7));
		produtoRepository.saveAll(Arrays.asList(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11));

		Estado est1 = new Estado(null, "Pernambuco");
		Estado est2 = new Estado(null, "Paraiba");

		Cidade c1 = new Cidade(null, "Itambé", est1);
		Cidade c2 = new Cidade(null, "Pedras de fogo", est2);
		Cidade c3 = new Cidade(null, "João Pessoa", est2);

		est1.getCidades().addAll(Arrays.asList(c1));
		est2.getCidades().addAll(Arrays.asList(c2, c3));

		estadoRepository.saveAll(Arrays.asList(est1, est2));
		cidadeRepository.saveAll(Arrays.asList(c1, c2, c3));

		Cliente cli1 = new Cliente(null, "Rafael", "rafaelmatheusdecastro@hotmail.com.br", "536.897.700-09", TipoCliente.PESSOAFISICA);
		cli1.getTelefones().addAll(Arrays.asList("2222-2222", "3333-3333"));

		Cliente cli2 = new Cliente(null, "Debora", "debora@hotmail.com", "507.366.830-89", TipoCliente.PESSOAFISICA);
		cli2.getTelefones().addAll(Arrays.asList("2222-2222", "3333-3333"));

		Endereco e1 = new Endereco(null, "Eliud", "140", "", "Centro", "559200-000", cli1, c1);
		Endereco e2 = new Endereco(null, "Primeiro de janeiro", "283", "", "Centro", "58328-000", cli1, c2);
		Endereco e3 = new Endereco(null, "Não sei o nome", "Não sei", "Próximo ao waldecyr", "Centro", "58328-000",
				cli2, c2);
		cli1.getEnderecos().addAll(Arrays.asList(e1, e2));
		cli2.getEnderecos().addAll(Arrays.asList(e3));

		clienteRepository.saveAll(Arrays.asList(cli1, cli2));
		enderecoRepository.saveAll(Arrays.asList(e1, e2, e3));

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		Pedido ped1 = new Pedido(null, sdf.parse("30/09/2017 10:30"), cli1, e1);
		Pedido ped2 = new Pedido(null, sdf.parse("10/10/2017 19:30"), cli1, e2);

		PagamentoCartao pagto1 = new PagamentoCartao(null, EstadoPagamento.QUITADO, ped1, 6);
		PagamentoBoleto pagto2 = new PagamentoBoleto(null, EstadoPagamento.PENDENTE, ped2,
				sdf.parse("20/10/2017 00:00"), null);

		ped2.setPagamento(pagto2);

		cli1.getPedidos().addAll(Arrays.asList(ped1, ped2));

		pagamentoRepository.saveAll(Arrays.asList(pagto1, pagto2));
		pedidoRepository.saveAll(Arrays.asList(ped1, ped2));

		ItemPedido ip1 = new ItemPedido(ped1, p1, 0.0, 1, 2000.00);
		ItemPedido ip2 = new ItemPedido(ped1, p3, 0.0, 2, 80.00);
		ItemPedido ip3 = new ItemPedido(ped2, p2, 100.0, 1, 800.00);

		ped1.getItens().addAll(Arrays.asList(ip1, ip2));
		ped2.getItens().addAll(Arrays.asList(ip3));

		p1.getItens().addAll(Arrays.asList(ip1));
		p2.getItens().addAll(Arrays.asList(ip3));
		p3.getItens().addAll(Arrays.asList(ip2));

		itemPedidoRepository.saveAll(Arrays.asList(ip1, ip2, ip3));
	}
}
