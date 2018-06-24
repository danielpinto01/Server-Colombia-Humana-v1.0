package connections;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;

import models.Area;
import models.Enemy;
import models.MyThread;
import models.Player;
import models.User;
import persistence.FileManager;

public class Connection extends MyThread implements IObservable {

	private static int count = 0;
	private IObserver iObserver;
	private DataInputStream input;
	private DataOutputStream output;
	private Socket socket;
	private Player player;
	
	private Enemy enemy;
	
	public Connection(Socket socket) {
		super(String.valueOf(count++), 20);
		enemy = new Enemy();
		this.socket = socket;
		try {
			input = new DataInputStream(this.socket.getInputStream());
			output = new DataOutputStream(this.socket.getOutputStream());
		} catch (IOException e) {
			System.err.println(e.getLocalizedMessage());
		}
		start();
	}

	@SuppressWarnings("incomplete-switch")
	private void managerRequest(String response) throws IOException {
		switch (Request.valueOf(response)) {
		case LOG_IN:
			break;
		case SIGN_IN:
			createPlayer();
			break;
		case MOVE_PLAYER:
			setPosition();
			break;
		}
	}
	
	private void setPosition() throws IOException {
		player.getArea().setX(input.readInt());
		player.getArea().setY(input.readInt());
	}

	public void createPlayer() throws IOException {
		player = new Player(input.readUTF(), new Area(input.readInt(), input.readInt(), input.readInt(), input.readInt()));
		advise();
	}

	public void sendPlayers(ArrayList<User> players) {
		try {
			output.writeUTF(Response.PLAYERS_INFO.toString());
			FileManager.saveFile(player.getName() + ".xml", players);
			sendFile(new File(player.getName() + ".xml"));
			
			output.writeInt(enemy.getPositionInX());
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}
	}

	private void sendFile(File file) throws IOException {
		byte[] array = new byte[(int) file.length()];
		readFileBytes(file, array);
		output.writeUTF(file.getName());
		output.writeInt(array.length);
		output.write(array);
		file.delete();
	}
	
	private void readFileBytes(File file, byte[] array) throws IOException {
		FileInputStream fInputStream = new FileInputStream(file);
		fInputStream.read(array);
		fInputStream.close();
	}

	public void startMessage() {
		try {
			output.writeUTF(Response.START_GAME.toString());
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}
	}

	@Override
	public void execute() {
		String request;
		try {
			request = input.readUTF();
			if (request != null) {
				managerRequest(request);
			}
		} catch (IOException e) {
			System.err.println(e.getMessage() + "-" + player.getName());
			stop();
			iObserver.removeConnection(this);
		}
	}

	private void advise() {
		iObserver.addPlayer(this);
	}

	@Override
	public void addObserver(IObserver iObserver) {
		this.iObserver = iObserver;
	}

	@Override
	public void removeObserver(IObserver observer) {
		iObserver = null;
	}

	public Player getPlayer() {
//		System.out.println(player + "en conexion");
		return player;
	}

	@Override
	public String toString() {
//		return "Connection [player=" + player + "]";
		return " " + player;
	}
}