package Entity;

public class Invoice {
	private int tableNo;
	private int date;
	private int time;
	private double price;
	
	public Invoice() {
		this.tableNo = 0;
		this.date = 0;
		this.time = 0;
		this.price = 0;
	}
	
	public Invoice(int tableNo, int date, int time) {
		this.tableNo = tableNo;
		this.date = date;
		this.time = time;
	}
 
}
