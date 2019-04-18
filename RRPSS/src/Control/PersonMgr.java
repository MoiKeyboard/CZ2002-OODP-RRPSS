package Control;

import java.util.ArrayList;

import Entity.Customer;
import Entity.Person;
import Entity.Staff;

/**
 * The {@code PersonMgr} is a control class used to model all control behavior
 * specific to the {@link Person} and its subclasses {@link Staff} and
 * {@link Customer}.
 * 
 * @author Joseph Fung King Yiu
 * @version 1.0
 * @since 2019-04-17
 */

public class PersonMgr {
	private ArrayList<Customer> custAl;
	private ArrayList<Staff> staffAl;

	/**
	 * Constructor for PersonMgr, calls {@link TextDB#readCustomer(String)} and
	 * {@link TextDB#readStaff(String)} to populate custAl and staffAl.
	 */
	public PersonMgr() {
		custAl = new ArrayList<Customer>();
		staffAl = new ArrayList<Staff>();
		try {
			custAl = TextDB.readCustomer("Customer.txt");
			staffAl = TextDB.readStaff("Staff.txt");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Returns custAl ({@code ArrayList<Customer>}).
	 */
	protected ArrayList<Customer> getCustAl() {
		return custAl;
	}

	/**
	 * Returns Customer based on contact number
	 * 
	 * @param contactNo Contact Number of Customer
	 */
	protected Customer findCustomer(int contactNo) {
		Customer customerResult = new Customer();
		for (Customer cust : custAl) {
			if (cust.getContactNumber() == contactNo) {
				customerResult = cust;
				break;
			}
		}
		return customerResult;
	}

	/**
	 * Deletes Customer in the ArrayList[Customer]
	 * 
	 * @param searchTerm Contact Number of Customer
	 */
	protected void removeCustomer(int searchTerm) {
		for (Customer cust : custAl) {
			if (cust.getContactNumber() == searchTerm) {
				custAl.remove(cust);
				break;
			}
		}
	}

	/**
	 * Returns ArrayList[Staff]
	 */
	protected ArrayList<Staff> getStaffAl() {
		return staffAl;
	}

	/**
	 * Prints out all the Staff in staffAl {@code ArrayList<Staff>}
	 */
	public void printStaff() {
		for (Staff staff : staffAl)
			System.out.println(staff.toString());
	}

	/**
	 * Returns the Index of the Staff Object in ArrayList[Staff] based on Staff ID
	 * 
	 * @param staffInput ID of Staff
	 */
	protected int getStaffIndex(int staffInput) {
		for (int i = 0; i < staffAl.size(); i++) {
			if (staffInput == staffAl.get(i).getStaffId())
				return i;
		}
		System.out.println("Staff not found");
		return -1;
	}
}