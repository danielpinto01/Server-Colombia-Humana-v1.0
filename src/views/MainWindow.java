package views;

import java.awt.BorderLayout;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import connections.Connection;
import controllers.Controller;

public class MainWindow extends JFrame{

	private static final long serialVersionUID = 1L;
	private JPanel pnlList;
	private JButton btnDownload;
	private JDialogInit jDialogInit;
	private DefaultListModel<String> defaultListModel;
	private JList<String> jList;
	private JList<Connection> sockets;
	private DefaultListModel<Connection> modelSockets;
	
	
	public MainWindow(Controller controller ) {
		setLayout(new BorderLayout());
		setBackground(Color.BLUE);
		setTitle("Servidor v1.0");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(300, 220);
		setLocationRelativeTo(null);
		
		
		jDialogInit = new JDialogInit(controller, this);
		
		defaultListModel = new DefaultListModel<>();
		jList = new JList<>(defaultListModel);
		
		pnlList = new JPanel();
		pnlList.setBackground(Color.WHITE);
		pnlList.setFont(new Font("Century Gothic", 1, 16));
		
		pnlList.add(new JScrollPane(jList));
		jList.setBackground(Color.GRAY);
		add(pnlList, BorderLayout.CENTER);
		
		btnDownload = new JButton("Exit");
		btnDownload.setBackground(Color.GRAY);
		btnDownload.setForeground(Color.WHITE);
		btnDownload.setFont(new Font("Century Gothic", 0, 18));
		btnDownload.setFocusable(false);
		add(btnDownload, BorderLayout.SOUTH);
		
		modelSockets = new DefaultListModel<>();
		sockets = new JList<>(modelSockets);
		add(sockets, BorderLayout.CENTER);
		
		setVisible(true);
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
	
	public void initList(ArrayList<String> messages) {
		pnlList.removeAll();
		pnlList.setLayout(new GridLayout(messages.size(), 1));
		for (String string : messages) {
			addListToPanel(string);
		}
		pnlList.revalidate();
		pnlList.repaint();
	}

	private void addListToPanel(String string) {
		JPanel jPanel = new JPanel();
		jPanel.setBackground(Color.WHITE);
		JLabel lbArchive = new JLabel("//';" + string);
		lbArchive.setFont(new Font("Century Gothic", 0, 20));
		jPanel.add(lbArchive);
		pnlList.add(jPanel);
	}
	
	public void updateList(ArrayList<Connection> sockets){
		modelSockets.clear();
		for (Connection connection : sockets) {
			modelSockets.addElement(connection);
		}
	}
}