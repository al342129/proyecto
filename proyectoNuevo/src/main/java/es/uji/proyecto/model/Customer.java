package es.uji.proyecto.model;

public class Customer {
	private String nid;
	private String name;
	private String gender;
	private String email;
	
	
	public Customer() {
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
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	@Override
	public String toString() {
		return "Customer [nid=" + nid + ", name=" + name + ", gender=" + gender + ", email=" + email + "]";
	}
	
	
	
	
	
}
