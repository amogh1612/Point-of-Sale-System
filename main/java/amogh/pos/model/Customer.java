package amogh.pos.model;

public abstract class Customer extends Person{

	private int orderID;
	
	public Customer(int id, String name, String email, String address, int orderID) {
		super(id, name, email, address);
		this.orderID = orderID;
		
	}

	public int getOrderID() {
		return orderID;
	}

	public void setOrderID(int orderID) {
		this.orderID = orderID;
	}

	public abstract String[] getTableRowData();


	

	

	
}
