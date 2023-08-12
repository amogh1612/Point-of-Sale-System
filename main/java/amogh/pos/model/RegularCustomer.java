package amogh.pos.model;

public class RegularCustomer extends Customer{

	public RegularCustomer(int id, String name, String email, String address, int orderID) {
		super(id, name, email, address, orderID);
	}

	@Override
	public String[] getTableRowData() {
		return new String[] {getName(), getEmail(), getAddress(), String.valueOf(getOrderID()), "", ""};
	}

}
