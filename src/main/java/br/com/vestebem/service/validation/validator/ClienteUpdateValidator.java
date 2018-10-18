package br.com.vestebem.service.validation.validator;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import br.com.vestebem.model.Cliente;
import br.com.vestebem.model.dto.ClienteDto;
import br.com.vestebem.repositories.ClienteRepository;
import br.com.vestebem.service.exceptions.FieldMessage;
import br.com.vestebem.service.validation.ClienteUpdate;

public class ClienteUpdateValidator implements ConstraintValidator<ClienteUpdate, ClienteDto> {
	@Autowired
	private HttpServletRequest request;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Override
	public void initialize(ClienteUpdate ann) {
	}

	@Override
	public boolean isValid(ClienteDto objDto, ConstraintValidatorContext context) {
		@SuppressWarnings("unchecked")
		Map<String, String> map = (Map<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
		Integer id = Integer.parseInt(map.get("id"));
		List<FieldMessage> list = new ArrayList<>();
		Cliente aux = clienteRepository.findByEmail(objDto.getEmail());
		
		if(aux!=null && !aux.getId().equals(id)) {
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