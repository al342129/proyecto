package es.uji.proyecto.model;

public class UserDetails {
	String username;
	String password;
	int acceso;
	
	
	
	public UserDetails(String username, String password, int acceso) {
		super();
		this.username = username;
		this.password = password;
		this.acceso=acceso;
	}
	
	public UserDetails() {
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

	public int getAcceso() {
		return acceso;
	}

	public void setAcceso(int acceso) {
		this.acceso = acceso;
	}
	
	

}
