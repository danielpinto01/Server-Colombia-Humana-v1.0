package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JOptionPane;
import javax.swing.Timer;

import connections.Server;
import models.Manager;
import views.MainWindow;

public class Controller implements ICObserver, ActionListener{

	private Server server;
	private MainWindow mainWindow;
	
	public Controller() {
		mainWindow = new MainWindow(this);
		mainWindow.showDialogInit();
//		int port = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el puerto", "2000"));
//		try {
//			server = new Server(port);
//		} catch (IOException e) {
//			JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
//		}
		refreshListConnection();
	}
	
	public void init() {
		try {
			server = new Server(mainWindow.getPort());
		} catch (IOException e1) {
			e1.printStackTrace();
			System.exit(0);
		}
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

	@Override
	public void actionPerformed(ActionEvent e) {
		switch (Events.valueOf(e.getActionCommand())) {
		case INIT:
			init();
			mainWindow.ocultDialogInit();
			mainWindow.setVisible(true);
			break;
		case EXIT:
			System.exit(0);
			break;
		default:
			break;	
		}	
	}
}