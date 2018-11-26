package store;

import java.util.*;

/**
 * This is the main class with which this application runs
 */
public class Main {
  private static Scanner sc = new Scanner(System.in);
  
  /**
   * Displays the main menu
   */
  public static void displayMenu() {
    while(true) {
      System.out.println("*** MAIN MENU ***");
      System.out.println("Enter 1 to create a member"
        + "\nEnter 2 to make a purchase"
        + "\nEnter 3 to display a summary of transaction data for the current session"
        + "\nEnter 4 to exit the system");
      System.out.print("Enter your choice: ");
      int choice = sc.nextInt();
      switch(choice) {
      case 1:      
        createMember();
        break;
      case 2:
        makePurchase();
        break;
      case 3:
        displaySummary();
        break;
      case 4:
        System.exit(0);
      default:
        System.out.println("Invalid input! Try again");
      }
    }
  }
  
  /**
   * Create a member
   */
  public static void createMember() {
    System.out.print("Enter the name of your member: ");
    String name = sc.next();
    int totalMembers = DataPersistor.member.size();
    int memberId = totalMembers + 1;
    System.out.println("Choose membership \nEnter 1 for Guest"
        + "\nEnter 2 for Standard Membership"
        + "\nEnter 3 for Gold Membership"
        + "\nEnter 4 for Employee");
    System.out.print("Make your choice: ");
    int choice = sc.nextInt();
    Member member = null;
    if(choice == 1) {
      member = new NonMember(memberId, name, 0);
    } else if(choice == 2) {
      member = new StandardMember(memberId, name, 0);
    } else if(choice == 3) {
      member = new GoldMember(memberId, name, 0);
    } else if(choice == 4) {
      member = new Employee(memberId, name, 0);
    } else {
      System.out.println("Sorry, invalid input!");
    }
    DataPersistor.addMember(memberId, member);
    System.out.println("Member is added successfully!");
  }
  
  /**
   * Feed a purchase information
   */
  public static void makePurchase() {
    Member.processPayment(DataPersistor.getMember());
  }
  
  /**
   * Displays the summary of data processed
   */
  public static void displaySummary() {
    System.out.println("SUMMARY \nTotal sales made: " + DataPersistor.totalSalesMade);
    System.out.println("Total payment received: " + DataPersistor.totalPaymentReceived);
    System.out.println("\nTotal sales in Guest tier: " + NonMember.totalIndividualMemberSales);
    System.out.println("Total sales in Standard Membership: " + StandardMember.totalIndividualMemberSales);
    System.out.println("Total sales in Gold Membership: " + GoldMember.totalIndividualMemberSales);
    System.out.println("Total sales in Employee tier: " + Employee.totalIndividualMemberSales);
    System.out.println("\nTotal loyalty points accrued in guest tier: " + NonMember.totalIndividualMemberLPoints);
    System.out.println("Total loyalty points accrued in Standard Membership: " + StandardMember.totalIndividualMemberLPoints);
    System.out.println("Total loyalty points accrued in Gold Membership: " + GoldMember.totalIndividualMemberLPoints);
    System.out.println("Total loyalty points accrued in Employee tier: " + Employee.totalIndividualMemberLPoints);
    System.out.println("\nTotal discounts given in guest tier: " + NonMember.totalIndividualMemberDiscounts);
    System.out.println("Total discounts given accrued in Standard Membership: " + StandardMember.totalIndividualMemberDiscounts);
    System.out.println("Total discounts given accrued in Gold Membership: " + GoldMember.totalIndividualMemberDiscounts);
    System.out.println("Total discounts given accrued in Employee tier: " + Employee.totalIndividualMemberDiscounts);
  }
  
  /**
   * This is the main method of this class
   * @param args : String arguments to main method
   */
  public static void main(String[] args) {
    displayMenu(); 
  }
}
