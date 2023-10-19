package com.subzeronitro.java_api.utilities.http;

public class HttpResponseBuilder {
	private HttpResponseHeader header;
	private String body;
	
	public void setHeader(HttpResponseHeader header) {
		this.header = header;
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
	
	public void setBody(String body) {
		this.body = body;
	}
	
	public HttpResponse build() {
		return new HttpResponse(this.header, this.body);
	}
}
