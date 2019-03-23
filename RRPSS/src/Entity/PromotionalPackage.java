package Entity;
import java.util.ArrayList;

public class PromotionalPackage {
	private String promoName;
	private String description;
	private ArrayList<MenuItem> promoPackageArr;

	public PromotionalPackage() {
		this.promoName = null;
		this.description = null;
		promoPackageArr = new ArrayList<MenuItem>();
	}

	public PromotionalPackage(String promoName, String description, ArrayList<MenuItem> promoPackageArr) {
		this.promoName = promoName;
		this.description = description;
		this.promoPackageArr = promoPackageArr;
	}

	void createPromoPackage(String promoName) {

	}

	void updatePromoPackage() {

	}

	void deletePromoPackage() {

	}
}