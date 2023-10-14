package com.subzeronitro.java_api;
import java.util.logging.Logger;
import com.subzeronitro.java_api.server.Server;

public class Main {
	public final static Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
	
	public static void main(String[] args) {
		Server server = new Server(4200);
	}
}
