package Entity;

/**
 * The {@code Customer} entity class is an object wrapper. Subclass of
 * {@link Person}.
 * <p>
 * Contains primitive information related to a customer (e.g customer name,
 * customer contact number).
 * </p>
 * 
 * @author Qwek Zhi Hui
 * @version 1.0
 * @since 2019-04-13
 */
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
		custDetails = "Customer Name: " + super.getName() + "\nCustomer contact number: " + getContactNumber() + "\n";
		return custDetails;
	}
}
