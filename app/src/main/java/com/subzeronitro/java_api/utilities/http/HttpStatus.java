package com.subzeronitro.java_api.utilities.http;

public enum HttpStatus {
	// Information Responses
	CONTINUE						(100, "Continue"),
	SWITCHING_PROTOCOLS				(101, "Switching Protocols"),
	PROCESSING						(102, "Processing"),
	EARLY_HINTS						(103, "Early Hints"),
	
	// Successful Responses
	OK								(200, "OK"),
	CREATED							(201, "Created"),
	ACCEPTED						(202, "Accepted"),
	NON_AUTHORITATIVE_INFORMATION	(203, "Non-Authoritative Information"),
	NO_CONTENT						(204, "No Content"),
	RESET_CONTENT					(205, "Reset Content"),
	PARTIAL_CONTENT					(206, "Partial Content"),
	MULTI_STATUS					(207, "Multi-Status"),
	ALREADY_REPORTED				(208, "Already Reported"),
	IM_USED							(209, "IM Used"),
	
	// Redirection Responses
	MULTIPLE_CHOICES				(300, "Multiple Choices"),
	MOVED_PERMANENTLY				(301, "Moved Permanently"),
	FOUND							(302, "Found"),
	SEE_OTHER						(303, "See Other"),
	NOT_MODIFIED					(304, "Not Modified"),
	USE_PROXY						(305, "Use Proxy"),
	UNUSED							(306, "Unused"),
	TEMPORARY_REDIRECT				(307, "Temporary Redirect"),
	PERMANENT_REDIRECT				(308, "Permanent Redirect"),
	
	// Client Error Responses
	BAD_REQUEST						(400, "Bad Request"),
	UNAUTHORIZED					(401, "Unauthorized"),
	PAYMENT_REQUIRED				(402, "Payment Required"),
	FORBIDDEN						(403, "Forbidden"),
	NOT_FOUND						(404, "Not Found"),
	METHOD_NOT_ALLOWED				(405, "Method Not Allowed"),
	NOT_ACCEPTABLE					(406, "Not Acceptable"),
	PROXY_AUTHENTICATION_REQUIRED	(407, "Proxy Authentication Required"),
	REQUEST_TIMEOUT					(408, "Request Timeout"),
	CONFLICT						(409, "Conflict"),
	GONE							(410, "Gone"),
	LENGTH_REQUIRED					(411, "Length Required");
	
	public final int code;
	public final String description;
	
	private HttpStatus(int code, String description) {
		this.code = code;
		this.description = description;
	}
}
