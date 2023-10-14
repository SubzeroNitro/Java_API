package com.subzeronitro.java_api.server;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConnectionHandler implements Runnable {
	private final static Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
	
	private final Socket clientSocket;
	private final OutputStream out;
	private final InputStream in;
	
	ConnectionHandler(Socket socket) throws IOException {
		clientSocket = socket;
		
		logger.log(Level.INFO, "Connection established!");
		
		out = clientSocket.getOutputStream();
		in = clientSocket.getInputStream();
	}
	
	public void run() {
		try {
			byte[] b = new byte[1024];
			int length = in.read(b);
			
			String response = "";
			
			for (int i = 0; i < length; i++)
			{
				response += (char)b[i];
			}
			
			logger.log(Level.INFO, response);
		}
		catch (IOException e) {
			logger.log(Level.SEVERE, "Exception in method 'run'", e);
		}
	}
}
