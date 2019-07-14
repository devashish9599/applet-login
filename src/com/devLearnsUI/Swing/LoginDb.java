package com.devLearnsUI.Swing;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class LoginDb {

	private JFrame frame;
	private JTextField textuserName;
	private JTextField textpass;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginDb window = new LoginDb();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public LoginDb() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		textuserName = new JTextField();
		textuserName.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		textuserName.setBackground(new Color(255, 255, 255));
		textuserName.setForeground(new Color(0, 0, 0));
		textuserName.setBounds(138, 90, 172, 34);
		frame.getContentPane().add(textuserName);
		textuserName.setColumns(10);
		
		textpass = new JTextField();
		textpass.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		textpass.setBounds(138, 154, 172, 34);
		frame.getContentPane().add(textpass);
		textpass.setColumns(10);
		
		JLabel lblUserName = new JLabel("User Name :");
		lblUserName.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		lblUserName.setBounds(45, 99, 94, 16);
		frame.getContentPane().add(lblUserName);
		
		JLabel lblPassword = new JLabel("  Password :");
		lblPassword.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		lblPassword.setBounds(45, 163, 81, 16);
		frame.getContentPane().add(lblPassword);
		
		JButton btnlogin = new JButton("Login");
		btnlogin.setBackground(new Color(238, 232, 170));
		btnlogin.setForeground(new Color(0, 0, 0));
		btnlogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Class.forName("com.mysql.jdbc.Driver");
					Connection connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/swingdb","root","dev@0920");
			
					String name =  textuserName.getText();
					String pass = textpass.getText();
					String query = "SELECT * FROM details WHERE name=? AND password=?";
					PreparedStatement statement = connect.prepareStatement(query);
					statement.setString(1, name);
					statement.setString(2, pass);
					ResultSet set = statement.executeQuery();
					if (set.next()) {
						JOptionPane.showMessageDialog(null, "Hi "+name);
						 }
					 else {
						JOptionPane.showMessageDialog(null, name+" does not exist");
					}
					
					} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		btnlogin.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		btnlogin.setBounds(176, 215, 97, 25);
		frame.getContentPane().add(btnlogin);
	}
}
