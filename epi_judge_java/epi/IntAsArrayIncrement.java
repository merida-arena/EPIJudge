package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.List;

public class IntAsArrayIncrement {
  @EpiTest(testDataFile = "int_as_array_increment.tsv")
  public static List<Integer> plusOne(List<Integer> A) {
    int carry=1, i = A.size() -1;

    do {
      int ele = A.get(i);
      A.set(i, (ele+carry)%10);
      carry = (ele+carry)/10;

      --i;
    } while (i >= 0 && carry != 0);

    if (carry != 0) {
      A.add(0, carry);
    }
    return A;
  }

  public static void main(String[] args) {
    System.exit(
            GenericTest
                    .runFromAnnotations(args, "IntAsArrayIncrement.java",
                            new Object() {}.getClass().getEnclosingClass())
                    .ordinal());
  }
}
