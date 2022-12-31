package gui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import calc.Addition;
import calc.CalcStrategy;
import calc.Division;
import calc.IntegerPower;
import calc.Multiplication;
import calc.Subtraction;
import calc.UnitConversionEnum;
import data.CompositeExpression;
import data.Digit;
import data.Operand;
import data.SingleUnit;
import data.Unit;
import data.io.InputParser;
import utilities.UnitException;

/**
 * This class creates the main window for the calculator's GUI.
 * 
 * @author Nick Spokes, Luke Bieniek
 * @version 1
 * 
 *          This work complies with the JMU Honor Code.
 */
public class UnitEDWindow extends JFrame
    implements ActionListener, ComponentListener, WindowListener, KeyEventDispatcher, ItemListener
{
  // ResourceBundle
  public static final ResourceBundle STRINGS = ResourceBundle.getBundle("customization.Strings");

  // Serial version UID
  private static final long serialVersionUID = 1L;

  // Button names
  private static final String FILE = UnitEDWindow.STRINGS.getString("FILE");
  private static final String HELP = UnitEDWindow.STRINGS.getString("HELP");
  private static final String ABOUT = UnitEDWindow.STRINGS.getString("ABOUT");
  private static final String BACKSPACE = "\u232B";
  private static final String CLEAR = "C";
  private static final String DIVIDE = "\u00F7";
  private static final String EQUAL = "=";
  private static final String HISTORY = ">";
  private static final String INVERSE_HISTORY = "<";
  private static final String INVERT = "<html><font size=\"-1\"><b><sup>1</sup>&frasl;<sub>x</sub>"
      + "</b></font></html>";
  private static final String MINUS = "-";
  private static final String MULTIPLY = "x";
  private static final String PLUS = "+";
  private static final String POWER = "<html><font size=\"-1\"><b>X<sup>y</sup></b></font></html>";
  private static final String PRINT = UnitEDWindow.STRINGS.getString("PRINT");
  private static final String RESET = "R";
  private static final String SIGN = "\u00B1";

  // Other String constants
  private static final String EMPTY = "";
  private static final String SPACE = " ";
  private static final String ZERO = "0";

  // Constant parameters
  private static final Font DEFAULT_FONT = new Font("Tahoma", Font.PLAIN, 26);
  private static final Insets BOTTOM = new Insets(0, 0, 0, 5);
  private static final Insets LEFT = new Insets(0, 0, 5, 5);
  private static final Insets RIGHT = new Insets(0, 0, 5, 0);

  // Used components
  private CompositeExpression expression;
  private DisplayField display;
  private HistoryDisplay historyDisplay;
  private Image icon;
  private InputField input;
  private JButton backspace;
  private JButton divBtn;
  private JButton equalBtn;
  private JButton historyBtn;
  private JButton minusBtn;
  private JButton multiBtn;
  private JButton plusBtn;
  private JButton resetBtn;
  private JButton[] numBtns;
  private JComboBox<String> resultDrop;
  private JPanel btnPnl;
  private Operand answer;

  private List<JButton> buttons = new ArrayList<JButton>();

  /**
   * Creates the main window for the calculator's GUI.
   */
  public UnitEDWindow()
  {
    super();
    super.setBounds(100, 100, 525, 693);
    super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    super.getContentPane().setLayout(new BorderLayout(0, 0));
    super.setTitle("UnitED");
    icon = new ImageIcon(this.getClass().getResource("/customization/default_logo.png")).getImage();
    super.setIconImage(icon);
    super.setResizable(false);
    super.setAlwaysOnTop(true);
    initializeWindow();
    addComponentListener(this);
    addWindowListener(this);
    addMouseListener(historyDisplay);
    KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(this);
    for (Component jc : super.getComponents())
    {
      ((JComponent) jc).setDoubleBuffered(true);
    }
    super.setVisible(true);
  }

  /**
   * Handles the ActionEvents generating by the user pressing soft buttons.
   * 
   * @param e
   *          - The ActionEvent generated by the user pressing a button
   */
  @Override
  public void actionPerformed(final ActionEvent e)
  {
    String activate = null;
    String command = e.getActionCommand();
    String entryAsString = null;
    if (Character.isDigit(command.toCharArray()[0]))
    {
      input.addText(command);
    }
    else if (command.equals(RESET))
    {
      display.empty();
      input.empty();
      depopulateResults();
      equalBtn.setEnabled(false);
      answer = null;
    }
    else if (command.equals(CLEAR))
    {
      input.empty();
    }
    else if (command.equals(SIGN))
    {
      input.negate();
    }
    else if (command.equals(BACKSPACE))
    {
      input.backspace();
    }
    else if (command.equals(HISTORY))
    {
      historyDisplay.animate();
      historyBtn.setText(INVERSE_HISTORY);
    }
    else if (command.equals(INVERSE_HISTORY))
    {
      historyDisplay.animate();
      historyBtn.setText(HISTORY);
    }
    else if (command.equals(INVERT))
    {
      input.invert();
    }
    else if (command.equals(PLUS))
    {
      operatorHelper(null, new Addition());
    }
    else if (command.equals(MINUS))
    {
      operatorHelper(null, new Subtraction());
    }
    else if (command.equals(MULTIPLY))
    {
      operatorHelper(null, new Multiplication());
    }
    else if (command.equals(DIVIDE))
    {
      operatorHelper(null, new Division());
    }
    else if (command.equals(POWER))
    {
      operatorHelper(null, new IntegerPower());
    }
    else if (command.equals(PRINT))
    {
      historyDisplay.initiatePrint();
    }
    else if (command.equals(ABOUT))
    {
      new AboutWindow(icon);
    }
    else if (command.equals(EQUAL))
    {
      try
      {
        expression.setRight(InputParser.parseInput(input.getText()));
        if (expression.getStrategy().toString().equals(DIVIDE))
        {
          if (expression.getRight().getValue().getValueDouble() == 0.0)
          {
            throw new UnitException(UnitException.DIVIDE_BY_ZERO);
          }
        }
        if (expression.getStrategy().toString().equals(PLUS)
            || expression.getStrategy().toString().equals(MINUS))
        {
          Unit leftUnit = expression.getLeft().getUnit();
          Unit rightUnit = expression.getRight().getUnit();
          if (!leftUnit.toString().equals(rightUnit.toString()))
          {
            if (!UnitConversionEnum.isEnum(leftUnit) || !UnitConversionEnum.isEnum(rightUnit)
                || !UnitConversionEnum.getEnum(leftUnit.toString()).getType()
                    .equals(UnitConversionEnum.getEnum(rightUnit.toString()).getType()))
            {
              throw new UnitException(UnitException.UNIT_MISMATCH);
            }
          }
        }
        if (expression.canCalculate())
        {
          this.answer = UnitConversionEnum.checkUnits(expression);
          entryAsString = expression.toString() + " = " + answer.toString();
          display.setText(entryAsString);
          historyDisplay.addEntry(entryAsString, expression.getLeft(), expression.getRight(),
              answer);
          input.empty();
          equalBtn.setEnabled(false);
          activate = UnitConversionEnum.shouldActivateDrop(expression);
          if (activate != null)
          {
            resultDrop.setEnabled(true);
            populateResults(activate, answer.getUnit());
          }
        }
      }
      catch (IllegalArgumentException iae)
      {
        UnitException.genericError();
      }
      catch (UnitException ue)
      {
        ue.match();
      }
    }
  }

  /**
   * Replaces the Displays text with what is given in the newDisplay parameter.
   * 
   * @param newDisplay
   *          - The String that the Display will be set to contain
   */
  public void changeDisplay(final String newDisplay)
  {
    display.setText(newDisplay);
  }

  /**
   * Replaces the InputField's text with what is given in the newInput variable.
   * 
   * @param newInput
   *          - The String that the InputField will be set to contain
   */
  public void changeInput(final String newInput)
  {
    input.empty();
    input.addText(newInput);
  }

  /**
   * Handles the hiding of components.
   * 
   * @param e
   *          - The ComponentEvent generated by the user
   */
  @Override
  public void componentHidden(final ComponentEvent e)
  {
  }

  /**
   * Handles the moving of components.
   * 
   * @param e
   *          - The ComponentEvent generated by the user
   */
  @Override
  public void componentMoved(final ComponentEvent e)
  {
    historyDisplay.adjustPositioning();
  }

  /**
   * Handles the resizing of components.
   * 
   * @param e
   *          - The ComponentEvent generated by the user
   */
  @Override
  public void componentResized(final ComponentEvent e)
  {
  }

  /**
   * Handles the showing of components.
   * 
   * @param e
   *          - The ComponentEvent generated by the user
   */
  @Override
  public void componentShown(final ComponentEvent e)
  {
  }

  /**
   * Creates a button with the input parameters without a specified grid width.
   * 
   * @param name
   *          - Name of the button
   * @param insets
   *          - Spacing between buttons
   * @param x
   *          - X position within the grid bag layout
   * @param y
   *          - Y position within the grid bag layout
   * @return - Resulting button
   */
  private JButton createButton(final String name, final Insets insets, final int x, final int y)
  {
    return createButton(name, 1, insets, x, y);
  }

  /**
   * Creates a button with the input parameters with a specified grid width.
   * 
   * @param name
   *          - Name of the button
   * @param width
   *          - Number of spaces to use horizontally within the grid
   * @param insets
   *          - Spacing between buttons
   * @param x
   *          - X position within the grid bag layout
   * @param y
   *          - Y position within the grid bag layout
   * @return - Resulting button
   */
  private JButton createButton(final String name, final int width, final Insets insets, final int x,
      final int y)
  {
    JButton button = new JButton(name);
    GridBagConstraints gbc = new GridBagConstraints();
    gbc.gridwidth = width;
    gbc.fill = GridBagConstraints.BOTH;
    gbc.insets = insets;
    gbc.gridx = x;
    gbc.gridy = y;
    btnPnl.add(button, gbc);
    button.addActionListener(this);
    button.setFont(new Font(button.getFont().getName(), Font.PLAIN, 26));
    buttons.add(button);
    CustomizeEDWindow.setButtonColor(CustomizeEDWindow.getButtonsColor(),
        CustomizeEDWindow.getLetterColor(), button);
    return button;
  }

  /**
   * Depopulates the resulting unit dropdown.
   */
  private void depopulateResults()
  {
    resultDrop.setEnabled(false);
    resultDrop.removeAllItems();
  }

  /**
   * Handles the pressing of a hard key.
   * 
   * @param e
   *          - The KeyEvent generated by the user
   */
  @Override
  public boolean dispatchKeyEvent(final KeyEvent e)
  {
    Character key = e.getKeyChar();
    if (e.getID() == KeyEvent.KEY_PRESSED)
    {
      if (!input.compareSource(e.getSource().getClass()))
      {
        if (Character.isDigit(key))
        {
          numBtns[Character.getNumericValue(key)].doClick();
        }
        else
        {
          switch (key)
          {
            case '.':
              if (expression == null || !expression.getStrategy().isPower())
              {
                input.addText(EMPTY + key.toString());
              }
              break;
            case '-':
              minusBtn.doClick();
              break;
            case '/':
              divBtn.doClick();
              break;
            case '*':
              multiBtn.doClick();
              break;
            case '+':
              plusBtn.doClick();
              break;
            case '=':
              equalBtn.doClick();
              break;
            default:
              if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE)
              {
                backspace.doClick();
              }
              else if (e.getKeyCode() == KeyEvent.VK_DELETE)
              {
                resetBtn.doClick();
              }
              else if (e.getKeyCode() == KeyEvent.VK_ENTER)
              {
                equalBtn.doClick();
              }
              else if (Character.isAlphabetic(e.getKeyCode()) || key == '$')
              {
                input.comboFocus(key, true);
              }
          }
        }
      }
      else
      {
        if (e.getKeyCode() == KeyEvent.VK_ENTER || key == '=')
        {
          super.requestFocus();
          equalBtn.doClick();
        }
        else if (key == '*')
        {
          multiBtn.doClick();
          super.requestFocus();
        }
        else if (key == '+')
        {
          plusBtn.doClick();
          super.requestFocus();
        }

      }
    }
    return false;
  }

  /**
   * Initializes the main window for the calculator's GUI.
   */
  private void initializeWindow()
  {
    CustomizeEDWindow.readFile();

    // Main panel
    JPanel mainPnl = new JPanel();
    super.getContentPane().add(mainPnl, BorderLayout.CENTER);
    mainPnl.setLayout(new BorderLayout(0, 0));

    // Exterior logo panel
    JPanel logoPnl = new JPanel();
    mainPnl.add(logoPnl, BorderLayout.NORTH);
    logoPnl.setLayout(new GridLayout(0, 2, 0, 0));

    // Interior logo panel
    JPanel intLogoPnl = new JPanel();
    logoPnl.add(intLogoPnl);
    intLogoPnl.setLayout(new BoxLayout(intLogoPnl, BoxLayout.X_AXIS));
    intLogoPnl.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

    // unitED Logo
    JLabel logo = CustomizeEDWindow.getLogo();
    intLogoPnl.add(logo);

    // Button and text field panel
    btnPnl = new JPanel();
    mainPnl.add(btnPnl, BorderLayout.CENTER);
    btnPnl.setBorder(BorderFactory.createEmptyBorder(0, 20, 10, 0));
    GridBagLayout btnPnlGbl = new GridBagLayout();
    btnPnlGbl.columnWidths = new int[] {56, 56, 56, 0, 56, 56, 0};
    btnPnlGbl.rowHeights = new int[] {0, 0, 40, 40, 40, 40, 40, 0};
    btnPnlGbl.columnWeights = new double[] {1.0, 1.0, 1.0, 0.0, 1.0, 1.0, Double.MIN_VALUE};
    btnPnlGbl.rowWeights = new double[] {1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, Double.MIN_VALUE};
    btnPnl.setLayout(btnPnlGbl);

    // Display field
    display = new DisplayField(btnPnl, DEFAULT_FONT);

    // Result panel
    JPanel resultPanel = new JPanel();
    GridBagConstraints resultGbc = new GridBagConstraints();
    resultGbc.insets = new Insets(0, 0, 5, 0);
    resultGbc.fill = GridBagConstraints.BOTH;
    resultGbc.gridx = 5;
    resultGbc.gridy = 0;
    btnPnl.add(resultPanel, resultGbc);
    resultPanel.setLayout(new GridLayout(0, 1, 0, 0));
    resultPanel.setBorder(BorderFactory.createEmptyBorder(10, 2, 10, 0));

    // Result dropdown
    resultDrop = new JComboBox<String>();
    resultPanel.add(resultDrop);
    // CustomizeEDWindow.setColor(CustomizeEDWindow.getButtonsColor(), resultDrop);
    resultDrop.setEnabled(false);
    resultDrop.addItemListener(this);

    // Input field
    input = new InputField(btnPnl, DEFAULT_FONT);

    // Create sign, clear, and reset buttons
    createButton(SIGN, LEFT, 0, 2);
    createButton(CLEAR, LEFT, 1, 2);
    resetBtn = createButton(RESET, LEFT, 2, 2);

    // Strut (used for spacing between 3rd and 4th column of buttons)
    Component strut = Box.createHorizontalStrut(5);
    GridBagConstraints strutGbc = new GridBagConstraints();
    strutGbc.insets = LEFT;
    strutGbc.gridx = 3;
    strutGbc.gridy = 2;
    btnPnl.add(strut, strutGbc);

    // Starting from the plus button, create the rest of the buttons
    numBtns = new JButton[10];
    plusBtn = createButton(PLUS, LEFT, 4, 2);
    createButton(POWER, RIGHT, 5, 2);
    numBtns[7] = createButton("7", LEFT, 0, 3);
    numBtns[8] = createButton("8", LEFT, 1, 3);
    numBtns[9] = createButton("9", LEFT, 2, 3);
    minusBtn = createButton(MINUS, LEFT, 4, 3);
    createButton(INVERT, RIGHT, 5, 3);
    numBtns[4] = createButton("4", LEFT, 0, 4);
    numBtns[5] = createButton("5", LEFT, 1, 4);
    numBtns[6] = createButton("6", LEFT, 2, 4);
    multiBtn = createButton(MULTIPLY, LEFT, 4, 4);
    numBtns[1] = createButton("1", LEFT, 0, 5);
    numBtns[2] = createButton("2", LEFT, 1, 5);
    numBtns[3] = createButton("3", LEFT, 2, 5);
    divBtn = createButton(DIVIDE, LEFT, 4, 5);
    numBtns[0] = createButton(ZERO, 2, BOTTOM, 0, 6);
    backspace = createButton(BACKSPACE, BOTTOM, 2, 6);

    // Equals button
    equalBtn = createButton(EQUAL, BOTTOM, 4, 6);
    equalBtn.setEnabled(false);

    // History button panel
    JPanel historyBtnPnl = new JPanel();
    super.getContentPane().add(historyBtnPnl, BorderLayout.EAST);
    GridBagLayout historyBtnPnlGbl = new GridBagLayout();
    historyBtnPnl.setLayout(historyBtnPnlGbl);
    historyBtnPnl.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 0));

    // History button
    historyBtn = new JButton(HISTORY);
    CustomizeEDWindow.setButtonColor(CustomizeEDWindow.getButtonsColor(),
        CustomizeEDWindow.getLetterColor(), historyBtn);
    buttons.add(historyBtn);
    historyBtn.setMargin(new Insets(5, 0, 5, 0));
    GridBagConstraints historyGbc = new GridBagConstraints();
    historyGbc.insets = new Insets(0, 0, 0, 3);
    historyGbc.gridx = 0;
    historyGbc.gridy = 1;
    historyBtnPnl.add(historyBtn, historyGbc);
    historyBtn.addActionListener(this);

    // History frame
    this.historyDisplay = new HistoryDisplay(this, input);
    equalBtn.addMouseListener(historyDisplay);
    CustomizeEDWindow.setHistoryDisplay(CustomizeEDWindow.getBackgroundColor(),
        historyDisplay.getHistoryPanel());

    // Menu bar
    JMenuBar menuBar = new JMenuBar();
    super.setJMenuBar(menuBar);

    // File menu and file menu items
    JMenu fileMenu = new JMenu(FILE);
    menuBar.add(fileMenu);
    JMenuItem printItem = new JMenuItem(PRINT);
    fileMenu.add(printItem);
    printItem.addActionListener(this);

    // Help menu and help menu items
    JMenu helpMenu = new JMenu(HELP);
    menuBar.add(helpMenu);
    JMenuItem aboutItem = new JMenuItem(ABOUT);
    helpMenu.add(aboutItem);
    aboutItem.addActionListener(this);

    CustomizeEDWindow.setBackgroundColor(CustomizeEDWindow.getBackgroundColor(), resultPanel,
        btnPnl);
    CustomizeEDWindow.setBorderColor(CustomizeEDWindow.getBorderColor(), intLogoPnl, logoPnl,
        historyBtnPnl);
  }

  /**
   * Handles the selections from the Resulting units dropdown.
   * 
   * @param e
   *          - The ItemEvent generated by the user selecting an item in the resulting unit dropdown
   */
  @SuppressWarnings("unchecked")
  @Override
  public void itemStateChanged(final ItemEvent e)
  {
    if (e.getStateChange() == ItemEvent.SELECTED)
    {
      JComboBox<String> source = (JComboBox<String>) e.getSource();
      Operand leftOperand = new Operand(new Digit(ZERO),
          new SingleUnit(source.getSelectedItem().toString()));
      CompositeExpression compositeExpression = new CompositeExpression(leftOperand,
          new Addition());
      compositeExpression.setRight(answer);
      Operand answerConverted;

      if (!answer.getUnit().toString().equals(EMPTY)) // with unit
      {
        answerConverted = UnitConversionEnum.checkUnits(compositeExpression);
        answerConverted.getUnit().setPower(answer.getUnit().getPower());
      }
      else // no unit
      {
        answerConverted = answer;
        depopulateResults();
      }
      String fullExpression = display.getText();
      String[] fullExpressionSplit = fullExpression.split(SPACE);
      StringBuilder sb = new StringBuilder();

      if (!answerConverted.getUnit().toString().equals(EMPTY)) // with unit
      {
        fullExpressionSplit[fullExpressionSplit.length - 1] = answerConverted.getUnit().toString();
        fullExpressionSplit[fullExpressionSplit.length - 2] = answerConverted.getValue().toString();
        for (int i = 0; i < fullExpressionSplit.length - 2; i++)
        {
          sb.append(fullExpressionSplit[i] + SPACE);
        }
      }
      else // no unit
      {
        fullExpressionSplit[fullExpressionSplit.length - 1] = answerConverted.getValue().toString();
        for (int i = 0; i < fullExpressionSplit.length - 1; i++)
        {
          sb.append(fullExpressionSplit[i] + SPACE);
        }
      }
      sb.append(answerConverted.toString());
      display.setText(sb.toString());
    }
  }

  /**
   * Serves as a helper method for the actionPerformed() method.
   * 
   * @param in
   *          - Input operand used for running calculations
   * @param strat
   *          - A string representing the operation button that was pressed
   */
  private void operatorHelper(final Operand in, final CalcStrategy strat)
  {
    Operand op = null;
    try
    {
      if (in == null)
      {
        op = InputParser.parseInput(input.getText());
      }
      else
      {
        op = in;
      }
      this.expression = new CompositeExpression(op, strat);
      display.setText(expression.toString());
      input.empty();
      equalBtn.setEnabled(true);
      depopulateResults();
    }
    catch (IllegalArgumentException | NullPointerException ex)
    {
      ex.printStackTrace();
      UnitException.genericError();
    }
    catch (UnitException ue)
    {
      if (ue.getErrorType() != UnitException.NO_INPUT || answer == null)
      {
        ue.match();
      }
      else
      {
        operatorHelper(answer, strat);
      }
    }
  }

  /**
   * Populates the resulting unit dropdown.
   * 
   * @param type
   *          - Type of unit
   * @param defaultUnit
   *          - Unit used before user selects anything
   */
  private void populateResults(final String type, final Unit defaultUnit)
  {
    for (UnitConversionEnum unit : UnitConversionEnum.values())
    {
      if (unit.getType().equals(type))
      {
        resultDrop.addItem(unit.getUnit());
      }
    }
    resultDrop.setSelectedItem(defaultUnit.getName());
    CustomizeEDWindow.setButtonColor(CustomizeEDWindow.getButtonsColor(),
        CustomizeEDWindow.getLetterColor(), resultDrop);
  }

  /**
   * Sets UnitEDWindow's answer attribute to what is given in the answer parameter.
   * 
   * @param answer
   *          - The Operand that the answer attribute will be assigned to
   */
  public void setAnswer(final Operand answer)
  {
    this.answer = answer;
  }

  /**
   * Handles the activating of the window.
   * 
   * @param e
   *          - The WindowEvent generated by the user
   */
  @Override
  public void windowActivated(final WindowEvent e)
  {
    super.setAlwaysOnTop(true);
    historyDisplay.makeActiveHistoryDisplay();
    super.setVisible(true);
  }

  /**
   * Handles anything to do after the window is closed.
   * 
   * @param e
   *          - The WindowEvent generated by the user
   */
  @Override
  public void windowClosed(final WindowEvent e)
  {
  }

  /**
   * Handles the closing of the window.
   * 
   * @param e
   *          - The WindowEvent generated by the user
   */
  @Override
  public void windowClosing(final WindowEvent e)
  {
  }

  /**
   * Handles the deactivating of the window.
   * 
   * @param e
   *          - The WindowEvent generated by the user
   */
  @Override
  public void windowDeactivated(final WindowEvent e)
  {
    historyDisplay.setVisible(false);
    super.setAlwaysOnTop(false);
    super.setState(WindowEvent.WINDOW_ICONIFIED);
  }

  /**
   * Handles the maximizing of the window.
   * 
   * @param e
   *          - The WindowEvent generated by the user
   */
  @Override
  public void windowDeiconified(final WindowEvent e)
  {
    historyDisplay.openHistoryDisplay();
  }

  /**
   * Handles the minimizing of the window.
   * 
   * @param e
   *          - The WindowEvent generated by the user
   */
  @Override
  public void windowIconified(final WindowEvent e)
  {
    historyDisplay.closeHistoryDisplay();
  }

  /**
   * Handles the opening of the window.
   * 
   * @param e
   *          - The WindowEvent generated by the user
   */
  @Override
  public void windowOpened(final WindowEvent e)
  {
    super.toFront();
  }
}
