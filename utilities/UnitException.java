package utilities;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import gui.UnitEDWindow;

/**
 * Exception used to show error messages.
 * 
 * @author Nick Spokes
 * @version 1
 * 
 *          This work complies with the JMU Honor Code.
 */
public class UnitException extends RuntimeException
{
  public static final int CONTIGUOUS_CONJUNCTION = 1;
  public static final int CONTIGUOUS_NUM_LETTER = 2;
  public static final int DIVIDE_BY_ZERO = 3;
  public static final int GENERAL_ERROR = 4;
  public static final int INVALID_NUMBER = 5;
  public static final int INVALID_UNIT = 6;
  public static final int NO_INPUT = 7;
  public static final int UNIT_ENDS_CONTAIN_CONJUNCTION = 8;
  public static final int UNIT_MISMATCH = 9;
  private static final long serialVersionUID = 1L;
  private static final String ERROR = UnitEDWindow.STRINGS.getString("ERROR");
  private static final String ERROR_NAME = UnitEDWindow.STRINGS.getString("ERROR_NAME");
  
  private int error;
  
  /**
   * Constructs a UnitException object.
   * 
   * @param error - Error message to show
   */
  public UnitException(final int error)
  {
    super("");
    this.error = error;
  }
  
  /**
   * Shows a generic error message.
   */
  public static void genericError()
  {
    new UnitException(GENERAL_ERROR).match();
  }
  
  /**
   * Gets the error identifier.
   * 
   * @return - Error identifier
   */
  public int getErrorType()
  {
    return error;
  }
  
  /**
   * Matches a catched exception's internal error identifier with its corresponding error message.
   */
  public void match()
  {
    switch (error)
    {
      case CONTIGUOUS_CONJUNCTION:
        showMessage(UnitEDWindow.STRINGS.getString("CONTIGUOUS_CONJUNCTION"));
        break;
      case CONTIGUOUS_NUM_LETTER:
        showMessage(UnitEDWindow.STRINGS.getString("CONTIGUOUS_NUM_LETTER"));
        break;
      case DIVIDE_BY_ZERO:
        showMessage(UnitEDWindow.STRINGS.getString("DIVIDE_BY_ZERO"));
        break;
      case INVALID_NUMBER:
        showMessage(UnitEDWindow.STRINGS.getString("INVALID_NUMBER"));
        break;
      case INVALID_UNIT:
        showMessage(UnitEDWindow.STRINGS.getString("INVALID_UNIT"));
        break;
      case NO_INPUT:
        showMessage(UnitEDWindow.STRINGS.getString("NO_INPUT"));
        break;
      case UNIT_ENDS_CONTAIN_CONJUNCTION:
        showMessage(UnitEDWindow.STRINGS.getString("UNIT_ENDS_CONTAIN_CONJUNCTION"));
        break;
      case UNIT_MISMATCH:
        showMessage(UnitEDWindow.STRINGS.getString("UNIT_MISMATCH"));
        break;
      default:
        showMessage(UnitEDWindow.STRINGS.getString("GENERAL_ERROR"));
    }
  }
  
  /**
   * Shows a specified error message.
   * 
   * @param message - Error message to show
   */
  private void showMessage(final String message)
  {
    JOptionPane.showMessageDialog(new JFrame(), ERROR + message, ERROR_NAME,
        JOptionPane.ERROR_MESSAGE);
  }
}
