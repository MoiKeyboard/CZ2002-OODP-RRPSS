package Control;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

import Entity.Customer;
import Entity.Reservation;
import Entity.Table;

/**
 * (Control) Object wrapper for ReservationMgr
 * 
 * @author Joseph Fung King Yiu
 * @version 1.0
 * @since 2019-04-17
 */

public class ReservationMgr {
	private ArrayList<Reservation> reservationAl;
	private ArrayList<Table> tableAl;
	private ArrayList<Customer> custAl;
	private Scanner sc;

	public ReservationMgr() {
		reservationAl = new ArrayList<Reservation>();
		sc = new Scanner(System.in);
		try {
			reservationAl = TextDB.readReservation("Reservations.txt");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected ArrayList<Reservation> getReservationAl() {
		return reservationAl;
	}

	public void printReservation(TableMgr tMgr, PersonMgr pMgr) {
		tableAl = tMgr.getTableAL();
		custAl = pMgr.getCustAl();
		System.out.println("Reservation as follows:");
		for (Reservation mi : reservationAl) {
			Customer cust = pMgr.findCustomer(mi.getContactNo());
			System.out.println(cust.toString());
			System.out.println(mi.toString());
			System.out.println("Table status: " + tMgr.getTableStatusForReservation(mi) + "\n");
		}
		System.out.println();
	}

	public void createReservation() throws Exception {
		String reservationDT, custName;
		int contactNo, pax;
		boolean successFlag = false;
		System.out.println("Please enter phone number");
		contactNo = sc.nextInt();
		sc.nextLine();
		System.out.println("Please enter name");
		custName = sc.nextLine();
		System.out.println("Please enter reservation date and time in 24hour clock format (E.g 10-04-19 1200)");
		reservationDT = sc.nextLine();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yy kkmm");
		LocalDateTime reservationDateTime = LocalDateTime.parse(reservationDT, formatter);
		if (reservationDateValidation(contactNo, reservationDateTime) == false)
			return;
		System.out.println("Please enter number of pax");
		pax = sc.nextInt();
		for (Table t : tableAl) {
			if (t.getSeatCap() == pax && (checkAvailability(t, reservationDateTime) == true)) {
				Reservation i1 = new Reservation(contactNo, reservationDateTime, pax, t.getTableNo());
				Customer customer = new Customer(custName, contactNo);
				t.setTableStatus("Reserved");
				reservationAl.add(i1);
				custAl.add(customer);
				System.out.println("Successfully reserved a table");
				successFlag = true;
				break;
			}
		}
		if (successFlag == false) {
			for (Table t2 : tableAl) {
				if (t2.getSeatCap() > pax && (checkAvailability(t2, reservationDateTime) == true)) {
					Reservation i1 = new Reservation(contactNo, reservationDateTime, pax, t2.getTableNo());
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
		if (successFlag == false) {
			System.out.println("Sorry! All tables for the number of pax that you requested are fully booked!!");
			return;
		}
		TextDB.saveReservations("Reservations.txt", reservationAl, custAl);
	}

	public void checkReservation(TableMgr tMgr, PersonMgr pMgr) {
		int contactNo;
		System.out.println("Please enter contact Number to search for reservation");
		contactNo = sc.nextInt();
		for (Reservation r : reservationAl) {
			if (r.getContactNo() == contactNo) {
				Customer cust = pMgr.findCustomer(contactNo);
				System.out.println(cust.toString());
				System.out.println(r.toString());
				System.out.println("Table status: " + tMgr.getTableStatusForReservation(r) + "\n");
			}
		}
	}

	private boolean reservationDateValidation(int contactNum, LocalDateTime reservationDT) {
		LocalDateTime today = LocalDateTime.now();
		LocalDateTime existingReservationDT;
		int newReservationHour = reservationDT.getHour();
		int newReservationMinute = reservationDT.getMinute();
		int existingReservationHour, existingReservationMinute;
		for (Reservation r : reservationAl) {
			if (r.getContactNo() == contactNum) {
				existingReservationDT = r.getReservationDate();
				existingReservationHour = existingReservationDT.getHour();
				existingReservationMinute = existingReservationDT.getMinute();
				if (existingReservationDT.getDayOfYear() == reservationDT.getDayOfYear()) {
					if ((((existingReservationHour >= 11) && (existingReservationHour <= 14))
							|| ((existingReservationHour == 15) && (existingReservationMinute == 0)))
							&& (((newReservationHour >= 11) && (newReservationHour <= 14))
									|| ((newReservationHour == 15) && (newReservationMinute == 0)))) {
						System.out.println(
								"Existing reservation with the same contact number on the same day and same AM Session");
						return false;
					} else if ((((existingReservationHour >= 18) && (existingReservationHour <= 21))
							|| ((existingReservationHour == 22) && (existingReservationMinute == 0)))
							&& (((newReservationHour >= 18) && (newReservationHour <= 21))
									|| ((newReservationHour == 22) && (newReservationMinute == 0)))) {
						System.out.println(
								"Existing reservation with the same contact number on the same day and same PM session");
						return false;
					}
				}
			}
		}
		if ((reservationDT.getDayOfYear() - today.getDayOfYear()) > 30) {
			System.out.println("Reservation can only be made at most 1 month(30 days) in advance.\n");
			return false;
		} else if (((((newReservationHour >= 11) && (newReservationHour <= 14))
				|| ((newReservationHour == 15) && (newReservationMinute == 0)))) != true
				&& (((newReservationHour >= 18) && (newReservationHour <= 21))
						|| ((newReservationHour == 22) && (newReservationMinute == 0))) != true) {
			System.out.println(
					"Restaurant is open only from 11:00 am - 15:00 pm (AM Session) and from 18:00 pm - 22:00pm (PM Session).\n");
			return false;
		}
		return true;
	}

	private boolean checkAvailability(Table t, LocalDateTime reservationDT) {
		LocalDateTime existingReservationDT;
		int existingReservationHour, existingReservationMinute, newReservationHour, newReservationMinute;
		for (Reservation r : reservationAl) {
			if (r.getTableNo() == t.getTableNo()) {
				existingReservationDT = r.getReservationDate();
				existingReservationHour = existingReservationDT.getHour();
				existingReservationMinute = existingReservationDT.getMinute();
				newReservationHour = reservationDT.getHour();
				newReservationMinute = reservationDT.getMinute();
				if (existingReservationDT.getDayOfYear() == reservationDT.getDayOfYear()) {
					if ((((existingReservationHour >= 11) && (existingReservationHour <= 14))
							|| ((existingReservationHour == 15) && (existingReservationMinute == 0)))
							&& (((newReservationHour >= 11) && (newReservationHour <= 14))
									|| ((newReservationHour == 15) && (newReservationMinute == 0)))) {
						return false;
					} else if ((((existingReservationHour >= 18) && (existingReservationHour <= 21))
							|| ((existingReservationHour == 22) && (existingReservationMinute == 0)))
							&& (((newReservationHour >= 18) && (newReservationHour <= 21))
									|| ((newReservationHour == 22) && (newReservationMinute == 0)))) {
						return false;
					}
				}
			}
		}
		return true;
	}

	public void removeReservation(PersonMgr pMgr) throws IOException {
		System.out.println("Please enter the contact number used for the reservation that you want to remove");
		int searchTerm = sc.nextInt();
		for (Reservation mi : reservationAl) {
			if (mi.getContactNo() == searchTerm) {
				for (Table t : tableAl) {
					if (t.getTableNo() == mi.getTableNo()) {
						t.setTableStatus("Vacated");
					}
				}
				reservationAl.remove(mi);
				break;
			}
		}
		pMgr.removeCustomer(searchTerm);
		TextDB.saveReservations("Reservations.txt", reservationAl, custAl);
	}

	public void removeExpiredReservations() {
		LocalDateTime existingReservationDT, expiringDT;
		int existingReservationDay;
		LocalDateTime today = LocalDateTime.now();
		Iterator<Reservation> it = reservationAl.iterator();
		while (it.hasNext()) {
			Reservation r = it.next();
			existingReservationDT = r.getReservationDate();
			existingReservationDay = existingReservationDT.getDayOfYear();
			expiringDT = today.minus(10, ChronoUnit.MINUTES);
			if (expiringDT.getDayOfYear() == existingReservationDT.getDayOfYear()
					&& expiringDT.getHour() == existingReservationDT.getHour()
					&& expiringDT.getMinute() == existingReservationDT.getMinute()) {
				if (r.isAttended() != true) {
					System.out.println("Removing Reservation:\n" + r.toString());
					it.remove();
				}
			}
		}
		try {
			TextDB.saveReservations("Reservations.txt", reservationAl, custAl);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected int getReservationIndex(int contactNo) {
		LocalDateTime today = LocalDateTime.now();
		for (int i = 0; i < reservationAl.size(); i++) {
			LocalDateTime dt = reservationAl.get(i).getReservationDate();
			// AM SESSION
			if (reservationAl.get(i).getContactNo() == contactNo && today.getHour() >= 11 && dt.getHour() >= 11
					&& today.getHour() <= 15 && dt.getHour() <= 15) {
				return i;
			}
			// PM SESSION
			else if (reservationAl.get(i).getContactNo() == contactNo && today.getHour() >= 18 && dt.getHour() >= 18
					&& today.getHour() <= 22 && dt.getHour() <= 22) {
				return i;
			}
		}
		System.out.println("Reservation not found. Please try again.");
		return -1;
	}

}
