package Control;

import java.util.ArrayList;
import java.util.Scanner;

import Entity.Invoice;

public class InvoiceMgr {
	private ArrayList<Invoice> invoiceAL;
	protected Scanner sc;
	private static final double GST = 0.07; // CONSTANT GST 7%
	private static final double serviceCharge = 0.1; // CONSTANT SERVICE CHARGE 10%

	public InvoiceMgr() {
		invoiceAL = new ArrayList<Invoice>();
		sc = new Scanner(System.in);
		try {
			// read text file for invoice items
			// orderAl = TextDB.readOrder("Orders.txt");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void generateInvoice(OrderMgr orderMgr, TableMgr tableMgr) {
		Invoice currInvoice = new Invoice();

	}

}
