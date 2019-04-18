package Control;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import Entity.Alacarte;
import Entity.Menu;
import Entity.PromotionalPackage;

/**
 * The {@code MenuMgr} is a control class used to model all control behavior
 * specific to the {@link Menu} and its subclasses {@link Alacarte} and
 * {@link PromotionalPackage}.
 * 
 * @author Qwek Zhi Hui
 * @version 1.0
 * @since 2019-04-17
 */

public class MenuMgr {
	private ArrayList<Menu> menuAl;
	private Scanner sc;

	/**
	 * Constructor for MenuMgr. Calls {@link TextDB#readMenu(String)} to read from
	 * file and populate menuAl ({@code ArrayList<Menu>}).
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
	 * Prints out every {@link Alacarte} object in menuAl.
	 */
	public void printAlacarteItem() {
		String itemCat = "";
		ArrayList<String> catsArr = new ArrayList<String>();
		System.out.println();
		System.out.println("[][][][][][][][][][][][][][][][][][][][][]");
		System.out.println("[][][]            M E N U           [][][]");
		System.out.println("------------------------------------------");
		for (int i = 0; i < menuAl.size(); i++) {
			if (menuAl.get(i) instanceof Alacarte) {
				Alacarte aItem = (Alacarte) menuAl.get(i);
				if (!catsArr.contains(aItem.getCategory())) {
					catsArr.add(aItem.getCategory());
				}
			}
		}

		for (int j = 0; j < catsArr.size(); j++) {
			itemCat = catsArr.get(j);
			System.out.println();
			System.out.println("               - " + itemCat + "-                 ");
			System.out.println();
			for (int k = 0; k < menuAl.size(); k++) {

				if (menuAl.get(k) instanceof Alacarte) {
					// System.out.println(((Alacarte) menuAl.get(k)).getCategory());
					Alacarte aItem = (Alacarte) menuAl.get(k);
					if (aItem.getCategory().equals(itemCat))
						System.out.println(aItem.toString());
				}
			}
		}

		/*
		 * for (int i = 0; i < menuAl.size(); i++) { if (menuAl.get(i) instanceof
		 * Alacarte) { Alacarte aItem = (Alacarte) menuAl.get(i);
		 * System.out.println(aItem.toString()); } }
		 */
		System.out.println();
	}

	/**
	 * Creates {@link Alacarte} object based on user inputs and adds it to menuAl.
	 * Upon completion, Calls {@link TextDB#saveMenu(String, List)} to save the
	 * updated menuAl.
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
		System.out.println("Creation of menu object is sucessful!");
		TextDB.saveMenu("MenuItems.txt", menuAl);
	}

	/**
	 * Updates an existing {@link Alacarte} object (in menuAl) based on user inputs.
	 * Updated {@link Alacarte} values will reflect inside
	 * {@link PromotionalPackage} as well by calling
	 * {@link updateAlafromPP(Alacarte, Alacarte)}.
	 * <p>
	 * Upon completion, Calls {@link TextDB#saveMenu(String, List)} to save the
	 * updated menuAl.
	 */
	// check instance of alacarte
	public void updateAlacarte() throws IOException {
		String searchName;
		int choice = 0, index;
		System.out.println("Please enter the name of the food object that you want to update");
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
				choice = Integer.parseInt(sc.nextLine());
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
					chosenAla.setPrice(Double.parseDouble(sc.next()));
					break;
				case 5:
					break;
				default:
					System.out.println("Invalid input try again");
				}
			}
