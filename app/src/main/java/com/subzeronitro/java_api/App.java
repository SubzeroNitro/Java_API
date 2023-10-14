package com.subzeronitro.java_api;

import java.util.logging.Level;
import java.util.logging.Logger;
import com.subzeronitro.java_api.server.Server;

public class App {
	public final static Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
	
	public static void main(String[] args) {
		logger.log(Level.INFO, "Start of application");
		
		Server server = new Server(4200);
	}
}
