package Control;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import Entity.Menu;
import Entity.Order;

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
			e.printStackTrace();
		}
	}

	public void createOrder(TableMgr tableMgr, MenuMgr menuMgr, PersonMgr personMgr, ReservationMgr reservationMgr) {

		int orderNo, tableInput, staffInput;
		ArrayList<Menu> foodAL = null;
		String foodInput;

		orderNo = generateOrderNumber();
		System.out.println("Please enter staff ID: ");
		staffInput = Integer.parseInt(sc.next());
		// Check staff
		if (personMgr.getStaffIndex(staffInput) == -1)
			return;
		System.out.println("Please enter table No: ");
		// Check table
		tableInput = Integer.parseInt(sc.next());
		if (tableMgr.checkTableVacancy(tableInput) == false)
			return;
		// Call menu manager to create list
		menuMgr.updateMenuAL(foodAL);

		// Instantiate new order
		orderAl.add(new Order(orderNo, staffInput, tableInput, foodAL));
		tableMgr.updateTableStatus(tableInput, "Occupied");

		System.out.println("Creation of order is successful!");

	}

	// View order of the current session
	public void viewOrder() throws IOException {
		int index;
		System.out.println("Please enter table number");
		index = getOrderIndex(sc.nextInt());
		if (index == -1)
			return;
		System.out.println(orderAl.get(index).toString());
	}

	public void updateOrder(MenuMgr menuMgr) {
		int index;
		System.out.println("Please enter table number");
		index = getOrderIndex(sc.nextInt());
		if (index == -1)
			return;

		ArrayList<Menu> currFoodAL = orderAl.get(index).getFoodAL();
		menuMgr.updateMenuAL(currFoodAL);
	}

	public void removeOrder() {
		int index;
		System.out.println("Please enter table number");
		index = getOrderIndex(sc.nextInt());
		if (index != -1) {
			orderAl.remove(index);
			System.out.println("Order remove successfully");
		}
	}

	public void printInvoice() {

		// Remove reservation tied to that particular table ID in the current session
		// Change table to vacated

	}

	public int generateOrderNumber() {
		int i = orderAl.size() + 1;
		for (Order o : orderAl) {
			if (i == o.getOrderNo())
				i++;
		}
		return i;
	}

	public int getOrderIndex(int search) {
		for (int i = 0; i < orderAl.size(); i++) {
			if (search == orderAl.get(i).getOrderNo())
				return i;
		}
		System.out.println("Table " + search + " has no order");
		return -1;
	}

	public ArrayList<Order> getOrderAL() {
		return this.orderAl;
	}
}
