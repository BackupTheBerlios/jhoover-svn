/**
 * jHoover - UMLV IR2
 * UI Project
 */
package fr.umlv.ir2.jhoover.gui;

import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;

import fr.umlv.ir2.jhoover.gui.tool.Labels;

/**
 * ToolBar of jHoover
 * @author Romain Papuchon
 */
public final class JHToolBar extends JToolBar
{
	/**
	 * Default constructor which builds the toolbar with all its components and
	 * actions associated
	 */
	public JHToolBar () {
		super(SwingConstants.HORIZONTAL);
		setFloatable(false);
		addButtonToToolBar(ActionManager.newAction, Labels.NEW_LABEL);
		addButtonToToolBar(ActionManager.resumeAction, Labels.RESUME_LABEL);
		addButtonToToolBar(ActionManager.pauseAction, Labels.PAUSE_LABEL);
		addButtonToToolBar(ActionManager.stopAction, Labels.STOP_LABEL);
//		addButtonToToolBar(ActionManager.closeAction, Labels.CLOSE_LABEL);
//		addButtonToToolBar(ActionManager.exitAction, Labels.EXIT_LABEL);
		addButtonToToolBar(ActionManager.addFilterAction, Labels.ADD_FILTER_LABEL);
		addButtonToToolBar(ActionManager.modifyFilterAction, Labels.MODIFY_FILTER_LABEL);
		addButtonToToolBar(ActionManager.deleteFilterAction, Labels.DELETE_FILTER_LABEL);
		addButtonToToolBar(ActionManager.findAction, Labels.FIND_LABEL);
		addButtonToToolBar(ActionManager.configurationAction, Labels.CONFIGURATION_LABEL);
		addButtonToToolBar(ActionManager.colorsAction, Labels.COLORS_LABEL);
		addButtonToToolBar(ActionManager.helpAction, Labels.HELP_LABEL);
	}

	/**
	 * Add a button in the toolbar with an action and a toolTip
	 * @param action the action
	 * @param toolTip the toolTip
	 */
	private void addButtonToToolBar (final Action action, final String toolTip) {
		JButton myButton = new JButton (action);
		myButton.setToolTipText (toolTip);
		myButton.setText (null);
		myButton.setBorderPainted (false);
		add(myButton);
	}
}