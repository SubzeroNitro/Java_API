package com.subzeronitro.java_api.server;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.DataFormatException;

import com.subzeronitro.java_api.utilities.http.HttpRequest;
import com.subzeronitro.java_api.utilities.http.HttpResponse;
import com.subzeronitro.java_api.utilities.http.HttpResponseBuilder;
import com.subzeronitro.java_api.utilities.http.HttpStatus;
import com.subzeronitro.java_api.utilities.http.HttpVersion;

public class ConnectionHandler implements Runnable {
	private final static Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

	private final Socket clientSocket;
	
	private static final int MAX_ARRAY_SIZE = 1024;
	
	ConnectionHandler(Socket socket) {
		this.clientSocket = socket;
		
		logger.log(Level.INFO, "Connection established!");
	}
	
	@Override
	public void finalize() {
		try {
			this.clientSocket.close();
		}
		catch (IOException e) {
			logger.log(Level.SEVERE, "Exception in method 'finalize'", e);
		}
	}
	
	public void run() {
		try {
			OutputStream out = clientSocket.getOutputStream();
			InputStream in = clientSocket.getInputStream();
			
			byte[] rawRequest = new byte[MAX_ARRAY_SIZE];
			in.read(rawRequest);
			
			HttpRequest request = new HttpRequest(new String(rawRequest, StandardCharsets.UTF_8));
		
			String html = "<!doctype html>"
					+ "<html>"
					+ "<head>"
					+ "<title>This is a title</title>"
					+ "</head>"
					+ "<body>"
					+ "<h1>This is a header!</h1>"
					+ "<p>This is a paragraph!</p>"
					+ "</body>"
					+ "</html>";
			
			HttpResponse response = new HttpResponseBuilder()
					.setProtocolVersion(HttpVersion.V11)
					.setStatus(HttpStatus.OK)
					.setContentType("text/html")
					.setContentLength(html.length())
					.setBody(html)
					.build();
			
			String generatedResponse = response.generateResponse();
			
			logger.log(Level.INFO, generatedResponse);
			
			out.write(generatedResponse.getBytes());
		}
		catch (IOException e) {
			logger.log(Level.WARNING, "Exception in method 'run'", e);
		}
		catch (DataFormatException e) {
			logger.log(Level.WARNING, "Exception in method 'run': Invalid request format", e);
		}
	}	
}
