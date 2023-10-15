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
	
	@Override
	public void finalize() {
		try {
			clientSocket.close();
		}
		catch (IOException e) {
			logger.log(Level.SEVERE, "Exception in method 'finalize'", e);
		}
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
		
			out.write("HTTP/1.1 301\n".getBytes());
			out.write("Content-Type: application/binary\n".getBytes());
			out.write("Content-Length: 0\n".getBytes());
			out.write("Location: https://www.youtube.com/watch?v=dQw4w9WgXcQ\n".getBytes());
			out.write("\n".getBytes());
			
			logger.log(Level.INFO, "https://www.youtube.com/watch?v=dQw4w9WgXcQ'");
		}
		catch (IOException e) {
			logger.log(Level.SEVERE, "Exception in method 'run'", e);
		}
	}
	
	public void ParseHttpRequest() {
		
	}
	
	
}
