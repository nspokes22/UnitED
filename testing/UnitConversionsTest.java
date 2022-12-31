package testing;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import calc.Addition;
import calc.Division;
import calc.Multiplication;
import calc.Subtraction;
import calc.UnitConversionEnum;
import data.CompositeExpression;
import data.Digit;
import data.Operand;
import utilities.Power;
import data.SingleUnit;
import data.Unit;

class UnitConversionsTest
{
  // types used multiple times
  private String in = "in";
  private String ft = "ft";
  private String liters = "l";
  private String mile = "mi";
  private String yd = "yd";
  private String cm = "cm";
  private String mm = "mm";
  private String m = "m";
  private String km = "km";
  private String mon = "mon";
  private String year = "yr";
  private String day = "day";
  private String hr = "hr";
  private String sec = "sec";
  private String lb = "lb";
  private String oz = "oz";
  private String min = "min";
  private String g = "g";
  private String pt = "pt";
  private String gal = "gal";
  private String qt = "qt";
  private String ton = "ton";
  private String kg = "kg";
  private String money = "$";
  private String c = "c";
  private String cc = "cc";

  // numbers used multiple times
  private String sixT = "16";
  private String one = "1";
  private String s1678 = "16.78";
  private String fifteen = "15";
  private String s160 = "160";
  private String fifty = "50";
  private String five = "5";
  private String fHundred = "500";
  private String six = "6";
  private String sixteenHundred = "1600";
  private String sixThousand = "6000";
  private String oneMil = "1000000";
  private String p05 = "0.5";
  private String ttSixHundredM = "2600 m";
  private String hundred = "100";
  private String answer160Days = "160 day";
  private String answer161Days = "161 day";
  private String s16m = "1600000 m";
  private String oneHundredK = "100000";
  private String fiveK = "5000";
  private String one20 = "120";
  private String s160m = "160 mon";
  private String s161m = "161 mon";
  private String oneK = "1000";
  private String twoK = "2000";
  private String four = "4";
  private String y161 = "161 yr";
  private String y160 = "160 yr";
  private String y = "31536000";

  @Test
  void ftToInches()
  {
    String s19678 = "196.78 ";
    // test addition
    Operand left = new Operand(new Digit(sixT), new SingleUnit(in));
    Operand right = new Operand(new Digit(one), new SingleUnit(ft));
    CompositeExpression expression = new CompositeExpression(left, new Addition(), right);
    Operand answer = UnitConversionEnum.checkUnits(expression);
    assertEquals("28 in", answer.toString());

    left = new Operand(new Digit(s1678), new SingleUnit(in));
    right = new Operand(new Digit(fifteen), new SingleUnit(ft));
    expression = new CompositeExpression(left, new Addition(), right);
    answer = UnitConversionEnum.checkUnits(expression);
    assertEquals(s19678 + in, answer.toString());

    // test addition
    left = new Operand(new Digit(s1678), new SingleUnit(in));
    right = new Operand(new Digit(fifteen), new SingleUnit(ft));
    expression = new CompositeExpression(left, new Addition(), right);
    answer = UnitConversionEnum.checkUnits(expression);
    assertEquals(s19678 + in, answer.toString());
    answer = UnitConversionEnum.checkUnits(expression);
    assertEquals(s19678 + in, answer.toString());

    // test subtraction
    left = new Operand(new Digit(s1678), new SingleUnit(in));
    right = new Operand(new Digit(fifteen), new SingleUnit(ft));
    expression = new CompositeExpression(left, new Subtraction(), right);
    answer = UnitConversionEnum.checkUnits(expression);
    assertEquals("-163.22 in", answer.toString());

    // test multiplication
    expression = new CompositeExpression(left, new Multiplication(), right);
    answer = UnitConversionEnum.checkUnits(expression);
    assertEquals("3020.4 in" + Power.createPower(2), answer.toString());

    // test division
    expression = new CompositeExpression(left, new Division(), right);
    answer = UnitConversionEnum.checkUnits(expression);
    assertEquals("0.09322 ", answer.toString());
  }

  @Test
  void inToFeet()
  {
    // test addition
    Operand left = new Operand(new Digit(sixT), new SingleUnit(ft));
    Operand right = new Operand(new Digit("77"), new SingleUnit(in));
    CompositeExpression expression = new CompositeExpression(left, new Addition(), right);
    Operand answer = UnitConversionEnum.checkUnits(expression);
    assertEquals("22.41667 ft", answer.toString());
    // test Subtraction
    expression = new CompositeExpression(left, new Subtraction(), right);
    answer = UnitConversionEnum.checkUnits(expression);
    assertEquals("9.58333 ft", answer.toString());

    // test Division
    expression = new CompositeExpression(left, new Division(), right);
    answer = UnitConversionEnum.checkUnits(expression);
    assertEquals("2.49351 ", answer.toString());

    // test Multiplication
    expression = new CompositeExpression(left, new Multiplication(), right);
    answer = UnitConversionEnum.checkUnits(expression);
    assertEquals("102.66672 ft" + Power.createPower(2), answer.toString());
  }

  @Test
  void inXmiles()
  {
    // addition
    Operand left = new Operand(new Digit(sixT), new SingleUnit(in));
    Operand right = new Operand(new Digit(five), new SingleUnit(mile));
    CompositeExpression expression = new CompositeExpression(left, new Addition(), right);
    Operand answer = UnitConversionEnum.checkUnits(expression);
    assertEquals("316816 in", answer.toString());

    // subtraction
    left = new Operand(new Digit(s1678), new SingleUnit(in));
    right = new Operand(new Digit(five), new SingleUnit(mile));
    expression = new CompositeExpression(left, new Subtraction(), right);
    answer = UnitConversionEnum.checkUnits(expression);
    assertEquals("-316783.22 in", answer.toString());

    // multiplication
    expression = new CompositeExpression(left, new Multiplication(), right);
    answer = UnitConversionEnum.checkUnits(expression);
    assertEquals("5315904 in" + Power.createPower(2), answer.toString());

    // division
    expression = new CompositeExpression(left, new Division(), right);
    answer = UnitConversionEnum.checkUnits(expression);
    // CHECK THE ROUNDING ON THIS
    assertEquals("0.00005 ", answer.toString());
  }

  @Test
  void miXin()

  {
    // addition
    Operand left = new Operand(new Digit(sixT), new SingleUnit(mile));
    Operand right = new Operand(new Digit(fiveK), new SingleUnit(in));
    CompositeExpression expression = new CompositeExpression(left, new Addition(), right);
    Operand answer = UnitConversionEnum.checkUnits(expression);
    assertEquals("16.07891 mi", answer.toString());
    // Subtraction
    expression = new CompositeExpression(left, new Subtraction(), right);
    answer = UnitConversionEnum.checkUnits(expression);
    assertEquals("15.92109 mi", answer.toString());
    // multiplication
    expression = new CompositeExpression(left, new Multiplication(), right);
    answer = UnitConversionEnum.checkUnits(expression);
    assertEquals("1.26256 mi" + Power.createPower(2), answer.toString());

    // division
    expression = new CompositeExpression(left, new Division(), right);
    answer = UnitConversionEnum.checkUnits(expression);
    assertEquals("202.76264 ", answer.toString());

  }

  @Test
  void inXyard()
  {
    // addition
    Operand left = new Operand(new Digit(sixT), new SingleUnit(in));
    Operand right = new Operand(new Digit(fifty), new SingleUnit(yd));
    CompositeExpression expression = new CompositeExpression(left, new Addition(), right);
    Operand answer = UnitConversionEnum.checkUnits(expression);
    assertEquals("1816 in", answer.toString());

    // subtraction
    expression = new CompositeExpression(left, new Subtraction(), right);
    answer = UnitConversionEnum.checkUnits(expression);
    assertEquals("-1784 in", answer.toString());

    // multiplication
    expression = new CompositeExpression(left, new Multiplication(), right);
    answer = UnitConversionEnum.checkUnits(expression);
    assertEquals("28800 in" + Power.createPower(2), answer.toString());

    // division
    expression = new CompositeExpression(left, new Division(), right);
    answer = UnitConversionEnum.checkUnits(expression);
    assertEquals("0.00889 ", answer.toString());
  }

  @Test
  void yardXin()
  {
    // addition
    Operand left = new Operand(new Digit(sixT), new SingleUnit(yd));
    Operand right = new Operand(new Digit(fHundred), new SingleUnit(in));
    CompositeExpression expression = new CompositeExpression(left, new Addition(), right);
    Operand answer = UnitConversionEnum.checkUnits(expression);
    assertEquals("29.88889 yd", answer.toString());

    // multiplication
    expression = new CompositeExpression(left, new Multiplication(), right);
    answer = UnitConversionEnum.checkUnits(expression);
    assertEquals("222.22224 yd" + Power.createPower(2), answer.toString());

  }

  @Test
  void ftXyds()
  {
    // addition
    Operand left = new Operand(new Digit("16.55"), new SingleUnit(ft));
    Operand right = new Operand(new Digit("50.55"), new SingleUnit(yd));
    CompositeExpression expression = new CompositeExpression(left, new Addition(), right);
    Operand answer = UnitConversionEnum.checkUnits(expression);
    assertEquals("168.2 ft", answer.toString());

    // multiplication
    expression = new CompositeExpression(left, new Multiplication(), right);
    answer = UnitConversionEnum.checkUnits(expression);
    assertEquals("2509.8075 ft" + Power.createPower(2), answer.toString());
  }

  @Test
  void ydsXft()
  {
    // addition
    Operand left = new Operand(new Digit("16.54"), new SingleUnit(yd));
    Operand right = new Operand(new Digit("50.54"), new SingleUnit(ft));
    CompositeExpression expression = new CompositeExpression(left, new Addition(), right);
    Operand answer = UnitConversionEnum.checkUnits(expression);
    assertEquals("33.38667 yd", answer.toString());

    // multiplication
    expression = new CompositeExpression(left, new Multiplication(), right);
    answer = UnitConversionEnum.checkUnits(expression);
    assertEquals("278.64392 yd" + Power.createPower(2), answer.toString());
  }

