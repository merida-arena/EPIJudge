package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.List;

public class MatrixRotation {

  // Time O(N^2) Space O(1)
  // 4 way element exchange
  // 0 0 -> 3 0
  // 3 0 -> 3 3
  // 3 3 -> 0 3
  // 0 3 -> 0 0
  public static void rotateMatrix(List<List<Integer>> squareMatrix) {
    int n = squareMatrix.size()-1;

    for (int i = 0; i < squareMatrix.size()/2; i++){
      for (int j = i; j < n - i; j++){
        // 0 0
        int temp1 = squareMatrix.get(i).get(j);
        // 0 3
        int temp2 = squareMatrix.get(j).get(n-i);
        //3 3
        int temp3 = squareMatrix.get(n-i).get(n-j);
        // 3 0
        int temp4 = squareMatrix.get(n-j).get(i);

        // 0 0 -> 0 3
        // i.e at location 0 3 put location 0 0 values
        squareMatrix.get(j).set(n-i, temp1);
        // 0 3 -> 3 3
        // i.e at location 3 3 put location 0 3 values
        squareMatrix.get(n-i).set(n-j, temp2);
        // 3 3 -> 3 0
        // i.e at location 3 0 put location 3 3 values
        squareMatrix.get(n-j).set(i, temp3);
        // 3 0 -> 0 0
        // i.e at location 0 0 put location 3 0 values
        squareMatrix.get(i).set(j, temp4);
      }
    }

    return;
  }

  // brute force solution.
  public static void rotateMatrixBruteForce(List<List<Integer>> squareMatrix) {
    List<List<Integer>> result = new ArrayList<>();

    // 0 3 become 3 0 th location element
    // therefore, i = 0 and j = size -1
    // result.add(j, i)
    // Time O(N^2) Space O(N^2)
    for (int i = 0; i < squareMatrix.size(); i++){
      List<Integer> r = new ArrayList<>();
      for (int j = squareMatrix.get(i).size()-1; j >= 0  ;j--){
        r.add(squareMatrix.get(j).get(i));
      }
      result.add(r);
    }

    for (int i = 0; i < squareMatrix.size(); i++) {
      for (int j = 0; j < squareMatrix.size(); j++) {
        squareMatrix.set(i, result.get(i));
      }
    }

    return;
  }

  @EpiTest(testDataFile = "matrix_rotation.tsv")
  public static List<List<Integer>>
  rotateMatrixWrapper(List<List<Integer>> squareMatrix) {
    rotateMatrix(squareMatrix);
    return squareMatrix;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "MatrixRotation.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
