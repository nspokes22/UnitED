package data;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

/**
 * Can create Slash Conjunction Units, has a method that allows for conversion of Units If they are
 * involved with a division expression.
 * 
 * @author Samantha Steigleman-Cox
 * @version 1
 * 
 *          This work complies with the JMU Honor Code.
 *
 */
public class SlashConjunction extends Conjunction
{
  private static final String EMPTY = "";
  private static final String ONE = "1";
  private static final String SLASH = "/";
  private boolean flipped;
  
  /**
   * Constructs a SlashConjunction object.
   * 
   * @param left
   *          - The left unit
   * @param right
   *          - The right unit
   */
  public SlashConjunction(final Unit left, final Unit right)
  {
    this(left, right, 1);
  }

  /**
   * Creates SlashConjunction Object.
   * @param string
   */
  public SlashConjunction(final String string)
  {
    super(string);
    flipped = false;
  }

  /**
   * Constructs a SlashConjunction object with a specified power.
   * 
   * @param left
   *          - The left unit
   * @param right
   *          - The right unit
   * @param power
   *          - Specified power
   */
  private SlashConjunction(final Unit left, final Unit right, final int power)
  {
    super(left, right, power);
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
    return new SlashConjunction(super.getLeft().copyUnit(), super.getRight().copyUnit(),
        super.getPower());
  }
  
  @Override
  public String flip()
  {
    flipped = !flipped;
    String str = EMPTY;
    if (flipped)
    {
      str += toString();
    }
    else
    {
      str += left.flip() + "-" + right.toString();
    }
    return str;
  }

  /**
   * Gets the type of unit the current unit is.
   * 
   * @return - Stored unit is a SlashConjunction object, so it will always return a slash.
   */
  @Override
  public String getType()
  {
    return SLASH;
  }

  /**
   * Takes the given Units and appropriately converts them at they would with Division.
   * 
   * @param unit
   * @return Unit
   */
  public static Unit convertMultiSlash(final SlashConjunction unit)
  {
      if (unit.getLeft().equals(unit.getRight()) 
              || unit.getLeft().toString().equals(unit.getRight().toString())) 
      {
          return new SingleUnit(EMPTY, 0);
      }

      List<Unit> units = expandSlashConjunction(unit);
      List<Unit> numerator = new ArrayList<Unit>();
      List<Unit> denominator = new ArrayList<Unit>();
       ListIterator<Unit> it = units.listIterator();

       while (it.hasNext())
       {
           numerator.add(it.next());
           if (it.hasNext())
           {
               denominator.add(it.next());
           }
       }
      Unit num = newDash(numerator);
      Unit denom = newDash(denominator);
      SlashConjunction lastsc = new SlashConjunction(num, denom);
      return cancel(lastsc);
  }
  
  /**
   * Creates a new DashConjunction from a List of Units. If the list only has one Unit
   * the method returns that Unit.
   * @param bits Units
   * @return Unit
   */
  protected static Unit newDash(final List<Unit> bits)
  {
	  if (bits.size() > 1) 
	  {
		  DashConjunction dc = new DashConjunction(bits.get(0), bits.get(1));
			if (bits.size() > 2) 
			{
				for (int j = 2; j < bits.size(); j++) 
				{
					if (bits.get(j).getPower() > 0) 
					{
						dc = new DashConjunction(dc, bits.get(j));
					}
				}	
			}
			return dc;
	  } else if (bits.size() == 0) 
	  {
		  return new SingleUnit(EMPTY, 0);
	  }
	  return bits.get(0);
  }
	  
  /**
   * This method iterates through a SlashConjunction, finding like Units on each side of the
   * Slash, removing the appropriate Unit, and returning a completely canceled Unit.
   * @param unit SlashConjunction
   * @return Unit
   */
  public static Unit cancel(final SlashConjunction unit) 
  { 
	  List<Unit> numeratorCopy = DashConjunction.expandDashConjunction(
	      condense(unit.getLeft().copyUnit()));
	  List<Unit> denominatorCopy = DashConjunction.expandDashConjunction(
	      condense(unit.getRight().copyUnit()));
	  ListIterator<Unit> itn = numeratorCopy.listIterator();
	  
	  if (unit.getRight().getName().equals(ONE) || unit.getRight().getName().equals(""))
	  {
	    return unit.getLeft();
	  }
	  
	  while (itn.hasNext())
	  {
		  Unit num = itn.next();
		  ListIterator<Unit> itd = denominatorCopy.listIterator();
		  while (itd.hasNext())
		  {
			  Unit denom = itd.next();
			  if (num.getName().equals(denom.getName()) && !(num==denom))
			  {
				  if (num.getPower()-denom.getPower() > 0)
				  {
					  num.setPower(num.getPower()-denom.getPower());
					  itd.remove();
				  } else if (denom.getPower()-num.getPower() > 0)
				  {
					  denom.setPower(denom.getPower()-num.getPower());
					  itn.remove();
				  } else 
				  {
					  itn.remove();
					  itd.remove();
				  }
			  }
		  }
	  }
	  return helpCancel(numeratorCopy, denominatorCopy);
  }
  
  private static Unit helpCancel(final List<Unit> numeratorCopy, final List<Unit> denominatorCopy)
  {
	  if (numeratorCopy.size() == 0 && denominatorCopy.size() == 0)
	  {
		  return new SingleUnit("", 0);
	  } else if (numeratorCopy.size() == 0)
	  {
		  return new SlashConjunction(new SingleUnit(ONE), newDash(denominatorCopy));
	  } else if (denominatorCopy.size() == 0)
	  {
		  return newDash(numeratorCopy);
	  } else
	  {
		  return new SlashConjunction(newDash(numeratorCopy), newDash(denominatorCopy));
	  }
  }
  
  public static List<Unit> expandSlashConjunction(final Unit unit)
  {
      List<Unit> unitList = new ArrayList<Unit>();
      if (unit.isConjunction()) 
      {
          Conjunction conj = copyUnit(unit);
          if (conj.getLeft().toString().contains(SLASH)) 
          {
              unitList.addAll(expandSlashConjunction(conj.getLeft()));
          } else 
          {
              unitList.add(conj.getLeft());
          }
          if (conj.getRight().toString().contains(SLASH))
          {
              unitList.addAll(expandSlashConjunction(conj.getRight()));
          } else 
          {
              unitList.add(conj.getRight());
          }
      } else 
      {
          unitList.add(unit);
      }
      return unitList;
  }
}
