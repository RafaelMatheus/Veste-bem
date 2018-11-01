package br.com.vestebem.controller;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.vestebem.model.Cliente;
import br.com.vestebem.model.dto.ClienteDto;
import br.com.vestebem.model.dto.ClienteNewDto;
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
	
	@RequestMapping(value="/email",method=RequestMethod.GET)
	public ResponseEntity<Cliente> findByEmail(@RequestParam(value="value") String email) {
		 Cliente cliente = clienteService.findByEmail(email);
		 return ResponseEntity.ok().body(cliente);
	}
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<ClienteDto>> findAll() {
		List<Cliente> clientes = clienteService.findall();
		List<ClienteDto> listDto = clientes.stream().map(obje -> new ClienteDto(obje)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDto);
	}
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody ClienteNewDto clienteDto) throws Exception{
		Cliente cliente = clienteService.fromDto(clienteDto);
		cliente = clienteService.insert(cliente);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(cliente.getId())
				.toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@Valid @RequestBody ClienteDto clienteDto, @PathVariable Integer id) {
		Cliente cliente = clienteService.fromDto(clienteDto);
		cliente.setId(id);
		cliente = clienteService.update(cliente);
		return ResponseEntity.noContent().build();
	}
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		clienteService.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value="/picture", method = RequestMethod.POST)
	public ResponseEntity<Void> insertPicture(@RequestParam(name="file")MultipartFile file){
		URI uri = clienteService.uploadProfilePicture(file);
		return ResponseEntity.created(uri).build();
	}
}
