package calc;

import data.Operand;
import data.Unit;
import utilities.Power;
/**
 * A CalcStrategy specifically for Integer Powers.
 * 
 * @author Samantha Steigleman-Cox
 * Complies with JMU honor code.
 */
public class IntegerPower implements CalcStrategy 
{
  
	private Integer power;
  
	/**
	 * Constructs IntergerPower Object, default power set to null.
	 */
	public IntegerPower()
	{
	  power = null;
	}
  
	@Override
	public boolean canCalculate(final Unit left, final Unit right) 
	{
		if (left != null && right != null)
		{
			return true;
		}
		return false;
	}

	/**
	 * Left operand is the actual Operand to that is to be calculated, 
	 * the second Operand only have a Digit value of the power of the first Operand.
	 * @param left Operand to be calculated
	 * @param right only holds power information for first Operand
	 * @return Operand calculated
	 */
	@Override
	public Operand calculate(final Operand left, final Operand right) 
	{
	  power = (int) right.getValue().getValueDouble();
	  Unit leftUnit = left.getUnit().copyUnit();
	  leftUnit.multiplyPower(power);
		return new Operand(left.getValue().pow(right.getValue()), leftUnit);
	}
	
	@Override
	public boolean isPower()
	{
		return true;
  }
	
	/**
	 * Returns spring representation of power.
	 * @return String 
	 */
	public String toString()
	{
	  String str = "";
	  if (power != null)
	  {
	    str = Power.createPower(power);
	  }
	  return str;
	}
}
