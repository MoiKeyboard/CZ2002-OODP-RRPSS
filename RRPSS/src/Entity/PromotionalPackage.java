package Entity;

import java.util.ArrayList;

public class PromotionalPackage extends MenuItem {
	private String promoName;
	private String description;
	private double price;
	private ArrayList<Alacarte> menuItemArr;

	public PromotionalPackage() {
		this.promoName = null;
		this.description = null;
		this.price = 0;
		this.menuItemArr = new ArrayList<Alacarte>();
	}

	public PromotionalPackage(String promoName, String description, double price, ArrayList<Alacarte> menuItemsArr) {
		this.promoName = promoName;
		this.description = description;
		this.price = price;
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

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public ArrayList<Alacarte> getMenuItemArr() {
		return menuItemArr;
	}

	public void setMenuItemArr(ArrayList<Alacarte> menuItemArr) {
		this.menuItemArr = menuItemArr;
	}

	@Override
	public String toString() {
		String promoDetails = null;
		promoDetails = "Promo Name: " + getPromoName() + "\nDescription: " + getDescription() + "\n";
		for (Alacarte mi : menuItemArr) {
			promoDetails += mi.toString();
		}
		return promoDetails;
	}
}