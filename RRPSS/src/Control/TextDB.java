package Control;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.StringTokenizer;

import Entity.Alacarte;
import Entity.Customer;
import Entity.Invoice;
import Entity.Menu;
import Entity.PromotionalPackage;
import Entity.Reservation;
import Entity.Staff;
import Entity.Table;


/**
 * (Control) Object wrapper for TextDB
 * 
 * @author Joseph Fung King Yiu
 * @version 1.0
 * @since 2019-04-17
 */

public class TextDB {
	private static final String SEPARATOR = "|";
	
	/**
	 * Reads in the Menu Item from text file. Returns ArrayList[Menu].
	 * 
	 * @param filename FilePath
	 */
	public static ArrayList<Menu> readMenu(String filename) throws IOException {
		ArrayList stringArray = (ArrayList) read(filename);
		ArrayList<Menu> menuAl = new ArrayList<Menu>();
		for (int i = 0; i < stringArray.size(); i++) {
			String st = (String) stringArray.get(i);
			// get individual 'fields' of the string separated by SEPARATOR
			StringTokenizer star = new StringTokenizer(st, SEPARATOR); // pass in the string to the string tokenizer
																		// using delimiter ","
			String menuType = star.nextToken().trim();
			if (menuType.equalsIgnoreCase("PromoPackage")) {
				ArrayList<Alacarte> tempAlacarteAl = new ArrayList<Alacarte>();
				String promoName = star.nextToken().trim();
				String promoDesc = star.nextToken().trim();
				double promoPrice = Double.parseDouble(star.nextToken());
				while (star.hasMoreTokens()) {
					String foodName = star.nextToken().trim();
					String description = star.nextToken().trim();
					double price = Double.parseDouble(star.nextToken());
					String category = star.nextToken().trim();
					Alacarte mi = new Alacarte(foodName, description, price, category);
					tempAlacarteAl.add(mi);
				}
				PromotionalPackage promoPkg = new PromotionalPackage(promoName, promoDesc, promoPrice, tempAlacarteAl);
				menuAl.add(promoPkg);
			} else if (menuType.equalsIgnoreCase("AlaCarte")) {
				String alaCarteName = star.nextToken().trim();
				String alaCartedesc = star.nextToken().trim();
				double alaCarteprice = Double.parseDouble(star.nextToken());
				String category = star.nextToken().trim();
				Alacarte item = new Alacarte(alaCarteName, alaCartedesc, alaCarteprice, category);
				// add to MenuAl list
				menuAl.add(item);
			}
		}
		return menuAl;
	}
	
	/**
	 * Saves ArrayList[Menu] to text file.
	 * 
	 * @param filename FilePath
	 * @param menuAl ArrayList[Menu]
	 */
	public static void saveMenu(String filename, List menuAl) throws IOException {
		List alw = new ArrayList();// to store Orders data
		for (int i = 0; i < menuAl.size(); i++) {
			if (menuAl.get(i) instanceof PromotionalPackage) {
				PromotionalPackage s1 = (PromotionalPackage) menuAl.get(i);
				StringBuilder st = new StringBuilder();
				st.append("PromoPackage");
				st.append(SEPARATOR);
				st.append(s1.getName());
				st.append(SEPARATOR);
				st.append(s1.getDescription());
				st.append(SEPARATOR);
				st.append(s1.getPrice());
				st.append(SEPARATOR);
				for (int i2 = 0; i2 < s1.getMenuItemArr().size(); i2++) {
					st.append(s1.getMenuItemArr().get(i2).getName());
					st.append(SEPARATOR);
					st.append(s1.getMenuItemArr().get(i2).getDescription());
					st.append(SEPARATOR);
					st.append(s1.getMenuItemArr().get(i2).getPrice());
					st.append(SEPARATOR);
					st.append(s1.getMenuItemArr().get(i2).getCategory());
					st.append(SEPARATOR);
				}
				alw.add(st.toString());
			} else if (menuAl.get(i) instanceof Alacarte) {
				Alacarte item = (Alacarte) menuAl.get(i);
				StringBuilder st = new StringBuilder();
				st.append("AlaCarte");
				st.append(SEPARATOR);
				st.append(item.getName().trim());
				st.append(SEPARATOR);
				st.append(item.getDescription().trim());
				st.append(SEPARATOR);
				st.append(item.getPrice());
				st.append(SEPARATOR);
				st.append(item.getCategory());
				alw.add(st.toString());
			}
		}
		write(filename, alw);
	}

