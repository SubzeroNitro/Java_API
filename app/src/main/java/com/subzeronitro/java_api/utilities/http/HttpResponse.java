package com.subzeronitro.java_api.utilities.http;

import java.util.zip.DataFormatException;

public class HttpResponse {
	private HttpResponseHeader header = new HttpResponseHeader();
	private String body;
	
	public HttpResponse(HttpVersion protocolVersion, HttpStatus status) {
		this.header.protocolVersion = protocolVersion;
		this.header.status = status;
	}
	
	public HttpResponse(HttpResponseHeader header) {
		this.header = header;
	}
	
	public HttpResponse(HttpResponseHeader header, String body) {
		this.header = header;
		this.body = body;
	}
	
	public String generateResponse() throws DataFormatException {
		var resultWrapper = new Object() { String value = ""; };
		
		if (this.header.protocolVersion == HttpVersion.INVALID) {
			throw new DataFormatException("Invalid HTTP protocol version");
		}
		
		if (this.header.status == HttpStatus.INVALID) {
			throw new DataFormatException("Invalid HTTP status");
		}
		
		resultWrapper.value += this.header.protocolVersion.value + " " + this.header.status + "\n";
		
		this.header.fields.forEach((key, value) -> {
			resultWrapper.value += key + "=" + value + "\n";
		});
		
		resultWrapper.value += "\n" + this.body;
		
		return resultWrapper.value;

	}
	
	public HttpResponseHeader getHeader() {
		return this.header;
	}
	
	public void setProtocolVersion(HttpVersion protocolVersion) {
		this.header.protocolVersion = protocolVersion;
	}
	
	public void setStatus(HttpStatus status) {
		this.header.status = status;
	}
	
	public void setField(String key, String value) {
		this.header.fields.put(key, value);
	}
	
	public void removeField(String key) {
		this.header.fields.remove(key);
	}
	
	public String getBody() {
		return this.body;
	}
	
	public void setBody(String body) {
		this.body = body;
	}
}
