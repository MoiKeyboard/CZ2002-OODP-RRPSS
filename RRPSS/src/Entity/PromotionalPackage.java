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

	public String getPromoName() {
		return promoName;
	}

	public void setPromoName(String promoName) {
		this.promoName = promoName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public ArrayList<MenuItem> getPromoPackageArr() {
		return promoPackageArr;
	}

	public void setPromoPackageArr(ArrayList<MenuItem> promoPackageArr) {
		this.promoPackageArr = promoPackageArr;
	}

}