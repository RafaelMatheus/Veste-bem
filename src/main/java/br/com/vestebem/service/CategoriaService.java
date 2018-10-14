package br.com.vestebem.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.vestebem.model.Categoria;
import br.com.vestebem.repositories.CategoriaRepository;
import br.com.vestebem.service.exceptions.ObjectNotFoundException;

@Service
public class CategoriaService {
	@Autowired
	private CategoriaRepository categoriaRepository;

	public List<Categoria> findall() {
		return categoriaRepository.findAll();
	}
	public Categoria findById(Integer id) {
		Optional<Categoria> categoria = categoriaRepository.findById(id);
		return categoria.orElseThrow(
				()->new ObjectNotFoundException("Objeto com o "
				+ "id "+id+" não encontrado, tipo: "
				+Categoria.class.getName()));
	}

	public Categoria insert(Categoria obj) {
		obj.setId(null);
		return categoriaRepository.save(obj);
	}
}
