package com.ltim.product.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ltim.product.dto.LoginRequestDTO;
import com.ltim.product.util.JWTUtil;

@RestController
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JWTUtil jwtUtil;

	@Autowired
	private UserDetailsService userDetailsService;

	@PostMapping("/login")
	public Map<String, String> login(@RequestBody LoginRequestDTO request) {
		String username = request.getUsername();
		String password = request.getPassword();

		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));

		UserDetails userDetails = userDetailsService.loadUserByUsername(username);
		String token = jwtUtil.generateToken(userDetails.getUsername());

		return Map.of("token", token);
	}
}
