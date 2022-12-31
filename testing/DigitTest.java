package testing;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.Test;

import data.Digit;

/**
 * This class is used to test the Digit class.
 * 
 * @author Luke Bieniek
 * @version 1
 * 
 *          This work complies with the JMU Honor Code.
 */

public class DigitTest
{

  private static final String DIGIT = "45";
  private static final String DIGIT_2 = "2";

  /**
   * Tests the add() method.
   */

  @Test
  public void addTest()
  {
    Digit digit = new Digit(DIGIT);
    Digit digit2 = new Digit("3");
    Digit result = new Digit("48");

    assertEquals(result, digit.add(digit2), "add() method given a Digit argument");
    assertEquals(result, digit.add(3.0), "add() method given a double argument");
  }

  /**
   * Tests the subtract() method.
   */

  @Test
  public void subtractTest()
  {
    Digit digit = new Digit(DIGIT);
    Digit digit2 = new Digit("8");
    Digit result = new Digit("37");

    assertEquals(result, digit.subtract(digit2), "subtract() method given a Digit argument");
    assertEquals(result, digit.subtract(8.0), "subtract() method given a double argument");
  }

  /**
   * Tests the multiplication() method.
   */

  @Test
  public void multiplicationTest()
  {
    Digit digit = new Digit(DIGIT);
    Digit digit2 = new Digit(DIGIT_2);
    Digit result = new Digit("90");

    assertEquals(result, digit.multiply(digit2), "multiplication() method given a Digit argument");
    assertEquals(result, digit.multiply(2.0), "multiplication() method given a double argument");
  }

  /**
   * Tests the division() method.
   */

  @Test
  public void divisionTest()
  {
    Digit digit = new Digit(DIGIT);
    Digit digit2 = new Digit("9");
    Digit result = new Digit("5");

    assertEquals(result, digit.divide(digit2), "division() method given a Digit argument");
    assertEquals(result, digit.divide(9.0), "division() method given a double argument");
  }

  /**
   * Tests the equals() method.
   */

  @Test
  public void equalsTest()
  {
    Digit digit = new Digit(DIGIT);

    assertEquals(true, digit.equalsDigit(45.0), "equals() method with matching value");
    assertEquals(false, digit.equalsDigit(35.0), "equals() method with non-matching value");
  }

  /**
   * Tests the getValueDouble() method.
   */

  @Test
  public void getValueDoubleTest()
  {
    Digit digit = new Digit(DIGIT);
    assertEquals(45.0, digit.getValueDouble(), "getValueDouble() method");
  }
  
  /**
   * Tests the isIntValue() method.
   */
  
  @Test
  public void isIntValueTest()
  {
	  Digit digit = new Digit("6");
	  assertEquals(true, digit.isIntValue(), "isIntValue() method with no decimals");
	  
	  digit = new Digit("6.000");
	  assertEquals(true, digit.isIntValue(), "isIntValue() method with trailing zeros");
	  
	  digit = new Digit("0");
	  assertEquals(true, digit.isIntValue(), "isIntValue() method with zero");
	  
	  digit = new Digit("1.2");
	  assertEquals(false, digit.isIntValue(), "isIntValue() method with non-integer");
  }
  
  /**
   * Tests the pow() method.
   */
  
  @Test
  public void powTest()
  {
	  Digit digit = new Digit("4");
	  Digit power = new Digit(DIGIT_2);
	  Digit answer = new Digit("16");
	  
	  assertEquals(answer.getValueDouble(), digit.pow(power).getValueDouble(), "pow()");
  }

  /**
   * Tests the toString() method.
   */
  
  @Test
  public void toStringTest()
  {
	  Digit digit = new Digit(DIGIT);
	  
	  assertEquals(digit.toString(), DIGIT, "toString()");
  }
}
