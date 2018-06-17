package views;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import controllers.Controller;
import controllers.Events;

public class JDialogInit extends JDialog{

	private static final String TITLE = "Init";
	private static final int SIZE_X = 300;
	private static final int SIZE_Y = 220;

	private static final long serialVersionUID = 1L;
	
	private JLabel lbInformation;
	private JTextField txtPort;
	private JButton btnInit;
	
	public JDialogInit(Controller controller, MainWindow mainWindow) {
		super(mainWindow, true);
		setLayout(new GridLayout(3, 1));
		setBackground(Color.WHITE);
		setTitle(TITLE);
		setSize(SIZE_X, SIZE_Y);
		setLocationRelativeTo(null);
		
		lbInformation = new JLabel("Enter the port:");
		lbInformation.setBackground(Color.GRAY);
		lbInformation.setHorizontalAlignment(SwingConstants.CENTER);
		lbInformation.setFont(new Font("Century Gothic", 0, 18));
		add(lbInformation);
		
		txtPort = new JTextField(12);
		txtPort.setFont(new Font("Century Gothic", 2, 16));
		txtPort.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 5)); 
//		txtPort.setBorder(BorderFactory.createTitledBorder("Port"));
		add(txtPort);
		
		btnInit = new JButton("Init");
		btnInit.setBackground(Color.GRAY);
		btnInit.setForeground(Color.WHITE);
		btnInit.setFont(new Font("Century Gothic", 0, 18));
		btnInit.setFocusable(false);
		btnInit.addActionListener(controller);
		btnInit.setActionCommand(Events.INIT.toString());
		add(btnInit);
	}
	
	public int getPort() {
		return Integer.parseInt(txtPort.getText());
	}
}