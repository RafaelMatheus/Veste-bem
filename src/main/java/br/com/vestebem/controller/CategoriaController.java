package br.com.vestebem.controller;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.vestebem.model.Categoria;
import br.com.vestebem.model.dto.CategoriaDto;
import br.com.vestebem.service.CategoriaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(value="Greeting")
@CrossOrigin(origins  = "http://localhost:8080")
@RestController
@RequestMapping(value = "/categorias")
public class CategoriaController {
	@Autowired
	CategoriaService categoriaService;

	@ApiOperation(
			value="Retorna uma lista de categorias existentes.", 
			response=CategoriaDto.class, 
			notes="Essa operação não é necessário nenhuma premissa.")
	@ApiResponses(value= {
			@ApiResponse(
					code=200, 
					message="Retorna uma categoriaDto com o status code ok"
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
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<CategoriaDto>> findAll() {
		List<Categoria> categorias = categoriaService.findall();
		List<CategoriaDto> listDto = categorias.stream().map(obje -> new CategoriaDto(obje))
				.collect(Collectors.toList());
		return ResponseEntity.ok().body(listDto);
	}
	
	
	@ApiOperation(
			value="Retorna uma categoria com paginação.", 
			response=CategoriaDto.class, 
			notes="Essa operação não é necessario nenhuma premissa.")
	@ApiResponses(value= {
			@ApiResponse(
					code=200, 
					message="Retorna um CategoriaDto com o status code ok"
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
	@RequestMapping(value = "/page", method = RequestMethod.GET, produces="json/application")
	public ResponseEntity<Page<CategoriaDto>> findPage(@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
			@RequestParam(value = "orderBy", defaultValue = "nome") String orderBy,
			@RequestParam(value = "direction", defaultValue = "ASC") String direction) {
		Page<Categoria> list = categoriaService.findPage(page, linesPerPage, orderBy, direction);
		Page<CategoriaDto> listDto = list.map(obj -> new CategoriaDto(obj));
		return ResponseEntity.ok().body(listDto);
	}
	
	@ApiOperation(
			value="Retorna uma categoria por id.", 
			response=CategoriaDto.class, 
			notes="Essa operação não é necessario nenhuma premissa.")
	@ApiResponses(value= {
			@ApiResponse(
					code=200, 
					message="Retorna um CategoriaDto com o status code ok"
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
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Categoria> findById(@PathVariable Integer id) {
		Categoria categoria = categoriaService.findById(id);
		return ResponseEntity.ok().body(categoria);
	}
	
	@ApiOperation(
			value="cadastrada uma nova categoria.", 
			response=CategoriaDto.class, 
			notes="Essa operação tem como premissa o login para o endpoint /login, após isso colocar o token JWT gerado no header.")
	@ApiResponses(value= {
			@ApiResponse(
					code=101, 
					message="Retorna um CategoriaDto com o status code created"
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
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody CategoriaDto categoriaDto) {
		Categoria categoria = categoriaService.fromDto(categoriaDto);
		categoria = categoriaService.insert(categoria);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(categoria.getId())
				.toUri();
		return ResponseEntity.created(uri).build();
	}
	
	
	@ApiOperation(
			value="Atualiza uma categoria existente por id.", 
			response=CategoriaDto.class, 
			notes="Essa operação tem como premissa o login para o endpoint /login, após isso colocar o token JWT gerado no header.")
	@ApiResponses(value= {
			@ApiResponse(
					code=200, 
					message="Retorna um CategoriaModel com o status code ok"
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
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@Valid @RequestBody CategoriaDto categoriaDto, @PathVariable Integer id) {
		Categoria categoria = categoriaService.fromDto(categoriaDto);
		categoria.setId(id);
		categoria = categoriaService.update(categoria);
		return ResponseEntity.noContent().build();
	}
	
	@ApiOperation(
			value="Deleta uma categoria existente por id.", 
			response=CategoriaDto.class, 
			notes="Essa operação tem como premissa o login para o endpoint /login, após isso colocar o token JWT gerado no header.")
	@ApiResponses(value= {
			@ApiResponse(
					code=204, 
					message="Retorna um CategoriaModel com o status code nocontent, informando que o conteudo foi deletado com sucesso"
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
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		categoriaService.delete(id);
		return ResponseEntity.noContent().build();
	}

}
