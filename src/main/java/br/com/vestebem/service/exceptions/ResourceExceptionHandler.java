package br.com.vestebem.service.exceptions;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.model.AmazonS3Exception;

@ControllerAdvice
public class ResourceExceptionHandler {
	@ExceptionHandler(ObjectNotFoundException.class)
	public ResponseEntity<StandardError> objectNotFound(ObjectNotFoundException objectNotFoundException, 
														HttpServletRequest httpServletRequest){
		StandardError standardError = new StandardError(
				System.currentTimeMillis(),
				HttpStatus.NOT_FOUND.value(),
				"Não encontrado",
				objectNotFoundException.getMessage(),
				httpServletRequest.getRequestURI());
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(standardError);
		
	}
	
	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<StandardError> DataIntegrityViolationException(DataIntegrityViolationException dataIntegrityViolationException, 
														HttpServletRequest httpServletRequest){
	
		StandardError standardError = new StandardError(
				System.currentTimeMillis(),
				HttpStatus.BAD_REQUEST.value(),
				"Integridade de dados",
				dataIntegrityViolationException.getMessage(),
				httpServletRequest.getRequestURI());
	
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(standardError);
		
	}
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<StandardError> MethodArgumentNotValidException(MethodArgumentNotValidException methodArgumentNotValidException,
																		HttpServletRequest httpServletRequest){
		
		ValidationError validationError = new ValidationError(
				System.currentTimeMillis(),
				HttpStatus.UNPROCESSABLE_ENTITY.value(),
				"Erro de validação", 
				methodArgumentNotValidException.getMessage(),
				httpServletRequest.getRequestURI());
		
		for(FieldError x :methodArgumentNotValidException.getBindingResult().getFieldErrors()) {
			validationError.setError(x.getField(), x.getDefaultMessage());
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(validationError);
		
	}
	
	@ExceptionHandler(AuthorizationException.class)
	public ResponseEntity<StandardError> authorizationException(AuthorizationException authorizationException, 
														HttpServletRequest httpServletRequest){

		StandardError standardError = new StandardError(
				System.currentTimeMillis(),
				HttpStatus.FORBIDDEN.value(),
				"Acesso negado",
				authorizationException.getMessage(),
				httpServletRequest.getRequestURI());
		
		return ResponseEntity.status(HttpStatus.FORBIDDEN).body(standardError);
		
	}
	
	@ExceptionHandler(FileException.class)
	public ResponseEntity<StandardError> fileException(FileException fileException, 
														HttpServletRequest httpServletRequest){
		StandardError standardError = new StandardError(
				System.currentTimeMillis(),
				HttpStatus.BAD_REQUEST.value(),
				"Erro de arquivo",
				fileException.getMessage(),
				httpServletRequest.getRequestURI());
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(standardError);
		
	}
	
	@ExceptionHandler(AmazonServiceException.class)
	public ResponseEntity<StandardError> amazonServiceException(AmazonServiceException amazonServiceException, 
														HttpServletRequest httpServletRequest){
		
		HttpStatus code = HttpStatus.valueOf(amazonServiceException.getErrorCode());
		
		StandardError standardError = new StandardError(
				System.currentTimeMillis(),
				code.value(),
				"Erro amazon service",
				amazonServiceException.getMessage(),
				httpServletRequest.getRequestURI());
		
		return ResponseEntity.status(code).body(standardError);
		
	}
	
	@ExceptionHandler(AmazonClientException.class)
	public ResponseEntity<StandardError> amazonClientException(AmazonClientException amazonClientException, 
														HttpServletRequest httpServletRequest){
		
		StandardError standardError = new StandardError(
				System.currentTimeMillis(),
				HttpStatus.BAD_REQUEST.value(),
				"Erro amazon cliente",
				amazonClientException.getMessage(),
				httpServletRequest.getRequestURI());
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(standardError);
		
	}
	
	@ExceptionHandler(AmazonS3Exception.class)
	public ResponseEntity<StandardError> amazonClientException(AmazonS3Exception amazonS3Exception, 
														HttpServletRequest httpServletRequest){
		
		StandardError standardError = new StandardError(
				System.currentTimeMillis(),
				HttpStatus.BAD_REQUEST.value(),
				"Erro S3",
				amazonS3Exception.getMessage(),
				httpServletRequest.getRequestURI());
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(standardError);
		
	}
	
}
