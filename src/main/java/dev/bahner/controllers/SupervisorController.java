package dev.bahner.controllers;

import java.util.List;

import com.google.gson.Gson;

import dev.bahner.models.Supervisor;
import dev.bahner.services.SupervisorService;
import io.javalin.http.Context;
import io.javalin.http.Handler;

public class SupervisorController {

	SupervisorService ss;
	Gson gson = new Gson();

	public SupervisorController(SupervisorService ss) {
		this.ss = ss;
	}

	public Handler getAllSupervisors = (ctx) -> {
		
		String name = ctx.queryParam("username","");
		if (name == "") {
			List<Supervisor> supervisors = ss.getAllSupervisors();
			if (supervisors != null) {
				ctx.result(gson.toJson(supervisors));
			}else {
				ctx.result("[]");
			}
		}else {
			getSupervisorByName(ctx);
		}
		
	};

	public Handler getSupervisorById = (ctx) -> {
		String input = ctx.pathParam("id");
		int id;
		try {
			id = Integer.parseInt(input);
		} catch (NumberFormatException e) {
			id = -1;
		}
		Supervisor s = ss.getSupervisor(id);

		if (s != null) {
			ctx.result(gson.toJson(s));
		} else {
			ctx.status(400);
		}
	};

	public Handler addSupervisor = (ctx) -> {
		Supervisor s = gson.fromJson(ctx.body(), Supervisor.class);
		s = ss.addSupervisor(s);
		ctx.result((s != null) ? gson.toJson(s) : "{}");
	};

	public Handler updateSupervisor = (ctx) -> {
		Supervisor s = gson.fromJson(ctx.body(), Supervisor.class);
		s = ss.updateSupervisor(s);
		ctx.result((s != null) ? gson.toJson(s) : "{}");
	};

	public Handler deleteSupervisor = (ctx) -> {
		String input = ctx.pathParam("id");
		int id;
		try {
			id = Integer.parseInt(input);
		} catch (NumberFormatException e) {
			id = -1;
		}
		Supervisor s = ss.deleteSupervisor(id);
		ctx.result((s != null) ? gson.toJson(s) : "{}");
	};

	
	private void getSupervisorByName(Context ctx) {
		String name = ctx.queryParam("username");
		System.out.println(name);
		
		Supervisor s = ss.getSupervisor(name);
		
		populateResult(ctx, s);
	};
	
	private void populateResult(Context ctx, Supervisor s) {
		if (s != null ) {
			ctx.result(gson.toJson(s));
		} else {
			ctx.result("{}");
		}
	}
}
