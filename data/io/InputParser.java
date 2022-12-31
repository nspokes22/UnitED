package data.io;

import java.util.ArrayList;
import java.util.List;
import data.DashConjunction;
import data.Digit;
import data.Operand;
import data.SingleUnit;
import data.SlashConjunction;
import data.Unit;
import utilities.UnitException;

/**
 * InputParser - parses the user input and assigns to corresponding variables.
 * 
 * @author Katherine Hassler
 * @version 2
 */
public class InputParser
{
  // Regular expressions
  private static final String CONJUNCTION_ID = "[-/]";
  private static final String VALID_NUM = "[0-9.-]*";
  private static final String VALID_UNIT_CHARS = "[A-Za-z1$]";

  // Other constants
  private static final String DASH = "-";
  private static final String DELIMITER = "\u00A7";
  private static final String EMPTY = "";
  private static final String ONE = "1";
  private static final String SLASH = "/";

  /**
   * Parses user input and assigns to corresponding variables. Addresses invalid user input.
   * 
   * @param inputStr
   *          - String containing user's input
   * @return - Operand created by parsing the user's input
   */
  public static Operand parseInput(final String inputStr)
  {
    char[] splittedInput;
    Digit digit = null;
    String input = inputStr;
    String[] values;
    Unit unit = null;
    input = input.replaceAll("^ +| +$|( )+", EMPTY).trim();
    values = input.split(DELIMITER);

    if (values.length == 0 || values[0].equals(EMPTY))
    {
      throw new UnitException(UnitException.NO_INPUT);
    }
    else if (values[0].equals(".") || values[0].split("[.]").length > 2
        || !values[0].matches(VALID_NUM))
    {
      throw new UnitException(UnitException.INVALID_NUMBER);
    }

    splittedInput = input.toCharArray();

    for (int i = 0; i < splittedInput.length - 1; i++)
    {
      if ((Character.isDigit(splittedInput[i]) && Character.isLetter(splittedInput[i + 1]))
          || (Character.isLetter(splittedInput[i]) && Character.isDigit(splittedInput[i + 1])))
      {
        throw new UnitException(UnitException.CONTIGUOUS_NUM_LETTER);
      }
      if (splittedInput[i] == splittedInput[i + 1] && splittedInput[i] == '.')
      {
        throw new UnitException(UnitException.INVALID_NUMBER);
      }
    }

    if (values.length == 1)
    {
      return new Operand(new Digit(values[0]));
    }
    else if (values.length == 2)
    {
      String u = values[1];
      if (String.valueOf(u.charAt(0)).matches(CONJUNCTION_ID)
          || String.valueOf(u.charAt(u.length() - 1)).matches(CONJUNCTION_ID))
      {
        throw new UnitException(UnitException.UNIT_ENDS_CONTAIN_CONJUNCTION);
      }
      else if (u.equals(ONE) || u.contains("1-1") || u.contains("1/1") || u.contains("11"))
      {
        throw new UnitException(UnitException.INVALID_UNIT);
      }

      digit = new Digit(values[0]);

      if (isAllCharacters(values[1]) && hasConjunction(values[1])) // unit with conjunction.
      {
        if (values[1].contains(SLASH))
        {
          String left = values[1].substring(0, values[1].indexOf(SLASH));
          String right = values[1].substring(values[1].indexOf(SLASH) + 1, values[1].length());
          Unit leftUnit = recursiveConjunction(left.toCharArray(), null);
          Unit rightUnit = recursiveConjunction(right.toCharArray(), null);
          unit = new SlashConjunction(leftUnit, rightUnit);
        }
        else
        {
          unit = recursiveConjunction(values[1].toCharArray(), null);
        }
      }
      else if (isAllCharacters(values[1])) // if a valid unit without a conjunction.
      {
        unit = new SingleUnit(values[1]);
      }
      else
      {
        throw new UnitException(UnitException.INVALID_UNIT);
      }
    }
    else
    {
      throw new UnitException(UnitException.GENERAL_ERROR); // if values array has 3 or more fields.
    }
    unit = unit.simplify();

    return new Operand(digit, unit);
  }

  /**
   * Helper that checks if ALL the characters are either letters or special characters (everything
   * but a digit).
   * 
   * @param input
   *          String
   * @return true/false
   */
  private static boolean isAllCharacters(final String input)
  {
    char[] chars = input.toCharArray();
    for (char character : chars)
    {
      if (Character.isDigit(character) && character != '1')
      {
        return false;
      }
    }
    return true;
  }

  /**
   * Helper that checks if the input's unit has a conjunction type.
   * 
   * @param unit
   *          String
   * @return true/false
   */
  public static boolean hasConjunction(final String unit)
  {
    boolean result = false;
    char[] chars = unit.toCharArray();

    if (unit.contains(DASH) || unit.contains(SLASH))
    {
      for (int i = 0; i < chars.length - 1; i++)
      {
        if (String.valueOf(chars[i]).matches(CONJUNCTION_ID)
            && String.valueOf(chars[i + 1]).matches(CONJUNCTION_ID))
        {
          throw new UnitException(UnitException.CONTIGUOUS_CONJUNCTION);
        }
      }
      result = true;
    }
    return result;
  }

