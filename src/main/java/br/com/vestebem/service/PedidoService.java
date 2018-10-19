package br.com.vestebem.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.vestebem.model.ItemPedido;
import br.com.vestebem.model.PagamentoBoleto;
import br.com.vestebem.model.Pedido;
import br.com.vestebem.model.enums.EstadoPagamento;
import br.com.vestebem.repositories.ItemPedidoRepository;
import br.com.vestebem.repositories.PagamentoRepository;
import br.com.vestebem.repositories.PedidoRepository;
import br.com.vestebem.service.exceptions.ObjectNotFoundException;

@Service
public class PedidoService {
	@Autowired
	private PedidoRepository pedidoRepository;

	@Autowired
	private boletoService boletoService;

	@Autowired
	private PagamentoRepository pagamentoRepository;

	@Autowired
	private ProdutoService produtoService;

	@Autowired
	private ItemPedidoRepository itemPedidoRepository;

	public List<Pedido> findall() {
		return pedidoRepository.findAll();
	}

	public Pedido findById(Integer id) {
		Optional<Pedido> pedidos = pedidoRepository.findById(id);
		return pedidos.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto com o " + "id " + id + " não encontrado, tipo: " + Pedido.class.getName()));
	}

	public Pedido insert(Pedido pedido) {
		pedido.setId(null);
		pedido.setInstante(new Date());
		pedido.getPagamento().setEstado(EstadoPagamento.PENDENTE);
		pedido.getPagamento().setPedido(pedido);
		if (pedido.getPagamento() instanceof PagamentoBoleto) {
			PagamentoBoleto pagamento = (PagamentoBoleto) pedido.getPagamento();
			boletoService.preencherPagamentoComBoleto(pagamento, pedido.getInstante());
		}

		pedido = pedidoRepository.save(pedido);
		pagamentoRepository.save(pedido.getPagamento());

		for (ItemPedido itemPedido : pedido.getItens()) {
			itemPedido.setDesconto(0.0);
			itemPedido.setPreco(produtoService.findById(itemPedido.getProduto().getId()).getPreco());
			itemPedido.setPedido(pedido);
			
		}
		itemPedidoRepository.saveAll(pedido.getItens());
		return pedido;
	}

}
