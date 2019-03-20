package es.uji.proyecto.dao;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import es.uji.proyecto.model.Instructor;

public class InstructorDao {
	
	private JdbcTemplate jdbcTemplate;

	   // Obté el jdbcTemplate a partir del Data Source
	   @Autowired
	   public void setDataSource(DataSource dataSource) {
	       jdbcTemplate = new JdbcTemplate(dataSource);
	   }

	   /* Afegeix l'instructor a la base de dades */
	   void addInstructor(Instructor instructor) {
	       jdbcTemplate.update("INSERT INTO Instructor VALUES(?, ?, ?, ?, ?)",
	    		   instructor.getName(), instructor.getState(), instructor.getNid(), 
	    		   instructor.getAcceptanceDate());
	   }

	   /* Esborra l'instructor de la base de dades */
	   void deleteInstructor(Instructor instructor) {
	       jdbcTemplate.update("DELETE FROM Instructor WHERE nid=?", instructor.getNid());
	   }

	   /* Actualitza els atributs de l'instructor
	      (excepte el nid, que és la clau primària) */
	   //habria que actualizar el acceptancedate?
	   void updateInstructor(Instructor instructor) {
	       jdbcTemplate.update("UPDATE Instructor SET name=?,state=?, acceptanceDate=? WHERE nid=?",
	    		   instructor.getName(), instructor.getState(),instructor.getAcceptanceDate(), instructor.getNid());
	   }

	   /* Obté l'instructor amb el nid donat. Torna null si no existeix. */
	   Instructor getInstructor(Instructor instructorNid) {
	       try {
	           return jdbcTemplate.queryForObject("SELECT * FROM Instructor WHERE nid = ?",
		      		     new InstructorRowMapper(), instructorNid);
		       }
	       catch(EmptyResultDataAccessException e) {
	           return null;
	       }
	   }

	   /* Obté tots els instructors. Torna una llista buida si no n'hi ha cap. */
	   List<Instructor> getInstructors() {
	       try {
	           return jdbcTemplate.query("SELECT * FROM Instructor", new InstructorRowMapper());
	       }
	       catch(EmptyResultDataAccessException e) {
	           return new ArrayList<Instructor>();
	       }
	   }

}
