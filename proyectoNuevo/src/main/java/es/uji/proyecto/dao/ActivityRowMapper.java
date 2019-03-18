package es.uji.proyecto.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import es.uji.proyecto.model.Activity;

public class ActivityRowMapper implements RowMapper<Activity>{

	
	public Activity mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
			Activity activity = new Activity();
			activity.setIdActivity(rs.getInt("idActivity"));
			activity.setActDate(rs.getDate("actDate"));
			activity.setActivityName(rs.getString("activityName"));
			activity.setDuration(rs.getDate("duration"));
			activity.setDescriptionActivity(rs.getString("descriptionActivity"));
			activity.setPrice(rs.getDouble("price"));
			activity.setMaxPeople(rs.getInt("maxPeople"));
			activity.setMinPeople(rs.getInt("minPeople"));
			activity.setVacancies(rs.getInt("vacancies"));
			return activity;
	}
	
	
	
}

