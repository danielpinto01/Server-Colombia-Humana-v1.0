package models;

import java.util.ArrayList;

public class Manager {
	
	private ArrayList<PlayerServer> playerServers;
	
	public Manager() {
		playerServers = new ArrayList<>();
	}
	
	public static PlayerServer createPlayer(String namePlayer, String characterPlayer, int positionX, int positionY, int lifePlayer) {
		return new PlayerServer(namePlayer, characterPlayer, positionX, positionY, lifePlayer);
	}
	
	public void addPlayerToList(PlayerServer playerServer) {
		playerServers.add(playerServer);
	}

	public ArrayList<PlayerServer> getPlayerServers() {
		return playerServers;
	}
}