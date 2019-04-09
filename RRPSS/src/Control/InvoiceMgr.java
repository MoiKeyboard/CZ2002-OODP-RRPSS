package Control;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import Entity.Alacarte;
import Entity.Customer;
import Entity.Staff;
import Entity.Invoice;
import Entity.Menu;
import Entity.Order;
import Entity.PromotionalPackage;
import Entity.Table;

public class InvoiceMgr {
	private ArrayList<Invoice> invoiceAl;
	private ArrayList<Table> tableAl;
	private ReservationMgr rMgr;
	private static final double GST = 0.07; // CONSTANT GST 7%
	private static final double serviceCharge = 0.1; // CONSTANT SERVICE CHARGE 10%
	protected Scanner sc;
	
	public InvoiceMgr() {
		sc = new Scanner(System.in);
		try {	
			invoiceAl = TextDB.readInvoice("Invoices.txt");
			for(Invoice invoice : invoiceAl) {
				System.out.println(invoice.toString());
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
   public void generateInvoice(OrderMgr orderMgr, TableMgr tableMgr) {
	    int tableNo = 0;
		LocalDateTime timeStamp = LocalDateTime.now();
		System.out.println("Please enter the table no: ");
		tableNo = Integer.parseInt(sc.nextLine());
		Order o = orderMgr.getOrderAL().get(orderMgr.getOrderIndex(tableNo));
		Invoice currInvoice = new Invoice(tableNo,o.getStaffId(),timeStamp,(computeTotalPrice(o) * GST),(computeTotalPrice(o) * serviceCharge),
				((computeTotalPrice(o) * (1+GST+serviceCharge))),generateInvoiceNum(),o.getFoodAL());
		System.out.println(currInvoice.toString());
		invoiceAl.add(currInvoice);
		try {
			TextDB.saveInvoice("Invoices.txt", invoiceAl);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
   
	public long generateInvoiceNum() {
		long invoiceNum = 0;
		Random rand = new Random();
		LocalDateTime timeStamp = LocalDateTime.now();
	   	invoiceNum = (long) ((timeStamp.getYear() * Math.pow(10, 11)) + (timeStamp.getMonthValue() * Math.pow(10, 9)) 
	   			+(timeStamp.getDayOfMonth() * Math.pow(10, 7)) + (timeStamp.getHour() * Math.pow(10, 5)) 
	   			+(timeStamp.getMinute() * Math.pow(10, 3)) + (timeStamp.getSecond() * Math.pow(10, 1)) + rand.nextInt(10));
		return invoiceNum;
	}
	
	public double computeTotalPrice(Order o) {
		double price = 0;
		for(Menu menu : o.getFoodAL()) {
			price += menu.getPrice();
		}
		return price;
	}

	// Option 1 - print revenue report for particular day , Option 2 - print revenue report for particular month, Option 3 - return
	public void printSaleRevenueReport() {
		int optionInput;
		String periodDT;
		System.out.println("1) Sale Revenue Report by day");
		System.out.println("2) Sale Revenue Report by month");
		System.out.println("0) Back to previous screen");
		optionInput = Integer.parseInt(sc.nextLine());
		if(optionInput == 1) {
			System.out.println("Please enter date (E.g 10-04-2019)");
			periodDT = sc.nextLine();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
			LocalDate saleReportPeriod = LocalDate.parse(periodDT, formatter);
			System.out.println(saleReportPeriod);
			for(Invoice invoice : invoiceAl) {
				if(invoice.getInvoiceDT().getYear() == saleReportPeriod.getYear()  && invoice.getInvoiceDT().getDayOfYear() == saleReportPeriod.getDayOfYear()) {
					System.out.println("Selected printing revenue report by day");
					System.out.println(invoice.toString());
				}
			}
		} else if (optionInput == 2) {
			System.out.println("Please enter month and year in the following format (E.g 04-2019)");
			periodDT = sc.nextLine();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-yyyy");
			YearMonth ym = YearMonth.parse(periodDT, formatter);
			LocalDate saleReportPeriod = ym.atDay(1);
			for(Invoice invoice : invoiceAl) {
				if(invoice.getInvoiceDT().getYear() == saleReportPeriod.getYear() && invoice.getInvoiceDT().getMonthValue() == saleReportPeriod.getMonthValue()) {
					System.out.println("Selected printing revenue report by month");
					System.out.println(invoice.toString());
				}
			}
		} else if (optionInput == 0) 
			return;
	}
	
}