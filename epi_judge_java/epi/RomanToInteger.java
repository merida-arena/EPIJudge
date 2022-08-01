package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.HashMap;
import java.util.Map;

public class RomanToInteger {

    // start from end of the string.
    // eg: IX -> store it to sum and now if the next element is smaller, subtract next element int mapping from sum, otherwise add.
    // before doing this, check whether the cur, and next element follows roman chars rules.
    @EpiTest(testDataFile = "roman_to_integer.tsv")
    public static int romanToInteger(String s) {
        Map<Character, Integer> romanIntMapping = new HashMap<>();
        romanIntMapping.put('I', 1);
        romanIntMapping.put('V', 5);
        romanIntMapping.put('X', 10);
        romanIntMapping.put('L', 50);
        romanIntMapping.put('C', 100);
        romanIntMapping.put('D', 500);
        romanIntMapping.put('M', 1000);

        int sum = romanIntMapping.get(s.charAt(s.length() - 1));
        for (int i = s.length() - 2; i >= 0; i--) {
            // check whether the cur, and next element follows roman chars rules.
            if (!isValidRomanRules(s.charAt(i), s.charAt(i), romanIntMapping))
                return -1;

            // start from end of the string.
            // eg: IX -> store it to sum and now if the next element is smaller, subtract next element int mapping from sum, otherwise add.
            if (romanIntMapping.get(s.charAt(i)) < romanIntMapping.get(s.charAt(i + 1)))
                sum = sum - romanIntMapping.get(s.charAt(i));
            else
                sum = sum + romanIntMapping.get(s.charAt(i));
        }
        return sum;
    }

    private static boolean isValidRomanRules(Character c, Character followingChar, Map<Character, Integer> romanIntMapping) {
        // if cur char is I but the next char is not I,V,X then it is not a valid roman string
        // I can only proceeds V and X
        if (c == 'I' && romanIntMapping.get(followingChar) > romanIntMapping.get('X'))
            return false;

        // if cur char is X but the next char is not I,V,X,L,C then it is not a valid roman string
        // X can only proceeds L and C
        if (c == 'X' && romanIntMapping.get(followingChar) > romanIntMapping.get('C'))
            return false;

        // if cur char is C but the next char is not I,V,X,L,C,D,M then it is not a valid roman string
        // C can only proceeds D and M
        if (c == 'C' && romanIntMapping.get(followingChar) > romanIntMapping.get('M'))
            return false;

        return true;
    }

    public static void main(String[] args) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "RomanToInteger.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }
}
