package data;

import java.util.List;
import java.util.ListIterator;

/**
 * A type of Unit called a Conjunction, parent to Dash/SlashConjunction classes.
 * 
 * @author Samantha Steigleman-cox
 * @version 1
 * 
 *          This work complies with the JMU Honor Code.
 */
public abstract class Conjunction implements Unit
{
  private static final String SLASH = "/";
  private static final String DASH = "-";
  private static final String ONE = "1";
  protected Unit left;
  protected Unit right;
  protected String string;
  private int power;

  /**
   * Create Conjunction Object.
   * 
   * @param left
   *          the left side of the conjuction
   * @param right
   *          the right side of the conjuction
   * @param power
   *          the power that is used
   */
  protected Conjunction(final Unit left, final Unit right, final int power)
  {
    this.left = left;
    this.power = power;
    this.right = right;
  }

  /**
   * Added to assist InputParser.
   * 
   * @param string
   */
  public Conjunction(final String string)
  {
    this.string = string;
  }

  /**
   * Determines if the stored conjunction contains a specified unit.
   * 
   * @return - Whether the stored conjunction contains a specified unit
   */
  @Override
  public boolean contains(final Unit other)
  {
    return left.equals(other) || right.equals(other);
  }

  /**
   * Checks to see if two Conjunctions are equal.
   * 
   * @param other
   *          the unit to be compared
   * @return if the two conjuctions are equal
   */
  public boolean equalsUnit(final Unit other)
  {
    boolean equals = false;
    if (toString().equals(other.toString()))
    {
      equals = true;
    }
    return equals;
  }

  /**
   * Gets the left unit.
   * 
   * @return - The left unit
   */
  public Unit getLeft()
  {
    return left;
  }

  /**
   * Gets the String representation of the stored conjunction's name without its power.
   * 
   * @return - String representation of the stored conjunction's name
   */
  @Override
  public String getName()
  {
    return left.toString() + this.getType() + right.toString();
  }

  /**
   * Gets the power of the stored conjunction.
   * 
   * @return - The power of the stored conjunction
   */
  @Override
  public int getPower()
  {
    return this.power;
  }

  /**
   * Gets the right unit.
   * 
   * @return - The right unit
   */
  public Unit getRight()
  {
    return right;
  }

  /**
   * Determines if the current unit is a conjunction.
   * 
   * @return - The stored unit is a conjunction - always returns true
   */
  @Override
  public boolean isConjunction()
  {
    return true;
  }

  @Override
  public void multiplyPower(final int factor)
  {
    left.multiplyPower(factor);
    right.multiplyPower(factor);
  }

  /**
   * Sets the power of the stored conjunctino.
   * 
   * @param power
   *          - New power of the stored conjunction
   */
  @Override
  public void setPower(final int power)
  {
    left.setPower(power);
    right.setPower(power);
  }

  /**
   * Flip the left and the right side of conjuction.
   * 
   * @param unit
   *          the unit that we need to flip
   * @return the flipped unit
   */
  public static Unit flip(final Unit unit)
  {
    Conjunction u = null;
    if (!unit.getName().equals(""))
    {
      if (unit.getType().equals(SLASH))

      {
        u = copyUnit(unit);
        if (u.getLeft().getName().equals(ONE))
        {
          return u.getRight();
        }
        else
        {
          u = (Conjunction) SlashConjunction.convertMultiSlash((SlashConjunction) unit);
          return new SlashConjunction(u.getRight(), u.getLeft());
        }
      }
    }
    else
    {
      return unit;
    }
    return new SlashConjunction(new SingleUnit(ONE), unit);
  }

  /**
   * Finds like units inside of a conjuction and combines the powers of the like units.
   * 
   * @param unit
   *          the unit that we are condensing
   * @return the new combined unit with new like units and adjusted powers
   */
  public static Unit condense(final Unit unit)
  {
    Unit condensed = unit;
    if (unit.getName().contains(SLASH))
    {
      Conjunction conj = copyUnit(unit);
      Unit denominator = null;
      if (((Unit) conj.getLeft()).isConjunction()
          && ((Unit) ((Unit) conj.getLeft().getRight())).getName().equals(conj.getLeft().getName()))
      {
        denominator = new DashConjunction((Unit) conj.getLeft().getRight(), (Unit) unit.getRight());
        condensed = new SlashConjunction(new SingleUnit(ONE), denominator);
      }
    }
    else if (unit.getType().equals(DASH))
    {
      Conjunction conj = (Conjunction) deepCopyDashConj(unit);
      List<Unit> list = DashConjunction.expandDashConjunction((DashConjunction) conj);
      ListIterator<Unit> it = list.listIterator();

      for (int i = 0; i < list.size(); i++)
      {
        Unit u = list.get(i);
        it = list.listIterator();
        while (it.hasNext())
        {
          Unit un = it.next();
          if (un.getName().equals(u.getName()) && !(un == u))
          {
            u.setPower(u.getPower() + un.getPower());
            it.remove();
          }
          else if (un.getName().equals(ONE))
          {
            it.remove();
          }
        }
      }
      condensed = SlashConjunction.newDash(list);
    }
    return condensed;
  }

  @Override
  public Unit simplify()
  {
    Unit unit = this;
    if (this.getType().equals(SLASH))
    {
      unit = SlashConjunction.cancel(((SlashConjunction) this));
    }
    else
    {
      unit = condense(this);
    }
    return unit;
  }

  /**
   * Gets the String representation of the stored conjunction.
   * 
   * @return String representation of the stored conjunction
   */
  @Override
  public String toString()
  {
    return left.toString() + this.getType() + right.toString();
  }

  /**
   * Gets the given Unit as a conjunction, if it is a conjunction.
   * 
   * @param unit
   * @return Conjunction
   */
  protected static Conjunction copyUnit(final Unit unit)
  {
    Conjunction copy = null;
    if (unit.isConjunction())
    {
      if (unit.getType().equals(SLASH))
      {
        copy = new SlashConjunction(((Conjunction) unit).getLeft().copyUnit(),
            ((Conjunction) unit).getRight().copyUnit());
      }
      else
      {
        copy = new DashConjunction(((Conjunction) unit).getLeft().copyUnit(),
            ((Conjunction) unit).getRight().copyUnit());
      }
    }
    return copy;
  }

  /**
   * Creates a deep copy of the given unit.
   * 
   * @param unit
   *          - Unit to create a deep copy of
   * @return - Deep copy of the input unit
   */
  protected static Unit deepCopyDashConj(final Unit unit)
  {
    DashConjunction dc = new DashConjunction("");
    if (unit.getType().equals(DASH))
    {
      dc = new DashConjunction(deepCopyDashConj((Unit) unit.getLeft()),
          deepCopyDashConj((Unit) unit.getRight()));

    }
    else
    {
      return new SingleUnit(unit.getName(), unit.getPower());
    }
    return dc;
  }
}
