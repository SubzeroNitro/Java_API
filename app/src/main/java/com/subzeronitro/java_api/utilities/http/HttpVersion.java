package com.subzeronitro.java_api.utilities.http;

public enum HttpVersion {
	INVALID("INVALID"),
	V09("HTTP/0.9"),
	V10("HTTP/1.0"),
	V11("HTTP/1.1"),
	V20("HTTP/2.0"),
	V30("HTTP/3.0");
	 
	public final String value;
	 
	private HttpVersion(String value) {
		this.value = value;
	}
	 
	public static HttpVersion fromString(String value) {
		switch (value.toLowerCase()) {
			case "http/0.9":
				return V09;
				
			case "http/1.0":
				return V10;
				
			case "http/1.1":
				return V11;
				
			case "http/2.0":
				return V20;
				
			case "http/3.0":
				return V30;
				
			default:
				return INVALID;
		}
	}
}
