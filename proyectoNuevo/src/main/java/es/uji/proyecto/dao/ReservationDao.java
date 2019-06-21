package es.uji.proyecto.dao;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import es.uji.proyecto.model.Reservation;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Repository // En Spring els DAOs van anotats amb @Repository
public class ReservationDao {

    private JdbcTemplate jdbcTemplate;

    // Obté el jdbcTemplate a partir del Data Source
    @Autowired
    public void setDataSource(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    /* Afegeix la reserva a la base de dades */
    public void addReservation(Reservation reservation) {
        jdbcTemplate.update("INSERT INTO Reservation VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)",
                reservation.getBookingDate(), reservation.getBookingNumber(), 
                reservation.getState(),reservation.getNumberOfPeople(), reservation.getTransactionNumber(), reservation.getPricePerPerson() , 
        		reservation.getTotalPrice(), reservation.getNid(), reservation.getIdActivity()); //priceperperson, totalprice, nid, idactivity;
    }

    /* Esborra la reserva de la base de dades */
    public void deleteReservation(Reservation reservation) {
    	int vacancies = reservation.getNumberOfPeople();
    	int actId= reservation.getIdActivity();

    	jdbcTemplate.update("UPDATE Activity SET vacancies=vacancies+? WHERE idActivity=?", vacancies, actId);
        jdbcTemplate.update("DELETE from Reservation where bookingNumber=?", reservation.getBookingNumber());
        
    }

    /* Actualitza els atributs de la reserva
       (excepte el bookingNumber, que és la clau primària) */
    public void updateReservation(Reservation reservation) {
        jdbcTemplate.update("UPDATE reservation SET bookingDate=?, numberOfPeople=?, state=?, transactionNumber=? where bookingDate=?",
                reservation.getBookingDate(), reservation.getNumberOfPeople(),
                reservation.getState(),reservation.getTransactionNumber(), reservation.getBookingNumber());
    }

    /* Obté la reserva amb el numero de reserva donat. Torna null si no existeix. */
    public Reservation getReservation(String reservationNumber) {
        try {
            return jdbcTemplate.queryForObject("SELECT * from Reservation WHERE bookingNumber=?",
                    new ReservationRowMapper(), reservationNumber);
        }
        catch(EmptyResultDataAccessException e) {
            return null;
        }
    }

    /* Obté tots els nadadors. Torna una llista buida si no n'hi ha cap. */
    public List<Reservation> getReservations() {
        try {
            return jdbcTemplate.query("SELECT * from Reservation",
                    new ReservationRowMapper());
        }
        catch(EmptyResultDataAccessException e) {
            return new ArrayList<Reservation>();
        }
    }
    
    public List<Reservation> getReservationsByNid(String nid){
    	try {
    		System.out.print("segundo milagro del dia ");
    		return jdbcTemplate.query("SELECT * from Reservation WHERE nid =?", new ReservationRowMapper(), nid);

    	}
    	catch(EmptyResultDataAccessException e) {
    		 return new ArrayList<Reservation>();
    	}
    }
//    //hehco por mi, no se si es necesario hacerlo así ---
//    public void deleteReservation(String reservationNumber) {
//    	jdbcTemplate.update("DELETE from Reservation where bookingNumber=?", reservationNumber);
//    }
//    
    public String getSaltString() {
        String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 11) {
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        String saltStr = salt.toString();
        return saltStr;

    }
    
//    select reservation.bookingdate, reservation.state,reservation.numberofpeople, reservation.idactivity, reservation.nid from reservation inner join activity on activity.idactivity = reservation.idactivity inner join instructor on activity.nidinstructor='54321234R';
   
    public List<Reservation> getReservationsPending(String nid){
    	try {
    		return jdbcTemplate.query("SELECT DISTINCT reservation.bookingNumber, reservation.transactionNumber, reservation.bookingDate, reservation.state, reservation.numberOfPeople, reservation.idActivity, reservation.nid FROM Reservation INNER JOIN Activity On activity.idActivity=reservation.idActivity INNER JOIN Instructor ON activity.nidInstructor=?",new ReservationRowMapper(), nid);

    	}
    	catch(EmptyResultDataAccessException e) {
    		 return new ArrayList<Reservation>();
    	}
    }
    
    public void stateConfirm(String bookingNumber) {
    	
    	jdbcTemplate.update("UPDATE Reservation SET state=? WHERE bookingNumber=?", "Aceptada", bookingNumber);

        
    }
    
    
    public void pay(String bookingNumber) {
    	jdbcTemplate.update("UPDATE Reservation SET state=? WHERE bookingNumber=?", "Pagada", bookingNumber);
    }
    
    
    public List<Reservation> getPaid(String nid){
    	try {
    		return jdbcTemplate.query("SELECT * FROM Reservation WHERE state=? AND nid=?",new ReservationRowMapper(), "Pagada",nid);

    	}
    	catch(EmptyResultDataAccessException e) {
    		 return new ArrayList<Reservation>();
    	}
    }
    
    
    public List<Reservation> getReservationsPaid(String nid){
    	try {
    		return jdbcTemplate.query("SELECT DISTINCT reservation.bookingNumber, reservation.transactionNumber, reservation.bookingDate, reservation.state, reservation.numberOfPeople, reservation.idActivity, reservation.nid FROM Reservation INNER JOIN Activity On activity.idActivity=reservation.idActivity INNER JOIN Instructor ON activity.nidInstructor=? WHERE reservation.state=?",new ReservationRowMapper(), nid, "Pagada");

    	}
    	catch(EmptyResultDataAccessException e) {
    		 return new ArrayList<Reservation>();
    	}
    }
    
    
    
    
    
}
