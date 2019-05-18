package es.uji.proyecto.model;

public class UserDetails {
	String username;
	String password;
	int acceso;	//hay que mirar como hacerlo. Debería tener un valor de 1 a 3 en funcion de si eres monitor, alumno o Manel.
	
	
	
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
