package Entity;
	
public class Order {
	private int staffId;
	private int tableNo;
	//private MenuItem menuItem;
	//private PromotionalPackage promoItem;
	private String orderItem;
	private int itemQuantity;
	private double itemPrice;
	private int orderNo;
	
	public Order(){
		this.orderNo = 0;
		this.staffId = 0;
		this.tableNo = 0;
		this.orderItem = "";
		this.itemQuantity = 0;
		this.itemPrice = 0;
		
	}
	public Order(int orderNo, int staffId, int tableNo, String orderItem, int itemQuantity, double itemPrice) {
		this.orderNo = orderNo;
		this.staffId = staffId;
		this.tableNo = tableNo;
		this.orderItem = orderItem;
		this.itemQuantity = itemQuantity;
		this.itemPrice = itemPrice;
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
	/*public MenuItem getMenuItem() {
		return menuItem;
	}
	public void setMenuItem(MenuItem menuItem) {
		this.menuItem = menuItem;
	}
	public PromotionalPackage getPromoItem() {
		return promoItem;
	}
	public void setPromoItem(PromotionalPackage promoItem) {
		this.promoItem = promoItem;
	}*/
	public int getItemQuantity() {
		return itemQuantity;
	}
	public void setItemQuantity(int itemQuantity) {
		this.itemQuantity = itemQuantity;
	}
	public double getItemPrice() {
		return itemPrice;
	}
	public void setItemPrice(double itemPrice) {
		this.itemPrice = itemPrice;
	}
	
	public String getOrderItem() {
		return orderItem;
	}
	public void setOrderItem(String orderItem) {
		this.orderItem = orderItem;
	}
	
	@Override
	public String toString() {
		String orderItemDetails = null;
		orderItemDetails = "Order item: " + getOrderItem() + "\nQuantity: " + getItemQuantity() + "\nPrice: " + getItemPrice() + "\n";
		return orderItemDetails;
	}

/*
	public void setTableOccupied(int tableNo) {
//set status to occupied
		
	}

	public void printInvoice(int tableNo) {
//set table to vacant
	}*/
	
	//public void () {
		
	//}
}
