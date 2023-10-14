package com.subzeronitro.java_api.server;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.IOException;
import java.net.ServerSocket;

public class Server {
	private final static Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
	
	private ServerSocket serverSocket;
	private final ExecutorService threadPool = Executors.newCachedThreadPool();
	
	public Server(int port) {
		try {
			this.serverSocket = new ServerSocket(port);
			logger.log(Level.INFO, "Server socket created!");			
		}
		catch (IOException e) {
			logger.log(Level.SEVERE, "IOException in constructor for 'Server'", e);
		}
	}
	
	@Override
	protected void finalize() {
		try {
			serverSocket.close();
			threadPool.shutdown();
		} 
		catch (Exception e) {
			logger.log(Level.SEVERE, "Exception in method 'finalize'", e);
		}
	}
	
	public void start() {
		logger.log(Level.INFO, "Listening for connections on port " + serverSocket.getLocalPort() + "...");	
		
		try {
			while (true) {
				ConnectionHandler handler = new ConnectionHandler(this.serverSocket.accept());
				threadPool.execute(handler);
			}
		}
		catch (IOException e) {
			logger.log(Level.SEVERE, "IOException in method 'start'", e);
			
			threadPool.shutdown();
		}
	}
}
