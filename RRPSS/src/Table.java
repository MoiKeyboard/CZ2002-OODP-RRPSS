import java.util.ArrayList;

public class Table {
	private int tableNo;
	private int seatCap;
	private boolean tableStatus;
	//private ArrayList<Reservation> reservationAl;
	//private ArrayList<Table> tableAl;

	public Table() {
		this.tableNo = 9999;
		this.seatCap = 0;
		this.tableStatus = false;
	}
	
	public Table(int tableNo,int seatCap, boolean tableStatus) {
		this.tableNo = tableNo;
		this.seatCap = seatCap;
		this.tableStatus = tableStatus;
	}

	public Table(int tableNo, int seatCap) {
		this.tableNo = tableNo;
		this.seatCap = seatCap;
		this.tableStatus = false;
	}
	
	public int getTableNo() {
		return tableNo;
	}

	public int getSeatCap() {
		return seatCap;
	}

	public boolean isOccupied() {
		return tableStatus;
	}
	
	public static void printTableList(ArrayList<Table> tableAl) {
		System.out.println("Table list as follows:");
		for(Table s : tableAl) {
			System.out.println("Table No: " + s.getTableNo());
			System.out.println("Seat capacity: " + s.getSeatCap());
			if (s.isOccupied() == true)
				System.out.println("Table status: Occupied\n");
			else 
				System.out.println("Table status: Empty\n");
		}
		System.out.println();
	}
}
