package Entity;

import java.util.ArrayList;

public class Order {
	private int orderNo;
	private int staffId;
	private int tableNo;
	private ArrayList<Menu> foodAL;

	public Order() {
		this.orderNo = 0;
		this.staffId = 0;
		this.tableNo = 0;
		this.foodAL = null;
	}

	public Order(int orderNo, int staffId, int tableNo, ArrayList<Menu> foodAL) {
		this.orderNo = orderNo;
		this.staffId = staffId;
		this.tableNo = tableNo;
		this.foodAL = foodAL;
	}

	public int getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(int orderNo) {
		this.orderNo = orderNo;
	}

	public int getStaffId() {
		return staffId;
	}

	public void setStaffId(int staffId) {
		this.staffId = staffId;
	}

	public int getTableNo() {
		return tableNo;
	}

	public void setTableNo(int tableNo) {
		this.tableNo = tableNo;
	}

	public ArrayList<Menu> getFoodAL() {
		return foodAL;
	}

	public void setFoodAL(ArrayList<Menu> foodAL) {
		this.foodAL = foodAL;
	}

	@Override
	public String toString() {
		String orderItemDetails = null;
		orderItemDetails = "Table No: " + getTableNo() + "\nStaff ID: " + getStaffId() + "\nOrder No: " + getOrderNo()
				+ "\n";
		for (Menu menu : foodAL) {
			if(menu instanceof Alacarte)
				orderItemDetails += ((Alacarte)menu).toString();
			else if (menu instanceof PromotionalPackage)
				orderItemDetails += ((PromotionalPackage)menu).toString();
		}
		return orderItemDetails;
	}

}
