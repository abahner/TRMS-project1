package dev.bahner.controllers;

import java.util.List;

import com.google.gson.Gson;

import dev.bahner.models.Employee;
import dev.bahner.services.EmployeeService;
import io.javalin.http.Context;
import io.javalin.http.Handler;

public class EmployeeController {

	EmployeeService es;
	Gson gson = new Gson();

	public EmployeeController(EmployeeService es) {
		this.es = es;
	}

	public Handler getAllEmployees = (ctx) -> {
		String name = ctx.queryParam("username", "");
		if (name == "") {
			List<Employee> employees = es.getAllEmployees();
			if (employees != null) {
				ctx.result(gson.toJson(employees));
			} else {
				ctx.result("[]");
			}
		} else {
			getEmployeeByName(ctx);
		}

	};

	public Handler getEmployeeById = (ctx) -> {
		String input = ctx.pathParam("id");
		int id;
		try {
			id = Integer.parseInt(input);
		} catch (NumberFormatException e) {
			id = -1;
		}
		Employee e = es.getEmployee(id);

		populateResult(ctx, e);
	};

	public Handler addEmployee = (ctx) -> {
		Employee e = gson.fromJson(ctx.body(), Employee.class);
		e = es.addEmployee(e);
		populateResult(ctx, e);
	};

	public Handler updateEmployee = (ctx) -> {
		Employee e = gson.fromJson(ctx.body(), Employee.class);
		e = es.updateEmployee(e);
		populateResult(ctx, e);
	};

	public Handler deleteEmployee = (ctx) -> {
		String input = ctx.pathParam("id");
		int id;
		try {
			id = Integer.parseInt(input);
		} catch (NumberFormatException e) {
			id = -1;
		}
		Employee e = es.deleteEmployee(id);
		ctx.result((e != null) ? gson.toJson(e) : "{}");
	};

	private void getEmployeeByName(Context ctx) {
		String name = ctx.queryParam("username");
		System.out.println(name);
		
		Employee e = es.getEmployee(name);
		
		populateResult(ctx, e);
	};
	
	private void populateResult(Context ctx, Employee e) {
		if (e != null ) {
			ctx.result(gson.toJson(e));
		} else {
			ctx.result("{}");
		}
	}
	

}
