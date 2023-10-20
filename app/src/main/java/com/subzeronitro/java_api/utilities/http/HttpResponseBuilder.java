package com.subzeronitro.java_api.utilities.http;

import java.util.zip.DataFormatException;

public class HttpResponseBuilder {
	private HttpResponseHeader header;
	private String body;
	
	public HttpResponseBuilder setHeader(HttpResponseHeader header) {
		this.header = header;
		
		return this;
	}
	
	public HttpResponseBuilder setProtocolVersion(HttpVersion protocolVersion) {
		this.header.protocolVersion = protocolVersion;
		
		return this;
	}
	
	public HttpResponseBuilder setStatus(HttpStatus status) {
		this.header.status = status;
		
		return this;
	}
	
	public HttpResponseBuilder setField(String key, String value) {
		this.header.fields.put(key, value);
		
		return this;
	}
	
	public HttpResponseBuilder setBody(String body) {
		this.body = body;
		
		return this;
	}
	
	public HttpResponseBuilder setContentType(String contentType) {
		this.header.fields.put("Content-Type", contentType);
		
		return this;
	}
	
	public HttpResponseBuilder setContentLength(int contentLength) throws DataFormatException {
		if (contentLength < 0)
		{
			throw new DataFormatException("Illegal content length");
		}
		
		this.header.fields.put("Content-Length", Integer.toString(contentLength));
		
		return this;
	}
	
	public HttpResponseBuilder setLocation(String url) {
		this.header.fields.put("Location", url);
		
		return this;
	}
	
	public HttpResponse build() {
		return new HttpResponse(this.header, this.body);
	}
}
