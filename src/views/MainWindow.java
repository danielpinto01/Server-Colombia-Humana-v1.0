package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;

import connections.Connection;
import controllers.Controller;
import controllers.Events;

public class MainWindow extends JFrame{

	private static final long serialVersionUID = 1L;
	
	private JList<Connection> sockets;
	private DefaultListModel<Connection> modelSockets;
	
	private JDialogInit jDialogInit;
	
	private JButton btnExit;
	
	public MainWindow(Controller controller) {
		setTitle("Servidor v1.0");
		setIconImage(new ImageIcon(getClass().getResource("/images/icon.jpg")).getImage());
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(320, 200);
		setLocationRelativeTo(null);
		
		jDialogInit = new JDialogInit(controller, this);
		
		modelSockets = new DefaultListModel<>();
		sockets = new JList<>(modelSockets);
		sockets.setFont(new Font("Century Gothic", 1, 16));
		add(sockets, BorderLayout.CENTER);
		
		btnExit = new JButton("Salir");
		btnExit.setBackground(Color.GRAY);
		btnExit.setForeground(Color.WHITE);
		btnExit.setFont(new Font("Century Gothic", 0, 18));
		btnExit.setFocusable(false);
		btnExit.addActionListener(controller);
		btnExit.setActionCommand(Events.EXIT.toString());
		add(btnExit, BorderLayout.SOUTH);
		
	}
	
	public void setModel(ArrayList<Connection> sockets){
		modelSockets.clear();
		for (Connection connection : sockets) {
			modelSockets.addElement(connection);
		}
	}
	
	public void showDialogInit() {
		jDialogInit.setVisible(true);
	}
	
	public void ocultDialogInit() {
		jDialogInit.setVisible(false);
	}
	
	public int getPort() {
		return jDialogInit.getPort();
	}
}