package Entity;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * The {@code Invoice} entity class is an object wrapper.
 * <p>
 * Contains primitive information related to an invoice (e.g Invoice
 * number,table number of invoice, etc).
 * </p>
 * 
 * @author Qwek Zhi Hui
 * @version 1.0
 * @since 2019-04-13
 */
public class Invoice implements Serializable {
	private int tableNo;
	private int staffID;
	private LocalDateTime invoiceDT;
	private double GST;
	private double serviceCharge;
	private double totalPrice;
	private long invoiceNo;
	private ArrayList<Menu> foodAL;

	/**
	 * Default constructor for {@code Invoice}.
	 */
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

	/**
	 * Constructor for {@code Invoice}, creates an {@code Invoice} object with
	 * required parameters
	 * 
	 * @param tableNo       Table Number of table used for generating the invoice.
	 * @param staffID       Staff Id of staff that creates the invoice.
	 * @param invoiceDT     Date and time of invoice.
	 * @param GST           Government Service Tax for calculating total price.
	 * @param serviceCharge Service Tax for calculating total price.
	 * @param price         Total price of invoice.
	 * @param invoiceNo     Invoice identifier number.
	 * @param foodAL        {@code ArrayList} of {@link Menu} items within the
	 *                      invoice.
	 */
	public Invoice(int tableNo, int staffID, LocalDateTime invoiceDT, double GST, double serviceCharge, double price,
			long invoiceNo, ArrayList<Menu> foodAL) {
		this.tableNo = tableNo;
		this.staffID = staffID;
		this.invoiceDT = invoiceDT;
		this.GST = Double.parseDouble(new DecimalFormat("#.##").format(GST));
		this.serviceCharge = Double.parseDouble(new DecimalFormat("#.##").format(serviceCharge));
		this.totalPrice = Double.parseDouble(new DecimalFormat("#.##").format(price));
		this.invoiceNo = invoiceNo;
		this.foodAL = foodAL;
	}

	/**
	 * Returns the invoice identfier number of {@code Invoice} object.
	 * 
	 * @return the String of invoiceNumber
	 */
	public long getInvoiceNo() {
		return invoiceNo;
	}

	/**
	 * Returns the table number used in generating {@code Invoice} object.
	 * 
	 * @return the integer value of tableNo
	 */
	public int getTableNo() {
		return tableNo;
	}

	/**
	 * Returns the date and time used in generating {@code Invoice} object.
	 * 
	 * @return the {@linkplain LocalDateTime}
	 */
	public LocalDateTime getInvoiceDT() {
		return invoiceDT;
	}

	/**
	 * Returns the staff identification number that generates the {@code Invoice}
	 * object.
	 * 
	 * @return the integer value of staffID
	 */
	public int getStaffID() {
		return staffID;
	}

	/**
	 * Returns the government service tax used in calculating total price of
	 * {@code Invoice} object.
	 * 
	 * @return the decimal value of GST
	 */
	public double getGST() {
		return GST;
	}

	/**
	 * Returns the service tax used in calculating total price of {@code Invoice}
	 * object.
	 * 
	 * @return the decimal value of service tax
	 */
	public double getServiceCharge() {
		return serviceCharge;
	}

	/**
	 * Returns the total price of {@code Invoice} object.
	 * 
	 * @return the decimal value of service tax
	 */
	public double getTotalPrice() {
		return totalPrice;
	}

	/**
	 * Returns the {@code ArrayList} of {@link Menu} items within the
	 * {@code Invoice} object.
	 * 
	 * @return the {@link Menu} {@code ArrayList} of foodAL
	 */
	public ArrayList<Menu> getFoodAL() {
		return foodAL;
	}

	/**
	 * Returns the string resepresentation of the {@code Invoice} object.
	 */
	@Override
	public String toString() {

		String invoiceDetails = null;
		String display = "";
		double subTotal = 0.0;
		double total = 0.0;

		// or just use menu array list..
		ArrayList<Menu> uniqueList = new ArrayList<Menu>();
		// to count each unique item
		ArrayList<Integer> eachCount = new ArrayList<Integer>();

		invoiceDetails = "============ Oops Bar & Cafe ============\n" + "        50 Nanyang Ave, 639798\n"
				+ "               SCSE, NTU\n" + "Table: " + getTableNo() + "\nDate/time: " + invoiceDT.toString()
				+ "\n------------------------------------------\n";

		for (Menu menu : foodAL) {
			if (uniqueList.contains(menu)) {
				// if item is a repeat, add to respective count.
				// .set not .add
				eachCount.set(uniqueList.indexOf(menu), eachCount.get(uniqueList.indexOf(menu)) + 1);
			} else {
				// item is not a repeat, add to uniqueList and start eachCount at 1.
				uniqueList.add(menu);
				eachCount.add(1);

			}
		}

		for (int i = 0; i < uniqueList.size(); i++) {
			display = String.format("%-5d %-22s %5.2f\n", eachCount.get(i), uniqueList.get(i).getName(),
					uniqueList.get(i).getPrice());
			invoiceDetails += display;

		}

		invoiceDetails += "------------------------------------------\n";
		subTotal = getTotalPrice() - (getGST() + getServiceCharge());

		display = String.format("                        SubTotal: %.2f", subTotal);
		invoiceDetails += display;
		display = String.format("\n                        GST:%.2f \n                        Service Charge:%.2f\n",
				getGST(), getServiceCharge());

		invoiceDetails += display;

		invoiceDetails += "------------------------------------------\n";

		total = getTotalPrice();
		display = String.format("                        TOTAL: %.2f\n", total);
		invoiceDetails += display;
		invoiceDetails += "\n==========================================\n";
		invoiceDetails += "Thank you for your patronage!\n" + "                   *****                    ";

		return invoiceDetails;
	}
}
