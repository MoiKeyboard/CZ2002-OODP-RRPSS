package Control;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import Entity.Menu;
import Entity.Order;

/**
 * The {@code OrderMgr} is a control class used to model all control behavior
 * specific to the {@link Order}.
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
	 * Asks User for Input if it is Walk In Case or Reservation Case and calls
	 * either {@link walkInOrder(TableMgr, MenuMgr, PersonMgr)} OR
	 * {@link reservationOrder(TableMgr, MenuMgr, PersonMgr, ReservationMgr)}
	 * accordingly.
	 * 
	 * @param tableMgr  Control for TableMgr
	 * @param menuMgr   Control for MenuMgr
	 * @param personMgr Control for PersonMgr
	 * @param rMgr      Control for ReservationMgr
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
	 * Creates {@link Order} object for WalkIn cases. Calls the following methods
	 * <ul>
	 * <li>{@link menuMgr#updateMenuAl(ArrayList)} to populate list of food in
	 * {@link Order}
	 * <li>{@link TableMgr#updateTableStatus(int, String)} to set table status to
	 * occupied upon {@link Order} creation.
	 * 
	 * @param tableMgr  Control for TableMgr
	 * @param menuMgr   Control for MenuMgr
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
	 * Creates {@link Order} object for Reservation cases. Calls the following
	 * methods
	 * <ul>
	 * <li>{@link ReservationMgr#getReservationAl()} to obtain reservation details.
	 * <li>{@link menuMgr#updateMenuAl(ArrayList)} to populate list of food in
	 * {@link Order}.
	 * <li>{@link TableMgr#updateTableStatus(int, String)} to set table status to
	 * occupied upon {@link Order} creation.
	 * 
	 * 
	 * @param tableMgr  Control for TableMgr
	 * @param menuMgr   Control for MenuMgr
	 * @param personMgr Control for PersonMgr
	 * @param rMgr      Control for ReservationMgr
	 * @throws IOException
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
		rMgr.removeReservation(personMgr, contactNo);

		System.out.println("Creation of order is successful!");
		return true;
	}

	/**
	 * Prints out the {@link Order} for a particular Table.
	 */
	public void viewOrder() throws IOException {
		int index;
		String display = "";
		System.out.println("Please enter table number");
		index = getOrderIndex(Integer.parseInt(sc.nextLine()));
		if (index == -1)
			return;
		// System.out.println(orderAl.get(index).toString());
		display += "Viewing order at Table " + orderAl.get(index).getTableNo() + "...";
		display += "\n------------------------------------------\n";
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

		for (int i = 0; i < uniqueList.size(); i++) {
			display += String.format("Qty: %-5d \n%s\n", eachCount.get(i), uniqueList.get(i).toString());
		}

		System.out.println(display);
	}

	/**
	 * Calls {@link MenuMgr#updateMenuAL(ArrayList)} to update the {@link Order} for
	 * a particular table.
	 * 
	 * @param menuMgr Control for MenuMgr
	 */
	public void updateOrder(MenuMgr menuMgr) {
		int index;
		System.out.println("Please enter table number");
		index = getOrderIndex(Integer.parseInt(sc.nextLine()));
		if (index == -1)
			return;

		ArrayList<Menu> currFoodAL = orderAl.get(index).getFoodAL();
		menuMgr.updateMenuAL(currFoodAL);
	}

	/**
	 * Removes {@link Order} for a particular Table and sets the status of Table to
	 * be vacated.
	 * 
	 * @param tableMgr Control for TableMgr
	 */
	public void removeOrder(TableMgr tableMgr) {
		System.out.println("Please enter table number");
		int tableNo = Integer.parseInt(sc.nextLine());
		removeOrder(tableMgr, tableNo);
	}

	/**
	 * 
	 * @param tableMgr
	 * @param tableNo
	 */
	protected void removeOrder(TableMgr tableMgr, int tableNo) {
		int tableIndex = tableMgr.getTableIndex(tableNo);
		int orderIndex = getOrderIndex(tableNo);
		if (orderIndex != -1 && tableIndex != -1) {
			orderAl.remove(orderIndex);
			System.out.println("Order remove successfully");
			tableMgr.updateTableStatus(tableNo, "Vacated");
		}
	}

	/**
	 * Returns Index of {@link Order} in {@code ArrayList<Order>} based on Table
	 * Number Input.
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
	 * Returns current list of orders, orderAl ({@code ArrayList<Order>}
	 */
	protected ArrayList<Order> getOrderAL() {
		return this.orderAl;
	}
}
