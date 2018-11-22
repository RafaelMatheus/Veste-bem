package br.com.vestebem.repositories;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.vestebem.model.Categoria;
import br.com.vestebem.model.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Integer> {
	       
	@Transactional(readOnly=true)
	Page<Produto> findDistinctByNomeContainingAndCategoriasIn(String nome, List<Categoria> categorias, Pageable pageRequest);

	@Transactional(readOnly=true)
	@Query("SELECT obj FROM Produto obj WHERE obj.preco >= :min and obj.preco <=:max")
	Page<Produto> findProdutoPromocao(@Param("max")Double max, @Param("min")Double min, Pageable pageRequest);
	
}