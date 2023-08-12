package amogh.pos.model;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;

import javax.swing.ImageIcon;

public class Product implements Serializable {

	// static constant.
	public static final String PRODUCT_PICTURES_PATH = "data/products/pictures";
	
	private String SKUId;
	// Link Picture..
	private String picturePath;
	
	private String name;
	private String description;
	private double price;
	private int quantity;

	public Product(Product originalProduct) {
		
		this.SKUId = originalProduct.getSKUId();
		
		this.picturePath = originalProduct.getSKUId();
		this.name = originalProduct.getName();
		this.description = originalProduct.getDescription();
		this.price = originalProduct.getPrice();
		this.quantity = originalProduct.getQuantity();
		
	}
	
	public Product(String sKUId, String picturePath, String name, String description, double price, int quantity) {
		
		SKUId = sKUId;
		this.picturePath = picturePath;
		this.name = name;
		this.description = description;
		this.price = price;
		this.quantity = quantity;
		
		if (!picturePath.trim().isEmpty()) {
			// We have some picture.
			
			String extension = picturePath.substring(picturePath.lastIndexOf('.'), picturePath.length());
			
			File sourceFile = new File(picturePath);
			
			
			this.picturePath = PRODUCT_PICTURES_PATH + "/" + SKUId + extension;
			
			
			File destinationFile = new File(this.picturePath);
			
			System.out.println("Test Source: " + sourceFile.getAbsolutePath());
			System.out.println("Test Destination: " + destinationFile.getAbsolutePath());
			
			try {
				Files.copy(sourceFile.toPath(), destinationFile.toPath());
			} catch (IOException e) {
				e.printStackTrace();
			}
			System.out.println("Copy Completed.");
			
		}
		
	}
	
	public ImageIcon readProductImage() {
		
		return new ImageIcon(picturePath);
		
	}
	
	public boolean searchExists(String text) {
		
		String completeProduct = SKUId + " " + name + " " + description + " " +
				price + " " + quantity;
		completeProduct = completeProduct.toLowerCase().trim();
		
		text = text.trim().toLowerCase();
		
		return completeProduct.contains(text);
		
	}

	public String getSKUId() {
		return SKUId;
	}

	public void setSKUId(String sKUId) {
		SKUId = sKUId;
	}

	public String getPicturePath() {
		return picturePath;
	}

	public void setPicturePath(String picturePath) {
		this.picturePath = picturePath;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public static String getProductPicturesPath() {
		return PRODUCT_PICTURES_PATH;
	}

	@Override
	public String toString() {
		return "Product [SKUId=" + SKUId + ", picturePath=" + picturePath + ", name=" + name + ", description="
				+ description + ", price=" + price + ", quantity=" + quantity + "]";
	}

	public String[] getTableRowData() {
		return new String[] {getSKUId(), getName(), getDescription(), 
				String.valueOf(price), String.valueOf(quantity)};
	}

	public String[] getCartTableRowData() {
		return new String[] {getSKUId(), getName(), String.valueOf(quantity)};
	}

}
