package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.Collections;
import java.util.List;

public class ApplyPermutation {
  /*
  Swap the elements in A with indexes in perm. and place -1 in the perm list.
   */
  public static void applyPermutation(List<Integer> perm, List<Integer> A) {
    for(int i =0; i < A.size(); i++){
      int nextIndex = i;

      while (perm.get(nextIndex) >= 0){
        int permIndex = perm.get(nextIndex);
        Collections.swap(A, permIndex, i);

        perm.set(nextIndex, -1);

        nextIndex = permIndex;
      }
    }
    return;
  }
  @EpiTest(testDataFile = "apply_permutation.tsv")
  public static List<Integer> applyPermutationWrapper(List<Integer> perm,
                                                      List<Integer> A) {
    applyPermutation(perm, A);
    return A;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "ApplyPermutation.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
