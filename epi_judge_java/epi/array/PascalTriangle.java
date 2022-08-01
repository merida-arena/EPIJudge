package epi.array;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PascalTriangle {
    @EpiTest(testDataFile = "pascal_triangle.tsv")

    public static List<List<Integer>> generatePascalTriangle(int numRows) {
        if (numRows <= 0) {
            return Collections.emptyList();
        }

        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i < numRows; i++) {
            List<Integer> curRow = new ArrayList<>();
            for (int j = 0; j <= i; j++) {
                int entry = 1;

                // when j is non negative or j stays under i (not be <= because last element is gonna be 1).
                if (j > 0 && j < i)
                    entry = result.get(i - 1).get(j - 1) + result.get(i - 1).get(j);

                curRow.add(entry);
            }
            result.add(curRow);
        }
        return result;
    }

    public static void main(String[] args) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "PascalTriangle.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }
}
