package gui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;
import data.Expression;
import data.Operand;
import data.Unit;
import utilities.Animation;
import utilities.Power;
import utilities.PrintableHistory;

/**
 * This class creates the history display.
 * 
 * @author Luke Bieniek
 * @version 1
 * 
 * This work complies with the JMU Honor Code.
 */
public class HistoryDisplay implements ItemListener, MouseListener
{
  private static final String SPACE = " ";
  private static final String PRINTED_DOC_NAME = UnitEDWindow.STRINGS.getString("PRINTED_DOC_NAME");
  private static final String PRINT_ERROR = UnitEDWindow.STRINGS.getString("PRINT_ERROR");
  private static final String ERROR_NAME = UnitEDWindow.STRINGS.getString("ERROR_NAME");
  
  private Animation animation;
  private boolean isOut;
  private InputField inputField;
  private JComboBox<String> copyAndPasteOptions;
  private JDialog frame;
  private JPanel historyPanel;
  private JScrollPane scrollPane;
  private Map<JComboBox<String>, Operand> answers;
  private HashMap<JComboBox<String>, Operand> leftOperands = new HashMap<JComboBox<String>, 
		  Operand>();
  private HashMap<JComboBox<String>, Operand> rightOperands = new HashMap<JComboBox<String>, 
		  Operand>();
  private PrintableHistory printable;
  private UnitEDWindow parent;
  
  /**
   * This constructs the HistoryDisplay object.
   * 
   * @param parent - The UnitEDWindow object that the HistoryDisplay object will be added to
   * @param inputField - Input field for the window
   */
  public HistoryDisplay(final UnitEDWindow parent, final InputField inputField)
  {
    JFrame first = new JFrame();
    frame = new JDialog(first);
    answers = new HashMap<JComboBox<String>, Operand>();
    historyPanel = new JPanel();
    historyPanel.setDoubleBuffered(true);
    this.inputField = inputField;
    isOut = false;
    this.parent = parent;
    printable = new PrintableHistory();
    frame.setBounds(calculateLoc());
    frame.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
    frame.getContentPane().setLayout(new BorderLayout());
    frame.setResizable(false);
    frame.setFocusableWindowState(false);
    frame.setFocusable(false);
    frame.setUndecorated(true);
    scrollPane = new JScrollPane(historyPanel); 
    historyPanel.setLayout(new BoxLayout(historyPanel, BoxLayout.Y_AXIS));
    frame.getContentPane().add(scrollPane);
    frame.toBack();
    scrollPane.addMouseListener(this);
    for (Component jc : frame.getComponents())
    {
      ((JComponent) jc).setDoubleBuffered(true);
    }
  }
  
  /**
   * Adds a new entry to the HistoryDisplay object representing the expression entered by the user.
   * 
   * @param currEntry
   *        A string representing the new entry
   * @param leftOperand
   *        A string representing the left operand
   * @param rightOperand
   *        A string representing the right operand
   * @param answer - import java.awt.EventQueue;Answer for the whole expression
   */
  public void addEntry(final String currEntry, final Expression leftOperand, 
      final Expression rightOperand, final Operand answer) 
  {
    copyAndPasteOptions = new JComboBox<String>();
    copyAndPasteOptions.setMinimumSize(new Dimension(50, 20));
    copyAndPasteOptions.setMaximumSize(new Dimension(Short.MAX_VALUE, 20));
    copyAndPasteOptions.addItem("    " + currEntry);
    copyAndPasteOptions.addItem("    Copy Full:   " + currEntry);
    copyAndPasteOptions.addItem("    Copy Left:       " + leftOperand.toString());
    copyAndPasteOptions.addItem("    Copy Right:     " + rightOperand.toString());
    printable.addEntry(currEntry);
    historyPanel.add(copyAndPasteOptions);
    copyAndPasteOptions.setAlignmentX(Component.LEFT_ALIGNMENT);
    copyAndPasteOptions.addItemListener(this);
    copyAndPasteOptions.addMouseListener(this);
    for (Component c : copyAndPasteOptions.getComponents())
    {
      c.addMouseListener(this);
    }
      
    historyPanel.repaint();
    this.openHistoryDisplay();
    
    CustomizeEDWindow.setHistoryDisplay(CustomizeEDWindow.getBackgroundColor(), historyPanel);
      
    answers.put(this.copyAndPasteOptions, answer);
    leftOperands.put(copyAndPasteOptions, (Operand) leftOperand);
    rightOperands.put(copyAndPasteOptions, (Operand) rightOperand);
  }
  