  @Test
  void ftXmi()
  {
    // addition
    Operand left = new Operand(new Digit(sixteenHundred), new SingleUnit(ft));
    Operand right = new Operand(new Digit(five), new SingleUnit(mile));
    CompositeExpression expression = new CompositeExpression(left, new Addition(), right);
    Operand answer = UnitConversionEnum.checkUnits(expression);
    assertEquals("28000 ft", answer.toString());
    // multiplication
    expression = new CompositeExpression(left, new Multiplication(), right);
    answer = UnitConversionEnum.checkUnits(expression);
    assertEquals("42240000 ft" + Power.createPower(2), answer.toString());
  }

  @Test
  void miXFT()
  {
    // addition
    Operand left = new Operand(new Digit(sixT), new SingleUnit(mile));
    Operand right = new Operand(new Digit(sixThousand), new SingleUnit(ft));
    CompositeExpression expression = new CompositeExpression(left, new Addition(), right);
    Operand answer = UnitConversionEnum.checkUnits(expression);
    assertEquals("17.13636 mi", answer.toString());
    // multiplication
    expression = new CompositeExpression(left, new Multiplication(), right);
    answer = UnitConversionEnum.checkUnits(expression);
    assertEquals("18.18176 mi" + Power.createPower(2), answer.toString());
  }

  @Test
  void miXyds()
  {
    // addition
    Operand left = new Operand(new Digit(sixT), new SingleUnit(mile));
    Operand right = new Operand(new Digit(sixThousand), new SingleUnit(yd));
    CompositeExpression expression = new CompositeExpression(left, new Addition(), right);
    Operand answer = UnitConversionEnum.checkUnits(expression);
    assertEquals("19.40909 mi", answer.toString());
    // multiplication
    expression = new CompositeExpression(left, new Multiplication(), right);
    answer = UnitConversionEnum.checkUnits(expression);
    assertEquals("54.54544 mi" + Power.createPower(2), answer.toString());
  }

  @Test
  void ydsXmi()
  {
    // addition
    Operand left = new Operand(new Digit(sixteenHundred), new SingleUnit(yd));
    Operand right = new Operand(new Digit("61"), new SingleUnit(mile));
    CompositeExpression expression = new CompositeExpression(left, new Addition(), right);
    Operand answer = UnitConversionEnum.checkUnits(expression);
    assertEquals("108960 yd", answer.toString());
    // multiplication
    expression = new CompositeExpression(left, new Multiplication(), right);
    answer = UnitConversionEnum.checkUnits(expression);
    assertEquals("171776000 yd" + Power.createPower(2), answer.toString());
  }

  @Test
  void cmXm()
  {
    // addition
    Operand left = new Operand(new Digit(sixteenHundred), new SingleUnit(cm));
    Operand right = new Operand(new Digit("60"), new SingleUnit(m));
    CompositeExpression expression = new CompositeExpression(left, new Addition(), right);
    Operand answer = UnitConversionEnum.checkUnits(expression);
    assertEquals("7600 cm", answer.toString());
    // multiplication
    expression = new CompositeExpression(left, new Multiplication(), right);
    answer = UnitConversionEnum.checkUnits(expression);
    assertEquals("9600000 cm" + Power.createPower(2), answer.toString());
  }

  @Test
  void cmXmm()
  {
    // addition
    Operand left = new Operand(new Digit(sixteenHundred), new SingleUnit(cm));
    Operand right = new Operand(new Digit(sixThousand), new SingleUnit(mm));
    CompositeExpression expression = new CompositeExpression(left, new Addition(), right);
    Operand answer = UnitConversionEnum.checkUnits(expression);
    assertEquals("2200 cm", answer.toString());
    // multiplication
    expression = new CompositeExpression(left, new Multiplication(), right);
    answer = UnitConversionEnum.checkUnits(expression);
    assertEquals("960000 cm" + Power.createPower(2), answer.toString());
  }

  @Test
  void cmXkm()
  {
    // addition
    Operand left = new Operand(new Digit(sixteenHundred), new SingleUnit(cm));
    Operand right = new Operand(new Digit(six), new SingleUnit(km));
    CompositeExpression expression = new CompositeExpression(left, new Addition(), right);
    Operand answer = UnitConversionEnum.checkUnits(expression);
    assertEquals("601600 cm", answer.toString());
    // multiplication
    expression = new CompositeExpression(left, new Multiplication(), right);
    answer = UnitConversionEnum.checkUnits(expression);
    assertEquals("960000000 cm" + Power.createPower(2), answer.toString());
  }

  @Test
  void mmXcm()
  {
    // addition
    Operand left = new Operand(new Digit(sixteenHundred), new SingleUnit(mm));
    Operand right = new Operand(new Digit(six), new SingleUnit(cm));
    CompositeExpression expression = new CompositeExpression(left, new Addition(), right);
    Operand answer = UnitConversionEnum.checkUnits(expression);
    assertEquals("1660 mm", answer.toString());
    // multiplication
    expression = new CompositeExpression(left, new Multiplication(), right);
    answer = UnitConversionEnum.checkUnits(expression);
    assertEquals("96000 mm" + Power.createPower(2), answer.toString());
  }

  @Test
  void mmXm()
  {
    // addition
    Operand left = new Operand(new Digit(sixteenHundred), new SingleUnit(mm));
    Operand right = new Operand(new Digit(six), new SingleUnit(m));
    CompositeExpression expression = new CompositeExpression(left, new Addition(), right);
    Operand answer = UnitConversionEnum.checkUnits(expression);
    assertEquals("7600 mm", answer.toString());
    // multiplication
    expression = new CompositeExpression(left, new Multiplication(), right);
    answer = UnitConversionEnum.checkUnits(expression);
    assertEquals("9600000 mm" + Power.createPower(2), answer.toString());
  }

  @Test
  void mmXkm()
  {
    // addition
    Operand left = new Operand(new Digit(sixteenHundred), new SingleUnit(mm));
    Operand right = new Operand(new Digit(one), new SingleUnit(km));
    CompositeExpression expression = new CompositeExpression(left, new Addition(), right);
    Operand answer = UnitConversionEnum.checkUnits(expression);
    assertEquals("1001600 mm", answer.toString());
    // multiplication
    expression = new CompositeExpression(left, new Multiplication(), right);
    answer = UnitConversionEnum.checkUnits(expression);
    assertEquals("1600000000 mm" + Power.createPower(2), answer.toString());
  }

  @Test
  void mXmm()
  {
    // addition
    Operand left = new Operand(new Digit(sixteenHundred), new SingleUnit(m));
    Operand right = new Operand(new Digit(oneMil), new SingleUnit(mm));
    CompositeExpression expression = new CompositeExpression(left, new Addition(), right);
    Operand answer = UnitConversionEnum.checkUnits(expression);
    assertEquals(ttSixHundredM, answer.toString());
    // multiplication
    expression = new CompositeExpression(left, new Multiplication(), right);
    answer = UnitConversionEnum.checkUnits(expression);
    assertEquals(s16m + Power.createPower(2), answer.toString());
  }

  @Test
  void mXcm()
  {
    // addition
    Operand left = new Operand(new Digit(sixteenHundred), new SingleUnit(m));
    Operand right = new Operand(new Digit(oneHundredK), new SingleUnit(cm));
    CompositeExpression expression = new CompositeExpression(left, new Addition(), right);
    Operand answer = UnitConversionEnum.checkUnits(expression);
    assertEquals(ttSixHundredM, answer.toString());
    // multiplication
    expression = new CompositeExpression(left, new Multiplication(), right);
    answer = UnitConversionEnum.checkUnits(expression);
    assertEquals(s16m + Power.createPower(2), answer.toString());
  }

  @Test
  void mXkm()
  {
    // addition
    Operand left = new Operand(new Digit(sixteenHundred), new SingleUnit(m));
    Operand right = new Operand(new Digit("10000"), new SingleUnit(km));
    CompositeExpression expression = new CompositeExpression(left, new Addition(), right);
    Operand answer = UnitConversionEnum.checkUnits(expression);
    assertEquals("10001600 m", answer.toString());
    // multiplication
    expression = new CompositeExpression(left, new Multiplication(), right);
    answer = UnitConversionEnum.checkUnits(expression);
    assertEquals("16000000000 m" + Power.createPower(2), answer.toString());
  }

  @Test
  void kmXmm()
  {
    // addition
    Operand left = new Operand(new Digit(sixteenHundred), new SingleUnit(km));
    Operand right = new Operand(new Digit(oneMil), new SingleUnit(mm));
    CompositeExpression expression = new CompositeExpression(left, new Addition(), right);
    Operand answer = UnitConversionEnum.checkUnits(expression);
    assertEquals("1601 km", answer.toString());
    // multiplication
    expression = new CompositeExpression(left, new Multiplication(), right);
    answer = UnitConversionEnum.checkUnits(expression);
    assertEquals("1600 km" + Power.createPower(2), answer.toString());
  }

  @Test
  void kmXcm()
  {
    // addition
    Operand left = new Operand(new Digit(sixteenHundred), new SingleUnit(km));
    Operand right = new Operand(new Digit(oneMil), new SingleUnit(cm));
    CompositeExpression expression = new CompositeExpression(left, new Addition(), right);
    Operand answer = UnitConversionEnum.checkUnits(expression);
    assertEquals("1610 km", answer.toString());
    // multiplication
    expression = new CompositeExpression(left, new Multiplication(), right);
    answer = UnitConversionEnum.checkUnits(expression);
    assertEquals("16000 km" + Power.createPower(2), answer.toString());
  }

  @Test
  void kmXm()
  {
    // addition
    Operand left = new Operand(new Digit(sixteenHundred), new SingleUnit(km));
    Operand right = new Operand(new Digit(oneMil), new SingleUnit(m));
    CompositeExpression expression = new CompositeExpression(left, new Addition(), right);
    Operand answer = UnitConversionEnum.checkUnits(expression);
    assertEquals("2600 km", answer.toString());
    // multiplication
    expression = new CompositeExpression(left, new Multiplication(), right);
    answer = UnitConversionEnum.checkUnits(expression);
    assertEquals("1600000 km" + Power.createPower(2), answer.toString());
  }

  @Test
  void inXcm()
  {
    // addition
    Operand left = new Operand(new Digit(sixteenHundred), new SingleUnit(in));
    Operand right = new Operand(new Digit(hundred), new SingleUnit(cm));
    CompositeExpression expression = new CompositeExpression(left, new Addition(), right);
    Operand answer = UnitConversionEnum.checkUnits(expression);
    assertEquals("1639.37008 in", answer.toString());
    // multiplication
    expression = new CompositeExpression(left, new Multiplication(), right);
    answer = UnitConversionEnum.checkUnits(expression);
    assertEquals("62992.128 in" + Power.createPower(2), answer.toString());
  }

