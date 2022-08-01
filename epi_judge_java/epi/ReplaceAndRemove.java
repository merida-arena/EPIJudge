package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TimedExecutor;

import java.util.ArrayList;
import java.util.List;

public class ReplaceAndRemove {

    // time: O(n) space : O(1)
    // first run to remove all b's -> from starting of the array till number of chars i.e. provide size.
    // second run to replace a's with 2 d's -> from end of the array, here depending of number of a's, the number of chars can increase i.e. the size (final size).
    public static int replaceAndRemove(int size, char[] s) {
        int wIndex = 0, countOfA = 0;

        // first run to remove all b's -> from starting of the array till number of chars i.e. provide size.
        for (int i = 0; i < size; i++) {
            if (s[i] != 'b') {
                s[wIndex++] = s[i];
            }
            if (s[i] == 'a')
                countOfA++;
        }

        // second run to replace a's with 2 d's -> from end of the array, here depending of number of a's, the number of chars can increase i.e. the size (final size).
        int lastCharIndex = wIndex - 1;

        //from backwards
        wIndex = wIndex + countOfA - 1;
        //finalValidCharSize includes number of chars + extra size due to a's
        int finalValidCharSize = wIndex + 1;

        while (lastCharIndex >= 0) {
            if (s[lastCharIndex] == 'a') {
                s[wIndex--] = 'd';
                s[wIndex--] = 'd';
            } else {
                s[wIndex--] = s[lastCharIndex];
            }
            lastCharIndex--;
        }
        return finalValidCharSize;
    }

    @EpiTest(testDataFile = "replace_and_remove.tsv")
    public static List<String>
    replaceAndRemoveWrapper(TimedExecutor executor, Integer size, List<String> s)
            throws Exception {
        char[] sCopy = new char[s.size()];
        for (int i = 0; i < size; ++i) {
            if (!s.get(i).isEmpty()) {
                sCopy[i] = s.get(i).charAt(0);
            }
        }

        Integer resSize = executor.run(() -> replaceAndRemove(size, sCopy));

        List<String> result = new ArrayList<>();
        for (int i = 0; i < resSize; ++i) {
            result.add(Character.toString(sCopy[i]));
        }
        return result;
    }

    public static void main(String[] args) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "ReplaceAndRemove.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }
}
