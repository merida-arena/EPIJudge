package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TimedExecutor;

public class ReverseWords {

    // time O(n)
    public static void reverseWords(char[] input) {
        int start = 0, end = input.length;

        // reverse whole sentence.
        reverse(input, start, end-1);

        // reverse individual words.
        // this will reverse all words until last word.
        // end holds here the index of space.
        end = findWordSizeBeforeSpace(input, start);
        while (end != -1) {
            reverse(input, start, end-1);
            // end holds here the index of space, start from next char to space.
            start = end + 1;
            // the index of next space.
            end = findWordSizeBeforeSpace(input, start);
        }

        // reverse the last word.
        reverse(input, start, input.length-1);

        return;
    }

    private static void reverse(char[] input, int start, int end) {
        if (start > end) {
            return;
        }

        // we are swapping from start and end.
        // in the middle, they will be swapped.
        for (int i = start; i <= start + (end - start) / 2; i++) {
            char temp = input[i];
            input[i] = input[end - i + start];
            input[end -i + start] = temp;
        }
    }

    private static int findWordSizeBeforeSpace(char[] input, int start) {
        for (int i = start; i < input.length; i++) {
            if (input[i] == ' ') {
                return i;
            }
        }
        return -1;
    }

    @EpiTest(testDataFile = "reverse_words.tsv")
    public static String reverseWordsWrapper(TimedExecutor executor, String s)
            throws Exception {
        char[] sCopy = s.toCharArray();

        executor.run(() -> reverseWords(sCopy));

        return String.valueOf(sCopy);
    }

    public static void main(String[] args) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "ReverseWords.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }
}
