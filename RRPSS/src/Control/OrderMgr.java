package Control;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import Entity.Alacarte;
import Entity.Customer;
import Entity.Order;
import Entity.PromotionalPackage;
import Entity.Staff;
import Entity.Table;

public class OrderMgr {
	private ArrayList<Order> orderAl;
	protected Scanner sc;
	private static final double GST = 0.07; // CONSTANT GST 7%
	private static final double serviceCharge = 0.1; // CONSTANT SERVICE CHARGE 10%

	public OrderMgr() {
		orderAl = new ArrayList<Order>();
		sc = new Scanner(System.in);
		try {
			// read text file for order items
			// orderAl = TextDB.readOrder("Orders.txt");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void createOrder(TableMgr tableMgr, MenuMgr menuMgr, PersonMgr personMgr, ReservationMgr reservationMgr) {
		// Retrieve object by reference on runtime
		ArrayList<Alacarte> alacarteAL = menuMgr.getAlacarteAL();
		ArrayList<PromotionalPackage> promotionalPackageAL = menuMgr.getPromotionalPackageAL();
		ArrayList<Table> tableAL = tableMgr.getTableAL();
		ArrayList<Staff> staffAL = personMgr.getStaffAL();
		ArrayList<Customer> customerAL = personMgr.getCustomerAL();
		

		
		int tableNo;
		int staffId;
		ArrayList<Menu> 
		Order o1;
		

		System.out.println("Please enter table No: ");

		int input = 0;
		System.out.println("Please enter table No: ");
		int tableID = Integer.parseInt(sc.next());
		// Should also add in checks if the order exists for the same table number
		// Check the reservation which is tied to the table No that staff entered and
		// set the boolean attended to true for the particular reservation
		if (findTableID(tableID) != true) {
			System.out.println("Table not found!");
			return;
		}
		do {
			System.out.println("1.) Add Promotional Set");
			System.out.println("2.) Add AlaCarte Item");
			System.out.println("5.) Exit");
			input = Integer.parseInt(sc.next());
			switch (input) {
			case (1):
				System.out.println("Enter 0 when you are done adding");
				// should print out all the promotion package here and the staff could just
				// select the index and enter quantity
				System.out.println("Please enter quantity of promo package");
				int promoPkgQty = Integer.parseInt(sc.next());
				// PromotionalPackage promoPkg = new PromotionalPackage(packageSelected);
				// promoPkg.setPromoPkgQty(promoPkgQty);
				// promoAl.add(promoPkg);
				break;
			case (2):
				System.out.println("Enter 0 when you are done adding");
				// should print out all the ala carte menu items here and the staff could just
				// select the index and enter quantity
				System.out.println("Please enter quantity of ala carte item");
				int alaCarteQty = Integer.parseInt(sc.next());
				// Alacarte alaCarteItem = new Alacarte(alaCarteItemSelected);
				// alaCarteItem.setAlaCarteQty(alaCarteItem);
				// alaCarteAl.add(alaCarteItem);
				break;
			case (5):
				break;
			default:
				System.out.println("Invalid input!");
				break;
			}
		} while (input != 5);
		// set the table flag to occupied
		// Order newOrder = new Order(tableID,staffID,alaCarteAl,promoAl);
		// orderAl.add(newOrder);
		System.out.println("Creation of order is successful!");

	}

	// View order of the current session
	public void viewOrder() throws IOException {
		int tableInput = 0;
		System.out.println("Please enter table number");
		tableInput = sc.nextInt();
		for (Order orderView : orderAl) {
			if (tableInput == orderView.getTableNo()) {
				System.out.println(orderView.toString());
			}
		}
	}

	public void updateOrder() {

	}

	public void removeOrder() {

	}

	public void printInvoice() {
		// Remove reservation tied to that particular table ID in the current session
		// Change table to vacated

	}

	public boolean findTableID(int tableNo) {
		boolean found = false;
		for (Table t : tableAl) {
			if (t.getTableNo() == tableNo) {
				found = true;
				break;
			}
		}
		return found;
	}
}
