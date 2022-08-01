package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

public class SwapBits {
  /*
  Find the bits to be swapped differ, if yes swap it.

  1. Right shift x by i, then AND with 1 -> to extract bit at i.
  2. Right shift x by j, then AND with 1 -> to extract bit at j.

  3. if both differ, they need swapping:
      a. Mark the position to be swapped with 1 (i.e. generate bitmask)
      (because when we XOR it x in next step, if 1 is present at i in x, the marked position with 1 in bitmask, will flip it to 0 i position in x
      and if 0 is present at i in x, the marked position with 1 in bitmask, will flip it to 1 at i position in x).

      b. x = x XOR bitmask

  4. return x

  time : O(1)
   */
  @EpiTest(testDataFile = "swap_bits.tsv")
  public static long swapBits(long x, int i, int j) {
    if(needSwapping(x, i, j)){
      long bitMask = (1L << i) | (1L << j);

      x = x ^ bitMask;
    }

    return x;
  }

  private static boolean needSwapping(long x, int i, int j) {
    if(((x>>>i) & 1) != ((x>>>j) & 1)){
      return true;
    }
    return false;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "SwapBits.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
