package Entity;
import java.util.ArrayList;

public class Staff extends Person {
	private int staffId;
	private String jobTitle;
	
	public Staff() {
		super(null);
		this.staffId = 0;
		this.jobTitle = null;
	}
	
	public Staff(int staffId, String jobTitle, String name) {
		super(name);
		this.staffId = staffId;
		this.jobTitle = jobTitle;	
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
	
}
