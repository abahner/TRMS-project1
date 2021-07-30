package dev.bahner.services;

import java.util.List;

import dev.bahner.models.Employee;

public interface EmployeeService {

	public Employee getEmployee(int id);

	public Employee getEmployee(String name);

	public List<Employee> getAllEmployees();

	public Employee addEmployee(Employee e);

	public Employee updateEmployee(Employee change);

	public Employee deleteEmployee(int id);

}
