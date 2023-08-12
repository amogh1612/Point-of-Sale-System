package amogh.pos.model;

public class SalariedEmployee extends Employee{

	// Salary will be calculated by montlhly pay and how many months worked
	private double monthlySalary;
	
	// constructor
	public SalariedEmployee(int id, String name, String email, String address, String role, double monthlySalary) {
		super(id, name, email, address, role);
		this.monthlySalary = monthlySalary;
	}

	// Getters and Setters for Monthly Salary
	public double getMonthlySalary() {
		return monthlySalary;
	}

	public void setMonthlySalary(double monthlySalary) {
		this.monthlySalary = monthlySalary;
	}
	
	// salary calculator
	public double calculateSalary() {
		return monthlySalary;
	}

	@Override
	public String toString() {
		return "Salaried,"+ super.toString() + "," + monthlySalary;
	}

	@Override
	public String[] getTableRowData() {
		
		return new String[] {this.getName(), this.getEmail(), this.getAddress(),
				this.getRole(), "Salaried Employee", String.valueOf(monthlySalary), ""};
		
	}
	
	
	
}
