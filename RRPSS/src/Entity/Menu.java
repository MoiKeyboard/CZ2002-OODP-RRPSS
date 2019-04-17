package Entity;

import java.io.Serializable;

/**
 * The {@code Menu} entity class is an object wrapper. Superclass of
 * {@link Alacarte} and {@link Menu}.
 * <p>
 * Contains primitive information related to a menu item (e.g name, price,
 * description).
 * </p>
 * 
 * @author Qwek Zhi Hui
 * @version 1.0
 * @since 2019-04-13
 */
public class Menu implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2L;
	private String name;
	private String description;
	private double price;

	/**
	 * Default constructor for {@code Menu}.
	 */
	public Menu() {
		this.name = null;
		this.description = null;
		this.price = 0;
	}

	/**
	 * Constructor for {@code Menu}. Creates a {@code Menu} object with required
	 * parameters
	 * 
	 * @param name        Name of {@code Menu}
	 * @param description Description of {@code Menu}
	 * @param price       Price of {@code Menu}
	 */
	public Menu(String name, String description, double price) {
		this.name = name;
		this.description = description;
		this.price = price;
	}

	/**
	 * Returns name of {@code Menu} object.
	 * 
	 * @return the String of name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Updates name of {@code Menu} object.
	 * 
	 * @param name the updated name String
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Returns description of {@code Menu} object.
	 * 
	 * @return the String of description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Updates the description of {@code Menu} object.
	 * 
	 * @param description the updated description String
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Returns the price of {@code Menu} object.
	 * 
	 * @return the decimal value of price
	 */
	public double getPrice() {
		return price;
	}

	/**
	 * Updates the price of {@code Menu} object.
	 * 
	 * @param price the updated price double value
	 */
	public void setPrice(double price) {
		this.price = price;
	}

}
