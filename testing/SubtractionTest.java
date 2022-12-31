package testing;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import calc.Subtraction;
import data.Digit;
import data.Operand;
import data.SingleUnit;

/**
 * Subtraction test class
 * 
 * @author Robbie Deonarain
 * @version 1
 *
 */
class SubtractionTest
{

  @Test
  void test()
  {
    Subtraction s = new Subtraction();
    assertEquals(new Digit("14"), s.calculate(new Operand(new Digit("15"), new SingleUnit("mg")),
        new Operand(new Digit("1"), new SingleUnit("mg"))).getValue());
    assertEquals(new Digit("432.381"),
        s.calculate(new Operand(new Digit("476.123"), new SingleUnit("mg")),
            new Operand(new Digit("43.742"), new SingleUnit("mg"))).getValue());
  }

}
