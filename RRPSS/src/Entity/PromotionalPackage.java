package Entity;

import java.util.ArrayList;

public class PromotionalPackage {
	private String promoName;
	private String description;
	private ArrayList<MenuItem> menuItemArr;

	public PromotionalPackage() {
		this.promoName = null;
		this.description = null;
		menuItemArr = new ArrayList<MenuItem>();
	}

	public PromotionalPackage(String promoName, String description, ArrayList<MenuItem> menuItemsArr) {
		this.promoName = promoName;
		this.description = description;
		this.menuItemArr = menuItemsArr;
	}

	public String getPromoName() {
		return promoName;
	}

	public void setPromoName(String promoName) {
		this.promoName = promoName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public ArrayList<MenuItem> getMenuItemArr() {
		return menuItemArr;
	}

	public void setMenuItemArr(ArrayList<MenuItem> menuItemArr) {
		this.menuItemArr = menuItemArr;
	}
	
	@Override
	public String toString() {
		String promoDetails = null;
		promoDetails = "Promo Name: " + getPromoName() + "\nDescription: " + getDescription() + "\n";
		for(MenuItem mi : menuItemArr) {
			promoDetails += mi.toString();
		}
		return promoDetails;
	}
}