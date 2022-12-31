package data;

import calc.CalcStrategy;

/**
 * Encapsulation of data necessary for a full expression.
 * 
 * @author Nick Spokes
 * @version 1 This work complies with the JMU Honor Code.
 */
public class CompositeExpression implements Expression
{
  private static final String SPACE = " ";
  private CalcStrategy strat;
  private Expression left;
  private Expression right;
  private Operand answer;

  /**
   * Constructs a CompositeExpression object.
   * 
   * @param left
   *          - The left operand
   * @param strat
   *          - The operation the calculation will use (ie: addition, subtraction, etc)
   */
  public CompositeExpression(final Operand left, final CalcStrategy strat)
  {
    this.left = left;
    this.strat = strat;
  }

  /**
   * Constructor.
   * 
   * @param left
   *          the left side of the expression
   * @param strat
   *          the strategy to use
   * @param right
   *          the right side of the expression
   */
  public CompositeExpression(final Operand left, final CalcStrategy strat, final Operand right)
  {
    this.left = left;
    this.strat = strat;
    this.right = right;
  }

  /**
   * Determines whether the stored expression can be calculated.
   * 
   * @return - Whether the stored expression can be calculated
   */
  public boolean canCalculate()
  {
    return strat.canCalculate(left.getUnit(), right.getUnit());
  }

  /**
   * Sets the right operand.
   * 
   * @param right
   *          - The right operand
   */
  public void setRight(final Expression right)
  {
    this.right = right;
  }

  /**
   * Sets the left operand.
   * 
   * @param left
   *          - the left operand.
   */
  public void setLeft(final Expression left)
  {
    this.left = left;
  }

  /**
   * Gets the value the expression evaluates to.
   * 
   * @return - Value the expression evaluates to
   */
  @Override
  public Digit getValue()
  {
    checkNull();
    return answer.getValue();
  }

  /**
   * Gets the unit the expression evaluates to.
   * 
   * @return - Unit the expression evaluates to
   */
  @Override
  public Unit getUnit()
  {
    checkNull();
    return answer.getUnit();
  }

  /**
   * Checks if the expression has already been evaluated. If it has not been evaluated yet, a
   * calculation is performed.
   */
  private void checkNull()
  {
    if (answer == null)
    {
      answer = strat.calculate((Operand) left, (Operand) right);
    }
  }

  /**
   * return the left operand.
   * 
   * @return the left side of the equation
   */
  public Expression getLeft()
  {
    return left;
  }

  /**
   * return the right operand.
   * 
   * @return the right side of the equation
   */
  public Expression getRight()
  {
    return right;
  }

  /**
   * return the CalcStrategy we are using.
   * 
   * @return the calc strat
   */
  public CalcStrategy getStrategy()
  {
    return this.strat;
  }

  /**
   * Gets the String representation of the current CompositeExpression without the answer.
   * 
   * @return - String representation of the current CompositeExpression
   */
  @Override
  public String toString()
  {
    String str = "";
    if (strat.isPower())
    {
      if (right == null)
      {
        str = left.toString();
      }
      else
      {
        str = "(" + left.toString() + ")" + strat.toString();
      }
    }
    else
    {
      str = left.toString() + SPACE + strat.toString();
      if (right != null)
      {
        str += SPACE + right.toString();
      }
    }
    return str;
  }

  /**
   * Gets the String representation of the current CompositeExpression with the answer.
   * 
   * @return - String representation of the current CompositeExpression
   */
  public String toFullString()
  {
    String str = this.toString();
    str += SPACE + this.getValue().getValueDouble() + SPACE + this.getUnit().toString();
    return str;
  }
}
