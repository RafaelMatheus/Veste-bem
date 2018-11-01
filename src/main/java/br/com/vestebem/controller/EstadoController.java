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

@RestController
@RequestMapping(value="/estados")
public class EstadoController {
	@Autowired
	private EstadoService estadoService;
	@Autowired
	private CidadeService cidadeService;
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<EstadoDto>> findAll() {
		List<Estado> estados = estadoService.findByEmail();
		List<EstadoDto> listDto = estados.stream().map(obje -> new EstadoDto(obje)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDto);
	}
	
	@RequestMapping(value="/{estadoId}/cidades",method=RequestMethod.GET)
	public ResponseEntity<List<CidadeDto>> findEstados(@PathVariable Integer estadoId) {
		List<Cidade> cidades = cidadeService.findByEstado(estadoId);
		List<CidadeDto> listDto = cidades.stream().map(obje -> new CidadeDto(obje)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDto);
	}
}
