package es.uji.proyecto.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.sql.DataSource;

import org.jasypt.util.password.BasicPasswordEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import es.uji.proyecto.model.Customer;
import es.uji.proyecto.model.UserDetails;

@Repository
public class UserProvider implements UserDao{
	private JdbcTemplate jdbcTemplate;

	   // Obté el jdbcTemplate a partir del Data Source
	   @Autowired
	   public void setDataSource(DataSource dataSource) {
	       jdbcTemplate = new JdbcTemplate(dataSource);
	   }

	   /* Afegeix el usuari a la base de dades */
	   public void addUser(UserDetails user) {
	       jdbcTemplate.update("INSERT INTO Usuario VALUES(?, ?, ?)",
		       		user.getNid(), user.getPassword(),  user.getUserType());
	   }

	   /* Esborra el usuari de la base de dades */
	   public void deleteUser( String nid) {
	       jdbcTemplate.update("DELETE FROM Usuario WHERE nid=?", nid);
	   }

	   /* Actualitza els atributs del usuari
	      (excepte el nid, que és la clau primària) */
	   public void updateUser(UserDetails user) {
	       jdbcTemplate.update("UPDATE Usuario SET password=?, type=? WHERE nid=?", user.getPassword(), user.getUserType(),user.getNid());
	   }

	   /* Obté el usuari amb el nom donat. Torna null si no existeix. */
	   public Customer getCustomer(String customerNid) {
	       try {
	           return jdbcTemplate.queryForObject("SELECT * FROM Customer WHERE nid = ?",
		      		     new CustomerRowMapper(),
		      		     customerNid);
		       }
	       catch(EmptyResultDataAccessException e) {
	           return null;
	       }
	   }

	@Override
	public UserDetails loadUserByUsername(String username, String password) {
		 try {
	           UserDetails user = jdbcTemplate.queryForObject("SELECT * FROM Usuario WHERE nid = ?",
		      		     new UserRowMapper(),
		      		     username);
	           
	         BasicPasswordEncryptor passwordEncryptor = new BasicPasswordEncryptor(); 
	      	 if (passwordEncryptor.checkPassword(password, user.getPassword())) {
	      	 // Es deuria esborrar de manera segura el camp password abans de tornar-lo
	      		 return user; 
	          } 
	      	 else {
	      		 return null; // bad login!
	      	 }
	      	 
		   }
	       catch(EmptyResultDataAccessException e) {
	           return null;
	       }
		 
		
		
	}

	@Override
	public Collection<UserDetails> listAllUsers() {
		// TODO Auto-generated method stub
		return null;
	}

	 
}
