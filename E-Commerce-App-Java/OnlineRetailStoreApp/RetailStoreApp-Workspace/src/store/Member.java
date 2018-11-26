package store;

import java.util.*;

/**
 * This is the interface representing a member
 */
public interface Member {
  int discountPointsThreshold = 1000;
  Scanner sc = new Scanner(System.in);
  void setName(String name);
  String getName();
  void setPointsBalance(int pointsBalance);
  int getPointsBalance();
  int getPointsAccruedThreshold();
  int getTotalIndividualMemberSales();
  void setTotalIndividualMemberSales(int totalIndividualMemberSales);
  int getTotalIndividualMemberLPoints();
  void setTotalIndividualMemberLPoints(int totalIndividualMemberLPoints);
  int getTotalIndividualMemberDiscounts();
  void setTotalIndividualMemberDiscounts(int totalIndividualMemberDiscounts);
  
  /**
   * This method takes care of processing payment of a member's purchase
   * This is used by all membership sub-classes with its common behaviour
   * @param members : HashMap holding members
   */
  static void processPayment(HashMap<Integer,Member> members) {
    System.out.println("Select either a guest(non-member) or a member who is making a purchase");
    members.forEach((key,value) -> displayMembers(key,value));
    System.out.print("Make your choice: ");    
    int choice = sc.nextInt();
    Member member = members.get(choice);
    int points = member.getPointsBalance();
    System.out.println("The current points balance is " + points);
    System.out.print("Enter the total recommended retail value of this purchase: $");
    int purchaseValue = sc.nextInt();
    points = purchaseValue * member.getPointsAccruedThreshold();
    member.setTotalIndividualMemberLPoints(member.getTotalIndividualMemberLPoints()+points);
    if(points >= Member.discountPointsThreshold) {
      int discount = (points/Member.discountPointsThreshold)*10;
      purchaseValue -= discount;
      points %= Member.discountPointsThreshold;
      member.setPointsBalance(points);
      member.setTotalIndividualMemberDiscounts(member.getTotalIndividualMemberDiscounts()+discount);
    }
    DataPersistor.setTotalSalesMade(DataPersistor.getTotalSalesMade()+1);
    DataPersistor.setTotalPaymentReceived(DataPersistor.getTotalPaymentReceived()+purchaseValue);
    member.setTotalIndividualMemberSales(member.getTotalIndividualMemberSales()+1);
    System.out.println("Done with payment processing"
      + "\nCurrent loyalty points of this member is " + points);
  }
  
  /**
   * A method to display members
   * @param id : Member ID
   * @param member : Member constructor
   */
  static void displayMembers(Integer id, Member member) {
    String memberClass = member.getClass().toString();
    String memberType = memberClass.substring(memberClass.indexOf(".")+1);
    System.out.println("Enter " + id + " for " + member.getName() + 
        " [" + memberType + "]");
  }
}
