package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TestFailure;

public class StringIntegerInterconversion {

    public static String intToString(int x) {
        StringBuilder sb = new StringBuilder();
        boolean isNegative = false;
        if (x < 0) {
            isNegative = true;
            //x = Math.abs(x);
        }

        // used do while becuase is x = 0, i want to add that to result.
        do {
            // only one test case is failing for input
            // x = -2147483648
            // int range is -2147483648 to 2,147,483,647
            // if I make make x = Math.abs(x) while checking for negative numbers at line 12,
            // the x will go out of integer range,
            // therefore do Math.abs here.
            sb.append((char) ('0' + Math.abs(x % 10)));
           // sb.append((char) ('0' + (x % 10)));
            x = x / 10;
        }
        while (x != 0);

        // if x is negative append - sign
        if (isNegative)
            sb.append('-');

        return sb.reverse().toString();
    }

    public static int stringToInt(String s) {
        int result = 0, sign = 1, i = 0;

        // - and + are allowed in put, in both case i = 1;
        // if it is - then sign = -1
        if (s.charAt(0) == '-' || s.charAt(0) == '+') {
            i = 1;

            if (s.charAt(0) == '-')
                sign = -1;
        }

        for (; i < s.length(); i++) {
            int digit = s.charAt(i) - '0';
            result = result * 10 + digit;
        }
        return result * sign;
    }

    @EpiTest(testDataFile = "string_integer_interconversion.tsv")
    public static void wrapper(int x, String s) throws TestFailure {
        if (Integer.parseInt(intToString(x)) != x) {
            throw new TestFailure("Int to string conversion failed");
        }
        if (stringToInt(s) != x) {
            throw new TestFailure("String to int conversion failed");
        }
    }

    public static void main(String[] args) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "StringIntegerInterconversion.java",
                                new Object() {}.getClass().getEnclosingClass())
                        .ordinal());
    }
}
