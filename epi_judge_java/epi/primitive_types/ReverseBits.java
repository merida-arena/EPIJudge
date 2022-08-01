package epi.primitive_types;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

public class ReverseBits {

  // size : 2048, to represent 16 bit number.
  // keep precomputed reverse of 16 bit numbers.
  private static long[] precomputedReverse = new long[(1<<16)];

  static {
    // i.e 2048 times, to iterate for all 16 bit numbers.
    for(int i=0 ; i< (1<<16); i++){
      //do the reversal for the ith number
      precomputedReverse[i] = calculatePreReversal(i);
    }
  }

  private static long calculatePreReversal(long x) {
    // do the reversal for the passed number by swapping all its bits.
    // since the passed number is 16 bit, that's why j=15
    for(int i=0, j=15; i<j ;i++, j--){
      //swap the ith and jth bit.
      x = swapBits(x, i, j);
    }
    return x;
  }

  private static long swapBits(long x, int i, int j) {
    if (((x>>>i) & 1) != ((x >>>j) &1)) {
      long bitMask = 1 << i | 1 << j;
      x = x ^ bitMask;
    }
    return x;
  }

  /*
  1. pre-calculate the reversal of 16 bit numbers.
  2. Break the 64 bit numbers in 16 bits and get the pre computed reversal and arrange them in the correct order (also using OR).

  time :
  L - width of 16 bits
  n - word size

  since there are n/L terms or parts

  O (n/L)
   */

  @EpiTest(testDataFile = "reverse_bits.tsv")
  public static long reverseBits(long x) {
    final int bitSize = 16;
    final int bitMask = 0xFFFF;
    long result = precomputedReverse[(int) (x & bitMask)] << 3*bitSize |
            precomputedReverse[(int) ((x>>>bitSize) & bitMask)] << 2*bitSize |
            precomputedReverse[(int) ((x >>> 2* bitSize) & bitMask)] << bitSize |
            precomputedReverse[(int) ((x >>> 3*bitSize) & bitMask)];

    return result;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "ReverseBits.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