  @Test
  void inXmm()
  {
    // addition
    Operand left = new Operand(new Digit(sixteenHundred), new SingleUnit(in));
    Operand right = new Operand(new Digit(hundred), new SingleUnit(mm));
    CompositeExpression expression = new CompositeExpression(left, new Addition(), right);
    Operand answer = UnitConversionEnum.checkUnits(expression);
    assertEquals("1603.93701 in", answer.toString());
    // multiplication
    expression = new CompositeExpression(left, new Multiplication(), right);
    answer = UnitConversionEnum.checkUnits(expression);
    assertEquals("6299.216 in" + Power.createPower(2), answer.toString());
  }

  @Test
  void inXm()
  {
    // addition
    Operand left = new Operand(new Digit(sixteenHundred), new SingleUnit(in));
    Operand right = new Operand(new Digit(hundred), new SingleUnit(m));
    CompositeExpression expression = new CompositeExpression(left, new Addition(), right);
    Operand answer = UnitConversionEnum.checkUnits(expression);
    assertEquals("5537.00787 in", answer.toString());
    // multiplication
    expression = new CompositeExpression(left, new Multiplication(), right);
    answer = UnitConversionEnum.checkUnits(expression);
    assertEquals("6299212.592 in" + Power.createPower(2), answer.toString());
  }

  @Test
  void inXkm()
  {
    // addition
    Operand left = new Operand(new Digit(sixteenHundred), new SingleUnit(in));
    Operand right = new Operand(new Digit("10"), new SingleUnit(km));
    CompositeExpression expression = new CompositeExpression(left, new Addition(), right);
    Operand answer = UnitConversionEnum.checkUnits(expression);
    assertEquals("395300.7874 in", answer.toString());
    // multiplication
    expression = new CompositeExpression(left, new Multiplication(), right);
    answer = UnitConversionEnum.checkUnits(expression);
    assertEquals("629921259.84 in" + Power.createPower(2), answer.toString());
  }

  @Test
  void ftXcm()
  {
    // addition
    Operand left = new Operand(new Digit(sixteenHundred), new SingleUnit(ft));
    Operand right = new Operand(new Digit("32"), new SingleUnit(cm));
    CompositeExpression expression = new CompositeExpression(left, new Addition(), right);
    Operand answer = UnitConversionEnum.checkUnits(expression);
    assertEquals("1601.04987 ft", answer.toString());
    // multiplication
    expression = new CompositeExpression(left, new Multiplication(), right);
    answer = UnitConversionEnum.checkUnits(expression);
    assertEquals("1679.792 ft" + Power.createPower(2), answer.toString());
  }

  @Test
  void ftXmm()
  {
    // addition
    Operand left = new Operand(new Digit(sixteenHundred), new SingleUnit(ft));
    Operand right = new Operand(new Digit("310"), new SingleUnit(mm));
    CompositeExpression expression = new CompositeExpression(left, new Addition(), right);
    Operand answer = UnitConversionEnum.checkUnits(expression);
    assertEquals("1601.01706 ft", answer.toString());
    // multiplication
    expression = new CompositeExpression(left, new Multiplication(), right);
    answer = UnitConversionEnum.checkUnits(expression);
    assertEquals("1627.296 ft" + Power.createPower(2), answer.toString());
  }

  @Test
  void ftXm()
  {
    // addition
    Operand left = new Operand(new Digit(sixteenHundred), new SingleUnit(ft));
    Operand right = new Operand(new Digit("311"), new SingleUnit(m));
    CompositeExpression expression = new CompositeExpression(left, new Addition(), right);
    Operand answer = UnitConversionEnum.checkUnits(expression);
    assertEquals("2620.34121 ft", answer.toString());
    // multiplication
    expression = new CompositeExpression(left, new Multiplication(), right);
    answer = UnitConversionEnum.checkUnits(expression);
    assertEquals("1632545.936 ft" + Power.createPower(2), answer.toString());
  }

  @Test
  void ftXkm()
  {
    // addition
    Operand left = new Operand(new Digit(sixteenHundred), new SingleUnit(ft));
    Operand right = new Operand(new Digit("31"), new SingleUnit(km));
    CompositeExpression expression = new CompositeExpression(left, new Addition(), right);
    Operand answer = UnitConversionEnum.checkUnits(expression);
    assertEquals("103306.03674 ft", answer.toString());
    // multiplication
    expression = new CompositeExpression(left, new Multiplication(), right);
    answer = UnitConversionEnum.checkUnits(expression);
    assertEquals("162729658.784 ft" + Power.createPower(2), answer.toString());
  }

  @Test
  void ydsXcm()
  {
    // addition
    Operand left = new Operand(new Digit(sixteenHundred), new SingleUnit(yd));
    Operand right = new Operand(new Digit(fHundred), new SingleUnit(cm));
    CompositeExpression expression = new CompositeExpression(left, new Addition(), right);
    Operand answer = UnitConversionEnum.checkUnits(expression);
    assertEquals("1605.46807 yd", answer.toString());
    // multiplication
    expression = new CompositeExpression(left, new Multiplication(), right);
    answer = UnitConversionEnum.checkUnits(expression);
    assertEquals("8748.912 yd" + Power.createPower(2), answer.toString());
  }

  @Test
  void ydsXmm()
  {
    // addition
    Operand left = new Operand(new Digit(sixteenHundred), new SingleUnit(yd));
    Operand right = new Operand(new Digit("5001"), new SingleUnit(mm));
    CompositeExpression expression = new CompositeExpression(left, new Addition(), right);
    Operand answer = UnitConversionEnum.checkUnits(expression);
    assertEquals("1605.46916 yd", answer.toString());
    // multiplication
    expression = new CompositeExpression(left, new Multiplication(), right);
    answer = UnitConversionEnum.checkUnits(expression);
    assertEquals("8750.656 yd" + Power.createPower(2), answer.toString());
  }

  @Test
  void ydsXm()
  {
    // addition
    Operand left = new Operand(new Digit(sixteenHundred), new SingleUnit(yd));
    Operand right = new Operand(new Digit(fifty), new SingleUnit(m));
    CompositeExpression expression = new CompositeExpression(left, new Addition(), right);
    Operand answer = UnitConversionEnum.checkUnits(expression);
    assertEquals("1654.68067 yd", answer.toString());
    // multiplication
    expression = new CompositeExpression(left, new Multiplication(), right);
    answer = UnitConversionEnum.checkUnits(expression);
    assertEquals("87489.072 yd" + Power.createPower(2), answer.toString());
  }

  @Test
  void ydsXkm()
  {
    // addition
    Operand left = new Operand(new Digit(sixteenHundred), new SingleUnit(yd));
    Operand right = new Operand(new Digit(fifty), new SingleUnit(km));
    CompositeExpression expression = new CompositeExpression(left, new Addition(), right);
    Operand answer = UnitConversionEnum.checkUnits(expression);
    assertEquals("56280.66492 yd", answer.toString());
    // multiplication
    expression = new CompositeExpression(left, new Multiplication(), right);
    answer = UnitConversionEnum.checkUnits(expression);
    assertEquals("87489063.872 yd" + Power.createPower(2), answer.toString());
  }

  @Test
  void miXcm()
  {
    // addition
    Operand left = new Operand(new Digit(sixteenHundred), new SingleUnit(mile));
    Operand right = new Operand(new Digit("50000"), new SingleUnit(cm));
    CompositeExpression expression = new CompositeExpression(left, new Addition(), right);
    Operand answer = UnitConversionEnum.checkUnits(expression);
    assertEquals("1600.31069 mi", answer.toString());
    // multiplication
    expression = new CompositeExpression(left, new Multiplication(), right);
    answer = UnitConversionEnum.checkUnits(expression);
    assertEquals("497.104 mi" + Power.createPower(2), answer.toString());
  }

  @Test
  void miXmm()
  {
    // addition
    Operand left = new Operand(new Digit(sixteenHundred), new SingleUnit(mile));
    Operand right = new Operand(new Digit("5000000"), new SingleUnit(mm));
    CompositeExpression expression = new CompositeExpression(left, new Addition(), right);
    Operand answer = UnitConversionEnum.checkUnits(expression);
    assertEquals("1603.10685 mi", answer.toString());
    // multiplication
    expression = new CompositeExpression(left, new Multiplication(), right);
    answer = UnitConversionEnum.checkUnits(expression);
    assertEquals("4970.96 mi" + Power.createPower(2), answer.toString());
  }

  @Test
  void miXm()
  {
    // addition
    Operand left = new Operand(new Digit(sixteenHundred), new SingleUnit(mile));
    Operand right = new Operand(new Digit(fifty), new SingleUnit(m));
    System.out.println(right.getValue().getValueDouble());
    CompositeExpression expression = new CompositeExpression(left, new Addition(), right);
    Operand answer = UnitConversionEnum.checkUnits(expression);
    assertEquals("1600.03107 mi", answer.toString());
    // multiplication
    expression = new CompositeExpression(left, new Multiplication(), right);
    answer = UnitConversionEnum.checkUnits(expression);
    assertEquals("49.712 mi" + Power.createPower(2), answer.toString());
  }

  @Test
  void miXkm()
  {
    // addition
    Operand left = new Operand(new Digit(sixteenHundred), new SingleUnit(mile));
    Operand right = new Operand(new Digit(fifty), new SingleUnit(km));
    System.out.println(right.getValue().getValueDouble());
    CompositeExpression expression = new CompositeExpression(left, new Addition(), right);
    Operand answer = UnitConversionEnum.checkUnits(expression);
    assertEquals("1631.06856 mi", answer.toString());
    // multiplication
    expression = new CompositeExpression(left, new Multiplication(), right);
    answer = UnitConversionEnum.checkUnits(expression);
    assertEquals("49709.696 mi" + Power.createPower(2), answer.toString());
  }

  @Test
  void cmXin()
  {
    // addition
    Operand left = new Operand(new Digit(s160), new SingleUnit(cm));
    Operand right = new Operand(new Digit(fifty), new SingleUnit(in));
    CompositeExpression expression = new CompositeExpression(left, new Addition(), right);
    Operand answer = UnitConversionEnum.checkUnits(expression);
    assertEquals("287 cm", answer.toString());
    // multiplication
    expression = new CompositeExpression(left, new Multiplication(), right);
    answer = UnitConversionEnum.checkUnits(expression);
    assertEquals("20320 cm" + Power.createPower(2), answer.toString());
  }

