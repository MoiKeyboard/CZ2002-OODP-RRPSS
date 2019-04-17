package Control;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import Entity.Menu;
import Entity.Order;

public class OrderMgr {
	private ArrayList<Order> orderAl;
	private Scanner sc;

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

	public void createOrder(TableMgr tableMgr, MenuMgr menuMgr, PersonMgr personMgr, ReservationMgr rMgr) {
		// update tableAL on runtime via tableMgr
		tableMgr.updateTableStatus(rMgr);

		boolean success = false;
		System.out.println("Create order for Walk in or Reservation?");
		System.out.println("1) Walk in");
		System.out.println("2) Reservation");
		int input = Integer.parseInt(sc.nextLine());

		if (input == 1) {
			success = walkInOrder(tableMgr, menuMgr, personMgr);
		} else if (input == 2)
			success = reservationOrder(tableMgr, menuMgr, personMgr, rMgr);
		else
			System.out.println("Invalid input, please try again");
		if (!success)
			System.out.println();
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

		// Create food list through menuMgr
		ArrayList<Menu> foodAL = new ArrayList<Menu>();
		foodAL = menuMgr.updateMenuAL(foodAL);

		// Instantiate new order
		orderAl.add(new Order(staffInput, tableNo, foodAL));
		tableMgr.updateTableStatus(tableNo, "Occupied");
		System.out.println("Creation of order is successful!");
		return true;
	}

	private boolean reservationOrder(TableMgr tableMgr, MenuMgr menuMgr, PersonMgr personMgr, ReservationMgr rMgr) {
		// Staff
		System.out.println("Please enter staff ID: ");
		int staffInput = Integer.parseInt(sc.nextLine());
		if (personMgr.getStaffIndex(staffInput) == -1)
			return false;

		// Retrieve tableNo via reservationMgr
		System.out.println("Enter reservation phone number");
		int contactNo = Integer.parseInt(sc.nextLine());
		int reservationIndex = rMgr.getReservationIndex(contactNo);
		if (reservationIndex == -1)
			return false;
		int tableNo = rMgr.getReservationAl().get(reservationIndex).getTableNo();

		// Create food list via menuMgr
		ArrayList<Menu> foodAL = new ArrayList<Menu>();
		foodAL = menuMgr.updateMenuAL(foodAL);

		// Instantiate new order
		orderAl.add(new Order(staffInput, tableNo, foodAL));
		// Update table status
		tableMgr.updateTableStatus(tableNo, "Occupied");
		// Remove reservation via rMgr
		rMgr.getReservationAl().remove(reservationIndex);

		System.out.println("Creation of order is successful!");
		return true;
	}

	// View order of the current session
	public void viewOrder() throws IOException {
		int index;
		String display = "";
		System.out.println("Please enter table number");
		index = getOrderIndex(sc.nextInt());
		if (index == -1)
			return;
		//System.out.println(orderAl.get(index).toString());
		display += "Viewing order at Table "+orderAl.get(index).getTableNo()+"...";
		display+="\n------------------------------------------\n";
		ArrayList<Menu> uniqueList = new ArrayList<Menu>();
		ArrayList<Integer> eachCount = new ArrayList<Integer>();
		ArrayList<Menu> foodAL = new ArrayList<Menu>();
		
		foodAL = orderAl.get(index).getFoodAL();
		
		for (Menu menu : foodAL) {
			
			if (uniqueList.contains(menu)) {
				eachCount.set(uniqueList.indexOf(menu), eachCount.get(uniqueList.indexOf(menu)) + 1);
			} else {
				uniqueList.add(menu);
				eachCount.add(1);

			}
		}
		
		for(int i = 0; i < uniqueList.size(); i++) {
			display += String.format("Qty: %-5d \n%s\n", eachCount.get(i), uniqueList.get(i).toString());
		}
		
		System.out.println(display);
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

	public void removeOrder(TableMgr tableMgr) {
		System.out.println("Please enter table number");
		int tableNo = sc.nextInt();
		removeOrder(tableMgr, tableNo);
	}

	protected void removeOrder(TableMgr tableMgr, int tableNo) {
		int tableIndex = tableMgr.getTableIndex(tableNo);
		int orderIndex = getOrderIndex(tableNo);
		if (orderIndex != -1 && tableIndex != -1) {
			orderAl.remove(orderIndex);
			System.out.println("Order remove successfully");
			tableMgr.updateTableStatus(tableNo, "Vacated");
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
