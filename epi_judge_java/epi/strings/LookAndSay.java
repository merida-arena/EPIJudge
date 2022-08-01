package epi.strings;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

public class LookAndSay {
    @EpiTest(testDataFile = "look_and_say.tsv")

    // maintain a count of each char in string and then add count & number to the result string.
    //time :: O(n2^n)
    public static String lookAndSay(int n) {
        StringBuilder sb = new StringBuilder("1");
        for (int i = 1; i < n; i++) {
            sb = NextNumber(sb);
        }
        return sb.toString();
    }

    private static StringBuilder NextNumber(StringBuilder sb) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < sb.length(); i++) {
            // for cur num, this is 1st occurrence of it, so occurrence = 1.
            int occurrence = 1;
            // go until the repetitions of the cur num, increment occurrence occurrence and i.
            while (i + 1 < sb.length() && sb.charAt(i) == sb.charAt(i + 1)) {
                occurrence++;
                i++;
            }
            result.append(occurrence);
            result.append(sb.charAt(i));
        }
        return result;
    }

    public static void main(String[] args) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "LookAndSay.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }
}