  @Test
  void cmXft()
  {
    // addition
    Operand left = new Operand(new Digit(s160), new SingleUnit(cm));
    Operand right = new Operand(new Digit(fifty), new SingleUnit(ft));
    CompositeExpression expression = new CompositeExpression(left, new Addition(), right);
    Operand answer = UnitConversionEnum.checkUnits(expression);
    assertEquals("1684 cm", answer.toString());
    // multiplication
    expression = new CompositeExpression(left, new Multiplication(), right);
    answer = UnitConversionEnum.checkUnits(expression);
    assertEquals("243840 cm" + Power.createPower(2), answer.toString());
  }

  @Test
  void cmXyds()
  {
    // addition
    Operand left = new Operand(new Digit(s160), new SingleUnit(cm));
    Operand right = new Operand(new Digit(fifty), new SingleUnit(yd));
    CompositeExpression expression = new CompositeExpression(left, new Addition(), right);
    Operand answer = UnitConversionEnum.checkUnits(expression);
    assertEquals("4732 cm", answer.toString());
    // multiplication
    expression = new CompositeExpression(left, new Multiplication(), right);
    answer = UnitConversionEnum.checkUnits(expression);
    assertEquals("731520 cm" + Power.createPower(2), answer.toString());
  }

  @Test
  void cmXmi()
  {
    // addition
    Operand left = new Operand(new Digit(s160), new SingleUnit(cm));
    Operand right = new Operand(new Digit(five), new SingleUnit(mile));
    CompositeExpression expression = new CompositeExpression(left, new Addition(), right);
    Operand answer = UnitConversionEnum.checkUnits(expression);
    assertEquals("804832 cm", answer.toString());
    // multiplication
    expression = new CompositeExpression(left, new Multiplication(), right);
    answer = UnitConversionEnum.checkUnits(expression);
    assertEquals("128747520 cm" + Power.createPower(2), answer.toString());
  }

  @Test
  void mmXin()
  {
    // addition
    Operand left = new Operand(new Digit(s160), new SingleUnit(mm));
    Operand right = new Operand(new Digit(fifty), new SingleUnit(in));
    CompositeExpression expression = new CompositeExpression(left, new Addition(), right);
    Operand answer = UnitConversionEnum.checkUnits(expression);
    assertEquals("1430 mm", answer.toString());
    // multiplication
    expression = new CompositeExpression(left, new Multiplication(), right);
    answer = UnitConversionEnum.checkUnits(expression);
    assertEquals("203200 mm" + Power.createPower(2), answer.toString());
  }

  @Test
  void mmXft()
  {
    // addition
    Operand left = new Operand(new Digit(s160), new SingleUnit(mm));
    Operand right = new Operand(new Digit(five), new SingleUnit(ft));
    CompositeExpression expression = new CompositeExpression(left, new Addition(), right);
    Operand answer = UnitConversionEnum.checkUnits(expression);
    assertEquals("1684 mm", answer.toString());
    // multiplication
    expression = new CompositeExpression(left, new Multiplication(), right);
    answer = UnitConversionEnum.checkUnits(expression);
    assertEquals("243840 mm" + Power.createPower(2), answer.toString());
  }

  @Test
  void mmXyds()
  {
    // addition
    Operand left = new Operand(new Digit(s160), new SingleUnit(mm));
    Operand right = new Operand(new Digit(fifty), new SingleUnit(yd));
    CompositeExpression expression = new CompositeExpression(left, new Addition(), right);
    Operand answer = UnitConversionEnum.checkUnits(expression);
    assertEquals("45880 mm", answer.toString());
    // multiplication
    expression = new CompositeExpression(left, new Multiplication(), right);
    answer = UnitConversionEnum.checkUnits(expression);
    assertEquals("7315200 mm" + Power.createPower(2), answer.toString());
  }

  @Test
  void mmXmi()
  {
    // addition
    Operand left = new Operand(new Digit(s160), new SingleUnit(mm));
    Operand right = new Operand(new Digit(p05), new SingleUnit(mile));
    CompositeExpression expression = new CompositeExpression(left, new Addition(), right);
    Operand answer = UnitConversionEnum.checkUnits(expression);
    assertEquals("804832 mm", answer.toString());
    // multiplication
    expression = new CompositeExpression(left, new Multiplication(), right);
    answer = UnitConversionEnum.checkUnits(expression);
    assertEquals("128747520 mm" + Power.createPower(2), answer.toString());
  }

  @Test
  void mXin()
  {
    // addition
    Operand left = new Operand(new Digit(s160), new SingleUnit(m));
    Operand right = new Operand(new Digit(fifty), new SingleUnit(in));
    CompositeExpression expression = new CompositeExpression(left, new Addition(), right);
    Operand answer = UnitConversionEnum.checkUnits(expression);
    assertEquals("161.27 m", answer.toString());
    // multiplication
    expression = new CompositeExpression(left, new Multiplication(), right);
    answer = UnitConversionEnum.checkUnits(expression);
    assertEquals("203.2 m" + Power.createPower(2), answer.toString());
  }

  @Test
  void mXft()
  {
    // addition
    Operand left = new Operand(new Digit(s160), new SingleUnit(m));
    Operand right = new Operand(new Digit(five), new SingleUnit(ft));
    CompositeExpression expression = new CompositeExpression(left, new Addition(), right);
    Operand answer = UnitConversionEnum.checkUnits(expression);
    assertEquals("161.524 m", answer.toString());
    // multiplication
    expression = new CompositeExpression(left, new Multiplication(), right);
    answer = UnitConversionEnum.checkUnits(expression);
    assertEquals("243.84 m" + Power.createPower(2), answer.toString());
  }

  @Test
  void mXyds()
  {
    // addition
    Operand left = new Operand(new Digit(s160), new SingleUnit(m));
    Operand right = new Operand(new Digit(fifty), new SingleUnit(yd));
    CompositeExpression expression = new CompositeExpression(left, new Addition(), right);
    Operand answer = UnitConversionEnum.checkUnits(expression);
    assertEquals("205.72 m", answer.toString());
    // multiplication
    expression = new CompositeExpression(left, new Multiplication(), right);
    answer = UnitConversionEnum.checkUnits(expression);
    assertEquals("7315.2 m" + Power.createPower(2), answer.toString());
  }

  @Test
  void mXmi()
  {
    // addition
    Operand left = new Operand(new Digit(s160), new SingleUnit(m));
    Operand right = new Operand(new Digit(p05), new SingleUnit(mile));
    CompositeExpression expression = new CompositeExpression(left, new Addition(), right);
    Operand answer = UnitConversionEnum.checkUnits(expression);
    assertEquals("964.672 m", answer.toString());
    // multiplication
    expression = new CompositeExpression(left, new Multiplication(), right);
    answer = UnitConversionEnum.checkUnits(expression);
    assertEquals("128747.52 m" + Power.createPower(2), answer.toString());
  }

  @Test
  void kmXin()
  {
    // addition
    Operand left = new Operand(new Digit(s160), new SingleUnit(km));
    Operand right = new Operand(new Digit("500000"), new SingleUnit(in));
    CompositeExpression expression = new CompositeExpression(left, new Addition(), right);
    Operand answer = UnitConversionEnum.checkUnits(expression);
    assertEquals("172.7 km", answer.toString());
    // multiplication
    expression = new CompositeExpression(left, new Multiplication(), right);
    answer = UnitConversionEnum.checkUnits(expression);
    assertEquals("2032 km" + Power.createPower(2), answer.toString());
  }

  @Test
  void kmXft()
  {
    // addition
    Operand left = new Operand(new Digit(s160), new SingleUnit(km));
    Operand right = new Operand(new Digit("5500"), new SingleUnit(ft));
    CompositeExpression expression = new CompositeExpression(left, new Addition(), right);
    Operand answer = UnitConversionEnum.checkUnits(expression);
    assertEquals("161.6764 km", answer.toString());
    // multiplication
    expression = new CompositeExpression(left, new Multiplication(), right);
    answer = UnitConversionEnum.checkUnits(expression);
    assertEquals("268.224 km" + Power.createPower(2), answer.toString());
  }

  @Test
  void kmXyds()
  {
    // addition
    Operand left = new Operand(new Digit(s160), new SingleUnit(km));
    Operand right = new Operand(new Digit(fiveK), new SingleUnit(yd));
    CompositeExpression expression = new CompositeExpression(left, new Addition(), right);
    Operand answer = UnitConversionEnum.checkUnits(expression);
    assertEquals("164.572 km", answer.toString());
    // multiplication
    expression = new CompositeExpression(left, new Multiplication(), right);
    answer = UnitConversionEnum.checkUnits(expression);
    assertEquals("731.52 km" + Power.createPower(2), answer.toString());
  }

  @Test
  void kmXmi()
  {
    // addition
    Operand left = new Operand(new Digit(s160), new SingleUnit(km));
    Operand right = new Operand(new Digit(one), new SingleUnit(mile));
    CompositeExpression expression = new CompositeExpression(left, new Addition(), right);
    Operand answer = UnitConversionEnum.checkUnits(expression);
    assertEquals("161.60934 km", answer.toString());
    // multiplication
    expression = new CompositeExpression(left, new Multiplication(), right);
    answer = UnitConversionEnum.checkUnits(expression);
    assertEquals("257.4944 km" + Power.createPower(2), answer.toString());
  }

  @Test
  void secXmin()
  {
    // addition
    Operand left = new Operand(new Digit(s160), new SingleUnit(sec));
    Operand right = new Operand(new Digit(one), new SingleUnit(min));
    CompositeExpression expression = new CompositeExpression(left, new Addition(), right);
    Operand answer = UnitConversionEnum.checkUnits(expression);
    assertEquals("220 sec", answer.toString());
    // multiplication
    expression = new CompositeExpression(left, new Multiplication(), right);
    answer = UnitConversionEnum.checkUnits(expression);
    assertEquals("9600 sec" + Power.createPower(2), answer.toString());
  }

