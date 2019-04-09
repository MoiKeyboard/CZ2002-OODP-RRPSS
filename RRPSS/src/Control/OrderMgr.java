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

		int tableInput, staffInput;
		ArrayList<Menu> foodAL = new ArrayList<Menu>();
		System.out.println("Please enter staff ID: ");
		staffInput = Integer.parseInt(sc.nextLine());
		// Check staff
		if (personMgr.getStaffIndex(staffInput) == -1)
			return;
		System.out.println("Please enter table No: ");
		// Check table
		tableInput = Integer.parseInt(sc.nextLine());
		// Call menu manager to create list
		foodAL = menuMgr.updateMenuAL(foodAL);
		// Instantiate new order
		orderAl.add(new Order(staffInput, tableInput, foodAL));
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
