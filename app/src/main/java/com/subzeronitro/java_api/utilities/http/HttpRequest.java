package com.subzeronitro.java_api.utilities.http;

import java.util.zip.DataFormatException;

public class HttpRequest {
	private HttpRequestHeader header;
	
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
		
		if (requestInfo.length != 3) {
			throw new DataFormatException("Invalid HTTP header");
		}
		
		this.header.method = HttpMethod.fromString(requestInfo[0]);
		
		if (this.header.method == HttpMethod.INVALID) {
			throw new DataFormatException("Invalid HTTP method");
		}
		
		String[] uriSections = requestInfo[1].split("\\?|#", 1);
		
		this.header.uri.path = uriSections[0];
		
		if (uriSections.length == 2) {
			String[] sectionsAfterPath = uriSections[1].split("#", 1);
			String[] parameters = sectionsAfterPath[0].split("&");

			for (String parameter : parameters) {
				String[] pair = parameter.split("=", 1);
				
				if (pair.length != 2) {
					throw new DataFormatException("Invalid parameter: " + parameter);
				}
				
				if (this.header.uri.parameters.containsKey(pair[0])) {
					throw new DataFormatException("Repeated parameter in URI");
				}
				
				this.header.uri.parameters.put(pair[0], pair[1]);
			}
			
			if (sectionsAfterPath.length == 2) {
				this.header.uri.fragment = sectionsAfterPath[1];
			}
		}
	
	}
}
