package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.text.DecimalFormat;

import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import calc.UnitConversionEnum;
import data.Operand;
import data.io.InputParser;
import utilities.UnitException;

/**
 * Input field that the user can enter expressions into.
 * 
 * @author Nick Spokes
 * @version 1
 * 
 *          This work complies with the JMU Honor Code.
 */
public class InputField extends JPanel
{
  private static final long serialVersionUID = 1L;
  private static final char NEGATIVE = '-';
  private static final String EMPTY = "";
  private JComboBox<String> comboBox;
  private JTextField textField;

  /**
   * Constructs an InputField object and adds it to the proper JPanel.
   * 
   * @param panel - JPanel to add the InputField object to
   * @param font - Font for the input field
   */
  public InputField(final JPanel panel, final Font font)
  {
    super();
    
    // Input panel
    GridBagConstraints inputGbc = new GridBagConstraints();
    inputGbc.gridwidth = 5;
    inputGbc.fill = GridBagConstraints.BOTH;
    inputGbc.insets = new Insets(0, 0, 5, 0);
    inputGbc.gridx = 0;
    inputGbc.gridy = 1;
    panel.add(this, inputGbc);
    GridBagLayout inputGbl = new GridBagLayout();
    inputGbl.columnWidths = new int[] {56, 56, 56, 56, 56, 0};
    inputGbl.rowHeights = new int[] {52, 0};
    inputGbl.columnWeights = new double[] {0.0, 1.0, 1.0, 1.0, 1.0, Double.MIN_VALUE};
    inputGbl.rowWeights = new double[] {1.0, Double.MIN_VALUE};
    this.setLayout(inputGbl);
    
    // Input field
    textField = new JTextField();
    textField.setHorizontalAlignment(SwingConstants.RIGHT);
    GridBagConstraints txtGbc = new GridBagConstraints();
    txtGbc.fill = GridBagConstraints.BOTH;
    txtGbc.gridwidth = 4;
    txtGbc.gridx = 0;
    txtGbc.gridy = 0;
    add(textField, txtGbc);
    textField.setColumns(10);
    textField.setMargin(new Insets(10, 10, 10, 10));
    textField.setFont(font);
    Color background = textField.getBackground();
    textField.setEditable(false);
    textField.setBackground(background);
    
    

    // Combo Panel
    JPanel comboPnl = new JPanel();
    GridBagConstraints comboGbc = new GridBagConstraints();
    comboGbc.fill = GridBagConstraints.BOTH;
    comboGbc.gridx = 4;
    comboGbc.gridy = 0;
    add(comboPnl, comboGbc);
    comboPnl.setLayout(new GridLayout(0, 1, 0, 0));

    // Combo box (drop down)
    comboBox = new JComboBox<String>();
    comboPnl.add(comboBox);
    comboBox.setBorder(BorderFactory.createEmptyBorder(10, 5, 10, 10));
    comboBox.setEditable(true);
    populateComboBox();
    comboBox.setEditor(new UnitComboEditor(this));

    // Puts the combo box and input field together
    super.setBackground(textField.getBackground());
    comboBox.setBackground(textField.getBackground());
    super.setBorder(textField.getBorder());
    textField.setBorder(null);
  }
  
  /**
   * Adds the specified string to the end of the current entered expression.
   * 
   * @param str - String to concatenate at the end of the current entered expression
   */
  public void addText(final String str)
  {
    textField.setText(textField.getText() + str);
  }

  /**
   * Removes the last character entered in the input field.
   */
  public void backspace()
  {
    String old = textField.getText();
    if (old.length() > 0)
    {
      textField.setText(old.substring(0, old.length() - 1));
      if(textField.getText().equals("-"))
      {
    	  textField.setText(EMPTY);
      }
    }
  }
  
  /**
   * Focuses on either the unit dropdown editor or the number input field depending on which keys
   * the user presses.
   * 
   * @param key - Key that was pressed
   * @param putInCombo - Whether the character should go into the dropdown editor or the
   * number input field
   */
  public void comboFocus(final Character key, final boolean putInCombo)
  {
    if (putInCombo)
    {
      comboBox.requestFocus();
      changeComboBoxText(comboBox.getEditor().getItem().toString() + key);
    }
    else
    {
      textField.requestFocus();
      addText(key.toString());
    }
  }
  
  /**
   * Compares determines whether the source of a keypress was from the unit dropdown.
   * 
   * @param source - Class that was focused on when the key was pressed
   * @return - Whether the two classes are the same
   */
  public boolean compareSource(final Class<?> source)
  {
    return source.equals(comboBox.getEditor().getEditorComponent().getClass());
  }

  /**
   * Clears the text of the input field.
   */
  public void empty()
  {
    textField.setText(EMPTY);
    comboBox.setSelectedItem(EMPTY);
  }
  
  /**
   * Inverts the operand that is currently in the input field.
   */
  public void invert()
  {
    DecimalFormat form = new DecimalFormat("0.000");
    Operand op = null;
    form.setMinimumFractionDigits(0);
    form.setMaximumFractionDigits(5);
    try
    {
      op = InputParser.parseInput(getText());
      op.flip();
      textField.setText(op.getValue().stripTrailingZeros().toString());
      comboBox.setSelectedItem(op.getUnit().toString());
    }
    catch (UnitException ue)
    {
      ue.match();
    }
  }

  /**
   * Gets the text in the text field and unit dropdown combined.
   * 
   * @return - Text in the text field and unit dropdown combined
   */
  public String getText()
  {
    return textField.getText() + "\u00A7" + comboBox.getEditor().getItem();
  }

  /**
   * Negates the current entered expression.
   */
  public void negate()
  {
    String inputText = textField.getText();
    if (!inputText.isEmpty())
    {
      if (inputText.charAt(0) == NEGATIVE)
      {
        inputText = inputText.substring(1, inputText.length());
      }
      else
      {
        inputText = NEGATIVE + inputText;
      }
      textField.setText(inputText);
    }
  }

  /**
   * Private helper method to populate the unit dropdown.
   */
  private void populateComboBox() // length, time, temp, mass
  {
    comboBox.addItem(EMPTY);
    for (UnitConversionEnum u : UnitConversionEnum.values())
    {
      comboBox.addItem(u.getUnit());
    }
  }
  
  /**
   * Changes the text in the JComboBox.
   * 
   * @param newText - The text to display in the JComboBox
   */
  public void changeComboBoxText(final String newText)
  {
	  comboBox.setSelectedItem(newText);
  }
}
