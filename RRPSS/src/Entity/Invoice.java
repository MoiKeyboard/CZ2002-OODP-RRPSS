package Entity;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Locale;

public class Invoice implements Serializable {
	private int tableNo;
	private int staffID;
	private LocalDateTime invoiceDT;
	private double GST;
	private double serviceCharge;
	private double totalPrice;
	private long invoiceNo;
	private ArrayList<Menu> foodAL;

	public Invoice() {
		this.tableNo = 0;
		this.staffID = 0;
		this.invoiceDT = null;
		this.GST = 0;
		this.serviceCharge = 0;
		this.totalPrice = 0;
		this.invoiceNo = 0;
		foodAL = null;
	}

	public Invoice(int tableNo, int staffID, LocalDateTime invoiceDT,double GST, double serviceCharge,double price, long invoiceNo, ArrayList<Menu> foodAL) {
		this.tableNo = tableNo;
		this.staffID = staffID;
		this.invoiceDT = invoiceDT;
		this.GST =  Double.parseDouble(new DecimalFormat("#.##").format(GST));
		this.serviceCharge =  Double.parseDouble(new DecimalFormat("#.##").format(serviceCharge));
		this.totalPrice =  Double.parseDouble(new DecimalFormat("#.##").format(price));
		this.invoiceNo = invoiceNo;
		this.foodAL = foodAL;
	}

	public long getInvoiceNo() {
		return invoiceNo;
	}

	public int getTableNo() {
		return tableNo;
	}

	public LocalDateTime getInvoiceDT() {
		return invoiceDT;
	}
	
	public int getStaffID() {
		return staffID;
	}

	public double getGST() {
		return GST;
	}

	public double getServiceCharge() {
		return serviceCharge;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public ArrayList<Menu> getFoodAL() {
		return foodAL;
	}

	@Override
	public String toString() {
		String invoiceDetails = null;
		invoiceDetails = "Table Number: " + getTableNo() + "\nDate and Time of Invoice: " + invoiceDT.toString() + "\n";
		for(Menu menu : foodAL) {
			invoiceDetails += menu.toString();
		}
		invoiceDetails +=  "\nGST: " + getGST() + "\nService charge: " + getServiceCharge() + "\nTotal Price of order: " + getTotalPrice() + "\n";
		return invoiceDetails;
	}
}
