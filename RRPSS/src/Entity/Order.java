package Entity;

import java.util.ArrayList;

public class Order {
	private int orderNo;
	private int staffId;
	private int tableNo;
	private ArrayList<Menu>;
	private ArrayList<Alacarte> orderAlaCarteItemAl;
	private ArrayList<PromotionalPackage> orderPromoPackageAl;
	
	
	public Order(){
		this.orderNo = 0;
		this.staffId = 0;
		this.tableNo = 0;
		this.orderAlaCarteItemAl = null;
		this.orderPromoPackageAl = null;
		
	}
	public Order(int orderNo, int staffId, int tableNo, ArrayList<Alacarte> orderAlaCarteItemAl,ArrayList<PromotionalPackage> orderPromoPackageAl) {
		this.orderNo = orderNo;
		this.staffId = staffId;
		this.tableNo = tableNo;
		this.orderAlaCarteItemAl =orderAlaCarteItemAl;
		this.orderPromoPackageAl = orderPromoPackageAl;
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
	
	public ArrayList<Alacarte> getAlaCarteOrderItem() {
		return orderAlaCarteItemAl;
	}
	
	public void setOrderAlaCarteItem(Alacarte orderAlaCarteItem) {
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
		orderItemDetails = "Table No: " + getTableNo() + "\nStaff ID: " + getStaffId() + "\nOrder No: " + getOrderNo() + "\n";
		for(Alacarte alaCarteItem : orderAlaCarteItemAl) {
			orderItemDetails += alaCarteItem.toString() + alaCarteItem.getAlaCarteQty();
		}
		for(PromotionalPackage promoItem : orderPromoPackageAl) {
			orderItemDetails += promoItem.toString() + promoItem.getPromoPkgQty();
		}
		return orderItemDetails;
	}
	
}
