
public class Staff extends Person {
	int staffId;
	String jobTitle;
	
	public Staff() {
		super(null,"","0");
		this.staffId = 0;
		this.jobTitle = null;
	}
	
	public Staff(int staffId, String jobTitle, String name, String gender, String contactNum) {
		super(name,gender,contactNum);
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
