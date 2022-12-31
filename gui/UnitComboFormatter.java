package gui;

import java.text.ParseException;
import javax.swing.JFormattedTextField;
import javax.swing.text.DefaultFormatter;

/**
 * Formatter for the unit dropdown's text editor.
 * 
 * @author Nick Spokes
 * @version 1
 * 
 *          This work complies with the JMU Honor Code.
 */
public class UnitComboFormatter extends DefaultFormatter
{
  private static final String PARSE_EXCEPTION = UnitEDWindow.STRINGS.getString("PARSE_EXCEPTION");
  private static final long serialVersionUID = 1L;
  private static final String REGEX = "^$|([A-Za-z1\\-\\/\\$]+)";
  private InputField parentField;
  
  /**
   * Constructs a UnitComboFormatter object.
   * 
   * @param parentField - The InputField object this was created by
   */
  public UnitComboFormatter(final InputField parentField)
  {
    super();
    this.parentField = parentField;
    super.install(new JFormattedTextField());
    super.setValueClass(String.class);
    super.setAllowsInvalid(false);
    super.setCommitsOnValidEdit(true);
    super.setOverwriteMode(false);
  }
  
  /**
   * Determines if a string contains a digit or a decimal.
   * 
   * @param text - Text to search for a digit or decimal
   * @return - The first invalid character or null if text is valid
   */
  private Character containsNum(final String text)
  {
    Character digit = null;
    for (char curr : text.toCharArray())
    {
      if (Character.isDigit(curr) || ((Character) curr).equals('.'))
      {
        digit = curr;
      }
    }
    return digit;
  }
  
  /**
   * Gets the text field this formatter formats.
   * 
   * @return - Text field this formatter formats
   */
  public JFormattedTextField getField()
  {
    return super.getFormattedTextField();
  }

  /**
   * Verifies that an input string complies with the specified regular expression.
   * 
   * @param text - Text to check
   * @return - The text to add
   */
  @Override
  public Object stringToValue(final String text) throws ParseException
  {
    Character key = null;
    if (!text.matches(REGEX))
    {
      key = containsNum(text);
      if (key != null)
      {
        parentField.comboFocus(key, false);
      }
      throw new ParseException(PARSE_EXCEPTION, 0);
    }
    return text;
  }
}
