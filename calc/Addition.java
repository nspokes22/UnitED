package calc;

import data.Operand;
import data.Unit;

/**
 * Addition class that adds two numbers together.
 * 
 * @author Robbie Deonarain
 * @version 1 This work complies with the JMU Honor Code.
 */
public class Addition implements CalcStrategy
{

  /**
   * Default Constructor.
   */
  public Addition()
  {
  }

  /**
   * Add two numbers together.
   * 
   * @return - Resulting value and unit
   */
  @Override
  public Operand calculate(final Operand left, final Operand right)
  {
    // add the 2 numbers that were in the input field
    return new Operand(left.getValue().add(right.getValue()), right.getUnit());
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
    boolean canCalculate = false;
    if (((UnitConversionEnum.isEnum(left) && UnitConversionEnum.isEnum(right))
        && !left.equalsUnit(right)))
    {
      canCalculate = true;
    }
    else if ((left == null && left == right) || left.equalsUnit(right))
    {
      canCalculate = true;
    }
    return canCalculate;
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
    return "+";
  }
}
