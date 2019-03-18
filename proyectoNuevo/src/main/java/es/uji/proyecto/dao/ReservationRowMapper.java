package es.uji.proyecto.dao;


import org.springframework.jdbc.core.RowMapper;

import es.uji.proyecto.model.Reservation;

import java.sql.ResultSet;
import java.sql.SQLException;

public final class ReservationRowMapper implements RowMapper<Reservation> {
   public Reservation mapRow(ResultSet rs, int rowNum) throws SQLException {
       Reservation reservation = new Reservation();
       reservation.setBookingDate(rs.getDate("bookingDate"));
       reservation.setBookingNumber(rs.getString("bookingNumber"));
       reservation.setState(rs.getString("state"));
       reservation.setNumberOfPeople(rs.getInt("numberOfPeople"));
       reservation.setTransactionNumber(rs.getString("transactionNumber"));
       
       return reservation;
   }
}
