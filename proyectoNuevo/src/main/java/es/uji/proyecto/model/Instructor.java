package es.uji.proyecto.model;

public class Instructor {
	
	private String name;
	private String state;
	private String nid;
	private String profileImage;
	private String acceptanceDate;
	
	
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
	public String getAcceptanceDate() {
		return acceptanceDate;
	}
	public void setAcceptanceDate(String acceptanceDate) {
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
	
	
	
}
