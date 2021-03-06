package es.uji.proyecto.model;

import java.sql.Date;
import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

public class InstructorRequest {
	private String nid;
	private String name;
	private String state;
	@DateTimeFormat(iso=DateTimeFormat.ISO.DATE)
	private LocalDate requestDate;
	private String activityTypeRequest;
	private String instructorRequestPDF;
	private String email;
	
	
	
	public InstructorRequest() {
		super();	
	}
	
	public String getNid() {
		return nid;
	}
	public void setNid(String nid) {
		this.nid = nid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	
	
	@Override
	public String toString() {
		return "InstructorRequest [nid=" + nid + ", name=" + name + ", state=" + state + ", requestDate=" + requestDate
				+"]";
	}
	public void setRequestDate(LocalDate requestDate) {
		this.requestDate = requestDate;
	}
	public LocalDate getRequestDate() {
		return requestDate;
	}
	public String getInstructorRequestPDF() {
		return instructorRequestPDF;
	}
	public void setInstructorRequestPDF(String instructorRequestPDF) {
		this.instructorRequestPDF = instructorRequestPDF;
	}
	public String getActivityTypeRequest() {
		return activityTypeRequest;
	}
	public void setActivityTypeRequest(String activityTypeRequest) {
		this.activityTypeRequest = activityTypeRequest;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	
}
