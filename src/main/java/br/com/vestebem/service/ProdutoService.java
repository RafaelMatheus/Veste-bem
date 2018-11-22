package br.com.vestebem.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import br.com.vestebem.model.Categoria;
import br.com.vestebem.model.Produto;
import br.com.vestebem.repositories.CategoriaRepository;
import br.com.vestebem.repositories.ProdutoRepository;
import br.com.vestebem.service.exceptions.ObjectNotFoundException;

@Service
public class ProdutoService {
	@Autowired
	private ProdutoRepository produtoRepository;
	@Autowired
	private CategoriaRepository categoriaRepository;

	public List<Produto> findall() {
		return produtoRepository.findAll();
	}

	public Produto findById(Integer id) {
		Optional<Produto> produtos = produtoRepository.findById(id);
		return produtos.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto com o " + "id " + id + " não encontrado, tipo: " + Produto.class.getName()));
	}

	public Page<Produto> search(String nome, List<Integer> id, Integer page, Integer linesPerPage, String orderBy,
			String direction) {
		@SuppressWarnings("deprecation")
		PageRequest pageRequest = new PageRequest(page, linesPerPage, Direction.valueOf(direction), orderBy);
		List<Categoria> categorias = categoriaRepository.findAllById(id);
		return produtoRepository.findDistinctByNomeContainingAndCategoriasIn(nome, categorias, pageRequest);
	}
	

	public Page<Produto> findByPromocao(Double max, Double min, Integer page, Integer linesPerPage, String orderBy,
			String direction) {
		@SuppressWarnings("deprecation")
		PageRequest pageRequest = new PageRequest(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return produtoRepository.findProdutoPromocao(max, min,  pageRequest);
	}
}