  @Test
  void secXhr()
  {
    // addition
    Operand left = new Operand(new Digit(s160), new SingleUnit(sec));
    Operand right = new Operand(new Digit(one), new SingleUnit(hr));
    CompositeExpression expression = new CompositeExpression(left, new Addition(), right);
    Operand answer = UnitConversionEnum.checkUnits(expression);
    assertEquals("3760 sec", answer.toString());
    // multiplication
    expression = new CompositeExpression(left, new Multiplication(), right);
    answer = UnitConversionEnum.checkUnits(expression);
    assertEquals("576000 sec" + Power.createPower(2), answer.toString());
    left = new Operand(new Digit(s160), new SingleUnit(sec));
    right = new Operand(new Digit(one), new SingleUnit(sec));
    expression = new CompositeExpression(left, new Addition(), right);
    answer = UnitConversionEnum.checkUnits(expression);
    assertEquals("161 sec", answer.toString());
  }

  @Test
  void minXsec()
  {
    // addition
    Operand left = new Operand(new Digit(s160), new SingleUnit(min));
    Operand right = new Operand(new Digit(one20), new SingleUnit(sec));
    CompositeExpression expression = new CompositeExpression(left, new Addition(), right);
    Operand answer = UnitConversionEnum.checkUnits(expression);
    assertEquals("162 min", answer.toString());
    // multiplication
    expression = new CompositeExpression(left, new Multiplication(), right);
    answer = UnitConversionEnum.checkUnits(expression);
    assertEquals("320 min" + Power.createPower(2), answer.toString());
  }

  @Test
  void minXhr()
  {
    // addition
    Operand left = new Operand(new Digit(s160), new SingleUnit(min));
    Operand right = new Operand(new Digit(one20), new SingleUnit(hr));
    CompositeExpression expression = new CompositeExpression(left, new Addition(), right);
    Operand answer = UnitConversionEnum.checkUnits(expression);
    assertEquals("7360 min", answer.toString());
    // multiplication
    expression = new CompositeExpression(left, new Multiplication(), right);
    answer = UnitConversionEnum.checkUnits(expression);
    assertEquals("1152000 min" + Power.createPower(2), answer.toString());
  }

  @Test
  void hrXsec()
  {
    // addition
    Operand left = new Operand(new Digit(s160), new SingleUnit(hr));
    Operand right = new Operand(new Digit("1200"), new SingleUnit(sec));
    CompositeExpression expression = new CompositeExpression(left, new Addition(), right);
    Operand answer = UnitConversionEnum.checkUnits(expression);
    assertEquals("160.33333 hr", answer.toString());
    // multiplication
    expression = new CompositeExpression(left, new Multiplication(), right);
    answer = UnitConversionEnum.checkUnits(expression);
    assertEquals("53.3328 hr" + Power.createPower(2), answer.toString());
  }

  @Test
  void hrXmin()
  {
    // addition
    Operand left = new Operand(new Digit(s160), new SingleUnit(hr));
    Operand right = new Operand(new Digit("12000"), new SingleUnit(min));
    CompositeExpression expression = new CompositeExpression(left, new Addition(), right);
    Operand answer = UnitConversionEnum.checkUnits(expression);
    assertEquals("360 hr", answer.toString());
    // multiplication
    expression = new CompositeExpression(left, new Multiplication(), right);
    answer = UnitConversionEnum.checkUnits(expression);
    assertEquals("32000 hr" + Power.createPower(2), answer.toString());
  }

  @Test
  void ozXg()
  {
    // addition
    Operand left = new Operand(new Digit(s160), new SingleUnit(oz));
    Operand right = new Operand(new Digit(one20), new SingleUnit(g));
    CompositeExpression expression = new CompositeExpression(left, new Addition(), right);
    Operand answer = UnitConversionEnum.checkUnits(expression);
    assertEquals("164.23288 oz", answer.toString());
    // multiplication
    expression = new CompositeExpression(left, new Multiplication(), right);
    answer = UnitConversionEnum.checkUnits(expression);
    assertEquals("677.2608 oz" + Power.createPower(2), answer.toString());
  }

  @Test
  void ozXlb()
  {
    // addition
    Operand left = new Operand(new Digit(s160), new SingleUnit(oz));
    Operand right = new Operand(new Digit(one20), new SingleUnit(lb));
    CompositeExpression expression = new CompositeExpression(left, new Addition(), right);
    Operand answer = UnitConversionEnum.checkUnits(expression);
    assertEquals("2080 oz", answer.toString());
    // multiplication
    expression = new CompositeExpression(left, new Multiplication(), right);
    answer = UnitConversionEnum.checkUnits(expression);
    assertEquals("307200 oz" + Power.createPower(2), answer.toString());
  }

  @Test
  void ozXton()
  {
    // addition
    Operand left = new Operand(new Digit(s160), new SingleUnit(oz));
    Operand right = new Operand(new Digit("122"), new SingleUnit(ton));
    CompositeExpression expression = new CompositeExpression(left, new Addition(), right);
    Operand answer = UnitConversionEnum.checkUnits(expression);
    assertEquals("3904160 oz", answer.toString());
    // multiplication
    expression = new CompositeExpression(left, new Multiplication(), right);
    answer = UnitConversionEnum.checkUnits(expression);
    assertEquals("624640000 oz" + Power.createPower(2), answer.toString());
  }

  @Test
  void ozXkg()
  {
    // addition
    Operand left = new Operand(new Digit(s160), new SingleUnit(oz));
    Operand right = new Operand(new Digit("12"), new SingleUnit(kg));
    CompositeExpression expression = new CompositeExpression(left, new Addition(), right);
    Operand answer = UnitConversionEnum.checkUnits(expression);
    assertEquals("583.288 oz", answer.toString());
    // multiplication
    expression = new CompositeExpression(left, new Multiplication(), right);
    answer = UnitConversionEnum.checkUnits(expression);
    assertEquals("67726.08 oz" + Power.createPower(2), answer.toString());
  }

  @Test
  void lbXoz()
  {
    // addition
    Operand left = new Operand(new Digit(s160), new SingleUnit(lb));
    Operand right = new Operand(new Digit(sixT), new SingleUnit(oz));
    CompositeExpression expression = new CompositeExpression(left, new Addition(), right);
    Operand answer = UnitConversionEnum.checkUnits(expression);
    assertEquals("161 lb", answer.toString());
    // multiplication
    expression = new CompositeExpression(left, new Multiplication(), right);
    answer = UnitConversionEnum.checkUnits(expression);
    assertEquals("160 lb" + Power.createPower(2), answer.toString());
  }

  @Test
  void lbXton()
  {
    // addition
    Operand left = new Operand(new Digit(s160), new SingleUnit(lb));
    Operand right = new Operand(new Digit(sixT), new SingleUnit(ton));
    CompositeExpression expression = new CompositeExpression(left, new Addition(), right);
    Operand answer = UnitConversionEnum.checkUnits(expression);
    assertEquals("32160 lb", answer.toString());
    // multiplication
    expression = new CompositeExpression(left, new Multiplication(), right);
    answer = UnitConversionEnum.checkUnits(expression);
    assertEquals("5120000 lb" + Power.createPower(2), answer.toString());
  }

  @Test
  void lbXg()
  {
    // addition
    Operand left = new Operand(new Digit(s160), new SingleUnit(lb));
    Operand right = new Operand(new Digit(oneK), new SingleUnit(g));
    CompositeExpression expression = new CompositeExpression(left, new Addition(), right);
    Operand answer = UnitConversionEnum.checkUnits(expression);
    assertEquals("162.20462 lb", answer.toString());
    // multiplication
    expression = new CompositeExpression(left, new Multiplication(), right);
    answer = UnitConversionEnum.checkUnits(expression);
    assertEquals("352.7392 lb" + Power.createPower(2), answer.toString());
  }

  @Test
  void lbXkg()
  {
    // addition
    Operand left = new Operand(new Digit(s160), new SingleUnit(lb));
    Operand right = new Operand(new Digit(oneK), new SingleUnit(kg));
    CompositeExpression expression = new CompositeExpression(left, new Addition(), right);
    Operand answer = UnitConversionEnum.checkUnits(expression);
    assertEquals("2364.625 lb", answer.toString());
    // multiplication
    expression = new CompositeExpression(left, new Multiplication(), right);
    answer = UnitConversionEnum.checkUnits(expression);
    assertEquals("352740 lb" + Power.createPower(2), answer.toString());
  }

  @Test
  void tonXoz()
  {
    // addition
    Operand left = new Operand(new Digit(s160), new SingleUnit(ton));
    Operand right = new Operand(new Digit(oneHundredK), new SingleUnit(oz));
    CompositeExpression expression = new CompositeExpression(left, new Addition(), right);
    Operand answer = UnitConversionEnum.checkUnits(expression);
    assertEquals("163.125 ton", answer.toString());
    // multiplication
    expression = new CompositeExpression(left, new Multiplication(), right);
    answer = UnitConversionEnum.checkUnits(expression);
    assertEquals("500 ton" + Power.createPower(2), answer.toString());
  }

  @Test
  void tonXg()
  {
    // addition
    Operand left = new Operand(new Digit(s160), new SingleUnit(ton));
    Operand right = new Operand(new Digit(oneMil), new SingleUnit(g));
    CompositeExpression expression = new CompositeExpression(left, new Addition(), right);
    Operand answer = UnitConversionEnum.checkUnits(expression);
    assertEquals("161.10231 ton", answer.toString());
    // multiplication
    expression = new CompositeExpression(left, new Multiplication(), right);
    answer = UnitConversionEnum.checkUnits(expression);
    assertEquals("176.3696 ton" + Power.createPower(2), answer.toString());
  }

  @Test
  void tonXlb()
  {
    // addition
    Operand left = new Operand(new Digit(s160), new SingleUnit(ton));
    Operand right = new Operand(new Digit(oneMil), new SingleUnit(lb));
    CompositeExpression expression = new CompositeExpression(left, new Addition(), right);
    Operand answer = UnitConversionEnum.checkUnits(expression);
    assertEquals("660 ton", answer.toString());
    // multiplication
    expression = new CompositeExpression(left, new Multiplication(), right);
    answer = UnitConversionEnum.checkUnits(expression);
    assertEquals("80000 ton" + Power.createPower(2), answer.toString());
  }

