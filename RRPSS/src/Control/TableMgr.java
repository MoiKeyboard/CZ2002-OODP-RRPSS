package Control;

import java.util.ArrayList;
import java.util.Scanner;

import Entity.Reservation;
import Entity.Table;

public class TableMgr {
	private ArrayList<Table> tableAl;
	protected Scanner sc;

	public TableMgr() {
		tableAl = new ArrayList<Table>();
		try {
			tableAl = TextDB.readTable("Table.txt");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void printTableList() {
		System.out.println("Table list as follows:");
		for (Table s : tableAl) {
			System.out.println("Table No: " + s.getTableNo());
			System.out.println("Seat capacity: " + s.getSeatCap());
			System.out.println("Table status: " + s.getTableStatus() + "\n");
		}
		System.out.println();
	}

	protected ArrayList<Table> getTableAL() {
		return tableAl;
	}

	protected void updateTableStatus(int tableNo, String status) {
		int index = getTableIndex(tableNo);
		tableAl.get(index).setTableStatus(status);
		System.out.println("Table " + tableNo + " set to " + status);
	}

	protected boolean checkTableVacancy(int tableNo) {
		int index = getTableIndex(tableNo);
		if (!"Vaccated".equals(tableAl.get(index).getTableStatus())) {
			System.out.println("Table " + tableNo + " is currently " + tableAl.get(index).getTableStatus());
			return false;
		} else
			return true;
	}

	protected String getTableStatusForReservation(Reservation r) {
		for (Table tables : tableAl) {
			if (tables.getTableNo() == r.getTableNo()) {
				return tables.getTableStatus();
			}
		}
		return null;
	}

	protected int getTableIndex(int search) {
		for (int i = 0; i < tableAl.size(); i++) {
			if (search == tableAl.get(i).getTableNo())
				return i;
		}
		System.out.println("Table " + search + " not found");
		return -1;
	}

}
