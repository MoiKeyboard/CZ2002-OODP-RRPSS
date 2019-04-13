package Entity;

import java.io.Serializable;
import java.util.ArrayList;

public class PromotionalPackage extends Menu implements Serializable {
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
		int qty = 0;
		Alacarte a = new Alacarte();
		promoDetails = "Promo Name: " + getName() + "\nDescription: " + getDescription() + "\nPromo Price: "
				+ getPrice() + "\n";
		for (Alacarte mi : menuItemArr) {
			if (qty == 0) {
				a = mi;
				qty++;
			} else if (mi.equals(a)) {
				qty++;
			} else {
				System.out.println("Quantity of " + qty);
				promoDetails += mi.toString();
				qty = 0;
			}

		}
		return promoDetails;
	}
	
	@Override
	public boolean equals(Object o) {
		
		
		if (o instanceof PromotionalPackage) {
			PromotionalPackage comparatorObject = (PromotionalPackage) o;
			
			
			if (this.getName().equalsIgnoreCase(comparatorObject.getName())
					&& this.getDescription().equalsIgnoreCase(comparatorObject.getDescription())
					&& this.getPrice() == comparatorObject.getPrice()) {

				return true;
			}
			
			
		}
	
		return false;
	}
	
	
	

	
	
}