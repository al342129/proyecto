package es.uji.proyecto.dao;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import es.uji.proyecto.model.Customer;

public class CustomerDao {

	private JdbcTemplate jdbcTemplate;

	   // Obté el jdbcTemplate a partir del Data Source
	   @Autowired
	   public void setDataSource(DataSource dataSource) {
	       jdbcTemplate = new JdbcTemplate(dataSource);
	   }

	   /* Afegeix el usuari a la base de dades */
	   void addCustomer(Customer customer) {
	       jdbcTemplate.update("INSERT INTO Customer VALUES(?, ?, ?, ?)",
		       		customer.getNid(), customer.getName(), customer.getGender(), customer.getEmail());
	   }

	   /* Esborra el usuari de la base de dades */
	   void deleteCustomer(Customer customer) {
	       jdbcTemplate.update("DELETE FROM Customer WHERE nom=?", customer.getNid());
	   }

	   /* Actualitza els atributs del usuari
	      (excepte el nid, que és la clau primària) */
	   void updateCustomer(Customer customer) {
	       jdbcTemplate.update("UPDATE Customer SET name=?,gender=?, email=? WHERE nid=?",customer.getName(),customer.getGender(),customer.getEmail(), customer.getNid());
	   }

	   /* Obté el usuari amb el nom donat. Torna null si no existeix. */
	   Customer getCustomer(Customer customerName) {
	       try {
	           return jdbcTemplate.queryForObject("SELECT * FROM Customer WHERE nid = ?",
		      		     new CustomerRowMapper(),
		      		     customerName);
		       }
	       catch(EmptyResultDataAccessException e) {
	           return null;
	       }
	   }

	   /* Obté tots els usuaris. Torna una llista buida si no n'hi ha cap. */
	   List<Customer> getCustomers() {
	       try {
	           return jdbcTemplate.query("SELECT * FROM Customer", new CustomerRowMapper());
	       }
	       catch(EmptyResultDataAccessException e) {
	           return new ArrayList<Customer>();
	       }
	   }

}

