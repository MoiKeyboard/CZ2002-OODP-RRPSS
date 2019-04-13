package Entity;

import java.io.Serializable;

/**
 * (Entity) Object wrapper for Alacarte. Subclass of {@link Menu}
 * 
 * @author Qwek Zhi Hui
 * @version 1.0
 * @since 2019-04-13
 */
public class Alacarte extends Menu implements Serializable {
	private String category;

	/**
	 * Default constructor for Alacarte, calls constructor superclass {@link Menu}
	 * and creates an empty object.
	 */
	public Alacarte() {
		super();
		this.category = "";
	}

	/**
	 * Constructor for Alacarte, calls superclass constructor {@link Menu} and
	 * creates an object with required parameters
	 * 
	 * @param name        Name of Alacarte
	 * @param description Description of Alacarte
	 * @param price       Price of alacarte
	 * @param category    Category of alacarte
	 */
	public Alacarte(String name, String description, double price, String category) {
		super(name, description, price);
		this.category = category;
	}

	/**
	 * Returns category of Alacarte object
	 * 
	 * @return
	 */
	public String getCategory() {
		return category;
	}

	/**
	 * Replaces catogory of Alacarte object
	 * 
	 * @param category Updated category
	 */
	public void setCategory(String category) {
		this.category = category;
	}

	/**
	 * Returns String of entire
	 */
	@Override
	public String toString() {
		String menuItemDetails = null;
		menuItemDetails = "Category: " + getCategory() + "\nFood Name: " + getName() + "\nDescription: "
				+ getDescription() + "\nPrice: " + getPrice() + "\n";
		return menuItemDetails;
	}

	@Override
	public boolean equals(Object o) {
		if (o instanceof Alacarte) {
			Alacarte comparatorObject = (Alacarte) o;
			if (this.getName().equalsIgnoreCase(comparatorObject.getName())
					&& this.getDescription().equalsIgnoreCase(comparatorObject.getDescription())
					&& this.getPrice() == comparatorObject.getPrice()) {

				return true;
			}
		}
		return false;
	}
}
