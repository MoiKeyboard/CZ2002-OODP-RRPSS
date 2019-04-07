package Entity;

import java.util.ArrayList;

public class PromotionalPackage extends Menu {
	private ArrayList<Alacarte> menuItemArr;

	public PromotionalPackage() {
		super();
		this.menuItemArr = new ArrayList<Alacarte>();
	}

	public PromotionalPackage(String promoName, String description, double price, ArrayList<Alacarte> menuItemsArr) {
		super(promoName, description, price);
		this.menuItemArr = menuItemsArr;
	}

	public ArrayList<Alacarte> getMenuItemArr() {
		return menuItemArr;
	}

	public void setAlacarteArr(ArrayList<Alacarte> menuItemArr) {
		this.menuItemArr = menuItemArr;
	}

	@Override
	public String toString() {
		String promoDetails = null;
		promoDetails = "Promo Name: " + getName() + "\nDescription: " + getDescription() + "\nPromo Price: "
				+ getPrice() + "\n";
		for (Alacarte mi : menuItemArr) {
			promoDetails += mi.toString();
		}
		return promoDetails;
	}
}