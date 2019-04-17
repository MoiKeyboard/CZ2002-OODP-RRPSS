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

	/**
	 * Default constructor for {@code Customer}, calls superclass {@link Person}
	 * constructor.
	 */
	public Customer() {
		super(null);
	}

	/**
	 * Constructor for {@code Customer}, calls superclass constructor {@link Person}
	 * and creates a {@code Customer} object with required parameters
	 * 
	 * @param name          Name of {@code Customer}
	 * @param contactNumber Contact number of {@code Customer}
	 */
	public Customer(String name, int contactNumber) {
		super(name);
		this.contactNumber = contactNumber;
	}

	/**
	 * @return Contact number of {@code Customer} object.
	 */
	public int getContactNumber() {
		return contactNumber;
	}

	/**
	 * Indicates whether some other {@code Customer} object is "equal to" this one.
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 * 
	 */
	@Override
	public String toString() {
		String custDetails = null;
		custDetails = "Customer Name: " + super.getName() + "\nCustomer contact number: " + getContactNumber() + "\n";
		return custDetails;
	}
}
