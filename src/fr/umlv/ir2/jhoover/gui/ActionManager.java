/*
 * File : Actions.java 
 * Created : 6 janv. 2005
 * ====================================== 
 * PROJET BOSS ("http://boss.berlios.de")
 * ======================================
 */

package fr.umlv.ir2.jhoover.gui;

import java.awt.event.ActionEvent;
import java.util.HashSet;
import java.util.Set;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JToolBar;

import fr.umlv.ir2.jhoover.gui.tool.Icons;
import fr.umlv.ir2.jhoover.gui.tool.Labels;

/**
 * This class manages every actions of the application
 * 
 * @author bricou 
 */
public final class ActionManager
{
	private static final Set myActions = new HashSet();

//	private static abstract class MyAction extends AbstractAction
//	{
//		/**
//		 * Default constructor for an action that can not be activated or unactivated
//		 * 
//		 * @param szLabel
//		 * @param icon
//		 */
//		public MyAction(String szLabel, Icon icon)
//		{
//			super(szLabel, icon);
//			myActions.add(this);
//		}
//		
//		/**
//		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
//		 */
//		public abstract void actionPerformed (ActionEvent e);
//	}



	/**
	 * Action to quit the application
	 */
	public static final Action	quitAction = new AbstractAction(Labels.QUIT_LABEL, Icons.QUIT_ICON) {
		public void actionPerformed (ActionEvent e) {
			//TODO: quit
			System.out.println("Quit action");
		}
	};

	/**
	 * Action to display the help, according to the current view
	 */
	public static final Action helpAction = new AbstractAction (Labels.HELP_LABEL, Icons.HELP_ICON) {
		public void actionPerformed (ActionEvent e) {
			//TODO: help
			System.out.println("Help action");
		}
	};

	
	/**
	 * Action for displaying the preferences
	 */
	public static final Action	preferences = new AbstractAction (Labels.PREFERENCES_LABEL, null) {
		public void actionPerformed (ActionEvent e) {
//			TODO: preference
			System.out.println("Preference action");
		}
	};

	public static final Action visibleToolBar = new AbstractAction () {
		public void actionPerformed (ActionEvent arg0) {
			final JHToolBar toolBar = JHMainFrame.getInstance().getMyToolBar ();
			if (!JHMainFrame.getInstance().getMyMenuBar().getToolBarItem().isSelected ()) {
				toolBar.setVisible (false);
			}
			else {
				toolBar.setVisible (true);
			}
		}
	};
}