package es.uji.proyecto.dao;

import es.uji.proyecto.model.UserDetails;

public interface UserDao {
	UserDetails loadUserByUsername(String username, String password);
}
