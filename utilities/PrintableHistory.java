package utilities;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Allows the history to be printed.
 * 
 * @author Nick Spokes
 * @version 1
 *
 */
public class PrintableHistory implements Printable
{
  private static final Font DEFAULT_FONT = new Font("Tahoma", Font.PLAIN, 14);
  private static final int MAX_LENGTH = 80;
  private static final String SPACING = "    ";

  private int calls;
  private List<Integer> breaks;
  private List<String> lines;

  /**
   * Constructor.
   */
  public PrintableHistory()
  {
    calls = 0;
    lines = new LinkedList<String>();
  }

  /**
   * adds an entry to the list.
   * 
   * @param entry
   *          the entry to be added
   */
  public void addEntry(final String entry)
  {
    String text = SPACING + ++calls + ":";
    if (entry.length() <= MAX_LENGTH)
    {
      lines.add(text + " " + entry);
    }
    else
    {
      lines.add(text);
      formatText(entry);
    }
  }

  private void addSub(final String sub)
  {
    lines.add(SPACING + SPACING + sub);
  }

  private List<String> consolidateParts(final String str, final Map<Integer, Integer> map)
  {
    int curr = 0;
    int last = 0;
    List<String> list = new LinkedList<String>();
    for (Integer i : map.keySet())
    {
      curr = map.get(i);
      splitLength(str.substring(last, curr));
      last = curr + 1;
    }
    return list;
  }

  /**
   * Formats the text.
   * 
   * @param old
   *          the text that is going to be formatted
   */
  private void formatText(final String old)
  {
    int count = 0;
    char[] charArray = old.toCharArray();
    Map<Integer, Integer> map = new HashMap<Integer, Integer>();
    for (int i = 0; i < charArray.length; i++)
    {
      if (charArray[i] == ' ')
      {
        map.put(++count, i);
      }
    }
    map.put(++count, old.length());
    consolidateParts(old, map);
  }

  @Override
  public int print(final Graphics graphics, final PageFormat pageFormat, final int pageIndex)
      throws PrinterException
  {
    int breakQty = 0;
    int end = 0;
    int lHeight = graphics.getFontMetrics(DEFAULT_FONT).getHeight();
    int linesOnPage = (int) pageFormat.getImageableHeight() / lHeight;
    int pos = 0;
    int start = 0;
    int status = NO_SUCH_PAGE;
    Graphics2D g2d;

    breakQty = (lines.size() - 1) / linesOnPage;
    breaks = new ArrayList<Integer>(breakQty);
    for (int i = 0; i < breakQty; i++)
    {
      breaks.add((i + 1) * linesOnPage);
    }

    if (pageIndex <= breaks.size())
    {
      g2d = (Graphics2D) graphics;
      g2d.translate(pageFormat.getImageableX(), pageFormat.getImageableY());

      if (pageIndex > 0)
      {
        start = breaks.get(pageIndex - 1);
      }

      if (pageIndex == breaks.size())
      {
        end = lines.size();
      }
      else
      {
        end = breaks.get(pageIndex);
      }

      for (int j = start; j < end; j++)
      {
        pos += lHeight;
        graphics.drawString(lines.get(j), 0, pos);
      }
      status = PAGE_EXISTS;
    }
    return status;
  }

  private void splitLength(final String old)
  {
    if (old.length() <= MAX_LENGTH)
    {
      addSub(old);
    }
    else
    {
      addSub(old.substring(0, MAX_LENGTH));
      splitLength(old.substring(MAX_LENGTH, old.length()));
    }
  }
}
