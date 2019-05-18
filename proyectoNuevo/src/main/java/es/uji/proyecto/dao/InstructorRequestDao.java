package es.uji.proyecto.dao;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import es.uji.proyecto.model.Instructor;
import es.uji.proyecto.model.InstructorRequest;


@Repository // En Spring els DAOs van anotats amb @Repository
public class InstructorRequestDao {
	
	private JdbcTemplate jdbcTemplate;

	   // Obté el jdbcTemplate a partir del Data Source
	   @Autowired
	   public void setDataSource(DataSource dataSource) {
	       jdbcTemplate = new JdbcTemplate(dataSource);
	   }

	   /* Afegeix la peticio a la base de dades */
	   public void addInstructorRequest(InstructorRequest instructorRequest) {
		   LocalDate a = LocalDate.now();
		   instructorRequest.setRequestDate(a);
	       jdbcTemplate.update("INSERT INTO InstructorRequest VALUES(?, ?, ?, ?,?)",
	    		   instructorRequest.getNid(), instructorRequest.getName(), "Pendiente", 
	    		   instructorRequest.getRequestDate(), "/peticiones/"+instructorRequest.getNid()+".pdf");
	      
	   }

	   /* Esborra la peticio de la base de dades */
	   void deleteInstructorRequest(InstructorRequest instructorRequest) {
	       jdbcTemplate.update("DELETE FROM InstructorRequest WHERE nid=?", instructorRequest.getNid());
	   }
	   
	   /*Esborra per DNI*/
	   
	   public void deleteInstructorRequest(String nid) {
	       jdbcTemplate.update("DELETE FROM InstructorRequest WHERE nid=?", nid);
	   }
	   

	   /* Actualitza els atributs de l'instructor
	      (excepte el nid, que és la clau primària) */
	   //habria que actualizar el acceptancedate?
	   void updateInstructorRequest(InstructorRequest instructorRequest) {
	       jdbcTemplate.update("UPDATE InstructorRequest SET nid=?, name=?, state=?, requestDate=? WHERE nid=?",
	    		   instructorRequest.getNid(), instructorRequest.getName(),instructorRequest.getState(), instructorRequest.getRequestDate());
	   }

	   /* Obté la peticio amb el nid donat. Torna null si no existeix. */
	   public InstructorRequest getInstructorRequest(String instructorRequestNid) {
	       try {
	           return jdbcTemplate.queryForObject("SELECT * FROM InstructorRequest WHERE nid = ?",
		      		     new InstructorRequestRowMapper(), instructorRequestNid);
		       }
	       catch(EmptyResultDataAccessException e) {
	           return null;
	       }
	   }

	   /* Obté totes les peticions d'instructors. Torna una llista buida si no n'hi ha cap. */
	   public List<InstructorRequest> getInstructorRequests() {
	       try {
	           return jdbcTemplate.query("SELECT * FROM InstructorRequest", new InstructorRequestRowMapper());
	       }
	       catch(EmptyResultDataAccessException e) {
	           return new ArrayList<InstructorRequest>();
	       }
	   }
	   
	   public void acceptInstructorRequest(@PathVariable String nid) {
		   
		   InstructorRequest nuevoMonitor = getInstructorRequest(nid);
		   DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		   LocalDate localdate = LocalDate.now();
		   jdbcTemplate.update("DELETE FROM InstructorRequest WHERE nid=?", nid);
		   jdbcTemplate.update("INSERT INTO Instructor VALUES(?, ?, ?, ?)",nuevoMonitor.getNid(),"Hola",nuevoMonitor.getName()
				   ,"Disponible");
		   
	   }

}
