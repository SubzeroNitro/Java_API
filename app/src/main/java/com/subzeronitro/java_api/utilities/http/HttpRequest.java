package com.subzeronitro.java_api.utilities.http;

import java.util.stream.Stream;
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
		
		this.header.method = HttpMethod.fromString(requestInfo[0]);
		
		if (this.header.method == HttpMethod.INVALID) {
			throw new DataFormatException("Invalid HTTP method");
		}
	}
}