  @Test
  void tonXkg()
  {
    // addition
    Operand left = new Operand(new Digit(s160), new SingleUnit(ton));
    Operand right = new Operand(new Digit(oneMil), new SingleUnit(kg));
    CompositeExpression expression = new CompositeExpression(left, new Addition(), right);
    Operand answer = UnitConversionEnum.checkUnits(expression);
    assertEquals("1262.3125 ton", answer.toString());
    // multiplication
    expression = new CompositeExpression(left, new Multiplication(), right);
    answer = UnitConversionEnum.checkUnits(expression);
    assertEquals("176370 ton" + Power.createPower(2), answer.toString());
  }

  @Test
  void gXoz()
  {
    // addition
    Operand left = new Operand(new Digit(s160), new SingleUnit(g));
    Operand right = new Operand(new Digit(oneK), new SingleUnit(oz));
    CompositeExpression expression = new CompositeExpression(left, new Addition(), right);
    Operand answer = UnitConversionEnum.checkUnits(expression);
    assertEquals("28509.49254 g", answer.toString());
    // multiplication
    expression = new CompositeExpression(left, new Multiplication(), right);
    answer = UnitConversionEnum.checkUnits(expression);
    assertEquals("4535918.8064 g" + Power.createPower(2), answer.toString());
  }

  @Test
  void gXton()
  {
    // addition
    Operand left = new Operand(new Digit(s160), new SingleUnit(g));
    Operand right = new Operand(new Digit(one), new SingleUnit(ton));
    CompositeExpression expression = new CompositeExpression(left, new Addition(), right);
    Operand answer = UnitConversionEnum.checkUnits(expression);
    assertEquals("907343.76141 g", answer.toString());
    // multiplication
    expression = new CompositeExpression(left, new Multiplication(), right);
    answer = UnitConversionEnum.checkUnits(expression);
    assertEquals("145149401.8256 g" + Power.createPower(2), answer.toString());
  }

  @Test
  void gXlb()
  {
    // addition
    Operand left = new Operand(new Digit(s160), new SingleUnit(g));
    Operand right = new Operand(new Digit(one), new SingleUnit(lb));
    CompositeExpression expression = new CompositeExpression(left, new Addition(), right);
    Operand answer = UnitConversionEnum.checkUnits(expression);
    assertEquals("613.59188 g", answer.toString());
    // multiplication
    expression = new CompositeExpression(left, new Multiplication(), right);
    answer = UnitConversionEnum.checkUnits(expression);
    assertEquals("72574.7008 g" + Power.createPower(2), answer.toString());
  }

  @Test
  void gXkg()
  {
    // addition
    Operand left = new Operand(new Digit(s160), new SingleUnit(g));
    Operand right = new Operand(new Digit(one), new SingleUnit(kg));
    CompositeExpression expression = new CompositeExpression(left, new Addition(), right);
    Operand answer = UnitConversionEnum.checkUnits(expression);
    assertEquals("1160 g", answer.toString());
    // multiplication
    expression = new CompositeExpression(left, new Multiplication(), right);
    answer = UnitConversionEnum.checkUnits(expression);
    assertEquals("160000 g" + Power.createPower(2), answer.toString());
  }

  @Test
  void kgXoz()
  {
    // addition
    Operand left = new Operand(new Digit(s160), new SingleUnit(kg));
    Operand right = new Operand(new Digit(oneK), new SingleUnit(oz));
    CompositeExpression expression = new CompositeExpression(left, new Addition(), right);
    Operand answer = UnitConversionEnum.checkUnits(expression);
    assertEquals("188.34949 kg", answer.toString());
    // multiplication
    expression = new CompositeExpression(left, new Multiplication(), right);
    answer = UnitConversionEnum.checkUnits(expression);
    assertEquals("4535.9184 kg" + Power.createPower(2), answer.toString());
  }

  @Test
  void kgXton()
  {
    // addition
    Operand left = new Operand(new Digit(s160), new SingleUnit(kg));
    Operand right = new Operand(new Digit(one), new SingleUnit(ton));
    CompositeExpression expression = new CompositeExpression(left, new Addition(), right);
    Operand answer = UnitConversionEnum.checkUnits(expression);
    assertEquals("1067.18376 kg", answer.toString());
    // multiplication
    expression = new CompositeExpression(left, new Multiplication(), right);
    answer = UnitConversionEnum.checkUnits(expression);
    assertEquals("145149.4016 kg" + Power.createPower(2), answer.toString());
  }

  @Test
  void kgXlb()
  {
    // addition
    Operand left = new Operand(new Digit(s160), new SingleUnit(kg));
    Operand right = new Operand(new Digit(four), new SingleUnit(lb));
    CompositeExpression expression = new CompositeExpression(left, new Addition(), right);
    Operand answer = UnitConversionEnum.checkUnits(expression);
    assertEquals("161.81437 kg", answer.toString());
    // multiplication
    expression = new CompositeExpression(left, new Multiplication(), right);
    answer = UnitConversionEnum.checkUnits(expression);
    assertEquals("290.2992 kg" + Power.createPower(2), answer.toString());
  }

  @Test
  void kgXg()
  {
    // addition
    Operand left = new Operand(new Digit(s160), new SingleUnit(kg));
    Operand right = new Operand(new Digit(oneK), new SingleUnit(g));
    CompositeExpression expression = new CompositeExpression(left, new Addition(), right);
    Operand answer = UnitConversionEnum.checkUnits(expression);
    assertEquals("161 kg", answer.toString());
    // multiplication
    expression = new CompositeExpression(left, new Multiplication(), right);
    answer = UnitConversionEnum.checkUnits(expression);
    assertEquals("160 kg" + Power.createPower(2), answer.toString());
  }

  @Test
  void moneyXc()
  {
    // addition
    Operand left = new Operand(new Digit(s160), new SingleUnit(money));
    Operand right = new Operand(new Digit(oneK), new SingleUnit(c));
    CompositeExpression expression = new CompositeExpression(left, new Addition(), right);
    Operand answer = UnitConversionEnum.checkUnits(expression);
    assertEquals("170 $", answer.toString());
    // multiplication
    expression = new CompositeExpression(left, new Multiplication(), right);
    answer = UnitConversionEnum.checkUnits(expression);
    assertEquals("1600 $" + Power.createPower(2), answer.toString());
  }

  @Test
  void cXmoney()
  {
    // addition
    Operand left = new Operand(new Digit(s160), new SingleUnit(c));
    Operand right = new Operand(new Digit(one), new SingleUnit(money));
    CompositeExpression expression = new CompositeExpression(left, new Addition(), right);
    Operand answer = UnitConversionEnum.checkUnits(expression);
    assertEquals("260 c", answer.toString());
    // multiplication
    expression = new CompositeExpression(left, new Multiplication(), right);
    answer = UnitConversionEnum.checkUnits(expression);
    assertEquals("16000 c" + Power.createPower(2), answer.toString());
  }

  @Test
  void ptXqt()
  {
    // addition
    Operand left = new Operand(new Digit(s160), new SingleUnit(pt));
    Operand right = new Operand(new Digit(four), new SingleUnit(qt));
    CompositeExpression expression = new CompositeExpression(left, new Addition(), right);
    Operand answer = UnitConversionEnum.checkUnits(expression);
    assertEquals("168 pt", answer.toString());
    // multiplication
    expression = new CompositeExpression(left, new Multiplication(), right);
    answer = UnitConversionEnum.checkUnits(expression);
    assertEquals("1280 pt" + Power.createPower(2), answer.toString());
  }

  @Test
  void ptXgal()
  {
    // addition
    Operand left = new Operand(new Digit(s160), new SingleUnit(pt));
    Operand right = new Operand(new Digit("2"), new SingleUnit(gal));
    CompositeExpression expression = new CompositeExpression(left, new Addition(), right);
    Operand answer = UnitConversionEnum.checkUnits(expression);
    assertEquals("179.2152 pt", answer.toString());
    // multiplication
    expression = new CompositeExpression(left, new Multiplication(), right);
    answer = UnitConversionEnum.checkUnits(expression);
    assertEquals("3074.432 pt" + Power.createPower(2), answer.toString());
  }

  @Test
  void ptXcc()
  {
    // addition
    Operand left = new Operand(new Digit(s160), new SingleUnit(pt));
    Operand right = new Operand(new Digit(twoK), new SingleUnit(cc));
    CompositeExpression expression = new CompositeExpression(left, new Addition(), right);
    Operand answer = UnitConversionEnum.checkUnits(expression);
    assertEquals("163.5195 pt", answer.toString());
    // multiplication
    expression = new CompositeExpression(left, new Multiplication(), right);
    answer = UnitConversionEnum.checkUnits(expression);
    assertEquals("563.12 pt" + Power.createPower(2), answer.toString());
  }

  @Test
  void ptXl()
  {
    // addition
    Operand left = new Operand(new Digit(s160), new SingleUnit(pt));
    Operand right = new Operand(new Digit(twoK), new SingleUnit(liters));
    CompositeExpression expression = new CompositeExpression(left, new Addition(), right);
    Operand answer = UnitConversionEnum.checkUnits(expression);
    assertEquals("4386.76 pt", answer.toString());
    // multiplication
    expression = new CompositeExpression(left, new Multiplication(), right);
    answer = UnitConversionEnum.checkUnits(expression);
    assertEquals("676281.6 pt" + Power.createPower(2), answer.toString());
  }

  @Test
  void qtXpt()
  {
    // addition
    Operand left = new Operand(new Digit(s160), new SingleUnit(qt));
    Operand right = new Operand(new Digit(twoK), new SingleUnit(pt));
    CompositeExpression expression = new CompositeExpression(left, new Addition(), right);
    Operand answer = UnitConversionEnum.checkUnits(expression);
    assertEquals("1160 qt", answer.toString());
    // multiplication
    expression = new CompositeExpression(left, new Multiplication(), right);
    answer = UnitConversionEnum.checkUnits(expression);
    assertEquals("160000 qt" + Power.createPower(2), answer.toString());
  }

  @Test
  void qtXgal()
  {
    // addition
    Operand left = new Operand(new Digit(s160), new SingleUnit(qt));
    Operand right = new Operand(new Digit(twoK), new SingleUnit(gal));
    CompositeExpression expression = new CompositeExpression(left, new Addition(), right);
    Operand answer = UnitConversionEnum.checkUnits(expression);
    assertEquals("8160 qt", answer.toString());
    // multiplication
    expression = new CompositeExpression(left, new Multiplication(), right);
    answer = UnitConversionEnum.checkUnits(expression);
    assertEquals("1280000 qt" + Power.createPower(2), answer.toString());
  }

