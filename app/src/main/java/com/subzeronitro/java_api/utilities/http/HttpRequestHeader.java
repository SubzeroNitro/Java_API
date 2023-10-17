package com.subzeronitro.java_api.utilities.http;

import java.util.HashMap;

public class HttpRequestHeader {
	public HttpMethod method;
	public HttpURI uri = new HttpURI();
	public HttpVersion protocolVersion;
	public HashMap<String, String> fields = new HashMap<String, String>();
}