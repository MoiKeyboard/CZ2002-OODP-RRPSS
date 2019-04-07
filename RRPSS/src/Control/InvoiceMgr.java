package Control;
import java.time.LocalDateTime;
import java.util.ArrayList;

import Entity.Customer;
import Entity.Staff;
import Entity.Invoice;
import Entity.Order;
import Entity.Table;

public class InvoiceMgr {
	private ArrayList<Invoice> invoiceAl;
	private ArrayList<Table> tableAl;
	private ReservationMgr rMgr;
	private static final double GST = 0.07; // CONSTANT GST 7%
	private static final double serviceCharge = 0.1; // CONSTANT SERVICE CHARGE 10%
	
	public InvoiceMgr() {
		//invoiceAl = TextDB.readInvoice();
		try {	
			
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
   public void generateInvoice(OrderMgr orderMgr, TableMgr tableMgr) {
		Invoice currInvoice = new Invoice();

	}
   
	public int generateInvoiceNum() {
		return 0;
	}

	// Option 0 - print revenue report for particular day , Option 1 - print revenue report for particular month
	public void printSaleRevenueReport(LocalDateTime period, int option) {
		int month,day,year;
		double totalRevenue;
		if(option == 0) {
			day = period.getDayOfYear();
			month = period.getMonthValue();
			year = period.getYear();
			for(Invoice invoice : invoiceAl) {
				if(invoice.getInvoiceDT().getYear() == year && invoice.getInvoiceDT().getMonthValue() == month && invoice.getInvoiceDT().getDayOfYear() == day) {
					
				}
			}
		} else if (option == 1) {
			month = period.getMonthValue();
			year = period.getYear();
			for(Invoice invoice : invoiceAl) {
				if(invoice.getInvoiceDT().getYear() == year && invoice.getInvoiceDT().getMonthValue() == month) {
					
				}
			}
		}
	}
	
}