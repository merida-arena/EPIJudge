package epi.primitive_types;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

public class ReverseDigits {
  /*
  1. extract last digit in the input number.
  2. in result, move the result to left by *10 and add extracted last digit.
  3. new x will be the quotient.

  if the number was negative, add the sign.

  time: O(n)
   */
  @EpiTest(testDataFile = "reverse_digits.tsv")
  public static long reverse(int x) {
    int sign = 1; long result = 0;
    if(x < 0){
      x = -x;
      sign = -1;
    }

    while (x != 0){
      int r = x % 10;
      result = result * 10 + r;

      x = x/10;
    }
    return result * sign;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "ReverseDigits.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
