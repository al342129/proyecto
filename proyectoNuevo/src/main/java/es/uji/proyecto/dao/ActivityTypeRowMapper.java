package es.uji.proyecto.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import es.uji.proyecto.model.ActivityType;

public class ActivityTypeRowMapper implements RowMapper<ActivityType>{

	
	public ActivityType mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
		ActivityType activityType = new ActivityType();
		activityType.setTypeName(rs.getString("typeName"));
		activityType.setDescriptionActType(rs.getString("description"));
		activityType.setLevel(rs.getString("level"));
		
			return activityType;
	}
	
	
	
}
