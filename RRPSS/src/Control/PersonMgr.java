package Control;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	protected ArrayList<Customer> getCustAl(){
		return custAl;
	}
	
	protected Customer findCustomer(int contactNo) {
		Customer customerResult = new Customer();
		for(Customer cust : custAl) {
			if(cust.getContactNumber() == contactNo) {
				customerResult = cust;
				break;
			}
		}
		return customerResult;
	}
	
	protected void removeCustomer(int searchTerm) {
		for(Customer cust : custAl){{
			if(cust.getContactNumber() == searchTerm) {
				custAl.remove(cust);
				break;
				}
			}
		}
	}
	
	public ArrayList<Staff> getStaffAl() {
		return this.staffAl;
	}
	
	public void printStaff() {
		for (Staff staff : staffAl)
			System.out.println(staff.toString());
	}
	
	/*public void findStaff(int staffid) throws IOException {
		for(Staff staff : staffAl) {
			if(staff.getStaffId() == staffid) {
				
				break;
			}
		}
	}*/
	
}