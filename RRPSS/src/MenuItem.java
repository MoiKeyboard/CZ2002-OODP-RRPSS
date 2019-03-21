import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class MenuItem {
	private String foodType;
	private String foodName;
	private String description;
	private double price;
	
	public MenuItem() {
		foodType = "";
		this.foodName = "";
		this.description = "";
		this.price = 0;
	}
	
	public MenuItem(String foodType, String foodName,String description, double price){
		this.foodType = foodType;
		this.foodName = foodName;
		this.description = description;
		this.price = price;
	}

	public String getFoodName() {
		return foodName;
	}

	public String getFoodType() {
		return foodType;
	}

	public void setFoodType(String foodType) {
		this.foodType = foodType;
	}

	public void setFoodName(String foodName) {
		this.foodName = foodName;
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
	
	public static void printMenuItem(ArrayList<MenuItem> menuAl) {
		System.out.println("Menu Item as follows:");
		for(MenuItem mi : menuAl) {
			System.out.println("Category: " + mi.getFoodType());
			System.out.println("Food Name: " + mi.getFoodName());
			System.out.println("Description: " + mi.getDescription());
			System.out.println("Price: " + mi.getPrice() + "\n");
		}
		System.out.println();
	}
	
	public static void createMenuItem(Scanner sc, ArrayList<MenuItem> menuAl) throws Exception {
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

	public static void updateMenuItem(Scanner sc, ArrayList<MenuItem> menuAl) throws IOException {
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
	
	public static void removeMenuItem(Scanner sc, ArrayList<MenuItem> menuAl) throws IOException {
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
