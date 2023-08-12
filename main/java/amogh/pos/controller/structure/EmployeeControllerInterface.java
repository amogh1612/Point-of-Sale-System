package amogh.pos.controller.structure;

import java.util.List;

import amogh.pos.model.Employee;

public interface EmployeeControllerInterface {

	boolean addEmployee(Employee employee);
	
	Employee deleteEmployee(int index);
	
	boolean deleteEmployee(Employee employee);
	
	void updateEmployee(Employee employee);
	void setEmployee(int index, Employee employee);
	
	Employee getEmployee(int index);
	
	List<Employee> getAllEmployees();
	
	int size();
	
}
