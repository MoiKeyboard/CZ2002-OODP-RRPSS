package Control;

import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.StringTokenizer;

import Entity.Customer;
import Entity.MenuItem;
import Entity.Order;
import Entity.PromotionalPackage;
import Entity.Reservation;
import Entity.Staff;
import Entity.Table;

public class TextDB {
	public static final String SEPARATOR = "|";

	// an example of reading
	public static ArrayList<MenuItem> readMenuItem(String filename) throws IOException {
		// read String from text file
		ArrayList stringArray = (ArrayList) read(filename);
		ArrayList<MenuItem> menuAl = new ArrayList<MenuItem>();

		for (int i = 0; i < stringArray.size(); i++) {
			String st = (String) stringArray.get(i);
			// get individual 'fields' of the string separated by SEPARATOR
			StringTokenizer star = new StringTokenizer(st, SEPARATOR); // pass in the string to the string tokenizer
																		// using delimiter ","
			String foodType = star.nextToken().trim(); // first token
			String foodName = star.nextToken().trim(); // second token
			String description = star.nextToken().trim(); // third token
			double price = Double.parseDouble(star.nextToken().trim());
			// create Professor object from file data
			MenuItem item = new MenuItem(foodType, foodName, description, price);
			// add to Professors list
			menuAl.add(item);
		}
		return menuAl;
	}

	// an example of saving
	public static void saveMenuItem(String filename, ArrayList<MenuItem> al) throws IOException {
		List alw = new ArrayList();// to store Professors data

		for (int i = 0; i < al.size(); i++) {
			MenuItem item = (MenuItem) al.get(i);
			StringBuilder st = new StringBuilder();
			st.append(item.getFoodType().trim());
			st.append(SEPARATOR);
			st.append(item.getFoodName().trim());
			st.append(SEPARATOR);
			st.append(item.getDescription().trim());
			st.append(SEPARATOR);
			st.append(item.getPrice());
			alw.add(st.toString());
		}
		write(filename, alw);
	}

	public static ArrayList<Staff> readStaff(String filename) throws IOException {
		// read String from text file
		ArrayList stringArray = (ArrayList) read(filename);
		ArrayList<Staff> staffAl = new ArrayList<Staff>();
		for (int i = 0; i < stringArray.size(); i++) {
			String st = (String) stringArray.get(i);
			// get individual 'fields' of the string separated by SEPARATOR
			StringTokenizer star = new StringTokenizer(st, SEPARATOR); // pass in the string to the string tokenizer
																		// using delimiter ","
			int staffId = Integer.parseInt(star.nextToken().trim()); // first token
			String jobTitle = star.nextToken().trim(); // second token
			String staffName = star.nextToken().trim(); // third token
			// create Staff object from file data
			Staff item = new Staff(staffId, jobTitle, staffName);
			// add to Staff array list
			staffAl.add(item);
		}
		return staffAl;
	}

// an example of saving
	public static void saveStaff(String filename, List staffAl) throws IOException {
		List alw = new ArrayList();// to store Professors data

		for (int i = 0; i < staffAl.size(); i++) {
			Staff s1 = (Staff) staffAl.get(i);
			StringBuilder st = new StringBuilder();
			st.append(s1.getStaffId());
			st.append(SEPARATOR);
			st.append(s1.getJobTitle());
			st.append(SEPARATOR);
			st.append(s1.getName().trim());
			alw.add(st.toString());
		}
		write(filename, alw);
	}

