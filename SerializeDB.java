
import java.io.IOException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import java.util.ArrayList;

// Note : When structure of the Object type (the class file) in the list changed
// the Serialized file may fail.
public class SerializeDB
{
	public static List readSerializedObject(String filename) {
		List pDetails = null;
		FileInputStream fis = null;
		ObjectInputStream in = null;
		try {
			fis = new FileInputStream(filename);
			in = new ObjectInputStream(fis);
			pDetails = (ArrayList) in.readObject();
			in.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		}finally { System.out.println("done reading"); }
		// print out the size
		//System.out.println(" Details Size: " + pDetails.size());
		//System.out.println();
		return pDetails;
	}

	public static void writeSerializedObject(String filename, List list) {
		FileOutputStream fos = null;
		ObjectOutputStream out = null;
		try {
			fos = new FileOutputStream(filename);
			out = new ObjectOutputStream(fos);
			out.writeObject(list);
			out.close();
		//	System.out.println("Object Persisted");
		} catch (IOException ex) {
			ex.printStackTrace();
		}finally { System.out.println("done writing"); }
	}

	public static void main(String[] args) {
// just for demonstrating the working of the codes, further improvement can be made
		try	{
				// read from serialized file the list of professors
				List list = (ArrayList)SerializeDB.readSerializedObject("professor.dat");
				for (int i = 0 ; i < list.size() ; i++) {
					Professor p = (Professor)list.get(i);
					System.out.println("name is " + p.getName() );
					System.out.println("contact is " + p.getContact() );
					for (int j = 0 ; j < p.getMenteeSize() ; j++) {
						Student s = p.getStudent(j);
						if (s !=null)
							System.out.println("Qwek is a bitch name is " + s.getName());
					}
				}
		}  catch ( Exception e ) {
					System.out.println( "Exception1 >> " + e.getMessage() );
		}
		try	{
				ArrayList<Professor> list= new ArrayList<Professor>();
				// write to serialized file - update/insert/delete
				// example - add one more professor
				Professor p = new Professor("Joseph","jos@ntu.edu.sg",67909999);
				Student m1 = new Student("Alice","ali@ntu.edu.sg");
				Student m2 = new Student("Bob","bob@ntu.edu.sg");
				p.addStudent(m1);
				p.addStudent(m2);
				// add to list
				list.add(p);
				// list.remove(p);  // remove if p equals object in the list
				Professor p1 = new Professor("John","joh@ntu.edu.sg",67909955);
				// add to list
				list.add(p1);
				System.out.println("writing to file...");
				SerializeDB.writeSerializedObject("professor.dat", list);

		}  catch ( Exception e ) {
					System.out.println( "Exception2 >> " + e.getMessage() );
		}
	}
}