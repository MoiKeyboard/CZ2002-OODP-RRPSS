package Control;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import Entity.MenuItem;
import Entity.Order;
import Entity.PromotionalPackage;

public class OrderMgr {
	private ArrayList<Order> orderAl;
	protected Scanner sc;
	
	public OrderMgr() {
		orderAl = new ArrayList<Order>();
		sc = new Scanner(System.in);
		try {
			// read text file for order items
			orderAl = TextDB.readOrder("Orders.txt");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void viewOrder() throws IOException{
		int tableInput = 0, orderNo = 0, staffId = 0, tableNo = 0;
		System.out.println("Please enter table number");
		tableInput = sc.nextInt();
		for (Order orderView : orderAl) {
			if(tableInput == orderView.getTableNo()) {
				//System.out.println(tableInput.toString());
				/*orderNo = orderView.getOrderNo();
				staffId = orderView.getStaffId();
				tableNo = orderView.getTableNo();
				*/
			}
		}
		/*System.out.println("Order No: " + orderNo);
		System.out.println("Table No: " + tableNo);
		System.out.println("Staff ID: " + staffId);
		for (Order or : orderAl) {
			if(tableInput == or.getTableNo()) {
				System.out.println(or.toString());
			}
		}
		System.out.println();
		*/
	}
	
	
	
}
