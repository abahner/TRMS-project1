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
@Table(name = "supervisors")
public class Supervisor {

	@Id
	@Column(name = "s_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "username")
	private String username;

	@Column(name = "password")
	private String password;

	@Column(name = "email", unique = true)
	private String email;
	
	@Column(name = "dep_head", columnDefinition = "boolean")
	private boolean depHead;
	
	@ManyToMany
	@LazyCollection (LazyCollectionOption.FALSE)
	@JoinTable(
			name = "supervisors_employees", 
			joinColumns = @JoinColumn(name = "s_id"),
			inverseJoinColumns = @JoinColumn(name = "e_id")
			)
	private List<Employee> employees;
	
	public Supervisor() {
		super();
	}

	public Supervisor(String username, String password, String email, boolean depHead) {
		super();
		this.username = username;
		this.password = password;
		this.email = email;
		this.depHead = depHead;
	}

	public Supervisor(int id, String username, String password, String email, boolean depHead) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.email = email;
		this.depHead = depHead;
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

	public boolean isDepHead() {
		return depHead;
	}

	public void setDepHead(boolean depHead) {
		this.depHead = depHead;
	}

	@Override
	public String toString() {
		return "Supervisor [id=" + id + ", username=" + username + ", password=" + password + ", email=" + email
				+ ", depHead=" + depHead + "]";
	}

}
