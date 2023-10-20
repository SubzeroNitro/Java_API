package com.subzeronitro.java_api.utilities.http;

import java.util.zip.DataFormatException;

public class HttpRequest {
	private HttpRequestHeader header = new HttpRequestHeader();
	private String body;
	
	public HttpRequest(String request) throws DataFormatException {
		String[] splitRequest = request.split("\n\n", 2);
		
		if (splitRequest.length == 2) {
			this.body = splitRequest[1];
		}
		
		try {
			this.parseRequestHeader(splitRequest[0]);	
		}
		catch (DataFormatException e) {
			throw e;
		}
	}
	
	private void parseRequestHeader(String header) throws DataFormatException {
		String[] lines = header.trim().split("\n");
		String[] requestInfo = lines[0].split(" ");
		
		String httpMethod = requestInfo[0].trim();
		String uri = requestInfo[1].trim();
		String protocolVersion = requestInfo[2].trim();
		
		if (requestInfo.length != 3) {
			throw new DataFormatException("Invalid HTTP header");
		}
		
		this.header.method = HttpMethod.fromString(httpMethod);
		
		if (this.header.method == HttpMethod.INVALID) {
			throw new DataFormatException("Invalid HTTP method");
		}
		
		String[] uriSections = uri.split("\\?|#", 2);
		
		this.header.uri.path = uriSections[0].trim();
		
		if (uriSections.length == 2) {
			String[] sectionsAfterPath = uriSections[1].split("#", 2);
			String[] parameters = sectionsAfterPath[0].split("&");

			for (String parameter : parameters) {
				String[] pair = parameter.split("=", 2);
				String key = pair[0].trim();
				String value = pair[1].trim();
				
				if (this.header.uri.parameters.containsKey(key)) {
					throw new DataFormatException("Repeated parameter in URI: '" + key + "'");
				}
				
				this.header.uri.parameters.put(key, value);
			}
			
			if (sectionsAfterPath.length == 2) {
				this.header.uri.fragment = sectionsAfterPath[1];
			}
		}
		
		this.header.protocolVersion = HttpVersion.fromString(protocolVersion);
		
		if (this.header.protocolVersion == HttpVersion.INVALID) {
			throw new DataFormatException("Invalid HTTP protocol version: " + protocolVersion);
		}
		
		for (int i = 1; i < lines.length; i++) {
			String[] pair = lines[i].split(":", 2);
			
			if (pair.length != 2) {
				throw new DataFormatException("Invalid field: " + lines[i]);
			}
			
			String key = pair[0].trim();
			String value = pair[1].trim();
			
			if (this.header.fields.containsKey(key)) {
				throw new DataFormatException("Repeated field in header: '" + key + "'");
			}
			
			this.header.fields.put(key, value);
		}
	}
	
	public HttpRequestHeader getHeader() {
		return this.header;
	}
	
	public String getBody() {
		return this.body;
	}
}
