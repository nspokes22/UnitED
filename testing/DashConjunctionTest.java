package testing;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import data.*;

/**
 * This class is used to test the DashConjunction class.
 * 
 * @author Samantha Steigleman-Cox
 * @version 1
 * 
 *          This work complies with the JMU Honor Code.
 */

class DashConjunctionTest
{
  // testing dash (multiplication) of units pattern
  // single unit - single unit, a-b A
  // single unit - dash conj, a-[b.l-b.r], a-b A
  // single unit - slash conj, (a-b.l)/b.r, B
  // dash conj - single unit, a.l-a.r-b, a-b A
  // dash conj - dash conj, a.l-a.r-b.l-b.r, a-b A
  // dash conj - slash conj, (a.l-a.r-b.l)/b.r B
  // slash conj - single unit, (a.l-b) / a.r C
  // slash conj - dash conj, (a.l-b.l-b.r) / a.r C
  // slash conj - slash conj, (a.l-b.l) / (a.r-b.r) D
  private SingleUnit sua = new SingleUnit("popcorn");
  private SingleUnit sub = new SingleUnit("blueberry");
  private DashConjunction dca = new DashConjunction(new SingleUnit("melon"),
      new SingleUnit("loaf"));
  private DashConjunction dcb = new DashConjunction(new SingleUnit("pineapple"),
      new SingleUnit("jam"));
  private SlashConjunction sca = new SlashConjunction(new SingleUnit("corn"),
      new SingleUnit("bread"));
  private SlashConjunction scb = new SlashConjunction(new SingleUnit("mango"),
      new SingleUnit("slushee"));

  @Test
 	void testemptyUnits()
  {
	 SingleUnit a = new SingleUnit("a", 3);
	 SingleUnit empty = new SingleUnit("");
	 DashConjunction dc = new DashConjunction(a, empty);
	 DashConjunction.convertUnitsMultiplication(dc);
  }
  @Test
  void completeCancelation()
  {
	  SingleUnit b = new SingleUnit("b");
	  SingleUnit a = new SingleUnit("a");
	  SingleUnit c = new SingleUnit("c");
	  SingleUnit bb = new SingleUnit("b");
	  SingleUnit aa = new SingleUnit("a");
	  SingleUnit cc = new SingleUnit("c");
	  SlashConjunction scn = new SlashConjunction(aa, bb);
	  SlashConjunction scan = new SlashConjunction(a,b);
	  SlashConjunction scf = new SlashConjunction(scn, cc);
	  SlashConjunction scaf = new SlashConjunction(scan,c);
	  
	  Unit fin = DashConjunction.convertUnitsMultiplication(new DashConjunction (scf,scaf));
	  assertEquals("a\u00B2-c\u00B2/b\u00B2", fin.toString());
	  //Unit fin = DashConjunction.convertUnitsMultiplication(new DashConjunction (scn, scan));
	  //assertEquals("a\u00B2/b\u00B2", fin.toString());
	  
	  
	  
  }
  @Test 
  void testCondense() 
  {
	  SingleUnit a = new SingleUnit("a", 1);
	  SingleUnit aa = new SingleUnit("a", 2);
	  SingleUnit d = new SingleUnit("b");
	  SlashConjunction bba = new SlashConjunction(d, a);// d/a-aa
	  
	  Unit aaa = DashConjunction.convertUnitsMultiplication(new DashConjunction(bba, aa));
	  assertEquals("b-a", aaa.toString());
  }
  /**
   * Tests the operations described by pattern A.
   */

  @Test
  void testPatternA()
  {
    // single unit - single unit, a-b
    Conjunction susu = (Conjunction) DashConjunction.convertUnitsMultiplication(new DashConjunction(sua, sub));
    //assertEquals(sua, susu.getLeft());
    //assertEquals(sub, susu.getRight());
    assertEquals("-", susu.getType());
    assertEquals("popcorn-blueberry", susu.toString());
    // single unit - dash conj, a-[b.l-b.r], a-b
    Conjunction sudc = (Conjunction) DashConjunction.convertUnitsMultiplication(new DashConjunction(sua, dca));
//    assertEquals(sua, sudc.getLeft());
//    assertEquals(dca, sudc.getRight());
    assertEquals("-", sudc.getType());
    assertEquals("popcorn-melon-loaf", sudc.toString());
    // dash conj - single unit, a.l-a.r-b, a-b
    Conjunction dcsu = (Conjunction) DashConjunction.convertUnitsMultiplication(new DashConjunction(dca, sua));
//    assertEquals(dca, dcsu.getLeft());
//    assertEquals(sua, dcsu.getRight());
    assertEquals("-", dcsu.getType());
    assertEquals("melon-loaf-popcorn", dcsu.toString());
    // dash conj - dash conj, a.l-a.r-b.l-b.r, a-b
    Conjunction dcdc = (Conjunction) DashConjunction.convertUnitsMultiplication(new DashConjunction(dca, dcb));
//    assertEquals(dca, dcdc.getLeft());
//    assertEquals(dcb, dcdc.getRight());
    assertEquals("-", dcdc.getType());
    assertEquals("melon-loaf-pineapple-jam", dcdc.toString());
  }

  /**
   * Tests the operations described by pattern B.
   */

  @Test
  void testPatternB()
  {
    // single unit - slash conj, (a-b.l)/b.r
    Conjunction susc = (Conjunction) DashConjunction.convertUnitsMultiplication(new DashConjunction(sua, sca));
//    assertEquals("popcorn-corn", susc.getLeft().toString());
//    assertEquals("bread", susc.getRight().toString());
    assertEquals("/", susc.getType().toString());
    assertEquals("popcorn-corn/bread", susc.toString());
    // dash conj - slash conj, (a.l-a.r-b.l)/b.r
    Conjunction dcsc = (Conjunction) DashConjunction.convertUnitsMultiplication(new DashConjunction(dca, sca));
//    assertEquals("melon-loaf-corn", dcsc.getLeft().toString());
//    assertEquals("bread", dcsc.getRight().toString());
    assertEquals("/", dcsc.getType());
    assertEquals("melon-loaf-corn/bread", dcsc.toString());
  }

  /**
   * Tests the operations described by pattern C.
   */

  @Test
  void testPatternC()
  {
    // slash conj - single unit, (a.l-b) / a.r
    Conjunction scsu = (Conjunction) DashConjunction.convertUnitsMultiplication(new DashConjunction(sca, sua));
    assertEquals("corn-popcorn", scsu.getLeft().toString());
    assertEquals("bread", scsu.getRight().toString());
    assertEquals("/", scsu.getType());
    assertEquals("corn-popcorn/bread", scsu.toString());
    // slash conj - dash conj, (a.l-b.l-b.r) / a.r
    Conjunction scdc = (Conjunction) DashConjunction.convertUnitsMultiplication(new DashConjunction(sca, dca));
    assertEquals("corn-melon-loaf", scdc.getLeft().toString());
    assertEquals("bread", scdc.getRight().toString());
    assertEquals("/", scdc.getType());
    assertEquals("corn-melon-loaf/bread", scdc.toString());
  }

  /**
   * Tests the operations described by pattern D.
   */

  @Test
  void testPatternD()
  {
    // slash conj - slash conj, (a.l-b.l) / (a.r-b.r)
    Conjunction scsc = (Conjunction) DashConjunction.convertUnitsMultiplication(new DashConjunction(sca, scb));
    assertEquals("corn-mango", scsc.getLeft().toString());
    assertEquals("bread-slushee", scsc.getRight().toString());
    assertEquals("/", scsc.getType());
    assertEquals("corn-mango/bread-slushee", scsc.toString());
  }
}
