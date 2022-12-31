package calc;

import java.util.Arrays;
import java.util.List;
import data.CompositeExpression;
import data.Digit;
import data.Operand;
import data.SingleUnit;
import data.Unit;

public class UnitConversions
{
  private String unit;
  private String type;
  // converts everything to lowest imperial or metric unit
  private double conversionFactor;

  // /**
  // * Constructor.
  // *
  // * @param unit
  // * the unit of the number.
  // * @param type
  // * the type of number it is
  // * @param conversionFactor
  // * converting to a smaller number in the same unit system
  // */
  // private void create(String unit, String type, double conversionFactor)
  // {
  // List<UnitConversionEnum> r = Arrays.asList(UnitConversionEnum.values());
  // // populate the maps with their respective units
  // for (int i = 0; i < r.size(); i++)
  // {
  // allTypes.put(r.get(i).getType(), 0.0);
  // if (r.get(i).getType().equals("imperial"))
  // {
  // imperial.put(r.get(i).toString(), r.get(i).getConversionFactor());
  // }
  // else if (r.get(i).getType().equals("metric"))
  // {
  // metric.put(r.get(i).toString(), r.get(i).getConversionFactor());
  // }
  // else if (r.get(i).getType().equals("time"))
  // {
  // time.put(r.get(i).toString(), r.get(i).getConversionFactor());
  // }
  // else if (r.get(i).getType().equals("weight"))
  // {
  // weights.put(r.get(i).toString(), r.get(i).getConversionFactor());
  // }
  // else if (r.get(i).getType().equals("volume"))
  // {
  // volume.put(r.get(i).toString(), r.get(i).getConversionFactor());
  // }
  // else if (r.get(i).getType().equals("money"))
  // {
  // money.put(r.get(i).toString(), r.get(i).getConversionFactor());
  // }
  // }
  // this.unit = unit;
  // this.conversionFactor = conversionFactor;
  // }

  /**
   * Checks if the the unit we are looking at is in our enum set.
   * 
   * @param uni
   *          the unit we are checking.
   * @return if it is in the enum set.
   */
  public static boolean isEnum(Unit uni)
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
   * Getter method for the unit.
   * 
   * @return
   */
  public String getUnit()
  {
    return this.unit;
  }

  /**
   * Getter method for the type.
   * 
   * @return
   */
  public String getType(String unit)
  {
    return type;
  }

  /**
   * getter method for the conversion factor.
   * 
   * @return
   */
  public double getConversionFactor()
  {
    return this.conversionFactor;
  }

