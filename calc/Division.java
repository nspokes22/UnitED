package calc;

import data.Operand;
import data.SlashConjunction;
import data.Unit;

/**
 * Division class that divides two numbers.
 * 
 * @author Robbie Deonarain
 * @version 1 This work complies with the JMU Honor Code.
 */
public class Division implements CalcStrategy
{

  /**
   * Default Constructor.
   */
  public Division()
  {
  }

  /**
   * Divide one number by another number.
   * 
   * @return - Resulting value and unit
   */
  @Override
  public Operand calculate(final Operand left, final Operand right)
  {
    // do the division with the given inputs
    return new Operand(left.getValue().divide(right.getValue()),
        SlashConjunction.convertMultiSlash(new SlashConjunction(
        		left.getUnit(), right.getUnit())).simplify());
  }

  /**
   * Determines if the calculation can be done based on the inputted units.
   * 
   * @param left
   *          - The left unit
   * @param right
   *          - The right unit
   * @return - Whether the calculation can be done
   */
  @Override
  public boolean canCalculate(final Unit left, final Unit right)
  {
    return true;
  }
  
  /**
   * Returns whether or not the CalcStrategy is an IntegerPower.
   * @return - boolean representing whether or not the CalcStrategy is an IntegerPower
   */
  @Override
  public boolean isPower()
  {
    return false;
  }

  /**
   * Gets the String representation of the operation.
   * 
   * @return - String representation of the operation
   */
  @Override
  public String toString()
  {
    return "\u00F7";
  }
}
