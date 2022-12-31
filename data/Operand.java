package data;

import java.math.MathContext;
import java.text.DecimalFormat;

/**
 * Operand hold the user inputed values for number and Unit.
 * 
 * @author Samantha Steigleman-Cox
 * @version 1 This work complies with the JMU Honor Code.
 */
public class Operand implements Expression
{
  private static final Unit EMPTY = new SingleUnit("");
  private Digit number;
  private Unit unit;

  /**
   * Constructs an Operand object.
   * 
   * @param number
   */
  public Operand(final Digit number)
  {
    this(number, EMPTY);
  }

  /**
   * Constructs an Operand object.
   * 
   * @param number
   * @param unit
   */
  public Operand(final Digit number, final Unit unit)
  {
    this.number = number;
    this.unit = unit;
  }

  /**
   * For the Invert functionality, flips both Unit and Digit value of Operand.
   */
  public void flip()
  {
    this.unit = Conjunction.flip(this.unit);
    String str = Digit.ONE.divide(number, MathContext.DECIMAL32).stripTrailingZeros().toString();
    this.number = new Digit(str);
  }

  /**
   * Gets the numeric value of the stored operand.
   * 
   * @return - Numeric value of the stored operand
   */
  @Override
  public Digit getValue()
  {
    return this.number;
  }

  /**
   * Gets the unit of the stored operand, if there is no Unit stored in the Operand, this method
   * returns null.
   * 
   * @return - Unit of the stored operand
   */
  @Override
  public Unit getUnit()
  {
    return this.unit;
  }

  /**
   * Returns the String representation of the stored Operand.
   * 
   * @return - String representation of the stored Operand
   */
  @Override
  public String toString()
  {
    DecimalFormat form = new DecimalFormat("0.000");
    form.setMinimumFractionDigits(0);
    form.setMaximumFractionDigits(5);
    String str = form.format(number);
    if (!unit.equals(EMPTY))
    {
      str += " " + unit.toString();
    }
    return str;
  }
}
