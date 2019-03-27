package Entity;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class Invoice {
	private int tableNo;
	private LocalDateTime invoiceDT;
	private double price;

	public Invoice() {
		this.tableNo = 0;
		this.invoiceDT = null;
		this.price = 0;
	}
	
	public Invoice(int tableNo, LocalDateTime invoiceDT) {
		this.tableNo = tableNo;
		this.invoiceDT = invoiceDT;
	} 
 
	public int getTableNo() {
		return tableNo;
	}

	public LocalDateTime getInvoiceDT() {
		return invoiceDT;
	}

	public double getPrice() {
		return price;
	}

	@Override
	public String toString() {
		String invoiceDetails = null;
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yy kkmm", Locale.ENGLISH);
		invoiceDetails =  "Table Number: " + getTableNo() + "\nDate and Time of Invoice" + invoiceDT.format(formatter) + "\nPrice of order" + getPrice() + "\n";
		return invoiceDetails;
		
	}
}
