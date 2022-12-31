package testing;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import data.SingleUnit;
import data.Unit;
import utilities.Power;

class PowerTest
{
  private static final String EMPTY = "";
  
  @Test
  void testCreatePower()
  {
    String str = Power.createPower(0);
    assertEquals(Power.ZERO.toString(), str);
    str = Power.createPower(193);
    assertEquals(EMPTY + Power.ONE + Power.NINE + Power.THREE, str);
    str = Power.createPower(2856);
    assertEquals(EMPTY + Power.TWO + Power.EIGHT + Power.FIVE + Power.SIX, str);
    str = Power.createPower(47);
    assertEquals(EMPTY + Power.FOUR + Power.SEVEN, str);
  }
  
  @Test
  void testContainsPower()
  {
    Unit unit = new SingleUnit("a", 3);
    assertTrue(Power.containsPower(unit.toString()));
  }
}
