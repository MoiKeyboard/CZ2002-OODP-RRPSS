
public class Person {
	private String name, gender, contactNum;
	
	public Person() {
		name = null;
		gender = "";
		contactNum = "0";
	}
	
	
	public Person(String name, String gender, String contactNum) {
		this.name = name;
		this.gender = gender;
		this.contactNum = contactNum;
		
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getGender() {
		return gender;
	}


	public void setGender(String gender) {
		this.gender = gender;
	}


	public String getContactNum() {
		return contactNum;
	}


	public void setContactNum(String contactNum) {
		this.contactNum = contactNum;
	}
}
