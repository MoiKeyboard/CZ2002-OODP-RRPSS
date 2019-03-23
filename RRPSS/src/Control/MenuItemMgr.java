package Control;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import Entity.MenuItem;

public class MenuItemMgr {
	private ArrayList<MenuItem> menuAl;
	protected Scanner sc;
	
	public MenuItemMgr() {
		menuAl = new ArrayList<MenuItem>();
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
			System.out.println("Category: " + mi.getFoodType());
			System.out.println("Food Name: " + mi.getFoodName());
			System.out.println("Description: " + mi.getDescription());
			System.out.println("Price: " + mi.getPrice() + "\n");
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
}
