package connections;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import models.PlayerServer;

public class Server extends Thread implements IObserver{
	
	private ServerSocket server;
	private boolean stop;
	private int port;
	
	private ArrayList<Connection> connections; 
	
	private ArrayList<PlayerServer> list;
	
	public final static Logger LOGGER = Logger.getGlobal();
	
	public Server(int port) {
		try {
			this.port = port;
			server = new ServerSocket(port);
			connections = new ArrayList<>();
			start();
		} catch (IOException e) {
			e.printStackTrace();
		}
		list = new ArrayList<>();
		LOGGER.log(Level.INFO, "Servidor inicado en puerto: " + this.port);
		writeList();
	}
	
	@Override
	public void run() {
		while (!stop) {
			try {
				Socket socket = server.accept();
				Connection connection = new Connection(socket);
				connection.addObservables(this);
				connections.add(connection);
				list = connection.getPlayerServers();
//				connection.sendMessageServer();
				
				LOGGER.log(Level.INFO, "Conexion aceptada: " + socket.getInetAddress().getHostAddress());
			} catch (IOException e) {
				System.err.println(e.getMessage());
				stop = true;
			}
		}
	}
	
	public ArrayList<Connection> getConnections() {
		return connections;
	}
	
	public void writeList() {
		System.out.println("In server" + list);
	}

	@Override
	public void update(int idObservable, String message) {
		for (Connection connection : connections) {
			if (!(idObservable == connection.getIdObservable())) {
				connection.devolverMensaje(message);
			}
		}
	}
}