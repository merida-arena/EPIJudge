package epi.array;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class IntAsArrayMultiply {
    @EpiTest(testDataFile = "int_as_array_multiply.tsv")
    public static List<Integer> multiply(List<Integer> num1, List<Integer> num2) {
        // Extract the sign
        final int sign = num1.get(0) < 0 ^ num2.get(0) < 0 ? -1 : 1;
        // Now make the signed integer at beginning to positive
        num1.set(0, Math.abs(num1.get(0)));
        num2.set(0, Math.abs(num2.get(0)));

        // Need to fill something, if you are setting something at any location in list, there must be some value
        List<Integer> result = new ArrayList<>(Collections.nCopies(num1.size() + num2.size(), 0));

        for (int i = num1.size() - 1; i >= 0; i--) {
            for (int j = num2.size() - 1; j >= 0; j--) {
                // The number present at the loc (the intermediatory multiplication) and add with current multiplication/
                int mul = result.get(i + j + 1) + (num1.get(i) * num2.get(j));

                // Add the carry to the (the intermediatory multiplication)
                result.set(i + j, result.get(i + j) + mul / 10);

                // Set the val by taking mod.
                result.set(i + j + 1, mul % 10);
            }
        }


        /*  num1:     [0]
            num2:     [-1, 0, 0, 0]

            result : [0]
        */

        int non_zero_idx = 0;
        while (non_zero_idx < result.size() && result.get(non_zero_idx) == 0) {
            non_zero_idx++;
        }

        result = result.subList(non_zero_idx, result.size());

        // if there are all zeros in the result and no non_zero_idx found then result will become empty then return 0.
        /*  Example

            num1:     [0]
            num2:     [-1, 0, 0, 0]

            result before getting sublist
            result = result.subList(non_zero_idx, result.size());

            is

            [0, 0, 0, 0]

            and the non_zero_idx would be result.size and become empty, therefore add single 0 and return.

            result : [0]
        */

        if(result.isEmpty()){
            return Arrays.asList(0);
        }

        // Put the sign that was decided at beginning.
        result.set(0, result.get(0) * sign);
        return result;
    }

    public static void main(String[] args) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "IntAsArrayMultiply.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }
}
