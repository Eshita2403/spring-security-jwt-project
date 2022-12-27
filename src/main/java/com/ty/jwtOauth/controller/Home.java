package com.ty.jwtOauth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ty.jwtOauth.dto.AuthReponse;
import com.ty.jwtOauth.dto.AuthRequest;
import com.ty.jwtOauth.service.CustomUserDetailService;
import com.ty.jwtOauth.util.JwtUtil;

@RestController
public class Home {

	@Autowired
	private JwtUtil jwtUtil;
	
	@Autowired
	CustomUserDetailService customUserDetailService;
	@Autowired
	private AuthenticationManager authenticationManager;

	@GetMapping("/")
	public String welcome() {
		return "welcome to security";
	}

	@RequestMapping(value="/authenticate", method = RequestMethod.POST,produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<AuthReponse> creatingToken(@RequestBody AuthRequest authRequest) throws Exception  {
		try {
		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
		}catch (BadCredentialsException e) {
			throw new Exception("incorrect username and password",e);
		}
		final UserDetails userDetails =customUserDetailService.loadUserByUsername(authRequest.getUsername());
		 String jwt = jwtUtil.generateToken(userDetails);
		AuthReponse response=new AuthReponse(jwt);
		System.out.println("JSONWebToken for the user: "+jwt  );
		return new ResponseEntity<AuthReponse>(response,HttpStatus.OK);
	}
	
}













