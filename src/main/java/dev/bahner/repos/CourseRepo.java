package dev.bahner.repos;

import java.util.List;

import dev.bahner.models.Course;

public interface CourseRepo {

	public Course getCourse(int id);
	
	public Course getCourse(String name);
	
	public List<Course> getAllCourses();
	
	public Course addCourse(Course c);
	
	public Course updateCourse(Course change);
	
	public Course deleteCourse(int id);
	
}
