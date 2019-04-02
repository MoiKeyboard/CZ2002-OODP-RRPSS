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

	protected boolean checkTableVacancy(int tableNo) {
		boolean found = false;
		Table foundTable = null;
		for (Table t : tableAl) {
			if (t.getTableNo() == tableNo) {
				foundTable = t;
				found = true;
				break;
			}
		}
		if (found == false) {
			System.out.println("Table " + tableNo + " not found");
			return false;
		} else if (!"Vaccated".equals(foundTable.getTableStatus())) {
			System.out.println("Table " + tableNo + " is currently " + foundTable.getTableStatus());
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

	protected void modifyTableAl(ArrayList<Table> tableArr) {
		tableAl = tableArr;
	}

}
