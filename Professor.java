import java.io.Serializable;
import java.util.ArrayList;

public class Professor implements Serializable {
	private String name ;
	private String email ;
	private int contact ;
	private ArrayList<Student> sList;
	public Professor(String n, String e, int c)  {
		name = n ;
		email = e ;
		contact = c ;
		sList = new ArrayList<Student>();
	}
	public String getName() { return name ; }
	public int getContact() { return contact ; }
	public String getEmail() { return email ; }
	public int getMenteeSize() { return sList.size() ; }
	public void addStudent(Student s) { sList.add(s); }
	public Student getStudent(int i) { return sList.get(i); }

	public boolean equals(Object o) {
		if (o instanceof Professor) {
			Professor p = (Professor)o;
			return (getName().equals(p.getName()));
		}
		return false;
	}
}