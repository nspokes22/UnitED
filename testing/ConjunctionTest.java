package testing;

import static org.junit.jupiter.api.Assertions.*;

import javax.swing.JPanel;

import org.junit.jupiter.api.Test;

import data.*;
import gui.InputField;

class ConjunctionTest {

	@Test
	void testflip() {
		DashConjunction dc = new DashConjunction(new SingleUnit("a"), new SingleUnit("b"));
		Unit unit = Conjunction.flip(dc);
		assertEquals("1/a-b", unit.toString());
		unit = Conjunction.flip(unit);
		assertEquals("a-b", unit.toString());
		unit = Conjunction.flip(unit);
		assertEquals("1/a-b", unit.toString());
		unit = Conjunction.flip(unit);
		assertEquals("a-b", unit.toString());
		
		InputField field = new InputField(new JPanel(), null);
		//String delim = "\u00A7";
		String input = "1\u00A71/e-r";
		field.addText(input);
		field.invert();
		assertEquals("1\u00A7e/r", field.getText());
	}
}
