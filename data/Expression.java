package data;

/**
 * The Expression class.
 * 
 * @author Samantha Steigleman-cox
 * @version 1
 * This work complies with the JMU Honor Code.
 */

public interface Expression
{
  /**
   * Gets the numeric value of the stored expression.
   * 
   * @return - Numeric value of the stored expression
   */
  public Digit getValue();

  /**
   * Gets the unit of the stored expression.
   * 
   * @return - Unit of the stored expression
   */
  public Unit getUnit();
}
