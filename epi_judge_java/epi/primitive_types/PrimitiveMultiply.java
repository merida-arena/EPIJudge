package epi.primitive_types;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

public class PrimitiveMultiply {
  // time: O(n2)
  @EpiTest(testDataFile = "primitive_multiply.tsv")
  public static long multiply(long x, long y) {
    long sum = 0;

    while(x != 0){
      //if it unset bit that would not change my sum.
      if ((x &1)==1){
        // add bitwise
        sum = add(sum, y);
      }
      x >>>=1;

      // same as decimal multiplication, i will left shift the number
      y <<= 1;
    }
    return sum;
  }

  private static long add(long x, long y) {
    long sum =0, carry = 0, tempX=x, tempY=y; int k = 1;

    while(tempX !=0 || tempY != 0){
      long x1 = x & k, y1 = y & k;
      long carryout = (x1 & y1) | (x1 & carry) | (y1 & carry);
      sum |= x1 ^ y1 ^ carry;
      //this is out carry so, move to left and save in carry for the next bit.
      carry = carryout << 1;
      //increment k (binary increment by left shift)
      k <<= 1;
      tempX >>>= 1;
      tempY >>>= 1;
    }
    return sum | carry;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "PrimitiveMultiply.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
