package testing;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import calc.Addition;
import data.Digit;
import data.Operand;
import data.SingleUnit;

/**
 * Addition test class.
 * 
 * @author Robbie Deonarain
 * @version 1
 *
 */
class AdditionTest
{

  @Test
  void test()
  {
    Addition a = new Addition();
    assertEquals(new Digit("16"), a.calculate(new Operand(new Digit("15"), new SingleUnit("mg")),
        new Operand(new Digit("1"), new SingleUnit("mg"))).getValue());
    assertNotEquals(new Digit("16"), a.calculate(new Operand(new Digit("15"), new SingleUnit("mg")),
        new Operand(new Digit("2"), new SingleUnit("mg"))).getValue());
  }

}
