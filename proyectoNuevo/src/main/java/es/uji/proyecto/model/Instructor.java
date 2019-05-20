package es.uji.proyecto.model;

import java.sql.Date;
import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

public class Instructor {
	private String nid;
	private String name;
	private String state;
	private String profileImage;
	private String activities;
	@DateTimeFormat(iso=DateTimeFormat.ISO.DATE)
	private LocalDate acceptanceDate;
	
	
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
	public String getNid() {
		return nid;
	}
	public void setNid(String nid) {
		this.nid = nid;
	}
	public LocalDate getAcceptanceDate() {
		return acceptanceDate;
	}
	public void setAcceptanceDate(LocalDate acceptanceDate) {
		this.acceptanceDate = acceptanceDate;
	}
	
	public String getProfileImage() {
		return profileImage;
	}
	public void setProfileImage(String profileImage) {
		this.profileImage = profileImage;
	}
	
	@Override
	public String toString() {
		return "Instructor [name=" + name + ", state=" + state + ", nid=" + nid + ", acceptanceDate=" + acceptanceDate
				+ "]";
	}
	public String getActivities() {
		return activities;
	}
	public void setActivities(String activities) {
		this.activities = activities;
	}
	
	
	
}
