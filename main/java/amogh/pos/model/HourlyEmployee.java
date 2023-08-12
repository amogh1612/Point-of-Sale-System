package amogh.pos.model;

public class HourlyEmployee extends Employee{

	//instance variables
	private double hours;
	private double hourlyWage;
	
	//Constructor
	public HourlyEmployee(int id, String name, String email, String address, String role, double hours, double hourlyWage) {
		super(id, name, email, address, role);
		this.hours = hours;
		this.hourlyWage = hourlyWage;
	}

	//Getters and Setters for Hours
	public double getHours(){
		return hours;
	}
	
	public double setHours(double hours) {
		return this.hours = hours;
	}
	
	//Getters and Setters for Hourlywage
	public double getHourlyWage(){
		return hourlyWage;
	}
		
	public double setHourlyWage(double hourlyWage) {
		return this.hourlyWage = hourlyWage;
	}
	
	
	public double calculateSalary() {
		return hours * hourlyWage;
	}

	@Override
	public String toString() {
		return "Hourly,"+ super.toString() + "," + hours + "," + hourlyWage;
	}

	@Override
	public String[] getTableRowData() {
		
		return new String[] {this.getName(), this.getEmail(), this.getAddress(),
				this.getRole(), "Hourly Employee", String.valueOf(hourlyWage),
				String.valueOf(hours)};
		
	}
	
	
	
}
