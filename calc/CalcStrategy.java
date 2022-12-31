package calc;

import data.Operand;
import data.Unit;

/**
 * Calculate strategy interface that all the operators are able to use.
 *
 * @author Robbie Deonarain
 * @version 1
 * This work complies with the JMU Honor Code.
 */
public interface CalcStrategy
{
  
  /**
   * Determines if the calculation can be done based on the inputted units.
   * 
   * @param left - The left unit
   * @param right - The right unit
   * @return - Whether the calculation can be done
   */
  public abstract boolean canCalculate(final Unit left, final Unit right);

  /**
   * The calculate method that does all the calculations based on the operator.
   * 
   * @param left
   *          the left digit of the operator
   * @param right
   *          the right digit of the operator
   * @return a result that is a double given the operator
   */
  public abstract Operand calculate(final Operand left, final Operand right);
  
  /**
   * Returns whether or not the CalcStrategy is an IntegerPower.
   * @return - boolean representing whether or not the CalcStrategy is an IntegerPower
   */
  public abstract boolean isPower();
}
