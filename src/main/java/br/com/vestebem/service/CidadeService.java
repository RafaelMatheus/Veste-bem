package br.com.vestebem.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.vestebem.model.Cidade;
import br.com.vestebem.repositories.CidadeRepository;

@Service
public class CidadeService {
	@Autowired
	private CidadeRepository cidadeRepository;
	
	public List<Cidade> findByEstado(Integer id){
		return cidadeRepository.findCidades(id);
	}
}
