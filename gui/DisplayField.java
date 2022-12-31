package gui;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * Display field that calculations are displayed in.
 * 
 * @author Nick Spokes
 * @version 1
 */
public class DisplayField extends JTextField
{
  private static final long serialVersionUID = 1L;
  private static int DEFAULT_FONT_SIZE = 26;
  private static String DEFAULT_FONT = "Tahoma";
  /**
   * Constructs a display field object.
   * 
   * @param panel - JPanel to add the display field to
   * @param font - Font for the display field
   */
  public DisplayField(final JPanel panel, final Font font)
  {
    super();
    GridBagConstraints displayGbc = new GridBagConstraints();
    displayGbc.gridwidth = 5;
    displayGbc.fill = GridBagConstraints.BOTH;
    displayGbc.insets = new Insets(0, 0, 5, 0);
    displayGbc.gridx = 0;
    displayGbc.gridy = 0;
    panel.add(this, displayGbc);
    super.setColumns(10);
    super.setMargin(new Insets(10, 10, 10, 10));
    super.setEditable(false);
    super.setFont(defaultFont());
  }
  
  private Font defaultFont()
  {
    return new Font(DEFAULT_FONT, Font.PLAIN, DEFAULT_FONT_SIZE);
  }

  /**
   * Clears the text within the display field.
   */
  public void empty()
  {
    super.setText("");
  }
  
  private int fontSize(final String text, final int fontSize, final Graphics g)
  {
    int size = 0;
    Font font = new Font(DEFAULT_FONT, Font.PLAIN, fontSize);
    int fieldWidth = super.getWidth(); 
    int strWidth = g.getFontMetrics(font).stringWidth(text);
    if (strWidth >= fieldWidth - 20)
    {
      size = fontSize(text, fontSize - 1, g);
    }
    return size;
  }
  
  @Override
  public void setText(final String text)
  {
    Graphics g = super.getGraphics();
    int fontSize = 0;
    if (g.getFontMetrics(defaultFont()).stringWidth(text) >= super.getWidth())
    {
      fontSize = fontSize(text, DEFAULT_FONT_SIZE, g);
      if (fontSize >= 12)
      {
        super.setFont(new Font(DEFAULT_FONT, Font.PLAIN, fontSize));
      }
    }
    else
    {
      if (!super.getFont().equals(defaultFont()))
      {
        super.setFont(defaultFont());
      }
    }
    super.setText(text);
  }
}
