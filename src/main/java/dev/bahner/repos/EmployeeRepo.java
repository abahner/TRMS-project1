package dev.bahner.repos;

import java.util.List;

import dev.bahner.models.Employee;

public interface EmployeeRepo {

	public Employee getEmployee(int id);

	public Employee getEmployee(String name);

	public List<Employee> getAllEmployees();

	public Employee addEmployee(Employee e);

	public Employee updateEmployee(Employee change);

	public Employee deleteEmployee(int id);

}
