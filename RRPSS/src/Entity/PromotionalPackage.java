package Entity;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * The {@code PromotionalPackage} entity class is an object wrapper. Subclass of
 * {@link Menu}.
 * <p>
 * Contains primitive information related to a promotional package.
 * </p>
 * 
 * @author Qwek Zhi Hui
 * @version 1.0
 * @since 2019-04-13
 */
public class PromotionalPackage extends Menu implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3L;
	private ArrayList<Alacarte> menuItemArr;

	/**
	 * Default constructor for {@code PromotionalPackage}, calls superclass
	 * {@link Menu} constructor.
	 */
	public PromotionalPackage() {
		super();
		this.menuItemArr = new ArrayList<Alacarte>();
	}

	/**
	 * Constructor for {@code PromotionalPackage}. Calls superclass constructor
	 * {@link Menu} and creates an {@code PromotionalPackage} object with required
	 * parameters.
	 * 
	 * @param promoName    Name of {@code PromotionalPackage}
	 * @param description  Description of {@code PromotionalPackage}
	 * @param price        Price of {@code PromotionalPackage}
	 * @param menuItemsArr {@code ArrayList} of {@link Alacarte} items within the
	 *                     promotional package.
	 */
	public PromotionalPackage(String promoName, String description, double price, ArrayList<Alacarte> menuItemsArr) {
		super(promoName, description, price);
		this.menuItemArr = menuItemsArr;
	}

	/**
	 * Returns the {@code ArrayList} of {@link Alacarte} items within the
	 * {@code PromotionalPackage} object.
	 * 
	 * @return the {@link Alacarte} {@code ArrayList} of menuItemArr
	 */
	public ArrayList<Alacarte> getMenuItemArr() {
		return menuItemArr;
	}

	/**
	 * Updates the {@code ArrayList} of {@link Alacarte} items within the
	 * {@code Order} object.
	 * 
	 * @param menuItemArr the updated menuItemArr ({@code ArrayList} of
	 *                    {@link Alacarte})
	 */
	public void setAlacarteArr(ArrayList<Alacarte> menuItemArr) {
		this.menuItemArr = menuItemArr;
	}

	/**
	 * Returns the string resepresentation of the {@code PromotionalPackage} object.
	 */
	@Override
	public String toString() {
		String promoDetails = null;
		String display = "";
		int index = 0;
		int count = 0;
		Alacarte a = new Alacarte();

		ArrayList<Alacarte> uniqueList = new ArrayList<Alacarte>();
		ArrayList<Integer> eachCount = new ArrayList<Integer>();

		for (Alacarte mi : menuItemArr) {
			if (uniqueList.contains(mi)) {
				index = uniqueList.indexOf(mi);
				count = eachCount.get(index) + 1;
				eachCount.set(index, count);
			}

			else {
				uniqueList.add(mi);
				eachCount.add(1);
			}

		}

		display = String.format("%-10s : %-22s %5.2f\n", "PROMO", getName(), getPrice());
		promoDetails = display;
		promoDetails += "******************************************\n";
		for (int i = 0; i < uniqueList.size(); i++) {
			// System.out.println("[DEBUG]: "+uniqueList.get(i).getName()+"\n");
			display = String.format("%-5d %s\n", eachCount.get(i), uniqueList.get(i).getName());
			promoDetails += display;

		}
		promoDetails += "******************************************\n";

		return promoDetails;
	}

	/**
	 * Indicates whether some other {@code PromotionalPackage} object is "equal to"
	 * this one.
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 * 
	 */
	@Override
	public boolean equals(Object o) {

		ArrayList<Alacarte> coMenuItemArr = new ArrayList<Alacarte>();
		int count = 0;
		Menu coMenu;
		boolean flag = true;
		if (o instanceof PromotionalPackage) {
			PromotionalPackage comparatorObject = (PromotionalPackage) o;
			if (this.getName().equalsIgnoreCase(comparatorObject.getName())
					&& this.getDescription().equalsIgnoreCase(comparatorObject.getDescription())
					&& this.getPrice() == comparatorObject.getPrice()) {
				// now check further if each Alacarte menu item in its array is equal to
				// coMenuItemArr's
				coMenuItemArr = comparatorObject.getMenuItemArr();
				for (Menu menu : this.getMenuItemArr()) {
					coMenu = coMenuItemArr.get(count);
					if (!menu.equals(coMenu)) {
						flag = false;
						break;
					} else {
						count++;
					}
				}
				return flag;
			}
		}

		return false;
	}

}