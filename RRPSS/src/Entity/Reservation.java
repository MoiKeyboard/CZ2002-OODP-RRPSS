package Entity;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 * The {@code Reservation} entity class is an object wrapper.
 * <p>
 * Contains primitive information related to a reservation.
 * </p>
 *
 */
public class Reservation {
	private int contactNo; // primary key
	private LocalDateTime reservationDateTime;
	private int pax, tableNo;

	/**
	 * Default constructor for {@code Reservation}
	 */
	public Reservation() {
		this.contactNo = 0;
		this.reservationDateTime = null;
		this.pax = 0;
		this.tableNo = 0;
	}

	/**
	 * Constructor for {@code Reservation}. Creates a {@code Reservation} object
	 * with required parameters.
	 * 
	 * @param contactNo           Contact number used for {@code Reservation}
	 * @param reservationDateTime Date-time of {@code Reservation}
	 * @param pax                 Amount of customer on {@code Reservation}
	 * @param tableNo             Table number allocated for {@code Reservation}
	 */
	public Reservation(int contactNo, LocalDateTime reservationDateTime, int pax, int tableNo) {
		this.contactNo = contactNo;
		this.reservationDateTime = reservationDateTime;
		this.pax = pax;
		this.tableNo = tableNo;
	}

	/**
	 * Returns contact number of {@code Reservation}
	 * 
	 * @return the integer value of contactNo
	 */
	public int getContactNo() {
		return contactNo;
	}

	/**
	 * Returns date-time of {@code Reservation}
	 * 
	 * @return the {@link LocalDateTime} of reservationDate
	 */
	public LocalDateTime getReservationDate() {
		return reservationDateTime;
	}

	/**
	 * Returns amount customers in a particular {@code Reservation}.
	 * 
	 * @return the integer value of pax
	 */
	public int getPax() {
		return pax;
	}

	/**
	 * Returns table number allocated for {@code Reservation} object.
	 * 
	 * @return the integer value of tableNo
	 */
	public int getTableNo() {
		return tableNo;
	}


	/**
	 * Returns the string resepresentation of the {@code Reservation} object.
	 */
	@Override
	public String toString() {
		String reservationDetails = null;
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yy kkmm", Locale.ENGLISH);
		reservationDetails = "Reservation Date/Time: " + getReservationDate().format(formatter) + "\nPax: " + getPax();
		return reservationDetails;
	}
}
