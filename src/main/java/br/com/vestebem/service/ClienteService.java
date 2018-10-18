package br.com.vestebem.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import br.com.vestebem.model.Cidade;
import br.com.vestebem.model.Cliente;
import br.com.vestebem.model.Endereco;
import br.com.vestebem.model.dto.ClienteDto;
import br.com.vestebem.model.dto.ClienteNewDto;
import br.com.vestebem.model.enums.TipoCliente;
import br.com.vestebem.repositories.ClienteRepository;
import br.com.vestebem.repositories.EnderecoRepository;
import br.com.vestebem.service.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {
	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private EnderecoRepository enderecoRepository;

	public Cliente findById(Integer id) {
		Optional<Cliente> clientes = clienteRepository.findById(id);
		return clientes.orElseThrow(
				()->new ObjectNotFoundException("Objeto com o "
				+ "id "+id+" não encontrado, tipo: "
				+Cliente.class.getName()));
	}


	public List<Cliente> findall() {
		return clienteRepository.findAll();
	}


	public Cliente insert(Cliente cliente) {
		cliente.setId(null);
		clienteRepository.save(cliente);
		enderecoRepository.saveAll(cliente.getEnderecos());
		return cliente;
	}

	public Cliente update(Cliente cliente) {
		Cliente clienteBd = this.findById(cliente.getId());
		updateData(clienteBd, cliente);
		return clienteRepository.save(clienteBd);
	}
	
	public void delete(Integer id) {
		this.findById(id);
		try {
			clienteRepository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new br.com.vestebem.service.exceptions.
					DataIntegrityViolationException("Não é possivel excluir "
					+ "clientes com produtos"
					+ " associados");
		}
		
	}
	public Cliente fromDto(ClienteDto clienteDto){
		return new Cliente(clienteDto.getId(), clienteDto.getNome(), clienteDto.getEmail(), null, null);
	}
	public Cliente fromDto(ClienteNewDto clienteDto) throws Exception{
		
		Cliente cliente =  new Cliente(null, 
				clienteDto.getNome(), 
				clienteDto.getEmail(), 
				clienteDto.getCpfOuCnpj(), 
				TipoCliente.toEnum(clienteDto.getTipo()));
		
		Cidade cidade = new Cidade(clienteDto.getCidadeId(), null,null);
		
		Endereco endereco = new Endereco(null, 
				clienteDto.getLogradouro(), 
				clienteDto.getNumero(), 
				clienteDto.getComplemento(), 
				clienteDto.getBairro(), 
				clienteDto.getCep(), 
				cliente, 
				cidade);
		
		cliente.getEnderecos().add(endereco);
		cliente.getTelefones().add(clienteDto.getTelefone1());
		
		if(clienteDto.getTelefone2()!=null) {
			cliente.getTelefones().add(clienteDto.getTelefone2());
		}
		if(clienteDto.getTelefone3()!=null) {
			cliente.getTelefones().add(clienteDto.getTelefone3());
		}
		return cliente;
	}
	private void updateData(Cliente clienteBd, Cliente cliente) {
		clienteBd.setNome(cliente.getNome());
		clienteBd.setEmail(cliente.getEmail());
	}
}
