package epi.array;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.Collections;
import java.util.List;

public class NextPermutation {
  @EpiTest(testDataFile = "next_permutation.tsv")
  public static List<Integer> nextPermutation(List<Integer> perm) {
    int k = perm.size() - 2;

    while(k>=0 && perm.get(k) >= perm.get(k+1)){
      k--;
    }

    if(k == -1)
      return Collections.emptyList();

    for(int i = perm.size()-1; i > k; i--){
      if(perm.get(i) > perm.get(k)){
        Collections.swap(perm, i, k);
        break;
      }
    }

    Collections.reverse(perm.subList(k+1, perm.size()));
    return perm;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "NextPermutation.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
