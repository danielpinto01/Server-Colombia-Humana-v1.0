package persistence;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import models.Manager;
import models.PlayerServer;

public class FileManager {

	public PlayerServer readPlayer(String namePlayer) throws IOException, ParseException{
		PlayerServer playerServer = new PlayerServer();;

		JSONParser parser = new JSONParser();
		JSONObject root = (JSONObject) 
				parser.parse(new FileReader(namePlayer));
		JSONArray playersRoot = (JSONArray) root.get("Player");

		for (int i = 0; i < playersRoot.size(); i++) {
			JSONObject jsonObject = (JSONObject) playersRoot.get(i);

			String name = (String)jsonObject.get("namePlayer");
			String character = (String)jsonObject.get("characterPlayer");
			int x = (int)(long)jsonObject.get("positionX");
			int y = (int)(long)jsonObject.get("positionY");
			int life = (int)(long)jsonObject.get("lifePlayer");

			playerServer = Manager.createPlayer(name, character, x, y, life);
		} 
//		System.out.println("Aqui" + playerServer.toString());
		return playerServer;
	}

	@SuppressWarnings("unchecked")
	public void writeTotalListJson(ArrayList<PlayerServer> players) throws IOException {
		JSONObject root = new JSONObject();
		JSONArray array = new JSONArray();
		for (PlayerServer player : players) {
			JSONObject not = new JSONObject();
			not.put("namePlayer", player.getNamePlayer());
			not.put("characterPlayer", player.getCharacterPlayer());
			not.put("positionX", player.getPositionInX());
			not.put("positionY", player.getPositionInY());
			not.put("lifePlayer", player.getLifePlayer());
			array.add(not);
		}

		root.put("Players", array);

		FileWriter outputStream = new FileWriter(new File("TotalListPlayers.json"));
		outputStream.write(root.toJSONString());
		outputStream.close();
	}
}