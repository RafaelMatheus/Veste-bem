package br.com.vestebem.service.exceptions;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ResourceExceptionHandler {
	@ExceptionHandler(ObjectNotFoundException.class)
	public ResponseEntity<StandardError> objectNotFound(ObjectNotFoundException objectNotFoundException, 
														HttpServletRequest httpServletRequest){
		StandardError standardError = new StandardError(
				HttpStatus.NOT_FOUND.value(),
				objectNotFoundException.getMessage(), 
				System.currentTimeMillis());
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(standardError);
		
	}
	
	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<StandardError> DataIntegrityViolationException(DataIntegrityViolationException dataIntegrityViolationException, 
														HttpServletRequest httpServletRequest){
		StandardError standardError = new StandardError(
				HttpStatus.BAD_REQUEST.value(),
				dataIntegrityViolationException.getMessage(), 
				System.currentTimeMillis());
	
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(standardError);
		
	}
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<StandardError> MethodArgumentNotValidException(MethodArgumentNotValidException methodArgumentNotValidException,
																		HttpServletRequest httpServletRequest){
		
		ValidationError validationError = new ValidationError(
				HttpStatus.BAD_REQUEST.value(),
				"Erro de validação", 
				System.currentTimeMillis());
		
		for(FieldError x :methodArgumentNotValidException.getBindingResult().getFieldErrors()) {
			validationError.setError(x.getField(), x.getDefaultMessage());
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(validationError);
		
	}
	
	@ExceptionHandler(AuthorizationException.class)
	public ResponseEntity<StandardError> authorizationException(AuthorizationException authorizationException, 
														HttpServletRequest httpServletRequest){
		StandardError standardError = new StandardError(
				HttpStatus.NOT_FOUND.value(),
				authorizationException.getMessage(), 
				System.currentTimeMillis());
		
		return ResponseEntity.status(HttpStatus.FORBIDDEN).body(standardError);
		
	}
	
}
