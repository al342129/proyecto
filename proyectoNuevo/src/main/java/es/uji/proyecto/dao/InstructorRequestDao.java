package es.uji.proyecto.dao;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import es.uji.proyecto.model.Instructor;
import es.uji.proyecto.model.InstructorRequest;
import es.uji.proyecto.model.UserDetails;


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
	       jdbcTemplate.update("INSERT INTO InstructorRequest VALUES(?, ?, ?, ?,?,?, ?)",
	    		   instructorRequest.getNid(), instructorRequest.getName(), "Pendiente", 
	    		   instructorRequest.getRequestDate(), "/peticiones/"+instructorRequest.getNid()+".pdf",instructorRequest.getActivityTypeRequest(), instructorRequest.getEmail());
	      
	   }

	   /* Esborra la peticio de la base de dades */
	   void deleteInstructorRequest(InstructorRequest instructorRequest) {
	       jdbcTemplate.update("DELETE FROM InstructorRequest WHERE nid=?", instructorRequest.getNid());
	   }
	   
	   /*Esborra per DNI*/
	   
	   public void deleteInstructorRequest(String nid, String activityTypeRequest) {
	       jdbcTemplate.update("DELETE FROM InstructorRequest WHERE nid=? AND activityTypeRequest=?", nid, activityTypeRequest);
	   }
	   

	   /* Actualitza els atributs de l'instructor
	      (excepte el nid, que és la clau primària) */
	   //habria que actualizar el acceptancedate?
	   void updateInstructorRequest(InstructorRequest instructorRequest) {
	       jdbcTemplate.update("UPDATE InstructorRequest SET nid=?, name=?, state=?, requestDate=? WHERE nid=?",
	    		   instructorRequest.getNid(), instructorRequest.getName(),instructorRequest.getState(), instructorRequest.getRequestDate());
	   }

	   /* Obté la peticio amb el nid donat. Torna null si no existeix. */
	   public InstructorRequest getInstructorRequest(String instructorRequestNid, String activityTypeRequest) {
	       try {
	           return jdbcTemplate.queryForObject("SELECT * FROM InstructorRequest WHERE nid = ? AND activityTypeRequest=?",
		      		     new InstructorRequestRowMapper(), instructorRequestNid, activityTypeRequest);
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
	   
	   
	   protected String getSaltString() {
	        String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
	        StringBuilder salt = new StringBuilder();
	        Random rnd = new Random();
	        while (salt.length() < 11) {
	            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
	            salt.append(SALTCHARS.charAt(index));
	        }
	        String saltStr = salt.toString();
	        return saltStr;

	    }
	   
	   public void acceptInstructorRequest(@PathVariable String nid,@PathVariable String activityTypeRequest) {
		   
		   InstructorRequest nuevoMonitor = getInstructorRequest(nid,activityTypeRequest);
		   LocalDate a = LocalDate.now();
		   nuevoMonitor.setRequestDate(a);
		   String password = getSaltString();
		   jdbcTemplate.update("INSERT INTO Usuario VALUES(?,?,?)", nuevoMonitor.getNid(),password,"instructor");
		   jdbcTemplate.update("DELETE FROM InstructorRequest WHERE nid=? AND activityTypeRequest=? ", nuevoMonitor.getNid(), activityTypeRequest);
		   jdbcTemplate.update("INSERT INTO Instructor VALUES(?, ?, ?, ?, ?,?,?)",nuevoMonitor.getNid(),nuevoMonitor.getName(),"Disponible",
				   "default.jpg",nuevoMonitor.getRequestDate(),nuevoMonitor.getActivityTypeRequest()+"/","instructor");
		   
		   
		   
		   
	   }
	   
	   public UserDetails getNewInstructor(String instructorRequestNid) {
	       try {
	           return jdbcTemplate.queryForObject("SELECT * FROM Usuario WHERE nid = ? ",
		      		     new UserRowMapper(), instructorRequestNid);
		       }
	       catch(EmptyResultDataAccessException e) {
	           return null;
	       }
	   }
	   
	   public void modifyInstructor(Instructor instructor, String activityTypeRequest) {
	       String activities = instructor.getActivities();
	       String[] listado = activities.split("/");
	       for(String activity:listado) {
	    	   if(activity.equals(activityTypeRequest)) {
	    		   jdbcTemplate.update("UPDATE Instructor SET activities=? WHERE nid=?",
	    	    		   activities,  instructor.getNid());
	    	       jdbcTemplate.update("DELETE FROM InstructorRequest WHERE nid=? AND activityTypeRequest=? ", instructor.getNid(), activityTypeRequest);
	    	       return;
	    	   }
	       }
	       //instructor.setActivities(activities + activityTypeRequest+"/");
	       jdbcTemplate.update("UPDATE Instructor SET activities=? WHERE nid=?",
	    		   activities+ activityTypeRequest+"/",  instructor.getNid());
	       jdbcTemplate.update("DELETE FROM InstructorRequest WHERE nid=? AND activityTypeRequest=? ", instructor.getNid(), activityTypeRequest);
	   }

}