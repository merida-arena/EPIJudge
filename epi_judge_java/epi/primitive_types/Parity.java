package epi.primitive_types;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

public class Parity {

    /*

    Brute force: Check every bit in the number while tracking the no. of 1s seen so far.
    Until, x is not zero:
        1. check every bit from right (LSB) is 1 (by AND with x).
        2. Track the count in result (by XOR with parity). (since we are only concerned about even and odd, we can store in modulo 2).
        3. Trim the last bit of x (by right shift) and set it as x.


    Time: 0(n)
     */
  @EpiTest(testDataFile = "parity.tsv")
/*
  public static short parity1(long x) {
    short parity = 0;

    while(x != 0){
        if((x&1) == 1)
            parity = (short) (parity^1);

        x>>>=1;
    }
    return parity;
  }

  */
/*
  I will keep on flipping parity from even or odd until x becomes zero and
  keep clearing the rightmost set bits (i.e. counting only 1's or looping for only 1s).

  1. flip parity
  2. clear the rightmost set bit


  time : O(k)
   *//*

    public static short parity2(long x) {
        short parity = 0;

        while(x != 0) {
           parity = (short) (parity^1);

           x = x & (x-1);
        }
        return parity;
    }
*/

    /*

    Best approach : parity of S = S1 XOR S2
    Breaking s into equal parts
        1. by moving the first 32 bits to last (then 16, then 8, then 4, then 2 and then 1).
        2. take XOR to extract the first half and second half.

    At the end, x will be either 0 or 1 i.e parity is even or odd.
        To extract the parity in short type do, x AND 1.

       time: log(n)

     */
    public static short parity(long x) {

        x = x ^ x >>> 32;
        x = x ^ x >>> 16;
        x = x ^ x >>> 8;
        x = x ^ x >>> 4;
        x = x ^ x >>> 2;
        x = x ^ x >>> 1;

        return (short) (x & 1);
    }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "Parity.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
