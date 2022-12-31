package data;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

/**
 * Can create Dash Conjunction Units, has a method that allows for conversion of Units if they are
 * involved with a multiplication expression.
 * 
 * @author Samantha Steigleman-Cox
 * @version 1
 * 
 *          This work complies with the JMU Honor Code.
 */
public class DashConjunction extends Conjunction
{
  private static final String DASH = "-";
  private static final String EMPTY = "";
  private static final String ONE = "1";
  private static final String SLASH = "/";
  private boolean flipped;

  /**
   * Constructs a DashConjunction object.
   * 
   * @param left
   *          - The left unit
   * @param right
   *          - The right unit
   */
  public DashConjunction(final Unit left, final Unit right)
  {
    this(left, right, 1);
  }

  /**
   * Constructs a DashConjunction object with a specified power.
   * 
   * @param left
   *          - The left unit
   * @param right
   *          - The right unit
   * @param power
   *          - Specified power
   */
  private DashConjunction(final Unit left, final Unit right, final int power)
  {
    super(left, right, power);
    flipped = false;
  }

  /**
   * Creates a DashConjunction Object.
   * 
   * @param string
   */
  public DashConjunction(final String string)
  {
    super(string);
    flipped = false;
  }

  /**
   * Creates a deep copy of the current unit.
   * 
   * @return - Deep copy of the current unit
   */
  @Override
  public Unit copyUnit()
  {
    return new DashConjunction(super.getLeft().copyUnit(), super.getRight().copyUnit(),
        super.getPower());
  }

  @Override
  public String flip()
  {
    flipped = !flipped;
    String str = EMPTY;
    str += left.flip() + DASH + right.flip();
    return str;
  }

  /**
   * Gets the type of unit the current unit is.
   * 
   * @return - Stored unit is a DashConjunction object, so it will always return a dash.
   */
  @Override
  public String getType()
  {
    return DASH;
  }

  /**
   * Takes the given Units and appropriately converts them as they would with Multiplication.
   * 
   * @param unit
   *          DashConjunction
   * @return Unit
   */
  public static Unit convertUnitsMultiplication(final DashConjunction unit)
  {
    Unit temp = unit;
    List<Unit> numerator = new ArrayList<Unit>();
    List<Unit> denominator = new ArrayList<Unit>();
    boolean found = false;
    if (unit.getLeft().getName().equals(""))
    {
      return unit.getRight();
    }
    else if (unit.getRight().getName().equals(""))
    {
      return unit.getLeft();
    }
    if (unit.toString().contains(SLASH))
    {
      ListIterator<Unit> it = expandDashConjunction(unit).listIterator();
      while (it.hasNext())
      {
        Unit u = it.next();
        if (found)
        {
          if (u.getType().equals(SLASH))
          {
            Conjunction conj = copyUnit(u);
            SlashConjunction.convertMultiSlash((SlashConjunction) u);
            numerator.add(conj.getLeft());
            denominator.add(conj.getRight());
          }
          else
          {
            numerator.add(u);
          }
        }
        if (u.getType().equals(SLASH) && !found)
        {
          found = true;
          if (((Unit) u.getRight()).getType().equals(SLASH))
          {
            SlashConjunction.convertMultiSlash((SlashConjunction) u);
          }
          numerator.add((Unit) u.getLeft());
          denominator.add((Unit) u.getRight());
        }
        else if (!found)
        {
          numerator.add(u);
        }
      }
      SlashConjunction sc = new SlashConjunction(SlashConjunction.newDash(numerator),
          SlashConjunction.newDash(denominator));
      temp = SlashConjunction.convertMultiSlash(sc);
      if (temp.getLeft() != null)
      {
        if (temp.getLeft().toString().equals(""))
        {
          temp = new SlashConjunction(new SingleUnit(ONE), (Unit) temp.getRight());
        }
      }
    }
    else if (unit.getType().equals(DASH))
    {
      temp = condense(unit);
    }
    return temp;
  }

  /**
   * Expands a DashConjunction into a List of each Unit within the DashConjunction.
   * 
   * @param unit
   *          Unit
   * @return List<Unit>
   */
  protected static List<Unit> expandDashConjunction(final Unit unit)
  {
    List<Unit> unitList = new ArrayList<Unit>();
    if (unit.getType().equals(DASH))
    {
      Conjunction conj = copyUnit(unit);
      if (conj.getLeft().getType().equals(DASH))
      {
        unitList.addAll(expandDashConjunction((DashConjunction) conj.getLeft()));
      }
      else
      {
        unitList.add(conj.getLeft());
      }
      if (conj.getRight().getType().equals(DASH))
      {
        unitList.addAll(expandDashConjunction((DashConjunction) conj.getRight()));
      }
      else
      {
        unitList.add(conj.getRight());
      }
    }
    else
    {
      unitList.add(unit);
    }
    return unitList;
  }
}
