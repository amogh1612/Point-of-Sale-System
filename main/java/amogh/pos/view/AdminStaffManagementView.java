package amogh.pos.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;

import amogh.pos.controller.Controller;
import amogh.pos.controller.DatabaseFactory;
import amogh.pos.model.Employee;
import amogh.pos.model.HourlyEmployee;
import amogh.pos.model.SalariedEmployee;

public class AdminStaffManagementView extends JFrame implements ActionListener {
	
	private JTextField nameTextField;
	private JTextField emailTextField;
	private JTextField addressTextField;
	private JTextField WageTextField;
	private JTextField hoursTextField;
	private JTextField idTextField;
	private JLabel hourlyLabel;
	private JLabel wageLabel;
	private JComboBox<String> empDrop;
	private JComboBox<String> roleDrop;
	private JTable staffTable;
	private JButton viewBTN;
	private JButton deleteBTN;
	private JButton addBTN;
	private JScrollPane staffTableScrollPane;
	private Controller<Employee> controller;
	private JButton dashboardBTN;
	
	public AdminStaffManagementView() {
		
		controller = DatabaseFactory.getEmployeeDatabase().read();
		if (controller == null) {
			controller = new Controller<Employee>();
		}
		
		// UI..
		setTitle("Staff Management");
		ImageIcon empIcon = new ImageIcon("data/stafflogo.png");
		this.setIconImage(empIcon.getImage());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 850, 659);
		setBackground(new Color(173, 216, 230));
		getContentPane().setBackground(new Color(176, 224, 230));
		getContentPane().setLayout(null);
		
		addComponents();
		
		listeners();
		populateStaffTable();
		
	}
	
	private void populateStaffTable() {
		
		try {
			getContentPane().remove(staffTableScrollPane);
		} catch (Exception e) {}
		
		int numberOfStaff = controller.size();
		String[] columnNames = {"Name", "Email", "Address", "Role", "Type", "Wage", "Hours"};
		String[][] data = new String[numberOfStaff][columnNames.length];
		for (int i = 0; i < controller.size(); i++) {
			data[i] = controller.get(i).getTableRowData();
		}
		
		staffTable = new JTable(data, columnNames);
		staffTable.getTableHeader().setFont(new Font("Ariel", Font.BOLD, 16));
		staffTableScrollPane = new JScrollPane(staffTable);
		staffTable.setBounds(10, 245, 814, 330);
		staffTableScrollPane.setBounds(10, 245, 814, 330);
		
		getContentPane().add(staffTableScrollPane);
		
		
		
	}
	
	private void listeners() {
		
		empDrop.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				
				int index = empDrop.getSelectedIndex();
				if (index == 0) {
					wageLabel.setText("Rate Per Hour:");
					hourlyLabel.setVisible(true); 
					hoursTextField.setVisible(true);
				} else {
					hourlyLabel.setVisible(false); 
					hoursTextField.setVisible(false);
					wageLabel.setText("Monthly Wage:");
				}
				
				
			}
			
		});
			
	}
	
	
