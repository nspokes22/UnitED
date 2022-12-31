package calc;

import data.DashConjunction;
import data.Operand;
import data.Unit;

/**
 * Multiplication class to do multiplication if the multiply button is used.
 * 
 * @author Robbie Deonarain
 * @version 1
 * This work complies with the JMU Honor Code.
 */
public class Multiplication implements CalcStrategy
{

  /**
   * Default constructor.
   */
  public Multiplication()
  {
  }

  /**
   * Multiply two numbers together.
   * 
   * @return - Resulting value and unit
   */
  @Override
  public Operand calculate(final Operand left, final Operand right)
  {
    // multiply the 2 numbers and convert the units if they are both different
    return new Operand(left.getValue().multiply(right.getValue()),
        DashConjunction.convertUnitsMultiplication(new DashConjunction(
        		left.getUnit(), right.getUnit())).simplify());
  }
  
  /**
   * Determines if the calculation can be done based on the inputted units.
   * 
   * @param left - The left unit
   * @param right - The right unit
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
    return "x";
  }
}
