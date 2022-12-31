package testing;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;

import data.SingleUnit;

/**
 * This class is used to test the SingleUnit class.
 * 
 * @author Luke Bieniek
 * @version 1
 * 
 *          This work complies with the JMU Honor Code.
 */

public class SingleUnitTest
{
	private static final String FOOT = "ft";
	private static final String EMPTY = "";
	private static final String POUND = "lb";

  /**
   * Tests the contains() method.
   */

  @Test
  public void containsTest()
  {
    SingleUnit su1 = new SingleUnit(POUND);

    assertEquals(true, su1.contains(su1), "contains() method");
  }
  
  /**
   * Tests the copyUnit() method.
   */

  @Test
  public void copyUnitTest()
  {
    SingleUnit su1 = new SingleUnit(POUND);
    SingleUnit su2 = (SingleUnit) su1.copyUnit();

    assertEquals(true, su1.equalsUnit(su2), "copyUnit() method");
  }
  
  /**
   * Tests the equals() method.
   */

  @Test
  public void equalsTest()
  {
    SingleUnit su1 = new SingleUnit(POUND);
    SingleUnit su2 = new SingleUnit(POUND);
    SingleUnit su3 = new SingleUnit(FOOT);

    assertEquals(true, su1.equalsUnit(su2), "equals() method when values are equals");
    assertEquals(false, su1.equalsUnit(su3), "equals() method when values are not equal");
  }
  
  /**
   * Tests the getLeft() method.
   */
  
  @Test
  public void getLeft()
  {
	  SingleUnit su1 = new SingleUnit(POUND);
	  
	  assertEquals(null, su1.getLeft(), "getLeft() method");
  }
  
  /**
   * Tests the getName() method.
   */

  @Test
  public void getNameTest()
  {
    SingleUnit su1 = new SingleUnit(POUND);

    assertEquals(POUND, su1.getName(), "getName() method");
  }
  
  /**
   * Tests the getPower() method.
   */

  @Test
  public void getPowerTest()
  {
    SingleUnit su1 = new SingleUnit(EMPTY);

    assertEquals(1, su1.getPower(), "getPower() method with empty unit");
    
    su1 = new SingleUnit(POUND);
    
    assertEquals(1, su1.getPower(), "getPower() method");
  }
  
  /**
   * Tests the getRight() method.
   */
  
  @Test
  public void getRight()
  {
	  SingleUnit su1 = new SingleUnit(POUND);
	  
	  assertEquals(null, su1.getRight(), "getRight() method");
  }
  
  /**
   * Tests the getType() method.
   */
  
  @Test
  public void getType()
  {
    SingleUnit su = new SingleUnit(POUND);

    assertEquals("1", su.getType(), "getType() method");
  }
  
  /**
   * Tests the isConjunction() method.
   */

  @Test
  public void isConjuntionTest()
  {
    SingleUnit su1 = new SingleUnit(POUND);

    assertEquals(false, su1.isConjunction(), "isConjunction() method with no dash or slash");
  }
  
  /**
   * Tests the multiplyPower() method.
   */

  @Test
  public void multiplyPowerTest()
  {
    SingleUnit su1 = new SingleUnit(POUND);
    su1.multiplyPower(3);

    assertEquals(3, su1.getPower(), "multiplyPower() method");
  }
  
  /**
   * Tests the setPower() method.
   */

  @Test
  public void setPowerTest()
  {
    SingleUnit su1 = new SingleUnit("");
    su1.setPower(3);

    assertEquals(su1.getPower(), 1, "setPower() method with no unit");
    
    su1 = new SingleUnit(POUND);
    su1.setPower(3);
    
    assertEquals(su1.getPower(), 3, "setPower() method with unit");
  }

  /**
   * Tests the toString() method.
   */

  @Test
  public void toStringTest()
  {
    SingleUnit su = new SingleUnit(FOOT);

    assertEquals(FOOT, su.toString(), "toString() method");
    
    su.setPower(3);
    assertEquals("ft³", su.toString(), "toString() method with power");
    
    su = new SingleUnit(EMPTY);
    assertEquals(EMPTY, su.toString(), "toString() method with no units and a power of 1");
    
    su = new SingleUnit(EMPTY, 3);
    assertEquals(EMPTY, su.toString(), "toString() method with no units and a power of 3");
  }
  
  /**
   * Tests the simply() method.
   */

  @Test
  public void simplifyTest()
  {
    SingleUnit su = new SingleUnit(FOOT);

    assertEquals(su, su.simplify(), "simplify() method");
  }
  
}
