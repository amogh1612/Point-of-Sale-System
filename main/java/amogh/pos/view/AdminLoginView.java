package amogh.pos.view;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.border.LineBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JToggleButton;
import javax.swing.JPasswordField;
import javax.swing.SwingConstants;
import java.awt.SystemColor;

public class AdminLoginView extends JFrame {

	private JPanel contentPane;
	private JTextField usernameField;
	
	// HARD CODE
	private static final String USERNAME = "pos";
	private static final String PASSWORD = "pospass";
	private JPasswordField passwordField;
	

	public AdminLoginView() {
		setBackground(Color.RED);
		setTitle("POS Software");
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 740, 580);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(176, 224, 230));
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		usernameField = new JTextField();
		usernameField.setBackground(new Color(245, 245, 245));
		usernameField.setBounds(273, 214, 281, 30);
		contentPane.add(usernameField);
		usernameField.setColumns(10);
		
		JButton loginButton = new JButton("Login");
		loginButton.setForeground(Color.DARK_GRAY);
		loginButton.setBackground(new Color(176, 196, 222));
		loginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				// TODO
				// VALIDATION.
				//	NO ANY FIELD SHOULD BE EMPTY!
				// 	PASSWORD & USERNAME SHOULD MATCH.
				// OPEN NEW SCREEN.
				String username = usernameField.getText();
				String password = String.valueOf(passwordField.getPassword());
				
				// 
				if (username.isBlank() || password.isBlank()) {
					JOptionPane.showMessageDialog(null, "No any field should be empty", 
							"Error - Empty Fields", JOptionPane.ERROR_MESSAGE);
				} else if (username.equals(USERNAME) && password.equals(PASSWORD)){
					dispose();
					AdminDashboardView adminDashboard = new AdminDashboardView();
					adminDashboard.setVisible(true);
				} else {
					JOptionPane.showMessageDialog(null, "Username or Password is wrong", 
							"Error", JOptionPane.ERROR_MESSAGE);
				}
				
			}
		});
		
		loginButton.setBounds(343, 328, 119, 30);
		contentPane.add(loginButton);
		
		passwordField = new JPasswordField();
		passwordField.setBackground(new Color(245, 245, 245));
		passwordField.setBounds(273, 273, 281, 29);
		contentPane.add(passwordField);
		
		JLabel passwordText = new JLabel("Enter Password:");
		passwordText.setBounds(163, 279, 145, 16);
		contentPane.add(passwordText);
		
		JLabel enterUsernameLabel = new JLabel("Enter Username:");
		enterUsernameLabel.setBounds(163, 221, 145, 16);
		contentPane.add(enterUsernameLabel);
		
		JPanel panel = new JPanel();
		panel.setForeground(new Color(255, 255, 255));
		panel.setBackground(new Color(255, 255, 255));
		panel.setBounds(82, 62, 577, 422);
		contentPane.add(panel);
		
		JLabel lblNewLabel_1 = new JLabel("Point of Sale System");
		panel.add(lblNewLabel_1);
		lblNewLabel_1.setForeground(new Color(105, 105, 105));
		lblNewLabel_1.setFont(new Font("Apple SD Gothic Neo", Font.PLAIN, 39));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		
	}
	
	private void doStuff() {
		
		
		
	}
}
