package Control;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import Entity.Alacarte;
import Entity.Menu;
import Entity.PromotionalPackage;

public class MenuMgr {
	private ArrayList<Menu> menuAl;
	protected Scanner sc;

	public MenuMgr() {
		sc = new Scanner(System.in);
		try {
			
			menuAl = TextDB.readMenu("MenuItems.txt");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void printMenuItem() {
		System.out.println("Menu Item as follows:");
		for(int i = 0;i < menuAl.size();i++) {
			if(menuAl.get(i) instanceof Alacarte) {
				Alacarte aItem = (Alacarte) menuAl.get(i);
				System.out.println(aItem.toString());
			}
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
		if (getIndex(foodName) != -1) {
			System.out.println("Existing food name found. Please try again");
			return;
		}
		System.out.println("Please enter description of food");
		foodDesc = sc.nextLine();
		System.out.println("Please enter price");
		foodPrice = Double.parseDouble(sc.nextLine());
		Alacarte i1 = new Alacarte(foodName, foodDesc, foodPrice, foodCat);
		menuAl.add(i1);
		System.out.println(i1.toString());
		System.out.println("Creation of menu item is sucessful!");
		TextDB.saveMenu("MenuItems.txt", menuAl);
	}

	public void updateMenuItem() throws IOException {
		String searchName;
		int choice = 0, index;
		Alacarte oldAla, newAla;
		System.out.println("Please enter the name of the food item that you want to update");
		searchName = sc.nextLine();
		index = getIndex(searchName);
		if (index == -1)
			System.out.println("Food not found");
		else {
			oldAla = (Alacarte) menuAl.get(index);
			newAla = (Alacarte) menuAl.get(index);
			while (choice != 5) {
				System.out.println(menuAl.get(index).toString());
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
					newAla.setCategory(sc.nextLine());
					break;
				case 2:
					System.out.println("Enter updated food name");
					newAla.setName(sc.nextLine());
					break;
				case 3:
					System.out.println("Enter updated description");
					newAla.setDescription(sc.nextLine());
					break;
				case 4:
					System.out.println("Enter updated price");
					newAla.setPrice(sc.nextDouble());
					break;
				case 5:
					break;
				default:
					System.out.println("Invalid input try again");
				}
			}
			updateAlafromPP(oldAla, (Alacarte) menuAl.get(index)); // doesnt update alacarte in promo pkg
		}
		TextDB.saveMenu("MenuItems.txt", menuAl);
	}

	public void removeMenuItem() throws IOException { // whole method working
		int index;
		Alacarte currAla = new Alacarte();
		System.out.println("Please enter the food item that you want to remove");
		String searchName = sc.nextLine();
		index = getIndex(searchName);
		if (index == -1)
			System.out.println("Food not found");
		else {
			currAla = (Alacarte) menuAl.get(index);
			menuAl.remove(index);
			removeAlafromPP(currAla);
			System.out.println("Removed ala carte menu item successfully!");
		}
		TextDB.saveMenu("MenuItems.txt", menuAl);
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
		if (getIndex(promoName) != -1) {
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
			index = getIndex(foodName);
			if (foodName.equals("0"))
				break;
			else if (index == -1)
				System.out.println("Food not found, Please try again");
			else
				promoItems.add((Alacarte)menuAl.get(index));
		} while (!(foodName.equals("0")));
		PromotionalPackage p1 = new PromotionalPackage(promoName, promoDesc, promoPrice, promoItems);
		menuAl.add(p1);
		System.out.println("Creation of promotional package is sucessful!");
		TextDB.saveMenu("MenuItems.txt", menuAl);

	}

	public void updatePromotionalPackage() throws IOException {
		int index, index2, choice = 0;
		String promoName;
		String searchFood;
		PromotionalPackage curPP;
		System.out.println("Please enter the name of the promotional package that you want to update");
		promoName = sc.nextLine();
		System.out.println("Search term " + promoName);
		index = getIndex(promoName);
		if (index == -1) {
			System.out.println("Promotional Package not found, please try again");
			return;
		} else {
			curPP = (PromotionalPackage) menuAl.get(index);
			while (choice != 6) {
				System.out.println(curPP.toString());
				System.out.println("1) Update promotional package name");
				System.out.println("2) Update promotional package description");
				System.out.println("3) Update promotional package price");
				System.out.println("4) Add items to promotional package");
				System.out.println("5) Remove items from promotional package");
				System.out.println("6) Exit");
				choice = Integer.parseInt(sc.nextLine());
				switch (choice) {
				case 1: // working
					System.out.println("Enter updated promotional package name");
					curPP.setName(sc.nextLine());
					break;
				case 2: // working
					System.out.println("Enter updated promotional package description");
					curPP.setDescription(sc.nextLine());
					break;
				case 3: // working
					System.out.println("Enter updated promotional package price");
					curPP.setPrice(Double.parseDouble(sc.nextLine()));
					break;
				case 4: // working
					System.out.println("Enter food name to add to promotional package");
					searchFood = sc.nextLine();
					System.out.println("Wat food u adding to promo pkg : " + searchFood);
					index2 = getIndex(searchFood);
					if (index2 == -1) {
						System.out.println("Food not found. Please try again");
						break;
					} else {
						curPP.getMenuItemArr().add((Alacarte)menuAl.get(index2));
					}
					break;
				case 5: // not working
					System.out.println("Enter food name to remove from promotional package");
					searchFood = sc.nextLine();
					System.out.println("Wat food u removing from promo pkg : " + searchFood);
					index2 = getMenuinPPIndex(curPP, searchFood);
					if (index2 == -1) {
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
			TextDB.saveMenu("MenuItems.txt", menuAl);
		}
	}

	public void updateAlafromPP(Alacarte oldA, Alacarte newA) throws IOException {
		for(int i = 0;i<menuAl.size();i++) {
			if(menuAl.get(i) instanceof PromotionalPackage) {
				for (Alacarte a : new ArrayList<>(((PromotionalPackage)menuAl.get(i)).getMenuItemArr())) {
					if (oldA.equals(a))
						a = newA;
				}
			}
		}
		TextDB.saveMenu("MenuItems.txt", menuAl);
	}

	// rename
	private void removeAlafromPP(Alacarte currA) throws IOException {
		for(int i = 0;i<menuAl.size();i++) {
			if(menuAl.get(i) instanceof PromotionalPackage) {
				for (Alacarte a : new ArrayList<>(((PromotionalPackage)menuAl.get(i)).getMenuItemArr())) {
					if (currA.equals(a)) 
						((PromotionalPackage)menuAl.get(i)).getMenuItemArr().remove(a);
				}
			}
		}
		TextDB.saveMenu("MenuItems.txt", menuAl);
	}

	// rename
	public void removePromotionalPackage() throws IOException {
		int index;
		System.out.println("Please enter the name of the promotional package that you want to remove");
		index = getIndex(sc.nextLine());
		if (index == -1)
			System.out.println("Promotional package not found");
		else {
			menuAl.remove(index);
			System.out.println("Promotional package removed");
		}
		TextDB.saveMenu("MenuItems.txt", menuAl);

	}

	public void printPromotionalPackage() {
		for(int i = 0;i<menuAl.size();i++) {
			if (menuAl.get(i) instanceof PromotionalPackage)
	        	System.out.println(((PromotionalPackage) menuAl.get(i)).toString());
		}
	}
	
	public int getIndex(String search) {
		for (int i = 0; i < menuAl.size(); i++) {
			System.out.println(menuAl.get(i).toString());
			if (search.equalsIgnoreCase(menuAl.get(i).getName()))
				return i;
		}
		return -1;
	}
	
	public int getMenuinPPIndex(PromotionalPackage pp, String search) {
		ArrayList<Alacarte> al = pp.getMenuItemArr();
		for (int i = 0; i < al.size(); i++){
			if(search.equalsIgnoreCase(al.get(i).getName()))
				return i;
		}
		return -1;	
	}
	

	public ArrayList<Menu> getMenuAl() {
		return this.menuAl;
	}
	
}
