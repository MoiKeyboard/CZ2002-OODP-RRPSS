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

	public void createOrder(TableMgr tableMgr, MenuMgr menuMgr, PersonMgr personMgr, ReservationMgr reservationMgr) {

		int orderNo, tableInput, staffInput;
		ArrayList<Menu> foodAL = null;
		String foodInput;

		orderNo = generateOrderNumber();
		System.out.println("Please enter staff ID: ");
		staffInput = Integer.parseInt(sc.next());
		// check staff id exists
//		if (checkDuplicateOrderNumber(orderNo) == false)
//			return;
		System.out.println("Please enter table No: ");
		tableInput = Integer.parseInt(sc.next());
		if (tableMgr.checkTableVacancy(tableInput) == false)
			return;

		do {

			int foodQty;
			int foodIndex;
			Menu foodObj = null;

			System.out.println("Please enter food/promotional package name to add to order (enter 0 to finish adding)");
			foodInput = sc.nextLine();
			System.out.println("Please enter quanity of " + foodInput);
			foodQty = Integer.parseInt(sc.nextLine());

			// retrieve foodobj
			// foodObj = menuMgr.getMenu(foodInput);

			if (foodObj != null) {
				while (foodQty > 0) {
					foodAL.add(foodObj);
					foodQty--;
				}
			}
		} while (!"0".equals(foodInput));

		orderAl.add(new Order(orderNo, staffInput, tableInput, foodAL));
		tableMgr.updateTableStatus(tableInput, "Occupied");

		System.out.println("Creation of order is successful!");

	}

	// View order of the current session
	public void viewOrder() throws IOException {
		int index;
		System.out.println("Please enter table number");
		index = getOrderIndex(sc.nextInt());
		System.out.println(orderAl.get(index).toString());
	}

	public void updateOrder(MenuMgr menuMgr) {
		int index, input = -1;
		System.out.println("Please enter table number");
		index = getOrderIndex(sc.nextInt());
		ArrayList<Menu> currFoodAL = orderAl.get(index).getFoodAL();
		do {
			System.out.println(orderAl.get(index).toString());
			System.out.println("1) Add order item(s)");
			System.out.println("2) Remove order item(s)");
			System.out.println("3) Finish updating order");
			input = Integer.parseInt(sc.nextLine());

			String foodInput;
			int qtyInput, foodIndex;
			switch (input) {
			case 1:
				System.out.println("Enter foodname you want to add");
				foodInput = sc.nextLine();
				System.out.println("Please enter quanity of " + foodInput);
				qtyInput = Integer.parseInt(sc.nextLine());
				foodIndex = menuMgr.getIndex(foodInput);
				if (foodIndex != -1) {
					while (qtyInput > 0) {
						currFoodAL.add(menuMgr.getMenuAl().get(foodIndex));
						qtyInput--;
					}
				}
				break;
			case 2:
				int removeQty = 0;
				System.out.println("Enter foodname you want to remove");
				foodInput = sc.nextLine();
				System.out.println("Please enter quanity of " + foodInput);
				qtyInput = Integer.parseInt(sc.nextLine());
				for (Menu m : currFoodAL) {
					if (foodInput.equalsIgnoreCase(m.getName()) && qtyInput != 0) {
						currFoodAL.remove(m);
						qtyInput--;
						removeQty++;
					}
				}
				System.out.println(foodInput + " " + qtyInput);
				break;
			default:
				System.out.println("Invalid input please try again");
				break;
			}
		} while (input != 3);
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
		System.out.println("Order no" + search + " not found");
		return -1;
	}
}
