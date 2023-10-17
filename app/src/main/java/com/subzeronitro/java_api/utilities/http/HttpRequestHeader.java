package com.subzeronitro.java_api.utilities.http;

public class HttpRequestHeader {
	public HttpMethod method;
	public HttpURI uri = new HttpURI();
	public String protocolVersion;
	public String userAgent;
	public String[] contentTypes;
	public String[] languages;
	public String[] encodings;
	public boolean keepAlive;
}
