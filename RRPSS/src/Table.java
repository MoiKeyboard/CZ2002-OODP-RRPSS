
public class Table {
	private int tableNo;
	private int seatCap;
	private boolean tableStatus;

	public Table(int tableNo, int seatCap) {
		this.tableNo = tableNo;
		this.seatCap = seatCap;
	}
	
	public boolean isOccupied() {
		return tableStatus;
	}
	
}
