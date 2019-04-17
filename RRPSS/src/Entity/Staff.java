package Entity;

/**
 * The {@code Staff} entity class is an object wrapper. Subclass of
 * {@link Person}.
 * <p>
 * Contains primitive information related to a staff.
 * </p>
 * 
 * @author Qwek Zhi Hui
 * @version 1.0
 * @since 2019-04-13
 */
public class Staff extends Person {
	private int staffId;
	private String jobTitle;
	private String gender;

	/**
	 * Default constructor for {@code Staff}. Calls superclass {@link Person}
	 * constructor.
	 */
	public Staff() {
		super(null);
		this.staffId = 0;
		this.jobTitle = null;
		this.gender = null;
	}

	/**
	 * Constructor for {@code Staff}. Calls superclass constructor {@link Person}
	 * and creates a {@code Staff} object with required parameters.
	 * 
	 * @param staffId  Identification number of {@code Staff}
	 * @param jobTitle Title of {@code Staff}
	 * @param name     name of {@code Staff}
	 * @param gender   gender of {@code Staff}
	 */
	public Staff(int staffId, String jobTitle, String name, String gender) {
		super(name);
		this.staffId = staffId;
		this.jobTitle = jobTitle;
		this.gender = gender;
	}

	/**
	 * Returns staff identification number of {@code Staff} object.
	 * 
	 * @return the integer value of staffId
	 */
	public int getStaffId() {
		return staffId;
	}

	/**
	 * Updates the staff identication number of {@code Staff} object.
	 * 
	 * @param staffId the updated staffId integer value
	 */
	public void setStaffId(int staffId) {
		this.staffId = staffId;
	}

	/**
	 * Returns job title of {@code Staff} object.
	 * 
	 * @return the String of jobTitle
	 */
	public String getJobTitle() {
		return jobTitle;
	}

	/**
	 * Updates the job title of {@code Staff} object.
	 * 
	 * @param jobTitle the updated jobTitle String value
	 */
	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}

	/**
	 * Returns the gender of {@code Staff} object.
	 * 
	 * @return the String of gender
	 */
	public String getGender() {
		return gender;
	}

	/**
	 * Returns the string resepresentation of the {@code Staff} object.
	 */
	@Override
	public String toString() {
		String staffDetails = null;
		staffDetails = "Staff Name: " + super.getName() + "\nGender: " + getGender() + "\nStaff ID: " + getStaffId()
				+ "\nJob Title: " + getJobTitle();
		return staffDetails;
	}
}
