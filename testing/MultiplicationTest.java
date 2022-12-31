package testing;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import calc.Multiplication;
import data.Digit;
import data.Operand;
import data.SingleUnit;

/**
 * 
 * This class tests the Multiplication class.
 * 
 * @author Robbie Deonarain
 * @version 1
 */

class MultiplicationTest
{

  @Test
  void test()
  {
    Multiplication m = new Multiplication();
    assertEquals(new Digit("4856.6961"),
        m.calculate(new Operand(new Digit("69.69"), new SingleUnit("mg")),
            new Operand(new Digit("69.69"), new SingleUnit("mg"))).getValue());
    assertNotEquals(new Digit("324234"),
        m.calculate(new Operand(new Digit("2131"), new SingleUnit("mg")),
            new Operand(new Digit("31231"), new SingleUnit("mg"))).getValue());
  }

}
