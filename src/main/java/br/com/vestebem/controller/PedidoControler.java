package br.com.vestebem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.vestebem.model.Pedido;
import br.com.vestebem.service.PedidoService;

@RestController
@RequestMapping(value="/pedidos")
public class PedidoControler {
	@Autowired
	PedidoService pedidoService;
	
	@RequestMapping(value="/{id}",method=RequestMethod.GET)
	public ResponseEntity<?> findById(@PathVariable Integer id) {
		 Pedido pedido = pedidoService.findById(id);
		 return ResponseEntity.ok().body(pedido);
	}
}
