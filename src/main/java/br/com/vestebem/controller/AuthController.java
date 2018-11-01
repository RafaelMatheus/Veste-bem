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

@RestController
@RequestMapping(value = "/auth")
public class AuthController {
	@Autowired
	private JwtUtils jwtUtil;
	@Autowired
	private AuthService service;
	
	@RequestMapping(value = "/refresh_token", method = RequestMethod.POST)
	public ResponseEntity<Void> refreshToken(HttpServletResponse response) {
		UserSS user = UserService.authenticated();
		String token = jwtUtil.generateToken(user.getUsername());
		response.addHeader("Authorization", "Bearer " + token);
		response.addHeader("access-control-expose-headers", "Authorization");
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value = "/forgot", method = RequestMethod.POST)
	public ResponseEntity<Void> forgot(@RequestBody @Valid EmailDto dto) {
		service.sendNewPassWord(dto.getEmail());
		return ResponseEntity.noContent().build();
	}
}
