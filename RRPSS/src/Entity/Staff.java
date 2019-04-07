package Entity;
import java.util.ArrayList;

public class Staff extends Person {
	private int staffId;
	private String jobTitle;
	private String gender;
	
	public Staff() {
		super(null);
		this.staffId = 0;
		this.jobTitle = null;
		this.gender = null;
	}
	
	public Staff(int staffId, String jobTitle, String name,String gender) {
		super(name);
		this.staffId = staffId;
		this.jobTitle = jobTitle;	
		this.gender = gender;
	}

	public int getStaffId() {
		return staffId;
	}

	public void setStaffId(int staffId) {
		this.staffId = staffId;
	}

	public String getJobTitle() {
		return jobTitle;
	}

	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}
	
	public String getGender() {
		return gender;
	}

	@Override
	public String toString() {
		String staffDetails = null;
		staffDetails = "Staff Name: " + super.getName() +"\nGender: " + getGender() + "\nStaff ID: " + getStaffId() + "\nJob Title: " + getJobTitle();
		return staffDetails;
	}
}
