package dev.bahner.models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity
@Table(name = "employees")
public class Employee {

	@Id
	@Column(name = "e_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "username")
	private String username;

	@Column(name = "password")
	private String password;

	@Column(name = "email", unique = true)
	private String email;

	@Column(name = "role")
	private String role;
	
	@Column(name = "reimbursed_amt")
	private int reimbursedAmt;
	
	@ManyToMany 	
	@LazyCollection (LazyCollectionOption.FALSE)
	@JoinTable(
			name = "employees_courses", 
			joinColumns = @JoinColumn(name = "e_id"),
			inverseJoinColumns = @JoinColumn(name = "c_id")
			)
	private List<Course> courses; 
	
	public Employee() {
		super();
	}

	public Employee(String username, String password, String email, String role, int reimbursedAmt) {
		super();
		this.username = username;
		this.password = password;
		this.email = email;
		this.role = role;
		this.reimbursedAmt = reimbursedAmt;
	}

	public Employee(int id, String username, String password, String email, String role, int reimbursedAmt) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.email = email;
		this.role = role;
		this.reimbursedAmt = reimbursedAmt;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public int getReimbursedAmt() {
		return reimbursedAmt;
	}

	public void setReimbursedAmt(int reimbursedAmt) {
		this.reimbursedAmt = reimbursedAmt;
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", username=" + username + ", password=" + password + ", email=" + email
				+ ", role=" + role + ", reimbursed_amt=" + reimbursedAmt + "]";
	}

}
