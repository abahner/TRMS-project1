package dev.bahner.services;

import java.util.List;

import dev.bahner.models.Employee;
import dev.bahner.repos.EmployeeRepo;

public class EmployeeServiceImpl implements EmployeeService {

	public EmployeeRepo er;

	public EmployeeServiceImpl(EmployeeRepo er) {
		this.er = er;
	}

	public Employee getEmployee(int id) {
		return er.getEmployee(id);
	}

	public Employee getEmployee(String name) {
		return er.getEmployee(name);
	}

	public List<Employee> getAllEmployees() {
		return er.getAllEmployees();
	}

	public Employee addEmployee(Employee e) {
		return er.addEmployee(e);
	}

	public Employee updateEmployee(Employee change) {
		return er.updateEmployee(change);
	}

	public Employee deleteEmployee(int id) {
		return er.deleteEmployee(id);
	}

}
