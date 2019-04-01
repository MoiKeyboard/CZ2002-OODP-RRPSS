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
