package epi.primitive_types;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

public class PowerXY {
  // time :O(n)
  @EpiTest(testDataFile = "power_x_y.tsv")
  public static double power(double x, int y) {
    double result =1;
    if(y < 0){
      x = 1/x;
      y = -y;
    }
    while (y!=0){
      if((y &1)==1){
        result *= x;
      }

      x *= x;
      y >>>= 1;
    }
    return result;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "PowerXY.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
