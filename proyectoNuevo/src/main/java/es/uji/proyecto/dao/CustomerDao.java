package es.uji.proyecto.dao;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import es.uji.proyecto.model.Customer;


@Repository // En Spring els DAOs van anotats amb @Repository
public class CustomerDao {

	private JdbcTemplate jdbcTemplate;

	   // Obté el jdbcTemplate a partir del Data Source
	   @Autowired
	   public void setDataSource(DataSource dataSource) {
	       jdbcTemplate = new JdbcTemplate(dataSource);
	   }

	   /* Afegeix el usuari a la base de dades */
	   public void addCustomer(Customer customer) {
		   System.out.println(customer.getPassword());
		   jdbcTemplate.update("INSERT INTO Usuario VALUES(?, ?, ?)", 
				   	customer.getNid(), customer.getPassword(), "customer");
	       jdbcTemplate.update("INSERT INTO Customer VALUES(?, ?, ?, ?, ?)",
		       		customer.getNid(), customer.getName(), customer.getGender(), customer.getEmail(), "customer");
	   
	       
	   }

	   /* Esborra el usuari de la base de dades */
	   public void deleteCustomer(String customerNid) {
	       jdbcTemplate.update("DELETE FROM Customer WHERE nid=?", customerNid);
	   }

	   /* Actualitza els atributs del usuari
	      (excepte el nid, que és la clau primària) */
	   public void updateCustomer(Customer customer) {
	       jdbcTemplate.update("UPDATE Customer SET name=?,gender=?, email=? WHERE nid=?",customer.getName(),customer.getGender(),customer.getEmail(), customer.getNid());
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

	   /* Obté tots els usuaris. Torna una llista buida si no n'hi ha cap. */
	   public List<Customer> getCustomers() {
	       try {
	    	  
	           return jdbcTemplate.query("SELECT * FROM Customer", new CustomerRowMapper());
	       }
	       catch(EmptyResultDataAccessException e) {
	           return new ArrayList<Customer>();
	       }
	   }

}























