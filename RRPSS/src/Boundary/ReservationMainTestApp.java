package Boundary;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import Control.*;

public class ReservationMainTestApp {
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		ReservationMainTestApp test = new ReservationMainTestApp();
		MenuItemMgr menuMgr = new MenuItemMgr();
		TableMgr tableMgr = new TableMgr();
		test.run(sc, menuMgr,tableMgr);
		sc.close();
	}
	
	public static void run(Scanner sc,MenuItemMgr menuMgr, TableMgr tableMgr) {
		int userInput;
		menuMgr.printMenuItem();
		tableMgr.printTableList();
		do {
			ReservationMainTestApp.printAppMenu();
			userInput = ReservationMainTestApp.getUserInput(sc, menuMgr, tableMgr);
			
		} while (userInput != 0 );
		System.out.println("Thanks for using our app!");
	}
	
	public static void printAppMenu() {
		System.out.println("1.\tCreate menu item");
		System.out.println("2.\tUpdate menu item");
		System.out.println("3.\tRemove menu item");
		System.out.println("4.\tCreate promotion");
		System.out.println("5.\tUpdate promotion");
		System.out.println("6.\tRemove promotion");
		System.out.println("7.\tCreate Order");
		System.out.println("8.\tView Order");
		System.out.println("9.\tAdd order item/s to/from order");
		System.out.println("10.\tRemove order item/s	to/from order");
		System.out.println("11.\tCreate reservation booking");
		System.out.println("12.\tCheck/Remove reservation booking");
		System.out.println("13.\tCheck table availability");
		System.out.println("14.\tPrint bill invoice");
		System.out.println("15.\tPrint sale revenue report by period(e.g day/month)\n0.\tExit the application");
	}
	
	public static int getUserInput(Scanner sc, MenuItemMgr menuMgr, TableMgr tableMgr) {
		int input = sc.nextInt();
		sc.nextLine();
		try {
			if (input == 0) return input;
			switch(input) {
				case 1:
					menuMgr.createMenuItem();
					menuMgr.printMenuItem();
					break;
				case 2:
					menuMgr.updateMenuItem();
					menuMgr.printMenuItem();
					break;
				case 3:
					menuMgr.removeMenuItem();
					menuMgr.printMenuItem();
					break;	
				case 6:
					break;
				case 7:
					break;
				case 8:
					break;
				case 9:
					break;
				case 10:
					break;
				case 11:
					break;
				case 12:
					break;
				case 13:
					tableMgr.printTableList();;
					break;
				case 14:
					break;
				case 15:
					break;
				default:
					break;
				
			}
		} catch (Exception e) {
			e.getStackTrace();
		}
		return input;
	}
	
}
