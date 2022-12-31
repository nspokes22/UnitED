package testing;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import data.*;
import utilities.Power;

/**
 * This class is used to test the Operand class.
 * 
 * @author Samantha Steigleman-Cox
 * @version 1
 * 
 *          This work complies with the JMU Honor Code.
 */

class OperandTest
{
  private static final String CAT = "cat";
  private static final Digit DIGIT = new Digit("10");
  private static final Digit DIGIT_D = new Digit("5.75");
  private static final Unit CONJ = new SingleUnit(CAT, 2);
  private static final Unit UNIT = new SingleUnit(CAT);

  /**
   * Tests the Operand constructor.
   */

  @Test
  void test()
  {
    Operand operand = new Operand(DIGIT, UNIT);
    assertEquals(DIGIT, operand.getValue());
    assertEquals(UNIT, operand.getUnit());
    
    Operand op = new Operand(DIGIT);
    assertEquals(DIGIT, op.getValue());
    assertEquals("", op.getUnit().getName());
  }

  /**
   * Tests the toString() method.
   */

  @Test
  void toStringTest()
  {
    final String TEN = "10 cat";
    Operand operand = new Operand(DIGIT, UNIT);
    assertEquals(TEN, operand.toString());
    Operand operandTwo = new Operand(DIGIT_D, UNIT);
    assertEquals("5.75 cat", operandTwo.toString());
    Operand operandThree = new Operand(DIGIT, CONJ);
    assertEquals(TEN + Power.createPower(2), operandThree.toString());
    
    Operand op = new Operand(DIGIT);
    assertEquals(DIGIT.toString(), op.toString());
    Operand optwo = new Operand(DIGIT_D);
    assertEquals(DIGIT_D.toString(), optwo.toString());
  }
}
