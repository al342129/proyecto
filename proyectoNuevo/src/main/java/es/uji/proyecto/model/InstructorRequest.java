package es.uji.proyecto.model;

import java.sql.Date;

public class InstructorRequest {
	private String nid;
	private String name;
	private String state;
	private String requestDate; 
	private String resolutionDate;
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
	public String getRequestDate() {
		return requestDate;
	}
	public void setRequestDate(String requestDate) {
		this.requestDate = requestDate;
	}
	public String getResolutionDate() {
		return resolutionDate;
	}
	public void setResolutionDate(String resolutionDate) {
		this.resolutionDate = resolutionDate;
	}
	@Override
	public String toString() {
		return "InstructorRequest [nid=" + nid + ", name=" + name + ", state=" + state + ", requestDate=" + requestDate
				+ ", resolutionDate=" + resolutionDate + "]";
	}
	
	
}
