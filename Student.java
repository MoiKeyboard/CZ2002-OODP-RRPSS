import java.io.Serializable;
import java.util.ArrayList;

public class Student implements Serializable {
	private String name ;
	private String email ;

	public Student(String n, String e)  {
		name = n ;
		email = e ;
	}
	public String getName() { return name ; }
	public String getEmail() { return email ; }
}