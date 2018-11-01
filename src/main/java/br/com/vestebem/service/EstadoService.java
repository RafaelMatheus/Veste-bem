package br.com.vestebem.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.vestebem.model.Estado;
import br.com.vestebem.repositories.EstadoRepository;

@Service
public class EstadoService {
	@Autowired
	private EstadoRepository estadoRepository;
	
	public List<Estado>findByEmail(){
		return estadoRepository.findAllByOrderByNome();
	}
}
