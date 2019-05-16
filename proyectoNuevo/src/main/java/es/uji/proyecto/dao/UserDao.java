package es.uji.proyecto.dao;

import java.util.Collection;

import es.uji.proyecto.model.UserDetails;

public interface UserDao {
	UserDetails loadUserByUsername(String username, String password);
	Collection<UserDetails> listAllUsers(); //lista información delicada de los usuarios, de momento lo dejanmos 
	
}
