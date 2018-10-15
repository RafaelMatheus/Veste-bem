package br.com.vestebem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.vestebem.model.Cliente;
import br.com.vestebem.service.ClienteService;

@RestController
@RequestMapping(value="/clientes")
public class ClientesController {
	@Autowired
	ClienteService clienteService;
	
	@RequestMapping(value="/{id}",method=RequestMethod.GET)
	public ResponseEntity<Cliente> findById(@PathVariable Integer id) {
		 Cliente cliente = clienteService.findById(id);
		 return ResponseEntity.ok().body(cliente);
	}
}
