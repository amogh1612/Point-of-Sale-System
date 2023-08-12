package amogh.pos.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import amogh.pos.controller.Controller;
import amogh.pos.controller.DatabaseFactory;
import amogh.pos.model.Employee;

public class AdminDashboardView extends JFrame {

	private JPanel contentPane;
	private Controller<Employee> employeeController;
	private JLabel lblNewLabel;
	private JPanel panel;
	private JButton shoppingPanel;
	private JButton productPanel;
	private JButton customerManagementButton;
	private JButton staffManageButton;

	/**
	 * Create the frame.
	 */
	public AdminDashboardView() {
		
		employeeController = DatabaseFactory.getEmployeeDatabase().read();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 632, 467);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		lblNewLabel = new JLabel("Adminstrator Dashboard");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblNewLabel, BorderLayout.NORTH);
		
		panel = new JPanel();
		panel.setBackground(new Color(176, 224, 230));
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(new GridLayout(2, 3, 10, 10));
		
		staffManageButton = new JButton("Staff Management");
		staffManageButton.setBackground(Color.WHITE);
		panel.add(staffManageButton);
		staffManageButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				AdminStaffManagementView staffManagement = new AdminStaffManagementView();
				staffManagement.setVisible(true);
				
			}
		});
		
		customerManagementButton = new JButton("Customer Management");
		panel.add(customerManagementButton);
		customerManagementButton.setBackground(Color.WHITE);
		customerManagementButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				CustomerManagementView customerManagement = new CustomerManagementView();
				customerManagement.setVisible(true);
				
			}
		});
		
		productPanel = new JButton("Product Panel");
		panel.add(productPanel);
		productPanel.setBackground(Color.WHITE);
		productPanel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				ProductPanel products = new ProductPanel();
				products.setVisible(true);
				
			}
		});
		
		shoppingPanel = new JButton("Shopping ");
		panel.add(shoppingPanel);
		shoppingPanel.setBackground(Color.WHITE);
		shoppingPanel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				ShoppingFrame shop = new ShoppingFrame();
				shop.setVisible(true);
				
			}
		});
		
		listeners();
		
	}
	
	private void listeners() {
		
	}
	
}
