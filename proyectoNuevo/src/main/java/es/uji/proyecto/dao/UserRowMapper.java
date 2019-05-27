package es.uji.proyecto.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import es.uji.proyecto.model.Customer;
import es.uji.proyecto.model.UserDetails;

public final class UserRowMapper implements RowMapper<UserDetails>{

	
	public UserDetails mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
			UserDetails user = new UserDetails();
			user.setNid(rs.getString("nid"));
			user.setPassword(rs.getString("password"));
			user.setUserType(rs.getString("userType"));
			return user;
	}
	
	
	
}

