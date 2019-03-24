package Control;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

import Entity.Customer;
import Entity.MenuItem;
import Entity.Reservation;
import Entity.Table;

public class ReservationMgr {
	private ArrayList<Reservation> reservationAl;
	private ArrayList<Table> tableAl;
	private ArrayList<Customer> custAl;
	private TableMgr tMgr;
	private CustomerMgr cMgr;
	protected Scanner sc;
	
	public ReservationMgr() {
		tMgr = new TableMgr();
		cMgr = new CustomerMgr();
		reservationAl = new ArrayList<Reservation>();
		tableAl = tMgr.getTableAl();
		custAl = cMgr.getCustAl();
		sc = new Scanner(System.in);
		try {
			reservationAl = TextDB.readReservation("Reservations.txt");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	protected ArrayList<Reservation> getReservationAl(){
		return reservationAl;
	}
	
	public void printReservation() {
		System.out.println("Reservation as follows:");
		for(Reservation mi : reservationAl) {
			Customer cust = cMgr.findCustomer(mi.getContactNo());
			System.out.println(cust.toString());
			System.out.println(mi.toString());
			System.out.println("Table status: " + tMgr.getTableStatusForReservation(mi) + "\n");
		}
		System.out.println();
	}
	
	public void createReservation() throws Exception {
		String reservationDT, custName;
		int contactNo,pax,tableNo;
		boolean successFlag = false,reservationExists = false;
		System.out.println("Please enter phone number");
		contactNo = sc.nextInt();
		sc.nextLine();
		reservationExists = checkReservation(contactNo);
		if (reservationExists == true) {
			System.out.println("Reservation already exists");
			return;
		}
		System.out.println("Please enter name");
		custName = sc.nextLine();
		System.out.println("Please enter reservation date and time in 24hour clock format (E.g 10-06-19 1200)");
		reservationDT = sc.nextLine();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yy kkmm");
		LocalDateTime reservationDateTime = LocalDateTime.parse(reservationDT, formatter);
		System.out.println("Please enter number of pax");
		pax = sc.nextInt(); 
		for(Table t : tableAl) {
			if(t.getSeatCap() == pax && t.getTableStatus().equalsIgnoreCase("vacated")) {
				Reservation i1 = new Reservation(contactNo,reservationDateTime,pax,t.getTableNo());
				Customer customer = new Customer(custName, contactNo);
				t.setTableStatus("Reserved");
				reservationAl.add(i1);
				custAl.add(customer);
				System.out.println("Successfully reserved a table");
				successFlag = true;
				break;
			}
		}
		if(successFlag == false) {
			for(Table t2 : tableAl) {
				if(t2.getSeatCap() > pax && t2.getTableStatus().equalsIgnoreCase("vacated")) {
					Reservation i1 = new Reservation(contactNo,reservationDateTime,pax,t2.getTableNo());
					Customer customer = new Customer(custName, contactNo);
					t2.setTableStatus("Reserved");
					reservationAl.add(i1);
					custAl.add(customer);
					System.out.println("Successfully reserved a table");
					successFlag = true;
					break;
				}
			}
		}
		if(successFlag == false) { 
			System.out.println("Restaurant is fully booked!");
			return;
		}
		TextDB.saveReservations("Reservations.txt", reservationAl, tableAl, custAl);
	}
	
	public boolean checkReservation(int contactNo) {
		boolean reservationExists = false;
		for (Reservation r: reservationAl) {
			if(r.getContactNo() == contactNo) {
				reservationExists = true;
			}
		}
		return reservationExists;
	}

	public void checkReservation() {
		int contactNo;
		System.out.println("Please enter contact Number to search for reservation");
		contactNo = sc.nextInt();
		for (Reservation r: reservationAl) {
			if(r.getContactNo() == contactNo) {
				Customer cust = cMgr.findCustomer(contactNo);
				System.out.println(cust.toString());
				System.out.println(r.toString());
				System.out.println("Table status: " + tMgr.getTableStatusForReservation(r) + "\n");
			}
		}
	}
	
	public void removeReservation() throws IOException {
		System.out.println("Please enter the contact number used for the reservation that you want to remove");
		int searchTerm = sc.nextInt();
		for(Reservation mi : reservationAl) {
			if(mi.getContactNo() == searchTerm) {
				for(Table t : tableAl) {
					if(t.getTableNo() == mi.getTableNo()) {
						t.setTableStatus("Vacated");
					}
				}
				reservationAl.remove(mi);
				break;
			}
		}
		for(Customer cust : custAl){{
			if(cust.getContactNumber() == searchTerm) {
				custAl.remove(cust);
				break;
			}
		}
		TextDB.saveReservations("Reservations.txt", reservationAl, tableAl, custAl);
		}
	}
}
