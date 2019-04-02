package Entity;

public class Alacarte extends Menu {
	private String category;

	public Alacarte() {
		super();
		this.category = "";
	}

	public Alacarte(String name, String description, double price, String category) {
		super(name, description, price);
		this.category = category;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	@Override
	public String toString() {
		String menuItemDetails = null;
		menuItemDetails = "Category: " + getCategory() + "\nFood Name: " + getName() + "\nDescription: "
				+ getDescription() + "\nPrice: " + getPrice() + "\n";
		return menuItemDetails;
	}

	@Override
	public boolean equals(Object o) {
		Alacarte comparatorObject = (Alacarte) o;
		if (this.getName().equalsIgnoreCase(comparatorObject.getName())
				&& this.getDescription().equalsIgnoreCase(comparatorObject.getDescription())
				&& this.getPrice() == comparatorObject.getPrice()) {

			return true;
		}
		return false;
	}
}
