package testing;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import utilities.Power;
import data.io.InputParser;
import utilities.UnitException;

class InputParserTest
{
  private static final String ADJACENT_DASH = "87.3\u00A7cats//dogs";
  private static final String ADJACENT_SLASH = "25.2\u00A7frogs--bogs";
  private static final String ALTERING_CONJ = "74.3\u00A7miles-meters/cm-km";
  private static final String CONJ_NO_SPACE_STRING = "89.2\u00A7km/h";
  private static final String DASH_CONJ_INPUT = "700.2\u00A7m-s";
  private static final String FULL_INPUT = "2.3\u00A7lbs";
  private static final String INVALID_DASH = "90.0\u00A7meters-";
  private static final String INVALID_DIGIT = "@j5";
  private static final String INVALID_DIGIT_2 = "5j\u00A7cups";
  private static final String INVALID_INPUT = "Not\u00A7allowed";
  private static final String INVALID_NEG_DIGIT = "9-9.0";
  private static final String INVALID_SLASH = "23.2\u00A7miles/";
  private static final String INVALID_UNIT = "33.3\u00A7r4ts";
  private static final String LONG_STRING = "45.3\u00A7cats\u00A7dogs";
  private static final String NEG_DIGIT_WITH_CONJ = "-90.9\u00A7cats/dogs";
  private static final String NEG_NO_SPACE_DIGIT = "-50.1\u00A7numbers";
  private static final String NEG_PARTIAL_INPUT = "-9.1";
  private static final String NEGATIVE_DIGIT = "-5.7\u00A7cm";
  private static final String NO_SPACE_STRING = "10.4\u00A7oz";
  private static final String PARTIAL_INPUT = "4.3";
  private static final String SLASH_CONJ_INPUT = "56.9\u00A7mi/h";
  private static final String TRIPLE_CONJ = "80.66\u00A7lbs/oz/quarts";

  @Test
  public void digitTest()
  {
    new InputParser();
    assertEquals("2.3", InputParser.parseInput(FULL_INPUT).getValue().toString());
    assertEquals("10.4", InputParser.parseInput(NO_SPACE_STRING).getValue().toString());
    assertEquals("89.2", InputParser.parseInput(CONJ_NO_SPACE_STRING).getValue().toString());
    assertEquals("5", InputParser.parseInput("5\u00A7^%#@!").getValue().toString());
    
    UnitException err = assertThrows(UnitException.class, () -> 
    {
      InputParser.parseInput(INVALID_DIGIT);
    });
    assertEquals(UnitException.INVALID_NUMBER, err.getErrorType());
    
    err = assertThrows(UnitException.class, () -> 
    {
      InputParser.parseInput(INVALID_DIGIT_2);
    });
    assertEquals(UnitException.INVALID_NUMBER, err.getErrorType());
  }

  @Test
  public void unitTest()
  {
    assertEquals("lbs", InputParser.parseInput(FULL_INPUT).getUnit().toString());
    assertEquals("oz", InputParser.parseInput(NO_SPACE_STRING).getUnit().toString());

    UnitException err = assertThrows(UnitException.class, () -> 
    {
      InputParser.parseInput(INVALID_UNIT);
    });
    assertEquals(UnitException.CONTIGUOUS_NUM_LETTER, err.getErrorType());
    
    err = assertThrows(UnitException.class, () -> 
    {
      InputParser.parseInput(INVALID_DASH);
    });
    assertEquals(UnitException.UNIT_ENDS_CONTAIN_CONJUNCTION, err.getErrorType());
    
    err = assertThrows(UnitException.class, () -> 
    {
      InputParser.parseInput(INVALID_SLASH);
    });
    assertEquals(UnitException.UNIT_ENDS_CONTAIN_CONJUNCTION, err.getErrorType());
    
    err = assertThrows(UnitException.class, () -> 
    {
      InputParser.parseInput(ADJACENT_DASH);
    });
    assertEquals(UnitException.CONTIGUOUS_CONJUNCTION, err.getErrorType());
    
    err = assertThrows(UnitException.class, () -> 
    {
      InputParser.parseInput(ADJACENT_SLASH);
    });
    assertEquals(UnitException.CONTIGUOUS_CONJUNCTION, err.getErrorType());
    
    err = assertThrows(UnitException.class, () -> 
    {
      InputParser.parseInput("4.3\u00A7catsdo--g");
    });
    assertEquals(UnitException.CONTIGUOUS_CONJUNCTION, err.getErrorType());
    
    err = assertThrows(UnitException.class, () -> 
    {
      InputParser.parseInput("3.2\u00A7cats//");
    });
    assertEquals(UnitException.UNIT_ENDS_CONTAIN_CONJUNCTION, err.getErrorType());
  }

  @Test
  public void slashConjunctionTest()
  {
    assertEquals("mi/h", InputParser.parseInput(SLASH_CONJ_INPUT).getUnit().toString());
    assertEquals("km/h", InputParser.parseInput(CONJ_NO_SPACE_STRING).getUnit().toString());
    assertEquals(true, InputParser.parseInput(CONJ_NO_SPACE_STRING).getUnit().isConjunction());
  }

  @Test
  public void dashConjunctionTest()
  {
    assertEquals("m-s", InputParser.parseInput(DASH_CONJ_INPUT).getUnit().toString());
  }
  
  @Test
  public void negativeDigitTest()
  {
    assertEquals("-5.7", InputParser.parseInput(NEGATIVE_DIGIT).getValue().toString());
    assertEquals("-50.1", InputParser.parseInput(NEG_NO_SPACE_DIGIT).getValue().toString());
    assertEquals("-90.9",
        InputParser.parseInput(NEG_DIGIT_WITH_CONJ).getValue().toString());
    assertNotEquals("-", InputParser.parseInput(NEG_PARTIAL_INPUT).getUnit().toString());
    assertThrows(IllegalArgumentException.class, () -> 
    {
      InputParser.parseInput(INVALID_NEG_DIGIT);
    });
  }
  
