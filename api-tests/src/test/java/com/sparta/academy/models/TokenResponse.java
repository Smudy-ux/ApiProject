package com.sparta.academy.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TokenResponse{

	@JsonProperty("token")
	private String token;

	public String getToken(){
		return token;
	}
}