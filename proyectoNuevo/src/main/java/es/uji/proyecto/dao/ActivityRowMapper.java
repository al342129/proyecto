package es.uji.proyecto.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import org.springframework.jdbc.core.RowMapper;
import es.uji.proyecto.model.Activity;

public class ActivityRowMapper implements RowMapper<Activity>{

	
	public Activity mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
			Activity activity = new Activity();
			activity.setIdActivity(rs.getString("idActivity"));
			activity.setActDate(rs.getObject("actDate",LocalDate.class));
			activity.setActivityName(rs.getString("activityName"));
			activity.setLocation(rs.getString("location"));
			activity.setActivityImage(rs.getString("activityImage"));
			activity.setDuration(rs.getDouble("duration"));
			activity.setDescriptionActivity(rs.getString("descriptionActivity"));
			activity.setPrice(rs.getDouble("price"));
			activity.setMaxPeople(rs.getInt("maxPeople"));
			activity.setMinPeople(rs.getInt("minPeople"));
			activity.setVacancies(rs.getInt("vacancies"));
			activity.setTypeName(rs.getString("typeName"));
			activity.setLevel(rs.getString("level"));
			activity.setNidInstructor(rs.getString("nidInstructor"));
			return activity;
	}
	
	
	
}

