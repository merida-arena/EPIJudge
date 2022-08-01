package epi.strings;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

public class SpreadsheetEncoding {
  @EpiTest(testDataFile = "spreadsheet_encoding.tsv")

  // c - 'A' + 1 because in question A represent 1, B 2, C 3 and so on, Z 26.
  // result * 26 because A represent 1, AA represent 27 (1+26), AB represent represent 28 ... , BA represent 53 (2*26 + 1), BB 54 ....
  public static int ssDecodeColID(final String col) {
    int result = 0;
    for(int i = 0; i < col.length(); i++){
      char c = col.charAt(i);
      result = result * 26 + c - 'A' + 1;
    }
    return result;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "SpreadsheetEncoding.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
