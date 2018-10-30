package br.com.vestebem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import br.com.vestebem.model.Cliente;
import br.com.vestebem.repositories.ClienteRepository;
import br.com.vestebem.security.UserSS;

public class UserDetailsServiceImpl implements UserDetailsService {
	@Autowired
	private ClienteRepository clienteRepository;
	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		Cliente c = clienteRepository.findByEmail(userName);
		if(c == null) 
			throw new UsernameNotFoundException(userName);
		
		return new UserSS(c.getId(),c.getEmail(),c.getSenha(),c.getPerfil());
	}

}
