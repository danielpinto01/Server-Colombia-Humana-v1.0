package connections;

import java.util.ArrayList;

import models.MyThread;
import models.Player;
import models.User;

public class Game extends MyThread implements IObserver {

	public static int gameNum = 0;
	public int shootNum;
	private ArrayList<Connection> connections;
	private int sockets;
	
	public Game() {
		super(String.valueOf(gameNum++), 20);
		connections = new ArrayList<>();
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
	}

	public ArrayList<Connection> getConnections() {
		return connections;
	}

	@Override
	public void execute() {
		for (int i = 0; i < connections.size(); i++) {			
			sendUsers(connections.get(i));
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
		return "Game [shootNum=" + shootNum + ", connections=" + connections + ", sockets=" + sockets+ "]";
	}
}