//	nameTextField;
//	private JTextField emailTextField;
//	private JTextField addressTextField;
//	private JTextField WageTextField;
//	private JTextField hoursTextField;
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if (e.getSource() == addBTN) {
			
			String name = nameTextField.getText();
			String email = emailTextField.getText();
			String address = addressTextField.getText();
			String wage = WageTextField.getText();
			String hours = hoursTextField.getText();
			String role = roleDrop.getSelectedItem().toString();
			String id = idTextField.getText();
			
			
			if (id.isBlank() || name.isBlank() || name.isBlank() || address.isBlank() || wage.isBlank() || role.isBlank()) {
				JOptionPane.showMessageDialog(null, "No field should be empty", 
						"Error - Empty Fields", JOptionPane.ERROR_MESSAGE);
			} 
			// GET All Fields..
			double wageDoub = Double.parseDouble(wage);
			
			int idInt= Integer.parseInt(id);
			// Check if any one is empty! 
			
			if (empDrop.getSelectedIndex() == 0) {
				if (hours.isBlank()) {
					JOptionPane.showMessageDialog(null, "No field should be empty", 
							"Error - Empty Fields", JOptionPane.ERROR_MESSAGE);
				} 
				double hoursDoub = Double.parseDouble(hours);
				// Hourly..
				// int id, String name, String email, String address, String role, double hours, double hourlyWage
				HourlyEmployee hourEmp = new HourlyEmployee(idInt, name, email, address, role, hoursDoub, wageDoub);
				// add the object into controller.
				controller.add(hourEmp);
				
			} else {
				// Salaried..
				// int id, String name, String email, String address, String role, double monthlySalary
				SalariedEmployee salEmp = new SalariedEmployee(idInt, name, email, address, role, wageDoub);
				hours = "";
				// add the object into controller.
				controller.add(salEmp);
			}
			
			
			// write the controller into database.
			try {
				DatabaseFactory.getEmployeeDatabase().write(controller);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			
			populateStaffTable();
			
		} else if (e.getSource() == viewBTN) {
			
			int selectedRowCount = staffTable.getSelectedRowCount();
			if (selectedRowCount == 1) {
				
				int index = staffTable.getSelectedRow();
				Employee emp = controller.get(index);
				JOptionPane.showMessageDialog(null, emp.toString(), "Viewing Employee", JOptionPane.ERROR_MESSAGE);
				// joption pane
				
			} else {
				// to select 1 row
				JOptionPane.showMessageDialog(null, "ERROR- Please only view one employee at a time", "ERROR", JOptionPane.ERROR_MESSAGE);

			}
			
		} else if (e.getSource() == deleteBTN) {
			
			int selectedRowCount = staffTable.getSelectedRowCount();
			if (selectedRowCount == 1) {
				
				int result = JOptionPane.showConfirmDialog(this, "Do you really want to delete this employee?");
				if (result == JOptionPane.OK_OPTION) {
					int index = staffTable.getSelectedRow();
					controller.remove(index);
					try {
						DatabaseFactory.getEmployeeDatabase().write(controller);
					} catch (Exception e1) {
						e1.printStackTrace();
					}
					populateStaffTable();
					// success
					JOptionPane.showMessageDialog(null, "Employee has been deleted", "Success", JOptionPane.ERROR_MESSAGE);
				}
				
			}  else {
				// to select 1 row
				JOptionPane.showMessageDialog(null, "ERROR- Please only delete one employee at a time", "ERROR", JOptionPane.ERROR_MESSAGE);

			}
			
			
			
		}
		
		if (e.getSource() == dashboardBTN) {
			dispose();
			AdminDashboardView dash = new AdminDashboardView();
			dash.setVisible(true);
		}
		
	}
	
	private void addComponents() {
		
		JLabel titleLabel = new JLabel("Staff Management");
		titleLabel.setBackground(new Color(255, 255, 255));
		titleLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		titleLabel.setBounds(0, 0, 834, 24);
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		getContentPane().add(titleLabel);
		
		JPanel addLayoutPanel = new JPanel();
		addLayoutPanel.setBackground(new Color(245, 245, 245));
		addLayoutPanel.setBorder(new TitledBorder(null, "Create Staff", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		addLayoutPanel.setBounds(10, 25, 814, 209);
		getContentPane().add(addLayoutPanel);
		addLayoutPanel.setLayout(new GridLayout(0, 4, 10, 10));
		
		JLabel nameLabel = new JLabel("Enter Name:");
		addLayoutPanel.add(nameLabel);
		
		nameTextField = new JTextField();
		addLayoutPanel.add(nameTextField);
		nameTextField.setColumns(10);
		
		JLabel emailLabel = new JLabel("Enter Email:");
		addLayoutPanel.add(emailLabel);
		
		emailTextField = new JTextField();
		addLayoutPanel.add(emailTextField);
		emailTextField.setColumns(10);
		
		JLabel addressLabel = new JLabel("Enter Address:");
		addLayoutPanel.add(addressLabel);
		
		addressTextField = new JTextField();
		addLayoutPanel.add(addressTextField);
		addressTextField.setColumns(10);
		
		JLabel roleLabel = new JLabel("Select Role:");
		addLayoutPanel.add(roleLabel);
		
		roleDrop = new JComboBox<>();
		for (String role: new String[] {"CEO", "CTO", "Cleaner", "Security", "Inventory"}) {
			roleDrop.addItem(role);
		}
		addLayoutPanel.add(roleDrop);
		
		JLabel empTypeLabel = new JLabel("Select Type:");
		addLayoutPanel.add(empTypeLabel);
		
		empDrop = new JComboBox<>();
		addLayoutPanel.add(empDrop);
		empDrop.addItem("Hourly Employee");
		empDrop.addItem("Monthly Employee");
		
		JLabel lblNewLabel_6 = new JLabel("Enter ID:");
		addLayoutPanel.add(lblNewLabel_6);
		
		idTextField = new JTextField();
		addLayoutPanel.add(idTextField);
		idTextField.setColumns(10);
		
		wageLabel = new JLabel("Rate Per Hour:");
		addLayoutPanel.add(wageLabel);
		
		WageTextField = new JTextField();
		addLayoutPanel.add(WageTextField);
		WageTextField.setColumns(10);
		
		hourlyLabel = new JLabel("Hours:");
		addLayoutPanel.add(hourlyLabel);
		
		hoursTextField = new JTextField();
		addLayoutPanel.add(hoursTextField);
		hoursTextField.setColumns(10);
		
		addBTN = new JButton("Add");
		addBTN.setForeground(new Color(0, 0, 0));
		addBTN.setBackground(new Color(0, 139, 139));
		addLayoutPanel.add(addBTN);
		
		addBTN.addActionListener(this);
		
		deleteBTN = new JButton("Delete");
		deleteBTN.setBounds(735, 586, 89, 23);
		getContentPane().add(deleteBTN);
		
		deleteBTN.addActionListener(this);
		
		viewBTN = new JButton("View");
		viewBTN.setBounds(636, 586, 89, 23);
		getContentPane().add(viewBTN);
		
		viewBTN.addActionListener(this);
		
		dashboardBTN = new JButton("Dashboard");
		dashboardBTN.setBounds(10, 583, 117, 29);
		getContentPane().add(dashboardBTN);
		dashboardBTN.addActionListener(this);
		
		
		
	}
}
