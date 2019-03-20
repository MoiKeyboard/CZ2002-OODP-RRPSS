
public class Customer extends Person {
	private String loyaltyStatus;
	
	public Customer() {
		super(null,"","0");
	}
	
	public Customer(String loyaltyStatus,String name, String gender, String contactNumber) {
		super(name,gender,contactNumber);
		this.loyaltyStatus = loyaltyStatus;
	}
}
