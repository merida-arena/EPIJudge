package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TestFailure;
import epi.test_framework.TimedExecutor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DutchNationalFlag {
  public enum Color { RED, WHITE, BLUE }

  public static void dutchFlagPartition(int pivotIndex, List<Color> A) {
    int eq = 0, lg = A.size(), sm = 0;
    epi.DutchNationalFlag.Color pivot = A.get(pivotIndex);

    while(eq < lg){
      if(A.get(eq).ordinal() < pivot.ordinal()){
        Collections.swap(A, sm++, eq++);
      } else if(A.get(eq).ordinal() > pivot.ordinal()) {
        Collections.swap(A, eq, --lg);
      } else {
        eq++;
      }
    }
    return;
  }
  @EpiTest(testDataFile = "dutch_national_flag.tsv")
  public static void dutchFlagPartitionWrapper(TimedExecutor executor,
                                               List<Integer> A, int pivotIdx)
          throws Exception {
    List<Color> colors = new ArrayList<>();
    int[] count = new int[3];

    epi.DutchNationalFlag.Color[] C = epi.DutchNationalFlag.Color.values();
    for (int i = 0; i < A.size(); i++) {
      count[A.get(i)]++;
      colors.add(C[A.get(i)]);
    }

    epi.DutchNationalFlag.Color pivot = colors.get(pivotIdx);
    executor.run(() -> dutchFlagPartition(pivotIdx, colors));

    int i = 0;
    while (i < colors.size() && colors.get(i).ordinal() < pivot.ordinal()) {
      count[colors.get(i).ordinal()]--;
      ++i;
    }

    while (i < colors.size() && colors.get(i).ordinal() == pivot.ordinal()) {
      count[colors.get(i).ordinal()]--;
      ++i;
    }

    while (i < colors.size() && colors.get(i).ordinal() > pivot.ordinal()) {
      count[colors.get(i).ordinal()]--;
      ++i;
    }

    if (i != colors.size()) {
      throw new TestFailure("Not partitioned after " + Integer.toString(i) +
              "th element");
    } else if (count[0] != 0 || count[1] != 0 || count[2] != 0) {
      throw new TestFailure("Some elements are missing from original array");
    }
  }

  public static void main(String[] args) {
    System.exit(
            GenericTest
                    .runFromAnnotations(args, "DutchNationalFlag.java",
                            new Object() {}.getClass().getEnclosingClass())
                    .ordinal());
  }
}
