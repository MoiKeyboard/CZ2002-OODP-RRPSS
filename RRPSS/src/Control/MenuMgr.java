package Control;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import Entity.Alacarte;
import Entity.Menu;
import Entity.PromotionalPackage;

/**
 * (Control) Object wrapper for MenuMgr
 * 
 * @author Qwek Zhi Hui
 * @version 1.0
 * @since 2019-04-17
 */

public class MenuMgr {
	private ArrayList<Menu> menuAl;
	private Scanner sc;

	/**
	 * Constructor for MenuMgr, calls {@link TextDB#readMenu(String)}.
	 */
	public MenuMgr() {
		sc = new Scanner(System.in);
		try {

			menuAl = TextDB.readMenu("MenuItems.txt");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Prints out every Alacarte Item in ArrayList[Menu].
	 */
	public void printMenuItem() {
		System.out.println("Menu Item as follows:");
		for (int i = 0; i < menuAl.size(); i++) {
			if (menuAl.get(i) instanceof Alacarte) {
				Alacarte aItem = (Alacarte) menuAl.get(i);
				System.out.println(aItem.toString());
			}
		}
		System.out.println();
	}

	/**
	 * Creates Alacarte item based on user inputs and adds it to ArrayList[Menu]. Calls {@link TextDB#saveMenu(String, List)}.
	 */
	public void createAlacarte() throws Exception {
		String foodCat, foodName, foodDesc;
		double foodPrice;
		System.out.println("Please enter food category");
		foodCat = sc.nextLine();
		System.out.println("Please enter food name");
		foodName = sc.nextLine();
		if (getMenuIndex(foodName) != -1) {
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


	/**
	 * Update the details for an existing Alacarte item based on user inputs and saves the updated ArrayList[Menu] to text file. Calls {@link updateAlafromPP(Alacarte, Alacarte)} AND {@link TextDB#saveMenu(String, List).
	 */
	// check instance of alacarte
	public void updateAlacarte() throws IOException {
		String searchName;
		int choice = 0, index;
		System.out.println("Please enter the name of the food item that you want to update");
		searchName = sc.nextLine();
		index = getMenuIndex(searchName);
		if (index == -1)
			System.out.println("Food not found");
		else {
			Alacarte chosenAla = (Alacarte) menuAl.get(index);
			Alacarte oldAla = new Alacarte(chosenAla.getName(), chosenAla.getDescription(), chosenAla.getPrice(),
					chosenAla.getCategory());
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
					chosenAla.setCategory(sc.nextLine());
					break;
				case 2:
					System.out.println("Enter updated food name");
					chosenAla.setName(sc.nextLine());
					break;
				case 3:
					System.out.println("Enter updated description");
					chosenAla.setDescription(sc.nextLine());
					break;
				case 4:
					System.out.println("Enter updated price");
					chosenAla.setPrice(sc.nextDouble());
					break;
				case 5:
					break;
				default:
					System.out.println("Invalid input try again");
				}
			}
			System.out.println("old ALA =" + oldAla);
			updateAlafromPP(oldAla, chosenAla); // working
		}
		TextDB.saveMenu("MenuItems.txt", menuAl);
	}

	/**
	 *  Deletes Alacarte item from ArrayList[Menu], removes it from ArrayList[Menu] and saves the updated ArrayList[Menu] to text file. Calls {@link removeAlafromPP(Alacarte)} and {@link TextDB#saveMenu(String, List)}.
	 */
	public void removeAlacarte() throws IOException { // whole method working
		int index;
		Alacarte currAla = new Alacarte();
		System.out.println("Please enter the food item that you want to remove");
		String searchName = sc.nextLine();
		index = getMenuIndex(searchName);
		if (index == -1)
			return;
		else {
			currAla = (Alacarte) menuAl.get(index);
			menuAl.remove(index);
			removeAlafromPP(currAla);
			System.out.println("Removed ala carte menu item successfully!");
		}
		TextDB.saveMenu("MenuItems.txt", menuAl);
	}
	
	/**
	 * Create new PromotionalPackage, adds it to ArrayList[Menu] and saves it to text file. Calls {@link updateAlacarteAL(PromotionalPackage)} AND {@link TextDB#saveMenu(String, List)}.
	 */
	public void createPromotionalPackage() throws IOException {
		String promoName;
		String promoDesc;
		double promoPrice;
		ArrayList<Alacarte> promoItems = new ArrayList<Alacarte>();
		System.out.println("Please enter new promotional package name");
		promoName = sc.nextLine();
		if (getMenuIndex(promoName) != -1) {
			System.out.println("Existing name found. Please try again");
			return;
		}
		System.out.println("Please enter new promotional package description");
		promoDesc = sc.nextLine();
		System.out.println("Please enter new promotional package price");
		promoPrice = sc.nextDouble();
		sc.nextLine();
		updateAlacarteAL(promoItems);
		PromotionalPackage p1 = new PromotionalPackage(promoName, promoDesc, promoPrice, promoItems);
		menuAl.add(p1);
		System.out.println("Creation of promotional package is sucessful!");
		TextDB.saveMenu("MenuItems.txt", menuAl);

	}

	/**
	 * Update the details for an existing PromotionalPackage item based on user inputs and saves the updated ArrayList[Menu] to text file. Calls {@link updateAlacarteAL(ArrayList)} AND {@link TextDB#saveMenu(String, List).
	 */
	public void updatePromotionalPackage() throws IOException {
		int index, choice = 0;
		String promoName;
		PromotionalPackage curPP;
		System.out.println("Please enter the name of the promotional package that you want to update");
		promoName = sc.nextLine();
		System.out.println("Search term " + promoName);
		index = getMenuIndex(promoName);
		if (index == -1) {
			System.out.println("Promotional Package not found, please try again");
			return;
		} else {
			curPP = (PromotionalPackage) menuAl.get(index);
			while (choice != 5) {
				System.out.println(curPP.toString());
				System.out.println("1) Update promotional package name");
				System.out.println("2) Update promotional package description");
				System.out.println("3) Update promotional package price");
				System.out.println("4) Update items of promotional package");
				System.out.println("5) Exit");
				choice = Integer.parseInt(sc.nextLine());
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
					curPP.setPrice(Double.parseDouble(sc.nextLine()));
					break;
				case 4:
					updateAlacarteAL(curPP.getMenuItemArr());
					break;
				default:
					System.out.println("Invalid input try again");
				}
			}
			TextDB.saveMenu("MenuItems.txt", menuAl);
		}
	}

	/**
	 * Add/Remove items to/from PromotionalPackage.
	 * 
	 * @param al Alacarte ArrayList
	 */
	private void updateAlacarteAL(ArrayList<Alacarte> al) {
		String foodName;
		int choice, qty;
		do {
			System.out.println(al.toString());
			System.out.println("1) Add items to promotional package");
			System.out.println("2) Remove items from promotional package");
			System.out.println("3) Finish");
			choice = Integer.parseInt(sc.nextLine());
			System.out.println("Enter food name");
			foodName = sc.nextLine();
			System.out.println("Please enter quanity of " + foodName);
			qty = Integer.parseInt(sc.nextLine());
			switch (choice) {
			case 1:
				al = addAlacarteAL(al, foodName, qty);
				break;
			case 2:
				al = removeAlacarteAL(al, foodName, qty);
				break;
			default:
				System.out.println("Invalid input try again");
			}

		} while (choice != 3);
		return;
	}

	/**
	 * Returns ArrayList[Alacarte] after removing the particular food from it.
	 * 
	 * @param al Alacarte ArrayList
	 * @param foodName Name of Alacarte item
	 * @param qty Quantity of Alacarte item
	 */
	private ArrayList<Alacarte> removeAlacarteAL(ArrayList<Alacarte> al, String foodName, int qty) {
		int index = getAlacarteIndex(al, foodName);
		if (index == -1)
			return al;
		else {
			while (qty > 0) {
				al.remove(index);
				qty--;
			}
			System.out.println(qty + " " + foodName + " removed");
		}
		return al;
	}

	/**
	 * Returns ArrayList[Alacarte] after adding the particular food to it.
	 * 
	 * @param al Alacarte ArrayList
	 * @param foodName Name of Alacarte item
	 * @param qty Quantity of Alacarte item
	 */
	private ArrayList<Alacarte> addAlacarteAL(ArrayList<Alacarte> al, String foodName, int qty) {
		int index = getMenuIndex(foodName);
		if (index == -1)
			return al;
		else if (menuAl.get(index) instanceof PromotionalPackage) {
			System.out.println("Can only add alacarte to promotional Packages... Please try again");
			return al;
		} else {
			while (qty > 0) {
				al.add((Alacarte) menuAl.get(index));
				qty--;
			}
			System.out.println(qty + " " + foodName + " added");
		}
		return al;
	}

	/**
	 * Replaces the New Updated Alacarte Object with the Existing Alacarte Object in the ArrayList[Menu] and saves it to text file.Calls {@link Alacarte#equals(Object)} AND {@link TextDB#saveMenu(String, List)}.
	 * 
	 * @param oldA Existing Alacarte Object to be replaced
	 * @param newA New Alacarte Object replacing oldA
	 */
	private void updateAlafromPP(Alacarte oldA, Alacarte newA) throws IOException {
		for (int i = 0; i < menuAl.size(); i++) {
			if (menuAl.get(i) instanceof PromotionalPackage) {
				for (Alacarte a : ((PromotionalPackage) menuAl.get(i)).getMenuItemArr()) {
					System.out.println(oldA.getName() + " ||| " + a.getName() + "<<<");
					if (oldA.equals(a)) {
						System.out.println("oldA = " + oldA.toString());
						System.out.println("menu = " + a.toString());
						a.setName(newA.getName());
						a.setDescription(newA.getDescription());
						a.setPrice(newA.getPrice());
						a.setCategory(newA.getCategory());
					}
				}
			}
		}
		TextDB.saveMenu("MenuItems.txt", menuAl);
	}

	/**
	 * Deletes a particular Alacarte Object from the ArrayList[Menu] and saves updated ArrayList[Menu] to text file .Calls {@link Alacarte#equals(Object)} AND {@link TextDB#saveMenu(String, List)}.
	 * 
	 * @param currA Alacarte Object to be removed
	 */
	private void removeAlafromPP(Alacarte currA) throws IOException {
		for (int i = 0; i < menuAl.size(); i++) {
			if (menuAl.get(i) instanceof PromotionalPackage) {
				for (Alacarte a : new ArrayList<>(((PromotionalPackage) menuAl.get(i)).getMenuItemArr())) {
					if (currA.equals(a))
						((PromotionalPackage) menuAl.get(i)).getMenuItemArr().remove(a);
				}
			}
		}
		TextDB.saveMenu("MenuItems.txt", menuAl);
	}

	/**
	 * Deletes a particular Promotional Package based on user input and saves updated ArrayList[Menu] to text file. Calls {@link TextDB#saveMenu(String, List)}.
	 * 
	 */
	public void removePromotionalPackage() throws IOException {
		int index;
		System.out.println("Please enter the name of the promotional package that you want to remove");
		index = getMenuIndex(sc.nextLine());
		if (index == -1)
			System.out.println("Promotional package not found");
		else {
			menuAl.remove(index);
			System.out.println("Promotional package removed");
		}
		TextDB.saveMenu("MenuItems.txt", menuAl);

	}

	/**
	 * Prints out every Alacarte Item in ArrayList[Menu].
	 */
	public void printPromotionalPackage() {
		for (int i = 0; i < menuAl.size(); i++) {
			if (menuAl.get(i) instanceof PromotionalPackage)
				System.out.println(((PromotionalPackage) menuAl.get(i)).toString());
		}
	}

	/**
	 * Returns the Index of Menu in ArrayList[Menu] based on Name.
	 * 
	 * @param search Name of MenuItem
	 */
	protected int getMenuIndex(String search) {
		for (int i = 0; i < menuAl.size(); i++) {
			if (search.equalsIgnoreCase(menuAl.get(i).getName()))
				return i;
		}
		System.out.println("Menu item: " + search + " not found");
		return -1;
	}

	/**
	 * Returns the Index of Menu in ArrayList[Menu] based on Name. This method can only be called within this class.
	 * 
	 * @param search Name of MenuItem
	 */
	private int getMenuIndex(ArrayList<Menu> al, String search) {
		for (int i = 0; i < al.size(); i++) {
			if (search.equalsIgnoreCase(al.get(i).getName()))
				return i;
		}
		System.out.println("Menu item: " + search + " not found");
		return -1;
	}

	// Is this even needed??????? Tot we migrated to Menu ArrayList 
	private int getAlacarteIndex(ArrayList<Alacarte> al, String search) {
		for (int i = 0; i < al.size(); i++) {
			if (search.equalsIgnoreCase(al.get(i).getName()))
				return i;
		}
		System.out.println("Menu item: " + search + " not found");
		return -1;
	}

	/**
	 * Returns the ArrayList[Menu].
	 */
	protected ArrayList<Menu> getMenuAl() {
		return menuAl;
	}

	/**
	 * Returns ArrayList[Menu] after updating the PromotionalPackage in it based on User Input.
	 * 
	 * @param al Menu ArrayList
	 */
	protected ArrayList<Menu> updateMenuAL(ArrayList<Menu> al) {
		String foodName;
		int choice, qty;
		do {
//			for (Menu menu : menuAl) {
//				if (menu instanceof Alacarte)
//					System.out.println(((Alacarte) menu).toString());
//				else
//					System.out.println(((PromotionalPackage) menu).toString());
//			}
			System.out.println("1) Add items to list");
			System.out.println("2) Remove items from list");
			System.out.println("3) Finish");
			choice = Integer.parseInt(sc.nextLine());
			if (choice == 3)
				return al;
			System.out.println("Enter food name");
			foodName = sc.nextLine();
			System.out.println("Please enter quanity of " + foodName);
			qty = Integer.parseInt(sc.nextLine());
			switch (choice) {
			case 1:
				al = addMenuAL(al, foodName, qty);
				break;
			case 2:
				al = removeMenuAL(al, foodName, qty);
				break;
			default:
				System.out.println("Invalid input try again");
			}

		} while (choice != 3);
		return al;
	}

	/**
	 * Returns ArrayList[Menu] after removing a particular Menu Item from it.
	 * 
	 * @param al ArrayList[Menu]
	 * @param foodName Name of Food to be removed
	 * @param qty Qty of Food to be removed
	 */
	private ArrayList<Menu> removeMenuAL(ArrayList<Menu> al, String foodName, int qty) {
		int index = getMenuIndex(al, foodName);
		if (index == -1)
			return al;
		else {
			while (qty > 0) {
				al.remove(index);
				qty--;
			}
			System.out.println(foodName + " removed");
		}
		return al;
	}

	/**
	 * Returns ArrayList[Menu] after adding a particular Menu Item into it.
	 * 
	 * @param al ArrayList[Menu]
	 * @param foodName Name of Food to be added
	 * @param qty Qty of Food to be added
	 */
	private ArrayList<Menu> addMenuAL(ArrayList<Menu> al, String foodName, int qty) {
		int index = getMenuIndex(foodName);
		if (index == -1)
			return al;
		else {
			while (qty > 0) {
				if (menuAl.get(index) instanceof Alacarte)
					al.add((Alacarte) menuAl.get(index));
				else if (menuAl.get(index) instanceof PromotionalPackage)
					al.add((PromotionalPackage) menuAl.get(index));
				qty--;
			}
			System.out.println(foodName + " added");
		}
		return al;
	}

}
