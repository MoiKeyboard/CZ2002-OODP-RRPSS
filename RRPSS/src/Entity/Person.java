package Entity;

/**
 * The {@code Person} entity class is an object wrapper. Superclass of
 * {@link Customer} and {@link Staff}.
 * <p>
 * Contains primitive information related to a person.
 * </p>
 * 
 * @author Qwek Zhi Hui
 * @version 1.0
 * @since 2019-04-13
 */
public class Person {
	private String name;

	/**
	 * Default constructor for {@code Person}.
	 */
	public Person() {
	}

	/**
	 * Constructor for {@code Person}. Creates a {@code Person} object with required
	 * parameters.
	 * 
	 * @param name Name of {@code Person}
	 */
	public Person(String name) {
		this.name = name;

	}

	/**
	 * Returns name of {@code Person} object.
	 * 
	 * @return the String of name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Updates name of {@code Person} object.
	 * 
	 * @param name the updated name String
	 */
	public void setName(String name) {
		this.name = name;
	}
}
