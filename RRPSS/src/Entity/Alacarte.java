package Entity;

import java.io.Serializable;

/**
 * The {@code Alacarte} entity class is an object wrapper. Subclass of
 * {@link Menu}.
 * <p>
 * Contains primitive information related to an alacarte item (e.g alacarte
 * name, alacarte price, alacarte description, etc).
 * </p>
 * 
 * @author Qwek Zhi Hui
 * @version 1.0
 * @since 2019-04-13
 */
public class Alacarte extends Menu implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String category;

	/**
	 * Default constructor for {@code Alcarte}, calls superclass {@link Menu}
	 * constructor.
	 */
	public Alacarte() {
		super();
		this.category = "";
	}

	/**
	 * Constructor for {@code Alcarte}, calls superclass constructor {@link Menu}
	 * and creates an object with required parameters
	 * 
	 * @param name        Name of {@code Alcarte}
	 * @param description Description of {@code Alcarte}
	 * @param price       Price of {@code Alcarte}
	 * @param category    Category of {@code Alcarte}
	 */
	public Alacarte(String name, String description, double price, String category) {
		super(name, description, price);
		this.category = category;
	}

	/**
	 * Returns category of {@code Alcarte} object
	 * 
	 * @return
	 */
	public String getCategory() {
		return category;
	}

	/**
	 * Replaces catogory of {@code Alcarte} object
	 * 
	 * @param category Updated category information
	 */
	public void setCategory(String category) {
		this.category = category;
	}

	/**
	 * Returns the string resepresentation of the {@code Alcarte} instance.
	 */
	@Override
	public String toString() {
		String menuItemDetails = null;
		menuItemDetails = "Category: " + getCategory() + "\nFood Name: " + getName() + "\nDescription: "
				+ getDescription() + "\nPrice: " + getPrice() + "\n";
		return menuItemDetails;
	}

	/**
	 * Indicates whether some other {@code Alcarte} object is "equal to" this one.
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 * 
	 */
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
