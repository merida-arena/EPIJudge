package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

public class IsNumberPalindromic {
  /*
  Extracting first and last digit and check if they are equal or not.

  time : O(n)
   */
  @EpiTest(testDataFile = "is_number_palindromic.tsv")
  public static boolean isPalindromeNumber(int x) {
    if(x < 0)
      return false;
    // eg.
    // log10 (1331) = log10 (1.333) * 10(power 3) => 3 * log10 (1.331) => 3.xxxx
    // take floor to take round
    // 1331 - is four digits, therefore add +1
    final int noOfDigits = (int) Math.floor(Math.log10(x)) + 1;
    int divisor = (int) Math.pow(10, noOfDigits-1);

    for (int i =0; i< noOfDigits/2; i++){
      int f = x%10;
      int l = x/divisor;

      if(f != l)
        return false;

      x = x%divisor;
      x = x/10;
      divisor/=100;
    }
    return true;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "IsNumberPalindromic.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