  /**
   * Helper that creates a unit with a conjunction.
   * 
   * @param unit
   *          Conjunction
   * @return Conjunction
   */
  private static Unit createConjunction(final String unit)
  {
    int i = 0;
    String string;
    String string2;
    Unit conjunction = null;
    String[] values = unit.split(VALID_UNIT_CHARS);
    List<String> list = new ArrayList<String>();

    for (int j = 0; j < values.length; j++)
    {
      if (values[j].equals(DASH) || values[j].equals(SLASH))
      {
        list.add(values[j]);
      }
    }

    if (list.size() == 0)
    {
      conjunction = new SingleUnit(unit);
    }
    else if (list.get(i).equals(DASH))
    {
      string = unit.substring(0, unit.indexOf(DASH));
      string2 = unit.substring(unit.indexOf(DASH) + 1, unit.length());
      if (string.equals(string2))
      {
        conjunction = new SingleUnit(string);
        conjunction.setPower(2);
      }
      else
      {
        conjunction = new DashConjunction(new SingleUnit(string), new SingleUnit(string2));
      }
    }
    else
    {
      string = unit.substring(0, unit.indexOf(SLASH));
      string2 = unit.substring(unit.indexOf(SLASH) + 1, unit.length());

      if (string.equals(string2))
      {
        conjunction = new SingleUnit(EMPTY);
      }
      else
      {
        conjunction = new SlashConjunction(new SingleUnit(string), new SingleUnit(string2));
      }
    }

    return conjunction;
  }

  /**
   * Creates a new composite conjunction by appending the string's corresponding unit to the
   * existing conjunction.
   * 
   * @param unit
   *          - String representation of the unit to append
   * @param conj
   *          - Existing conjunction to append the string to
   * @return - The newly created conjunction
   */
  private static Unit createConjunction(final String unit, final Unit conj)
  {
    Unit newUnit = null;
    Unit right = null;
    String[] parts = unit.split(CONJUNCTION_ID);
    String newPart = parts[parts.length - 1];
    if (unit.contains(DASH))
    {
      if (conj.getName().equals(newPart))
      {
        newUnit = conj;
        newUnit.setPower(conj.getPower() + 1);
      }
      else if (conj.isConjunction() && toU(conj.getRight()).getName().equals(newPart))
      {
        right = toU(conj.getRight());
        if (conj.getType().equals(SLASH))
        {
          newUnit = toU(conj.getLeft());
        }
        else
        {
          newUnit = conj;
          right.setPower(right.getPower() + 1);
        }
      }
      else if (conj.isConjunction() && toU(conj.getLeft()).getName().equals(ONE))
      {
        newUnit = new SlashConjunction(new SingleUnit(newPart), toU(conj.getRight()));
      }
      else
      {
        newUnit = new DashConjunction(conj, new SingleUnit(newPart)).simplify();
      }
    }
    else
    {
      if (conj.getType().equals(SLASH) && toU(conj.getRight()).getName().equals(newPart))
      {
        newUnit = toU(conj.getRight());
        newUnit.setPower(newUnit.getPower() + 1);
        newUnit = conj;
      }
      else
      {
        if (conj.getName().equals(EMPTY))
        {
          newUnit = new SlashConjunction(new SingleUnit(ONE), new SingleUnit(newPart));
        }
        else
        {
          newUnit = new SlashConjunction(conj, new SingleUnit(parts[parts.length - 1]));
        }
      }
    }
    return newUnit;
  }

  /**
   * Recursively creates arbitrarily long conjunctions.
   * 
   * @param inUnit
   *          - Each character that makes up the arbitrarily large unit
   * @param inConj
   *          - Unit to transform into a conjunction
   * @return - The newly created conjunction
   */
  public static Unit recursiveConjunction(final char[] inUnit, final Unit inConj)
  {
    char[] unit = inUnit;
    int conjCount = 0;
    int index = 0;
    String curr = EMPTY;
    Unit conj = inConj;
    if (unit.length > 0)
    {
      for (index = 0; index < unit.length; index++)
      {
        if (unit[index] == '-' || unit[index] == '/')
        {
          conjCount++;
          if (conjCount == 2)
          {
            break;
          }
        }
        curr += unit[index];
      }
      unit = removeToIndex(unit, index);
      if (conj == null)
      {
        conj = createConjunction(curr);
      }
      else
      {
        conj = createConjunction(curr, conj);
      }
      conj = recursiveConjunction(unit, conj);
    }
    return conj;
  }

  /**
   * Removes the indexes of the char array until a certain index.
   * 
   * @param array
   *          - Array to removed indexes from
   * @param inIndex
   *          - Index to remove indexes until
   * @return - Char array with removed indexes
   */
  private static char[] removeToIndex(final char[] array, final int inIndex)
  {
    char[] newArray = new char[array.length - inIndex];
    int index = inIndex;
    int other = 0;
    for (int i = 0; i < array.length; i++)
    {
      if (i >= index)
      {
        newArray[other] = array[index];
        index++;
        other++;
      }
    }
    return newArray;
  }

  /**
   * Private helper method to increase code elegance in createConjunction.
   * 
   * @param obj
   *          - Object to cast to a unit
   * @return - Casted unit
   */
  private static Unit toU(final Object obj)
  {
    return (Unit) obj;
  }
}
