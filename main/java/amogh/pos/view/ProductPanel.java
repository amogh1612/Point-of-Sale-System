package amogh.pos.view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.filechooser.FileNameExtensionFilter;

import amogh.pos.controller.Controller;
import amogh.pos.controller.DatabaseFactory;
import amogh.pos.model.Product;

public class ProductPanel extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JTextField nameTextField;
	private JTextField descriptionTextField;
	private JTextField priceTextField;
	private JTextField quantityTextField;
	private JTextField SKUfield;
	private JLabel productPictureLabel;
	private JTextField imagePathField;
	private JButton selectBTN;
	private JButton btnAddQty;
	private JTable table;
	private JScrollPane productScrollPane;
	private Controller<Product> productController;
	private JButton btnAddProduct;
	private JButton btnView;
	private JButton dashboardBTN;
	private JTextField searchField;
	private Controller<Product> filteredProductController;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		try {
			UIManager.setLookAndFeel(
			        UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ProductPanel frame = new ProductPanel();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ProductPanel() {
		setTitle("Products");
		
		
		productController = DatabaseFactory.getProductDatabase().read();
		if (productController == null) {
			productController = new Controller<Product>();
		}
		
		if (filteredProductController == null) {
			filteredProductController = new Controller<Product>();
		}
		
		addComponents();
		
		populateProductsTable();
		
		searchField.getDocument().addDocumentListener(new DocumentListener() {

	
			public void insertUpdate(DocumentEvent e) {
				populateProductsTable();
			}


			public void removeUpdate(DocumentEvent e) {
				populateProductsTable();
			}


			public void changedUpdate(DocumentEvent e) {
				populateProductsTable();
			}
			
		});
	}


	public void actionPerformed(ActionEvent e) {
		
		if (e.getSource() == btnAddQty) {
			
			
			Product product = filteredProductController.get(table.getSelectedRow());
			//Product product = productController.get(table.getSelectedRow());

			
			String response = JOptionPane.showInputDialog("Enter Quantity to add: ");
			
			
			
			try {
				int quantity = Integer.parseInt(response);
				product.setQuantity(quantity);
				
				JOptionPane.showMessageDialog(this, "Quantity is added.");
			} catch (NumberFormatException nfe) {
				JOptionPane.showMessageDialog(this, "Quantity should be valid integer.");
			}
			
		} else if (e.getSource() == btnView){
			
			
			int index = table.getSelectedRow();
			Product product = filteredProductController.get(index);
			String skuID = product.getSKUId();
			//String sKUId, String picturePath, String name, String description, double price, int quantity 
			String description = product.getDescription();
			String name = product.getName();
			double price = product.getPrice();
			int quantity = product.getQuantity();
			String picPath = product.getPicturePath();
			
			String quantityString = String.valueOf(quantity);
			String priceString = String.valueOf(price);
			
			SKUfield.setText(skuID);
			nameTextField.setText(name);
			quantityTextField.setText(quantityString);
			priceTextField.setText(priceString);
			descriptionTextField.setText(description);
			imagePathField.setText(picPath);
			
			putResizedProductPicture(product.readProductImage());
			
			
			
			
		} else if (e.getSource() == btnAddProduct){
			System.out.println("test");
			
			//String sKUId, String picturePath, String name, String description, double price, int quantity 
			String skuID = SKUfield.getText();
			String description = descriptionTextField.getText();
			String name = nameTextField.getText();
			String strPrice = priceTextField.getText();
			String strQuantity = quantityTextField.getText();
			String picPath = imagePathField.getText();
			
			double doubleValuePrice = Double.parseDouble(strPrice);
			int intValueQuantity = Integer.parseInt(strQuantity);			
			
			productController.add(new Product (skuID, picPath, name, description, doubleValuePrice, intValueQuantity));
			
			try {
				DatabaseFactory.getProductDatabase().write(productController);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			
			populateProductsTable();
			
			
			
		} else if (e.getSource() == dashboardBTN) {
			dispose();
			AdminDashboardView dash = new AdminDashboardView();
			dash.setVisible(true);
		} else {
			JFileChooser fileChooser = new JFileChooser();
			
			// bmp
			FileNameExtensionFilter imageFilter = new FileNameExtensionFilter("Image Files Only", 
					ImageIO.getReaderFileSuffixes());
			fileChooser.setFileFilter(imageFilter);
			fileChooser.setDialogTitle("Select Product Image");
			
			int result = fileChooser.showOpenDialog(this);
			if (result == JFileChooser.APPROVE_OPTION) {
				
				File pictureFile = fileChooser.getSelectedFile();
				imagePathField.setText(pictureFile.getAbsolutePath());
				
				ImageIcon icon = new ImageIcon(pictureFile.getAbsolutePath());
				putResizedProductPicture(icon);
				
			}
		}
		
		
		

	}
	
	private void putResizedProductPicture(ImageIcon icon) {
		
		// 1024x768
		Image image = icon.getImage();
		
		// 10 -> 1
		
		// 100x100
		BufferedImage newSizeImage = 
				new BufferedImage(100, 100, BufferedImage.TYPE_INT_ARGB);
		Graphics2D graphics = newSizeImage.createGraphics();
		
		graphics.setRenderingHint(RenderingHints.KEY_INTERPOLATION, 
				RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		
		graphics.drawImage(image, 0, 0, 100, 100, null);
		graphics.dispose();
		
		
		productPictureLabel.setIcon(new ImageIcon(newSizeImage));
		
	}

	private void populateProductsTable() {
		
		this.filteredProductController.clear();
		
		try {
			getContentPane().remove(productScrollPane);
		} catch (Exception e) {}
		
		// int id, String name, String email, String address, int orderID
		String[] columnNames = {"SKU", "Name", "Description", "Price", "Quantity"};
		
		ArrayList<String[]> dataList = new ArrayList<>();
		//ArrayList<String[]> filerDataList = new ArrayList<>();
		String search = searchField.getText();
		
		// 
		
		for (int i = 0; i < productController.size(); i++) {
			
			if (productController.get(i).searchExists(search)) {
				filteredProductController.add(productController.get(i));
				dataList.add((productController.get(i)).getTableRowData());
			}
			
		}
		
		// iterate through filtered controller
//			// data list.
//		for (int i = 0; i < filteredProductController.size(); i++) {
//			if (productController.get(i).searchExists(search)) {
//				dataList.add(filteredProductController.get(i).getTableRowData());
//			}
//		}
		

		
		// 2 rows, 5 columns
		String[][] data = dataList.toArray(new String[][] {});
		
		table = new JTable(data, columnNames);
		table.getTableHeader().setFont(new Font("Ariel", Font.BOLD, 16));
		productScrollPane = new JScrollPane(table);
		table.setBounds(10, 276, 713, 207);
		productScrollPane.setBounds(10, 276, 713, 207);
		
		getContentPane().add(productScrollPane);
		
		
		
	}

	private void addComponents() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 747, 566);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		nameTextField = new JTextField();
		nameTextField.setBounds(98, 38, 170, 20);
		contentPane.add(nameTextField);
		nameTextField.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Name");
		lblNewLabel.setBounds(27, 41, 46, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblDescription = new JLabel("Description");
		lblDescription.setBounds(278, 41, 84, 14);
		contentPane.add(lblDescription);
		
		descriptionTextField = new JTextField();
		descriptionTextField.setColumns(10);
		descriptionTextField.setBounds(387, 38, 334, 20);
		contentPane.add(descriptionTextField);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(176, 224, 230));
		panel.setBorder(new TitledBorder(null, "Product Picture", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(272, 66, 449, 141);
		contentPane.add(panel);
		panel.setLayout(null);
		
		productPictureLabel = new JLabel("");
		productPictureLabel.setBorder(new LineBorder(new Color(0, 0, 0)));
		productPictureLabel.setBounds(10, 25, 100, 100);
		panel.add(productPictureLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Select Image:");
		lblNewLabel_1.setBounds(121, 26, 318, 36);
		panel.add(lblNewLabel_1);
		
		imagePathField = new JTextField();
		imagePathField.setEditable(false);
		imagePathField.setBounds(120, 60, 318, 29);
		panel.add(imagePathField);
		imagePathField.setColumns(10);
		
		selectBTN = new JButton("Select");
		selectBTN.setBounds(120, 100, 319, 23);
		selectBTN.addActionListener(this);
		panel.add(selectBTN);
		
		priceTextField = new JTextField();
		priceTextField.setColumns(10);
		priceTextField.setBounds(98, 80, 170, 20);
		contentPane.add(priceTextField);
		
		JLabel lblPrice = new JLabel("Price");
		lblPrice.setBounds(27, 83, 46, 14);
		contentPane.add(lblPrice);
		
		quantityTextField = new JTextField();
		quantityTextField.setColumns(10);
		quantityTextField.setBounds(98, 123, 170, 20);
		contentPane.add(quantityTextField);
		
		JLabel lblQuantity = new JLabel("Quantity");
		lblQuantity.setBounds(27, 126, 75, 14);
		contentPane.add(lblQuantity);
		
		SKUfield = new JTextField();
		SKUfield.setColumns(10);
		SKUfield.setBounds(98, 175, 170, 20);
		contentPane.add(SKUfield);
		
		JLabel lblNewLabel_4 = new JLabel("SKU ID");
		lblNewLabel_4.setBounds(27, 178, 46, 14);
		contentPane.add(lblNewLabel_4);
		
		btnAddQty = new JButton("Add Qty");
		btnAddQty.addActionListener(this);
		btnAddQty.setBounds(10, 493, 92, 23);
		contentPane.add(btnAddQty);
		
		btnAddProduct = new JButton("Add Product");
		btnAddProduct.setBounds(27, 210, 319, 23);
		btnAddProduct.addActionListener(this);
		contentPane.add(btnAddProduct);
		
		btnView = new JButton("View");
		btnView.addActionListener(this);
		btnView.setBounds(98, 493, 139, 23);
		contentPane.add(btnView);
		
		searchField = new JTextField();
		searchField.setBounds(98, 247, 623, 19);
		contentPane.add(searchField);
		searchField.setColumns(10);
		
		JLabel lblNewLabel_44 = new JLabel("Search:");
		lblNewLabel_44.setBounds(27, 250, 46, 14);
		contentPane.add(lblNewLabel_44);
		
		dashboardBTN = new JButton("Dashboard");
		dashboardBTN.setBounds(604, 490, 117, 29);
		contentPane.add(dashboardBTN);
		dashboardBTN.addActionListener(this);
		
	}
	
}





