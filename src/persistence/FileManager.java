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

	@SuppressWarnings("unused")
	public PlayerServer readPlayer(String namePlayer) throws IOException, ParseException{
		PlayerServer playerServer = new PlayerServer();;

		JSONParser parser = new JSONParser();
		JSONObject root = (JSONObject) 
				parser.parse(new FileReader(namePlayer));
//		parser.parse(new FileReader(namePlayer+ "Information.json"));
		JSONArray playersRoot = (JSONArray) root.get("Player");

		for (int i = 0; i < playersRoot.size(); i++) {
			JSONObject jsonObject = (JSONObject) playersRoot.get(i);

			String name = (String)jsonObject.get("namePlayer");
			String character = (String)jsonObject.get("characterPlayer");
			int x = (int)(long)jsonObject.get("positionX");
			int y = (int)(long)jsonObject.get("positionY");

			playerServer = Manager.createPlayer(name, character, x, y);
		} 
				System.out.println("Aqui" + playerServer.toString());
		return playerServer;
	}
	

//	@SuppressWarnings("unused")
//	public ArrayList<String> readPlayerString(String namePlayer) throws IOException, ParseException{
//		//		PlayerServer playerServer = new PlayerServer();
//
//		ArrayList<String> list = new ArrayList<>();
//
//		JSONParser parser = new JSONParser();
//		JSONObject root = (JSONObject) 
//				parser.parse(new FileReader(namePlayer));
//		//				parser.parse(new FileReader(namePlayer+ "Information.json"));
//		JSONArray playersRoot = (JSONArray) root.get("Player");
//		for (int i = 0; i < playersRoot.size(); i++) {
//			JSONObject jsonObject = (JSONObject) playersRoot.get(i);
//
//			String name = (String)jsonObject.get("namePlayer");
//			System.out.println(name);
//			String character = (String)jsonObject.get("characterPlayer");
//			int x = (int)(long)jsonObject.get("positionX");
//			int y = (int)(long)jsonObject.get("positionY");
//
//			//				playerServer = Manager.createPlayer(name, character, x, y);
//
//			list.add(name);
//			list.add(character);
//			list.add(String.valueOf(x));
//			list.add(String.valueOf(y));
//		}
//		System.out.println(list.toString());
//		//		System.out.println("Aqui" + playerServer.toString());
//		return list;
//	}


//	public void writeJsonTotalList(ArrayList<String> list) throws IOException{
//		JSONObject root = new JSONObject();
//		JSONArray array = new JSONArray();
//		for (String string : list) {
//			JSONObject job = new JSONObject();
//			job.put("id", jobl.getCount());
//			job.put("title", jobl.getName());
//			job.put("Description", jobl.getDescription());
//			job.put("Aplication", jobl.getApplication());
//			job.put("Time", jobl.getTime());
//			job.put("Location", jobl.getLocation());
//
//			job.put("company", jobl.getCompany().getNameCompany());
//			job.put("company_url", jobl.getCompany().getUrlCompany());
//			job.put("company_logo", jobl.getCompany().getImageCompany());
//
//			job.put("url", "https://www.gitHub.com/jobs/" + jobl.getName() + "/" + jobl.getCompany().getNameCompany());
//			job.put("created_ad", jobl.getDate());
//
//			array.add(job);
//		}	
//		root.put("Jobs", array);
//
//		FileWriter outputStream = new FileWriter(new File("./Report.json"));
//		outputStream.write(root.toJSONString());
//		outputStream.close();
//	}

	@SuppressWarnings("unchecked")
	public void writeJson(ArrayList<PlayerServer> players) throws IOException {
		JSONObject root = new JSONObject();
		JSONArray array = new JSONArray();
		for (PlayerServer player : players) {
			JSONObject not = new JSONObject();
			not.put("namePlayer", player.getNamePlayer());
			not.put("characterPlayer", player.getCharacterPlayer());
			not.put("positionX", player.getPositionInX());
			not.put("positionY", player.getPositionInY());
			array.add(not);
		}

		root.put("Players", array);

		FileWriter outputStream = new FileWriter(new File("Totalplayers.json"));
		outputStream.write(root.toJSONString());
		outputStream.close();
	}


	public static void main(String[] args) {
		FileManager fileManager = new FileManager();
		try {
			fileManager.readPlayer("Juanita");
		} catch (IOException | ParseException e) {
			e.printStackTrace();
		}
	}

}
