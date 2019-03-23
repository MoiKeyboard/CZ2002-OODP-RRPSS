package Entity;
public class Reservation {
	private int contactNo; // primary key
	private String reservationDate;
	private String reservationTime;
	private int pax;
	private String tableStatus;
	
	public Reservation(){
		this.contactNo = 0;
		this.reservationDate = "";
		this.reservationTime = "";
		this.pax = 0;
		this.tableStatus = "";
	}
	
	public Reservation(int contactNo, String reservationDate, String reservationTime,int pax, String tableStatus){
		this.contactNo = contactNo;
		this.reservationDate = reservationDate;
		this.reservationTime = reservationTime;
		this.pax = pax;
		this.tableStatus = tableStatus;
	}

	public int getContactNo() {
		return contactNo;
	}

	public String getReservationDate() {
		return reservationDate;
	}

	public String getReservationTime() {
		return reservationTime;
	}

	public int getPax() {
		return pax;
	}

	public String getTableStatus() {
		return tableStatus;
	}

	public static void reserveTable() {
		
	}
}
