package amogh.pos.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import amogh.pos.model.Customer;
import amogh.pos.model.Employee;
import amogh.pos.controller.Controller;
import amogh.pos.controller.DatabaseFactory;

import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.JTable;
import javax.swing.JCheckBox;
import javax.swing.JButton;



public class CustomerManagementView extends JFrame implements ActionListener {
	
	private JScrollPane tableScrollPane;
	private Controller<Customer> controller;
	private JTable table_2;
	private JButton viewButton;
	private JButton deleteButton;
	private JButton dashboardBTN;


	
	public CustomerManagementView() {
		controller = DatabaseFactory.getCustomerDatabase().read();
		if (controller == null) {
			controller = new Controller<Customer>();
		}
		
		// UI..
		setTitle("Staff Management");
		ImageIcon empIcon = new ImageIcon("data/stafflogo.png");
		this.setIconImage(empIcon.getImage());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 868, 534);
		setBackground(new Color(173, 216, 230));
		getContentPane().setBackground(new Color(176, 224, 230));
		getContentPane().setLayout(null);
		
		JLabel titleLabel = new JLabel("Customer Management");
		titleLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		titleLabel.setBounds(10, 11, 832, 16);
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		getContentPane().add(titleLabel);
		
		viewButton = new JButton("View");
		viewButton.setBounds(598, 452, 117, 29);
		getContentPane().add(viewButton);
		
		deleteButton = new JButton("Delete");
		deleteButton.setBounds(725, 452, 117, 29);
		getContentPane().add(deleteButton);
		
		
		populateCustomerTable();
		
	}
	
	private void populateCustomerTable() {
		
		try {
			getContentPane().remove(tableScrollPane);
		} catch (Exception e) {}
		
		int numberOfCustomer = controller.size();
		// int id, String name, String email, String address, int orderID
		String[] columnNames = {"Name", "Email", "Address", "Order ID", "Member ID", "Member Since"};
		String[][] data = new String[numberOfCustomer][columnNames.length];
		for (int i = 0; i < controller.size(); i++) {
			data[i] = (controller.get(i)).getTableRowData();
		}
		
		table_2 = new JTable(data, columnNames);
		table_2.getTableHeader().setFont(new Font("Ariel", Font.BOLD, 16));
		tableScrollPane = new JScrollPane(table_2);
		table_2.setBounds(10, 38, 834, 403);
		tableScrollPane.setBounds(10, 38, 834, 403);
		
		getContentPane().add(tableScrollPane);
		
		dashboardBTN = new JButton("Dashboard");
		dashboardBTN.setBounds(10, 452, 117, 29);
		getContentPane().add(dashboardBTN);
		dashboardBTN.addActionListener(this);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == dashboardBTN) {
			dispose();
			AdminDashboardView dash = new AdminDashboardView();
			dash.setVisible(true);
		}
		
	}
	

	
	
}
