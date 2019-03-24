package Entity;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import Control.TextDB;

public class MenuItem {
	private String foodType;
	private String foodName;
	private String description;
	private double price;
	
	public MenuItem() {
		foodType = "";
		this.foodName = "";
		this.description = "";
		this.price = 0;
	}
	
	public MenuItem(String foodType, String foodName,String description, double price){
		this.foodType = foodType;
		this.foodName = foodName;
		this.description = description;
		this.price = price;
	}

	public String getFoodName() {
		return foodName;
	}

	public String getFoodType() {
		return foodType;
	}

	public String getDescription() {
		return description;
	}

	public double getPrice() {
		return price;
	}
	
	@Override
	public String toString() {
		String menuItemDetails = null;
		menuItemDetails = "Category: " + getFoodType() + "\nFood Name: " + getFoodName() + "\nDescription: " + getDescription() + "\nPrice: " + getPrice() + "\n";
		return menuItemDetails;
	}
}
