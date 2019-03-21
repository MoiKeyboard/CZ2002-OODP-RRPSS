
public class PromotionalPackage {
	String promoName;
	String description;
	MenuItem[] promoPackageArr;

	public PromotionalPackage(String promoName, String description, int promoPackageSize) {
		super();
		this.promoName = promoName;
		this.description = description;
		promoPackageArr = new MenuItem[promoPackageSize];
	}

	void addMenuItem(String foodName) {

	}

}
