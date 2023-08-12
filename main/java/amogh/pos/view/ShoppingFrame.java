package amogh.pos.view;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import amogh.pos.controller.Controller;
import amogh.pos.controller.DatabaseFactory;
import amogh.pos.model.Product;
import amogh.pos.model.ProductPartPanelView;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class ShoppingFrame extends JFrame implements ActionListener{

	private JPanel contentPane;
	private ArrayList<ProductPartPanelView> productPanelList;
	private ArrayList<Product> cartProducts;
	private Controller<Product> productController;
	private int startIndex;
	private int endIndex;
	private JTable table;
	private JPanel tablePanel;
	private JScrollPane pane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private static final int MAX_PRODUCTS_IN_PANEL = 6;
	private JButton orderButton, previousButton, btnNext;
	private JButton dashboardBTN;
	
	public ShoppingFrame() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 772, 722);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(2, 1, 10, 10));
		
		cartProducts = new ArrayList<>();
		
		initializeUI();
		
		productController = DatabaseFactory.getProductDatabase().read();
		if (productController == null) {
			productController = new Controller<Product>();
		}
		
		// default..
		startIndex = 0;
		endIndex = 6;
		
		putProducts(startIndex, endIndex);
		
	}
	
	private void putProducts(int startIndex, int endIndex) {
		
		int index = 0;
		for (int i = startIndex; i < endIndex; i++) {
			
			if (i >= 0 && i < productController.size()) {
				
				// 0 - 6							// 50 - 56
				productPanelList.get(index).setupProduct(productController.get(i));
				
			}
			index++;
			
		}
		
	}
	
	// Butter // 1
	
	// Oil.
	
	/*
	 * Milk, 2
	 * Butter, 9
	 * Oil, 1
	 */
	
	// 10 Butter
	
	public void addProductToCart(Product product) {
		
		Product copy = new Product(product);
		copy.setQuantity(1);
		boolean alreadyAdded = false;
		int index = 0;
		
		for (int i = 0; i < cartProducts.size(); i++) {
			if (cartProducts.get(i).getSKUId().equals(copy.getSKUId())) {
			
				// 1
				index = i;
				copy.setQuantity(cartProducts.get(i).getQuantity() + 1);
				// 2
				alreadyAdded = true;
				
			}
		}
		if (alreadyAdded == false) {
			
			this.cartProducts.add(copy);
			
		} else {
			
			this.cartProducts.set(index, copy);
			
		}
		
		this.updateTable();
		
	}
	
	private void initializeUI() {
		
		JPanel productsPanel = new JPanel();
		productsPanel.setBorder(new EmptyBorder(0, 10, 10, 10));
		productsPanel.setBackground(new Color(176, 224, 230));
		contentPane.add(productsPanel);
		productsPanel.setLayout(new GridLayout(2, 3, 10, 10));
		
		productPanelList = new ArrayList<>();
		for (int i = 0; i < 6; i++) {
			
			ProductPartPanelView panel = new ProductPartPanelView(this);
			productPanelList.add(panel);
			productsPanel.add(panel);
			
		}
		
		tablePanel = new JPanel();
		tablePanel.setBackground(Color.WHITE);
		contentPane.add(tablePanel);
		tablePanel.setLayout(null);
		
		previousButton = new JButton("< Previous");
		previousButton.addActionListener(this);
		previousButton.setBounds(10, 11, 89, 23);
		tablePanel.add(previousButton);
		
		btnNext = new JButton("Next >");
		btnNext.addActionListener(this);
		btnNext.setBounds(647, 11, 89, 23);
		tablePanel.add(btnNext);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Customer Details", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(431, 45, 305, 214);
		tablePanel.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Name: ");
		lblNewLabel.setBounds(6, 28, 61, 16);
		panel.add(lblNewLabel);
		
		JLabel lblAddress = new JLabel("Address:");
		lblAddress.setBounds(6, 65, 61, 16);
		panel.add(lblAddress);
		
		JLabel lblPhoneNumber = new JLabel("Phone Number:");
		lblPhoneNumber.setBounds(6, 106, 117, 16);
		panel.add(lblPhoneNumber);
		
		textField = new JTextField();
		textField.setBounds(55, 23, 229, 26);
		panel.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(65, 60, 219, 26);
		panel.add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(106, 101, 178, 26);
		panel.add(textField_2);
		
		orderButton = new JButton("Place Order");
		orderButton.setBounds(167, 160, 117, 29);
		panel.add(orderButton);
		
		updateTable();
		
	}
	
	private void updateTable() {
		
		try {
			tablePanel.remove(pane);
		} catch (Exception e) {}
		
		ArrayList<String[]> list = new ArrayList<>();
		for (Product each: cartProducts) {
			list.add(each.getCartTableRowData());
		}
		
		table = new JTable(list.toArray(new String[][] {}),
				new String[] {"ID", "Name", "Quantity"});
		pane = new JScrollPane(table);
		table.setBounds(10, 45, 411, 214);
		pane.setBounds(10, 45, 411, 214);
		tablePanel.add(pane);
		
		dashboardBTN = new JButton("Dashboard");
		dashboardBTN.setBounds(10, 285, 117, 29);
		tablePanel.add(dashboardBTN);
		dashboardBTN.addActionListener(this);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if (e.getSource() == btnNext) {
			
			if (startIndex + MAX_PRODUCTS_IN_PANEL < productController.size()) {
				
				
				for (int i = 0; i < MAX_PRODUCTS_IN_PANEL; i++) {
					this.productPanelList.get(i).setupProduct(null);
				}
				
				startIndex += MAX_PRODUCTS_IN_PANEL;
				endIndex += MAX_PRODUCTS_IN_PANEL;
				this.putProducts(startIndex, endIndex);
			}
			
		} else if (e.getSource() == previousButton) {
			
			if (startIndex - MAX_PRODUCTS_IN_PANEL >= 0) {
				
				
				for (int i = 0; i < MAX_PRODUCTS_IN_PANEL; i++) {
					this.productPanelList.get(i).setupProduct(null);
				}
				
				startIndex -= MAX_PRODUCTS_IN_PANEL;
				endIndex -= MAX_PRODUCTS_IN_PANEL;
				this.putProducts(startIndex, endIndex);
			}
			
		} else if (e.getSource() == orderButton) {
			
			JOptionPane.showMessageDialog(null, "Order Places", "Successful Order", JOptionPane.INFORMATION_MESSAGE);
			
		}  
		if (e.getSource() == dashboardBTN) {
			dispose();
			AdminDashboardView dash = new AdminDashboardView();
			dash.setVisible(true);
		}
		
	}
}
