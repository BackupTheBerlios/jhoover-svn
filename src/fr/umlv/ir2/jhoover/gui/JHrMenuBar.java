

package fr.umlv.ir2.jhoover.gui;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.Action;
import javax.swing.ButtonGroup;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JToolBar;
import javax.swing.KeyStroke;

import fr.umlv.ir2.jhoover.gui.tool.Labels;




public class JHrMenuBar extends JMenuBar// implements ChangeListener
{
	private JCheckBoxMenuItem toolBarItem = null;
	
	/**
	 * Default constructor
	 */
	public JHrMenuBar ()
	{
		add (createFileMenu());
		add (createEditMenu());
		add (createPreferencesMenu());
		add (createHelpMenu());
	}

	/**
	 * Creates the menu "Fichier" and returns it
	 * 
	 * @return JMenu the menu "Fichier" created
	 */
	private JMenu createFileMenu ()
	{
		JMenu fileMenu = new JMenu (Labels.MENUFILE_LABEL);
		fileMenu.setMnemonic ('F');
		// Menu Item
		// Ajout au menu
//		fileMenu.add (createItem (ActionManager.printAction  , KeyStroke.getKeyStroke (KeyEvent.VK_P , ActionEvent.CTRL_MASK)));
//		fileMenu.add (createItem (ActionManager.exportAction  , KeyStroke.getKeyStroke (KeyEvent.VK_E , ActionEvent.CTRL_MASK)));
//		fileMenu.add (createItem (ActionManager.mailAction , KeyStroke.getKeyStroke (KeyEvent.VK_M , ActionEvent.CTRL_MASK)));
//		fileMenu.addSeparator ();
//		fileMenu.add (createItem (ActionManager.logoutAction , KeyStroke.getKeyStroke (KeyEvent.VK_D , ActionEvent.CTRL_MASK)));
		fileMenu.addSeparator ();
		fileMenu.add (createItem (ActionManager.quitAction, KeyStroke.getKeyStroke (KeyEvent.VK_Q , ActionEvent.CTRL_MASK)));
		return fileMenu;
	}

	/**
	 * Creates the menu "Edition" with the actions associated and returns it
	 * 
	 * @return JMenu Edition menu
	 */
	private JMenu createEditMenu ()
	{
		JMenu editMenu = new JMenu (Labels.MENUEDIT_LABEL);
		editMenu.setMnemonic ('E');
//		editMenu.add (createItem (ActionManager.undoAction  , KeyStroke.getKeyStroke (KeyEvent.VK_Z , ActionEvent.CTRL_MASK)));
//		editMenu.add (createItem (ActionManager.redoAction , KeyStroke.getKeyStroke (KeyEvent.VK_Y , ActionEvent.CTRL_MASK)));
		editMenu.addSeparator ();
//		editMenu.add (createItem (ActionManager.copyAction, KeyStroke.getKeyStroke (KeyEvent.VK_C , ActionEvent.CTRL_MASK)));
//		editMenu.add (createItem (ActionManager.cutAction , KeyStroke.getKeyStroke (KeyEvent.VK_X , ActionEvent.CTRL_MASK)));
//		editMenu.add (createItem (ActionManager.pasteAction , KeyStroke.getKeyStroke (KeyEvent.VK_V , ActionEvent.CTRL_MASK)));
		return editMenu;
	}



	/**
	 * Creates an item which set visible the toolbar when is selected and set
	 * unvisible the toolbar if not selected
	 * 
	 * @return JMenuItem the item created
	 */
	private void initVisibleToolBarItem ()
	{
		toolBarItem = new JCheckBoxMenuItem ("Barre d'outils", true);
		toolBarItem.addActionListener (ActionManager.visibleToolBar);
	}


	/**
	 * Creates the menu "Preferences" with the action associated
	 * 
	 * @return JMenu the menu "Preferences"
	 */
	private JMenu createPreferencesMenu ()
	{
		JMenu prefMenu = new JMenu (Labels.PREFERENCES_LABEL);
		prefMenu.setMnemonic ('r');
		JMenuItem myParamItem = new JMenuItem(Labels.CONFIG_LABEL);
		myParamItem.addActionListener(ActionManager.preferences);
		prefMenu.add (myParamItem);
		prefMenu.addSeparator ();
		initVisibleToolBarItem();
		prefMenu.add(toolBarItem);
		return prefMenu;
	}

	/**
	 * Creates the menu "Help" with the action associated
	 * 
	 * @return JMenu the menu "Help"
	 */
	private JMenu createHelpMenu ()
	{
		JMenu helpMenu = new JMenu ("Aide");
		helpMenu.setMnemonic ('d');
		helpMenu.add (createItem (ActionManager.helpAction , KeyStroke.getKeyStroke (KeyEvent.VK_H , ActionEvent.CTRL_MASK)));
		helpMenu.addSeparator ();
		JMenuItem apropos = new JMenuItem ("A propos");
		helpMenu.add (apropos);
		return helpMenu;
	}


	/**
	 * This method creates a menuItem with its associated action and its key
	 * stroke
	 * 
	 * @param action Associated action with the item
	 * @param keyStroke Associated keystroke
	 * @return JMenuItem The Item created
	 */
	private JMenuItem createItem (Action action, KeyStroke keyStroke)
	{
		JMenuItem item = new JMenuItem(action);
		item.setIcon(null);
		item.setAccelerator(keyStroke);
		return item;
	}

	public JCheckBoxMenuItem getToolBarItem() {
		return toolBarItem;
	}
	
}