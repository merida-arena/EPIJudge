package epi.array;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.List;

public class SpiralOrdering {
  @EpiTest(testDataFile = "spiral_ordering.tsv")

  public static List<Integer> matrixInSpiralOrder(List<List<Integer>> squareMatrix) {
    List<Integer> result = new ArrayList<>();
    int left = 0, top = 0, right = squareMatrix.size()-1, bottom = squareMatrix.size()-1, dir = 0;

    while(left <= right && top <= bottom){
      if(dir == 0){
        for(int i = left; i <= right; i++){
          result.add(squareMatrix.get(top).get(i));
        }
        top++;
        dir++;
      } else if (dir == 1){
        for(int i = top; i <= bottom; i++){
          result.add(squareMatrix.get(i).get(right));
        }
        right--;
        dir++;
      } else if(dir == 2){
        for(int i = right; i >= left; i--){
          result.add(squareMatrix.get(bottom).get(i));
        }
        bottom--;
        dir++;
      } else if(dir == 3){
        for(int i = bottom; i >= top; i--){
          result.add(squareMatrix.get(i).get(left));
        }
        left++;
        dir=0;
      }
    }
    return result;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "SpiralOrdering.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
