package com.example.token1.models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class JwtResponse {

	private String JwtToken;
	
	private String username;
}
