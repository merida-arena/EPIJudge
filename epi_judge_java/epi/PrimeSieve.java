package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PrimeSieve {
    @EpiTest(testDataFile = "prime_sieve.tsv")
    // Given n, return all primes up to and including n.
    public static List<Integer> generatePrimes(int n) {
        List<Boolean> isPrimeList = new ArrayList<>(Collections.nCopies(n + 1, true));
        List<Integer> result = new ArrayList<>();

        isPrimeList.set(0, false);
        isPrimeList.set(1, false);

        for (int i = 2; i <= n; i++) {
            if (isPrimeList.get(i)) {
                result.add(i);

                for (int j = i; j <= n; j+=i) {
                    isPrimeList.set(j, false);
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "PrimeSieve.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }
}
