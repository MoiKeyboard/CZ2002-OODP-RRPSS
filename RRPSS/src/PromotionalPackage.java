import java.util.ArrayList;

public class PromotionalPackage {
	String promoName;
	String description;
	ArrayList<MenuItem> promoPackageArr;

	public PromotionalPackage() {
		this.promoName = null;
		this.description = null;
		promoPackageArr = null;
	}

	public PromotionalPackage(String promoName, String description, ArrayList<MenuItem> promoPackageArr) {
		super();
		this.promoName = promoName;
		this.description = description;
		this.promoPackageArr = new ArrayList<MenuItem>();
	}

	void createPromoPackage(String promoName) {

	}

	void updatePromoPackage() {

	}

	void deletePromoPackage() {

	}
}