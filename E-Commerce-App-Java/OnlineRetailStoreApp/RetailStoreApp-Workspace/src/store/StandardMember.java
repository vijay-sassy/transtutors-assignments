package store;

/**
 * This class represents members with Standard membership
 */
public class StandardMember implements Member {
  private Integer memberId;
  private String name;
  private int pointsBalance = 0;
  private static final int pointsAccruedThreshold = 1;
  public static int totalIndividualMemberSales;
  public static int totalIndividualMemberLPoints;
  public static int totalIndividualMemberDiscounts;
  
  public StandardMember(Integer memberId, String name, int pointsBalance) {
    super();
    this.memberId = memberId;
    this.name = name;
    this.pointsBalance = pointsBalance;
  }
  public Integer getMemberId() {
    return memberId;
  }
  public void setMemberId(Integer memberId) {
    this.memberId = memberId;
  }
  
  @Override
  public String getName() {
    return name;
  }
  @Override
  public void setName(String name) {
    this.name = name;
  }
  
  public int getPointsBalance() {
    return pointsBalance;
  }
  public void setPointsBalance(int pointsBalance) {
    this.pointsBalance = pointsBalance;
  }

  public int getPointsAccruedThreshold() {
    return pointsAccruedThreshold;
  }
  
  public int getTotalIndividualMemberSales() {
    return totalIndividualMemberSales;
  }
  public void setTotalIndividualMemberSales(int totalIndividualMemberSales) {
    StandardMember.totalIndividualMemberSales = totalIndividualMemberSales;
  }

  public int getTotalIndividualMemberLPoints() {
    return totalIndividualMemberLPoints;
  }
  public void setTotalIndividualMemberLPoints(int totalIndividualMemberLPoints) {
    StandardMember.totalIndividualMemberLPoints = totalIndividualMemberLPoints;
  }

  public int getTotalIndividualMemberDiscounts() {
    return totalIndividualMemberDiscounts;
  }
  public void setTotalIndividualMemberDiscounts(int totalIndividualMemberDiscounts) {
    StandardMember.totalIndividualMemberDiscounts = totalIndividualMemberDiscounts;
  }
}
