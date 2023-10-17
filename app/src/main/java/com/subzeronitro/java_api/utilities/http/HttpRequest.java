package com.subzeronitro.java_api.utilities.http;

import java.util.Arrays;
import java.util.zip.DataFormatException;

public class HttpRequest {
	private HttpRequestHeader header = new HttpRequestHeader();
	
	public HttpRequest(String request) throws DataFormatException {
		String[] splitRequest = request.split("\n\n", 1);
		
		try {
			parseRequestHeader(splitRequest[0]);		
		}
		catch (DataFormatException e) {
			throw e;
		}
	}
	
	private void parseRequestHeader(String header) throws DataFormatException {
		String[] lines = header.split("\n");
		String[] requestInfo = lines[0].split(" ");
		
		String httpMethod = requestInfo[0];
		String uri = requestInfo[1];
		String protocolVersion = requestInfo[2];
		
		if (requestInfo.length != 3) {
			throw new DataFormatException("Invalid HTTP header");
		}
		
		this.header.method = HttpMethod.fromString(httpMethod);
		
		if (this.header.method == HttpMethod.INVALID) {
			throw new DataFormatException("Invalid HTTP method");
		}
		
		String[] uriSections = uri.split("\\?|#", 1);
		
		this.header.uri.path = uriSections[0];
		
		if (uriSections.length == 2) {
			String[] sectionsAfterPath = uriSections[1].split("#", 1);
			String[] parameters = sectionsAfterPath[0].split("&");

			for (String parameter : parameters) {
				String[] pair = parameter.split("=", 1);
				String key = pair[0];
				String value = pair[1];
				
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
			String[] pair = lines[i].split(":", 1);
			String key = pair[0];
			String value = pair[1];
			
			if (this.header.fields.containsKey(key)) {
				throw new DataFormatException("Repeated field in header: '" + key + "'");
			}
			
			this.header.fields.put(key, value);
		}
	}
}
