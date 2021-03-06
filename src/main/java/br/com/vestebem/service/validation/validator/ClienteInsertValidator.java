package br.com.vestebem.service.validation.validator;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.vestebem.model.Cliente;
import br.com.vestebem.model.dto.ClienteNewDto;
import br.com.vestebem.model.enums.TipoCliente;
import br.com.vestebem.repositories.ClienteRepository;
import br.com.vestebem.service.exceptions.FieldMessage;
import br.com.vestebem.service.validation.ClienteInsert;
import br.com.vestebem.service.validation.validator.ultils.Br;

public class ClienteInsertValidator implements ConstraintValidator<ClienteInsert, ClienteNewDto> {
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Override
	public void initialize(ClienteInsert ann) {
	}

	@Override
	public boolean isValid(ClienteNewDto objDto, ConstraintValidatorContext context) {
		List<FieldMessage> list = new ArrayList<>();
		Cliente aux = clienteRepository.findByEmail(objDto.getEmail());
		
		if(objDto.getTipo().equals(TipoCliente.PESSOAFISICA.getCodigo())
				&& 
				!Br.isValidCPF(objDto.getCpfOuCnpj())) {
			list.add(new FieldMessage("CpfOuCnpj","CPF invalido"));
			
		}
		
		if(objDto.getTipo().equals(TipoCliente.PESSOAJURIDICA.getCodigo())
				&& 
				!Br.isValidCPF(objDto.getCpfOuCnpj())) {
			list.add(new FieldMessage("CpfOuCnpj","CNPJ invalido"));
			
		}
		
		
		if(aux!=null) {
			list.add(new FieldMessage("Email","Email existente"));
		}
		
		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}
}