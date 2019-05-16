package es.uji.proyecto.dao;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import es.uji.proyecto.model.ActivityType;

@Repository
public class ActivityTypeDao {

		private JdbcTemplate jdbcTemplate;

	   // Obté el jdbcTemplate a partir del Data Source
	   @Autowired
	   public void setDataSource(DataSource dataSource) {
	       jdbcTemplate = new JdbcTemplate(dataSource);
	   }

	   public void addActivityType(ActivityType activityType) {
	       jdbcTemplate.update("INSERT INTO ActivityType VALUES(?, ?, ?)",
	    		   activityType.getTypeName(), activityType.getDescriptionActType(), activityType.getLevel());
	   }


	   public void deleteActivityType(ActivityType activityType) {
	       jdbcTemplate.update("DELETE FROM ActivityType WHERE typeName=? AND level=?", activityType.getTypeName(), activityType.getLevel());
	   }

	  
	   public void updateActivityType(ActivityType activityType) {
	       jdbcTemplate.update("UPDATE ActivityType SET descriptionActType=? WHERE typeName=? AND level=?",activityType.getDescriptionActType(),activityType.getTypeName(),activityType.getLevel());
	   }


	   public ActivityType getActivityType(ActivityType activityType) {
	       try {
	           return jdbcTemplate.queryForObject("SELECT * FROM ActivityType WHERE typeName = ? AND level=?",
		      		     new ActivityTypeRowMapper(),
		      		     activityType.getTypeName(), activityType.getLevel());
		       }
	       catch(EmptyResultDataAccessException e) {
	           return null;
	       }
	   }

	   public List<ActivityType> getActivityTypes() {
	       try {
	    	  
	           return jdbcTemplate.query("SELECT * FROM ActivityType", new ActivityTypeRowMapper());
	       }
	       catch(EmptyResultDataAccessException e) {
	           return new ArrayList<ActivityType>();
	       }
	   }

}

