package server;

import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.IOException;
import java.net.ServerSocket;

public class Server {
	private final static Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
	private ServerSocket socket;
	
	public Server(int port) {
		try {
			socket = new ServerSocket(port);
		}
		catch (IOException ex) {
			logger.log(Level.SEVERE, "Exception in class 'Server'", ex);
		}
	}
}