	public static ArrayList<Table> readTable(String filename) throws IOException {
		// read String from text file
		ArrayList stringArray = (ArrayList) read(filename);
		ArrayList<Table> tableAl = new ArrayList<Table>();
		for (int i = 0; i < stringArray.size(); i++) {
			String st = (String) stringArray.get(i);
			// get individual 'fields' of the string separated by SEPARATOR
			StringTokenizer star = new StringTokenizer(st, SEPARATOR); // pass in the string to the string tokenizer
																		// using delimiter ","
			int tableNo = Integer.parseInt(star.nextToken().trim()); // first token
			int seatCap = Integer.parseInt(star.nextToken().trim());// second token
			String tableStatus = star.nextToken().trim();// third token
			// create Staff object from file data
			Table table = new Table(tableNo, seatCap, tableStatus);
			// add to Staff array list
			tableAl.add(table);
		}
		return tableAl;
	}

// an example of saving
	public static void saveTable(String filename, List tableAl) throws IOException {
		List alw = new ArrayList();// to store Professors data

		for (int i = 0; i < tableAl.size(); i++) {
			Table s1 = (Table) tableAl.get(i);
			StringBuilder st = new StringBuilder();
			st.append(s1.getTableNo());
			st.append(SEPARATOR);
			st.append(s1.getSeatCap());
			st.append(SEPARATOR);
			st.append(s1.getTableStatus());
			alw.add(st.toString());
		}
		write(filename, alw);
	}

	public static ArrayList<Reservation> readReservation(String filename) throws IOException {
		// read String from text file
		ArrayList stringArray = (ArrayList) read(filename);
		ArrayList<Reservation> reservationAl = new ArrayList<Reservation>();
		for (int i = 0; i < stringArray.size(); i++) {
			String st = (String) stringArray.get(i);
			// get individual 'fields' of the string separated by SEPARATOR
			StringTokenizer star = new StringTokenizer(st, SEPARATOR); // pass in the string to the string tokenizer
																		// using delimiter ","
			int contactNo = Integer.parseInt(star.nextToken().trim()); // first token
			String reservationDateTime = star.nextToken().trim();// second token
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yy kkmm", Locale.ENGLISH);
			LocalDateTime reservationDT = LocalDateTime.parse(reservationDateTime, formatter);
			int pax = Integer.parseInt(star.nextToken());
			int tableNo = Integer.parseInt(star.nextToken());
			// create Reservation object from file data
			Reservation reservation = new Reservation(contactNo, reservationDT, pax, tableNo);
			// add to Reservation array list
			reservationAl.add(reservation);
		}
		return reservationAl;
	}

// an example of saving
	public static void saveReservations(String filename, List reservationAl, List tableAl, List custAl)
			throws IOException {
		List alw = new ArrayList();// to store Professors data
		for (int i = 0; i < reservationAl.size(); i++) {
			Reservation s1 = (Reservation) reservationAl.get(i);
			StringBuilder st = new StringBuilder();
			st.append(s1.getContactNo());
			st.append(SEPARATOR);
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yy kkmm");
			st.append(s1.getReservationDate().format(formatter));
			st.append(SEPARATOR);
			st.append(s1.getPax());
			st.append(SEPARATOR);
			st.append(s1.getTableNo());
			alw.add(st.toString());
		}
		TextDB.saveTable("Table.txt", tableAl);
		TextDB.saveCustomer("Customer.txt", custAl);
		write(filename, alw);
	}

	public static ArrayList<Customer> readCustomer(String filename) throws IOException {
		// read String from text file
		ArrayList stringArray = (ArrayList) read(filename);
		ArrayList<Customer> customerAl = new ArrayList<Customer>();
		for (int i = 0; i < stringArray.size(); i++) {
			String st = (String) stringArray.get(i);
			// get individual 'fields' of the string separated by SEPARATOR
			StringTokenizer star = new StringTokenizer(st, SEPARATOR); // pass in the string to the string tokenizer
																		// using delimiter ","
			String custName = star.nextToken().trim(); // first token
			int contactNo = Integer.parseInt(star.nextToken());// )//second token
			// create Customer object from file data
			Customer customer = new Customer(custName, contactNo);
			// add to Customer array list
			customerAl.add(customer);
		}
		return customerAl;
	}

// an example of saving
	public static void saveCustomer(String filename, List custAl) throws IOException {
		List alw = new ArrayList();// to store Professors data
		for (int i = 0; i < custAl.size(); i++) {
			Customer s1 = (Customer) custAl.get(i);
			StringBuilder st = new StringBuilder();
			st.append(s1.getName());
			st.append(SEPARATOR);
			st.append(s1.getContactNumber());
			alw.add(st.toString());
		}
		write(filename, alw);
	}

