package Entity;

public class Customer extends Person {
	private int contactNumber;

	public Customer() {
		super(null);
	}

	public Customer(String name, int contactNumber) {
		super(name);
		this.contactNumber = contactNumber;
	}

	public int getContactNumber() {
		return contactNumber;
	}
	
	@Override
	public String toString() {
		String custDetails = null;
		custDetails = "Customer Name: " + super.getName() + "\nCustomer contact number: " + getContactNumber() +"\n";
		return custDetails;
	}
}