//			System.out.println("old ALA =" + oldAla);
			updateAlafromPP(oldAla, chosenAla); // working
		}
		TextDB.saveMenu("MenuItems.txt", menuAl);
	}

	/**
	 * Deletes an existing {@link Alacarte} object (in menuAl). Deleted
	 * {@link Alacarte} object will reflect inside {@link PromotionalPackage} as
	 * well by calling {@link removeAlafromPP(currAla)}.
	 * <p>
	 * Upon completion, Calls {@link TextDB#saveMenu(String, List)} to save the
	 * updated menuAl.
	 */
	public void removeAlacarte() throws IOException { // whole method working
		int index;
		Alacarte currAla = new Alacarte();
		System.out.println("Please enter the food object that you want to remove");
		String searchName = sc.nextLine();
		index = getMenuIndex(searchName);
		if (index == -1)
			return;
		else {
			currAla = (Alacarte) menuAl.get(index);
			menuAl.remove(index);
			removeAlafromPP(currAla);
			System.out.println("Removed ala carte menu object successfully!");
		}
		TextDB.saveMenu("MenuItems.txt", menuAl);
	}

	/**
	 * Create new {@link PromotionalPackage} and adds it to existing menuAl. Calls
	 * {@code updateAlacarteAL(promoItems)} to include any {@link Alacarte} of
	 * choice inside {@link PromotionalPackage}.
	 * <p>
	 * Upon completion, Calls {@link TextDB#saveMenu(String, List)} to save the
	 * updated menuAl.
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
		promoPrice = Double.parseDouble(sc.nextLine());
		updateAlacarteAL(promoItems);
		PromotionalPackage p1 = new PromotionalPackage(promoName, promoDesc, promoPrice, promoItems);
		menuAl.add(p1);
		System.out.println("Creation of promotional package is sucessful!");
		TextDB.saveMenu("MenuItems.txt", menuAl);

	}

	/**
	 * Update an existing {@link PromotionalPackage} object inside menuAl. Calls
	 * {@link updateAlacarteAL(ArrayList)} to update {@link Alacarte} within the
	 * {@link PromotionalPackage}.
	 * <p>
	 * Upon completion, Calls {@link TextDB#saveMenu(String, List)} to save the
	 * updated menuAl.
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
				case 5:
					break;
				default:
					System.out.println("Invalid input try again");
				}
			}
			TextDB.saveMenu("MenuItems.txt", menuAl);
		}
	}

	/**
	 * Add or removes objects in {@code ArrayList<Alacarte>} from
	 * {@link PromotionalPackage}. <br>
	 * To add, calls {@code addAlacarteAL}. <br>
	 * To remove, calls {@code removeAlacarteAL}.
	 * 
	 * @param al the {@code ArrayList<Alacarte>} that is being updated
	 */
	private void updateAlacarteAL(ArrayList<Alacarte> al) {
		String foodName;
		int choice, qty;
		do {
			System.out.println("1) Add items to promotional package");
			System.out.println("2) Remove items from promotional package");
			System.out.println("3) Finish");
			choice = Integer.parseInt(sc.nextLine());
			switch (choice) {
			case 1:
				System.out.println("Enter food name");
				foodName = sc.nextLine();
				System.out.println("Please enter quanity of " + foodName);
				qty = Integer.parseInt(sc.nextLine());
				al = addAlacarteAL(al, foodName, qty);
				break;
			case 2:
				System.out.println("Enter food name");
				foodName = sc.nextLine();
				System.out.println("Please enter quanity of " + foodName);
				qty = Integer.parseInt(sc.nextLine());
				al = removeAlacarteAL(al, foodName, qty);
				break;
			}

		} while (choice != 3);
		return;
	}

	/**
	 * Searches through the {@code ArrayList<Alacarte>} for an exact copy of
	 * {@link Alacarte} object. If the object exist, remove it from the list.
	 * <p>
	 * Returns the updated {@code ArrayList<Alacarte>} afterwards.
	 * 
	 * @param al       ArrayList of Alacarte that is going to be updated
	 * @param foodName Name of Alacarte object to be removed
	 * @param qty      Quantity of Alacarte object to be removed
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
	 * Returns the updated {@code ArrayList<Alacarte>} after adding the particular
	 * {@link Alacarte} to it.
	 * 
	 * @param al       ArrayList of Alacarte that is going to be updated
	 * @param foodName Name of Alacarte object to be added
	 * @param qty      Quantity of Alacarte object to be added
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
	 * Replaces existing {@link Alacarte} Object with the updated {@link Alacarte}
	 * within all {@link PromotionalPackage} in menuAl.
	 * <p>
	 * Upon completion, Calls {@link TextDB#saveMenu(String, List)} to save the
	 * updated menuAl.
	 * 
	 * @param oldA Existing Alacarte Object to be replaced
	 * @param newA Updated Alacarte Object replacing oldA
	 */
	private void updateAlafromPP(Alacarte oldA, Alacarte newA) throws IOException {
		for (int i = 0; i < menuAl.size(); i++) {
			if (menuAl.get(i) instanceof PromotionalPackage) {
				for (Alacarte a : ((PromotionalPackage) menuAl.get(i)).getMenuItemArr()) {
//					System.out.println(oldA.getName() + " ||| " + a.getName() + "<<<");
					if (oldA.equals(a)) {
//						System.out.println("oldA = " + oldA.toString());
//						System.out.println("menu = " + a.toString());
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
	 * Deletes existing {@link Alacarte} Object within all
	 * {@link PromotionalPackage} in menuAl.
	 * <p>
	 * Upon completion, Calls {@link TextDB#saveMenu(String, List)} to save the
	 * updated menuAl.
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
	 * Deletes existing {@code PromotionalPackage} object based on user input.
	 * <p>
	 * Upon completion, Calls {@link TextDB#saveMenu(String, List)} to save the
	 * updated menuAl.
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
	 * Prints out every {@link PromotionalPackage} object in menuAl.
	 */
	public void printPromotionalPackage() {
		System.out.println("[][][][][][][][][][][][][][][][][][][][][]");
		System.out.println("[][][]      P R O M O T I O N S     [][][]");
		System.out.println("------------------------------------------");
		for (int i = 0; i < menuAl.size(); i++) {
			if (menuAl.get(i) instanceof PromotionalPackage)
				System.out.println(((PromotionalPackage) menuAl.get(i)).toString());
		}
	}

	/**
	 * Loop through the menuAl to find the {@link Menu} object based on matching
	 * String. Returns the index of menuAl when found.
	 * 
	 * @param search Name of {@link Menu} object to match
	 */
	protected int getMenuIndex(String search) {
		for (int i = 0; i < menuAl.size(); i++) {
			if (search.equalsIgnoreCase(menuAl.get(i).getName()))
				return i;
		}
		return -1;
	}

	/**
	 * Loop through the specified {@code ArrayList<Menu> al} to find the
	 * {@link Menu} object based on matching String. Returns the index when found.
	 * This method can only be called within this class.
	 * 
	 * @param al     the Arraylist to be looped through
	 * @param search Name of {@link Menu} object to match
	 */
	private int getMenuIndex(ArrayList<Menu> al, String search) {
		for (int i = 0; i < al.size(); i++) {
			if (search.equalsIgnoreCase(al.get(i).getName()))
				return i;
		}
		return -1;
	}

	/**
	 * Loop through the specified {@code ArrayList<Alacarte> al} to find the
	 * {@link Alacarte} object based on matching String. Returns the index when
	 * found. This method can only be called within this class.
	 * 
	 * @param al     the Arraylist to be looped through
	 * @param search Name of {@link Menu} object to match
	 * @return
	 */
	private int getAlacarteIndex(ArrayList<Alacarte> al, String search) {
		for (int i = 0; i < al.size(); i++) {
			if (search.equalsIgnoreCase(al.get(i).getName()))
				return i;
		}
		System.out.println("Menu object: " + search + " not found");
		return -1;
	}

	/**
	 * Returns menuAl ({@code ArrayList<Menu>}).
	 */
	protected ArrayList<Menu> getMenuAl() {
		return menuAl;
	}

	/**
	 * Returns {@code ArrayList<Menu>} after updating the PromotionalPackage in it
	 * based on User Input.
	 * 
	 * @param al Menu ArrayList
	 */
	protected ArrayList<Menu> updateMenuAL(ArrayList<Menu> al) {
		String foodName;
		int choice, qty;
		do {
			System.out.println("1) Add items to list");
			System.out.println("2) Remove items from list");
			System.out.println("3) Finish");
			choice = Integer.parseInt(sc.nextLine());
			if (choice == 3)
				return al;
			System.out.println("Enter food name");
			foodName = sc.nextLine();
			if (getMenuIndex(foodName) == -1)
				System.out.println("Error! Food object does not exist.");
			else {
				System.out.println("Please enter quanity of " + foodName);
				qty = Integer.parseInt(sc.nextLine());
				switch (choice) {
				case 1:
					al = addMenuAL(al, foodName, qty);
					break;
				case 2:
					al = removeMenuAL(al, foodName, qty);
					break;
				case 3:
					break;
				default:
					System.out.println("Invalid input try again");
				}
			}

		} while (choice != 3);
		return al;
	}

	/**
	 * Returns {@code ArrayList<Menu>} after removing a particular Menu object from
	 * it.
	 * 
	 * @param al       {@code ArrayList<Menu>}
	 * @param foodName Name of Food to be removed
	 * @param qty      Quantity of Food to be removed
	 */
	private ArrayList<Menu> removeMenuAL(ArrayList<Menu> al, String foodName, int qty) {
		int count = 0;
		while (qty > 0) {
			int index = getMenuIndex(al, foodName);
			if (index == -1)
				break;
			else {
				al.remove(index);
				qty--;
				count++;
			}
		}
		System.out.println(count + " " + foodName + " removed");
		return al;
	}

	/**
	 * Returns {@code ArrayList<Menu>} after adding a particular Menu object into
	 * it.
	 * 
	 * @param al       {@code ArrayList<Menu>}
	 * @param foodName Name of Food to be added
	 * @param qty      Qty of Food to be added
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