  @Test
  public void multipleConjunctionsTest() //Needs composite unit.
  {
    assertEquals("lbs/oz/quarts", InputParser.parseInput(TRIPLE_CONJ).getUnit().toString());
    assertEquals("miles-meters/cm-km",
        InputParser.parseInput(ALTERING_CONJ).getUnit().toString());
  }
  
  @Test
  public void partialInputTest()
  {
    assertEquals(PARTIAL_INPUT, InputParser.parseInput(PARTIAL_INPUT).getValue().toString());
    assertEquals(NEG_PARTIAL_INPUT,
        InputParser.parseInput(NEG_PARTIAL_INPUT).getValue().toString());
  }

  @Test
  public void otherExceptionHandling()
  {
    UnitException err = assertThrows(UnitException.class, () -> 
    {
      InputParser.parseInput(LONG_STRING);
    });
    assertEquals(UnitException.GENERAL_ERROR, err.getErrorType());
    
    err = assertThrows(UnitException.class, () -> 
    {
      InputParser.parseInput("");
    });
    assertEquals(UnitException.NO_INPUT, err.getErrorType());
    
    err = assertThrows(UnitException.class, () -> 
    {
      InputParser.parseInput(INVALID_INPUT);
    });
    assertEquals(UnitException.INVALID_NUMBER, err.getErrorType());
    
    err = assertThrows(UnitException.class, () -> 
    {
      InputParser.parseInput("abcdefghijk");
    });
    assertEquals(UnitException.INVALID_NUMBER, err.getErrorType());
    
    err = assertThrows(UnitException.class, () -> 
    {
      InputParser.parseInput("!@#$%^&");
    });
    assertEquals(UnitException.INVALID_NUMBER, err.getErrorType());
    
    err = assertThrows(UnitException.class, () -> 
    {
      InputParser.parseInput("4.5\u00A7inv4lid");
    });
    assertEquals(UnitException.CONTIGUOUS_NUM_LETTER, err.getErrorType());
    
    err = assertThrows(UnitException.class, () -> 
    {
      InputParser.parseInput("1\u00A71-1");
    });
    assertEquals(UnitException.INVALID_UNIT, err.getErrorType());
    
    err = assertThrows(UnitException.class, () -> 
    {
      InputParser.parseInput("1\u00A71/1");
    });
    assertEquals(UnitException.INVALID_UNIT, err.getErrorType());
  }
  
  @Test
  public void testAdvancedExceptions()
  {
    UnitException err = assertThrows(UnitException.class, () -> 
    {
      InputParser.parseInput("\u00A7a");
    });
    assertEquals(UnitException.NO_INPUT, err.getErrorType());
    
    err = assertThrows(UnitException.class, () -> 
    {
      InputParser.parseInput("\u00A7");
    });
    assertEquals(UnitException.NO_INPUT, err.getErrorType());
    
    err = assertThrows(UnitException.class, () -> 
    {
      InputParser.parseInput(".");
    });
    assertEquals(UnitException.INVALID_NUMBER, err.getErrorType());
    
    err = assertThrows(UnitException.class, () -> 
    {
      InputParser.parseInput("10.0.0.1");
    });
    assertEquals(UnitException.INVALID_NUMBER, err.getErrorType());
    
    err = assertThrows(UnitException.class, () -> 
    {
      InputParser.parseInput("1\u00A71a");
    });
    assertEquals(UnitException.CONTIGUOUS_NUM_LETTER, err.getErrorType());
    
    err = assertThrows(UnitException.class, () -> 
    {
      InputParser.parseInput("..");
    });
    assertEquals(UnitException.INVALID_NUMBER, err.getErrorType());
    
    err = assertThrows(UnitException.class, () -> 
    {
      InputParser.parseInput("1\u00A7/a");
    });
    assertEquals(UnitException.UNIT_ENDS_CONTAIN_CONJUNCTION, err.getErrorType());
    
    err = assertThrows(UnitException.class, () -> 
    {
      InputParser.parseInput("1\u00A71");
    });
    assertEquals(UnitException.INVALID_UNIT, err.getErrorType());
    
    err = assertThrows(UnitException.class, () -> 
    {
      InputParser.parseInput("1\u00A74");
    });
    assertEquals(UnitException.INVALID_UNIT, err.getErrorType());
    
    err = assertThrows(UnitException.class, () -> 
    {
      InputParser.parseInput("1\u00A7111111");
    });
    assertEquals(UnitException.INVALID_UNIT, err.getErrorType());
  }
  
  @Test
  public void testAdvancedPowers()
  {
    final String A = "a";
    final String B = "-b";
    final String C = "1/a";
    
    assertEquals(A + Power.createPower(2), InputParser.parseInput("1\u00A7a-a").getUnit().toString());
    
    assertEquals(A + Power.createPower(3),
        InputParser.parseInput("1\u00A7a-a-a").getUnit().toString());
    
    assertEquals(A + Power.createPower(2) + B,
        InputParser.parseInput("1\u00A7a-a-b").getUnit().toString());
    
    assertEquals(A + Power.createPower(2) + B + Power.createPower(2),
        InputParser.parseInput("1\u00A7a-a-b-b").getUnit().toString());
    
    assertEquals("b", InputParser.parseInput("1\u00A7b/a-a").getUnit().toString());
    
    assertEquals(C + Power.createPower(2),
        InputParser.parseInput("1\u00A71/a/a").getUnit().toString());
    
    assertEquals(C, InputParser.parseInput("1\u00A7a/a/a").getUnit().toString());
  }
}
