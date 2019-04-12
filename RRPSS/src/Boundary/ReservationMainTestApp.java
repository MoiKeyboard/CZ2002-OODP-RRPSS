package Boundary;

import java.util.Scanner;

import Control.InvoiceMgr;
import Control.MenuMgr;
import Control.OrderMgr;
import Control.PersonMgr;
import Control.ReservationMgr;
import Control.TableMgr;

public class ReservationMainTestApp {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		ReservationMainTestApp test = new ReservationMainTestApp();
		MenuMgr menuMgr = new MenuMgr();
		ReservationMgr reservationMgr = new ReservationMgr();
		TableMgr tableMgr = new TableMgr(reservationMgr);
		OrderMgr orderMgr = new OrderMgr();
		InvoiceMgr invoiceMgr = new InvoiceMgr();
		PersonMgr personMgr = new PersonMgr();
		test.run(sc, menuMgr, tableMgr, reservationMgr, orderMgr, invoiceMgr, personMgr);
		sc.close();
	}

	public void run(Scanner sc, MenuMgr menuMgr, TableMgr tableMgr, ReservationMgr reservationMgr, OrderMgr orderMgr,
			InvoiceMgr invoiceMgr, PersonMgr personMgr) {
		int userInput;
		menuMgr.printMenuItem();
		menuMgr.printPromotionalPackage();
		reservationMgr.printReservation(tableMgr, personMgr);
		tableMgr.printTableList();
		do {
			reservationMgr.removeExpiredReservations();
			ReservationMainTestApp.printAppMenu();
			userInput = ReservationMainTestApp.getUserInput(sc, menuMgr, tableMgr, reservationMgr, orderMgr, invoiceMgr,
					personMgr);

		} while (userInput != 0);
		System.out.println("Thanks for using our app!");
	}

	public static void printAppMenu() {
		System.out.println("1)\tCreate menu item");
		System.out.println("2)\tUpdate menu item");
		System.out.println("3)\tRemove menu item");
		System.out.println("4)\tCreate promotion");
		System.out.println("5)\tUpdate promotion");
		System.out.println("6)\tRemove promotion");
		System.out.println("7)\tCreate Order");
		System.out.println("8)\tUpdate Order");
		System.out.println("9)\tRemove Order");
		System.out.println("10)\tView Order");
		System.out.println("11)\tCreate reservation booking");
		System.out.println("12)\tCheck reservation booking");
		System.out.println("13)\tRemove reservation booking");
		System.out.println("14)\tCheck table availability");
		System.out.println("15)\tPrint bill invoice");
		System.out.println("16)\tPrint sale revenue report by period(e.g day/month)\n0)\tExit the application");
	}

	public static int getUserInput(Scanner sc, MenuMgr menuMgr, TableMgr tableMgr, ReservationMgr reservationMgr,
			OrderMgr orderMgr, InvoiceMgr invoiceMgr, PersonMgr personMgr) {
		int input = sc.nextInt();
		sc.nextLine();
		try {
			if (input == 0)
				return input;
			switch (input) {
			case 1:
				menuMgr.createAlacarte();
				menuMgr.printMenuItem();
				break;
			case 2:
				menuMgr.updateAlacarte();
				menuMgr.printMenuItem();
				break;
			case 3:
				menuMgr.removeAlacarte();
				menuMgr.printMenuItem();
				break;
			case 4:
				menuMgr.createPromotionalPackage();
				menuMgr.printPromotionalPackage();
				break;
			case 5:
				menuMgr.updatePromotionalPackage();
				menuMgr.printPromotionalPackage();
				break;
			case 6:
				menuMgr.removePromotionalPackage();
				menuMgr.printPromotionalPackage();
				break;
			case 7:
				orderMgr.createOrder(tableMgr, menuMgr, personMgr, reservationMgr);
				break;
			case 8:
				orderMgr.updateOrder(menuMgr);
				break;
			case 9:
				orderMgr.removeOrder(tableMgr);
				break;
			case 10:
				orderMgr.viewOrder();
				break;
			case 11:
				reservationMgr.createReservation();
				reservationMgr.printReservation(tableMgr, personMgr);
				break;
			case 12:
				reservationMgr.checkReservation(tableMgr, personMgr);
				break;
			case 13:
				reservationMgr.removeReservation(personMgr);
				reservationMgr.printReservation(tableMgr, personMgr);
				break;
			case 14:
				tableMgr.printTableList();
				break;
			case 15:
				invoiceMgr.generateInvoice(orderMgr, tableMgr);
				break;
			case 16:
				invoiceMgr.printSaleRevenueReport();
				break;
			default:
				System.out.println("Invalid input! Please choose option 0-16");
				break;

			}
		} catch (Exception e) {
			e.getStackTrace();
		}
		return input;
	}

}
