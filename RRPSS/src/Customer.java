
public class Customer extends Person {
	private String contactNumber;

	public Customer() {
		super(null, "");
	}

	public Customer(String name, String gender, String contactNumber) {
		super(name, gender);
		this.contactNumber = contactNumber;
	}

	public String getContactNumber() {
		return contactNumber;
	}

}
