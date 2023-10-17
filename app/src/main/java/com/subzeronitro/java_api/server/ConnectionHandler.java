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

public class ConnectionHandler implements Runnable {
	private final static Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

	private final Socket clientSocket;
	private final OutputStream out;
	private final InputStream in;
	
	private static final int MAX_ARRAY_SIZE = 1024;
	
	ConnectionHandler(Socket socket) throws IOException {
		this.clientSocket = socket;
		
		logger.log(Level.INFO, "Connection established!");
		
		this.out = clientSocket.getOutputStream();
		this.in = clientSocket.getInputStream();
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
			byte[] rawRequest = new byte[MAX_ARRAY_SIZE];
			in.read(rawRequest);
			
			HttpRequest request = new HttpRequest(new String(rawRequest, StandardCharsets.UTF_8));
		
			out.write("HTTP/1.1 301\n".getBytes());
			out.write("Content-Type: application/binary\n".getBytes());
			out.write("Content-Length: 0\n".getBytes());
			out.write("Location: https://www.youtube.com/watch?v=dQw4w9WgXcQ\n".getBytes());
			out.write("\n".getBytes());
			
			logger.log(Level.INFO, "https://www.youtube.com/watch?v=dQw4w9WgXcQ'");
		}
		catch (IOException e) {
			logger.log(Level.WARNING, "Exception in method 'run'", e);
		}
		catch (DataFormatException e) {
			logger.log(Level.WARNING, "Exception in method 'run': Invalid request format", e);
		}
	}	
}
