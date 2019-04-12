package Control;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import Entity.Menu;
import Entity.Order;

public class OrderMgr {
	private ArrayList<Order> orderAl;
	protected Scanner sc;

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

	public void createOrder(TableMgr tableMgr, MenuMgr menuMgr, PersonMgr personMgr) {
		// updateTableAL tablemgr
		boolean success;
		System.out.println("Create order for Walk in or Reservation?");
		System.out.println("1) Walk in");
		System.out.println("2) Reservation");
		int input = Integer.parseInt(sc.nextLine());

		if (input == 1) {
			success = walkInOrder(tableMgr, menuMgr, personMgr);
		} else if (input == 2)
			success = reservationOrder(tableMgr, menuMgr, personMgr);
		else
			System.out.println("Invalid input, please try again");
	}

	private boolean walkInOrder(TableMgr tableMgr, MenuMgr menuMgr, PersonMgr personMgr) {
		// Staff
		System.out.println("Please enter staff ID: ");
		int staffInput = Integer.parseInt(sc.nextLine());
		if (personMgr.getStaffIndex(staffInput) == -1)
			return false;

		// Check table availability and auto assign table
		System.out.println("Please enter pax size: ");
		int tableNo = tableMgr.assignTable(Integer.parseInt(sc.nextLine()));
		if (tableNo == -1)
			return false;

		// all menu manager to create list
		ArrayList<Menu> foodAL = new ArrayList<Menu>();
		foodAL = menuMgr.updateMenuAL(foodAL);

		// Instantiate new order
		orderAl.add(new Order(staffInput, tableNo, foodAL));
		tableMgr.updateTableStatus(tableNo, "Occupied");
		System.out.println("Creation of order is successful!");
		return true;
	}

	private boolean reservationOrder(TableMgr tableMgr, MenuMgr menuMgr, PersonMgr personMgr) {
		System.out.println("Please enter staff ID: ");
		int staffInput = Integer.parseInt(sc.nextLine());
		if (personMgr.getStaffIndex(staffInput) == -1)
			return false;

	}

//delete reservation after creating order
	// branch methods for resercation and normal order

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

	protected int getOrderIndex(int search) {
		for (int i = 0; i < orderAl.size(); i++) {
			if (search == orderAl.get(i).getTableNo())
				return i;
		}
		System.out.println("Table " + search + " has no order");
		return -1;
	}

	protected ArrayList<Order> getOrderAL() {
		return this.orderAl;
	}
}
