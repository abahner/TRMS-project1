package dev.bahner.services;

import java.util.List;

import dev.bahner.models.Course;
import dev.bahner.repos.CourseRepo;

public class CourseServiceImpl implements CourseService {

	public CourseRepo cr;
	
	public CourseServiceImpl(CourseRepo cr) {
		this.cr = cr;
	}
	
	@Override
	public Course getCourse(int id) {
		return cr.getCourse(id);
	}

	@Override
	public Course getCourse(String name) {
		return cr.getCourse(name);
	}

	@Override
	public List<Course> getAllCourses() {
		return cr.getAllCourses();
	}

	@Override
	public Course addCourse(Course c) {
		return cr.addCourse(c);
	}

	@Override
	public Course updateCourse(Course change) {
		return cr.updateCourse(change);
	}

	@Override
	public Course deleteCourse(int id) {
		return cr.deleteCourse(id);
	}

}
