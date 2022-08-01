package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

public class ClosestIntSameWeight {
  /*
  same weight integer that means the return number must have same no. of 1's as of input.
  therefore, what we need to do, is maintain no. of 1s i.e. flip two bits, i and j.

  eg: 1000 (8) =>
  flipping 2nd MSB and 1st MSB => 0100 (4)
  flipping 3rd MSB and 1st MSB => 0010 (2)
  flipping 4th MSB and 1st MSB => 0001 (1)

  in order to maintain the minimum difference, i and j must be the closet (or consecutive bits that differ).

  As soon as the ith and jth bit differ.
  Mark them as 1.
  And flip them using XOR with x and return x (because that is the closet number).
   */
  @EpiTest(testDataFile = "closest_int_same_weight.tsv")
  public static long closestIntSameBitCount(long x) {
    for(int i = 0; i < 63; i++){
      if(((x>>>i) &1)  != ((x>>> i+1) &1)){
        long bitMask = 1 << i | 1 << i+1;
        x = x ^ bitMask;
        // return x as soon as you in and found the closet i and j
        return x;
      }
    }
    return -1;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "ClosestIntSameWeight.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
