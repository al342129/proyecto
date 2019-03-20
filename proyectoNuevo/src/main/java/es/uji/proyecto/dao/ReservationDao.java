package es.uji.proyecto.dao;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import es.uji.proyecto.model.Reservation;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

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
        jdbcTemplate.update("INSERT INTO Reservation VALUES(?, ?, ?, ?, ?)",
                reservation.getBookingDate(), reservation.getBookingNumber(), reservation.getNumberOfPeople(),
                reservation.getState(),reservation.getTransactionNumber());
    }

    /* Esborra la reserva de la base de dades */
    public void deleteReservation(Reservation reservation) {
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
            return jdbcTemplate.queryForObject("SELECT * from Reservarion WHERE reservationNumber=?",
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
    //hehco por mi, no se si es necesario hacerlo así ---
    public void deleteReservation(String reservationNumber) {
    	jdbcTemplate.update("DELETE from Reservation where reservationNumber=?", reservationNumber);
    }
}
