package utilities;

/**
 * Enum storing the unicode superscript representation of each number.
 * 
 * @author Nick Spokes
 * @version 1
 */
public enum Power
{
  ONE("\u00B9", 1), TWO("\u00B2", 2), THREE("\u00B3", 3), FOUR("\u2074", 4), FIVE("\u2075", 5), SIX(
      "\u2076", 6), SEVEN("\u2077", 7), EIGHT("\u2078", 8), NINE("\u2079", 9), ZERO("\u2070", 0);

  private int value;
  private String superscript;

  /**
   * Constructs each Power object with the given unicode superscript representation.
   * 
   * @param str
   *          - Unicode form of superscript representation of the number
   * @param value
   *          - Integer corresponding to the superscript text
   */
  private Power(final String str, final int value)
  {
    this.superscript = str;
    this.value = value;
  }

  /**
   * Creates a string representation of a given integer power.
   * 
   * @param num
   *          - Number that the power should retain the value of
   * @return - String representation of the input integer power
   */
  public static String createPower(final int num)
  {
    String pow = "";
    char[] val = String.valueOf(num).toCharArray();
    for (int i = 0; i < val.length; i++)
    {
      pow += getNum(Character.getNumericValue(val[i])).toString();
    }
    return pow;
  }

  /**
   * Determines whether a string contains a power.
   * 
   * @param str
   *          - String to examine
   * @return - Whether the string contains a power
   */
  public static boolean containsPower(final String str)
  {
    boolean hasPow = false;
    for (Character ch : str.toCharArray())
    {
      for (Power p : values())
      {
        if (String.valueOf(ch).equals(p.toString()))
        {
          hasPow = true;
        }
      }
    }
    return hasPow;
  }

  /**
   * Private helper method that gets the Power value of the input number.
   * 
   * @param num
   *          - Number to get the Power value of
   * @return - Power value of the input number
   */
  private static Power getNum(final int num)
  {
    Power val = null;
    for (Power curr : Power.values())
    {
      if (num == curr.getValue())
      {
        val = curr;
      }
    }
    return val;
  }

  /**
   * Get the integer corresponding to the superscript text.
   * 
   * @return - Integer corresponding to the superscript text
   */
  public int getValue()
  {
    return value;
  }

  /**
   * Converts a superscript power to its corresponding integer value.
   * 
   * @param power
   *          - Superscript value
   * @return - Integer representation of the superscript power
   */
  public static int powerToInt(final String power)
  {
    int pow = 0;
    for (Character ch : power.toCharArray())
    {
      for (Power p : values())
      {
        if (String.valueOf(ch).equals(p.toString()))
        {
          pow *= 10;
          pow += p.getValue();
        }
      }
    }
    return pow;
  }

  /**
   * Get the String representation of one of the Power.
   * 
   * @return - String representation of one of the Power
   */
  @Override
  public String toString()
  {
    return this.superscript;
  }
}
