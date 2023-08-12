package amogh.pos.view;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.border.TitledBorder;

import amogh.pos.controller.Controller;
import amogh.pos.controller.DatabaseFactory;
import amogh.pos.model.Product;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTable;
import javax.swing.JTextField;

public class ShoppingView extends JFrame implements ActionListener{
	private JPanel panel_1_1;
	private JPanel panel_1;
	private JPanel first_page_panel;
	private JPanel page1product1;
	private JPanel page1product2;
	private JPanel page1product3;
	private JPanel page1product4;
	private JPanel page1product5;
	private JPanel page1product6;
	
	
	
	private JPanel second_page_panel;
	private JPanel page2product1;
	private JPanel page2product2;
	private JPanel page2product3;
	private JPanel page2product4;
	private JPanel page2product5;
	private JPanel page2product6;
	
	private JButton nextButton;
	private JButton prevButton;
	private JLabel lblNewLabel;
	private JTable customerTable;
	
	private JTextField productPrice1;
	private JTextField productPrice2;
	private JTextField productPrice3;
	private JTextField productPrice4;
	private JTextField productPrice5;
	private JTextField productPrice6;
	
	private Controller<Product> productController;
	
	private JLabel product1title;
	private JLabel product2title;
	private JLabel product3title;
	private JLabel product4title;
	private JLabel product5title;
	private JLabel product6title;
	
	
	