  @Test
  void qtXcc()
  {
    // addition
    Operand left = new Operand(new Digit(s160), new SingleUnit(qt));
    Operand right = new Operand(new Digit(twoK), new SingleUnit(cc));
    CompositeExpression expression = new CompositeExpression(left, new Addition(), right);
    Operand answer = UnitConversionEnum.checkUnits(expression);
    assertEquals("162.11338 qt", answer.toString());
    // multiplication
    expression = new CompositeExpression(left, new Multiplication(), right);
    answer = UnitConversionEnum.checkUnits(expression);
    assertEquals("338.1408 qt" + Power.createPower(2), answer.toString());
  }

  @Test
  void qtXl()
  {
    // addition
    Operand left = new Operand(new Digit(s160), new SingleUnit(qt));
    Operand right = new Operand(new Digit(twoK), new SingleUnit(liters));
    CompositeExpression expression = new CompositeExpression(left, new Addition(), right);
    Operand answer = UnitConversionEnum.checkUnits(expression);
    assertEquals("2273.38 qt", answer.toString());
    // multiplication
    expression = new CompositeExpression(left, new Multiplication(), right);
    answer = UnitConversionEnum.checkUnits(expression);
    assertEquals("338140.8 qt" + Power.createPower(2), answer.toString());
  }

  @Test
  void galXpt()
  {
    // addition
    Operand left = new Operand(new Digit(s160), new SingleUnit(gal));
    Operand right = new Operand(new Digit(twoK), new SingleUnit(pt));
    CompositeExpression expression = new CompositeExpression(left, new Addition(), right);
    Operand answer = UnitConversionEnum.checkUnits(expression);
    assertEquals("410 gal", answer.toString());
    // multiplication
    expression = new CompositeExpression(left, new Multiplication(), right);
    answer = UnitConversionEnum.checkUnits(expression);
    assertEquals("40000 gal" + Power.createPower(2), answer.toString());
  }

  @Test
  void galXqt()
  {
    // addition
    Operand left = new Operand(new Digit(s160), new SingleUnit(gal));
    Operand right = new Operand(new Digit(twoK), new SingleUnit(qt));
    CompositeExpression expression = new CompositeExpression(left, new Addition(), right);
    Operand answer = UnitConversionEnum.checkUnits(expression);
    assertEquals("660 gal", answer.toString());
    // multiplication
    expression = new CompositeExpression(left, new Multiplication(), right);
    answer = UnitConversionEnum.checkUnits(expression);
    assertEquals("80000 gal" + Power.createPower(2), answer.toString());
  }

  @Test
  void galXcc()
  {
    // addition
    Operand left = new Operand(new Digit(s160), new SingleUnit(gal));
    Operand right = new Operand(new Digit(twoK), new SingleUnit(cc));
    CompositeExpression expression = new CompositeExpression(left, new Addition(), right);
    Operand answer = UnitConversionEnum.checkUnits(expression);
    assertEquals("160.52834 gal", answer.toString());
    // multiplication
    expression = new CompositeExpression(left, new Multiplication(), right);
    answer = UnitConversionEnum.checkUnits(expression);
    assertEquals("84.5344 gal" + Power.createPower(2), answer.toString());
  }

  @Test
  void galXl()
  {
    // addition
    Operand left = new Operand(new Digit(s160), new SingleUnit(gal));
    Operand right = new Operand(new Digit(twoK), new SingleUnit(liters));
    CompositeExpression expression = new CompositeExpression(left, new Addition(), right);
    Operand answer = UnitConversionEnum.checkUnits(expression);
    assertEquals("688.34435 gal", answer.toString());
    // multiplication
    expression = new CompositeExpression(left, new Multiplication(), right);
    answer = UnitConversionEnum.checkUnits(expression);
    assertEquals("84535.096 gal" + Power.createPower(2), answer.toString());
  }

  @Test
  void ccXpt()
  {
    // addition
    Operand left = new Operand(new Digit(s160), new SingleUnit(cc));
    Operand right = new Operand(new Digit(twoK), new SingleUnit(pt));
    CompositeExpression expression = new CompositeExpression(left, new Addition(), right);
    Operand answer = UnitConversionEnum.checkUnits(expression);
    assertEquals("946512 cc", answer.toString());
    // multiplication
    expression = new CompositeExpression(left, new Multiplication(), right);
    answer = UnitConversionEnum.checkUnits(expression);
    assertEquals("151416320 cc" + Power.createPower(2), answer.toString());
  }

  @Test
  void ccXqt()
  {
    // addition
    Operand left = new Operand(new Digit(s160), new SingleUnit(cc));
    Operand right = new Operand(new Digit(twoK), new SingleUnit(qt));
    CompositeExpression expression = new CompositeExpression(left, new Addition(), right);
    Operand answer = UnitConversionEnum.checkUnits(expression);
    assertEquals("1892866 cc", answer.toString());
    // multiplication
    expression = new CompositeExpression(left, new Multiplication(), right);
    answer = UnitConversionEnum.checkUnits(expression);
    assertEquals("302832960 cc" + Power.createPower(2), answer.toString());
  }

  @Test
  void ccXgal()
  {
    // addition
    Operand left = new Operand(new Digit(s160), new SingleUnit(cc));
    Operand right = new Operand(new Digit(twoK), new SingleUnit(gal));
    CompositeExpression expression = new CompositeExpression(left, new Addition(), right);
    Operand answer = UnitConversionEnum.checkUnits(expression);
    assertEquals("7570980 cc", answer.toString());
    // multiplication
    expression = new CompositeExpression(left, new Multiplication(), right);
    answer = UnitConversionEnum.checkUnits(expression);
    assertEquals("1211331200 cc" + Power.createPower(2), answer.toString());
  }

  @Test
  void ccXl()
  {
    // addition
    Operand left = new Operand(new Digit(s160), new SingleUnit(cc));
    Operand right = new Operand(new Digit(twoK), new SingleUnit(liters));
    CompositeExpression expression = new CompositeExpression(left, new Addition(), right);
    Operand answer = UnitConversionEnum.checkUnits(expression);
    assertEquals("2000160 cc", answer.toString());
    // multiplication
    expression = new CompositeExpression(left, new Multiplication(), right);
    answer = UnitConversionEnum.checkUnits(expression);
    assertEquals("320000000 cc" + Power.createPower(2), answer.toString());
  }

  @Test
  void lXpt()
  {
    // addition
    Operand left = new Operand(new Digit(s160), new SingleUnit(liters));
    Operand right = new Operand(new Digit(twoK), new SingleUnit(pt));
    CompositeExpression expression = new CompositeExpression(left, new Addition(), right);
    Operand answer = UnitConversionEnum.checkUnits(expression);
    assertEquals("1106.35295 l", answer.toString());
    // multiplication
    expression = new CompositeExpression(left, new Multiplication(), right);
    answer = UnitConversionEnum.checkUnits(expression);
    assertEquals("151416.47136 l" + Power.createPower(2), answer.toString());
  }

  @Test
  void lXqt()
  {
    // addition
    Operand left = new Operand(new Digit(s160), new SingleUnit(liters));
    Operand right = new Operand(new Digit(twoK), new SingleUnit(qt));
    CompositeExpression expression = new CompositeExpression(left, new Addition(), right);
    Operand answer = UnitConversionEnum.checkUnits(expression);
    assertEquals("2052.70268 l", answer.toString());
    // multiplication
    expression = new CompositeExpression(left, new Multiplication(), right);
    answer = UnitConversionEnum.checkUnits(expression);
    assertEquals("302832.4288 l" + Power.createPower(2), answer.toString());
  }

  @Test
  void lXgal()
  {
    // addition
    Operand left = new Operand(new Digit(s160), new SingleUnit(liters));
    Operand right = new Operand(new Digit(twoK), new SingleUnit(gal));
    CompositeExpression expression = new CompositeExpression(left, new Addition(), right);
    Operand answer = UnitConversionEnum.checkUnits(expression);
    assertEquals("7730.82 l", answer.toString());
    // multiplication
    expression = new CompositeExpression(left, new Multiplication(), right);
    answer = UnitConversionEnum.checkUnits(expression);
    assertEquals("1211331.2 l" + Power.createPower(2), answer.toString());
  }

  @Test
  void lXcc()
  {
    // addition
    Operand left = new Operand(new Digit(s160), new SingleUnit(liters));
    Operand right = new Operand(new Digit(twoK), new SingleUnit(cc));
    CompositeExpression expression = new CompositeExpression(left, new Addition(), right);
    Operand answer = UnitConversionEnum.checkUnits(expression);
    assertEquals("162 l", answer.toString());
    // multiplication
    expression = new CompositeExpression(left, new Multiplication(), right);
    answer = UnitConversionEnum.checkUnits(expression);
    assertEquals("320 l" + Power.createPower(2), answer.toString());
  }

  @Test
  void yrXmon()
  {
    // addition
    Operand left = new Operand(new Digit(s160), new SingleUnit(year));
    Operand right = new Operand(new Digit(twoK), new SingleUnit(mon));
    CompositeExpression expression = new CompositeExpression(left, new Addition(), right);
    Operand answer = UnitConversionEnum.checkUnits(expression);
    assertEquals("326.66667 yr", answer.toString());
    // multiplication
    expression = new CompositeExpression(left, new Multiplication(), right);
    answer = UnitConversionEnum.checkUnits(expression);
    assertEquals("26666.6672 yr" + Power.createPower(2), answer.toString());
  }

  @Test
  void monXyr()
  {
    // addition
    Operand left = new Operand(new Digit(s160), new SingleUnit(mon));
    Operand right = new Operand(new Digit("200"), new SingleUnit(year));
    CompositeExpression expression = new CompositeExpression(left, new Addition(), right);
    Operand answer = UnitConversionEnum.checkUnits(expression);
    assertEquals("2560 mon", answer.toString());
    // multiplication
    expression = new CompositeExpression(left, new Multiplication(), right);
    answer = UnitConversionEnum.checkUnits(expression);
    assertEquals("384000 mon" + Power.createPower(2), answer.toString());
  }

