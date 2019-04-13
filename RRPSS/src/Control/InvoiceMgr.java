package Control;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

import Entity.Invoice;
import Entity.Menu;
import Entity.Order;

public class InvoiceMgr {
	private ArrayList<Invoice> invoiceAl;
	private static final double GST = 0.07; // CONSTANT GST 7%
	private static final double SC = 0.1; // CONSTANT SERVICE CHARGE 10%
	private Scanner sc;

	public InvoiceMgr() {
		sc = new Scanner(System.in);
		try {
			invoiceAl = TextDB.readInvoice("Invoices.txt");
			for (Invoice invoice : invoiceAl) {
				System.out.println(invoice.toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

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
		invoiceAl.add(currInvoice);
		try {
			TextDB.saveInvoice("Invoices.txt", invoiceAl);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

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

	private double computeTotalPrice(Order o) {
		double price = 0;
		for (Menu menu : o.getFoodAL()) {
			price += menu.getPrice();
		}
		return price;
	}

	// Option 1 - print revenue report for particular day , Option 2 - print revenue
	// report for particular month, Option 3 - return
	public void printSaleRevenueReport() {
		int optionInput;
		String periodDT;
		System.out.println("1) Sale Revenue Report by day");
		System.out.println("2) Sale Revenue Report by month");
		System.out.println("0) Back to previous screen");
		optionInput = Integer.parseInt(sc.nextLine());
		if (optionInput == 1) {
			System.out.println(">>Selected printing revenue report by day<<");
			
			System.out.println("Please enter date (E.g 10-04-2019)");
			//use 11-04-2019 to test.
			periodDT = sc.nextLine();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
			LocalDate saleReportPeriod = LocalDate.parse(periodDT, formatter);
			
			
			
			//use menu array list..put outside for loop so it counts 
			//unique items in ALL invoices, not just one invoice.
			ArrayList<Menu> suniqueList = new ArrayList<Menu>();
			// to count each unique item
			ArrayList<Integer> seachCount = new ArrayList<Integer>();
			//foodAL declaration
			ArrayList<Menu> foodAL = new ArrayList<Menu>();
			//need to add all the total sales.
			double salesTotal = 0.0;
			double subTotal = 0.0;
			double totalGST = 0.0;
			double totalSC = 0.0;
			double itemCount = 0.0; //debug
			int oldCount = 0;

			/*
			 * uniqueList: [menuItem1, menuItem2] eachCount: [1, 2]
			 */
			
			
			for (Invoice invoice : invoiceAl) {  //going into each individual invoice
				if (invoice.getInvoiceDT().getYear() == saleReportPeriod.getYear()
						&& invoice.getInvoiceDT().getDayOfYear() == saleReportPeriod.getDayOfYear()) {
					//reformatting to print as one big invoice.
					
					//for each invoice object, retrieve FoodAL
					foodAL = invoice.getFoodAL();
					//repeat the same thing done in invoice to count unique food
					//add each invoice's total price to salesTotal
					salesTotal += invoice.getTotalPrice();
					totalGST += invoice.getGST();
					totalSC += invoice.getServiceCharge();
					oldCount = 0;
					//loop through the various menu objects in invoice
					for (Menu menu : foodAL) {
						itemCount++;
						if (suniqueList.contains(menu)) {
							// if item is a repeat, add to respective count.
							oldCount = seachCount.get(suniqueList.indexOf(menu));
							oldCount ++;
							seachCount.set(suniqueList.indexOf(menu), oldCount);
						} else {
							// item is not a repeat, add to uniqueList and start eachCount at 1.
							
							suniqueList.add(menu);
							seachCount.add(1);
							
							
						}
					}
					//System.out.println(invoice.toString());
				}
			}
		
			//now all the lists are filled with unique items and their count respectively
			//start printing as per normal, like the invoice.
			String salesRep = "";
			String display = "";
			salesRep += "==========================================\n";
		//	System.out.println(saleReportPeriod);
			salesRep+="   Sales Revenue Report - " + saleReportPeriod+"\n";
			salesRep += "==========================================\n";
			
			for (int i = 0; i < suniqueList.size(); i++) {
				display = String.format("%-5d %-22s %5.2f\n", seachCount.get(i), suniqueList.get(i).getName(),
						suniqueList.get(i).getPrice());
				salesRep += display;

			}
						
			//System.out.println("[DEBUG]: total item count: "+itemCount);
			//System.out.println("[DEBUG]: suniqueList: ");
			//System.out.println(Arrays.toString(suniqueList.toArray()));
			//System.out.println("[DEBUG]: seachCount");
			//System.out.println(Arrays.toString(seachCount.toArray()));

			salesRep += "------------------------------------------\n";
			
			
			subTotal = salesTotal - totalGST - totalSC;

			display = String.format("                        SubTotal: %.2f", subTotal);
			salesRep += display;
			display = String.format("\n                        GST:%.2f \n                        Service Charge:%.2f\n",
					totalGST, totalSC);
			salesRep += display;

			salesRep += "------------------------------------------\n";
			display = String.format("                        TOTAL: %.2f\n", salesTotal);
			salesRep += display;
			salesRep += "\n==========================================\n";
			System.out.println(salesRep);
			
			
		} else if (optionInput == 2) {
			System.out.println("Please enter month and year in the following format (E.g 04-2019)");
			periodDT = sc.nextLine();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-yyyy");
			YearMonth ym = YearMonth.parse(periodDT, formatter);
			LocalDate saleReportPeriod = ym.atDay(1);
			for (Invoice invoice : invoiceAl) {
				if (invoice.getInvoiceDT().getYear() == saleReportPeriod.getYear()
						&& invoice.getInvoiceDT().getMonthValue() == saleReportPeriod.getMonthValue()) {
					System.out.println("Selected printing revenue report by month");
					System.out.println(invoice.toString());
				}
			}
		} else if (optionInput == 0)
			return;
	}

}