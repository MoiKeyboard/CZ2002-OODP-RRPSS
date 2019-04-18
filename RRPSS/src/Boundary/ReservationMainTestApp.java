package Boundary;

import java.util.InputMismatchException;
import java.util.Scanner;

import Control.InvoiceMgr;
import Control.MenuMgr;
import Control.OrderMgr;
import Control.PersonMgr;
import Control.ReservationMgr;
import Control.TableMgr;

/**
 * Boundary class for RRPSS restaurant application Upon start, loads interfaces
 * and all required control classes
 * 
 * @author Qwek Zhi Hui
 * @version 1.0
 * @since 2019-04-13
 */
public class ReservationMainTestApp {

	/**
	 * Loads control layer managers through instantiation.
	 * 
	 * @param args
	 */
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

	/**
	 * 
	 * @param sc             {@link Scanner} object used for capturing console input
	 * @param menuMgr        {@link MenuMgr} object for control
	 * @param tableMgr
	 * @param reservationMgr
	 * @param orderMgr
	 * @param invoiceMgr
	 * @param personMgr
	 */
	public void run(Scanner sc, MenuMgr menuMgr, TableMgr tableMgr, ReservationMgr reservationMgr, OrderMgr orderMgr,
			InvoiceMgr invoiceMgr, PersonMgr personMgr) {
		int userInput;
		menuMgr.printAlacarteItem();
		menuMgr.printPromotionalPackage();
		reservationMgr.printReservation(tableMgr, personMgr);
		tableMgr.printTableList();
		do {
			reservationMgr.removeExpiredReservations(personMgr);
			ReservationMainTestApp.printAppMenu();
			userInput = ReservationMainTestApp.getUserInput(sc, menuMgr, tableMgr, reservationMgr, orderMgr, invoiceMgr,
					personMgr);

		} while (userInput != 0);
		System.out.println("Thanks for using our app!");
	}

	/**
	 * Print out general interface for application
	 */
	public static void printAppMenu() {
		System.out.println("1)\tView menu");
		System.out.println("2)\tCreate alacarte");
		System.out.println("3)\tUpdate alacarte");
		System.out.println("4)\tRemove alacarte");
		System.out.println("5)\tCreate promotion");
		System.out.println("6)\tUpdate promotion");
		System.out.println("7)\tRemove promotion");
		System.out.println("8)\tCreate Order");
		System.out.println("9)\tUpdate Order");
		System.out.println("10)\tRemove Order");
		System.out.println("11)\tView Order");
		System.out.println("12)\tCreate reservation booking");
		System.out.println("13)\tCheck reservation booking");
		System.out.println("14)\tRemove reservation booking");
		System.out.println("15)\tCheck table availability");
		System.out.println("16)\tPrint bill invoice");
		System.out.println("17)\tPrint sale revenue report by period(e.g day/month)\n0)\tExit the application");
		System.out.println("18) \tView reservations");
	}
	
	public static void printAppMenu1() {
		//sub-menu 1
		System.out.println("<<                Menu                  >>");
		System.out.println();
		System.out.println("1)View Menu");
		System.out.println("2)Create Alacarte item");
		System.out.println("3)Update Alacarte item");
		System.out.println("4)Remove Alacarte item");
		System.out.println("5)Create Promotion item");
		System.out.println("6)Update Promotion item");
		System.out.println("7)Remove Promotion item");
		System.out.println("0)Back");
	}

	public static void printAppMenu2() {
		//sub-menu 2
		System.out.println("<<               Orders                 >>");
		System.out.println();
		System.out.println("1)View Order");
		System.out.println("2)View Tables");
		System.out.println("3)Create Order");
		System.out.println("4)Update Order");
		System.out.println("5)Remove Order");
		System.out.println("0)Back");
		
	}
	
	public static void printAppMenu3() {
		//sub-menu 3
		System.out.println("<<              Reservations            >>");
		System.out.println();
		System.out.println("1)View Reservations");
		System.out.println("2)Create Reservation");
		System.out.println("3)Remove Reservation");
		System.out.println("0)Back");
		
		
	}
	
	public static void printAppMenu4() {
		//sub-menu 4
		System.out.println("<<            Invoices/Reports           >>");
		System.out.println();
		System.out.println("1)Print Invoice");
		System.out.println("2)Print Sales Revenue Report (Day/Month)");
		System.out.println("0)Back");
	}
	/**
	 * Captures input and calls related manager to perform required action
	 * 
	 * @param sc
	 * @param menuMgr
	 * @param tableMgr
	 * @param reservationMgr
	 * @param orderMgr
	 * @param invoiceMgr
	 * @param personMgr
	 * @return
	 */
	public static int getUserInput(Scanner sc, MenuMgr menuMgr, TableMgr tableMgr, ReservationMgr reservationMgr,
			OrderMgr orderMgr, InvoiceMgr invoiceMgr, PersonMgr personMgr) {
		int input = 1;
		try {
			input = Integer.parseInt(sc.nextLine());
			switch (input) {
			case 1:
				menuMgr.printAlacarteItem();
				menuMgr.printPromotionalPackage();
				break;
			case 2:
				menuMgr.createAlacarte();
				menuMgr.printAlacarteItem();
				break;
			case 3:
				menuMgr.updateAlacarte();
				menuMgr.printAlacarteItem();
				break;
			case 4:
				menuMgr.removeAlacarte();
				menuMgr.printAlacarteItem();
				break;
			case 5:
				menuMgr.createPromotionalPackage();
				menuMgr.printPromotionalPackage();
				break;
			case 6:
				menuMgr.updatePromotionalPackage();
				menuMgr.printPromotionalPackage();
				break;
			case 7:
				menuMgr.removePromotionalPackage();
				menuMgr.printPromotionalPackage();
				break;
			case 8:
				orderMgr.createOrder(tableMgr, menuMgr, personMgr, reservationMgr);
				break;
			case 9:
				orderMgr.updateOrder(menuMgr);
				break;
			case 10:
				orderMgr.removeOrder(tableMgr);
				break;
			case 11:
				orderMgr.viewOrder();
				break;
			case 12:
				reservationMgr.createReservation();
				reservationMgr.printReservation(tableMgr, personMgr);
				break;
			case 13:
				reservationMgr.checkReservation(tableMgr, personMgr);
				break;
			case 14:
				reservationMgr.removeReservation(personMgr);
				reservationMgr.printReservation(tableMgr, personMgr);
				break;
			case 15:
				tableMgr.printTableList();
				break;
			case 16:
				invoiceMgr.generateInvoice(orderMgr, tableMgr);
				break;
			case 17:
				invoiceMgr.printSaleRevenueReport();
				break;
				
			case 18:
				reservationMgr.printReservation(tableMgr, personMgr);
			default:
				System.out.println("Invalid input! Please choose option 1-17");
				break;

			}
		} catch (InputMismatchException e) {
			System.out.println("Error! Incorrect input format! Use ONLY NUMBERS (1-17).");
			sc.next();
		} catch (Exception e) {
			e.getStackTrace();
		}
		return input;
	}

}
