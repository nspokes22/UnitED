package utilities;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JDialog;
import javax.swing.Timer;
import gui.HistoryDisplay;
import gui.UnitEDWindow;

/**
 * Animation for the slide out history panel.
 * 
 * @author Nick Spokes
 * @version 1
 * 
 *          This work complies with the JMU Honor Code.
 */
public class Animation implements ActionListener, Runnable
{
  private static final int SPEED = 15;

  private static final int IN_FACTOR = 0;
  private static final int OUT_FACTOR = 10;
  private boolean stopped;
  private int move;
  private int target;
  private HistoryDisplay display;
  private JDialog frame;
  private Timer time;
  private UnitEDWindow parent;

  /**
   * Constructs an Animation object.
   * 
   * @param frame
   *          - History's body
   * @param display
   *          - History display object itself
   * @param parent
   *          - Main window
   */
  public Animation(final JDialog frame, final HistoryDisplay display, final UnitEDWindow parent)
  {
    this.display = display;
    this.frame = frame;
    this.parent = parent;
    stopped = false;
  }

  /**
   * Handles each action event created by the Timer object in order to animate the window in
   * "bursts" put together that become a seamless animation.
   * 
   * @param ae
   *          - ActionEvent for the current timer iteration
   */
  @Override
  public void actionPerformed(final ActionEvent ae)
  {
    int pos = frame.getX();
    int comparison = 0;
    if (move > 0)
    {
      pos = Math.min(target, pos - move);
    }
    else
    {
      pos = Math.max(target, pos - move);
    }

    if (stopped)
    {
      stop(ae);
    }
    else
    {
      frame.setLocation(pos, display.determineY());
    }

    pos = frame.getX();
    if (display.isOut())
    {
      comparison = parent.getX() + parent.getWidth() - OUT_FACTOR;
      if (pos == comparison)
      {
        stop(ae);
      }
      else if (pos > comparison)
      {
        stop(ae);
        frame.setLocation(comparison, display.determineY());
        frame.repaint();

      }
    }
    else
    {
      if (pos <= parent.getX() + parent.getWidth() - frame.getWidth() - IN_FACTOR)
      {
        stop(ae);
        frame.setVisible(false);
      }
    }
  }

  /**
   * Initiates the animation in a different thread from the GUI components.
   */
  @Override
  public void run()
  {
    move = 0;
    target = 0;
    target = frame.getX();
    if (display.isOut())
    {
      move = SPEED * -1;
    }
    else
    {
      move = SPEED;
    }
    target += move;
    time = new Timer(40, this);
    time.start();
  }

  /**
   * Forcibly stops the timer.
   */
  public void stop()
  {
    stopped = true;
  }

  /**
   * Stops the timer when either forcibly stopped or naturally stopped.
   * 
   * @param ae
   *          - ActionEvent for the last iteration of the Timer
   */
  private void stop(final ActionEvent ae)
  {
    ((Timer) ae.getSource()).stop();
  }
}
