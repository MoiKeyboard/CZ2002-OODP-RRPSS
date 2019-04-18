package Control;

import java.time.LocalDateTime;
import java.util.ArrayList;

import Entity.Reservation;
import Entity.Table;

/**
 * The {@code TableMgr} is a control class used to model all control behavior
 * specific to the {@link Table}.
 * 
 * @author Joseph Fung King Yiu
 * @version 1.0
 * @since 2019-04-17
 */

public class TableMgr {
	private ArrayList<Table> tableAl;

	/**
	 * Constructor for TableMgr, calls {@link TextDB#readTable(String)} and
	 * {@link updateTableStatus(int, String)}.
	 * 
	 * @param reservationMgr Control for ReservationManager
	 */
	public TableMgr(ReservationMgr reservationMgr) {
		tableAl = new ArrayList<Table>();
		try {
			tableAl = TextDB.readTable("Table.txt");
			updateTableStatus(reservationMgr);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Prints the Table List.
	 */
	public void printTableList() {
		System.out.println("Table list as follows:");
		for (Table s : tableAl) {
			System.out.println("Table No: " + s.getTableNo());
			System.out.println("Seat capacity: " + s.getSeatCap());
			System.out.println("Table status: " + s.getTableStatus() + "\n");
		}
		System.out.println();
	}

	/**
	 * Updates Table status to Reserved according to the Reservations for the
	 * particular AM/PM Session on runtime.
	 * 
	 * @param rMgr Control for ReservationMgr
	 */
	protected void updateTableStatus(ReservationMgr rMgr) {
		LocalDateTime today = LocalDateTime.now();
		ArrayList<Reservation> reservationAL = rMgr.getReservationAl();
		for (Reservation r : reservationAL) {
			LocalDateTime dt = r.getReservationDate();
			if (dt.getDayOfYear() == today.getDayOfYear()) {
				// AM SESSION
				if (today.getHour() >= 11 && dt.getHour() >= 11 && today.getHour() <= 15 && dt.getHour() <= 15) {
					int tableNo = r.getTableNo();
					int index = getTableIndex(tableNo);
					tableAl.get(index).setTableStatus("Reserved");
				}
				// PM SESSION
				else if (today.getHour() >= 18 && dt.getHour() >= 18 && today.getHour() <= 22 && dt.getHour() <= 22) {
					int tableNo = r.getTableNo();
					int index = getTableIndex(tableNo);
					tableAl.get(index).setTableStatus("Reserved");
				}
			}

		}
	}

	/**
	 * Returns the ArrayList[Table].
	 */
	protected ArrayList<Table> getTableAL() {
		return tableAl;
	}

	/**
	 * Updates the Table Status for a particular Table.
	 * 
	 * @param tableNo Number of table
	 * @param status  Status of table
	 */
	protected void updateTableStatus(int tableNo, String status) {
		int index = getTableIndex(tableNo);
		tableAl.get(index).setTableStatus(status);
		System.out.println("Table " + tableNo + " set to " + status);
	}

	/**
	 * Checks Table Vacancy for a particular Table.
	 * 
	 * @param tableNo Number of table
	 */
	protected boolean checkTableVacancy(int tableNo) {
		int index = getTableIndex(tableNo);
		if (!"Vaccated".equals(tableAl.get(index).getTableStatus())) {
			System.out.println("Table " + tableNo + " is currently " + tableAl.get(index).getTableStatus());
			return false;
		} else
			return true;
	}

	/**
	 * Assigns an Empty(Vacated) Table according to the number of Pax.
	 * 
	 * @param pax Number of Pax
	 */
	protected int assignTable(int pax) {
		for (Table t : tableAl) {
			if (pax <= t.getSeatCap() && t.getTableStatus().equals("Vacated")) {
				return t.getTableNo();
			}
		}
		System.out.println("All tables that can fit " + pax + " are unavailable right now...");
		return -1;
	}

	// redacted soon???
	protected String getTableStatusForReservation(Reservation r) {
		for (Table tables : tableAl) {
			if (tables.getTableNo() == r.getTableNo()) {
				return tables.getTableStatus();
			}
		}
		return null;
	}

	/**
	 * Returns the Index for a particular table.
	 * 
	 * @param search Number of Table
	 */
	protected int getTableIndex(int search) {
		for (int i = 0; i < tableAl.size(); i++) {
			if (search == tableAl.get(i).getTableNo())
				return i;
		}
		System.out.println("Table " + search + " not found");
		return -1;
	}

}
