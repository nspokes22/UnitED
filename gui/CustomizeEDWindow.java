package gui;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Scanner;
import javax.swing.*;

/**
 * CustomizeEDWindow - reads in a .txt file with RGB values assigned to gui components. Changes the
 * colors of the calculator as desired. Also changes the logo of the calculator as desired.
 * 
 * @author Katherine Hassler
 *
 */

public class CustomizeEDWindow
{
  private static final String COLOR_PATH = "/customization/colors.txt";
  private static final String LOGO_PATH = "/customization/logo.png";
  private static final String DEFAULT_LOGO_PATH = "/customization/default_logo.png";

  private static Color background;
  private static Color border;
  private static Color buttons;
  private static Color letters;

  /**
   * Reads the colors text file and assigns color measurement to the appropriate variables. *Note:
   * will not function if colors text file is not formatted correctly. Will use default colors if
   * so.
   */
  public static void readFile()
  {
    BufferedReader reader = null;

    try
    {
      reader = new BufferedReader(
          new InputStreamReader(CustomizeEDWindow.class.getResourceAsStream(COLOR_PATH)));

    }
    catch (NullPointerException e)
    {
      System.out.println("Cannot read file.");
    }

    String[] values;
    int redValue;
    int greenValue;
    int blueValue;

    try
    {
      Scanner sc = new Scanner(reader);

      while (sc.hasNextLine())
      {
        String str = sc.nextLine();
        values = str.split(" ");

        redValue = Integer.parseInt(values[3]);
        greenValue = Integer.parseInt(values[5]);
        blueValue = Integer.parseInt(values[7]);

        if (values[0].equalsIgnoreCase("background"))
        {
          background = new Color(redValue, greenValue, blueValue);
        }
        else if (values[0].equalsIgnoreCase("border"))
        {
          border = new Color(redValue, greenValue, blueValue);
        }
        else if (values[0].equalsIgnoreCase("buttons"))
        {
          buttons = new Color(redValue, greenValue, blueValue);
        }
        else if (values[0].equalsIgnoreCase("letters"))
        {
          letters = new Color(redValue, greenValue, blueValue);
        }
      }
      sc.close();
    }
    catch (NumberFormatException e)
    {
      // Nothing changes.
    }
  }

  /**
   * Gets the desired logo from the customization folder. Uses the default logo if the dimensions
   * for the desired logo are too big.
   * 
   * @return JLabel logo icon
   */
  public static JLabel getLogo()
  {
    URL url = null;
    JLabel logo = null;
    ImageIcon ico = null;

    // Check if the file is the correct size (at least 90 x 30).
    try
    {
      url = CustomizeEDWindow.class.getResource(LOGO_PATH);
      ico = new ImageIcon(url);
      if ((ico.getIconWidth() > 90 || ico.getIconHeight() > 30)
          || (ico.getIconWidth() <= 16 || ico.getIconHeight() <= 16))
      {
        throw new NullPointerException();
      }

      logo = new JLabel(ico);
    }
    catch (NullPointerException e)
    {
      url = CustomizeEDWindow.class.getResource(DEFAULT_LOGO_PATH);
      ico = new ImageIcon(url);
      logo = new JLabel(ico);
      logo.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
    }

    return logo;
  }

  /**
   * Sets the border color.
   * 
   * @param color
   *          borderColor
   * @param panel
   *          border component
   * @param panel2
   *          border component
   * @param panel3
   *          border component
   */
  public static void setBorderColor(final Color color, final JPanel panel, final JPanel panel2, 
      final JPanel panel3)
  {
    panel.setBackground(color);
    panel2.setBackground(color);
    panel3.setBackground(color);
  }

  /**
   * Sets the background color.
   * 
   * @param color
   *          backgroundColor
   * @param panel
   *          component
   * @param panel2
   *          component
   */
  public static void setBackgroundColor(final Color color, final JPanel panel, 
      final JPanel panel2)
  {
    panel.setBackground(color);
    panel2.setBackground(color);
  }

  /**
   * Sets the history display color.
   * 
   * @param color
   *          Color
   * @param panel
   *          history panel
   */
  public static void setHistoryDisplay(final Color color, final JPanel panel)
  {
    panel.setBackground(color);
  }

  /**
   * Sets the button color.
   * 
   * @param color
   *          buttonsColor
   * @param letterColor
   *          lettersColor
   * @param button
   *          component
   */
  public static void setButtonColor(final Color color, final Color letterColor,
      final JComponent button)
  {
    button.setBackground(color);
    button.setForeground(letterColor);
  }

  /**
   * Gets the background color.
   * 
   * @return background color
   */
  public static Color getBackgroundColor()
  {
    return background;
  }

  /**
   * Gets the border color.
   * 
   * @return border color
   */
  public static Color getBorderColor()
  {
    return border;
  }

  /**
   * Gets the button color.
   * 
   * @return button color
   */
  public static Color getButtonsColor()
  {
    return buttons;
  }

  /**
   * Gets the letter color.
   * 
   * @return letter color
   */
  public static Color getLetterColor()
  {
    return letters;
  }
}
