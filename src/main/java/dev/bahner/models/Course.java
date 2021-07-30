package dev.bahner.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "courses")
public class Course {

	@Id
	@Column(name = "c_id", updatable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "course_type")
	private String courseType;
	
	@Column(name = "grade")
	private double grade;
	
	
	public Course(int id, String courseType, double grade) {
		super();
		this.id = id;
		this.courseType = courseType;
		this.grade = grade;
	}

	public Course(String courseType, double grade) {
		super();
		this.courseType = courseType;
		this.grade = grade;
	}

	public Course() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCourseType() {
		return courseType;
	}

	public void setCourseType(String courseType) {
		this.courseType = courseType;
	}

	public double getGrade() {
		return grade;
	}

	public void setGrade(double grade) {
		this.grade = grade;
	}

	@Override
	public String toString() {
		return "Course [id=" + id + ", courseType=" + courseType + ", grade=" + grade + "]";
	}
	
}
