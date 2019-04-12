package Control;

import java.util.ArrayList;

import Entity.Customer;
import Entity.Staff;

public class PersonMgr {
	private ArrayList<Customer> custAl;
	private ArrayList<Staff> staffAl;

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

	protected ArrayList<Customer> getCustAl() {
		return custAl;
	}

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

	protected void removeCustomer(int searchTerm) {
		for (Customer cust : custAl) {
			if (cust.getContactNumber() == searchTerm) {
				custAl.remove(cust);
				break;
			}
		}
	}

	protected ArrayList<Staff> getStaffAl() {
		return staffAl;
	}

	public void printStaff() {
		for (Staff staff : staffAl)
			System.out.println(staff.toString());
	}

	protected int getStaffIndex(int staffInput) {
		for (int i = 0; i < staffAl.size(); i++) {
			if (staffInput == staffAl.get(i).getStaffId())
				return i;
		}
		return -1;
	}
}