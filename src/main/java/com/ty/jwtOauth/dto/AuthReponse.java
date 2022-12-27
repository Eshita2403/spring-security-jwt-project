package com.ty.jwtOauth.dto;

import lombok.Data;

@Data
public class AuthReponse {
private String jwt;



public AuthReponse(String jwt) {
	this.jwt = jwt;
}



}
