package amogh.pos.model;

public abstract class Employee extends Person implements SalaryCalculator {

	//instance variables
	private String role;
	
	//constructor
	public Employee (int id, String name, String email, String address, String role){
		super(id, name, email, address);
		this.setRole(role);
	}
	
	public abstract String[] getTableRowData();

	//Getters and Setters for Role
	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return super.toString() + "," + role;
	}
	
	

}
