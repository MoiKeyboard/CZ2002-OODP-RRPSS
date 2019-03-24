package Control;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import Entity.MenuItem;
import Entity.PromotionalPackage;

public class MenuItemMgr {
	private ArrayList<MenuItem> menuAl;
	private ArrayList<PromotionalPackage> promoPackageAl;
	protected Scanner sc;
	
	public MenuItemMgr() {
		menuAl = new ArrayList<MenuItem>();
		promoPackageAl = new ArrayList<PromotionalPackage>();
		sc = new Scanner(System.in);
		try {
			menuAl = TextDB.readMenuItem("MenuItems.txt");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public void printMenuItem() {
		System.out.println("Menu Item as follows:");
		for(MenuItem mi : menuAl) {
			System.out.println(mi.toString());
		}
		System.out.println();
	}
	
	public void createMenuItem() throws Exception {
		String foodCat, foodName,foodDesc;
		double foodPrice;
		System.out.println("Please enter food category");
		foodCat = sc.nextLine();
		System.out.println("Please enter food name");
		foodName = sc.nextLine();
		System.out.println("Please enter description of food");
		foodDesc = sc.nextLine();
		System.out.println("Please enter price");
		foodPrice = sc.nextDouble();
		MenuItem i1 = new MenuItem(foodCat,foodName,foodDesc,foodPrice);
		menuAl.add(i1);
		TextDB.saveMenuItem("MenuItems.txt", menuAl);
	}

	public void updateMenuItem() throws IOException {
		String searchName;
		String foodCat, foodName,foodDesc;
		double foodPrice;
		System.out.println("Please enter the food item that you want to update");
		searchName = sc.nextLine();
		for(MenuItem mi : menuAl) {
			if(mi.getFoodName().equalsIgnoreCase(searchName)) {
				menuAl.remove(mi);
				System.out.println("Please enter the new food category");
				foodCat = sc.nextLine();
				System.out.println("Please enter new food name");
				foodName = sc.nextLine();
				System.out.println("Please enter new description of food");
				foodDesc = sc.nextLine();
				System.out.println("Please enter new price");
				foodPrice = sc.nextDouble();
				menuAl.add(new MenuItem(foodCat,foodName,foodDesc,foodPrice));
				break;
			}
		}
		TextDB.saveMenuItem("MenuItems.txt", menuAl);
		
	}
	
	public void removeMenuItem() throws IOException {
		System.out.println("Please enter the food item that you want to remove");
		String searchName = sc.nextLine();
		for(MenuItem mi : menuAl) {
			if(mi.getFoodName().equalsIgnoreCase(searchName)) {
				menuAl.remove(mi);
				break;
			}
		}
		TextDB.saveMenuItem("MenuItems.txt", menuAl);
		
	}
	
	public void createPromotionalPackage() throws IOException {
		String promoName;
		String promoDesc;
		boolean found;
		ArrayList<MenuItem> promoPackageItems = new ArrayList<MenuItem>();

		System.out.println("Please enter new promotional package name");
		promoName = sc.nextLine();
		System.out.println("Please enter description for " + promoName);
		promoDesc = sc.nextLine();
		System.out.println("Please enter food name to add to " + promoName + " (enter 0 to finish adding)");
		while (sc.nextLine() != "0") {
			found = false;
			for (MenuItem mi : menuAl) {
				if (mi.getFoodName().equalsIgnoreCase(sc.nextLine())) {
					found = true;
					promoPackageItems.add(mi);
				}
			}
			if (!found)
				System.out.println("Menu item not found. Please try again");
		}
		if (promoPackageItems.isEmpty()) {
			System.out.println("New promotional package not added (no menu items selected)");
		} else {
			PromotionalPackage p1 = new PromotionalPackage(promoName, promoDesc, promoPackageItems);
			promoPackageAl.add(p1);
		}

		// save new promo name/desc/menuAL
	}
	
	public void updatePromotionalPackage(){
	}
	
	public void updatePromotionalPackage(MenuItem mi){
	//overloaded method to remove specific menuitem from all promotionalpackage
	}
	
	public void removePromotionalPackage(){
	}
}
