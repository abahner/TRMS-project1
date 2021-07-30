package dev.bahner.controllers;

import java.util.List;

import com.google.gson.Gson;

import dev.bahner.models.BenefitsCoordinator;
import dev.bahner.services.BenCoordService;
import io.javalin.http.Context;
import io.javalin.http.Handler;

public class BenCoordController {

	BenCoordService bs;
	Gson gson = new Gson();
	
	public BenCoordController(BenCoordService bs) {
		this.bs = bs;
	}
	
	public Handler getAllBenCoords = (ctx) -> {
		String name = ctx.queryParam("username", "");
		if (name == "") {
			List<BenefitsCoordinator> benCoords = bs.getAllBenCoords();
			if (benCoords != null) {
				ctx.result(gson.toJson(benCoords));
			} else {
				ctx.result("[]");
			}
		} else {
			getBenCoordByName(ctx);
		}
	};
	
	public Handler getBenCoordById = (ctx) -> {
		String input = ctx.pathParam("id");
		int id;
		try {
			id = Integer.parseInt(input);
		} catch (NumberFormatException e) {
			id = -1;
		}
		BenefitsCoordinator b = bs.getBenCoord(id);

		if (b != null) {
			ctx.result(gson.toJson(b));
		} else {
			ctx.status(400);
		}
	};
	
	public Handler addBenCoord = (ctx) -> {
		BenefitsCoordinator b = gson.fromJson(ctx.body(), BenefitsCoordinator.class); 
		b = bs.addBenCoord(b);
		ctx.result((b != null) ? gson.toJson(b) : "{}");
	};
	
	public Handler updateBenCoord = (ctx) -> {
		BenefitsCoordinator b = gson.fromJson(ctx.body(), BenefitsCoordinator.class);
		b = bs.updateBenCoord(b);
		ctx.result((b != null) ? gson.toJson(b) : "{}");
	};
	
	public Handler deleteBenCoord = (ctx) -> {
		String input = ctx.pathParam("id");
		int id;
		try {
			id = Integer.parseInt(input);
		} catch (NumberFormatException e) {
			id = -1;
		}
		BenefitsCoordinator b = bs.deleteBenCoord(id);
		ctx.result((b != null) ? gson.toJson(b) : "{}");
	};
	
	
	private void getBenCoordByName(Context ctx) {
		String name = ctx.queryParam("username");
		System.out.println(name);
		
		BenefitsCoordinator b = bs.getBenCoord(name);
		
		populateResult(ctx, b);
	}
	
	private void populateResult(Context ctx, BenefitsCoordinator b) {
		
		if (b != null) {
			ctx.result(gson.toJson(b));
		} else {
			ctx.result("{}");
		}
	}
	
}
