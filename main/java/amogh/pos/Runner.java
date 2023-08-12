package amogh.pos;

import java.io.File;

import javax.swing.UIManager;

import amogh.pos.database.Database;
import amogh.pos.model.Customer;
import amogh.pos.model.Product;
import amogh.pos.view.AdminDashboardView;
import amogh.pos.view.AdminLoginView;
import amogh.pos.view.AdminStaffManagementView;
import amogh.pos.view.ProductPanel;
import amogh.pos.view.ShoppingFrame;

public class Runner {

	public static void main(String[] args) {
		
		try {
			UIManager.setLookAndFeel(
			        UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
		
		/*
		EmployeeController controller = new EmployeeController();
		controller.add(new SalariedEmployee(1, "John",
				"john@mail.com", "USA", "CEO", 2400));
		try {
			EmployeeDatabase.write(controller);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Written");
		*/
		
		
		
		
	/*
		CustomerController customerController = new CustomerController();
		// int id, String name, String email, String address, int orderID
		Customer amogh1 = new RegularCustomer(9, "Amogh", "Amogh@mail.com", "USA", 3);
		Customer amogh2 = new MemberCustomer(10, "Second Amogh", "sa@mail.com", "UK", 23, 78, LocalDate.now());
		customerController.add(amogh1);
		customerController.add(amogh2);
		try {
			CustomerDatabase.write(customerController);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("Written");
		*/
		
		/*
		
		File file = new File(Product.PRODUCT_PICTURES_PATH);
		if (!file.exists()) {
			file.mkdirs();
		}
		
		//CustomerManagementView customerView = new CustomerManagementView();
		//customerView.setVisible(true);
		
		

		
		
//		
//		
//	
		
		
		
		*/
		
		//ProductPanel productView = new ProductPanel();
		//productView.setVisible(true);
		
		//ShoppingFrame frame = new ShoppingFrame();
		//frame.setVisible(true);
		
		
		AdminLoginView view = new AdminLoginView();
		view.setVisible(true);
		
		
		
		
	}
	
//	
}