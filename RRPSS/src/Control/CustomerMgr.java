package Control;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

import Entity.Customer;

public class CustomerMgr {
	private ArrayList<Customer> custAl;
	
	public CustomerMgr() {
		custAl = new ArrayList<Customer>();
		try {
			custAl = TextDB.readCustomer("Customer.txt");
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
}
	