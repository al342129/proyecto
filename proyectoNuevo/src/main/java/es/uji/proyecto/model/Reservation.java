package es.uji.proyecto.model;

import java.sql.Date;
import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

public class Reservation {
	@DateTimeFormat(iso=DateTimeFormat.ISO.DATE)
   private LocalDate bookingDate;
   private String bookingNumber;
   private String state;
   private int numberOfPeople;
   private String transactionNumber;
   
   
public Reservation() {
	super();
}

public LocalDate getBookingDate() {
	return bookingDate;
}
public void setBookingDate(LocalDate bookingDate) {
	this.bookingDate = bookingDate;
}
public String getBookingNumber() {
	return bookingNumber;
}
public void setBookingNumber(String bookingNumber) {
	this.bookingNumber = bookingNumber;
}
public String getState() {
	return state;
}
public void setState(String state) {
	this.state = state;
}
public int getNumberOfPeople() {
	return numberOfPeople;
}
public void setNumberOfPeople(int numberOfPeople) {
	this.numberOfPeople = numberOfPeople;
}
public String getTransactionNumber() {
	return transactionNumber;
}
public void setTransactionNumber(String transactionNumber) {
	this.transactionNumber = transactionNumber;
}
@Override
public String toString() {
	return "Reservation [bookingDate=" + bookingDate + ", bookingNumber=" + bookingNumber + ", state=" + state
			+ ", numberOfPeople=" + numberOfPeople + ", transactionNumber=" + transactionNumber + "]";
}
   

}