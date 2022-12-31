package data;

/**
 * The Unit interface.
 * 
 * 
 * @author Samantha Steigleman-cox
 * @version 1
 * 
 *          This work complies with the JMU Honor Code.
 */
public interface Unit
{
  /**
   * Determines if the stored unit contains a specified unit.
   * 
   * @param other - Unit to check if the current unit contains
   * @return - Whether the stored unit contains a specified unit
   */
  public abstract boolean contains(Unit other);
  
  /**
   * Creates a deep copy of the current unit.
   * 
   * @return - Deep copy of the current unit
   */
  public abstract Unit copyUnit();
  
  /**
   * Determines if one unit is the same as another.
   * 
   * @param other - Other unit to check on
   * @return - Whether the two units are equal
   */
  public abstract boolean equalsUnit(Unit other);
  
  /**
   * Gets the inverse of the current unit.
   * 
   * @return - Flipped version of the unit
   */
  public abstract String flip();
  
  /**
   * Gets the left unit of the expression.
   * 
   * @return - Left unit of the expression
   */
  public abstract Object getLeft();
  
  /**
   * Gets the String representation of the unit's name without its power.
   * 
   * @return - String representation of the unit's name without its power
   */
  public abstract String getName();
  
  /**
   * Get the unit's power.
   * 
   * @return - The unit's power
   */
  public abstract int getPower();
  
  /**
   * Gets the right unit of the expression.
   * 
   * @return - Right unit of the expression
   */
  public abstract Object getRight();

  /**
   * Determines the type of the unit.
   * 
   * @return - The type of the unit - '1' for SingleUnit, '-' for DashConjunction,
   * and '/' for SlashConjunction
   */
  public abstract String getType();

  /**
   * Determines if the current unit is a conjunction.
   * 
   * @return - Whether the current unit is a conjunction
   */
  public abstract boolean isConjunction();
  
  /**
   * Multiplies the power by a certain number.
   * 
   * @param factor - Factor to multiply power by
   */
  public abstract void multiplyPower(int factor);
  
  /**
   * Sets the power of the current unit.
   * 
   * @param power - Power to set the unit to
   */
  public abstract void setPower(int power);
  
  /**
   * Simplifies the given expression.
   * 
   * @return - Simplified unit for the given expression
   */
  public abstract Unit simplify();

  /**
   * Gets the String representation of the unit's name including its power.
   * 
   * @return - String representation of the unit's name including its power
   */
  public abstract String toString();
}
