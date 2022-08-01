package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

public class IsStringPalindromic {
    @EpiTest(testDataFile = "is_string_palindromic.tsv")

    // time O(n)
    // two pointer technique.
    public static boolean isPalindromic(String s) {
        int i = 0, j = s.length() - 1;
        while (i < j) {
            // skip non alphanumeric.
            if (!Character.isLetterOrDigit(s.charAt(i)))
                i++;
            if (!Character.isLetterOrDigit(s.charAt(j)))
                j--;
            // irrespective of the casing.
            // and do i++ and j-- to evaluate next chars.
            if (Character.toLowerCase(s.charAt(i++)) != Character.toLowerCase(s.charAt(j--)))
                return false;
        }
        return true;
    }

    public static void main(String[] args) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "IsStringPalindromic.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }
}
