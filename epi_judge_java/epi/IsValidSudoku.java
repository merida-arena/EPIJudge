package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class IsValidSudoku {
    @EpiTest(testDataFile = "is_valid_sudoku.tsv")

    // Check if a partially filled matrix has any conflicts.
    // Check row for duplicates.
    // check col for duplicates.
    // check submatrixes for duplicates.
    public static boolean isValidSudoku(List<List<Integer>> partialAssignment) {
        // for rows
        for (int i = 0; i < partialAssignment.size(); i++) {
            if (hasDuplicates(partialAssignment, i, i + 1, 0, partialAssignment.size()))
                return false;

        }

        // for columns
        for (int i = 0; i < partialAssignment.size(); i++) {
            if (hasDuplicates(partialAssignment, 0, partialAssignment.size(), i, i + 1))
                return false;
        }

        // for submatrix
        int subMatrixSize = (int) Math.sqrt(partialAssignment.size());
        for (int i = 0; i < subMatrixSize; i++) {
            for (int j = 0; j < subMatrixSize; j++) {
                if (hasDuplicates(partialAssignment, subMatrixSize * i, subMatrixSize * (i + 1), subMatrixSize * j, subMatrixSize * (j + 1)))
                    return false;
            }
        }

        return true;
    }

    private static boolean hasDuplicates(List<List<Integer>> partialAssignment, int startRow, int endRow, int startCol, int endCol) {
        List<Boolean> isPresent = new ArrayList<>(Collections.nCopies(partialAssignment.size() + 1, false));

        for (int i = startRow; i < endRow; i++) {
            for (int j = startCol; j < endCol; j++) {
              if(partialAssignment.get(i).get(j) !=0 && isPresent.get(partialAssignment.get(i).get(j)))
                return true;

              isPresent.set(partialAssignment.get(i).get(j), true);
            }
        }

        return false;
    }

    public static void main(String[] args) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "IsValidSudoku.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }
}
