package br.com.vestebem.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import br.com.vestebem.model.Categoria;
import br.com.vestebem.model.dto.CategoriaDto;
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
		return categoria.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto com o " + "id " + id + " não encontrado, tipo: " + Categoria.class.getName()));
	}

	public Categoria insert(Categoria categoria) {
		categoria.setId(null);
		return categoriaRepository.save(categoria);
	}

	public Categoria update(Categoria categoria) {
		this.findById(categoria.getId());
		return categoriaRepository.save(categoria);
	}
	
	public void delete(Integer id) {
		this.findById(id);
		try {
			categoriaRepository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new br.com.vestebem.service.exceptions.
					DataIntegrityViolationException("Não é possivel excluir "
					+ "categorias com produtos"
					+ " associados");
		}
		
	}
	public Categoria fromDto(CategoriaDto categoriaDto) {
		return new Categoria(categoriaDto.getId(), categoriaDto.getNome());
	}

}
