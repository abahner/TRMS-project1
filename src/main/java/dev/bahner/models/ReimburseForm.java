package dev.bahner.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity
@Table(name = "reimburse_forms")
public class ReimburseForm {

	@Id
	@Column(name = "r_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "date_time")
	private int dateTime;
	
	@Column(name = "locale")
	private String locale;
	
	@Column(name = "description")
	private String description;
	
	@Column(name = "cost")
	private double cost;
	
	@Column(name = "grading_format")
	private String gradeFormat;
	
	@Column(name = "event_type")
	private String eventType;
	
	@OneToOne
	@LazyCollection (LazyCollectionOption.FALSE)
	@JoinColumn(name = "e_id", referencedColumnName = "e_id")
	private Employee employee;
	
	@Column(name = "is_approved")
	private boolean isApproved;
	
	@Column(name = "benco_approved")
	private boolean bencoApproved;

	public ReimburseForm() {
		super();
	}

	public ReimburseForm(int dateTime, String locale, String description, double cost, String gradeFormat,
			String eventType, boolean isApproved, boolean bencoApproved) {
		super();
		this.dateTime = dateTime;
		this.locale = locale;
		this.description = description;
		this.cost = cost;
		this.gradeFormat = gradeFormat;
		this.eventType = eventType;
		this.isApproved = isApproved;
		this.bencoApproved = bencoApproved;
	}

	public ReimburseForm(int id, int dateTime, String locale, String description, double cost, String gradeFormat,
			String eventType, boolean isApproved, boolean bencoApproved) {
		super();
		this.id = id;
		this.dateTime = dateTime;
		this.locale = locale;
		this.description = description;
		this.cost = cost;
		this.gradeFormat = gradeFormat;
		this.eventType = eventType;
		this.isApproved = isApproved;
		this.bencoApproved = bencoApproved;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getDateTime() {
		return dateTime;
	}

	public void setDateTime(int dateTime) {
		this.dateTime = dateTime;
	}

	public String getLocale() {
		return locale;
	}

	public void setLocale(String locale) {
		this.locale = locale;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

	public String getGradeFormat() {
		return gradeFormat;
	}

	public void setGradeFormat(String gradeFormat) {
		this.gradeFormat = gradeFormat;
	}

	public String getEventType() {
		return eventType;
	}

	public void setEventType(String eventType) {
		this.eventType = eventType;
	}

	
	public boolean isApproved() {
		return isApproved;
	}

	public void setApproved(boolean isApproved) {
		this.isApproved = isApproved;
	}
	
	
	public boolean isBencoApproved() {
		return bencoApproved;
	}

	public void setBencoApproved(boolean bencoApproved) {
		this.bencoApproved = bencoApproved;
	}

	@Override
	public String toString() {
		return "ReimburseForm [id=" + id + ", dateTime=" + dateTime + ", locale=" + locale + ", description="
				+ description + ", cost=" + cost + ", gradeFormat=" + gradeFormat + ", eventType=" + eventType
				+ ", employee=" + employee + ", isApproved=" + isApproved + ", bencoApproved=" + bencoApproved + "]";
	}


	
	
}
