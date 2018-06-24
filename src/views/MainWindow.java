package views;

import java.awt.BorderLayout;

import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JList;

import connections.Connection;
import controllers.Controller;

public class MainWindow extends JFrame{

	private static final long serialVersionUID = 1L;
	
	private JList<Connection> sockets;
	private DefaultListModel<Connection> modelSockets;
	
	public MainWindow(Controller controller) {
		setTitle("Servidor v1.0");
		setIconImage(new ImageIcon(getClass().getResource("/images/icon.jpg")).getImage());
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(400, 200);
//		setLocationRelativeTo(null);
		
		modelSockets = new DefaultListModel<>();
		sockets = new JList<>(modelSockets);
		add(sockets, BorderLayout.CENTER);
	}
	
	public void setModel(ArrayList<Connection> sockets){
		modelSockets.clear();
		for (Connection connection : sockets) {
			modelSockets.addElement(connection);
		}
	}
}