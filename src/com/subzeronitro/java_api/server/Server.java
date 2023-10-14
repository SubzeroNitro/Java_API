package com.subzeronitro.java_api.server;

import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	private final static Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
	
	private ServerSocket serverSocket;
	private Socket clientSocket;
	
	private OutputStream out;
	private InputStream in;
	
	public Server(int port) {
		try {
			serverSocket = new ServerSocket(port);
			clientSocket = serverSocket.accept();
			
			out = clientSocket.getOutputStream();
			in = clientSocket.getInputStream();
			
			byte[] b = new byte[1024];
			int length = in.read(b);
			
			String response = "";
			
			for (int i = 0; i < length; i++)
			{
				response += b[i];
			}
			
			logger.log(Level.INFO, response);
		}
		catch (IOException ex) {
			logger.log(Level.SEVERE, "Exception in class 'Server'", ex);
		}
	}
}
