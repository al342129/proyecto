package es.uji.proyecto.dao;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import es.uji.proyecto.model.Activity;
import es.uji.proyecto.model.Instructor;


@Repository // En Spring els DAOs van anotats amb @Repository
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
	       jdbcTemplate.update("DELETE FROM Usuario WHERE nid=?", instructor.getNid());
	   }
	   
	   
	   public void deleteInstructor(String instructor) {
		   try {
			   jdbcTemplate.update("DELETE FROM Qualification WHERE nid=?", instructor);
			   jdbcTemplate.update("DELETE FROM Instructor WHERE nid=?", instructor);
		   }
		   catch(Exception ex) {
			   ex.printStackTrace();
		   }
	   }
	   
	   

	   /* Actualitza els atributs de l'instructor
	      (excepte el nid, que és la clau primària) */
	   //habria que actualizar el acceptancedate?
	   public void updateInstructor(Instructor instructor) {
	       jdbcTemplate.update("UPDATE Instructor SET name=?,state=? WHERE nid=?",
	    		   instructor.getName(), instructor.getState(), instructor.getNid());
	   }

	   /* Obté l'instructor amb el nid donat. Torna null si no existeix. */
	   public Instructor getInstructor(String instructorNid) {
	       try {
	           return jdbcTemplate.queryForObject("SELECT * FROM Instructor WHERE nid = ?",
		      		     new InstructorRowMapper(), instructorNid);
		       }
	       catch(EmptyResultDataAccessException e) {
	           return null;
	       }
	   }
	   
	   
	   

	   /* Obté tots els instructors. Torna una llista buida si no n'hi ha cap. */
	   public List<Instructor> getInstructors() {
	       try {
	           return jdbcTemplate.query("SELECT * FROM Instructor", new InstructorRowMapper());
	       }
	       catch(EmptyResultDataAccessException e) {
	           return new ArrayList<Instructor>();
	       }
	   }
	   
	   public int getRowNumber(){
		   
		   String sql = "SELECT MAX(idActivity) FROM Activity";
		   int maximo = jdbcTemplate.queryForObject(sql, Integer.class);
		   return maximo;
		   
	   }
	   
	   
	   public void createActivity(Activity activity,  String image) {
		   String identificador = activity.getNidInstructor() + activity.getLocation().substring(0,3);
	       jdbcTemplate.update("INSERT INTO Activity VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?)",
	    		   identificador, activity.getActivityName(), activity.getActDate(),activity.getLocation(),"/images/"+activity.getNidInstructor()+image, 
	    		   activity.getDuration(),activity.getDescriptionActivity(), activity.getPrice(), activity.getMaxPeople(), activity.getMinPeople(),
	    		   activity.getVacancies(), activity.getTypeName(), activity.getLevel(),activity.getNidInstructor()
	    		   );
	   }
	   
	   
	   public List<Activity> getActivities(String instructorNid) {
	       try {
	           return jdbcTemplate.query("SELECT * FROM Activity WHERE nidInstructor = ?",
		      		     new ActivityRowMapper(), instructorNid);
	       }
	       catch(EmptyResultDataAccessException e) {
	           return new ArrayList<Activity>();
	       }
	   }
	   

}
