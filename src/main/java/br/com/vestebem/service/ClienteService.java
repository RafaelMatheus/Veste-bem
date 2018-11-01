package br.com.vestebem.service;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import br.com.vestebem.model.Cidade;
import br.com.vestebem.model.Cliente;
import br.com.vestebem.model.Endereco;
import br.com.vestebem.model.dto.ClienteDto;
import br.com.vestebem.model.dto.ClienteNewDto;
import br.com.vestebem.model.enums.Perfil;
import br.com.vestebem.model.enums.TipoCliente;
import br.com.vestebem.repositories.ClienteRepository;
import br.com.vestebem.repositories.EnderecoRepository;
import br.com.vestebem.security.UserSS;
import br.com.vestebem.service.exceptions.AuthorizationException;
import br.com.vestebem.service.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {
	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private EnderecoRepository enderecoRepository;
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	@Autowired
	private S3Service s3Service;
	public Cliente findById(Integer id) {
		UserSS user = new UserService().authenticated();
		if(user == null || !user.hasRole(Perfil.ADMIN) && id.equals(user.getId())) {
			throw new AuthorizationException("Acesso negado");
		}
		
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
					+ "clientes com pedidos"
					+ " associados");
		}
		
	}
	public Cliente fromDto(ClienteDto clienteDto){
		return new Cliente(clienteDto.getId(), clienteDto.getNome(), clienteDto.getEmail(), null, null, null);
	}
	public Cliente fromDto(ClienteNewDto clienteDto) throws Exception{
		
		Cliente cliente =  new Cliente(null, 
				clienteDto.getNome(), 
				clienteDto.getEmail(), 
				clienteDto.getCpfOuCnpj(), 
				TipoCliente.toEnum(clienteDto.getTipo()),
				bCryptPasswordEncoder.encode(clienteDto.getSenha()));
		
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
	
	public URI uploadProfilePicture(MultipartFile file) {
		UserSS user = UserService.authenticated();
		
		if(user == null)
			throw new AuthorizationException("Acesso negado");
		
		URI uri = s3Service.uploadFile(file);
		
		Optional<Cliente> cli = clienteRepository.findById(user.getId());
		cli.orElse(null).setImagemUrll(uri.toString());
		clienteRepository.save(cli.orElse(null));
		
		return uri;
	
		
	}
}
