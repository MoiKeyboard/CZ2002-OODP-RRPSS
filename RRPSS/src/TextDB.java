import java.io.IOException;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.FileInputStream;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class TextDB {
	public static final String SEPARATOR = "|";

    // an example of reading
	public static void readMenuItem(String filename,ArrayList<MenuItem> al) throws IOException {
		// read String from text file
		ArrayList stringArray = (ArrayList)read(filename);

        for (int i = 0 ; i < stringArray.size() ; i++) {
				String st = (String)stringArray.get(i);
				// get individual 'fields' of the string separated by SEPARATOR
				StringTokenizer star = new StringTokenizer(st , SEPARATOR);	// pass in the string to the string tokenizer using delimiter ","

				String  foodType = star.nextToken().trim();	// first token
				String  foodName = star.nextToken().trim();	// second token
				String description = star.nextToken().trim(); // third token
				double price = Double.parseDouble(star.nextToken().trim());
				// create Professor object from file data
				MenuItem item = new MenuItem(foodType, foodName ,description,price);
				// add to Professors list
				al.add(item) ;
			}
	}

  // an example of saving
public static void saveMenuItem(String filename, List al) throws IOException {
		List alw = new ArrayList() ;// to store Professors data

        for (int i = 0 ; i < al.size() ; i++) {
				MenuItem item = (MenuItem)al.get(i);
				StringBuilder st =  new StringBuilder() ;
				st.append(item.getFoodType().trim());
				st.append(SEPARATOR);
				st.append(item.getFoodName().trim());
				st.append(SEPARATOR);
				st.append(item.getDescription().trim());
				st.append(SEPARATOR);
				st.append(item.getPrice());
				alw.add(st.toString()) ;
			}
			write(filename,alw);
	}

public static void readStaff(String filename,ArrayList<Staff> staffAl) throws IOException {
	// read String from text file
	ArrayList stringArray = (ArrayList)read(filename);

    for (int i = 0 ; i < stringArray.size() ; i++) {
			String st = (String)stringArray.get(i);
			// get individual 'fields' of the string separated by SEPARATOR
			StringTokenizer star = new StringTokenizer(st , SEPARATOR);	// pass in the string to the string tokenizer using delimiter ","
			int  staffId = Integer.parseInt(star.nextToken().trim());	// first token
			String  jobTitle = star.nextToken().trim();	// second token
			String  staffName = star.nextToken().trim();	// third token
			String  gender = star.nextToken().trim(); // fourth token 
			// create Staff object from file data
			Staff item = new Staff(staffId,jobTitle,staffName,gender);
			// add to Staff array list
			staffAl.add(item) ;
		}
}

// an example of saving
public static void saveStaff(String filename, List staffAl) throws IOException {
	List alw = new ArrayList() ;// to store Professors data

    for (int i = 0 ; i < staffAl.size() ; i++) {
			Staff s1 = (Staff)staffAl.get(i);
			StringBuilder st =  new StringBuilder() ;
			st.append(s1.getStaffId());
			st.append(SEPARATOR);
			st.append(s1.getJobTitle());
			st.append(SEPARATOR);
			st.append(s1.getName().trim());
			st.append(SEPARATOR);
			st.append(s1.getGender().trim());
			alw.add(st.toString()) ;
		}
		write(filename,alw);
}

  /** Write fixed content to the given file. */
  public static void write(String fileName, List data) throws IOException  {
    PrintWriter out = new PrintWriter(new FileWriter(fileName));

    try {
		for (int i =0; i < data.size() ; i++) {
      		out.println((String)data.get(i));
		}
    }
    finally {
      out.close();
    }
  }
  
  public static void deleteEverything(String fileName) throws IOException {
	  PrintWriter out = new PrintWriter(new FileWriter(fileName));
	  out.close();
  }

  /** Read the contents of the given file. */
  public static List read(String fileName) throws IOException {
	List data = new ArrayList() ;
    Scanner scanner = new Scanner(new FileInputStream(fileName));
    try {
      while (scanner.hasNextLine()){
        data.add(scanner.nextLine());
      }
    }
    finally{
      scanner.close();
    }
    return data;
  }
}
