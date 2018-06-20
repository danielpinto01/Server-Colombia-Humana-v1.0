package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

import connections.Server;
import models.Manager;
import views.MainWindow;


public class Controller implements ActionListener{

	private MainWindow mainWindow;
	private Server server;
	@SuppressWarnings("unused")
	private Manager manager;

	public Controller() {
		manager = new Manager();
		mainWindow = new MainWindow(this);
		mainWindow.showDialogInit();
		refreshListConnection();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch (Events.valueOf(e.getActionCommand())) {
		case INIT:
			init();
			mainWindow.ocultDialogInit();
			break;
		default:
			break;	
		}
	}

	public void init() {
		server = new Server(mainWindow.getPort());
	}

	public void refreshListConnection() {
		Timer timer = new Timer(1000, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				mainWindow.updateList(server.getConnections());
			}
		});
		timer.start();
	}
}