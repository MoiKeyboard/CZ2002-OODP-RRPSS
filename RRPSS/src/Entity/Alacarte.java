package Entity;

public class Alacarte extends MenuItem {
	private String category;

	public Alacarte() {
		super();
		this.category = "";
	}

	public Alacarte(String name, String description, double price, String category) {
		super();
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
}
