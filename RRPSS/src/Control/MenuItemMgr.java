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
			promoPackageAl = TextDB.readPromoPackageItem("PromoPackages.txt");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void printMenuItem() {
		System.out.println("Menu Item as follows:");
		for (MenuItem mi : menuAl) {
			System.out.println(mi.toString());
		}
		System.out.println();
	}

	public void createMenuItem() throws Exception {
		String foodCat, foodName, foodDesc;
		double foodPrice;
		System.out.println("Please enter food category");
		foodCat = sc.nextLine();
		System.out.println("Please enter food name");
		foodName = sc.nextLine();
		System.out.println("Please enter description of food");
		foodDesc = sc.nextLine();
		System.out.println("Please enter price");
		foodPrice = sc.nextDouble();
		MenuItem i1 = new MenuItem(foodCat, foodName, foodDesc, foodPrice);
		menuAl.add(i1);
		TextDB.saveMenuItem("MenuItems.txt", menuAl);
	}

	public void updateMenuItem() throws IOException {
		String searchName;
		int choice;
		boolean found = false;
		MenuItem old;
		System.out.println("Please enter the name of the food item that you want to update");
		searchName = sc.nextLine();

		for (MenuItem mi : menuAl) {
			if (mi.getFoodName().equalsIgnoreCase(searchName)) {
				old = mi;
				found = true;
				do {
					System.out.println("Food category: " + mi.getFoodType());
					System.out.println("Food name: " + mi.getFoodName());
					System.out.println("Description: " + mi.getDescription());
					System.out.println("Price: " + mi.getPrice());
					System.out.println("1) Update food category");
					System.out.println("2) Update food name");
					System.out.println("3) Update description");
					System.out.println("4) Update price");
					System.out.println("5) Exit");
					choice = sc.nextInt();
					switch (choice) {
					case 1:
						System.out.println("Enter updated food category");
						mi.setFoodType(sc.nextLine());
						break;
					case 2:
						System.out.println("Enter updated food name");
						mi.setFoodName(sc.nextLine());
						break;
					case 3:
						System.out.println("Enter updated description");
						mi.setDescription(sc.nextLine());
						break;
					case 4:
						System.out.println("Enter updated price");
						mi.setPrice(sc.nextDouble());
						break;
					case 5:
						break;
					default:
						System.out.println("Invalid input try again");
					}
				} while (sc.nextLine() != "5");
				break;
			}
		}
		if (!found)
			System.out.println("Item " + searchName + " not found");
		TextDB.saveMenuItem("MenuItems.txt", menuAl);
	}

	public void removeMenuItem() throws IOException {
		System.out.println("Please enter the food item that you want to remove");
		String searchName = sc.nextLine();
		for (MenuItem mi : menuAl) {
			if (mi.getFoodName().equalsIgnoreCase(searchName)) {
				menuAl.remove(mi);
				// call updatePromotionalPackage(mi);
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
		// overloaded save file
		TextDB.writePromoPackage("PromoPackages.txt", promoPackageAl);

	}

	public void updatePromotionalPackage() {
		int choice;
		String searchName;
		String searchFood;
		boolean found = false;
		System.out.println("Please enter the name of the promotional package that you want to update");
		searchName = sc.nextLine();

		for (PromotionalPackage pp : promoPackageAl) {
			if (pp.getPromoName().equalsIgnoreCase(searchName)) {
				found = true;
				do {
					System.out.println("Name: " + pp.getPromoName());
					System.out.println("Description: " + pp.getDescription());
					System.out.println("List of items in promotional Package");
					for (MenuItem mi : pp.getMenuItemArr()) {
						System.out.println(mi.getFoodName());
					}
					System.out.println("1) Update promotional package name");
					System.out.println("2) Update promotional package description");
					System.out.println("3) Add items to promotional package");
					System.out.println("4) Remove items from promotional package");
					System.out.println("5) Exit");
					choice = sc.nextInt();
					switch (choice) {
					case 1:
						System.out.println("Enter updated promotional package name");
						pp.setPromoName(sc.nextLine());
						break;
					case 2:
						System.out.println("Enter updated promotional package description");
						pp.setDescription(sc.nextLine());
						break;
					case 3:
						System.out.println("Enter food name to add to promotional package");
						found = false;
						for (MenuItem mi2 : menuAl) {
							if (mi2.getFoodName().equalsIgnoreCase(sc.nextLine())) {
								found = true;
								pp.getMenuItemArr().add(mi2);
							}
						}
						if (!found)
							System.out.println("Menu item not found. Please try again");
						break;
					case 4:
						System.out.println("Enter food name to remove from promotional package");
						found = false;
						searchFood = sc.nextLine();
						for (MenuItem mi : pp.getMenuItemArr()) {
							if (searchName == mi.getFoodName()) {
								pp.getMenuItemArr().remove(mi);
								System.out.println("Remove success");
							}
						}
						if (!found)
							System.out.println("Menu item not found, Please try again");
						break;
					case 5:
						break;
					default:
						System.out.println("Invalid input try again");
					}
				} while (sc.nextLine() != "5");
				break;
			}
		}
		if (!found)
			System.out.println("Promotional Package " + searchName + " not found");
	}

	public void updatePromotionalPackage(MenuItem oldMi, MenuItem newMi) {
		ArrayList<MenuItem> miArr;
		for (PromotionalPackage pp : promoPackageAl) {
			miArr = pp.getMenuItemArr();
			for (MenuItem mi : miArr) {
				if (mi.equals(oldMi))
					mi = newMi;
			}
		}
	}

	public void removePromotionalPackage() {
	}

//	public PromotionalPackage getObject(ArrayList<PromotionalPackage> al, String promoName) {
//		for (PromotionalPackage pp : al) {
//			if (promoName == pp.getPromoName())
//				return pp;
//		}
//		return null;
//	}
}
