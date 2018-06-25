package persistence;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

import com.sun.xml.internal.bind.v2.schemagen.xmlschema.List;

import models.Bees;
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
	
	public static void saveBeesFile(File file, ArrayList<Bees> beesList) {
		Element root = new Element("Players");
		Document doc = new Document(root);
		for (Bees bees : beesList) {
			Element element = new Element("Bees");
			Element name = new Element("Id").setText(String.valueOf(bees.getId()));
			Element positionX = new Element("X").setText(String.valueOf(bees.getX()));
			Element positionY = new Element("Y").setText(String.valueOf(bees.getY()));
			Element type = new Element("Type").setText(String.valueOf(bees.getType()));
			element.addContent(name);
			element.addContent(positionX);
			element.addContent(positionY);
			element.addContent(type);
			root.addContent(element);
		}
		try {
			FileWriter fileWriter = new FileWriter(file);
			XMLOutputter xmlOutputter = new XMLOutputter(Format.getPrettyFormat());
			xmlOutputter.setFormat(Format.getCompactFormat());
			xmlOutputter.output(doc, fileWriter);
			fileWriter.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}
	
	public ArrayList<Bees> listS() {
		ArrayList<Bees> list = new ArrayList<>();
		list.add(new Bees());
		list.add(new Bees());
		list.add(new Bees());
		return list;
	}
	
	
	public static void main(String[] args) {
		FileManager fileManager = new FileManager();
		FileManager.saveBeesFile(new File("beesPrueba.xml"), fileManager.listS());
	}
}