	public static ArrayList<Order> readOrder(String filename) throws IOException {
		// read String from text file
		ArrayList stringArray = (ArrayList) read(filename);
		ArrayList<Order> orderAl = new ArrayList<Order>();
		for (int i = 0; i < stringArray.size(); i++) {
			String st = (String) stringArray.get(i);
			// get individual 'fields' of the string separated by SEPARATOR
			StringTokenizer star = new StringTokenizer(st, SEPARATOR); // pass in the string to the string tokenizer
																		// using delimiter ","
			int orderNo = Integer.parseInt(star.nextToken()); // first token
			int staffId = Integer.parseInt(star.nextToken()); // second token
			int tableNo = Integer.parseInt(star.nextToken()); // third token
			String orderItem = star.nextToken().trim(); // fourth token
			int itemQuantity = Integer.parseInt(star.nextToken()); // fifth token
			// create Order object from file data
			//Order order = new Order(orderNo, staffId, tableNo, orderItem, itemQuantity);
			// add to Customer array list
			//orderAl.add(order);
		}
		return orderAl;
	}
	
	public static void saveOrder(String filename, List orderAl) throws IOException {
		List alw = new ArrayList();// to store Orders data
		for (int i = 0; i < orderAl.size(); i++) {
			Order s1 = (Order) orderAl.get(i);
			StringBuilder st = new StringBuilder();
			st.append(s1.getOrderNo());
			st.append(SEPARATOR);
			st.append(s1.getStaffId());
			st.append(SEPARATOR);
			st.append(s1.getTableNo());
			st.append(SEPARATOR);
			//st.append(s1.getOrderItem());
			//st.append(SEPARATOR);
			st.append(s1.getItemQuantity());
			alw.add(st.toString());
		}
		write(filename, alw);
	}

	public static ArrayList<PromotionalPackage> readPromoPackageItem(String filename) throws IOException {
		ArrayList stringArray = (ArrayList) read(filename);
		ArrayList<PromotionalPackage> promoAl = new ArrayList<PromotionalPackage>();
		ArrayList<MenuItem> menuItemAl = new ArrayList<MenuItem>();
		for (int i = 0; i < stringArray.size(); i++) {
			String st = (String) stringArray.get(i);
			// get individual 'fields' of the string separated by SEPARATOR
			StringTokenizer star = new StringTokenizer(st, SEPARATOR); // pass in the string to the string tokenizer
																		// using delimiter ","
			String promoName = star.nextToken().trim();
			String desc = star.nextToken().trim();
			while(star.hasMoreTokens()) {
				String foodType = star.nextToken().trim();
				String foodName = star.nextToken().trim();
				String description = star.nextToken().trim();
				double price = Double.parseDouble(star.nextToken());
				MenuItem mi = new MenuItem(foodType, foodName,description,price);
				menuItemAl.add(mi);
			}
			PromotionalPackage promoPkg = new PromotionalPackage(promoName,desc,menuItemAl);
		}
		return promoAl;
	}

	public static void writePromoPackage(String filename, ArrayList<PromotionalPackage> promoAl) throws IOException {

	}

	/** Write fixed content to the given file. */
	public static void write(String fileName, List data) throws IOException {
		PrintWriter out = new PrintWriter(new FileWriter(fileName));

		try {
			for (int i = 0; i < data.size(); i++) {
				out.println((String) data.get(i));
			}
		} finally {
			out.close();
		}
	}

	/** Delete everything within a file. **/
	public static void deleteEverything(String fileName) throws IOException {
		PrintWriter out = new PrintWriter(new FileWriter(fileName));
		out.close();
	}

	/** Read the contents of the given file. */
	public static List read(String fileName) throws IOException {
		List data = new ArrayList();
		Scanner scanner = new Scanner(new FileInputStream(fileName));
		try {
			while (scanner.hasNextLine()) {
				data.add(scanner.nextLine());
			}
		} finally {
			scanner.close();
		}
		return data;
	}
}
