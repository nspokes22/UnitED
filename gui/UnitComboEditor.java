package gui;

import javax.swing.plaf.metal.MetalComboBoxEditor;

/**
 * Text editor for the unit dropdown.
 * 
 * @author Nick Spokes
 * @version 1
 * 
 *          This work complies with the JMU Honor Code.
 */
public class UnitComboEditor extends MetalComboBoxEditor
{
  /**
   * Constructs a UnitComboEditor object.
   * 
   * @param field - InputField object this was created by
   */
  public UnitComboEditor(final InputField field)
  {
    super();
    UnitComboFormatter format = new UnitComboFormatter(field);
    super.editor = format.getField();
  }
}