  @Test
  void yrXmin()
  {
    // addition
    Operand left = new Operand(new Digit(s160), new SingleUnit(year));
    Operand right = new Operand(new Digit("525600"), new SingleUnit(min));
    CompositeExpression expression = new CompositeExpression(left, new Addition(), right);
    Operand answer = UnitConversionEnum.checkUnits(expression);
    assertEquals(y161, answer.toString());
    // multiplication
    expression = new CompositeExpression(left, new Multiplication(), right);
    answer = UnitConversionEnum.checkUnits(expression);
    assertEquals(y160 + Power.createPower(2), answer.toString());
  }

  @Test
  void yrXsec()
  {
    // addition
    Operand left = new Operand(new Digit(s160), new SingleUnit(year));
    Operand right = new Operand(new Digit(y), new SingleUnit(sec));
    CompositeExpression expression = new CompositeExpression(left, new Addition(), right);
    Operand answer = UnitConversionEnum.checkUnits(expression);
    assertEquals(y161, answer.toString());
    // multiplication
    expression = new CompositeExpression(left, new Multiplication(), right);
    answer = UnitConversionEnum.checkUnits(expression);
    assertEquals(y160 + Power.createPower(2), answer.toString());
  }

  @Test
  void yrXday()
  {
    // addition
    Operand left = new Operand(new Digit(s160), new SingleUnit(year));
    Operand right = new Operand(new Digit(y), new SingleUnit(day));
    CompositeExpression expression = new CompositeExpression(left, new Addition(), right);
    Operand answer = UnitConversionEnum.checkUnits(expression);
    assertEquals("86560 yr", answer.toString());
    // multiplication
    expression = new CompositeExpression(left, new Multiplication(), right);
    answer = UnitConversionEnum.checkUnits(expression);
    assertEquals("13824000 yr" + Power.createPower(2), answer.toString());
  }

  @Test
  void monXday()
  {
    // addition
    Operand left = new Operand(new Digit(s160), new SingleUnit(mon));
    Operand right = new Operand(new Digit("30"), new SingleUnit(day));
    CompositeExpression expression = new CompositeExpression(left, new Addition(), right);
    Operand answer = UnitConversionEnum.checkUnits(expression);
    assertEquals(s161m, answer.toString());
    // multiplication
    expression = new CompositeExpression(left, new Multiplication(), right);
    answer = UnitConversionEnum.checkUnits(expression);
    assertEquals(s160m + Power.createPower(2), answer.toString());
  }

  @Test
  void monXmin()
  {
    // addition
    Operand left = new Operand(new Digit(s160), new SingleUnit(mon));
    Operand right = new Operand(new Digit("43200"), new SingleUnit(min));
    CompositeExpression expression = new CompositeExpression(left, new Addition(), right);
    Operand answer = UnitConversionEnum.checkUnits(expression);
    assertEquals(s161m, answer.toString());
    // multiplication
    expression = new CompositeExpression(left, new Multiplication(), right);
    answer = UnitConversionEnum.checkUnits(expression);
    assertEquals(s160m + Power.createPower(2), answer.toString());
  }

  @Test
  void monXsec()
  {
    // addition
    Operand left = new Operand(new Digit(s160), new SingleUnit(mon));
    Operand right = new Operand(new Digit("2592000"), new SingleUnit(sec));
    CompositeExpression expression = new CompositeExpression(left, new Addition(), right);
    Operand answer = UnitConversionEnum.checkUnits(expression);
    assertEquals(s161m, answer.toString());
    // multiplication
    expression = new CompositeExpression(left, new Multiplication(), right);
    answer = UnitConversionEnum.checkUnits(expression);
    assertEquals(s160m + Power.createPower(2), answer.toString());
  }

  @Test
  void dayXsec()
  {
    // addition
    Operand left = new Operand(new Digit(s160), new SingleUnit(day));
    Operand right = new Operand(new Digit("86400"), new SingleUnit(sec));
    CompositeExpression expression = new CompositeExpression(left, new Addition(), right);
    Operand answer = UnitConversionEnum.checkUnits(expression);
    assertEquals(answer161Days, answer.toString());
    // multiplication
    expression = new CompositeExpression(left, new Multiplication(), right);
    answer = UnitConversionEnum.checkUnits(expression);
    assertEquals(answer160Days + Power.createPower(2), answer.toString());
  }

  @Test
  void dayXmin()
  {
    // addition
    Operand left = new Operand(new Digit(s160), new SingleUnit(day));
    Operand right = new Operand(new Digit("1440"), new SingleUnit(min));
    CompositeExpression expression = new CompositeExpression(left, new Addition(), right);
    Operand answer = UnitConversionEnum.checkUnits(expression);
    assertEquals(answer161Days, answer.toString());
    // multiplication
    expression = new CompositeExpression(left, new Multiplication(), right);
    answer = UnitConversionEnum.checkUnits(expression);
    assertEquals(answer160Days + Power.createPower(2), answer.toString());
  }

  @Test
  void dayXhr()
  {
    // addition
    Operand left = new Operand(new Digit(s160), new SingleUnit(day));
    Operand right = new Operand(new Digit("24"), new SingleUnit(hr));
    CompositeExpression expression = new CompositeExpression(left, new Addition(), right);
    Operand answer = UnitConversionEnum.checkUnits(expression);
    assertEquals(answer161Days, answer.toString());
    // multiplication
    expression = new CompositeExpression(left, new Multiplication(), right);
    answer = UnitConversionEnum.checkUnits(expression);
    assertEquals(answer160Days + Power.createPower(2), answer.toString());
  }

  @Test
  void hrXday()
  {
    // addition
    Operand left = new Operand(new Digit(s160), new SingleUnit(hr));
    Operand right = new Operand(new Digit(one), new SingleUnit(day));
    CompositeExpression expression = new CompositeExpression(left, new Addition(), right);
    Operand answer = UnitConversionEnum.checkUnits(expression);
    assertEquals("184 hr", answer.toString());
    // multiplication
    expression = new CompositeExpression(left, new Multiplication(), right);
    answer = UnitConversionEnum.checkUnits(expression);
    assertEquals("3840 hr" + Power.createPower(2), answer.toString());
  }

  @Test
  void secXyr()
  {
    // addition
    Operand left = new Operand(new Digit(s160), new SingleUnit(sec));
    Operand right = new Operand(new Digit(one), new SingleUnit(year));
    CompositeExpression expression = new CompositeExpression(left, new Addition(), right);
    Operand answer = UnitConversionEnum.checkUnits(expression);
    assertEquals("31536160 sec", answer.toString());
    // multiplication
    expression = new CompositeExpression(left, new Multiplication(), right);
    answer = UnitConversionEnum.checkUnits(expression);
    assertEquals("5045760000 sec" + Power.createPower(2), answer.toString());
  }

  @Test
  void secXmon()
  {
    // addition
    Operand left = new Operand(new Digit(s160), new SingleUnit(sec));
    Operand right = new Operand(new Digit(one), new SingleUnit(mon));
    CompositeExpression expression = new CompositeExpression(left, new Addition(), right);
    Operand answer = UnitConversionEnum.checkUnits(expression);
    assertEquals("2592160 sec", answer.toString());
    // multiplication
    expression = new CompositeExpression(left, new Multiplication(), right);
    answer = UnitConversionEnum.checkUnits(expression);
    assertEquals("414720000 sec" + Power.createPower(2), answer.toString());
  }

  @Test
  void secXday()
  {
    // addition
    Operand left = new Operand(new Digit(s160), new SingleUnit(sec));
    Operand right = new Operand(new Digit(one), new SingleUnit(day));
    CompositeExpression expression = new CompositeExpression(left, new Addition(), right);
    Operand answer = UnitConversionEnum.checkUnits(expression);
    assertEquals("86560 sec", answer.toString());
    // multiplication
    expression = new CompositeExpression(left, new Multiplication(), right);
    answer = UnitConversionEnum.checkUnits(expression);
    assertEquals("13824000 sec" + Power.createPower(2), answer.toString());
  }

  @Test
  void minXday()
  {
    // addition
    Operand left = new Operand(new Digit(s160), new SingleUnit(min));
    Operand right = new Operand(new Digit(one), new SingleUnit(day));
    CompositeExpression expression = new CompositeExpression(left, new Addition(), right);
    Operand answer = UnitConversionEnum.checkUnits(expression);
    assertEquals("1600 min", answer.toString());
    // multiplication
    expression = new CompositeExpression(left, new Multiplication(), right);
    answer = UnitConversionEnum.checkUnits(expression);
    assertEquals("230400 min" + Power.createPower(2), answer.toString());
  }

  @Test
  void minXmon()
  {
    // addition
    Operand left = new Operand(new Digit(s160), new SingleUnit(min));
    Operand right = new Operand(new Digit(one), new SingleUnit(mon));
    CompositeExpression expression = new CompositeExpression(left, new Addition(), right);
    Operand answer = UnitConversionEnum.checkUnits(expression);
    assertEquals("43360 min", answer.toString());
    // multiplication
    expression = new CompositeExpression(left, new Multiplication(), right);
    answer = UnitConversionEnum.checkUnits(expression);
    assertEquals("6912000 min" + Power.createPower(2), answer.toString());
  }

  @Test
  void minXyr()
  {
    // addition
    Operand left = new Operand(new Digit(s160), new SingleUnit(min));
    Operand right = new Operand(new Digit(one), new SingleUnit(year));
    CompositeExpression expression = new CompositeExpression(left, new Addition(), right);
    Operand answer = UnitConversionEnum.checkUnits(expression);
    assertEquals("525760 min", answer.toString());
    // multiplication
    expression = new CompositeExpression(left, new Multiplication(), right);
    answer = UnitConversionEnum.checkUnits(expression);
    assertEquals("84096000 min" + Power.createPower(2), answer.toString());
  }

  @Test
  void testIsEnum()
  {
    Unit u = new SingleUnit(in);
    assertTrue(UnitConversionEnum.isEnum(u));
    u = new SingleUnit(ft);
    assertTrue(UnitConversionEnum.isEnum(u));
    u = new SingleUnit(m);
    assertTrue(UnitConversionEnum.isEnum(u));
    u = new SingleUnit(mile);
    assertTrue(UnitConversionEnum.isEnum(u));
    u = new SingleUnit(sec);
    assertTrue(UnitConversionEnum.isEnum(u));
    u = new SingleUnit(min);
    assertTrue(UnitConversionEnum.isEnum(u));
  }

}
