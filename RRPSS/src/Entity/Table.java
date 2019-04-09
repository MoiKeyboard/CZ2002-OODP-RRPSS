package Entity;

public class Table {
	private int tableNo;
	private int seatCap;
	private String tableStatus;

	public Table() {
		this.tableNo = 0;
		this.seatCap = 0;
		this.tableStatus = null;
	}

	public Table(int tableNo, int seatCap, String tableStatus) {
		this.tableNo = tableNo;
		this.seatCap = seatCap;
		this.tableStatus = tableStatus;
	}

	public int getTableNo() {
		return tableNo;
	}

	public int getSeatCap() {
		return seatCap;
	}

	public String getTableStatus() {
		return tableStatus;
	}

	public void setTableStatus(String status) {
		tableStatus = status;
	}

	@Override
	public String toString() {
		String tableDetails = null;
		tableDetails = "Table No: " + getTableNo() + "\nSeat Capacity: " + getSeatCap() + "\nTable status"
				+ getTableStatus() + "\n";
		return tableDetails;
	}
}
