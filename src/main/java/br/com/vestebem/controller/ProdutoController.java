package br.com.vestebem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.vestebem.controller.utils.Url;
import br.com.vestebem.model.Produto;
import br.com.vestebem.model.dto.ProdutoDto;
import br.com.vestebem.service.ProdutoService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(value = "/produtos")
public class ProdutoController {
	@Autowired
	ProdutoService produtoService;

	@ApiOperation(
			value="Retorna os produtos por id", 
			response=Produto.class,
			consumes="",
			notes="Essa operação não é necessário nenhuma premissa.")
	@ApiResponses(value= {
			@ApiResponse(
					code=200, 
					message="Retorna produtos por id com o status code ok"
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
	public ResponseEntity<Produto> findById(@PathVariable Integer id) {
		Produto produto = produtoService.findById(id);
		return ResponseEntity.ok().body(produto);
	}

	@ApiOperation(
			value="Retorna todos produtos", 
			response=ProdutoDto.class,
			consumes="",
			notes="Essa operação não é necessário nenhuma premissa.")
	@ApiResponses(value= {
			@ApiResponse(
					code=200, 
					message="Retorna todos produtos com o status code ok"
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
	public ResponseEntity<Page<ProdutoDto>> findPage(@RequestParam(value = "nome", defaultValue = "") String nome,
			@RequestParam(value = "categorias", defaultValue = "") String categorias,
			@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
			@RequestParam(value = "orderBy", defaultValue = "nome") String orderBy,
			@RequestParam(value = "direction", defaultValue = "ASC") String direction) {
		List<Integer> id = Url.decodeIntList(categorias);
		String nomeDecoded = Url.decodeParam(nome);
		Page<Produto> list = produtoService.search(nomeDecoded, id, page, linesPerPage, orderBy, direction);
		Page<ProdutoDto> listDto = list.map(obj -> new ProdutoDto(obj));
		return ResponseEntity.ok().body(listDto);
	}
	
	

	@ApiOperation(
			value="Retorna todos produtos na promoção paginado", 
			response=ProdutoDto.class,
			consumes="",
			notes="Essa operação não é necessário nenhuma premissa.")
	@ApiResponses(value= {
			@ApiResponse(
					code=200, 
					message="Retorna todos produtos na promoção com o status code ok"
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
	@RequestMapping(value="/promocao", method = RequestMethod.GET)
	public ResponseEntity<Page<ProdutoDto>> findProdutoProocao(
			@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
			@RequestParam(value = "orderBy", defaultValue = "nome") String orderBy,
			@RequestParam(value = "max", defaultValue = "100") Double max,
			@RequestParam(value = "min", defaultValue = "0") Double min,
			@RequestParam(value = "direction", defaultValue = "ASC") String direction) {
		Page<Produto> list = produtoService.findByPromocao(max, min, page, linesPerPage, orderBy, direction);
		Page<ProdutoDto> listDto = list.map(obj -> new ProdutoDto(obj));
		return ResponseEntity.ok().body(listDto);
	}
}