  /**
   * Adjusts the display's positioning in response to the main window being moved by the user.
   */
  public void adjustPositioning()
  {
    if(!isOut)
    {
      frame.setLocation(parent.getLocation().x + 10, determineY());
    }
    else 
    {
      frame.setLocation(parent.getLocation().x + parent.getWidth()
          - 8, determineY());
    }
  }
  
  /**
   * Animates the HistoryDisplay object.
   */
  public void animate()
  {
    if (!isOut)
    {
      frame.setVisible(true);
      frame.revalidate();
    }
    try
    {
      Thread.sleep(50);
    }
    catch (InterruptedException ie)
    {
      
    }
    if (animation != null)
    {
      animation.stop();
    }
    animation = new Animation(frame, this, parent);
    SwingUtilities.invokeLater(animation);
    isOut = !isOut;
  }
  
  private Rectangle calculateLoc()
  {
    frame.setSize(350, 559);
    int xPos = parent.getX() + parent.getWidth() - frame.getWidth();
    int yPos = determineY();
    Rectangle loc = new Rectangle(xPos, yPos, frame.getWidth(), frame.getHeight());
    return loc;
  }
  
  /**
   * Returns a boolean representing whether or not the history display is out.
   * @return - a boolean representing whether or not the history display is out
   */
  public boolean isOut()
  {
    return isOut;
  }
  
  /**
   * Makes the history display visible.
   * @param visible - a boolean representing whether or not the frame should be set to be visible
   */
  public void setVisible(final boolean visible)
  {
    frame.setVisible(visible);
  }
  
  /**
   * Closes the HistoryDisplay in response to the main window being closed by the user.
   */
  public void closeHistoryDisplay()
  {
    frame.setVisible(false);
  }
  
  /**
   * Helps the adjustHistoryDisplay() method by determining the Y position to set the display to.
   * 
   * @return - the int representing the Y position to move the HistoryDisplay to
   */
  public int determineY()
  {
    return parent.getLocation().y + (parent.getHeight() / 2) - (frame.getHeight() / 2) + 10;
  }
  
  /**
   * Returns the historyPanel.
   * @return - the historyPanel
   */
  public JPanel getHistoryPanel()
  {
    return historyPanel;
  }
  
  /**
   * Initiates the printing process.
   */
  public void initiatePrint()
  {
    boolean shouldPrint = false;
    PrinterJob job = PrinterJob.getPrinterJob();
    job.setPrintable(printable);
    job.setJobName(PRINTED_DOC_NAME);
    shouldPrint = job.printDialog();
    try
    {
      if (shouldPrint)
      {
        job.print();
      }
    }
    catch (PrinterException pe)
    {
      JOptionPane.showMessageDialog(new JFrame(), PRINT_ERROR, ERROR_NAME,
          JOptionPane.ERROR_MESSAGE);
    }
  }
  
  /**
   * Returns a List of Integers that keeps track of where conjunctions occur within a String.
   * @param str - the String that we are finding conjunctions within
   * @return - A List of Integers that shows where conjunctions are within the String
   */
  private List<Integer> countConjunctions(final String str)
  {
    List<Integer> positions = new LinkedList<Integer>();
    for (int i = 0; i < str.length(); i++)
    {
      if (str.charAt(i) == '-' || str.charAt(i) == '/')
      {
        positions.add(i);
      }
    }
    positions.add(str.length());
    return positions;
  }
  
  /**
   * Converts Units with powers to their equivalent form with dashes.
   * @param str - String representing the Unit
   * @param last - the dash char
   * @return - a String representing a Unit with powers as the equivalent form with dashes
   */
  private String elongate(final String str, final char last)
  {
    int pos = 0;
    int power = 0;
    String unit = str;
    if (Power.containsPower(str))
    {
      while (Character.isAlphabetic(str.charAt(pos)))
      {
        pos++;
      }
      power = Power.powerToInt(str.substring(pos, unit.length())) - 1;
      unit = str.substring(0, pos);
      for (int i = 0; i < power; i++)
      {
        unit += last + str.substring(0, pos);
      }
    }
    return unit;
  }
  
