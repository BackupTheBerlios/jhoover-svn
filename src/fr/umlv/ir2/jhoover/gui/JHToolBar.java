

package fr.umlv.ir2.jhoover.gui;

import javax.swing.Action;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;

import fr.umlv.ir2.jhoover.gui.tool.Labels;




public final class JHToolBar extends JToolBar
{

	/**
	 * Default constructor which builds the toolbar with all its components and
	 * actions associated
	 */
	public JHToolBar ()
	{
		super(SwingConstants.HORIZONTAL);
		setFloatable(false);
		addButtonToToolBar (ActionManager.helpAction, Labels.HELP_LABEL);
		add (Box.createHorizontalStrut (10));
	}

	
	/**
	 * Add an action to the ToolBar in a JButton with a toolTipText
	 * 
	 * @param action action to execute
	 * @param szToolTipText tooltip for the button
	 */
	private void addButtonToToolBar (final Action action, final String szToolTipText)
	{
		JButton myButton = new JButton (action);
		myButton.setToolTipText (szToolTipText);
		myButton.setText (null);
		myButton.setBorderPainted (false);
		add(Box.createHorizontalStrut (5));
		add(myButton);
	}
}