package testing;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import calc.Division;
import data.Digit;
import data.Operand;
import data.SingleUnit;
import utilities.UnitException;

/**
 * Division test class.
 * 
 * @author Robbie Deonarain
 * @version 1
 */
class DivisionTest
{

  @Test
  void test()
  {
    Division d = new Division();
    assertEquals(new Digit("15"), d.calculate(new Operand(new Digit("15"), new SingleUnit("mg")),
        new Operand(new Digit("1"), new SingleUnit("mg"))).getValue());
    
    UnitException err = assertThrows(UnitException.class, () -> 
    {
      d.calculate(new Operand(new Digit("15"), new SingleUnit("mg")),
          new Operand(new Digit("0"), new SingleUnit("mg"))).getValue();
    });
    assertEquals(UnitException.DIVIDE_BY_ZERO, err.getErrorType());
    
    assertTrue(d.canCalculate(new SingleUnit("a"), new SingleUnit("b")));
    assertFalse(d.isPower());
    assertEquals("\u00F7", d.toString());
  }
}
