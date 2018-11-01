package br.com.vestebem.service;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.vestebem.model.Cliente;
import br.com.vestebem.repositories.ClienteRepository;
import br.com.vestebem.service.exceptions.ObjectNotFoundException;

@Service
public class AuthService {
	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private BCryptPasswordEncoder pe;
	@Autowired
	private EmailService emailService;
	private Random random = new Random();
	
	public void sendNewPassWord(String email) {
		Cliente cliente = clienteRepository.findByEmail(email);
		if(cliente == null) 
			throw new ObjectNotFoundException("Email "+email+" não encontrado");
		
		String newPass = newPassWord(10);
		cliente.setSenha(pe.encode(newPass));
		clienteRepository.save(cliente);
		emailService.sendNewPasswordEmail(cliente, newPass);
	}

	private String newPassWord(int tamanhoSenha) {
		char[] vet = new char[tamanhoSenha];
		
		for (int i = 0; i<tamanhoSenha; i++) {
			vet[i] = randomChar();
		}
		return new String(vet);
	}

	private char randomChar() {
		int opt = random.nextInt(3);
		//de acordo com a tabela unicode
		if(opt == 0) {//gera numero
			return (char) (random.nextInt(10) + 48);
		}
		else if(opt == 1) {//gera letra maiuscula
			return (char) (random.nextInt(26) + 65);
		}
		else {//gera letra minuscula
			return (char) (random.nextInt(26) + 97);
		}
	}
}
