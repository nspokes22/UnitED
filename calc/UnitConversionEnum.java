package calc;

import java.util.Arrays;
import java.util.List;
import data.CompositeExpression;
import data.Digit;
import data.Operand;
import data.SingleUnit;
import data.Unit;
// POSSIBLY USE A MAP  where unit is key and other object is value

/**
 * Unit Conversions class to convert the units.
 * 
 * @author Robbie Deonarain
 * @version 1
 */
public enum UnitConversionEnum
{

  IN("in", "length", 1), FT("ft", IN.getType(), 12), YD("yd", IN.getType(), 36), MI("mi",
      IN.getType(), 63360), CM("cm", IN.getType(), 1), M("m", IN.getType(), 100), KM("km",
          IN.getType(), 100000), MM("mm", IN.getType(), .10), SEC("sec", "time", 1), MIN("min",
              SEC.getType(), 60), HR("hr", SEC.getType(), 3600), DAY("day", SEC.getType(), 1),

  // New units
  C("c", "money", 1), $("$", C.getType(), 100), PT("pt", "volume", 1), QT("qt", PT.getType(),
      1), GAL("gal", PT.getType(), 1), CC("cc", PT.getType(), 1), L("l", PT.getType(), 1), OZ("oz",
          "weight", 0), LB("lb", OZ.getType(), 16), TON("ton", OZ.getType(), 32000), G("g",
              OZ.getType(), 0.035274), KG("kg", OZ.getType(),
                  35.274), MON("mon", SEC.getType(), 2629800), YR("yr", SEC.getType(), 31556952);

  @SuppressWarnings("unused")
  private static final String SLASH = "/";
  private String unit;
  private String type;
  // converts everything to lowest imperial or metric unit
  private double conversionFactor;

  /**
   * Constructor.
   * 
   * @param unit
   *          the unit of the number.
   * @param type
   *          the type of number it is
   * @param conversionFactor
   *          converting to a smaller number in the same unit system
   */
  private UnitConversionEnum(final String unit, final String type, final double conversionFactor)
  {
    this.conversionFactor = conversionFactor;
    this.unit = unit;
    this.type = type;
  }

  /**
   * Checks if the the unit we are looking at is in our enum set.
   * 
   * @param uni
   *          the unit we are checking.
   * @return if it is in the enum set.
   */
  public static boolean isEnum(final Unit uni)
  {
    List<UnitConversionEnum> r = Arrays.asList(UnitConversionEnum.values());
    for (int i = 0; i < r.size(); i++)
    {
      if (uni.toString().equalsIgnoreCase(r.get(i).getUnit()))
      {
        return true;
      }
    }
    return false;
  }

  /**
   * Gets the enum representation of the given unit.
   * 
   * @param u
   *          - String to get the enum representation of
   * @return - Enum representation of the string
   */
  public static UnitConversionEnum getEnum(final String u)
  {
    UnitConversionEnum item = null;
    for (UnitConversionEnum curr : values())
    {
      if (u.equals(curr.getUnit()))
      {
        item = curr;
      }
    }
    return item;
  }

  /**
   * Getter method for the unit.
   * 
   * @return - String representation of the unit
   */
  public String getUnit()
  {
    return this.unit;
  }

  /**
   * Getter method for the type.
   * 
   * @return - String representation of the type of unit (ie. length, time, weight, etc)
   */
  public String getType()
  {
    return this.type;
  }

  /**
   * getter method for the conversion factor.
   * 
   * @return - Conversion factor for the given unit
   */
  public double getConversionFactor()
  {
    return this.conversionFactor;
  }

