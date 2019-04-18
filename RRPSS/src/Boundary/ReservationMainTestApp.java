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
	 * Kickstarts the entire application by loading the general interface. Removes
	 * all expired reservation upon start as well.
	 * 
	 * @param sc             {@link Scanner} object used for capturing console input
	 * @param menuMgr        {@link MenuMgr} object for controlling for controlling
	 *                       all objects under menu.
	 * @param tableMgr       {@link TableMgr} object for controlling all tables
	 * @param reservationMgr {@link ReservationMgr} object for controlling all
	 *                       reservation
	 * @param orderMgr       {@link OrderMgr} object for controlling all orders.
	 * @param invoiceMgr     {@link InvoiceMgr} object for controlling all invoices.
	 * @param personMgr      {@link PersonMgr} object for controlling all objects
	 *                       under person.
	 */
	public void run(Scanner sc, MenuMgr menuMgr, TableMgr tableMgr, ReservationMgr reservationMgr, OrderMgr orderMgr,
			InvoiceMgr invoiceMgr, PersonMgr personMgr) {
		int userInput;
		// debug messages
//		menuMgr.printAlacarteItem();
//		menuMgr.printPromotionalPackage();
//		reservationMgr.printReservation(tableMgr, personMgr);
//		tableMgr.printTableList();
		do {
			reservationMgr.removeExpiredReservations(personMgr);
			ReservationMainTestApp.printAppMenu();
			userInput = ReservationMainTestApp.getUserInput(sc, menuMgr, tableMgr, reservationMgr, orderMgr, invoiceMgr,
					personMgr);

		} while (userInput != 0);
		System.out.println("Thanks for using our app!");
	}

	/**
	 * Prints out general interface for application.
	 */
	public static void printAppMenu() {
		System.out.println("<<         Oops Bar & Cafe [RRPSS]       >>");
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println("1)\tMenu");
		System.out.println("2)\tOrder");
		System.out.println("3)\tReservations");
		System.out.println("4)\tInvoices/Reports");
		System.out.println("0)\tExit");
	}

	/**
	 * Prints out Menu interface. Redirects to the {@link MenuMgr} control layer
	 * based on user Input.
	 * 
	 * @param sc      scanner required for taking in input
	 * @param menuMgr the redirected control class
	 * @return the integer input from scanner
	 */
	public static int printAppMenu1(Scanner sc, MenuMgr menuMgr) {
		// sub-menu 1
		System.out.println("<<                Menu                  >>");
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println("1)\tView Menu");
		System.out.println("2)\tCreate Alacarte item");
		System.out.println("3)\tUpdate Alacarte item");
		System.out.println("4)\tRemove Alacarte item");
		System.out.println("5)\tCreate Promotion item");
		System.out.println("6)\tUpdate Promotion item");
		System.out.println("7)\tRemove Promotion item");
		System.out.println("0)\tBack");

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
				break;
			case 3:
				menuMgr.updateAlacarte();
				break;
			case 4:
				menuMgr.removeAlacarte();
				break;
			case 5:
				menuMgr.createPromotionalPackage();
				break;
			case 6:
				menuMgr.updatePromotionalPackage();
				break;
			case 7:
				menuMgr.removePromotionalPackage();
				break;
			case 0:
				break;
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
		return 0;

	}

	public static int printAppMenu2(Scanner sc, OrderMgr orderMgr, TableMgr tableMgr, ReservationMgr reservationMgr,
			PersonMgr personMgr, MenuMgr menuMgr) {
		// sub-menu 2
		System.out.println("<<               Orders                 >>");
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println("1)View Order");
		System.out.println("2)View Tables");
		System.out.println("3)Create Order");
		System.out.println("4)Update Order");
		System.out.println("5)Remove Order");
		System.out.println("0)Back");

		int input = 1;
		try {
			input = Integer.parseInt(sc.nextLine());
			switch (input) {
			case 1:
				orderMgr.viewOrder();
				break;
			case 2:
				tableMgr.printTableList();
				break;
			case 3:
				orderMgr.createOrder(tableMgr, menuMgr, personMgr, reservationMgr);
				break;
			case 4:
				orderMgr.updateOrder(menuMgr);
				break;
			case 5:
				orderMgr.removeOrder(tableMgr);
				break;

			case 0:
				break;
			default:
				System.out.println("Invalid input! Please choose option 0-5");
				break;
			}

		} catch (InputMismatchException e) {
			System.out.println("Error! Incorrect input format! Use ONLY NUMBERS (0-5).");
			sc.next();
		} catch (Exception e) {
			e.getStackTrace();
		}
		return 0;

	}

	public static int printAppMenu3(Scanner sc, TableMgr tableMgr, ReservationMgr reservationMgr, PersonMgr personMgr) {
		// sub-menu 3
		System.out.println("<<              Reservations            >>");
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println("1)\tView ALL Reservations");
		System.out.println("2)\tCheck Reservation");
		System.out.println("3)\tCreate Reservation");
		System.out.println("4)\tRemove Reservation");
		System.out.println("0)Back");
		int input = 1;
		try {
			input = Integer.parseInt(sc.nextLine());
			switch (input) {
			case 1:
				reservationMgr.printReservation(tableMgr, personMgr);
				break;
			case 2:
				reservationMgr.checkReservation(tableMgr, personMgr);
				break;
			case 3:
				reservationMgr.createReservation(tableMgr, personMgr);
				reservationMgr.printReservation(tableMgr, personMgr);
				break;

			case 4:
				reservationMgr.removeReservation(personMgr);
				reservationMgr.printReservation(tableMgr, personMgr);
				break;

			case 0:
				break;
			default:
				System.out.println("Invalid input! Please choose option 0-5");
				break;
			}

		} catch (InputMismatchException e) {
			System.out.println("Error! Incorrect input format! Use ONLY NUMBERS (0-5).");
			sc.next();
		} catch (Exception e) {
			e.getStackTrace();
		}
		return 0;

	}

	public static int printAppMenu4(Scanner sc, OrderMgr orderMgr, InvoiceMgr invoiceMgr, TableMgr tableMgr) {
		// sub-menu 4
		System.out.println("<<            Invoices/Reports           >>");
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println("1)Print Invoice");
		System.out.println("2)Print Sales Revenue Report (Day/Month)");
		System.out.println("0)Back");

		int input = 1;
		try {
			input = Integer.parseInt(sc.nextLine());
			switch (input) {

			case 1:
				invoiceMgr.generateInvoice(orderMgr, tableMgr);
				break;
			case 2:
				invoiceMgr.printSaleRevenueReport();
				break;
			case 0:
				break;
			default:
				System.out.println("Invalid input! Please choose option 0-5");
				break;
			}

		} catch (InputMismatchException e) {
			System.out.println("Error! Incorrect input format! Use ONLY NUMBERS (0-5).");
			sc.next();
		} catch (Exception e) {
			e.getStackTrace();
		}
		return 0;
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
				printAppMenu1(sc, menuMgr);

				break;
			case 2:
				printAppMenu2(sc, orderMgr, tableMgr, reservationMgr, personMgr, menuMgr);

				break;
			case 3:
				printAppMenu3(sc, tableMgr, reservationMgr, personMgr);
				break;
			case 4:
				printAppMenu4(sc, orderMgr, invoiceMgr, tableMgr);
				break;

			case 0:
				System.out.println("...Exiting RRPSS...");
				break;
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
