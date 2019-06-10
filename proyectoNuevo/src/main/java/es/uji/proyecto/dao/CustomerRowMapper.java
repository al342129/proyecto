package es.uji.proyecto.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import es.uji.proyecto.model.Customer;

public final class CustomerRowMapper implements RowMapper<Customer>{

	
	public Customer mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
			Customer customer = new Customer();
			customer.setNid(rs.getString("nid"));
			customer.setName(rs.getString("name"));
			customer.setGender(rs.getString("gender"));
			customer.setEmail(rs.getString("email"));
			customer.setPassword(rs.getString("password"));
			//customer.setRepeat(rs.getString("repeat"));
			return customer;
	}
	
	
	
}

