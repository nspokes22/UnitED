package data;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

import utilities.UnitException;

/**
 * Holds a very large decimal value.
 * 
 * @author Nick Spokes
 * @version 1
 */
public class Digit extends BigDecimal
{
	private static final long serialVersionUID = 1L;

	/**
   	* Constructs a Digit object.
   	* 
   	* @param val - String representation of the decimal value
   	*/
	public Digit(final String val)
	{
		super(val);
		super.stripTrailingZeros();
	}

	/**
	 * Constructs a Digit object from another BigDecimal object.
	 * 
	 * @param val - BigDecimal to take the value from
	 */
	private Digit(final BigDecimal val)
	{
		this(val.toString());
	}
  
	/**
	 * Adds a double value.
	 * 
   * @param val - Value to add
   * @return - Resulting value
   */
  public Digit add(final double val)
  {
    return new Digit(super.add(new BigDecimal(val)));
  }

  /**
   * Adds another Digit value.
   * 
   * @param val - Value to add
   * @return - Resulting value
   */
  public Digit add(final Digit val)
  {
    return new Digit(super.add(val));
  }

  /**
   * Determines if the value of this number equals a given double.
   * 
   * @param val - Value to check if it equals
   * @return - Whether the numbers are equal
   */
  public boolean equalsDigit(final double val)
  {
    return super.stripTrailingZeros().equals(new BigDecimal(val));
  }
  
  /**
   * Get the double value of the Digit.
   * 
   * @return - Double value of the Digit
   */
  public double getValueDouble()
  {
    return super.doubleValue();
  }
  
  /**
   * Determines if the value of this number is an integer value. (ie: 12.000)
   * 
   * @return - Whether the number is an integer value
   */
  public boolean isIntValue()
  {
    return super.signum() == 0 || super.scale() <= 0 || super.stripTrailingZeros().scale() <= 0;
  }
  
  /**
   * Returns this raised to the power of the value of the power parameter.
   * 
   * @param power - Represents the value to raise this Digit to 
   * @return - A Digit representing this Digit raised to the power of the value of "power"
   */
  public Digit pow(final Digit power)
  {
    return new Digit(super.pow((int) power.getValueDouble(), MathContext.DECIMAL32));
  }

  /**
   * Subtract a double value.
   * 
   * @param val - Value to subtract
   * @return - Resulting value
   */
  public Digit subtract(final double val)
  {
    return new Digit(super.subtract(new BigDecimal(val)));
  }

  /**
   * Subtract another Digit value.
   * 
   * @param val - Value to subtract
   * @return - Resulting value
   */
  public Digit subtract(final Digit val)
  {
    return new Digit(super.subtract(val));
  }

  /**
   * Multiply a double value.
   * 
   * @param val - Value to multiply
   * @return - Resulting value
   */
  public Digit multiply(final double val)
  {
    return new Digit(super.multiply(new BigDecimal(val)));
  }

  /**
   * Multiply another Digit value.
   * 
   * @param val - Value to multiply
   * @return - Resulting value
   */
  public Digit multiply(final Digit val)
  {
    return new Digit(super.multiply(val));
  }

  /**
   * Divide by a double value.
   * 
   * @param val - Value to divide by
   * @return - Resulting value
   */
  public Digit divide(final double val)
  {
    return divide(new BigDecimal(val));
  }

  /**
   * Divide by another Digit value.
   * 
   * @param val - Value to divide by
   * @return - Resulting value
   */
  public Digit divide(final BigDecimal val)
  {
    if (val.equals(ZERO))
    {
      throw new UnitException(UnitException.DIVIDE_BY_ZERO);
    }
    return new Digit(super.divide(new BigDecimal(val.toString()), 5, RoundingMode.HALF_UP)
        .stripTrailingZeros().toPlainString());
  }

  /**
   * Get the String representation of the Digit.
   * 
   * @return - String representation of the Digit
   */
  public String toString()
  {
    return super.toString();
  }
}
