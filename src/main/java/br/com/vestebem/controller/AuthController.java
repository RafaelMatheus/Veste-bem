package br.com.vestebem.controller;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.vestebem.model.dto.EmailDto;
import br.com.vestebem.security.UserSS;
import br.com.vestebem.security.utils.JwtUtils;
import br.com.vestebem.service.AuthService;
import br.com.vestebem.service.UserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(value = "/auth")
public class AuthController {
	@Autowired
	private JwtUtils jwtUtil;
	@Autowired
	private AuthService service;
	
	@ApiOperation(
			value="Retorna um novo token pelo header.", 
			notes="Essa operação não é necessário nenhuma premissa.")
	@ApiResponses(value= {
			@ApiResponse(
					code=204, 
					message="Retorna uma novo token no header com o status noconted"
					),
			@ApiResponse(
					code=403,
					message="Acesso negado, existem alguns endpoints neste path que é necessário acesso de ADM"

					),
			@ApiResponse(
					code=401,
					message="Indica que você não possui as credencias de autenticação válida. "

					),
			@ApiResponse(
					code=404,
					message="Indica que o recurso que você está procurando não existe, ou não foi encontrado."

					)
			
 
	})
	@RequestMapping(value = "/refresh_token", method = RequestMethod.POST)
	public ResponseEntity<Void> refreshToken(HttpServletResponse response) {
		UserSS user = UserService.authenticated();
		String token = jwtUtil.generateToken(user.getUsername());
		response.addHeader("Authorization", "Bearer " + token);
		response.addHeader("access-control-expose-headers", "Authorization");
		return ResponseEntity.noContent().build();
	}
	
	@ApiOperation(
			value="Retorna uma nova senha randon para o email do usuário cadastrado na apicação", 
			notes="Essa operação não é necessário nenhuma premissa.")
	@ApiResponses(value= {
			@ApiResponse(
					code=204, 
					message="Retorna um email com a nova senha e o status code noconted"
					),
			@ApiResponse(
					code=403,
					message="Acesso negado, existem alguns endpoints neste path que é necessário acesso de ADM"

					),
			@ApiResponse(
					code=401,
					message="Indica que você não possui as credencias de autenticação válida. "

					),
			@ApiResponse(
					code=404,
					message="Indica que o recurso que você está procurando não existe, ou não foi encontrado."

					)
			
 
	})
	@RequestMapping(value = "/forgot", method = RequestMethod.POST)
	public ResponseEntity<Void> forgot(@RequestBody @Valid EmailDto dto) {
		service.sendNewPassWord(dto.getEmail());
		return ResponseEntity.noContent().build();
	}
}
