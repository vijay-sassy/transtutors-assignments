package assaug4;

import java.util.Scanner;

public class Multiply {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    System.out.print("Enter the table number(x): ");
    int x = sc.nextInt();
    System.out.print("Enter the table's row siz(n): ");
    int n = sc.nextInt();
    for(int i = 1; i <= n; i++)
    {
      System.out.println(x + " x " + i + " = " + x*i);
    }
  }
}
