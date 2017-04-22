package com.jonahisadev.fcfront;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class Login {

	private static JFrame frame;
	private static JPanel panel;
	
	public static void init() {
		panel = new JPanel();
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		panel.setLayout(new GridLayout(20, 1));
		
		frame = new JFrame("Login");
		frame.setSize(300, 550);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		
		JLabel lblAddress = new JLabel("IP Address:");
		panel.add(lblAddress);
		
		JTextField txtAddress = new JTextField();
		panel.add(txtAddress);
		
		JLabel lblPort = new JLabel("Port:");
		panel.add(lblPort);
		
		JTextField txtPort = new JTextField();
		panel.add(txtPort);
		
		JLabel lblUsername = new JLabel("Username:");
		panel.add(lblUsername);
		
		JTextField txtUsername = new JTextField();
		panel.add(txtUsername);
		
		JButton btnConnect = new JButton("Connect");
		btnConnect.addActionListener(e -> {
			frame.dispose();
			Client client = new Client(
				txtAddress.getText(),
				Integer.parseInt(txtPort.getText())
			);
			Window.init(client);
			client.sendData("/c/~" + txtUsername.getText());
		});
		panel.add(btnConnect);
		
		frame.setContentPane(panel);
		frame.setVisible(true);
	}

}