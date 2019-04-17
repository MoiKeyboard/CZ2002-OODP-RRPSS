package Control;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import Entity.Menu;
import Entity.Order;

/**
 * (Control) Object wrapper for OrderMgr
 * 
 * @author Qwek Zhi Hui
 * @version 1.0
 * @since 2019-04-17
 */

public class OrderMgr {
	private ArrayList<Order> orderAl;
	private Scanner sc;
	
	/**
	 * Constructor for OrderMgr.
	 */
	public OrderMgr() {
		orderAl = new ArrayList<Order>();
		sc = new Scanner(System.in);
	}
	
	/**
	 * Asks User for Input if it is Walk In Case or Reservation Case and calls either {@link walkInOrder(TableMgr, MenuMgr, PersonMgr)} OR {@link reservationOrder(TableMgr, MenuMgr, PersonMgr, ReservationMgr)} accordingly.
	 * 
	 * @param tableMgr Control for TableMgr
	 * @param menuMgr Control for MenuMgr
	 * @param personMgr Control for PersonMgr
	 * @param rMgr Control for ReservationMgr
	 */
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

	/**
	 * Creates Order for WalkIn cases. Calls {@link menuMgr#updateMenuAl(ArrayList)} AND {@link TableMgr#updateTableStatus(int, String)}.
	 * 
	 * @param tableMgr Control for TableMgr
	 * @param menuMgr Control for MenuMgr
	 * @param personMgr Control for PersonMgr
	 */
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

	/**
	 * Creates Order for Reservation cases. Calls {@link menuMgr#updateMenuAl(ArrayList)} AND {@link TableMgr#updateTableStatus(int, String)} AND {@link ReservationMgr#getReservationAl()}.
	 * 
	 * @param tableMgr Control for TableMgr
	 * @param menuMgr Control for MenuMgr
	 * @param personMgr Control for PersonMgr
	 * @param rMgr Control for ReservationMgr
	 */
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

	/**
	 *  Prints out the Order for a particular Table.
	 */
	public void viewOrder() throws IOException {
		int index;
		System.out.println("Please enter table number");
		index = getOrderIndex(sc.nextInt());
		if (index == -1)
			return;
		System.out.println(orderAl.get(index).toString());
	}

	/**
	 * Calls {@link MenuMgr#updateMenuAL(ArrayList)} to update the Order for a particular table.
	 * 
	 * @param menuMgr Control for MenuMgr
	 */
	public void updateOrder(MenuMgr menuMgr) {
		int index;
		System.out.println("Please enter table number");
		index = getOrderIndex(sc.nextInt());
		if (index == -1)
			return;

		ArrayList<Menu> currFoodAL = orderAl.get(index).getFoodAL();
		menuMgr.updateMenuAL(currFoodAL);
	}

	/**
	 * Removes Order for a particular Table and sets the status of Table to be vacated.
	 * 
	 * @param tableMgr Control for TableMgr
	 */
	public void removeOrder(TableMgr tableMgr) {
		int index;
		System.out.println("Please enter table number");
		index = getOrderIndex(sc.nextInt());
		if (index != -1) {
			orderAl.remove(index);
			System.out.println("Order remove successfully");
			tableMgr.updateTableStatus(index, "Vacated");
		}
	}

	/**
	 * Returns Index of Order in ArrayList[Order] based on Table Number Input.
	 * 
	 * @param search Number of Table
	 */
	protected int getOrderIndex(int search) {
		for (int i = 0; i < orderAl.size(); i++) {
			if (search == orderAl.get(i).getTableNo())
				return i;
		}
		System.out.println("Table " + search + " has no order");
		return -1;
	}

	/**
	 * Returns ArrayList[Order]
	 */
	protected ArrayList<Order> getOrderAL() {
		return this.orderAl;
	}
}
