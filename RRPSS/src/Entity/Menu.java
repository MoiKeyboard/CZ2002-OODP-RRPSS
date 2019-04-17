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

	public Menu() {
		this.name = null;
		this.description = null;
		this.price = 0;
	}

	/**
	 * Default constructor for {@code Menu}.
	 */
	public Menu(String name, String description, double price) {
		this.name = name;
		this.description = description;
		this.price = price;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

}
