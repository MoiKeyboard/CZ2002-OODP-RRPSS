import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class ReservationMainTestApp {
	
	public static void main(String[] args) {
		ReservationMainTestApp test = new ReservationMainTestApp();
		ArrayList<MenuItem> menuAL = new ArrayList<MenuItem>();
		ArrayList<Staff> staffAl = new ArrayList<Staff>();
		Scanner sc = new Scanner(System.in);
		test.run(sc,menuAL, staffAl);
		sc.close();
		
	}
	
	public void run(Scanner sc, ArrayList<MenuItem> menuAl, ArrayList<Staff> staffAl) {
		int userInput;
		readFromAllFiles(menuAl);
		do {
			printAppMenu();
			userInput = getUserInput(sc,menuAl);
			
		} while (userInput != 0 );
		System.out.println("Thanks for using our app!");
	}
	
	public void printAppMenu() {
		System.out.println("1.\tCreate menu item");
		System.out.println("2.\tUpdate menu item");
		System.out.println("3.\tRemove menu item");
		System.out.println("4.\tCreate promotion");
		System.out.println("5.\tUpdate promotion");
		System.out.println("6.\tRemove promotion");
		System.out.println("7.\tCreate Order");
		System.out.println("8.\tView Order");
		System.out.println("9.\tAdd order item/s to/from order");
		System.out.println("10.\tRemove order item/s	to/from order");
		System.out.println("11.\tCreate reservation booking");
		System.out.println("12.\tCheck/Remove reservation booking");
		System.out.println("13.\tCheck table availability");
		System.out.println("14.\tPrint bill invoice");
		System.out.println("15.\tPrint sale revenue report by period(e.g day/month)\n0.\tExit the application");
	}
	
	public int getUserInput(Scanner sc,ArrayList<MenuItem> menuAl) {
		double input2;
		int input = sc.nextInt();
		sc.nextLine();
		try {
			if (input == 0) return input;
			switch(input) {
				case 1:
					createMenuItem(sc,menuAl);
					break;
				case 2:
					updateMenuItem(sc,menuAl);
					break;
				case 3:
					removeMenuItem(sc,menuAl);
					break;		
				default:
					break;
				
			}
		} catch (Exception e) {
			e.getStackTrace();
		}
		return input;
	}
	
	public void createMenuItem(Scanner sc, ArrayList<MenuItem> menuAl) throws Exception {
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
	
	public void updateMenuItem(Scanner sc, ArrayList<MenuItem> menuAl) throws IOException {
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
	
	public void removeMenuItem(Scanner sc, ArrayList<MenuItem> menuAl) throws IOException {
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
	
	public void readFromAllFiles(ArrayList<MenuItem> menuAl) {
		try {
			TextDB.readMenuItem("MenuItems.txt", menuAl);
			printMenuItem(menuAl);
		} catch (Exception e) {
			e.getStackTrace();
		}
	}
	
	public void printMenuItem(ArrayList<MenuItem> menuAl) {
		for(MenuItem mi : menuAl) {
			System.out.println("Category: " + mi.getFoodType());
			System.out.println("Food Name: " + mi.getFoodName());
			System.out.println("Description: " + mi.getDescription());
			System.out.println("Price: " + mi.getPrice());
		}
	}
}