	/**
	 * Reads in Staff from text file. Returns ArrayList[Staff].
	 * 
	 * @param filename FilePath
	 */
	public static ArrayList<Staff> readStaff(String filename) throws IOException {
		// read String from text file
		ArrayList stringArray = (ArrayList) read(filename);
		ArrayList<Staff> staffAl = new ArrayList<Staff>();
		for (int i = 0; i < stringArray.size(); i++) {
			String st = (String) stringArray.get(i);
			// get individual 'fields' of the string separated by SEPARATOR
			StringTokenizer star = new StringTokenizer(st, SEPARATOR); // pass in the string to the string tokenizer
																		// using delimiter ","
			int staffId = Integer.parseInt(star.nextToken().trim());
			String jobTitle = star.nextToken().trim(); 
			String staffName = star.nextToken().trim(); 
			String gender = star.nextToken().trim();
			// create Staff object from file data
			Staff item = new Staff(staffId, jobTitle, staffName, gender);
			// add to Staff array list
			staffAl.add(item);
		}
		return staffAl;
	}

	/**
	 * Saves ArrayList[Staff] to text file.
	 * 
	 * @param filename FilePath
	 * @param staffAl ArrayList[Staff]
	 */
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
	
	/**
	 * Reads in Table from text file. Returns ArrayList[Table].
	 * 
	 * @param filename FilePath
	 */
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
			// create Table object from file data
			Table table = new Table(tableNo, seatCap, tableStatus);
			// add to Table array list
			tableAl.add(table);
		}
		return tableAl;
	}

	/**
	 * Reads in Reservation from text file. Returns ArrayList[Reservation].
	 * 
	 * @param filename FilePath
	 */
	public static ArrayList<Reservation> readReservation(String filename) throws IOException {
		// read String from text file
		ArrayList stringArray = (ArrayList) read(filename);
		ArrayList<Reservation> reservationAl = new ArrayList<Reservation>();
		for (int i = 0; i < stringArray.size(); i++) {
			String st = (String) stringArray.get(i);
			// get individual 'fields' of the string separated by SEPARATOR
			StringTokenizer star = new StringTokenizer(st, SEPARATOR); // pass in the string to the string tokenizer
																		// using delimiter ","
			int contactNo = Integer.parseInt(star.nextToken().trim());
			String reservationDateTime = star.nextToken().trim();
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

	/**
	 * Save Reservation to textfile. Calls {@link saveCustomer(String,List)}.
	 * 
	 * @param filename FilePath
	 * @param reservationAl ArrayList[Reservation]
	 * @param custAl ArrayList[Customer]
	 */
	public static void saveReservations(String filename, List reservationAl, List custAl) throws IOException {
		List alw = new ArrayList();
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
		TextDB.saveCustomer("Customer.txt", custAl);
		write(filename, alw);
	}


	/**
	 * Reads in Invoice from text file (Deserialization). Returns ArrayList[Invoice].
	 * 
	 * @param filename FilePath
	 */
	public static ArrayList<Invoice> readInvoice(String filename) throws IOException {
		// read String from text file
		ArrayList<Invoice> invoiceAl = new ArrayList<Invoice>();
		try {
			FileInputStream fis = new FileInputStream(filename);
			ObjectInputStream ois = new ObjectInputStream(fis);
			invoiceAl = (ArrayList) ois.readObject();
			ois.close();
			fis.close();
		} catch (Exception e) {
			System.out.println(e);
		}
		return invoiceAl;
	}


	/**
	 * Save ArrayList[Invoice] to text file (Serialization).
	 * 
	 * @param filename FilePath
	 * @param invoiceAl ArrayList[Invoice]
	 */
	public static void saveInvoice(String filename, ArrayList<Invoice> invoiceAl) throws IOException {
		try {
			FileOutputStream fos = new FileOutputStream(filename);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(invoiceAl);
			oos.close();
			fos.close();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}


	/**
	 * Reads in Customer from text file. Returns ArrayList[Customer].
	 * 
	 * @param filename FilePath
	 */
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


	/**
	 * Saves ArrayList[Customer] to text file.
	 * 
	 * @param filename FilePath
	 * @param custAl ArrayList[Customer]
	 */
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


	/**
	 * Writes data to file.
	 * 
	 * @param filename FilePath
	 * @param data Data
	 */
	/** Write fixed content to the given file. */
	private static void write(String fileName, List data) throws IOException {
		PrintWriter out = new PrintWriter(new FileWriter(fileName));

		try {
			for (int i = 0; i < data.size(); i++) {
				out.println((String) data.get(i));
			}
		} finally {
			out.close();
		}
	}
	

	/**
	 * Delete everything within a file.
	 * 
	 * @param filename FilePath
	 */
	private static void deleteEverything(String fileName) throws IOException {
		PrintWriter out = new PrintWriter(new FileWriter(fileName));
		out.close();
	}
	
	/**
	 * Reads the content of the given file.
	 * 
	 * @param filename FilePath
	 */
	private static List read(String fileName) throws IOException {
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
