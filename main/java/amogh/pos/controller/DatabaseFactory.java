package amogh.pos.controller;

import amogh.pos.database.Database;
import amogh.pos.model.Customer;
import amogh.pos.model.Employee;
import amogh.pos.model.Product;

public class DatabaseFactory {

	// CONSTANTS.
	private static final String EMPLOYEE_FILE = "data/employee.dat";
	private static final String ORDER_FILE = "data/order.dat";
	private static final String CUSTOMER_FILE = "data/customer.dat";
	private static final String PRODUCT_FILE = "data/product.dat";
	
	
	// STATIC METHODS.
	// Factory.
	public static Database<Controller<Employee>> getEmployeeDatabase(){
		
		return new Database<Controller<Employee>>(EMPLOYEE_FILE);
		
	}
	
	public static Database<Controller<Customer>> getCustomerDatabase(){
		
		return new Database<Controller<Customer>>(CUSTOMER_FILE);
		
	}
	
	public static Database<Controller<Product>> getProductDatabase(){
		
		return new Database<Controller<Product>>(PRODUCT_FILE);
		
	}
	
}
