package com.sparta.academy.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class LoginRequest{

	@JsonProperty("username")
	private String username;

	@JsonProperty("password")
	private String password;

	public String getPassword(){
		return password;
	}

	public String getUsername(){
		return username;
	}

	public LoginRequest(String username, String password) {
		this.username = username;
		this.password = password;
	}
}