	public static void main(String[]args) {
		
			
			try {
				UIManager.setLookAndFeel(
				        UIManager.getSystemLookAndFeelClassName());
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						ShoppingView frame = new ShoppingView();
						frame.setVisible(true);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
		
	}
	
	
	public ShoppingView() {
		productController = DatabaseFactory.getProductDatabase().read();
		if (productController == null) {
			productController = new Controller<Product>();
		}
		
		
		
		
		
		getContentPane().setBackground(new Color(255, 255, 255));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 747, 566);
		getContentPane().setLayout(null);
		
		panel_1 = new JPanel();
		panel_1.setBackground(new Color(135, 206, 250));
		panel_1.setBounds(53, 349, 284, 169);
		getContentPane().add(panel_1);
		
		customerTable = new JTable();
		panel_1.add(customerTable);
		
		panel_1_1 = new JPanel();
		panel_1_1.setBackground(new Color(216, 191, 216));
		panel_1_1.setBounds(364, 387, 327, 131);
		getContentPane().add(panel_1_1);
		
		first_page_panel = new JPanel();
		first_page_panel.setBackground(new Color(173, 216, 230));
		first_page_panel.setBounds(53, 40, 639, 281);
		getContentPane().add(first_page_panel);
		first_page_panel.setLayout(null);
		
		page1product1 = new JPanel();
		page1product1.setBounds(29, 17, 166, 106);
		first_page_panel.add(page1product1);
		page1product1.setLayout(null);
		
		product1title = new JLabel("title");
		product1title.setBounds(6, 6, 61, 16);
		page1product1.add(product1title);
		
		productPrice1 = new JTextField();
		productPrice1.setBounds(99, 74, 61, 26);
		page1product1.add(productPrice1);
		productPrice1.setColumns(10);
		
		JLabel product1title_1 = new JLabel("$");
		product1title_1.setBounds(88, 79, 13, 16);
		page1product1.add(product1title_1);
		
		page1product4 = new JPanel();
		page1product4.setBounds(29, 143, 166, 106);
		first_page_panel.add(page1product4);
		page1product4.setLayout(null);
		
		product4title = new JLabel("New label");
		product4title.setBounds(6, 6, 61, 16);
		page1product4.add(product4title);
		
		productPrice4 = new JTextField();
		productPrice4.setColumns(10);
		productPrice4.setBounds(99, 74, 61, 26);
		page1product4.add(productPrice4);
		
		JLabel product1title_1_3 = new JLabel("$");
		product1title_1_3.setBounds(91, 79, 13, 16);
		page1product4.add(product1title_1_3);
		
		page1product2 = new JPanel();
		page1product2.setBounds(238, 17, 166, 106);
		first_page_panel.add(page1product2);
		page1product2.setLayout(null);
		
		product2title = new JLabel("New label");
		product2title.setBounds(6, 6, 61, 16);
		page1product2.add(product2title);
		
		productPrice2 = new JTextField();
		productPrice2.setColumns(10);
		productPrice2.setBounds(99, 74, 61, 26);
		page1product2.add(productPrice2);
		
		JLabel product1title_1_1 = new JLabel("$");
		product1title_1_1.setBounds(91, 79, 13, 16);
		page1product2.add(product1title_1_1);
		
		page1product5 = new JPanel();
		page1product5.setBounds(238, 143, 166, 106);
		first_page_panel.add(page1product5);
		page1product5.setLayout(null);
		
		product5title = new JLabel("New label");
		product5title.setBounds(6, 6, 61, 16);
		page1product5.add(product5title);
		
		productPrice5 = new JTextField();
		productPrice5.setColumns(10);
		productPrice5.setBounds(99, 74, 61, 26);
		page1product5.add(productPrice5);
		
		JLabel product1title_1_4 = new JLabel("$");
		product1title_1_4.setBounds(88, 79, 13, 16);
		page1product5.add(product1title_1_4);
		
		page1product3 = new JPanel();
		page1product3.setBounds(444, 17, 166, 106);
		first_page_panel.add(page1product3);
		page1product3.setLayout(null);
		
		product3title = new JLabel("New label");
		product3title.setBounds(6, 6, 61, 16);
		page1product3.add(product3title);
		
		productPrice3 = new JTextField();
		productPrice3.setColumns(10);
		productPrice3.setBounds(99, 74, 61, 26);
		page1product3.add(productPrice3);
		
		JLabel product1title_1_2 = new JLabel("$");
		product1title_1_2.setBounds(99, 79, 13, 16);
		page1product3.add(product1title_1_2);
		
		page1product6 = new JPanel();
		page1product6.setBounds(444, 143, 166, 106);
		first_page_panel.add(page1product6);
		page1product6.setLayout(null);
		
		product6title = new JLabel("New label");
		product6title.setBounds(6, 6, 61, 16);
		page1product6.add(product6title);
		
		productPrice6 = new JTextField();
		productPrice6.setColumns(10);
		productPrice6.setBounds(99, 74, 61, 26);
		page1product6.add(productPrice6);
		
		JLabel product1title_1_5 = new JLabel("$");
		product1title_1_5.setBounds(92, 79, 13, 16);
		page1product6.add(product1title_1_5);
		
		///////////////////////////////////////////
		
		second_page_panel = new JPanel();
		second_page_panel.setBounds(0, 0, 639, 281);
		first_page_panel.add(second_page_panel);
		second_page_panel.setLayout(null);
		second_page_panel.setBackground(new Color(173, 216, 230));
		second_page_panel.setLayout(null);
		
//		JPanel page1product1_1 = new JPanel();
//		page1product1_1.setBounds(29, 17, 166, 106);
//		second_page_panel.add(page1product1_1);
		
		page2product4 = new JPanel();
		page2product4.setBounds(29, 143, 166, 106);
		second_page_panel.add(page2product4);
		
		page2product2 = new JPanel();
		page2product2.setBounds(238, 17, 166, 106);
		second_page_panel.add(page2product2);
		
		page2product5 = new JPanel();
		page2product5.setBounds(238, 143, 166, 106);
		second_page_panel.add(page2product5);
		
		page2product3 = new JPanel();
		page2product3.setBounds(444, 17, 166, 106);
		second_page_panel.add(page2product3);
		
		page2product6 = new JPanel();
		page2product6.setBounds(444, 143, 166, 106);
		second_page_panel.add(page2product6);
		
				
		
		
		///////////////////////////////////////////////
//		JPanel second_page_panel_1 = new JPanel();
//		second_page_panel_1.setBorder(new TitledBorder(null, "Products", TitledBorder.LEADING, TitledBorder.TOP, null, null));
//		second_page_panel_1.setLayout(null);
//		second_page_panel_1.setBackground(new Color(173, 216, 230));
//		second_page_panel_1.setBounds(0, 0, 639, 281);
//		second_page_panel.add(second_page_panel_1);
//		
//		JPanel page1product1_1 = new JPanel();
//		page1product1_1.setBounds(29, 17, 166, 106);
//		first_page_panel_1.add(page1product1_1);
//		
//		JPanel page1product4_1 = new JPanel();
//		page1product4_1.setBounds(29, 143, 166, 106);
//		first_page_panel_1.add(page1product4_1);
//		
//		JPanel page1product2_1 = new JPanel();
//		page1product2_1.setBounds(238, 17, 166, 106);
//		first_page_panel_1.add(page1product2_1);
//		
//		JPanel page1product5_1 = new JPanel();
//		page1product5_1.setBounds(238, 143, 166, 106);
//		first_page_panel_1.add(page1product5_1);
//		
//		JPanel page1product3_1 = new JPanel();
//		page1product3_1.setBounds(444, 17, 166, 106);
//		first_page_panel_1.add(page1product3_1);
//		
//		JPanel page1product6_1 = new JPanel();
//		page1product6_1.setBounds(444, 143, 166, 106);
//		first_page_panel_1.add(page1product6_1);
//		
		
		///////////////////////////////////////////////
		
		nextButton = new JButton("Next");
		nextButton.setBounds(582, 316, 117, 29);
		getContentPane().add(nextButton);
		nextButton.setForeground(new Color(0, 0, 0));
		nextButton.setBackground(new Color(0, 0, 0));
		nextButton.addActionListener(this);
		
		prevButton = new JButton("Previous");
		prevButton.setBounds(53, 316, 117, 29);
		getContentPane().add(prevButton);
		prevButton.setForeground(new Color(0, 0, 0));
		prevButton.setBackground(new Color(0, 0, 0));
		prevButton.addActionListener(this);
		
		lblNewLabel = new JLabel("Shopping");
		lblNewLabel.setFont(new Font("Lucida Grande", Font.BOLD, 15));
		lblNewLabel.setBounds(324, 6, 171, 16);
		getContentPane().add(lblNewLabel);
		
	}

	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == nextButton) {
			first_page_panel.setVisible(false);
			second_page_panel.setVisible(true);
			prevButton.setVisible(true);
		} else if (e.getSource() == prevButton) {
			first_page_panel.setVisible(true);
			second_page_panel.setVisible(false);
			prevButton.setVisible(false);
		} 
		
	}
	
	private void addProducts() {
		int x = productController.size();
		ArrayList<Double> priceList = new ArrayList<>();
		ArrayList<String> nameList = new ArrayList<>();
		ArrayList<String> descriptionList = new ArrayList<>();
		
		for (int i = 0; i < productController.size(); i++) {
			double price = productController.get(i).getPrice();
			String title = productController.get(i).getName();
			String description = productController.get(i).getDescription();
			priceList.add(price);
			nameList.add(title);
			descriptionList.add(description);
		}
		
		String strPrice = String.valueOf(priceList.get(0));
		productPrice1.setText(strPrice);
		product1title.setText(nameList.get(0));
		
		
		
		
	}
}