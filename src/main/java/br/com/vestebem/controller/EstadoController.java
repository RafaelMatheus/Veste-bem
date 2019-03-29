package br.com.vestebem.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.vestebem.model.Cidade;
import br.com.vestebem.model.Estado;
import br.com.vestebem.model.dto.CidadeDto;
import br.com.vestebem.model.dto.EstadoDto;
import br.com.vestebem.service.CidadeService;
import br.com.vestebem.service.EstadoService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(value="/estados")
public class EstadoController {
	@Autowired
	private EstadoService estadoService;
	@Autowired
	private CidadeService cidadeService;
	
	
	@ApiOperation(
			value="Retorna todos os estados ordenados por nome", 
			response=EstadoDto.class,
			consumes="",
			notes="Essa operação não é necessário nenhuma premissa.")
	@ApiResponses(value= {
			@ApiResponse(
					code=200, 
					message="Retorna todos estados com o status code ok"
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
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<EstadoDto>> findAll() {
		List<Estado> estados = estadoService.findAllOrderByNome();
		List<EstadoDto> listDto = estados.stream().map(obje -> new EstadoDto(obje)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDto);
	}
	
	
	@ApiOperation(
			value="Retorna todas cidades ordenados por estado", 
			response=CidadeDto.class,
			consumes="",
			notes="Essa operação não é necessário nenhuma premissa.")
	@ApiResponses(value= {
			@ApiResponse(
					code=200, 
					message="Retorna todas cidades com o status code ok"
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
	@RequestMapping(value="/{estadoId}/cidades",method=RequestMethod.GET)
	public ResponseEntity<List<CidadeDto>> findEstados(@PathVariable Integer estadoId) {
		List<Cidade> cidades = cidadeService.findByEstado(estadoId);
		List<CidadeDto> listDto = cidades.stream().map(obje -> new CidadeDto(obje)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDto);
	}
}