  /**
   * helper method to know which conversion method to call.
   * 
   * @param expression
   *          the expression we are currently evaluation
   * @return the calculated total of the converted expression
   */
  public static Operand checkUnits(CompositeExpression expression)
  {
    CalcStrategy strat = expression.getStrategy();
    // CompositeExpression e =
    String leftUnit = expression.getLeft().getUnit().getName().toLowerCase();
    String rightUnit = expression.getRight().getUnit().getName().toLowerCase();
    Digit leftNum = expression.getLeft().getValue();
    Digit rightNum = expression.getRight().getValue();
    Operand left = new Operand(leftNum, new SingleUnit(leftUnit));
    Operand right = new Operand(rightNum, new SingleUnit(rightUnit));
    Operand result = new Operand(expression.getValue(), expression.getUnit().simplify());
    // in * ft
    if (leftUnit.equals("in") && rightUnit.equals("ft"))
    {
      // left = new Operand(leftNum, new SingleUnit(leftUnit));
      right = new Operand(rightNum.multiply(UnitConversionEnum.FT.getConversionFactor()),
          new SingleUnit(leftUnit));
      result = strat.calculate(left, right);
    }
    // ft * in
    else if (leftUnit.equals("ft") && rightUnit.equals("in"))
    {
      left = new Operand(leftNum, new SingleUnit(leftUnit));
      right = new Operand(rightNum.divide(UnitConversionEnum.FT.getConversionFactor()),
          new SingleUnit(leftUnit));
      result = strat.calculate(left, right);
    }
    // in * mi
    else if (leftUnit.equals("in") && rightUnit.equals("mi"))
    {
      left = new Operand(leftNum, new SingleUnit(leftUnit));
      right = new Operand(rightNum.multiply(UnitConversionEnum.MI.getConversionFactor()),
          new SingleUnit(leftUnit));
      result = strat.calculate(left, right);

    }
    // mi * in
    else if (leftUnit.equals("mi") && rightUnit.equals("in"))
    {
      left = new Operand(leftNum, new SingleUnit(leftUnit));
      right = new Operand(rightNum.divide(UnitConversionEnum.MI.getConversionFactor()),
          new SingleUnit(leftUnit));
      result = strat.calculate(left, right);
    }
    // in * yds
    else if (leftUnit.equals("in") && rightUnit.equals("yds"))
    {
      left = new Operand(leftNum, new SingleUnit(leftUnit));
      right = new Operand(rightNum.multiply(UnitConversionEnum.YD.getConversionFactor()),
          new SingleUnit(leftUnit));
      result = strat.calculate(left, right);
    }
    // yds * in
    else if (leftUnit.equals("yds") && rightUnit.equals("in"))
    {
      left = new Operand(leftNum, new SingleUnit(leftUnit));
      right = new Operand(rightNum.divide(UnitConversionEnum.YD.getConversionFactor()),
          new SingleUnit(leftUnit));
      result = strat.calculate(left, right);
    }
    // ft * yds
    else if (leftUnit.equals("ft") && rightUnit.equals("yds"))
    {
      left = new Operand(leftNum, new SingleUnit(leftUnit));
      right = new Operand(rightNum.multiply(UnitConversionEnum.YD.getConversionFactor())
          .divide(UnitConversionEnum.FT.getConversionFactor()), new SingleUnit(leftUnit));
      result = strat.calculate(left, right);
    }
    // yds * ft
    else if (leftUnit.equals("yds") && rightUnit.equals("ft"))
    {
      left = new Operand(leftNum, new SingleUnit(leftUnit));
      right = new Operand(rightNum.multiply(UnitConversionEnum.FT.getConversionFactor())
          .divide(UnitConversionEnum.YD.getConversionFactor()), new SingleUnit(leftUnit));
      result = strat.calculate(left, right);
    }
    // ft * mi
    else if (leftUnit.equals("ft") && rightUnit.equals("mi"))
    {
      left = new Operand(leftNum, new SingleUnit(leftUnit));
      right = new Operand(rightNum.multiply(UnitConversionEnum.MI.getConversionFactor())
          .divide(UnitConversionEnum.FT.getConversionFactor()), new SingleUnit(leftUnit));
      result = strat.calculate(left, right);
    }
    // mi * ft
    else if (leftUnit.equals("mi") && rightUnit.equals("ft"))
    {
      left = new Operand(leftNum, new SingleUnit(leftUnit));
      right = new Operand(rightNum.multiply(UnitConversionEnum.FT.getConversionFactor())
          .divide(UnitConversionEnum.MI.getConversionFactor()), new SingleUnit(leftUnit));
      result = strat.calculate(left, right);
    }
    // mi * yds
    else if (leftUnit.equals("mi") && rightUnit.equals("yds"))
    {
      left = new Operand(leftNum, new SingleUnit(leftUnit));
      right = new Operand(rightNum.multiply(UnitConversionEnum.YD.getConversionFactor())
          .divide(UnitConversionEnum.MI.getConversionFactor()), new SingleUnit(leftUnit));
      result = strat.calculate(left, right);
    }
    // yds * mi
    else if (leftUnit.equals("yds") && rightUnit.equals("mi"))
    {
      left = new Operand(leftNum, new SingleUnit(leftUnit));
      right = new Operand(rightNum.multiply(UnitConversionEnum.MI.getConversionFactor())
          .divide(UnitConversionEnum.YD.getConversionFactor()), new SingleUnit(leftUnit));
      result = strat.calculate(left, right);
    }

    // cm * m
    else if (leftUnit.equals("cm") && rightUnit.equals("m"))
    {
      left = new Operand(leftNum, new SingleUnit(leftUnit));
      right = new Operand(rightNum.multiply(UnitConversionEnum.M.getConversionFactor())
          .divide(UnitConversionEnum.CM.getConversionFactor()), new SingleUnit(leftUnit));
      result = strat.calculate(left, right);
    }
    // cm * mm
    else if (leftUnit.equals("cm") && rightUnit.equals("mm"))
    {
      left = new Operand(leftNum, new SingleUnit(leftUnit));
      right = new Operand(rightNum.multiply(UnitConversionEnum.MM.getConversionFactor())
          .multiply(UnitConversionEnum.CM.getConversionFactor()), new SingleUnit(leftUnit));
      result = strat.calculate(left, right);
    }
    // cm * km
    else if (leftUnit.equals("cm") && rightUnit.equals("km"))
    {
      left = new Operand(leftNum, new SingleUnit(leftUnit));
      right = new Operand(rightNum.multiply(UnitConversionEnum.KM.getConversionFactor())
          .divide(UnitConversionEnum.CM.getConversionFactor()), new SingleUnit(leftUnit));
      result = strat.calculate(left, right);
    }
    // mm * cm
    else if (leftUnit.equals("mm") && rightUnit.equals("cm"))
    {
      left = new Operand(leftNum, new SingleUnit(leftUnit));
      right = new Operand(rightNum.multiply(UnitConversionEnum.CM.getConversionFactor())
          .divide(UnitConversionEnum.MM.getConversionFactor()), new SingleUnit(leftUnit));
      result = strat.calculate(left, right);
    }
    // mm * m
    else if (leftUnit.equals("mm") && rightUnit.equals("m"))
    {
      left = new Operand(leftNum, new SingleUnit(leftUnit));
      right = new Operand(rightNum.multiply(UnitConversionEnum.M.getConversionFactor())
          .divide(UnitConversionEnum.MM.getConversionFactor()), new SingleUnit(leftUnit));
      result = strat.calculate(left, right);
    }
    // mm * km
    else if (leftUnit.equals("mm") && rightUnit.equals("km"))
    {
      left = new Operand(leftNum, new SingleUnit(leftUnit));
      right = new Operand(rightNum.multiply(UnitConversionEnum.KM.getConversionFactor())
          .divide(UnitConversionEnum.MM.getConversionFactor()), new SingleUnit(leftUnit));
      result = strat.calculate(left, right);
    }
    // m * mm
    else if (leftUnit.equals("m") && rightUnit.equals("mm"))
    {
      left = new Operand(leftNum, new SingleUnit(leftUnit));
      right = new Operand(rightNum.multiply(UnitConversionEnum.MM.getConversionFactor())
          .divide(UnitConversionEnum.M.getConversionFactor()), new SingleUnit(leftUnit));
      result = strat.calculate(left, right);
    }
    // m * cm
    else if (leftUnit.equals("m") && rightUnit.equals("cm"))
    {
      left = new Operand(leftNum, new SingleUnit(leftUnit));
      right = new Operand(rightNum.multiply(UnitConversionEnum.CM.getConversionFactor())
          .divide(UnitConversionEnum.M.getConversionFactor()), new SingleUnit(leftUnit));
      result = strat.calculate(left, right);
    }
    // m * km
    else if (leftUnit.equals("m") && rightUnit.equals("km"))
    {
      left = new Operand(leftNum, new SingleUnit(leftUnit));
      right = new Operand(rightNum.multiply(UnitConversionEnum.KM.getConversionFactor())
          .divide(UnitConversionEnum.M.getConversionFactor()), new SingleUnit(leftUnit));
      result = strat.calculate(left, right);
    }
    // km * mm
    else if (leftUnit.equals("km") && rightUnit.equals("mm"))
    {
      left = new Operand(leftNum, new SingleUnit(leftUnit));
      right = new Operand(rightNum.multiply(UnitConversionEnum.MM.getConversionFactor())
          .divide(UnitConversionEnum.KM.getConversionFactor()), new SingleUnit(leftUnit));
      result = strat.calculate(left, right);
    }
    // km * cm
    else if (leftUnit.equals("km") && rightUnit.equals("cm"))
    {
      left = new Operand(leftNum, new SingleUnit(leftUnit));
      right = new Operand(rightNum.multiply(UnitConversionEnum.CM.getConversionFactor())
          .divide(UnitConversionEnum.KM.getConversionFactor()), new SingleUnit(leftUnit));
      result = strat.calculate(left, right);
    }
    // km * m
    else if (leftUnit.equals("km") && rightUnit.equals("m"))
    {
      left = new Operand(leftNum, new SingleUnit(leftUnit));
      right = new Operand(rightNum.multiply(UnitConversionEnum.M.getConversionFactor())
          .divide(UnitConversionEnum.KM.getConversionFactor()), new SingleUnit(leftUnit));
      result = strat.calculate(left, right);
    }

    // in * cm
    else if (leftUnit.equals("in") && rightUnit.equals("cm"))
    {
      left = new Operand(leftNum, new SingleUnit(leftUnit));
      right = new Operand(
          rightNum.multiply(UnitConversionEnum.IN.getConversionFactor()).divide(2.54),
          new SingleUnit(leftUnit));
      result = strat.calculate(left, right);
    }
    // in * mm
    else if (leftUnit.equals("in") && rightUnit.equals("mm"))
    {
      left = new Operand(leftNum, new SingleUnit(leftUnit));
      right = new Operand(
          rightNum.multiply(UnitConversionEnum.MM.getConversionFactor()).divide(2.54),
          new SingleUnit(leftUnit));
      result = strat.calculate(left, right);
    }
    // in * m
    else if (leftUnit.equals("in") && rightUnit.equals("m"))
    {
      left = new Operand(leftNum, new SingleUnit(leftUnit));
      right = new Operand(
          rightNum.multiply(UnitConversionEnum.M.getConversionFactor()).divide(2.54),
          new SingleUnit(leftUnit));
      result = strat.calculate(left, right);
    }
    // in * km
    else if (leftUnit.equals("in") && rightUnit.equals("km"))
    {
      left = new Operand(leftNum, new SingleUnit(leftUnit));
      right = new Operand(
          rightNum.multiply(UnitConversionEnum.KM.getConversionFactor()).divide(2.54),
          new SingleUnit(leftUnit));
      result = strat.calculate(left, right);
    }
    // ft * cm
    else if (leftUnit.equals("ft") && rightUnit.equals("cm"))
    {
      left = new Operand(leftNum, new SingleUnit(leftUnit));
      right = new Operand(
          (rightNum.divide(UnitConversionEnum.IN.getConversionFactor()))
              .divide(UnitConversionEnum.FT.getConversionFactor())
              .divide(UnitConversionEnum.CM.getConversionFactor()).divide(2.54),
          new SingleUnit(leftUnit));
      result = strat.calculate(left, right);
    }
    // ft * mm
    else if (leftUnit.equals("ft") && rightUnit.equals("mm"))
    {
      left = new Operand(leftNum, new SingleUnit(leftUnit));
      right = new Operand(
          (rightNum.multiply(UnitConversionEnum.MM.getConversionFactor()))
              .divide(UnitConversionEnum.FT.getConversionFactor()).divide(2.54),
          new SingleUnit(leftUnit));
      result = strat.calculate(left, right);
    }
    // ft * m
    else if (leftUnit.equals("ft") && rightUnit.equals("m"))
    {
      left = new Operand(leftNum, new SingleUnit(leftUnit));
      right = new Operand(
          (rightNum.multiply(UnitConversionEnum.M.getConversionFactor()))
              .divide(UnitConversionEnum.FT.getConversionFactor()).divide(2.54),
          new SingleUnit(leftUnit));
      result = strat.calculate(left, right);
    }
    // ft * km
    else if (leftUnit.equals("ft") && rightUnit.equals("km"))
    {
      left = new Operand(leftNum, new SingleUnit(leftUnit));
      right = new Operand(
          (rightNum.multiply(UnitConversionEnum.KM.getConversionFactor()))
              .divide(UnitConversionEnum.FT.getConversionFactor()).divide(2.54),
          new SingleUnit(leftUnit));
      result = strat.calculate(left, right);
    }

    // yds * cm
    else if (leftUnit.equals("yds") && rightUnit.equals("cm"))
    {
      left = new Operand(leftNum, new SingleUnit(leftUnit));
      right = new Operand(
          (rightNum.divide(UnitConversionEnum.IN.getConversionFactor()))
              .divide(UnitConversionEnum.YD.getConversionFactor())
              .divide(UnitConversionEnum.CM.getConversionFactor()).divide(2.54),
          new SingleUnit(leftUnit));
      result = strat.calculate(left, right);
    }
    // yds * mm
    else if (leftUnit.equals("yds") && rightUnit.equals("mm"))
    {
      left = new Operand(leftNum, new SingleUnit(leftUnit));
      right = new Operand(
          (rightNum.multiply(UnitConversionEnum.MM.getConversionFactor()))
              .divide(UnitConversionEnum.YD.getConversionFactor()).divide(2.54),
          new SingleUnit(leftUnit));
      result = strat.calculate(left, right);
    }
    // yds * m
    else if (leftUnit.equals("yds") && rightUnit.equals("m"))
    {
      left = new Operand(leftNum, new SingleUnit(leftUnit));
      right = new Operand(
          (rightNum.multiply(UnitConversionEnum.M.getConversionFactor()))
              .divide(UnitConversionEnum.YD.getConversionFactor()).divide(2.54),
          new SingleUnit(leftUnit));
      result = strat.calculate(left, right);
    }
    // yds * km
    else if (leftUnit.equals("yds") && rightUnit.equals("km"))
    {
      left = new Operand(leftNum, new SingleUnit(leftUnit));
      right = new Operand(
          (rightNum.multiply(UnitConversionEnum.KM.getConversionFactor()))
              .divide(UnitConversionEnum.YD.getConversionFactor()).divide(2.54),
          new SingleUnit(leftUnit));
      result = strat.calculate(left, right);
    }

    // mi * cm
    else if (leftUnit.equals("mi") && rightUnit.equals("cm"))
    {
      left = new Operand(leftNum, new SingleUnit(leftUnit));
      right = new Operand(
          (rightNum.divide(UnitConversionEnum.IN.getConversionFactor()))
              .divide(UnitConversionEnum.MI.getConversionFactor())
              .divide(UnitConversionEnum.CM.getConversionFactor()).divide(2.54),
          new SingleUnit(leftUnit));
      result = strat.calculate(left, right);
    }
    // mi * mm
    else if (leftUnit.equals("mi") && rightUnit.equals("mm"))
    {
      left = new Operand(leftNum, new SingleUnit(leftUnit));
      right = new Operand(
          (rightNum.multiply(UnitConversionEnum.MM.getConversionFactor()))
              .divide(UnitConversionEnum.MI.getConversionFactor()).divide(2.54),
          new SingleUnit(leftUnit));
      result = strat.calculate(left, right);
    }
    // mi * m
    else if (leftUnit.equals("mi") && rightUnit.equals("m"))
    {
      left = new Operand(leftNum, new SingleUnit(leftUnit));
      right = new Operand(
          (rightNum.multiply(UnitConversionEnum.M.getConversionFactor()))
              .divide(UnitConversionEnum.MI.getConversionFactor()).divide(2.54),
          new SingleUnit(leftUnit));
      result = strat.calculate(left, right);
    }
    // mi * km
    else if (leftUnit.equals("mi") && rightUnit.equals("km"))
    {
      left = new Operand(leftNum, new SingleUnit(leftUnit));
      right = new Operand(
          (rightNum.multiply(UnitConversionEnum.KM.getConversionFactor()))
              .divide(UnitConversionEnum.MI.getConversionFactor()).divide(2.54),
          new SingleUnit(leftUnit));
      result = strat.calculate(left, right);
    }

    // cm * in
    else if (leftUnit.equals("cm") && rightUnit.equals("in"))

    {
      left = new Operand(leftNum, new SingleUnit(leftUnit));
      right = new Operand(
          rightNum.multiply(UnitConversionEnum.IN.getConversionFactor()).multiply(2.54),
          new SingleUnit(leftUnit));
      result = strat.calculate(left, right);
    }
    // cm * ft
    else if (leftUnit.equals("cm") && rightUnit.equals("ft"))
    {
      left = new Operand(leftNum, new SingleUnit(leftUnit));
      right = new Operand(
          rightNum.multiply(UnitConversionEnum.FT.getConversionFactor()).multiply(2.54),
          new SingleUnit(leftUnit));
      result = strat.calculate(left, right);
    }
    // cm * yds
    else if (leftUnit.equals("cm") && rightUnit.equals("yds"))
    {
      left = new Operand(leftNum, new SingleUnit(leftUnit));
      right = new Operand(
          rightNum.multiply(UnitConversionEnum.YD.getConversionFactor()).multiply(2.54),
          new SingleUnit(leftUnit));
      result = strat.calculate(left, right);
    }
    // cm * mi
    else if (leftUnit.equals("cm") && rightUnit.equals("mi"))
    {
      left = new Operand(leftNum, new SingleUnit(leftUnit));
      right = new Operand(
          rightNum.multiply(UnitConversionEnum.MI.getConversionFactor()).multiply(2.54),
          new SingleUnit(leftUnit));
      result = strat.calculate(left, right);
    }
    // mm * in
    else if (leftUnit.equals("mm") && rightUnit.equals("in"))
    {
      left = new Operand(leftNum, new SingleUnit(leftUnit));
      right = new Operand(
          rightNum.divide(UnitConversionEnum.MM.getConversionFactor()).multiply(2.54),
          new SingleUnit(leftUnit));
      result = strat.calculate(left, right);
    }
    // mm * ft
    else if (leftUnit.equals("mm") && rightUnit.equals("ft"))
    {
      left = new Operand(leftNum, new SingleUnit(leftUnit));
      right = new Operand(
          rightNum.divide(UnitConversionEnum.MM.getConversionFactor())
              .multiply(UnitConversionEnum.FT.getConversionFactor()).multiply(2.54),
          new SingleUnit(leftUnit));
      result = strat.calculate(left, right);
    }
    // mm * yds
    else if (leftUnit.equals("mm") && rightUnit.equals("yds"))
    {
      left = new Operand(leftNum, new SingleUnit(leftUnit));
      right = new Operand(
          rightNum.divide(UnitConversionEnum.MM.getConversionFactor())
              .multiply(UnitConversionEnum.YD.getConversionFactor()).multiply(2.54),
          new SingleUnit(leftUnit));
      result = strat.calculate(left, right);
    }
    // mm * mi
    else if (leftUnit.equals("mm") && rightUnit.equals("mi"))
    {
      left = new Operand(leftNum, new SingleUnit(leftUnit));
      right = new Operand(
          rightNum.divide(UnitConversionEnum.MM.getConversionFactor())
              .multiply(UnitConversionEnum.MI.getConversionFactor()).multiply(2.54),
          new SingleUnit(leftUnit));
      result = strat.calculate(left, right);
    }
    // m * in
    else if (leftUnit.equals("m") && rightUnit.equals("in"))
    {
      left = new Operand(leftNum, new SingleUnit(leftUnit));
      right = new Operand(
          rightNum.divide(UnitConversionEnum.M.getConversionFactor())
              .multiply(UnitConversionEnum.IN.getConversionFactor()).multiply(2.54),
          new SingleUnit(leftUnit));
      result = strat.calculate(left, right);
    }
    // m * ft
    else if (leftUnit.equals("m") && rightUnit.equals("ft"))
    {
      left = new Operand(leftNum, new SingleUnit(leftUnit));
      right = new Operand(
          rightNum.divide(UnitConversionEnum.M.getConversionFactor())
              .multiply(UnitConversionEnum.FT.getConversionFactor()).multiply(2.54),
          new SingleUnit(leftUnit));
      result = strat.calculate(left, right);
    }
    // m * yds
    else if (leftUnit.equals("m") && rightUnit.equals("yds"))
    {
      left = new Operand(leftNum, new SingleUnit(leftUnit));
      right = new Operand(
          rightNum.divide(UnitConversionEnum.M.getConversionFactor())
              .multiply(UnitConversionEnum.YD.getConversionFactor()).multiply(2.54),
          new SingleUnit(leftUnit));
      result = strat.calculate(left, right);
    }
    // m * mi
    else if (leftUnit.equals("m") && rightUnit.equals("mi"))
    {
      left = new Operand(leftNum, new SingleUnit(leftUnit));
      right = new Operand(
          rightNum.divide(UnitConversionEnum.M.getConversionFactor())
              .multiply(UnitConversionEnum.MI.getConversionFactor()).multiply(2.54),
          new SingleUnit(leftUnit));
      result = strat.calculate(left, right);
    }
    // km * in
    else if (leftUnit.equals("km") && rightUnit.equals("in"))
    {
      left = new Operand(leftNum, new SingleUnit(leftUnit));
      right = new Operand(
          rightNum.divide(UnitConversionEnum.KM.getConversionFactor())
              .multiply(UnitConversionEnum.IN.getConversionFactor()).multiply(2.54),
          new SingleUnit(leftUnit));
      result = strat.calculate(left, right);
    }
    // km * ft
    else if (leftUnit.equals("km") && rightUnit.equals("ft"))
    {
      left = new Operand(leftNum, new SingleUnit(leftUnit));
      right = new Operand(
          rightNum.divide(UnitConversionEnum.KM.getConversionFactor())
              .multiply(UnitConversionEnum.FT.getConversionFactor()).multiply(2.54),
          new SingleUnit(leftUnit));
      result = strat.calculate(left, right);
    }
    // km * yds
    else if (leftUnit.equals("km") && rightUnit.equals("yds"))
    {
      left = new Operand(leftNum, new SingleUnit(leftUnit));
      right = new Operand(
          rightNum.divide(UnitConversionEnum.KM.getConversionFactor())
              .multiply(UnitConversionEnum.YD.getConversionFactor()).multiply(2.54),
          new SingleUnit(leftUnit));
      result = strat.calculate(left, right);
    }
    // km * mi
    else if (leftUnit.equals("km") && rightUnit.equals("mi"))
    {
      left = new Operand(leftNum, new SingleUnit(leftUnit));
      right = new Operand(rightNum.multiply(UnitConversionEnum.MI.getConversionFactor())
          .multiply(2.54).divide(UnitConversionEnum.KM.getConversionFactor()),
          new SingleUnit(leftUnit));
      result = strat.calculate(left, right);
    }

    // time conversions
    // sec * min
    else if (leftUnit.equals("sec") && rightUnit.equals("min"))
    {
      left = new Operand(leftNum, new SingleUnit(leftUnit));
      right = new Operand(rightNum.multiply(UnitConversionEnum.MIN.getConversionFactor()),
          new SingleUnit(leftUnit));
      result = strat.calculate(left, right);
    }
    // sec * hr
    else if (leftUnit.equals("sec") && rightUnit.equals("hr"))
    {
      left = new Operand(leftNum, new SingleUnit(leftUnit));
      right = new Operand(rightNum.multiply(UnitConversionEnum.HR.getConversionFactor()),
          new SingleUnit(leftUnit));
      result = strat.calculate(left, right);
    }
    // min * sec
    else if (leftUnit.equals("min") && rightUnit.equals("sec"))
    {
      left = new Operand(leftNum, new SingleUnit(leftUnit));
      right = new Operand(rightNum.divide(UnitConversionEnum.MIN.getConversionFactor()),
          new SingleUnit(leftUnit));
      result = strat.calculate(left, right);
    }
    // min * hr
    else if (leftUnit.equals("min") && rightUnit.equals("hr"))
    {
      left = new Operand(leftNum, new SingleUnit(leftUnit));
      right = new Operand(rightNum.multiply(UnitConversionEnum.HR.getConversionFactor())
          .divide(UnitConversionEnum.MIN.getConversionFactor()), new SingleUnit(leftUnit));
      result = strat.calculate(left, right);
    }
    // hr * sec
    else if (leftUnit.equals("hr") && rightUnit.equals("sec"))
    {
      left = new Operand(leftNum, new SingleUnit(leftUnit));
      right = new Operand(rightNum.divide(UnitConversionEnum.HR.getConversionFactor()),
          new SingleUnit(leftUnit));
      result = strat.calculate(left, right);
    }
    // hr * min
    else if (leftUnit.equals("hr") && rightUnit.equals("min"))
    {
      left = new Operand(leftNum, new SingleUnit(leftUnit));
      right = new Operand(rightNum.divide(UnitConversionEnum.MIN.getConversionFactor()),
          new SingleUnit(leftUnit));
      result = strat.calculate(left, right);
    }
    // days * hours
    else if (leftUnit.equals("days") && rightUnit.equals("hr"))
    {
      right = new Operand(rightNum.divide(24), new SingleUnit(leftUnit));
      result = strat.calculate(left, right);
    }
    // hr * days
    else if (leftUnit.equals("hr") && rightUnit.equals("days"))
    {
      right = new Operand(rightNum.multiply(24), new SingleUnit(leftUnit));
      result = strat.calculate(left, right);
    }
    // mi/hr * m/s
    else if (leftUnit.equals("mi/hr") && rightUnit.equals("m/s"))
    {
      right = new Operand(((rightNum.divide(0.44704))), new SingleUnit(leftUnit));
      result = strat.calculate(left, right);
    }
    // m/s * mi/hr
    else if (leftUnit.equals("m/s") && rightUnit.equals("mi/hr"))
    {
      right = new Operand(rightNum.multiply(0.44704), new SingleUnit(leftUnit));
      result = strat.calculate(left, right);
    }
    // m/s * km/hr
    else if (leftUnit.equals("m/s") && rightUnit.equals("km/hr"))
    {
      right = new Operand(rightNum.divide(3.6), new SingleUnit(leftUnit));
      result = strat.calculate(left, right);
    }
    // km/hr * m/s
    else if (leftUnit.equals("km/hr") && rightUnit.equals("m/s"))
    {
      right = new Operand(rightNum.multiply(3.6), new SingleUnit(leftUnit));
      result = strat.calculate(left, right);
    }

    return result;
  }

