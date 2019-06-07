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
   double pricePerPerson;
   double totalPrice;
   String nid;
   int idActivity;
   
public Reservation() {
	super();
}

public LocalDate getBookingDate() {
	return bookingDate;
}

public void setBookingDate(LocalDate date) {
	this.bookingDate = date;
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

public double getPricePerPerson() {
	return pricePerPerson;
}

public void setPricePerPerson(double pricePerPerson) {
	this.pricePerPerson = pricePerPerson;
}

public double getTotalPrice() {
	return totalPrice;
}

public void setTotalPrice(double totalPrice) {
	this.totalPrice = totalPrice;
}

public String getNid() {
	return nid;
}

public void setNid(String nid) {
	this.nid = nid;
}

public int getIdActivity() {
	return idActivity;
}

public void setIdActivity(int idActivity) {
	this.idActivity = idActivity;
}

@Override
public String toString() {
	return "Reservation [bookingDate=" + bookingDate + ", bookingNumber=" + bookingNumber + ", state=" + state
			+ ", numberOfPeople=" + numberOfPeople + ", transactionNumber=" + transactionNumber + ", pricePerPerson="
			+ pricePerPerson + ", totalPrice=" + totalPrice + ", nid=" + nid + ", idActivity=" + idActivity + "]";
}



}