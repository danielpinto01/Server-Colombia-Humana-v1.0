package connections;

import java.io.File;
import java.util.ArrayList;

import models.Manager;
import models.MyThread;
import models.Player;
import models.User;
import persistence.FileManager;

public class Game extends MyThread implements IObserver {

	public static int gameNum = 0;
	private ArrayList<Connection> connections;
	private int sockets;
	
	private Manager manager;
	
	public Game() {
		super(String.valueOf(gameNum++), 20);
		connections = new ArrayList<>();
		manager = new Manager();
	}

	public void addConnection(Connection connection) {
		System.out.println(connection.getPlayer().getName() + " - Game " + gameNum);
		connection.addObserver(this);
		connections.add(connection);
		sendUsers(connection);
		if (connections.size() == 2) {
			for (Connection actual : connections) {
				actual.startMessage();
			}
			start();
			manager.start();
			manager.timerBees();
		}	
		sockets++;
	}

	public int getSize() {
		return sockets;
	}

	private void sendUsers(Connection actual) {
		ArrayList<User> list = new ArrayList<>();
		for (Connection connection : connections) {
			if (connection != actual) {				
				Player player = connection.getPlayer();
				list.add(new User(player.getName(), player.getArea().getX(), player.getArea().getY()));
			}
		}
		actual.sendPlayers(list);
//		actual.sendShotList();
	}

	public ArrayList<Connection> getConnections() {
		return connections;
	}

	@Override
	public void execute() {
		try {
			File beesFile = new File("Bees.xml");
			FileManager.saveBeesFile(beesFile, manager.getBees());
			Connection connection;
			for (int i = 0; i < connections.size(); i++) {			
				connection = connections.get(i);
				sendUsers(connection);
				if (!manager.getBees().isEmpty()) {
					connection.sendBees(beesFile);
				}
			}
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	
	}

	@Override
	public void removeConnection(Connection connection) {
		connections.remove(connection);
	}

	@Override
	public void addPlayer(Connection connection) {
	}

	@Override
	public String toString() {
		return "Game [" + " connections=" + connections + ", sockets=" + sockets+ "]";
	}
}