package br.com.vestebem.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.vestebem.model.Pedido;
import br.com.vestebem.repositories.PedidoRepository;
import br.com.vestebem.service.exceptions.ObjectNotFoundException;

@Service
public class PedidoService {
	@Autowired
	private PedidoRepository pedidoRepository;

	public List<Pedido> findall() {
		return pedidoRepository.findAll();
	}
	public Pedido findById(Integer id) {
		Optional<Pedido> pedidos = pedidoRepository.findById(id);
		return pedidos.orElseThrow(
				()->new ObjectNotFoundException("Objeto com o "
				+ "id "+id+" não encontrado, tipo: "
				+Pedido.class.getName()));
	}
}
