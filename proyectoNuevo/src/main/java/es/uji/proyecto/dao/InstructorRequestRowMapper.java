package es.uji.proyecto.dao;


import org.springframework.jdbc.core.RowMapper;

import es.uji.proyecto.model.InstructorRequest;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public final class InstructorRequestRowMapper implements RowMapper<InstructorRequest> {
   public InstructorRequest mapRow(ResultSet rs, int rowNum) throws SQLException {
       InstructorRequest instructorRequest = new InstructorRequest();
       instructorRequest.setName(rs.getString("name"));
       instructorRequest.setState(rs.getString("state"));
       instructorRequest.setNid(rs.getString("nid"));
       instructorRequest.setRequestDate(rs.getObject("requestDate",LocalDate.class));
       instructorRequest.setInstructorRequestPDF(rs.getString("instructorRequestPDF"));
       
       return instructorRequest;
   }
}
