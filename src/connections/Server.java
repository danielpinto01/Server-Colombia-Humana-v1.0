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
	
	private ArrayList<PlayerServer> playerServers;


	public final static Logger LOGGER = Logger.getGlobal();

	public Server(int port) {
		super();
		try {
			this.port = port;
			server = new ServerSocket(port);
			connections = new ArrayList<>();
		} catch (IOException e) {
			e.printStackTrace();
		}
		LOGGER.log(Level.INFO, "Servidor inicado en puerto: " + this.port);
		playerServers = new ArrayList<>();
		start();
		//		writeList();
	}

	@Override
	public void run() {
		while (!stop) {
			try {
				Socket socket = server.accept();
				//				Connection connection = new Connection(socket);
				//				connection.addObservables(this);
				//				connections.add(connection);
				//				System.out.println("Ya en server" + connection.getPlayerServer());
				//				connection.sendMessageServer();
				
				addConnection(socket);
				for (Connection connection2 : connections) {
					connection2.checkSendTotalListFromServerToClient();
				}
				Thread.sleep(100);
				
				LOGGER.log(Level.INFO, "Conexion aceptada: " + socket.getInetAddress().getHostAddress());
			} catch (IOException | InterruptedException e) {
				System.err.println(e.getMessage());
				stop = true;
			}
		}
	}

	public void addConnection(Socket socket) throws IOException {
		Connection connection = new Connection(socket);
		connection.addObservables(this);
		connections.add(connection);
		//		getPlayerInServer(connection);

		
	}

//	public void getPlayerInServer(Connection actualConnection) {
//		ArrayList<String> list = new ArrayList<>();
//		for (Connection connection : connections) {
//			if (connection != actualConnection) {				
//				@SuppressWarnings("unused")
//				PlayerServer playerServer = connection.getPlayerServer();
//				System.out.println("[[[" + connection.getPlayerServer());
//				list.add("Name");
//				list.add("DUQUE");
//			}
//		}
//		System.out.println("Esta es la list "+ list);
//	}


	public ArrayList<Connection> getConnections() {
		return connections;
	}


	//	public void writeList() {
	//		System.out.println("In server" + list);
	//	}

	@Override
	public void update(int idObservable, String message) {
		for (Connection connection : connections) {
			if (!(idObservable == connection.getIdObservable())) {
				connection.devolverMensaje(message);
				
			}
		}
	}

	@Override
	public void addConnection(Connection connection) {
		connections.add(connection);
		playerServers.add(connection.getPlayerServer());
		connection.writeTotalList(getPlayerServers());
		try {
			connection.sendTotalListFromServerToClient();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public ArrayList<PlayerServer> getPlayerServers() {
		System.out.println("list" + playerServers);
		return playerServers;
	}
}