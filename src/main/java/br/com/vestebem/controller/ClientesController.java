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
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(value="/clientes")
public class ClientesController {
	@Autowired
	ClienteService clienteService;
	
	
	@ApiOperation(
			value="Retorna um clente por id", 
			response=Cliente.class,
			consumes="",
			notes="Essa operação não é necessário nenhuma premissa.")
	@ApiResponses(value= {
			@ApiResponse(
					code=200, 
					message="Retorna uma cliente com o status code ok"
					),
			@ApiResponse(
					code=403,
					message="Acesso negado, existem alguns endpoints neste path que é necessário acesso de ADM"

					),
			@ApiResponse(
					code=401,
					message="Indica que você não possui as credencias de autenticação válida. "

					),
			@ApiResponse(
					code=404,
					message="Indica que o recurso que você está procurando não existe, ou não foi encontrado."

					)
			
 
	})
	@RequestMapping(value="/{id}",method=RequestMethod.GET, consumes="json/application", produces="json/application")
	public ResponseEntity<Cliente> findById(@PathVariable Integer id) {
		 Cliente cliente = clienteService.findById(id);
		 return ResponseEntity.ok().body(cliente);
	}
	
	@ApiOperation(
			value="Retorna um clente por email", 
			response=Cliente.class, 
			notes="Essa operação não é necessário nenhuma premissa.")
	@ApiResponses(value= {
			@ApiResponse(
					code=200, 
					message="Retorna uma lista de clientes com o status code ok"
					),
			@ApiResponse(
					code=403,
					message="Acesso negado, existem alguns endpoints neste path que é necessário acesso de ADM"

					),
			@ApiResponse(
					code=401,
					message="Indica que você não possui as credencias de autenticação válida. "

					),
			@ApiResponse(
					code=404,
					message="Indica que o recurso que você está procurando não existe, ou não foi encontrado."

					)
			
 
	})
	@RequestMapping(value="/email",method=RequestMethod.GET, consumes="json/application", produces="json/application")
	public ResponseEntity<Cliente> findByEmail(@RequestParam(value="value") String email) {
		 Cliente cliente = clienteService.findByEmail(email);
		 return ResponseEntity.ok().body(cliente);
	}
	
	@ApiOperation(
			value="Retorna uma lista de cliente", 
			response=Cliente.class, 
			notes="Essa operação tem como premissa está logado na aplicação com o token JWT no header da pagina, é necessário acesso de ADM")
	@ApiResponses(value= {
			@ApiResponse(
					code=200, 
					message="Retorna todos clientes cadastrados com o status code ok"
					),
			@ApiResponse(
					code=403,
					message="Acesso negado, existem alguns endpoints neste path que é necessário acesso de ADM"

					),
			@ApiResponse(
					code=401,
					message="Indica que você não possui as credencias de autenticação válida. "

					),
			@ApiResponse(
					code=404,
					message="Indica que o recurso que você está procurando não existe, ou não foi encontrado."

					)
			
 
	})
	@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping(method = RequestMethod.GET, consumes="json/application", produces="json/application")
	public ResponseEntity<List<ClienteDto>> findAll() {
		List<Cliente> clientes = clienteService.findall();
		List<ClienteDto> listDto = clientes.stream().map(obje -> new ClienteDto(obje)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDto);
	}
	

	@ApiOperation(
			value="Cadastra um cliente", 
			response=Cliente.class, 
			notes="Essa operação tem como premissa está logado na aplicação com o token JWT no header da pagina, é necessário acesso de ADM")
	@ApiResponses(value= {
			@ApiResponse(
					code=201, 
					message="Retorna o cliente cadastrado com o status created"
					),
			@ApiResponse(
					code=403,
					message="Acesso negado, existem alguns endpoints neste path que é necessário acesso de ADM"

					),
			@ApiResponse(
					code=401,
					message="Indica que você não possui as credencias de autenticação válida. "

					),
			@ApiResponse(
					code=404,
					message="Indica que o recurso que você está procurando não existe, ou não foi encontrado."

					)
			
 
	})
	@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping(method = RequestMethod.POST, consumes="json/application", produces="json/application")
	public ResponseEntity<Void> insert(@Valid @RequestBody ClienteNewDto clienteDto) throws Exception{
		Cliente cliente = clienteService.fromDto(clienteDto);
		cliente = clienteService.insert(cliente);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(cliente.getId())
				.toUri();
		return ResponseEntity.created(uri).build();
	}
	

	@ApiOperation(
			value="Atualiza um cliente", 
			response=Cliente.class, 
			notes="Essa operação tem como premissa está logado na aplicação com o token JWT no header da pagina, é necessário acesso de ADM")
	@ApiResponses(value= {
			@ApiResponse(
					code=200, 
					message="Retorna o cliente cadastrado com o status code ok"
					),
			@ApiResponse(
					code=403,
					message="Acesso negado, existem alguns endpoints neste path que é necessário acesso de ADM"

					),
			@ApiResponse(
					code=401,
					message="Indica que você não possui as credencias de autenticação válida. "

					),
			@ApiResponse(
					code=404,
					message="Indica que o recurso que você está procurando não existe, ou não foi encontrado."

					)
			
 
	})
	@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes="json/application", produces="json/application")
	public ResponseEntity<Void> update(@Valid @RequestBody ClienteDto clienteDto, @PathVariable Integer id) {
		Cliente cliente = clienteService.fromDto(clienteDto);
		cliente.setId(id);
		cliente = clienteService.update(cliente);
		return ResponseEntity.noContent().build();
	}
	
	@ApiOperation(
			value="Deleta um cliente", 
			response=Cliente.class, 
			notes="Essa operação tem como premissa está logado na aplicação com o token JWT no header da pagina, é necessário acesso de ADM")
	@ApiResponses(value= {
			@ApiResponse(
					code=204, 
					message="Retorna um nocontent informando que o cliente foi deletado"
					),
			@ApiResponse(
					code=403,
					message="Acesso negado, existem alguns endpoints neste path que é necessário acesso de ADM"

					),
			@ApiResponse(
					code=401,
					message="Indica que você não possui as credencias de autenticação válida. "

					),
			@ApiResponse(
					code=404,
					message="Indica que o recurso que você está procurando não existe, ou não foi encontrado."

					)
			
 
	})
	@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE, consumes="json/application", produces="json/application")
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		clienteService.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@ApiOperation(
			value="Faz o update de uma imagem de cliente para o bucket da amazon", 
			response=Cliente.class, 
			notes="Essa operação tem como premissa está logado na aplicação com o token JWT no header da pagina, é necessário acesso de ADM")
	@ApiResponses(value= {
			@ApiResponse(
					code=204, 
					message="Retorna created informando que foi feito o upload com sucesso"
					),
			@ApiResponse(
					code=403,
					message="Acesso negado, existem alguns endpoints neste path que é necessário acesso de ADM"

					),
			@ApiResponse(
					code=401,
					message="Indica que você não possui as credencias de autenticação válida. "

					),
			@ApiResponse(
					code=404,
					message="Indica que o recurso que você está procurando não existe, ou não foi encontrado."

					)
			
 
	})
	@RequestMapping(value="/picture", method = RequestMethod.POST, consumes="json/application", produces="json/application")
	public ResponseEntity<Void> insertPicture(@RequestParam(name="file")MultipartFile file){
		URI uri = clienteService.uploadProfilePicture(file);
		return ResponseEntity.created(uri).build();
	}
}
