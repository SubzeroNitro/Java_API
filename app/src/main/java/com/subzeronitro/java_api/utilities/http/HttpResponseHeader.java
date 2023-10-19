package com.subzeronitro.java_api.utilities.http;

import java.util.HashMap;

public class HttpResponseHeader {
	public HttpVersion protocolVersion = HttpVersion.INVALID;
	public HttpStatus status = HttpStatus.INVALID;
	public HashMap<String, String> fields = new HashMap<String, String>();
}