  /**
   * Sets the type of unit that we are converting.
   * 
   * @param units
   *          the unit we are checking the type of
   * @return the type of unit it is
   */
  public void setType(String units)
  {
    // String leftUnit = expression.getLeft().getUnit().getName().toLowerCase();
    if (units == "ft" || units == "feet" || units == "in" || units == "inch" || units == "inches"
        || units == "mi" || units == "miles" || units == "mile" || units == "yards"
        || units == "yds")
    {
      this.type = "imperial";
    }
    else if (units == "sec" || units == "seconds" || units == "min" || units == "minutes"
        || units == "hrs" || units == "hours" || units == "hr")
    {
      this.type = "time";
    }
    else if (units == "cm" || units == "centimeters" || units == "m" || units == "meters"
        || units == "km" || units == "kilometers" || units == "mm" || units == "millimeter")
    {
      this.type = "metric";
    }
    else if (units == "day" || units == "days" || units == "week" || units == "weeks")
    {
      this.type = "month";
    }
    else if (units.equals("$") || units.equals("dollar") || units.equals("dollars")
        || units.equals("c") || units.equals("cent") || units.equals("cents"))
    {
      this.type = "money";
    }
  }

  public void multipleSpellings(String units)
  {
    if (units == "ft" || units == "feet")
    {

    }
  }
}
