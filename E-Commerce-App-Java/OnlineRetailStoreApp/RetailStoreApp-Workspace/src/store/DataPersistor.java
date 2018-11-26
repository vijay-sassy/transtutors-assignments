package store;

import java.util.HashMap;

/**
 * This class persists data of this session
 */
public class DataPersistor {
  public static int totalSalesMade;
  public static int totalPaymentReceived;
  public static HashMap<Integer,Member> member = new HashMap<>();
  
  public static int getTotalSalesMade() {
    return totalSalesMade;
  }
  public static void setTotalSalesMade(int totalSalesMade) {
    DataPersistor.totalSalesMade = totalSalesMade;
  }
  
  public static int getTotalPaymentReceived() {
    return totalPaymentReceived;
  }
  public static void setTotalPaymentReceived(int totalPaymentReceived) {
    DataPersistor.totalPaymentReceived = totalPaymentReceived;
  }
  
  public static HashMap<Integer, Member> getMember() {
    return member;
  }
  public static void addMember(Integer memberId, Member member) {
    DataPersistor.member.put(memberId, member);
  }
}
