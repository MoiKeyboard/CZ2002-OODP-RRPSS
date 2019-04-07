package Entity;

import java.util.ArrayList;

public class Order {
	private int staffId;
	private int tableNo;
	private ArrayList<Menu> foodAL;

	public Order() {
		this.staffId = 0;
		this.tableNo = 0;
		this.foodAL = null;
	}

	public Order(int staffId, int tableNo, ArrayList<Menu> foodAL) {
		this.staffId = staffId;
		this.tableNo = tableNo;
		this.foodAL = foodAL;
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
		orderItemDetails = "Table No: " + getTableNo() + "\nStaff ID: " + getStaffId() + "\n";
		for (Menu menu : foodAL) {
			if(menu instanceof Alacarte)
				orderItemDetails += ((Alacarte)menu).toString();
			else if (menu instanceof PromotionalPackage)
				orderItemDetails += ((PromotionalPackage)menu).toString();
		}
		return orderItemDetails;
	}

}
