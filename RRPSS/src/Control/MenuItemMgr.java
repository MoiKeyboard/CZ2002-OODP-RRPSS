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
		if (getMIIndex(menuAl, foodName) != -1) {
			System.out.println("Existing food name found. Please try again");
			return;
		}
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
		int choice = 0, index;
		MenuItem oldMI;
		System.out.println("Please enter the name of the food item that you want to update");
		searchName = sc.nextLine();

		index = getMIIndex(menuAl, searchName);
		if (index == -1)
			System.out.println("Food not found");
		else {
			oldMI = menuAl.get(index);
			while (choice != 5) {
				System.out.println(menuAl.get(index).toString());
				System.out.println("1) Update food category");
				System.out.println("2) Update food name");
				System.out.println("3) Update description");
				System.out.println("4) Update price");
				System.out.println("5) Exit");
				choice = sc.nextInt();
				switch (choice) {
				case 1:
					System.out.println("Enter updated food category");
					menuAl.get(index).setFoodType(sc.nextLine());
					break;
				case 2:
					System.out.println("Enter updated food name");
					menuAl.get(index).setFoodName(sc.nextLine());
					break;
				case 3:
					System.out.println("Enter updated description");
					menuAl.get(index).setDescription(sc.nextLine());
					break;
				case 4:
					System.out.println("Enter updated price");
					menuAl.get(index).setPrice(sc.nextDouble());
					break;
				case 5:
					break;
				default:
					System.out.println("Invalid input try again");
				}
			}
			updatePromotionalPackage(oldMI, menuAl.get(index));
		}
		TextDB.saveMenuItem("MenuItems.txt", menuAl);
	}

	public void removeMenuItem() throws IOException {
		int index;
		MenuItem oldMI;
		System.out.println("Please enter the food item that you want to remove");
		String searchName = sc.nextLine();
		index = getMIIndex(menuAl, searchName);
		if (index == -1)
			System.out.println("Food not found");
		else {
			oldMI = menuAl.get(index);
			menuAl.remove(index);
			updatePromotionalPackage(oldMI);
		}
		TextDB.saveMenuItem("MenuItems.txt", menuAl);
	}

	public void createPromotionalPackage() throws IOException {
		String promoName;
		String promoDesc;
		String foodName;
		int index;
		ArrayList<MenuItem> promoItems = new ArrayList<MenuItem>();

		System.out.println("Please enter new promotional package name");
		promoName = sc.nextLine();
		if (getPPIndex(promoPackageAl, promoName) != -1) {
			System.out.println("Existing promo name found. Please try again");
			return;
		}
		System.out.println("Please enter description for " + promoName);
		promoDesc = sc.nextLine();
		do {
			System.out.println("Please enter food name to add to " + promoName + " (enter 0 to finish adding)");
			foodName = sc.nextLine();
			index = getMIIndex(menuAl, foodName);
			if (index == 0)
				break;
			else if (index == -1)
				System.out.println("Food not found, Please try again");
			else
				promoItems.add(menuAl.get(index));
		} while (foodName != "0");

		PromotionalPackage p1 = new PromotionalPackage(promoName, promoDesc, promoItems);
		promoPackageAl.add(p1);

		TextDB.writePromoPackage("PromoPackages.txt", promoPackageAl);

	}

	public void updatePromotionalPackage() throws IOException {
		int index, choice = 0;
		String promoName;
		String searchFood;
		boolean found = false;
		System.out.println("Please enter the name of the promotional package that you want to update");
		promoName = sc.nextLine();
		index = getPPIndex(promoPackageAl, promoName);

		if (index == -1) {
			System.out.println("Promotional Package not found, please try again");
			return;
		} else {
			while (choice != 5) {
//				System.out.println(pp.toString());
				System.out.println("1) Update promotional package name");
				System.out.println("2) Update promotional package description");
				System.out.println("3) Add items to promotional package");
				System.out.println("4) Remove items from promotional package");
				System.out.println("5) Exit");
				choice = sc.nextInt();
				switch (choice) {
				case 1:
					System.out.println("Enter updated promotional package name");
					promoPackageAl.get(index).setPromoName(sc.nextLine());
					break;
				case 2:
					System.out.println("Enter updated promotional package description");
					promoPackageAl.get(index).setDescription(sc.nextLine());
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
			}
		}
		for (PromotionalPackage pp : promoPackageAl) {
			if (pp.getPromoName().equalsIgnoreCase(searchName)) {
				found = true;

				break;
			}
		}
		if (!found)
			System.out.println("Promotional Package " + searchName + " not found");
		TextDB.writePromoPackage("PromoPackages.txt", promoPackageAl);
	}

	public void updatePromotionalPackage(MenuItem oldMi, MenuItem newMi) throws IOException {
		ArrayList<MenuItem> miArr;
		for (PromotionalPackage pp : promoPackageAl) {
			miArr = pp.getMenuItemArr();
			for (MenuItem mi : miArr) {
				if (mi.equals(oldMi))
					mi = newMi;
			}
		}
		TextDB.writePromoPackage("PromoPackages.txt", promoPackageAl);
	}

	// rename
	private void updatePromotionalPackage(MenuItem mi) throws IOException {
		for (PromotionalPackage pp : promoPackageAl) {
			for (MenuItem mi2 : pp.getMenuItemArr()) {
				if (mi.equals(mi2)) {
					pp.getMenuItemArr().remove(mi);
				}
			}
		}
		TextDB.writePromoPackage("PromoPackages.txt", promoPackageAl);
	}

	// rename
	public void removePromotionalPackage() throws IOException {
		System.out.println("Please enter the name of the promotional package that you want to remove");
		String searchName = sc.nextLine();
		for (PromotionalPackage pp : promoPackageAl) {
			if (pp.getPromoName().equalsIgnoreCase(searchName)) {
				menuAl.remove(pp);
				break;
			}
		}
		TextDB.writePromoPackage("PromoPackages.txt", promoPackageAl);

	}

	public int getMIIndex(ArrayList<MenuItem> al, String search) {
		for (int i = 0; i < al.size(); i++) {
			if (search == al.get(i).getFoodName())
				return i;
		}
		return -1;
	}

	public int getPPIndex(ArrayList<PromotionalPackage> al, String search) {
		for (int i = 0; i < al.size(); i++) {
			if (search == al.get(i).getPromoName())
				return i;
		}
		return -1;
	}

//	public PromotionalPackage getObject(ArrayList<PromotionalPackage> al, String promoName) {
//		for (PromotionalPackage pp : al) {
//			if (promoName == pp.getPromoName())
//				return pp;
//		}
//		return null;
//
//	}
}
