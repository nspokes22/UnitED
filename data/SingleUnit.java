package data;

import utilities.Power;

/**
 * Encapsulation of the data for a single unit.
 * 
 * @author Samantha Steigleman-cox
 *
 */
public class SingleUnit implements Unit
{
  private static final String EMPTY = "";
  private static final String ONE = "1";
  private boolean empty;
  private boolean flipped;
  private int power;
  private String name;

  /**
   * Constructs a SingleUnit object.
   * 
   * @param name
   *          - Name of the unit
   */
  public SingleUnit(final String name)
  {
    this(name, 1);
  }

  /**
   * Constructs a SingleUnit object with a specified power.
   * 
   * @param name
   *          - Name of the unit
   * @param power
   *          - Power to set the unit to
   */
  public SingleUnit(final String name, final int power)
  {
    this.name = name;
    this.power = power;
    empty = false;
    flipped = false;
    if (name.equals(""))
    {
      empty = true;
    }
  }

  /**
   * Determines if the stored unit contains a specified unit.
   * 
   * @return - Whether the stored unit contains a specified unit
   */
  @Override
  public boolean contains(final Unit other)
  {
    return this.equals(other);
  }

  /**
   * Creates a deep copy of the current unit.
   * 
   * @return - Deep copy of the current unit
   */
  @Override
  public Unit copyUnit()
  {
    return new SingleUnit(new String(name), power);
  }

  /**
   * Determines if one unit is the same as another.
   * 
   * @param other
   *          - Other unit to check on
   * @return - Whether the two units are equal
   */
  public boolean equalsUnit(final Unit other)
  {
    return this.toString().equals(other.toString());
  }

  @Override
  public String flip()
  {
    flipped = !flipped;
    String str = EMPTY;
    if (!name.equals(EMPTY) && !name.equals(ONE) && !flipped)
    {
      str += toString();
    }
    else
    {
      str += "1/" + toString();
    }
    return str;
  }

  /**
   * Gets the String representation of the unit's name without its power.
   * 
   * @return - String representation of the unit's name without its power
   */
  @Override
  public String getName()
  {
    return this.name;
  }

  /**
   * Get the unit's power.
   * 
   * @return - The unit's power
   */
  @Override
  public int getPower()
  {
    int pow = this.power;
    if (empty)
    {
      pow = 1;
    }
    return pow;
  }

  /**
   * Determines the type of the unit.
   * 
   * @return - Since the current unit is a SingleUnit, it will always return '1'
   */
  @Override
  public String getType()
  {
    return ONE;
  }

  /**
   * Determines if the current unit is a conjunction.
   * 
   * @return - Since the current unit is a SingleUnit, it will always return false
   */
  @Override
  public boolean isConjunction()
  {
    return false;
  }

  /**
   * Multiplies the power by the given factor.
   * 
   * @param factor
   *          The factor to multiply the power by
   */
  @Override
  public void multiplyPower(final int factor)
  {
    power *= factor;
  }

  /**
   * Sets the power of the current unit.
   * 
   * @param power
   *          - Power to set the unit to
   */
  @Override
  public void setPower(final int power)
  {
    if (empty)
    {
      this.power = 1;
    }
    else
    {
      this.power = power;
    }
  }

  /**
   * Returns this Unit.
   * 
   * @return - this Unit
   */
  @Override
  public Unit simplify()
  {
    return this;
  }

  /**
   * Gets the String representation of the unit's name including its power.
   * 
   * @return - String representation of the unit's name including its power
   */
  @Override
  public String toString()
  {
    String str = this.name;
    if (this.power > 1 && !empty)
    {
      str += Power.createPower(this.power);
    }
    return str;
  }

  @Override
  public Object getLeft()
  {
    return null;
  }

  @Override
  public Object getRight()
  {
    return null;
  }
}
