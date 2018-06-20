package connections;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;

import org.json.simple.parser.ParseException;

import models.PlayerServer;
import persistence.FileManager;

public class Connection extends Thread implements IObservable{

	private Socket connection;
	private DataOutputStream outputStream;
	private DataInputStream inputStream;
	private boolean stop;
	
	private IObserver iObserver;
	private int idObservable;
	public static int count;
	
	private FileManager fileManager;
	private PlayerServer playerServer;
	
	public Connection(Socket socket) {
		this.connection = socket; 
		fileManager = new FileManager();
		try {
			outputStream = new DataOutputStream(socket.getOutputStream());
			inputStream = new DataInputStream(socket.getInputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		idObservable = ++count;
		start();
	}
	
	@Override
	public void run() {
		String request = "";
		while (!stop) {
			try {
				if ((request = inputStream.readUTF())!= null) {
					receiveRequest(request);
				}
			} catch (IOException | ParseException e) {
				System.err.println(e.getMessage());
				stop = true;
			}
		}
	}
	
	public void receiveRequest(String request) throws IOException, ParseException {
		Server.LOGGER.log(Level.INFO, "Request: " + connection.getInetAddress().getHostAddress() + " - " + request);
		switch (Request.valueOf(request)) {
		case MESSAGE:
			receiveMessage();
			break;
		case PLAYER_INFORMATION:
			receiveFile();
			break;
		default:
			break;
		}
		Server.LOGGER.log(Level.INFO, "Conexion con: " + connection.getInetAddress().getHostAddress() + " cerrada.");
	}
	
//	public void sendMessageServer() throws IOException {
//		outputStream.writeUTF(Response.MESSAGE_SERVER.toString());
//		outputStream.writeUTF("Hola");
//	}
	
	public void receiveMessage() {
		try {
			iObserver.update(idObservable, inputStream.readUTF());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void devolverMensaje(String message) {
		try {
			outputStream.writeUTF(Response.MESSAGE_SERVER.toString());
			outputStream.writeUTF(message);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void receiveFile() throws IOException, ParseException {
		File file = new File(inputStream.readUTF());
		byte[] bs = new byte[inputStream.readInt()];
		System.out.println("Receiving File..." + file.toString());
		inputStream.read(bs);
		writeFile(file, bs);
//		readOnePlayer(file);
//		writeTotalList(readOnePlayer(file));
//		listPlayersServers.add(fileManager.readPlayer(String.valueOf(file)));
//		getPlayerServers();
		playerServer = fileManager.readPlayer(String.valueOf(file));
//		writeTotalList(getPlayerServers());
		advise();
	}
	
	public void writeFile(File file, byte[] bs) throws IOException {
		FileOutputStream outputStream = new FileOutputStream(file);
		outputStream.write(bs);
		outputStream.close();
	}
	
	public void sendTotalListFromServerToClient() throws IOException {
		outputStream.writeUTF(Response.TOTAL_LIST.toString());
		File file = new File("TotalListPlayers.json");
		byte[] bs = new byte[(int) file.length()]; 
		System.out.println("Sending File... " + file.toString());
		readTotalListFromServerToClient(file, bs);
		outputStream.writeUTF(file.getName());
		outputStream.writeInt(bs.length);
		outputStream.write(bs);
	}
	
	public void readTotalListFromServerToClient(File file, byte[] bs) throws IOException {
		FileInputStream inputStream = new FileInputStream(file);
		inputStream.read(bs);
		inputStream.close();
	}
	
	public void writeTotalList(ArrayList<PlayerServer> list) {
		try {
			fileManager.writeTotalListJson(list);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void advise() {
		iObserver.addConnection(this);
	}

	@Override
	public String toString() {
		return "Connection=" + connection +  " IP: " + connection.getInetAddress().getHostAddress();
	}

	public PlayerServer getPlayerServer() {
		System.out.println("player in connection " + playerServer);
		return playerServer;
	}

	@Override
	public void addObservables(IObserver iObserver) {
		this.iObserver = iObserver;
	}

	@Override
	public void deleteObservables() {
		this.iObserver = null;
	}

	public int getIdObservable() {
		return idObservable;
	}
	
	
}