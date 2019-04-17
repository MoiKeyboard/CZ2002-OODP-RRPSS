package Entity;

/**
 * The {@code Table} entity class is an object wrapper.
 * <p>
 * Contains primitive information related to a table.
 * </p>
 * 
 * @author Qwek Zhi Hui
 * @version 1.0
 * @since 2019-04-13
 */
public class Table {
	private int tableNo;
	private int seatCap;
	private String tableStatus;

	/**
	 * Default constructor for {@code Table}.
	 */
	public Table() {
		this.tableNo = 0;
		this.seatCap = 0;
		this.tableStatus = null;
	}

	/**
	 * Constructor for {@code Table}. Creates a {@code Table} object with required
	 * parameters.
	 * 
	 * @param tableNo     Table identification number of {@code Table}
	 * @param seatCap     Seat capacity of {@code Table}
	 * @param tableStatus Current status of {@code Table}
	 */
	public Table(int tableNo, int seatCap, String tableStatus) {
		this.tableNo = tableNo;
		this.seatCap = seatCap;
		this.tableStatus = tableStatus;
	}

	/**
	 * Returns the table identification number of {@code Table} object.
	 * 
	 * @return the integer value of tableNo
	 */
	public int getTableNo() {
		return tableNo;
	}

	/**
	 * Returns the seating capacity of {@code Table} object.
	 * 
	 * @return the integer value of seatCap
	 */
	public int getSeatCap() {
		return seatCap;
	}

	/**
	 * Returns the current status of {@code Table} object.
	 * 
	 * @return the String of tableStatus
	 */
	public String getTableStatus() {
		return tableStatus;
	}

	/**
	 * Updates the current status of {@code Table} object.
	 * 
	 * @param status the updated tableStatus String
	 */
	public void setTableStatus(String status) {
		tableStatus = status;
	}

	/**
	 * Returns the string resepresentation of the {@code Table} object.
	 */
	@Override
	public String toString() {
		String tableDetails = null;
		tableDetails = "Table No: " + getTableNo() + "\nSeat Capacity: " + getSeatCap() + "\nTable status"
				+ getTableStatus() + "\n";
		return tableDetails;
	}
}
