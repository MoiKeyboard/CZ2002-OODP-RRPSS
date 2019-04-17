package Entity;

import java.util.ArrayList;

/**
 * The {@code Order} entity class is an object wrapper.
 * <p>
 * Contains primitive information related to an order (e.g staff that creates
 * order, list of food in order)
 * </p>
 * 
 * @author Qwek Zhi Hui
 * @version 1.0
 * @since 2019-04-13
 */
public class Order {
	private int staffId;
	private int tableNo;
	private ArrayList<Menu> foodAL;

	/**
	 * Default constructor for {@code Order}.
	 */
	public Order() {
		this.staffId = 0;
		this.tableNo = 0;
		this.foodAL = null;
	}

	/**
	 * Constructor for {@code Order}. Creates an {@code Order} object with required
	 * parameters.
	 * 
	 * @param staffId Staff ID of staff that creates the order
	 * @param tableNo Table number that assigned to the order
	 * @param foodAL  {@code ArrayList} of {@link Menu} items within the order.
	 */
	public Order(int staffId, int tableNo, ArrayList<Menu> foodAL) {
		this.staffId = staffId;
		this.tableNo = tableNo;
		this.foodAL = foodAL;
	}

	/**
	 * Returns the staff identification number of {@code Order} object.
	 * 
	 * @return the integer value of staffID
	 */
	public int getStaffId() {
		return staffId;
	}

	/**
	 * Updates staff identification number of {@code Alcarte} object.
	 * 
	 * @param staffId the updated staffId String
	 */
	public void setStaffId(int staffId) {
		this.staffId = staffId;
	}

	/**
	 * Returns the table number of {@code Order} object.
	 * 
	 * @return the integer value of tableNo
	 */
	public int getTableNo() {
		return tableNo;
	}

	/**
	 * Updates the table number of {@code Order} object.
	 * 
	 * @param tableNo the updated tableNo integer value
	 */
	public void setTableNo(int tableNo) {
		this.tableNo = tableNo;
	}

	/**
	 * Returns the {@code ArrayList} of {@link Menu} items within the {@code Order}
	 * object.
	 * 
	 * @return the {@code Menu ArrayList} of foodAL
	 */
	public ArrayList<Menu> getFoodAL() {
		return foodAL;
	}

	/**
	 * Updates the {@code ArrayList} of {@link Menu} items within the {@code Order}
	 * object.
	 * 
	 * @param foodAL the updated foodAL ({@link Menu} {@code ArrayList})
	 */
	public void setFoodAL(ArrayList<Menu> foodAL) {
		this.foodAL = foodAL;
	}

	/**
	 * Returns the string resepresentation of the {@code Order} object.
	 */
	@Override
	public String toString() {
		String orderItemDetails = null;
		orderItemDetails = "Table No: " + getTableNo() + "\nStaff ID: " + getStaffId() + "\n";
		for (Menu menu : foodAL) {
			if (menu instanceof Alacarte)
				orderItemDetails += ((Alacarte) menu).toString();
			else if (menu instanceof PromotionalPackage)
				orderItemDetails += ((PromotionalPackage) menu).toString();
		}
		return orderItemDetails;
	}

}
