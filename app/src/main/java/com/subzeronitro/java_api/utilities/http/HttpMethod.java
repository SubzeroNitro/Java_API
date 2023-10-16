package com.subzeronitro.java_api.utilities.http;

public enum HttpMethod {
	INVALID("INVALID"),
	GET("GET"),
	POST("POST"),
	PUT("PUT"),
	DELETE("DELETE"),
	HEAD("HEAD"),
	PATCH("PATCH");
	
	public final String value;
	
	private HttpMethod(String value) {
		this.value = value;
	}

	public static HttpMethod fromString(String value) {
		switch (value.toLowerCase()) {
			case "get":
				return GET;
				
			case "post":
				return POST;
				
			case "put":
				return PUT;
				
			case "delete":
				return DELETE;
				
			case "head":
				return HEAD;
				
			case "patch":
				return PATCH;
				
			default:
				return INVALID;
		}
	}
}
