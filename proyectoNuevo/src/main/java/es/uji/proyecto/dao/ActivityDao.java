package es.uji.proyecto.dao;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import es.uji.proyecto.model.Activity;


@Repository // En Spring els DAOs van anotats amb @Repository
public class ActivityDao {

	private JdbcTemplate jdbcTemplate;

	   // Obté el jdbcTemplate a partir del Data Source
	   @Autowired
	   public void setDataSource(DataSource dataSource) {
	       jdbcTemplate = new JdbcTemplate(dataSource);
	   }

	   /* Afegeix l'activitat a la base de dades */
	   void addActivity(Activity activity) {
	       jdbcTemplate.update("INSERT INTO Activity VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)",
	    		   activity.getIdActivity(), activity.getActDate(), activity.getActivityName(), 
	    		   activity.getDuration(), activity.getDescriptionActivity(), 
	    		   activity.getPrice(),activity.getMaxPeople(),activity.getMinPeople(),
	    		   activity.getVacancies());
	   }

	   /* Esborra l'activitat de la base de dades */
	   void deleteActivity(Activity activity) {
	       jdbcTemplate.update("DELETE FROM Activity WHERE IdActivity=?", activity.getIdActivity());
	   }

	   /* Actualitza els atributs de l'activitat
	      (excepte el idActivity, que és la clau primària) */
	   public void updateActivity(Activity activity) {
	       jdbcTemplate.update("UPDATE Activity SET actDate=?,activityName=?, duration=?, descriptionActivity=?, price=?,"
	       		+ " location=?,maxPeople=?, minPeople=?, vacancies=? WHERE idActivity=?",
	    		   activity.getActDate(), activity.getActivityName(), 
	    		   activity.getDuration(), activity.getDescriptionActivity(), 
	    		   activity.getPrice(),activity.getLocation(),activity.getMaxPeople(),activity.getMinPeople(),
	    		   activity.getVacancies() ,activity.getIdActivity());
	   }

	   /* Obté l'activitat amb el nom donat. Torna null si no existeix. */
	   public Activity getActivity(int idActivity) {
	       try {
	           return jdbcTemplate.queryForObject("SELECT * FROM Activity WHERE idActivity = ?",
		      		     new ActivityRowMapper(),
		      		     idActivity);
		       }
	       catch(EmptyResultDataAccessException e) {
	           return null;
	       }
	   }

	   /* Obté totes les activitats. Torna una llista buida si no n'hi ha cap. */
	   public List<Activity> getActivities() {
	       try {
	           return jdbcTemplate.query("SELECT * FROM Activity", new ActivityRowMapper());
	       }
	       catch(EmptyResultDataAccessException e) {
	           return new ArrayList<Activity>();
	       }
	   }

}

