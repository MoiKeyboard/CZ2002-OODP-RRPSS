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
		String display = "";
		
		//or just use menu array list..
		ArrayList<Menu> uniqueList = new ArrayList <Menu>();
		//to count each unique item
		ArrayList<Integer> eachCount = new ArrayList <Integer>();
		
		
		/*
		 * uniqueList: [menuItem1, menuItem2]
		 * eachCount: [1, 2]
		 */
		
		
		invoiceDetails = "============ Oops Bar & Cafe ============\n"
					    +"        50 Nanyang Ave, 639798\n"
					    +"               SCSE, NTU\n"
					    +"Table: " + getTableNo() + "\nDate/time: " 
				        + invoiceDT.toString() 
				      +"\n------------------------------------------\n";
						
		for(Menu menu : foodAL) {
			
			if (uniqueList.contains(menu)){
			//if item is a repeat, add to respective count.
				eachCount.add(uniqueList.indexOf(menu),
						eachCount.get(uniqueList.indexOf(menu))+1);
			}
			else {
			//item is not a repeat, add to uniqueList and start eachCount at 1.
				uniqueList.add(menu);
				eachCount.add(1);
			
			}
		}
		
		for (int i = 0; i<uniqueList.size(); i++) {
			display = String.format("%-5d %-22s %5.2f\n", eachCount.get(i), 
									uniqueList.get(i).getName(),
									uniqueList.get(i).getPrice());
			invoiceDetails += display;
			
		}
		
		invoiceDetails += "------------------------------------------";
		display = String.format("                          SubTotal: %.2d",
				(getTotalPrice()-(getGST()+getServiceCharge())));
		invoiceDetails += display;
		invoiceDetails +=  "\n                          GST: " 
							+ getGST() 
							+ "\nService Charge: " 
							+ getServiceCharge() 
							+ "\nTotal: " 
							+ getTotalPrice() + "\n";
		
		invoiceDetails += "------------------------------------------";
		display= String.format("                  TOTAL: %.2d\n",getTotalPrice());
		invoiceDetails += "==========================================\n";
		invoiceDetails += "Thank you for dropping by Oops (I did it again) Bar & Cafe."
						+ "                   *****                    ";
		invoiceDetails += display;
		return invoiceDetails;
	}
}
