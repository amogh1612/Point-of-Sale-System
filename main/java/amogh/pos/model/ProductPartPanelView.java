package amogh.pos.model;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import amogh.pos.view.ShoppingFrame;

public class ProductPartPanelView extends JPanel implements MouseListener {

	private JPanel bottomProductPart;
	private JLabel pictureLabel;
	private JLabel nameLabel;
	private JLabel priceLabel;
	private Product product;
	private ShoppingFrame frame;
	
	public ProductPartPanelView(ShoppingFrame frame) {
		
		this.frame = frame;
		super.setLayout(new BorderLayout());
		super.addMouseListener(this);
		
		pictureLabel = new JLabel();
		
		super.add(pictureLabel, BorderLayout.CENTER);
		
		bottomProductPart = new JPanel();
		bottomProductPart.setLayout(new GridLayout(1, 0, 0, 0));
		
		super.add(bottomProductPart, BorderLayout.SOUTH);
		
		nameLabel = new JLabel();
		nameLabel.setBackground(Color.WHITE);
		priceLabel = new JLabel();
		priceLabel.setBackground(Color.WHITE);
		
		bottomProductPart.add(nameLabel);
		bottomProductPart.add(priceLabel);
		
	}
	
	public Product getProduct() {
		
		return product;
		
	}
	
	public void setupProduct(Product product) {
		
		pictureLabel.setText("");
		if (product != null) {
			this.product = product;
			nameLabel.setText(product.getName());
			priceLabel.setText("$ "+String.format("%.2f", product.getPrice()));
			ImageIcon icon = new ImageIcon(product.getPicturePath());
			putResizedProductPicture(icon);
		} else {
			// null
			this.product = null;
			this.nameLabel.setText("");
			this.priceLabel.setText("");
			pictureLabel.setText("");
			pictureLabel.setIcon(null);
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
		
		
		pictureLabel.setIcon(new ImageIcon(newSizeImage));
		
	}

	public void mousePressed(MouseEvent e) {
		
		if (product != null) {
			frame.addProductToCart(product);
		}
		
	}
	
	// Not Necessary

	public void mouseClicked(MouseEvent e) {
	}

	public void mouseReleased(MouseEvent e) {
	}

	public void mouseEntered(MouseEvent e) {
	}


	public void mouseExited(MouseEvent e) {
	}
	
}
