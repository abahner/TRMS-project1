package dev.bahner.controllers;

import java.util.List;

import com.google.gson.Gson;

import dev.bahner.models.Course;
import dev.bahner.services.CourseService;
import io.javalin.http.Context;
import io.javalin.http.Handler;

public class CourseController {

	CourseService cs;
	Gson gson = new Gson();
	
	public CourseController(CourseService cs) {
		this.cs = cs;
	}
	
	public Handler getAllCourses = (ctx) -> {
		String name = ctx.queryParam("name", "");
		if (name == "") {
			List<Course> courses = cs.getAllCourses();
			if (courses != null) {
				ctx.result(gson.toJson(courses));
			} else {
				ctx.result("[]");
			}
		} else {
			getCourseByName(ctx);
		}
	};
	
	public Handler getCourseById = (ctx) -> {
		String input = ctx.pathParam("id");
		int id;
		try {
			id = Integer.parseInt(input);
		} catch (NumberFormatException e) {
			id = -1;
		}
		Course c = cs.getCourse(id);

		if (c != null) {
			ctx.result(gson.toJson(c));
		} else {
			ctx.status(400);
		}
	};
	
	public Handler addCourse = (ctx) -> {
		Course c = gson.fromJson(ctx.body(), Course.class);
		c = cs.addCourse(c);
		ctx.result((c != null) ? gson.toJson(c) : "{}");
	};

	public Handler updateCourse = (ctx) -> {
		Course c = gson.fromJson(ctx.body(), Course.class);
		c = cs.updateCourse(c);
		ctx.result((c != null) ? gson.toJson(c) : "{}");
	};
	
	public Handler deleteCourse = (ctx) -> {
		String input = ctx.pathParam("id");
		int id;
		try {
			id = Integer.parseInt(input);
		} catch (NumberFormatException e) {
			id = -1;
		}
		Course c = cs.deleteCourse(id);
		ctx.result((c != null) ? gson.toJson(c) : "{}");
	};
	
	private void getCourseByName(Context ctx) {
		String name = ctx.queryParam("name");
		Course c = cs.getCourse(name);

		if (c != null) {
			ctx.result(gson.toJson(c));
		} else {
			ctx.result("{}");
		}
	}
}
