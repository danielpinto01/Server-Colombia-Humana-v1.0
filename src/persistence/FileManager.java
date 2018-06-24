package persistence;

import java.io.FileWriter;
import java.util.ArrayList;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

import models.Enemy;
import models.User;

public class FileManager {

	public static void saveFile(String path, ArrayList<User> users) {
		Element root = new Element("Players");
		Document doc = new Document(root);
		for (User user : users) {
			Element player = new Element("Player");
			Element name = new Element("Name").setText(user.getName());
			Element positionX = new Element("X").setText(String.valueOf(user.getPositionX()));
			Element positionY = new Element("Y").setText(String.valueOf(user.getPositionY()));
			player.addContent(name);
			player.addContent(positionX);
			player.addContent(positionY);
			root.addContent(player);
		}
		try {
			FileWriter fileWriter = new FileWriter(path);
			XMLOutputter xmlOutputter = new XMLOutputter(Format.getPrettyFormat());
			xmlOutputter.setFormat(Format.getCompactFormat());
			xmlOutputter.output(doc, fileWriter);
			fileWriter.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}
	
	public static void saveFileEnemy(String path, Enemy enemy) {
		Element root = new Element("Enemys");
		Document doc = new Document(root);
			Element player = new Element("Enemy");
			Element positionX = new Element("X").setText(String.valueOf(enemy.getPositionInX()));
			Element positionY = new Element("Y").setText(String.valueOf(enemy.getPositionInY()));
			player.addContent(positionX);
			player.addContent(positionY);
			root.addContent(player);
		try {
			FileWriter fileWriter = new FileWriter(path);
			XMLOutputter xmlOutputter = new XMLOutputter(Format.getPrettyFormat());
			xmlOutputter.setFormat(Format.getCompactFormat());
			xmlOutputter.output(doc, fileWriter);
			fileWriter.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}
}