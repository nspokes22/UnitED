package testing;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import data.*;

/**
 * This class is used to test the SlashConjunction class.
 * 
 * @author Samantha Steigleman-Cox
 * @version 1
 * 
 *          This work complies with the JMU Honor Code.
 */

class SlashConjunctionTest
{
  private SingleUnit sua = new SingleUnit("popcorn", 2);
  private SingleUnit sub = new SingleUnit("blueberry", 1);
  private DashConjunction dca = new DashConjunction(new SingleUnit("melon", 2),
      new SingleUnit("loaf", 1));
  private DashConjunction dcb = new DashConjunction(new SingleUnit("pineapple"),
      new SingleUnit("jam"));
  private SlashConjunction sca = new SlashConjunction(new SingleUnit("corn"),
      new SingleUnit("bread"));
  private SlashConjunction scb = new SlashConjunction(new SingleUnit("mango"),
      new SingleUnit("slushee"));
  private SlashConjunction mscscsca = new SlashConjunction(new SlashConjunction(sca, scb),
		  new SlashConjunction(sua, sub)); // corn/bread / popcorn\u00B2^2/blueberry
  private SlashConjunction mscdcsc = new SlashConjunction(dca, sca); // melon^2-loaf / corn/bread
  //private SlashConjunction mscscdc = new SlashConjunction(sca, dcb); //corn/bread / pineapple-jam
 //
  @Test
  void testflipWhenDivSingleUnit()
  {
	 SingleUnit empty = new SingleUnit("");
	 SingleUnit a = new SingleUnit("a");
	 SlashConjunction sc = new SlashConjunction(empty, a);
	 Unit s = SlashConjunction.convertMultiSlash(sc);
	 assertEquals("1/a", s.toString());
  }

  @Test
  void testconvertMultiSlash()
  {
	  Unit sc = SlashConjunction.convertMultiSlash(mscscsca); // corn/bread/mango/slushee/popcorn\u00B2/blueberry
	  assertEquals("corn-mango-popcorn\u00B2/bread-slushee-blueberry", sc.toString());
	  sc = SlashConjunction.convertMultiSlash(new SlashConjunction(sua,sub));
	  assertEquals("popcorn\u00B2/blueberry", sc.toString());
	  sc = SlashConjunction.convertMultiSlash(new SlashConjunction(sua, new SingleUnit("popcorn")));
	  assertEquals("popcorn", sc.toString());
	  
//	  sc = SlashConjunction.convertMultiSlash(new SlashConjunction(new SingleUnit("popcorn"), sua));
//	  assertEquals("", sc.toString());
	  
  }
  
  @Test
  void test()
  {
	  SingleUnit p = new SingleUnit("p");
	  SingleUnit pp = new SingleUnit("p");
	  SingleUnit ppp = new SingleUnit("p");
	  SingleUnit t = new SingleUnit("t");
	  SingleUnit tt = new SingleUnit("t");
	  SingleUnit r = new SingleUnit("r");
	  SingleUnit rr = new SingleUnit("r");
	  SingleUnit y = new SingleUnit("y");
	  SingleUnit yy = new SingleUnit("y");
	  DashConjunction typ = new DashConjunction(new DashConjunction(t,y), p);
	  DashConjunction rp = new DashConjunction(r, pp);
	  DashConjunction yp = new DashConjunction(yy, ppp);
	  DashConjunction tr = new DashConjunction(tt, rr);
	  SlashConjunction scn = new SlashConjunction(typ, rp);
	  SlashConjunction scd = new SlashConjunction(yp, tr);
	  SlashConjunction scf = new SlashConjunction(scn, scd);
	  Unit sc = SlashConjunction.convertMultiSlash(scf);
	  assertEquals("y\u00B2-p/r\u00B2", sc.toString());
  }
  @Test
  void testCanceling()
  {
 	  Unit sc = SlashConjunction.convertMultiSlash(new SlashConjunction(mscdcsc, new SlashConjunction(new SlashConjunction(new SingleUnit("corn", 1), new SingleUnit("bread", 1)), dcb)));
	  //System.out.print(sc.toString()); // melon^2-loaf-bread^2 / corn^2-pineapple-jam
	  sc = SlashConjunction.convertMultiSlash(new SlashConjunction(new SlashConjunction(new SingleUnit("mile"), new SingleUnit("hr")), new SingleUnit("hr")));
	  assertEquals("mile", sc.toString());
	  DashConjunction dc = new DashConjunction(new SingleUnit("mi"), new SingleUnit("mi"));
	  sc = DashConjunction.convertUnitsMultiplication(dc);
	  assertEquals("mi\u00B2", sc.toString());
	  
	  sc = new SlashConjunction(new SlashConjunction(new SlashConjunction(new SingleUnit("s"), new SingleUnit("a")), new SingleUnit("a")), new SlashConjunction(new SingleUnit("r"), new SingleUnit("a")));
	  //System.out.println(SlashConjunction.convertMultiSlash((SlashConjunction) sc).toString());
	  
	  SingleUnit e = new SingleUnit("e");
	  SingleUnit ee = new SingleUnit("e");
	  SingleUnit r = new SingleUnit("r");
	  dc = new DashConjunction(ee, r);
	  sc = SlashConjunction.convertMultiSlash(new SlashConjunction(dc, e));
	  assertEquals("r", sc.toString());
  }
  @Test
  void testPatternA()
  {
    // single unit / single unit
    SlashConjunction sut = (SlashConjunction) SlashConjunction.convertMultiSlash(new SlashConjunction(sua, sub));
    assertEquals("popcorn\u00B2/blueberry", sut.toString());
    // single unit / dash conj
    SlashConjunction sudc = (SlashConjunction) SlashConjunction.convertMultiSlash(new SlashConjunction(sua, dca));

    assertEquals("popcorn\u00B2/melon\u00B2-loaf", sudc.toString());
    // dash conj / single unit
    SlashConjunction dcsu = (SlashConjunction) SlashConjunction.convertMultiSlash(new SlashConjunction(dca, sua));
    assertEquals("melon\u00B2-loaf/popcorn\u00B2", dcsu.toString());
    // dash conj / dash conj
    SlashConjunction dcdc = (SlashConjunction) SlashConjunction.convertMultiSlash(new SlashConjunction(dca, dcb));
    assertEquals("melon\u00B2-loaf/pineapple-jam", dcdc.toString());
  }

