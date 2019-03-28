package Control;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import Entity.Alacarte;
import Entity.PromotionalPackage;

public class MenuMgr {
	private ArrayList<Alacarte> alacarteAL;
	private ArrayList<PromotionalPackage> promoPackageAl;
	protected Scanner sc;

	public MenuMgr() {
		alacarteAL = new ArrayList<Alacarte>();
		promoPackageAl = new ArrayList<PromotionalPackage>();
		sc = new Scanner(System.in);
		try {
			alacarteAL = TextDB.readMenuItem("MenuItems.txt");
			promoPackageAl = TextDB.readPromoPackageItem("PromotionalPackages.txt");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void printMenuItem() {
		System.out.println("Menu Item as follows:");
		for (Alacarte mi : alacarteAL) {
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
		if (getAlacarteIndex(alacarteAL, foodName) != -1) {
			System.out.println("Existing food name found. Please try again");
			return;
		}
		System.out.println("Please enter description of food");
		foodDesc = sc.nextLine();
		System.out.println("Please enter price");
		foodPrice = Double.parseDouble(sc.nextLine());
		Alacarte i1 = new Alacarte(foodName, foodDesc, foodPrice, foodCat);
		alacarteAL.add(i1);
		System.out.println(alacarteAL.get(0).toString());
		System.out.println("Creation of menu item is sucessful!");
		TextDB.saveMenuItem("MenuItems.txt", alacarteAL);
	}

	public void updateMenuItem() throws IOException {
		String searchName;
		int choice = 0, index;
		Alacarte currAla;
		System.out.println("Please enter the name of the food item that you want to update");
		searchName = sc.nextLine();
		index = getAlacarteIndex(alacarteAL, searchName);
		if (index == -1)
			System.out.println("Food not found");
		else {
			currAla = alacarteAL.get(index);
			while (choice != 5) {
				System.out.println(alacarteAL.get(index).toString());
				System.out.println("1) Update food category");
				System.out.println("2) Update food name");
				System.out.println("3) Update description");
				System.out.println("4) Update price");
				System.out.println("5) Exit");
				choice = sc.nextInt();
				sc.nextLine();
				switch (choice) {
				case 1:
					System.out.println("Enter updated food category");
					currAla.setCategory(sc.nextLine());
					break;
				case 2:
					System.out.println("Enter updated food name");
					currAla.setName(sc.nextLine());
					break;
				case 3:
					System.out.println("Enter updated description");
					currAla.setDescription(sc.nextLine());
					break;
				case 4:
					System.out.println("Enter updated price");
					currAla.setPrice(sc.nextDouble());
					break;
				case 5:
					break;
				default:
					System.out.println("Invalid input try again");
				}
			}
			updatePromotionalPackage(currAla, alacarteAL.get(index));
		}
		TextDB.saveMenuItem("MenuItems.txt", alacarteAL);
	}

	public void removeMenuItem() throws IOException {
		int index;
		Alacarte oldMI;
		System.out.println("Please enter the food item that you want to remove");
		String searchName = sc.nextLine();
		index = getAlacarteIndex(alacarteAL, searchName);
		if (index == -1)
			System.out.println("Food not found");
		else {
			oldMI = alacarteAL.get(index);
			alacarteAL.remove(index);
			updatePromotionalPackage(oldMI);
		}
		TextDB.saveMenuItem("MenuItems.txt", alacarteAL);
	}

	public void createPromotionalPackage() throws IOException {
		String promoName;
		String promoDesc;
		double promoPrice;
		String foodName;
		int index;
		ArrayList<Alacarte> promoItems = new ArrayList<Alacarte>();
		System.out.println("Please enter new promotional package name");
		promoName = sc.nextLine();
		if (getPPIndex(promoPackageAl, promoName) != -1) {
			System.out.println("Existing promo name found. Please try again");
			return;
		}
		System.out.println("Please enter new promotional package description");
		promoDesc = sc.nextLine();
		System.out.println("Please enter new promotional package price");
		promoPrice = sc.nextDouble();
		sc.nextLine();
		do {
			System.out.println("Please enter food name to add to " + promoName + " (enter 0 to finish adding)");
			foodName = sc.nextLine();
			System.out.println("Food name entered : " + foodName);
			index = getAlacarteIndex(alacarteAL, foodName);
			if (foodName.equals("0"))
				break;
			else if (index == -1)
				System.out.println("Food not found, Please try again");
			else
				promoItems.add(alacarteAL.get(index));
		} while (!(foodName.equals("0")));
		PromotionalPackage p1 = new PromotionalPackage(promoName, promoDesc, promoPrice, promoItems);
		promoPackageAl.add(p1);
		System.out.println("Creation of promotional package is sucessful!");
		TextDB.savePromoPackage("PromotionalPackages.txt", promoPackageAl);

	}

	public void updatePromotionalPackage() throws IOException {
		int index, index2, choice = 0;
		String promoName;
		String searchFood;
		PromotionalPackage curPP;
		System.out.println("Please enter the name of the promotional package that you want to update");
		promoName = sc.nextLine();
		System.out.println("Search term" + promoName);
		index = getPPIndex(promoPackageAl, promoName);		
		if (index == -1) {
			System.out.println("Promotional Package not found, please try again");
			return;
		} else {
			curPP = promoPackageAl.get(index);
			while (choice != 5) {
				System.out.println(curPP.toString());
				System.out.println("1) Update promotional package name");
				System.out.println("2) Update promotional package description");
				System.out.println("3) Update promotional package price");
				System.out.println("4) Add items to promotional package");
				System.out.println("5) Remove items from promotional package");
				System.out.println("6) Exit");
				choice = sc.nextInt();
				switch (choice) {
				case 1:
					System.out.println("Enter updated promotional package name");
					curPP.setName(sc.nextLine());
					break;
				case 2:
					System.out.println("Enter updated promotional package description");
					curPP.setDescription(sc.nextLine());
					break;
				case 3:
					System.out.println("Enter updated promotional package price");
					curPP.setPrice(sc.nextInt());
					break;
				case 4:
					System.out.println("Enter food name to add to promotional package");
					searchFood = sc.nextLine();
					index2 = getAlacarteIndex(alacarteAL, searchFood);
					if (index2 != -1) {
						System.out.println("Food not found. Please try again");
						break;
					} else {
						curPP.getMenuItemArr().add(alacarteAL.get(index2));
					}
					break;
				case 5:
					System.out.println("Enter food name to remove from promotional package");
					searchFood = sc.nextLine();
					index2 = getAlacarteIndex(curPP.getMenuItemArr(), searchFood);
					if (index2 != -1) {
						System.out.println("Food not found. Please try again");
						break;
					} else {
						curPP.getMenuItemArr().remove(index2);
						System.out.println("Removed");
					}
					break;
				case 6:
					break;
				default:
					System.out.println("Invalid input try again");
				}
			}		
			TextDB.savePromoPackage("PromotionalPackages.txt", promoPackageAl);
		}
	}

	public void updatePromotionalPackage(Alacarte oldMi, Alacarte newMi) throws IOException {
		ArrayList<Alacarte> miArr;
		for (PromotionalPackage pp : promoPackageAl) {
			miArr = pp.getMenuItemArr();
			for (Alacarte mi : miArr) {
				if (mi.equals(oldMi))
					mi = newMi;
			}
		}
		TextDB.savePromoPackage("PromotionalPackages.txt", promoPackageAl);
	}

	// rename
	private void updatePromotionalPackage(Alacarte mi) throws IOException {
		for (PromotionalPackage pp : promoPackageAl) {
			for (Alacarte mi2 : pp.getMenuItemArr()) {
				if (mi.equals(mi2)) {
					pp.getMenuItemArr().remove(mi);
				}
			}
		}
		TextDB.savePromoPackage("PromoPackages.txt", promoPackageAl);
	}

	// rename
	public void removePromotionalPackage() throws IOException {
		int index;
		System.out.println("Please enter the name of the promotional package that you want to remove");
		index = getPPIndex(promoPackageAl, sc.nextLine());
		if (index == -1)
			System.out.println("Promotional package not found");
		else {
			promoPackageAl.remove(index);
			System.out.println("Promotional package removed");
		}
		TextDB.savePromoPackage("PromotionalPackages.txt", promoPackageAl);

	}
	
	public void printPromotionalPackage() {
		for(PromotionalPackage pp : promoPackageAl) 
			System.out.println(pp.toString());
	}

	public int getAlacarteIndex(ArrayList<Alacarte> al, String search) {
		for (int i = 0; i < al.size(); i++) {
			if (search.equalsIgnoreCase(al.get(i).getName()))
				return i;
		}
		return -1;
	}

	public int getPPIndex(ArrayList<PromotionalPackage> al, String search) {
		for (int i = 0; i < al.size(); i++) {
			if (search.equalsIgnoreCase(al.get(i).getName()))
				return i;
		}
		return -1;
	}
}
