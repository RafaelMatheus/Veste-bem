package br.com.vestebem.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.vestebem.model.Cliente;
import br.com.vestebem.repositories.ClienteRepository;
import br.com.vestebem.service.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {
	@Autowired
	private ClienteRepository clienteRepository;

	public List<Cliente> findall() {
		return clienteRepository.findAll();
	}
	public Cliente findById(Integer id) {
		Optional<Cliente> clientes = clienteRepository.findById(id);
		return clientes.orElseThrow(
				()->new ObjectNotFoundException("Objeto com o "
				+ "id "+id+" não encontrado, tipo: "
				+Cliente.class.getName()));
	}
}
