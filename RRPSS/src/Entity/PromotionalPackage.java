package Entity;

import java.io.Serializable;
import java.util.ArrayList;

public class PromotionalPackage extends Menu implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3L;
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
		String display = "";
		int index = 0;
		int count = 0;
		Alacarte a = new Alacarte();
		
		ArrayList<Alacarte> uniqueList = new ArrayList<Alacarte>();
		ArrayList<Integer> eachCount = new ArrayList<Integer>();
		
				
		for (Alacarte mi : menuItemArr) {
			if(uniqueList.contains(mi)) {
				index = uniqueList.indexOf(mi);
				count = eachCount.get(index)+1;
				eachCount.set(index, count);
			}
			
			else {
				uniqueList.add(mi);
				eachCount.add(1);
			}
			
		}
		
		display = String.format("%-10s : %-22s %5.2f\n","PROMO", getName(), getPrice());
		promoDetails = display;
		promoDetails += "******************************************\n";
		for (int i = 0; i < uniqueList.size(); i++) {
			//System.out.println("[DEBUG]: "+uniqueList.get(i).getName()+"\n");
			display = String.format("%-5d %s\n", eachCount.get(i), uniqueList.get(i).getName());
			promoDetails += display;

		}
		promoDetails += "******************************************\n";
		
		
		
		return promoDetails;
	}
	
	@Override
	public boolean equals(Object o) {
		
		ArrayList<Alacarte> coMenuItemArr = new ArrayList <Alacarte>();
		int count = 0;
		Menu coMenu;
		boolean flag = true;
		if (o instanceof PromotionalPackage) {
			PromotionalPackage comparatorObject = (PromotionalPackage) o;
			if (this.getName().equalsIgnoreCase(comparatorObject.getName())
					&& this.getDescription().equalsIgnoreCase(comparatorObject.getDescription())
					&& this.getPrice() == comparatorObject.getPrice()) {
				//now check further if each Alacarte menu item in its array is equal to coMenuItemArr's
				coMenuItemArr = comparatorObject.getMenuItemArr();
				for (Menu menu: this.getMenuItemArr()) {
					coMenu = coMenuItemArr.get(count);
					if(!menu.equals(coMenu)) {
						flag = false;
						break;
					}
					else {
						count++;
					}
				}
				return flag;
			}
		}
	
		return false;
	}
	
	
	

	
	
}