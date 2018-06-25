package connections;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import models.MyThread;

public class Server extends MyThread implements IObserver{

	private static final String SERVER = "Servidor";
	private static final int SLEEP = 1000;
	private ServerSocket serverSocket;
	private ArrayList<Connection> connections;
	private ArrayList<Game> games;
	private Socket socket;

	public Server(int port) throws IOException {
		super(SERVER, SLEEP);
		serverSocket = new ServerSocket(port);
		games = new ArrayList<>();
		connections = new ArrayList<>();
		games.add(new Game());
		System.out.println("Server create at port " + port);

		start();
	}

	@Override
	public void execute() {
		try {
			socket = serverSocket.accept();
			System.out.println("New connection!" + socket.getInetAddress());
			Connection connection = new Connection(socket);
			connection.addObserver(this);
			connections.add(connection);
			
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}
	}

	private void addToGame(Connection connection) {
		if (games.get(games.size()-1).getSize() < 4) {
			games.get(games.size()-1).addConnection(connection);
		} else {
			Game game = new Game();
			game.addConnection(connection);
			games.add(game);
		}
	}
	
	public ArrayList<Connection> getListToGame(){
		ArrayList<Connection> list = new ArrayList<>();
		for (Game game : games) {
			list = game.getConnections();
		}
		return list;
	}

	@Override
	public void addPlayer(Connection connection) {
		addToGame(connection);
		connections.remove(connection);
	}

	@Override
	public void removeConnection(Connection connection) {

	}
}