  /**
   * Tests the operations described by pattern B.
   */

  @Test
  void testPatternB()
  {
    // single unit / slash conj, a-b.l/br
    SlashConjunction susc = (SlashConjunction) SlashConjunction.convertMultiSlash(new SlashConjunction(sua, sca));
    assertEquals("popcorn\u00B2-bread/corn", susc.toString());
    SlashConjunction sucut = (SlashConjunction) SlashConjunction.convertMultiSlash(new SlashConjunction(sub, sca));
    assertEquals("blueberry-bread/corn", sucut.toString());
    // dash conj / slash conj
    SlashConjunction dcsc = (SlashConjunction) SlashConjunction.convertMultiSlash(new SlashConjunction(dca, sca));

    assertEquals("melon\u00B2-loaf-bread/corn", dcsc.toString());
  }

  /**
   * Tests the operations described by pattern C.
   */

  @Test
  void testPatternC()
  {
    // slash conj / single unit, a.l/(a.r-b)
    SlashConjunction scsu = (SlashConjunction) SlashConjunction.convertMultiSlash(new SlashConjunction(sca, sua));
    assertEquals("corn-popcorn\u00B2/bread", scsu.toString());
    SlashConjunction scdc = (SlashConjunction) SlashConjunction.convertMultiSlash(new SlashConjunction(sca, dca));
    assertEquals("corn-melon\u00B2-loaf/bread", scdc.toString());
  }

  /**
   * Tests the operations described by pattern D.
   */

  @Test
  void testPatternD()
  {
    // slash conj / slash conj, (a.l-b.r)/(a.r-b.l)
    SlashConjunction scsc = (SlashConjunction) SlashConjunction.convertMultiSlash(new SlashConjunction(sca, scb));
    assertEquals("corn-mango/bread-slushee", scsc.toString());
  }

  /**
   * Tests convertMultiSlash() method with arguments containing the same unit.
   */

  @Test
  void testSameUnit()
  {
//	  SingleUnit b = new SingleUnit("b", 1);
//	  SingleUnit a = new SingleUnit("a");
//	  //SingleUnit c = new SingleUnit("c");
//	  SingleUnit bb = new SingleUnit("b");
//	  SingleUnit aa = new SingleUnit("a");
//	  //SingleUnit cc = new SingleUnit("c");
//	  //SlashConjunction scn = new SlashConjunction(aa, bb);
//	  //SlashConjunction scan = new SlashConjunction(a,b);
//	  //SlashConjunction scf = new SlashConjunction(scn, cc);
//	  //SlashConjunction scaf = new SlashConjunction(scan,b);
//	  //Unit fin = SlashConjunction.convertMultiSlash(new SlashConjunction (scf,scaf));
//	 // System.out.print(fin.toString());
	  
	  SingleUnit p = new SingleUnit("b", 1);
	  SingleUnit d = new SingleUnit("b", 1);
	  DashConjunction dc = new DashConjunction(new SingleUnit("b"), p);
	  
	  SlashConjunction scb = new SlashConjunction(new SingleUnit("a"), d);
	  //Unit unnnn = SlashConjunction.condense(new SlashConjunction(scb, dc));
	  Unit un = SlashConjunction.convertMultiSlash(new SlashConjunction(scb, dc));
	  assertEquals("a-b", un.toString());
	 // assertEquals("b", un.toString());
	  
    Unit unit = SlashConjunction.convertMultiSlash(new SlashConjunction(sua, sua));
    //assertEquals("1", unit.getType());
    assertEquals("", unit.toString());

    unit = SlashConjunction.convertMultiSlash(new SlashConjunction(dca, dca));
    assertEquals("", unit.toString());
    //assertEquals("-", unit.getType());

    unit = SlashConjunction.convertMultiSlash(new SlashConjunction(sca, sca));
    //assertEquals("/", unit.getType());
    assertEquals("", unit.toString());
  }
}
