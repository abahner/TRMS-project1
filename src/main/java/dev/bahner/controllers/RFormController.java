package dev.bahner.controllers;

import java.util.List;

import com.google.gson.Gson;

import dev.bahner.models.ReimburseForm;
import dev.bahner.services.RFormService;
import io.javalin.http.Handler;

public class RFormController {

	RFormService rs;
	Gson gson = new Gson();
	
	public RFormController(RFormService rs) {
		this.rs = rs;
	}
	
	public Handler getAllRForms = (ctx) -> {
		List<ReimburseForm> rforms = rs.getAllRForms();
		ctx.result(gson.toJson(rforms));
	};

	public Handler getRFormById = (ctx) -> {
		String input = ctx.pathParam("id");
		int id;
		try {
			id = Integer.parseInt(input);
		} catch (NumberFormatException e) {
			id = -1;
		}
		ReimburseForm r = rs.getRForm(id);

		if (r != null) {
			ctx.result(gson.toJson(r));
		} else {
			ctx.status(400);
		}
	};

	public Handler addRForm = (ctx) -> {
		ReimburseForm r = gson.fromJson(ctx.body(), ReimburseForm.class);
		r = rs.addRForm(r);
		ctx.result((r != null) ? gson.toJson(r) : "{}");
	};

	public Handler updateRForm = (ctx) -> {
		ReimburseForm r = gson.fromJson(ctx.body(), ReimburseForm.class);
		r = rs.updateRForm(r);
		ctx.result((r != null) ? gson.toJson(r) : "{}");
	};

	public Handler deleteRForm = (ctx) -> {
		String input = ctx.pathParam("id");
		int id;
		try {
			id = Integer.parseInt(input);
		} catch (NumberFormatException e) {
			id = -1;
		}
		ReimburseForm r = rs.deleteRForm(id);
		ctx.result((r != null) ? gson.toJson(r) : "{}");
	};
	
	
	
}
