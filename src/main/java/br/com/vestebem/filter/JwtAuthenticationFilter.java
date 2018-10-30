package br.com.vestebem.filter;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.vestebem.model.dto.CredenciaisDto;
import br.com.vestebem.security.UserSS;
import br.com.vestebem.security.utils.JwtUtils;

public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private JwtUtils ultils;
	
	 
	public JwtAuthenticationFilter(AuthenticationManager authenticationManager, JwtUtils ultils) {
		setAuthenticationFailureHandler(new JwtAuthetication);
		this.authenticationManager = authenticationManager;
		this.ultils = ultils;
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		try {
			CredenciaisDto creds = new ObjectMapper().readValue(request.getInputStream(), CredenciaisDto.class);
			UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(creds.getUserName(), creds.getSenha(), new ArrayList<>());
			Authentication auth = authenticationManager.authenticate(authToken);
			return auth;
			
		} catch (IOException e) {
			throw new RuntimeException();
		}
		
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {

		String userName = ((UserSS)authResult.getPrincipal()).getUsername();
		String token = ultils.generateToken(userName);
		response.addHeader("Authorization", "Bearer"+token);
		
		super.successfulAuthentication(request, response, chain, authResult);
	}

}
