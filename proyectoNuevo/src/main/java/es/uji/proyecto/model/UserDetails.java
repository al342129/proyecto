package es.uji.proyecto.model;

public class UserDetails {
	String nid; //es el username 
	String password;
	String type;	//hay que mirar como hacerlo. Debería tener un valor de 1 a 3 en funcion de si eres monitor, alumno o Manel.
	
	
	
	public UserDetails(String nid, String password, int acceso) {
		super();
		this.nid = nid;
		this.password = password;
		this.type=type;
	}
	
	public UserDetails() {
	}
	
	
	public String getNid() {
		return nid;
	}
	public void setNid(String nid) {
		this.nid = nid;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserType() {
		return type;
	}

	public void setUserType(String type) {
		this.type = type;
	}
	
	

}
