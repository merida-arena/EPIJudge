package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

public class ConvertBase {
  @EpiTest(testDataFile = "convert_base.tsv")

  // convert numAsString to dec -> by multiply by b1
  // eg: b1 = 7, b2 = 13 and numAsString = 615 -> 6 * 7^2 + 1 * 7 + 5 * 1 = 306
  // convert to b2 -> by taking mod with b2
  // convert 306 to b2,
  //        306 mod 13 -> 7
  //        23 mod 13 -> 10 (== A) [as mentioned in Question 10 represent A, 11 represent B and so on 15 for F]
  //        1 mod 13 -> 1
  //        res = 7A1 -> reverse this to get answer 1A7
  //        therefore, output 1A7
  public static String convertBase(String numAsString, int b1, int b2) {
    if (numAsString.equals("0"))
      return numAsString;

    boolean isNegative = false;
    if (numAsString.charAt(0) == '-') {
      isNegative = true;
    }

    int num = convertBaseToDec(numAsString, b1, isNegative);
    return convertDecToBase(num, b2, isNegative);
  }

  private static int convertBaseToDec(String numAsString, int b1, boolean isNegative) {
    int runningNum = 0;

    // if numAsString is negative set i = 1 otherwise 0
    /*
    Each char has a corresponding Unicode value between 0 and 65,535.
    Subtracting 'A' (or, 65) scales each letter in the alphabet to the 0-26 range that corresponds to the "buckets" in the arr array. Here's an example:

    System.out.println('Z' - 'A'); // Output: 25 (the last bucket in the array)
    System.out.println('A' - 'A'); // Output: 0 (the first bucket in the array)


    In question, it is said that A represent 10, B 11, C 12 and so on. By Adding 10, we are making A equals 10.
    numAsString.charAt(i) - 'A' + 10 => converting letter to decimal digit.

     */
    for(int i = (isNegative ? 1 : 0); i < numAsString.length(); i++){
      int digit = Character.isDigit(numAsString.charAt(i)) ?
              numAsString.charAt(i) - '0' :
              numAsString.charAt(i) - 'A' + 10;

      runningNum = runningNum * b1 + digit;
    }

    return runningNum;
  }

  private static String convertDecToBase(int num, int b2, boolean isNegative) {
    StringBuilder sb = new StringBuilder();

    while (num > 0){
      int remainder = num % b2;

      if (remainder == 10)
        sb = sb.append('A');
      else if (remainder == 11)
        sb = sb.append('B');
      else if (remainder == 12)
        sb = sb.append('C');
      else if (remainder == 13)
        sb = sb.append('D');
      else if (remainder == 14)
        sb = sb.append('E');
      else if (remainder == 15)
        sb = sb.append('F');
      else
        sb = sb.append(remainder);

      num = num / b2;
    }

    // if the original numAsString is negative, append - sign
    if (isNegative)
      sb.append('-');

    return sb.reverse().toString();
  }



  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "ConvertBase.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
