package Control;

import java.io.EOFException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import Entity.Invoice;
import Entity.Menu;
import Entity.Order;
import Entity.Table;

/**
 * The {@code InvoiceMgr} is a control class used to model all control behavior
 * specific to the {@link Invoice}.
 * 
 * @author Tay Jaslyn
 * @version 1.0
 * @since 2019-04-17
 */

public class InvoiceMgr {
	private ArrayList<Invoice> invoiceAl;
	private static final double GST = 0.07; // CONSTANT GST 7%
	private static final double SC = 0.1; // CONSTANT SERVICE CHARGE 10%
	private Scanner sc;

	/**
	 * Constructor for {@code InvoiceMgr}. Calls {@link TextDB#readInvoice(String)}
	 * to read from file and populate {@code ArrayList<Invoice>} .
	 */
	public InvoiceMgr() {
		sc = new Scanner(System.in);

		try {
			invoiceAl = TextDB.readInvoice("Invoices.txt");
			for (Invoice invoice : invoiceAl) {
				System.out.println(invoice.toString());
			}
		} catch (EOFException e) {
			System.out.println("Reservation.txt is empty");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Creates Invoice based on a currently occupied {@link Table}. Calls
	 * {@link generateInvoiceNum()} and {@link computeTotalPrice(Order)} to obtain
	 * invoice number and total price.
	 * <p>
	 * Adds to the existing {@code ArrayList<Invoice>} upon creation of
	 * {@link Invoice}. After which it is saved to the {@code Invoice} text file by
	 * calling {@link TextDB#saveInvoice(String, ArrayList)}.
	 * <p>
	 * Vacates table by calling {@link TableMgr#updateTableStatus(int, String)} upon
	 * creation of {@code Invoice} as well.
	 * 
	 * @param orderMgr Control of OrderMgr
	 * @param tableMgr Control of TableMgr
	 */
	public void generateInvoice(OrderMgr orderMgr, TableMgr tableMgr) {
		int tableNo = 0;
		LocalDateTime timeStamp = LocalDateTime.now();
		System.out.println("Please enter the table no: ");
		tableNo = Integer.parseInt(sc.nextLine());
		Order o = orderMgr.getOrderAL().get(orderMgr.getOrderIndex(tableNo));
		Invoice currInvoice = new Invoice(tableNo, o.getStaffId(), timeStamp, (computeTotalPrice(o) * GST),
				(computeTotalPrice(o) * SC), ((computeTotalPrice(o) * (1 + GST + SC))), generateInvoiceNum(),
				o.getFoodAL());
		System.out.println(currInvoice.toString());
		orderMgr.removeOrder(tableMgr, tableNo);
		invoiceAl.add(currInvoice);
		try {
			TextDB.saveInvoice("Invoices.txt", invoiceAl);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Creates and returns a new Invoice Number based on the current time stamp.
	 * <p>
	 * Format of invoice number (YearMonthDayHourMinuteSecondRandomInt)
	 */
	private long generateInvoiceNum() {
		long invoiceNum = 0;
		Random rand = new Random();
		LocalDateTime timeStamp = LocalDateTime.now();
		invoiceNum = (long) ((timeStamp.getYear() * Math.pow(10, 11)) + (timeStamp.getMonthValue() * Math.pow(10, 9))
				+ (timeStamp.getDayOfMonth() * Math.pow(10, 7)) + (timeStamp.getHour() * Math.pow(10, 5))
				+ (timeStamp.getMinute() * Math.pow(10, 3)) + (timeStamp.getSecond() * Math.pow(10, 1))
				+ rand.nextInt(10));
		return invoiceNum;
	}

	/**
	 * Returns the total price of all the food items. The total price is caculated
	 * from the {@link Order} {@code foodAL ArrayList<Menu>}.
	 * 
	 * @param o {@code Order} object used for calculating total price
	 */
	private double computeTotalPrice(Order o) {
		double price = 0;
		for (Menu menu : o.getFoodAL()) {
			price += menu.getPrice();
		}
		return price;
	}

	/**
	 * Prints Sale Revenue Report for a particular Day or Month based on Users
	 * Input.
	 * <p>
	 * It will print out the following during that time period
	 * <ul>
	 * <li>Name of {@link Menu} item sold
	 * <li>Count of {@link Menu} items sold
	 * <li>Total GST charge
	 * <li>Total service chargee
	 * <li>Gross revenue
	 */
	// Option 1 - print revenue report for particular day , Option 2 - print revenue
	// report for particular month, Option 3 - return
	public void printSaleRevenueReport() {
		int optionInput;
		String periodDT;
		System.out.println("1) Sale Revenue Report by day");
		System.out.println("2) Sale Revenue Report by month");
		System.out.println("0) Back to previous screen");
		optionInput = Integer.parseInt(sc.nextLine());

		if (optionInput == 1 || optionInput == 2) {
			String salesRep = "";
			String display = "";
			salesRep += "============ Oops Bar & Cafe ============\n" + "        50 Nanyang Ave, 639798\n"
					+ "               SCSE, NTU\n" + "\nGenerated: " + LocalDateTime.now() + "\n";
			// asks for and sets appropriate saleReportPeriod
			LocalDate saleReportPeriod = null;
			if (optionInput == 1) {
				System.out.println(">>Selected printing revenue report by day<<");
				System.out.println("Please enter date (E.g 10-04-2019)");
				periodDT = sc.nextLine();
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
				saleReportPeriod = LocalDate.parse(periodDT, formatter);
				salesRep += "==========================================\n";
				salesRep += " Daily Sales Revenue Report - " + saleReportPeriod + "\n";
				salesRep += "==========================================\n";
			} else {
				System.out.println(">>Selected printing revenue report by month<<");
				System.out.println("Please enter month and year in the following format (E.g 04-2019)");
				periodDT = sc.nextLine();
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-yyyy");
				YearMonth ym = YearMonth.parse(periodDT, formatter);
				saleReportPeriod = ym.atDay(1);
				salesRep += "==========================================\n";
				salesRep += " Monthly Sales Revenue Report - " + ym + "\n";
				salesRep += "==========================================\n";
			}

			// use menu array list..put outside for loop so it counts
			// unique items in ALL invoices, not just one invoice.
			ArrayList<Menu> suniqueList = new ArrayList<Menu>();
			ArrayList<Integer> seachCount = new ArrayList<Integer>();
			ArrayList<Menu> foodAL = new ArrayList<Menu>();
			double salesTotal = 0.0;
			double subTotal = 0.0;
			double totalGST = 0.0;
			double totalSC = 0.0;
			int oldCount = 0;
			/*
			 * suniqueList: [menuItem1, menuItem2] seachCount: [1, 2]
			 */

			// for loop goes into each individual invoice
			for (Invoice invoice : invoiceAl) {
				// if option 1, will filter by date. if option 2, will filter by month.
				if ((optionInput == 1 && invoice.getInvoiceDT().getYear() == saleReportPeriod.getYear()
						&& invoice.getInvoiceDT().getDayOfYear() == saleReportPeriod.getDayOfYear())
						|| (optionInput == 2 && invoice.getInvoiceDT().getYear() == saleReportPeriod.getYear()
								&& invoice.getInvoiceDT().getMonthValue() == saleReportPeriod.getMonthValue())) {
					// reformatting to print as one big invoice.

					// for each invoice object, retrieve FoodAL
					foodAL = invoice.getFoodAL();
					salesTotal += invoice.getTotalPrice();
					totalGST += invoice.getGST();
					totalSC += invoice.getServiceCharge();
					oldCount = 0;
					// loop through the various menu objects in invoice
					for (Menu menu : foodAL) {
						if (suniqueList.contains(menu)) {
							// if item is a repeat, add to respective count.
							oldCount = seachCount.get(suniqueList.indexOf(menu));
							oldCount++;
							seachCount.set(suniqueList.indexOf(menu), oldCount);
						} else {
							// item is not a repeat, add to uniqueList and start eachCount at 1.
							suniqueList.add(menu);
							seachCount.add(1);
						}
					}
				}
			}

			for (int i = 0; i < suniqueList.size(); i++) {
				display = String.format("%-5d %-22s %5.2f\n", seachCount.get(i), suniqueList.get(i).getName(),
						suniqueList.get(i).getPrice());
				salesRep += display;

			}
			salesRep += "------------------------------------------\n";
			subTotal = salesTotal - totalGST - totalSC;
			display = String.format("                        SubTotal: %.2f", subTotal);
			salesRep += display;
			display = String.format(
					"\n                        GST:%.2f \n                        Service Charge:%.2f\n", totalGST,
					totalSC);
			salesRep += display;
			salesRep += "------------------------------------------\n";
			display = String.format("                        TOTAL: %.2f\n", salesTotal);
			salesRep += display;
			salesRep += "\n==========================================\n";
			System.out.println(salesRep);

		} else {
			System.out.println("...returning to main screen...");
			return;
		}
	}

}