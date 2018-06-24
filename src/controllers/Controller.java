package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JOptionPane;
import javax.swing.Timer;

import connections.Server;
import views.MainWindow;

public class Controller implements ICObserver {

	private Server server;
	private MainWindow mainWindow;
	
	public Controller() {
		int port = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el puerto", "2000"));
		try {
			server = new Server(port);
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
		}
		mainWindow = new MainWindow(this);
		refreshListConnection();
	}
	
	public void refreshListConnection() {
		Timer timer = new Timer(1000, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				mainWindow.setModel(server.getListToGame());
			}
		});
		timer.start();
		mainWindow.setVisible(true);
	}
	
	@Override
	public void update() {
		
	}
}