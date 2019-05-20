package es.uji.proyecto.dao;


import org.springframework.jdbc.core.RowMapper;

import es.uji.proyecto.model.Instructor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public final class InstructorRowMapper implements RowMapper<Instructor> {
   public Instructor mapRow(ResultSet rs, int rowNum) throws SQLException {
       Instructor instructor = new Instructor();
       instructor.setName(rs.getString("name"));
       instructor.setState(rs.getString("state"));
       instructor.setNid(rs.getString("nid"));
       instructor.setActivities(rs.getString("activities"));
       instructor.setProfileImage(rs.getString("profileImage"));
       instructor.setAcceptanceDate(rs.getObject("acceptanceDate",LocalDate.class));
       return instructor;
   }
}
