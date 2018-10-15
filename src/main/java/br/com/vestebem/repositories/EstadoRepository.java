package br.com.vestebem.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.vestebem.model.Estado;

@Repository
public interface EstadoRepository extends JpaRepository<Estado, Integer>{

}