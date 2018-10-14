package br.com.vestebem.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.vestebem.model.Categoria;
import br.com.vestebem.service.CategoriaService;

@RestController
@RequestMapping(value="/categorias")
public class CategoriaController {
	@Autowired
	CategoriaService categoriaService;
	
	@RequestMapping(value="/{id}",method=RequestMethod.GET)
	public ResponseEntity<?> findById(@PathVariable Integer id) {
		 Optional<Categoria> categoria = categoriaService.findById(id);
		 return ResponseEntity.ok().body(categoria);
	}
}