  /**
   * Helper method that takes in an expression and extracts the types and Digits to convert between
   * units if they are able to be converted. Otherwise calculate the units that are given.
   * 
   * @param expression
   *          the expression we are currently evaluation
   * @return the calculated total of the converted expression
   */
  public static Operand checkUnits(final CompositeExpression expression)
  {
    CalcStrategy strat = expression.getStrategy();
    String leftUnit = expression.getLeft().getUnit().getName().toLowerCase();
    String rightUnit = expression.getRight().getUnit().getName().toLowerCase();
    Digit leftNum = expression.getLeft().getValue();
    Digit rightNum = expression.getRight().getValue();
    Operand left = new Operand(leftNum, new SingleUnit(leftUnit));
    Operand right = new Operand(rightNum, new SingleUnit(rightUnit));
    Operand result = new Operand(expression.getValue(), expression.getUnit().simplify());
    // in * ft
    if (leftUnit.equals(IN.getUnit()) && rightUnit.equals(FT.getUnit()))
    {
      // left = new Operand(leftNum, new SingleUnit(leftUnit));
      right = new Operand(rightNum.multiply(FT.conversionFactor), new SingleUnit(leftUnit));
      result = strat.calculate(left, right);
    }
    // ft * in
    else if (leftUnit.equals(FT.getUnit()) && rightUnit.equals(IN.getUnit()))
    {
      left = new Operand(leftNum, new SingleUnit(leftUnit));
      right = new Operand(rightNum.divide(FT.conversionFactor), new SingleUnit(leftUnit));
      result = strat.calculate(left, right);
    }
    // in * mi
    else if (leftUnit.equals(IN.getUnit()) && rightUnit.equals(MI.getUnit()))
    {
      left = new Operand(leftNum, new SingleUnit(leftUnit));
      right = new Operand(rightNum.multiply(MI.conversionFactor), new SingleUnit(leftUnit));
      result = strat.calculate(left, right);

    }
    // mi * in
    else if (leftUnit.equals(MI.getUnit()) && rightUnit.equals(IN.getUnit()))
    {
      left = new Operand(leftNum, new SingleUnit(leftUnit));
      right = new Operand(rightNum.divide(MI.conversionFactor), new SingleUnit(leftUnit));
      result = strat.calculate(left, right);
    }
    // in * yds
    else if (leftUnit.equals(IN.getUnit()) && rightUnit.equals(YD.getUnit()))
    {
      left = new Operand(leftNum, new SingleUnit(leftUnit));
      right = new Operand(rightNum.multiply(YD.conversionFactor), new SingleUnit(leftUnit));
      result = strat.calculate(left, right);
    }
    // yds * in
    else if (leftUnit.equals(YD.getUnit()) && rightUnit.equals(IN.getUnit()))
    {
      left = new Operand(leftNum, new SingleUnit(leftUnit));
      right = new Operand(rightNum.divide(YD.conversionFactor), new SingleUnit(leftUnit));
      result = strat.calculate(left, right);
    }
    // ft * yds
    else if (leftUnit.equals(FT.getUnit()) && rightUnit.equals(YD.getUnit()))
    {
      left = new Operand(leftNum, new SingleUnit(leftUnit));
      right = new Operand(rightNum.multiply(YD.conversionFactor).divide(FT.conversionFactor),
          new SingleUnit(leftUnit));
      result = strat.calculate(left, right);
    }
    // yds * ft
    else if (leftUnit.equals(YD.getUnit()) && rightUnit.equals(FT.getUnit()))
    {
      left = new Operand(leftNum, new SingleUnit(leftUnit));
      right = new Operand(rightNum.multiply(FT.conversionFactor).divide(YD.conversionFactor),
          new SingleUnit(leftUnit));
      result = strat.calculate(left, right);
    }
    // ft * mi
    else if (leftUnit.equals(FT.getUnit()) && rightUnit.equals(MI.getUnit()))
    {
      left = new Operand(leftNum, new SingleUnit(leftUnit));
      right = new Operand(rightNum.multiply(MI.conversionFactor).divide(FT.conversionFactor),
          new SingleUnit(leftUnit));
      result = strat.calculate(left, right);
    }
    // mi * ft
    else if (leftUnit.equals(MI.getUnit()) && rightUnit.equals(FT.getUnit()))
    {
      left = new Operand(leftNum, new SingleUnit(leftUnit));
      right = new Operand(rightNum.multiply(FT.conversionFactor).divide(MI.conversionFactor),
          new SingleUnit(leftUnit));
      result = strat.calculate(left, right);
    }
    // mi * yds
    else if (leftUnit.equals(MI.getUnit()) && rightUnit.equals(YD.getUnit()))
    {
      left = new Operand(leftNum, new SingleUnit(leftUnit));
      right = new Operand(rightNum.multiply(YD.conversionFactor).divide(MI.conversionFactor),
          new SingleUnit(leftUnit));
      result = strat.calculate(left, right);
    }
    // yds * mi
    else if (leftUnit.equals(YD.getUnit()) && rightUnit.equals(MI.getUnit()))
    {
      left = new Operand(leftNum, new SingleUnit(leftUnit));
      right = new Operand(rightNum.multiply(MI.conversionFactor).divide(YD.conversionFactor),
          new SingleUnit(leftUnit));
      result = strat.calculate(left, right);
    }

    // cm * m
    else if (leftUnit.equals(CM.getUnit()) && rightUnit.equals(M.getUnit()))
    {
      left = new Operand(leftNum, new SingleUnit(leftUnit));
      right = new Operand(rightNum.multiply(M.conversionFactor).divide(CM.conversionFactor),
          new SingleUnit(leftUnit));
      result = strat.calculate(left, right);
    }
    // cm * mm
    else if (leftUnit.equals(CM.getUnit()) && rightUnit.equals(MM.getUnit()))
    {
      left = new Operand(leftNum, new SingleUnit(leftUnit));
      right = new Operand(rightNum.multiply(MM.conversionFactor).multiply(CM.conversionFactor),
          new SingleUnit(leftUnit));
      result = strat.calculate(left, right);
    }
    // cm * km
    else if (leftUnit.equals(CM.getUnit()) && rightUnit.equals(KM.getUnit()))
    {
      left = new Operand(leftNum, new SingleUnit(leftUnit));
      right = new Operand(rightNum.multiply(KM.conversionFactor).divide(CM.conversionFactor),
          new SingleUnit(leftUnit));
      result = strat.calculate(left, right);
    }
    // mm * cm
    else if (leftUnit.equals(MM.getUnit()) && rightUnit.equals(CM.getUnit()))
    {
      left = new Operand(leftNum, new SingleUnit(leftUnit));
      right = new Operand(rightNum.multiply(CM.conversionFactor).divide(MM.conversionFactor),
          new SingleUnit(leftUnit));
      result = strat.calculate(left, right);
    }
    // mm * m
    else if (leftUnit.equals(MM.getUnit()) && rightUnit.equals(M.getUnit()))
    {
      left = new Operand(leftNum, new SingleUnit(leftUnit));
      right = new Operand(rightNum.multiply(M.conversionFactor).divide(MM.conversionFactor),
          new SingleUnit(leftUnit));
      result = strat.calculate(left, right);
    }
    // mm * km
    else if (leftUnit.equals(MM.getUnit()) && rightUnit.equals(KM.getUnit()))
    {
      left = new Operand(leftNum, new SingleUnit(leftUnit));
      right = new Operand(rightNum.multiply(KM.conversionFactor).divide(MM.conversionFactor),
          new SingleUnit(leftUnit));
      result = strat.calculate(left, right);
    }
    // m * mm
    else if (leftUnit.equals(M.getUnit()) && rightUnit.equals(MM.getUnit()))
    {
      left = new Operand(leftNum, new SingleUnit(leftUnit));
      right = new Operand(rightNum.multiply(MM.conversionFactor).divide(M.conversionFactor),
          new SingleUnit(leftUnit));
      result = strat.calculate(left, right);
    }
    // m * cm
    else if (leftUnit.equals(M.getUnit()) && rightUnit.equals(CM.getUnit()))
    {
      left = new Operand(leftNum, new SingleUnit(leftUnit));
      right = new Operand(rightNum.multiply(CM.conversionFactor).divide(M.conversionFactor),
          new SingleUnit(leftUnit));
      result = strat.calculate(left, right);
    }
    // m * km
    else if (leftUnit.equals(M.getUnit()) && rightUnit.equals(KM.getUnit()))
    {
      left = new Operand(leftNum, new SingleUnit(leftUnit));
      right = new Operand(rightNum.multiply(KM.conversionFactor).divide(M.conversionFactor),
          new SingleUnit(leftUnit));
      result = strat.calculate(left, right);
    }
    // km * mm
    else if (leftUnit.equals(KM.getUnit()) && rightUnit.equals(MM.getUnit()))
    {
      left = new Operand(leftNum, new SingleUnit(leftUnit));
      right = new Operand(rightNum.multiply(MM.conversionFactor).divide(KM.conversionFactor),
          new SingleUnit(leftUnit));
      result = strat.calculate(left, right);
    }
    // km * cm
    else if (leftUnit.equals(KM.getUnit()) && rightUnit.equals(CM.getUnit()))
    {
      left = new Operand(leftNum, new SingleUnit(leftUnit));
      right = new Operand(rightNum.multiply(CM.conversionFactor).divide(KM.conversionFactor),
          new SingleUnit(leftUnit));
      result = strat.calculate(left, right);
    }
    // km * m
    else if (leftUnit.equals(KM.getUnit()) && rightUnit.equals(M.getUnit()))
    {
      left = new Operand(leftNum, new SingleUnit(leftUnit));
      right = new Operand(rightNum.multiply(M.conversionFactor).divide(KM.conversionFactor),
          new SingleUnit(leftUnit));
      result = strat.calculate(left, right);
    }

    // in * cm
    else if (leftUnit.equals(IN.getUnit()) && rightUnit.equals(CM.getUnit()))
    {
      left = new Operand(leftNum, new SingleUnit(leftUnit));
      right = new Operand(rightNum.multiply(IN.conversionFactor).divide(2.54),
          new SingleUnit(leftUnit));
      result = strat.calculate(left, right);
    }
    // in * mm
    else if (leftUnit.equals(IN.getUnit()) && rightUnit.equals(MM.getUnit()))
    {
      left = new Operand(leftNum, new SingleUnit(leftUnit));
      right = new Operand(rightNum.multiply(MM.conversionFactor).divide(2.54),
          new SingleUnit(leftUnit));
      result = strat.calculate(left, right);
    }
    // in * m
    else if (leftUnit.equals(IN.getUnit()) && rightUnit.equals(M.getUnit()))
    {
      left = new Operand(leftNum, new SingleUnit(leftUnit));
      right = new Operand(rightNum.multiply(M.conversionFactor).divide(2.54),
          new SingleUnit(leftUnit));
      result = strat.calculate(left, right);
    }
    // in * km
    else if (leftUnit.equals(IN.getUnit()) && rightUnit.equals(KM.getUnit()))
    {
      left = new Operand(leftNum, new SingleUnit(leftUnit));
      right = new Operand(rightNum.multiply(KM.conversionFactor).divide(2.54),
          new SingleUnit(leftUnit));
      result = strat.calculate(left, right);
    }
    // ft * cm
    else if (leftUnit.equals(FT.getUnit()) && rightUnit.equals(CM.getUnit()))
    {
      left = new Operand(leftNum, new SingleUnit(leftUnit));
      right = new Operand((rightNum.divide(IN.conversionFactor)).divide(FT.conversionFactor)
          .divide(CM.conversionFactor).divide(2.54), new SingleUnit(leftUnit));
      result = strat.calculate(left, right);
    }
    // ft * mm
    else if (leftUnit.equals(FT.getUnit()) && rightUnit.equals(MM.getUnit()))
    {
      left = new Operand(leftNum, new SingleUnit(leftUnit));
      right = new Operand(
          (rightNum.multiply(MM.conversionFactor)).divide(FT.conversionFactor).divide(2.54),
          new SingleUnit(leftUnit));
      result = strat.calculate(left, right);
    }
    // ft * m
    else if (leftUnit.equals(FT.getUnit()) && rightUnit.equals(M.getUnit()))
    {
      left = new Operand(leftNum, new SingleUnit(leftUnit));
      right = new Operand(
          (rightNum.multiply(M.conversionFactor)).divide(FT.conversionFactor).divide(2.54),
          new SingleUnit(leftUnit));
      result = strat.calculate(left, right);
    }
    // ft * km
    else if (leftUnit.equals(FT.getUnit()) && rightUnit.equals(KM.getUnit()))
    {
      left = new Operand(leftNum, new SingleUnit(leftUnit));
      right = new Operand(
          (rightNum.multiply(KM.conversionFactor)).divide(FT.conversionFactor).divide(2.54),
          new SingleUnit(leftUnit));
      result = strat.calculate(left, right);
    }

    // yds * cm
    else if (leftUnit.equals(YD.getUnit()) && rightUnit.equals(CM.getUnit()))
    {
      left = new Operand(leftNum, new SingleUnit(leftUnit));
      right = new Operand((rightNum.divide(IN.conversionFactor)).divide(YD.conversionFactor)
          .divide(CM.conversionFactor).divide(2.54), new SingleUnit(leftUnit));
      result = strat.calculate(left, right);
    }
    // yds * mm
    else if (leftUnit.equals(YD.getUnit()) && rightUnit.equals(MM.getUnit()))
    {
      left = new Operand(leftNum, new SingleUnit(leftUnit));
      right = new Operand(
          (rightNum.multiply(MM.conversionFactor)).divide(YD.conversionFactor).divide(2.54),
          new SingleUnit(leftUnit));
      result = strat.calculate(left, right);
    }
    // yds * m
    else if (leftUnit.equals(YD.getUnit()) && rightUnit.equals(M.getUnit()))
    {
      left = new Operand(leftNum, new SingleUnit(leftUnit));
      right = new Operand(
          (rightNum.multiply(M.conversionFactor)).divide(YD.conversionFactor).divide(2.54),
          new SingleUnit(leftUnit));
      result = strat.calculate(left, right);
    }
    // yds * km
    else if (leftUnit.equals(YD.getUnit()) && rightUnit.equals(KM.getUnit()))
    {
      left = new Operand(leftNum, new SingleUnit(leftUnit));
      right = new Operand(
          (rightNum.multiply(KM.conversionFactor)).divide(YD.conversionFactor).divide(2.54),
          new SingleUnit(leftUnit));
      result = strat.calculate(left, right);
    }

    // mi * cm
    else if (leftUnit.equals(MI.getUnit()) && rightUnit.equals(CM.getUnit()))
    {
      left = new Operand(leftNum, new SingleUnit(leftUnit));
      right = new Operand((rightNum.divide(IN.conversionFactor)).divide(MI.conversionFactor)
          .divide(CM.conversionFactor).divide(2.54), new SingleUnit(leftUnit));
      result = strat.calculate(left, right);
    }
    // mi * mm
    else if (leftUnit.equals(MI.getUnit()) && rightUnit.equals(MM.getUnit()))
    {
      left = new Operand(leftNum, new SingleUnit(leftUnit));
      right = new Operand(
          (rightNum.multiply(MM.conversionFactor)).divide(MI.conversionFactor).divide(2.54),
          new SingleUnit(leftUnit));
      result = strat.calculate(left, right);
    }
    // mi * m
    else if (leftUnit.equals(MI.getUnit()) && rightUnit.equals(M.getUnit()))
    {
      left = new Operand(leftNum, new SingleUnit(leftUnit));
      right = new Operand(
          (rightNum.multiply(M.conversionFactor)).divide(MI.conversionFactor).divide(2.54),
          new SingleUnit(leftUnit));
      result = strat.calculate(left, right);
    }
    // mi * km
    else if (leftUnit.equals(MI.getUnit()) && rightUnit.equals(KM.getUnit()))
    {
      left = new Operand(leftNum, new SingleUnit(leftUnit));
      right = new Operand(
          (rightNum.multiply(KM.conversionFactor)).divide(MI.conversionFactor).divide(2.54),
          new SingleUnit(leftUnit));
      result = strat.calculate(left, right);
    }

    // cm * in
    else if (leftUnit.equals(CM.getUnit()) && rightUnit.equals(IN.getUnit()))

    {
      left = new Operand(leftNum, new SingleUnit(leftUnit));
      right = new Operand(rightNum.multiply(IN.conversionFactor).multiply(2.54),
          new SingleUnit(leftUnit));
      result = strat.calculate(left, right);
    }
    // cm * ft
    else if (leftUnit.equals(CM.getUnit()) && rightUnit.equals(FT.getUnit()))
    {
      left = new Operand(leftNum, new SingleUnit(leftUnit));
      right = new Operand(rightNum.multiply(FT.conversionFactor).multiply(2.54),
          new SingleUnit(leftUnit));
      result = strat.calculate(left, right);
    }
    // cm * yds
    else if (leftUnit.equals(CM.getUnit()) && rightUnit.equals(YD.getUnit()))
    {
      left = new Operand(leftNum, new SingleUnit(leftUnit));
      right = new Operand(rightNum.multiply(YD.conversionFactor).multiply(2.54),
          new SingleUnit(leftUnit));
      result = strat.calculate(left, right);
    }
    // cm * mi
    else if (leftUnit.equals(CM.getUnit()) && rightUnit.equals(MI.getUnit()))
    {
      left = new Operand(leftNum, new SingleUnit(leftUnit));
      right = new Operand(rightNum.multiply(MI.conversionFactor).multiply(2.54),
          new SingleUnit(leftUnit));
      result = strat.calculate(left, right);
    }
    // mm * in
    else if (leftUnit.equals(MM.getUnit()) && rightUnit.equals(IN.getUnit()))
    {
      left = new Operand(leftNum, new SingleUnit(leftUnit));
      right = new Operand(rightNum.divide(MM.conversionFactor).multiply(2.54),
          new SingleUnit(leftUnit));
      result = strat.calculate(left, right);
    }
    // mm * ft
    else if (leftUnit.equals(MM.getUnit()) && rightUnit.equals(FT.getUnit()))
    {
      left = new Operand(leftNum, new SingleUnit(leftUnit));
      right = new Operand(
          rightNum.divide(MM.conversionFactor).multiply(FT.conversionFactor).multiply(2.54),
          new SingleUnit(leftUnit));
      result = strat.calculate(left, right);
    }
    // mm * yds
    else if (leftUnit.equals(MM.getUnit()) && rightUnit.equals(YD.getUnit()))
    {
      left = new Operand(leftNum, new SingleUnit(leftUnit));
      right = new Operand(
          rightNum.divide(MM.conversionFactor).multiply(YD.conversionFactor).multiply(2.54),
          new SingleUnit(leftUnit));
      result = strat.calculate(left, right);
    }
    // mm * mi
    else if (leftUnit.equals(MM.getUnit()) && rightUnit.equals(MI.getUnit()))
    {
      left = new Operand(leftNum, new SingleUnit(leftUnit));
      right = new Operand(
          rightNum.divide(MM.conversionFactor).multiply(MI.conversionFactor).multiply(2.54),
          new SingleUnit(leftUnit));
      result = strat.calculate(left, right);
    }
    // m * in
    else if (leftUnit.equals(M.getUnit()) && rightUnit.equals(IN.getUnit()))
    {
      left = new Operand(leftNum, new SingleUnit(leftUnit));
      right = new Operand(
          rightNum.divide(M.conversionFactor).multiply(IN.conversionFactor).multiply(2.54),
          new SingleUnit(leftUnit));
      result = strat.calculate(left, right);
    }
    // m * ft
    else if (leftUnit.equals(M.getUnit()) && rightUnit.equals(FT.getUnit()))
    {
      left = new Operand(leftNum, new SingleUnit(leftUnit));
      right = new Operand(
          rightNum.divide(M.conversionFactor).multiply(FT.conversionFactor).multiply(2.54),
          new SingleUnit(leftUnit));
      result = strat.calculate(left, right);
    }
    // m * yds
    else if (leftUnit.equals(M.getUnit()) && rightUnit.equals(YD.getUnit()))
    {
      left = new Operand(leftNum, new SingleUnit(leftUnit));
      right = new Operand(
          rightNum.divide(M.conversionFactor).multiply(YD.conversionFactor).multiply(2.54),
          new SingleUnit(leftUnit));
      result = strat.calculate(left, right);
    }
    // m * mi
    else if (leftUnit.equals(M.getUnit()) && rightUnit.equals(MI.getUnit()))
    {
      left = new Operand(leftNum, new SingleUnit(leftUnit));
      right = new Operand(
          rightNum.divide(M.conversionFactor).multiply(MI.conversionFactor).multiply(2.54),
          new SingleUnit(leftUnit));
      result = strat.calculate(left, right);
    }
    // km * in
    else if (leftUnit.equals(KM.getUnit()) && rightUnit.equals(IN.getUnit()))
    {
      left = new Operand(leftNum, new SingleUnit(leftUnit));
      right = new Operand(
          rightNum.divide(KM.conversionFactor).multiply(IN.conversionFactor).multiply(2.54),
          new SingleUnit(leftUnit));
      result = strat.calculate(left, right);
    }
    // km * ft
    else if (leftUnit.equals(KM.getUnit()) && rightUnit.equals(FT.getUnit()))
    {
      left = new Operand(leftNum, new SingleUnit(leftUnit));
      right = new Operand(
          rightNum.divide(KM.conversionFactor).multiply(FT.conversionFactor).multiply(2.54),
          new SingleUnit(leftUnit));
      result = strat.calculate(left, right);
    }
    // km * yds
    else if (leftUnit.equals(KM.getUnit()) && rightUnit.equals(YD.getUnit()))
    {
      left = new Operand(leftNum, new SingleUnit(leftUnit));
      right = new Operand(
          rightNum.divide(KM.conversionFactor).multiply(YD.conversionFactor).multiply(2.54),
          new SingleUnit(leftUnit));
      result = strat.calculate(left, right);
    }
    // km * mi
    else if (leftUnit.equals(KM.getUnit()) && rightUnit.equals(MI.getUnit()))
    {
      left = new Operand(leftNum, new SingleUnit(leftUnit));
      right = new Operand(
          rightNum.multiply(MI.conversionFactor).multiply(2.54).divide(KM.conversionFactor),
          new SingleUnit(leftUnit));
      result = strat.calculate(left, right);
    }

    // time conversions
    // sec * min
    else if (leftUnit.equals(SEC.getUnit()) && rightUnit.equals(MIN.getUnit()))
    {
      left = new Operand(leftNum, new SingleUnit(leftUnit));
      right = new Operand(rightNum.multiply(MIN.conversionFactor), new SingleUnit(leftUnit));
      result = strat.calculate(left, right);
    }
    // sec * hr
    else if (leftUnit.equals(SEC.getUnit()) && rightUnit.equals(HR.getUnit()))
    {
      left = new Operand(leftNum, new SingleUnit(leftUnit));
      right = new Operand(rightNum.multiply(HR.conversionFactor), new SingleUnit(leftUnit));
      result = strat.calculate(left, right);
    }
    // min * sec
    else if (leftUnit.equals(MIN.getUnit()) && rightUnit.equals(SEC.getUnit()))
    {
      left = new Operand(leftNum, new SingleUnit(leftUnit));
      right = new Operand(rightNum.divide(MIN.conversionFactor), new SingleUnit(leftUnit));
      result = strat.calculate(left, right);
    }
    // min * hr
    else if (leftUnit.equals(MIN.getUnit()) && rightUnit.equals(HR.getUnit()))
    {
      left = new Operand(leftNum, new SingleUnit(leftUnit));
      right = new Operand(rightNum.multiply(HR.conversionFactor).divide(MIN.conversionFactor),
          new SingleUnit(leftUnit));
      result = strat.calculate(left, right);
    }
    // hr * sec
    else if (leftUnit.equals(HR.getUnit()) && rightUnit.equals(SEC.getUnit()))
    {
      left = new Operand(leftNum, new SingleUnit(leftUnit));
      right = new Operand(rightNum.divide(HR.conversionFactor), new SingleUnit(leftUnit));
      result = strat.calculate(left, right);
    }
    // hr * min
    else if (leftUnit.equals(HR.getUnit()) && rightUnit.equals(MIN.getUnit()))
    {
      left = new Operand(leftNum, new SingleUnit(leftUnit));
      right = new Operand(rightNum.divide(MIN.conversionFactor), new SingleUnit(leftUnit));
      result = strat.calculate(left, right);
    }
    // days * hours
    else if (leftUnit.equals(DAY.getUnit()) && rightUnit.equals(HR.getUnit()))
    {
      right = new Operand(rightNum.divide(24), new SingleUnit(leftUnit));
      result = strat.calculate(left, right);
    }
    // hr * days
    else if (leftUnit.equals(HR.getUnit()) && rightUnit.equals(DAY.getUnit()))
    {
      right = new Operand(rightNum.multiply(24), new SingleUnit(leftUnit));
      result = strat.calculate(left, right);
    }
    // Oz * g
    else if (leftUnit.equals(OZ.getUnit()) && rightUnit.equals(G.getUnit()))
    {
      right = new Operand(rightNum.multiply(G.getConversionFactor()), new SingleUnit(leftUnit));
      result = strat.calculate(left, right);
    }
    // oz & lb
    else if (leftUnit.equals(OZ.getUnit()) && rightUnit.equals(LB.getUnit()))
    {
      right = new Operand(rightNum.multiply(LB.getConversionFactor()), new SingleUnit(leftUnit));
      result = strat.calculate(left, right);
    }
    // oz & ton
    else if (leftUnit.equals(OZ.getUnit()) && rightUnit.equals(TON.getUnit()))
    {
      right = new Operand(rightNum.multiply(TON.getConversionFactor()), new SingleUnit(leftUnit));
      result = strat.calculate(left, right);
    }
    // oz & kg
    else if (leftUnit.equals(OZ.getUnit()) && rightUnit.equals(KG.getUnit()))
    {
      right = new Operand(rightNum.multiply(KG.getConversionFactor()), new SingleUnit(leftUnit));
      result = strat.calculate(left, right);
    }
    // lb & oz
    else if (leftUnit.equals(LB.getUnit()) && rightUnit.equals(OZ.getUnit()))
    {
      right = new Operand(rightNum.divide(LB.getConversionFactor()), new SingleUnit(leftUnit));
      result = strat.calculate(left, right);
    }
    // lb & ton
    else if (leftUnit.equals(LB.getUnit()) && rightUnit.equals(TON.getUnit()))
    {
      right = new Operand(
          rightNum.multiply(TON.getConversionFactor()).divide(LB.getConversionFactor()),
          new SingleUnit(leftUnit));
      result = strat.calculate(left, right);
    }
    // lb & g
    else if (leftUnit.equals(LB.getUnit()) && rightUnit.equals(G.getUnit()))
    {
      right = new Operand(
          rightNum.multiply(G.getConversionFactor()).divide(LB.getConversionFactor()),
          new SingleUnit(leftUnit));
      result = strat.calculate(left, right);
    }
    // lb & kg
    else if (leftUnit.equals(LB.getUnit()) && rightUnit.equals(KG.getUnit()))
    {
      right = new Operand(
          rightNum.multiply(KG.getConversionFactor()).divide(LB.getConversionFactor()),
          new SingleUnit(leftUnit));
      result = strat.calculate(left, right);
    }
    // ton & oz
    else if (leftUnit.equals(TON.getUnit()) && rightUnit.equals(OZ.getUnit()))
    {
      right = new Operand(rightNum.divide(TON.getConversionFactor()), new SingleUnit(leftUnit));
      result = strat.calculate(left, right);
    }
    // ton & g
    else if (leftUnit.equals(TON.getUnit()) && rightUnit.equals(G.getUnit()))
    {
      right = new Operand(
          rightNum.multiply(G.getConversionFactor()).divide(TON.getConversionFactor()),
          new SingleUnit(leftUnit));
      result = strat.calculate(left, right);
    }
    // ton & lb
    else if (leftUnit.equals(TON.getUnit()) && rightUnit.equals(LB.getUnit()))
    {
      right = new Operand(
          rightNum.multiply(LB.getConversionFactor()).divide(TON.getConversionFactor()),
          new SingleUnit(leftUnit));
      result = strat.calculate(left, right);
    }
    // ton & kg
    else if (leftUnit.equals(TON.getUnit()) && rightUnit.equals(KG.getUnit()))
    {
      right = new Operand(
          rightNum.multiply(KG.getConversionFactor()).divide(TON.getConversionFactor()),
          new SingleUnit(leftUnit));
      result = strat.calculate(left, right);
    }
    // g & oz
    else if (leftUnit.equals(G.getUnit()) && rightUnit.equals(OZ.getUnit()))
    {
      right = new Operand(rightNum.divide(G.getConversionFactor()), new SingleUnit(leftUnit));
      result = strat.calculate(left, right);
    }
    // g & ton
    else if (leftUnit.equals(G.getUnit()) && rightUnit.equals(TON.getUnit()))
    {
      right = new Operand(
          rightNum.multiply(TON.getConversionFactor()).divide(G.getConversionFactor()),
          new SingleUnit(leftUnit));
      result = strat.calculate(left, right);
    }
    // g & lb
    else if (leftUnit.equals(G.getUnit()) && rightUnit.equals(LB.getUnit()))
    {
      right = new Operand(
          rightNum.multiply(LB.getConversionFactor()).divide(G.getConversionFactor()),
          new SingleUnit(leftUnit));
      result = strat.calculate(left, right);
    }
    // G & kg
    else if (leftUnit.equals(G.getUnit()) && rightUnit.equals(KG.getUnit()))
    {
      right = new Operand(
          rightNum.multiply(KG.getConversionFactor()).divide(G.getConversionFactor()),
          new SingleUnit(leftUnit));
      result = strat.calculate(left, right);
    }
    // kg & oz
    else if (leftUnit.equals(KG.getUnit()) && rightUnit.equals(OZ.getUnit()))
    {
      right = new Operand(rightNum.divide(KG.getConversionFactor()), new SingleUnit(leftUnit));
      result = strat.calculate(left, right);
    }
    // kg & ton
    else if (leftUnit.equals(KG.getUnit()) && rightUnit.equals(TON.getUnit()))
    {
      right = new Operand(
          rightNum.multiply(TON.getConversionFactor()).divide(KG.getConversionFactor()),
          new SingleUnit(leftUnit));
      result = strat.calculate(left, right);
    }
    // kg & lb
    else if (leftUnit.equals(KG.getUnit()) && rightUnit.equals(LB.getUnit()))
    {
      right = new Operand(
          rightNum.multiply(LB.getConversionFactor()).divide(KG.getConversionFactor()),
          new SingleUnit(leftUnit));
      result = strat.calculate(left, right);
    }
    // kG & g
    else if (leftUnit.equals(KG.getUnit()) && rightUnit.equals(G.getUnit()))
    {
      right = new Operand(
          rightNum.multiply(G.getConversionFactor()).divide(KG.getConversionFactor()),
          new SingleUnit(leftUnit));
      result = strat.calculate(left, right);
    }
    // $ * C
    else if (leftUnit.equals($.getUnit()) && rightUnit.equals(C.getUnit()))
    {
      right = new Operand(rightNum.divide($.getConversionFactor()), new SingleUnit(leftUnit));
      result = strat.calculate(left, right);
    }
    // C * $
    else if (leftUnit.equals(C.getUnit()) && rightUnit.equals($.getUnit()))
    {
      right = new Operand(rightNum.multiply($.getConversionFactor()), new SingleUnit(leftUnit));
      result = strat.calculate(left, right);
    }
    // pint * quart
    else if (leftUnit.equals(PT.getUnit()) && rightUnit.equals(QT.getUnit()))
    {
      right = new Operand(rightNum.multiply(2), new SingleUnit(leftUnit));
      result = strat.calculate(left, right);
    }
    // pint * gal
    else if (leftUnit.equals(PT.getUnit()) && rightUnit.equals(GAL.getUnit()))
    {
      right = new Operand(rightNum.multiply(9.6076), new SingleUnit(leftUnit));
      result = strat.calculate(left, right);
    }
    // pint * cc
    else if (leftUnit.equals(PT.getUnit()) && rightUnit.equals(CC.getUnit()))
    {
      right = new Operand(rightNum.multiply(0.00175975), new SingleUnit(leftUnit));
      result = strat.calculate(left, right);
    }
    // pint * l
    else if (leftUnit.equals(PT.getUnit()) && rightUnit.equals(L.getUnit()))
    {
      right = new Operand(rightNum.multiply(2.11338), new SingleUnit(leftUnit));
      result = strat.calculate(left, right);
    }
    // qt * pt
    else if (leftUnit.equals(QT.getUnit()) && rightUnit.equals(PT.getUnit()))
    {
      right = new Operand(rightNum.divide(2), new SingleUnit(leftUnit));
      result = strat.calculate(left, right);
    }
    // qt * gal
    else if (leftUnit.equals(QT.getUnit()) && rightUnit.equals(GAL.getUnit()))
    {
      right = new Operand(rightNum.multiply(4), new SingleUnit(leftUnit));
      result = strat.calculate(left, right);
    }
    // qt * cc
    else if (leftUnit.equals(QT.getUnit()) && rightUnit.equals(CC.getUnit()))
    {
      right = new Operand(rightNum.multiply(0.00105669), new SingleUnit(leftUnit));
      result = strat.calculate(left, right);
    }
    // qt * l
    else if (leftUnit.equals(QT.getUnit()) && rightUnit.equals(L.getUnit()))
    {
      right = new Operand(rightNum.multiply(1.05669), new SingleUnit(leftUnit));
      result = strat.calculate(left, right);
    }
    // gal * pt
    else if (leftUnit.equals(GAL.getUnit()) && rightUnit.equals(PT.getUnit()))
    {
      right = new Operand(rightNum.divide(8), new SingleUnit(leftUnit));
      result = strat.calculate(left, right);
    }
    // GAL * qt
    else if (leftUnit.equals(GAL.getUnit()) && rightUnit.equals(QT.getUnit()))
    {
      right = new Operand(rightNum.divide(4), new SingleUnit(leftUnit));
      result = strat.calculate(left, right);
    }
    // GAL * cc
    else if (leftUnit.equals(GAL.getUnit()) && rightUnit.equals(CC.getUnit()))
    {
      right = new Operand(rightNum.divide(3785.41), new SingleUnit(leftUnit));
      result = strat.calculate(left, right);
    }
    // GAL * l
    else if (leftUnit.equals(GAL.getUnit()) && rightUnit.equals(L.getUnit()))
    {
      right = new Operand(rightNum.divide(3.78541), new SingleUnit(leftUnit));
      result = strat.calculate(left, right);
    }
    // cc * pt
    else if (leftUnit.equals(CC.getUnit()) && rightUnit.equals(PT.getUnit()))
    {
      right = new Operand(rightNum.multiply(473.176), new SingleUnit(leftUnit));
      result = strat.calculate(left, right);
    }
    // CC * qt
    else if (leftUnit.equals(CC.getUnit()) && rightUnit.equals(QT.getUnit()))
    {
      right = new Operand(rightNum.multiply(946.353), new SingleUnit(leftUnit));
      result = strat.calculate(left, right);
    }
    // CC * GAL
    else if (leftUnit.equals(CC.getUnit()) && rightUnit.equals(GAL.getUnit()))
    {
      right = new Operand(rightNum.multiply(3785.41), new SingleUnit(leftUnit));
      result = strat.calculate(left, right);
    }
    // cc * l
    else if (leftUnit.equals(CC.getUnit()) && rightUnit.equals(L.getUnit()))
    {
      right = new Operand(rightNum.multiply(1000), new SingleUnit(leftUnit));
      result = strat.calculate(left, right);
    }
    // l * pt
    else if (leftUnit.equals(L.getUnit()) && rightUnit.equals(PT.getUnit()))
    {
      right = new Operand(rightNum.multiply(0.473176473), new SingleUnit(leftUnit));
      result = strat.calculate(left, right);
    }
    // L * qt
    else if (leftUnit.equals(L.getUnit()) && rightUnit.equals(QT.getUnit()))
    {
      right = new Operand(rightNum.divide(1.05669), new SingleUnit(leftUnit));
      result = strat.calculate(left, right);
    }
    // L * GAL
    else if (leftUnit.equals(L.getUnit()) && rightUnit.equals(GAL.getUnit()))
    {
      right = new Operand(rightNum.multiply(3.78541), new SingleUnit(leftUnit));
      result = strat.calculate(left, right);
    }
    // L * cc
    else if (leftUnit.equals(L.getUnit()) && rightUnit.equals(CC.getUnit()))
    {
      right = new Operand(rightNum.multiply(0.001), new SingleUnit(leftUnit));
      result = strat.calculate(left, right);
    }
    // mon * yr
    else if (leftUnit.equals(MON.getUnit()) && rightUnit.equals(YR.getUnit()))
    {
      right = new Operand(rightNum.multiply(12), new SingleUnit(leftUnit));
      result = strat.calculate(left, right);

    }
    // yr * month
    else if (leftUnit.equals(YR.getUnit()) && rightUnit.equals(MON.getUnit()))
    {
      right = new Operand(rightNum.divide(12), new SingleUnit(leftUnit));
      result = strat.calculate(left, right);

    }
    // yr * min
    else if (leftUnit.equals(YR.getUnit()) && rightUnit.equals(MIN.getUnit()))
    {
      right = new Operand(rightNum.divide(525600), new SingleUnit(leftUnit));
      result = strat.calculate(left, right);

    }
    // yr * sec
    else if (leftUnit.equals(YR.getUnit()) && rightUnit.equals(SEC.getUnit()))
    {
      right = new Operand(rightNum.divide(31536000), new SingleUnit(leftUnit));
      result = strat.calculate(left, right);

    }
    // yr * day
    else if (leftUnit.equals(YR.getUnit()) && rightUnit.equals(DAY.getUnit()))
    {
      right = new Operand(rightNum.divide(365), new SingleUnit(leftUnit));
      result = strat.calculate(left, right);

    }
    // mon * day
    else if (leftUnit.equals(MON.getUnit()) && rightUnit.equals(DAY.getUnit()))
    {
      right = new Operand(rightNum.divide(30), new SingleUnit(leftUnit));
      result = strat.calculate(left, right);

    }
    // mon * min
    else if (leftUnit.equals(MON.getUnit()) && rightUnit.equals(MIN.getUnit()))
    {
      right = new Operand(rightNum.divide(43200), new SingleUnit(leftUnit));
      result = strat.calculate(left, right);

    }
    // mon * sec
    else if (leftUnit.equals(MON.getUnit()) && rightUnit.equals(SEC.getUnit()))
    {
      right = new Operand(rightNum.divide(2592000), new SingleUnit(leftUnit));
      result = strat.calculate(left, right);

    }
    // day * sec
    else if (leftUnit.equals(DAY.getUnit()) && rightUnit.equals(SEC.getUnit()))
    {
      right = new Operand(rightNum.divide(86400), new SingleUnit(leftUnit));
      result = strat.calculate(left, right);
    }
    // day * min
    else if (leftUnit.equals(DAY.getUnit()) && rightUnit.equals(MIN.getUnit()))
    {
      right = new Operand(rightNum.divide(1440), new SingleUnit(leftUnit));
      result = strat.calculate(left, right);
    }
    // sec to year
    else if (leftUnit.equals(SEC.getUnit()) && rightUnit.equals(YR.getUnit()))
    {
      right = new Operand(rightNum.multiply(31536000), new SingleUnit(leftUnit));
      result = strat.calculate(left, right);
    }
    // sec to month
    else if (leftUnit.equals(SEC.getUnit()) && rightUnit.equals(MON.getUnit()))
    {
      right = new Operand(rightNum.multiply(2592000), new SingleUnit(leftUnit));
      result = strat.calculate(left, right);
    }
    // sec to day
    else if (leftUnit.equals(SEC.getUnit()) && rightUnit.equals(DAY.getUnit()))
    {
      right = new Operand(rightNum.multiply(86400), new SingleUnit(leftUnit));
      result = strat.calculate(left, right);
    }
    // min to day
    else if (leftUnit.equals(MIN.getUnit()) && rightUnit.equals(DAY.getUnit()))
    {
      right = new Operand(rightNum.multiply(1440), new SingleUnit(leftUnit));
      result = strat.calculate(left, right);
    }
    // min to month
    else if (leftUnit.equals(MIN.getUnit()) && rightUnit.equals(MON.getUnit()))
    {
      right = new Operand(rightNum.multiply(43200), new SingleUnit(leftUnit));
      result = strat.calculate(left, right);
    }
    // min to year
    else if (leftUnit.equals(MIN.getUnit()) && rightUnit.equals(YR.getUnit()))
    {
      right = new Operand(rightNum.multiply(525600), new SingleUnit(leftUnit));
      result = strat.calculate(left, right);
    }
    return result;
  }

  /**
   * Determines whether the result unit dropdown should be shown for the given input.
   * 
   * @param input
   *          - Input to examine
   * @return - Whether to show the result unit dropdown
   */
  public static String shouldActivateDrop(final CompositeExpression input)
  {
    String activate = null;
    Unit left = input.getLeft().getUnit();
    Unit right = input.getRight().getUnit();
    if (isEnum(left) && isEnum(right))
    {
      if (getEnum(left.getName()).getType().equals(getEnum(right.getName()).getType()))
      {
        activate = getEnum(left.toString()).getType();
      }
    }
    return activate;
  }
}