  /**
   * Copies what is selected into the InputField's JComboBox.
   * @param src - the JComboBox that the user clicked on
   * @param map - a map of the JComboBox entries in the history display to their operands
   * @param pair - An array of Strings representing a Digit and Unit
   */
  private void cloneToInput(final JComboBox<String> src, 
		  final HashMap<JComboBox<String>, Operand> map, final String[] pair)
  {
    char lastConj = '-';
    int last = 0;
    List<Integer> positions = null;
    String currUnit = null;
    String temp = "";
    Unit curr = null;
    if(pair.length > 1)
    {
      curr = map.get(src).getUnit();
      
      if (curr.isConjunction())
      {
        currUnit = curr.toString();
        if (Power.containsPower(currUnit))
        {
          positions = countConjunctions(currUnit);
          for (int i = 0; i < positions.size(); i++)
          {
            temp += elongate(currUnit.substring(last, positions.get(i)), lastConj);
            if (i < positions.size() - 1)
            {
              lastConj = currUnit.charAt(positions.get(i));
              temp += lastConj;
            }
            last = positions.get(i) + 1;
          }
          inputField.changeComboBoxText(temp);
        }
        else
        {
          inputField.changeComboBoxText(elongate(currUnit, lastConj));
        }
      }
      else
      {
        if (curr.getPower() > 1)
        {
          inputField.changeComboBoxText(elongate(curr.toString(), lastConj));
        }
        else
        {
          inputField.changeComboBoxText(pair[1]);
        }
      }
    }
  }
  
  /**
   * Handles the selection of new items in the JComboBox.
   * 
   * @param e - The ItemEvent generated by the user selecting a new item in the drop-down menu
   **/
  @SuppressWarnings("unchecked")
  @Override
  public void itemStateChanged(final ItemEvent e) 
  {
    int count = 0;
    JComboBox<String> source = null;
    String output = "";
    String[] selectionSplitByWhiteSpaces = null;
    String[] valueUnitPair = null;
    if(e.getStateChange() == ItemEvent.SELECTED)
    {
      source = (JComboBox<String>) e.getSource();
      selectionSplitByWhiteSpaces = source.getSelectedItem().toString().trim().split("\\s+");
      for(String s: selectionSplitByWhiteSpaces)
      {
        if(count > 1)
        {
          output += (s + SPACE);
        }
        count++;
      }
      output = output.trim();
      if(source.getSelectedIndex() > 1)
      {
    	  valueUnitPair = output.split(SPACE);
        parent.changeInput(valueUnitPair[0]);
    	  if(source.getSelectedIndex() == 2)
        {
          cloneToInput(source, leftOperands, valueUnitPair);
        }
        else
        {
          cloneToInput(source, rightOperands, valueUnitPair);
        }
      }
      else if(source.getSelectedIndex() == 1)
      {
        parent.changeDisplay(output);
        parent.setAnswer(answers.get(e.getSource()));
      }
      source.setSelectedIndex(0);
    }
  }
  
  /**
   * Makes the HistoryDisplay active after the main window is made active.
   */
  public void makeActiveHistoryDisplay()
  {
    this.openAndMakeActiveHelper(0);
  }
  
  /**
   * Serves as a helper method for the openHistoryDisplay() 
   * and makeActiveHistoryDisplay() methods.
   * 
   * @param time - The int representing the amount of 
   * time to wait before either opening or making active the HistoryDisplay
   */
  private void openAndMakeActiveHelper(final int time) 
  {
    parent.setVisible(true);
    try
    {
      Thread.sleep(time);
    }
    catch (InterruptedException ie)
    {
      ie.printStackTrace();
    }
    if (isOut)
    {
      frame.setVisible(true);
    }
    parent.toFront();
  }
  
  /**
   * Reopens the HistoryDisplay when the main window is opened.
   */
  public void openHistoryDisplay()
  {
    openAndMakeActiveHelper(50);
  }
  
  /**
   * Handles the clicking of the mouse.
   * 
   * @param e - The MouseEvent generated by the user
   */
  @Override
  public void mouseClicked(final MouseEvent e)
  {
    parent.requestFocus();
    parent.toFront();
  }
  
  /**
   * Handles the entering of a mouse into a component.
   * 
   * @param e - The MouseEvent generated by the user
   */
  @Override
  public void mouseEntered(final MouseEvent e)
  {
  }
  
  /**
   * Handles the exiting of the mouse from a component.
   * 
   * @param e - The MouseEvent generated by the user
   */
  @Override
  public void mouseExited(final MouseEvent e)
  {
  }
  
  /**
   * Handles the pressing of the mouse.
   * 
   * @param e - The MouseEvent generated by the user
   */
  @Override
  public void mousePressed(final MouseEvent e)
  {
  }
  
  /**
   * Handles the releasing of a mouse press.
   * 
   * @param e - The MouseEvent generated by the user
   */
  @Override
  public void mouseReleased(final MouseEvent e)
  {
    parent.toFront();
  }
}
