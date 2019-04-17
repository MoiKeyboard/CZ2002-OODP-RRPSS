package Entity;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class Reservation {
	private int contactNo; // primary key
	private LocalDateTime reservationDateTime;
	private int pax, tableNo;
	private boolean attended;

	public Reservation() {
		this.contactNo = 0;
		this.reservationDateTime = null;
		this.pax = 0;
		this.tableNo = 0;
	}

	public Reservation(int contactNo, LocalDateTime reservationDateTime, int pax, int tableNo) {
		this.contactNo = contactNo;
		this.reservationDateTime = reservationDateTime;
		this.pax = pax;
		this.tableNo = tableNo;
	}

	public int getContactNo() {
		return contactNo;
	}

	public LocalDateTime getReservationDate() {
		return reservationDateTime;
	}

	public int getPax() {
		return pax;
	}

	public int getTableNo() {
		return tableNo;
	}

	public boolean isAttended() {
		return attended;
	}

	public void setAttended(boolean attended) {
		this.attended = attended;
	}

	@Override
	public String toString() {
		String reservationDetails = null;
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yy kkmm", Locale.ENGLISH);
		reservationDetails = "Reservation Date/Time: " + getReservationDate().format(formatter) + "\nPax: " + getPax();
		return reservationDetails;
	}
}
