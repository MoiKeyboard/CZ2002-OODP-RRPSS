package Entity;

import java.util.ArrayList;

public class Order {
	private int staffId;
	private int tableNo;
	private ArrayList<MenuItem> orderAlaCarteItemAl;
	private ArrayList<PromotionalPackage> orderPromoPackageAl;
	private int itemQuantity;
	private int orderNo;
	
	public Order(){
		this.orderNo = 0;
		this.staffId = 0;
		this.tableNo = 0;
		this.orderAlaCarteItemAl = null;
		this.orderPromoPackageAl = null;
		this.itemQuantity = 0;
		
	}
	public Order(int orderNo, int staffId, int tableNo, ArrayList<MenuItem> orderAlaCarteItemAl,ArrayList<PromotionalPackage> orderPromoPackageAl, int itemQuantity) {
		this.orderNo = orderNo;
		this.staffId = staffId;
		this.tableNo = tableNo;
		this.orderAlaCarteItemAl =orderAlaCarteItemAl;
		this.orderPromoPackageAl = orderPromoPackageAl;
		this.itemQuantity = itemQuantity;
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
	
	public int getItemQuantity() {
		return itemQuantity;
	}
	
	public void setItemQuantity(int itemQuantity) {
		this.itemQuantity = itemQuantity;
	}
	
	public ArrayList<MenuItem> getAlaCarteOrderItem() {
		return orderAlaCarteItemAl;
	}
	
	public void setOrderAlaCarteItem(MenuItem orderAlaCarteItem) {
		orderAlaCarteItemAl.add(orderAlaCarteItem);
	}

	public ArrayList<PromotionalPackage> getPromoPackage() {
		return orderPromoPackageAl;
	}
	
	public void setOrderPromoItems(PromotionalPackage orderPromoItem) {
		orderPromoPackageAl.add(orderPromoItem);
	}
	
	@Override
	public String toString() {
		String orderItemDetails = null;
		//orderItemDetails = "Table No: " + getTableNo() + "\nStaff ID: " + getStaffId() + "\nOrder No: " + getOrderNo() + "Order item: " + getOrderItem() + "\nQuantity: " + getItemQuantity();
		return orderItemDetails;
	}
	
}
