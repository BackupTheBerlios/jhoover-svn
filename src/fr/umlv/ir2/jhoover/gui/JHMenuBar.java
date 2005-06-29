/**
 * jHoover - UMLV IR2
 * UI Project
 */
package fr.umlv.ir2.jhoover.gui;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.Action;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

import fr.umlv.ir2.jhoover.gui.tool.Labels;

/**
 * @author Romain Papuchon
 *
 */
public class JHMenuBar extends JMenuBar
{
	private JCheckBoxMenuItem toolBarItem = null;
	
	public JHMenuBar () {
		add (createFileMenu());
		add (createEditMenu());
		add (createPreferencesMenu());
		add (createHelpMenu());
	}

	private JMenu createFileMenu () {
		JMenu fileMenu = new JMenu (Labels.MENUFILE_LABEL);
		fileMenu.setMnemonic ('F');
		fileMenu.add (createItem (ActionManager.newAction, KeyStroke.getKeyStroke (KeyEvent.VK_N , ActionEvent.CTRL_MASK)));
		fileMenu.addSeparator ();
		fileMenu.add (createItem (ActionManager.resumeAction, KeyStroke.getKeyStroke (KeyEvent.VK_R , ActionEvent.CTRL_MASK)));
		fileMenu.add (createItem (ActionManager.pauseAction, KeyStroke.getKeyStroke (KeyEvent.VK_P , ActionEvent.CTRL_MASK)));
		fileMenu.add (createItem (ActionManager.stopAction, KeyStroke.getKeyStroke (KeyEvent.VK_C , ActionEvent.CTRL_MASK)));
		fileMenu.addSeparator ();
		fileMenu.add (createItem (ActionManager.closeAction, KeyStroke.getKeyStroke (KeyEvent.VK_W , ActionEvent.CTRL_MASK)));
		fileMenu.add (createItem (ActionManager.exitAction, KeyStroke.getKeyStroke (KeyEvent.VK_Q , ActionEvent.CTRL_MASK)));
		return fileMenu;
	}


	private JMenu createEditMenu () {
		JMenu editMenu = new JMenu (Labels.MENUEDIT_LABEL);
		editMenu.setMnemonic ('E');
		editMenu.add (createItem (ActionManager.addFilterAction  , KeyStroke.getKeyStroke (KeyEvent.VK_A , ActionEvent.CTRL_MASK)));
		editMenu.add (createItem (ActionManager.modifyFilterAction  , KeyStroke.getKeyStroke (KeyEvent.VK_M , ActionEvent.CTRL_MASK)));
		editMenu.add (createItem (ActionManager.deleteFilterAction  , KeyStroke.getKeyStroke (KeyEvent.VK_D , ActionEvent.CTRL_MASK)));
		editMenu.addSeparator ();
		editMenu.add (createItem (ActionManager.findAction, KeyStroke.getKeyStroke (KeyEvent.VK_F , ActionEvent.CTRL_MASK)));
		return editMenu;
	}


	private JMenu createPreferencesMenu () {
		JMenu prefMenu = new JMenu (Labels.PREFERENCES_LABEL);
		prefMenu.setMnemonic ('P');
		prefMenu.add (createItem (ActionManager.configurationAction  , KeyStroke.getKeyStroke (KeyEvent.VK_G , ActionEvent.CTRL_MASK)));
		prefMenu.addSeparator ();
		prefMenu.add (createItem (ActionManager.colorsAction  , KeyStroke.getKeyStroke (KeyEvent.VK_L , ActionEvent.CTRL_MASK)));
		prefMenu.addSeparator ();
		toolBarItem = new JCheckBoxMenuItem (Labels.TOOLBAR_LABEL, true);
		toolBarItem.addActionListener (ActionManager.visibleToolBar);
		prefMenu.add(toolBarItem);
		return prefMenu;
	}


	private JMenu createHelpMenu () {
		JMenu helpMenu = new JMenu (Labels.HELP_LABEL);
		helpMenu.setMnemonic ('H');
		helpMenu.add (createItem (ActionManager.helpAction , KeyStroke.getKeyStroke (KeyEvent.VK_H , ActionEvent.CTRL_MASK)));
		helpMenu.addSeparator ();
		helpMenu.add (new JMenuItem(ActionManager.aboutAction));
		return helpMenu;
	}


	private JMenuItem createItem (Action action, KeyStroke keyStroke) {
		JMenuItem item = new JMenuItem(action);
//		item.setIcon(null);
		item.setAccelerator(keyStroke);
		return item;
	}

	public JCheckBoxMenuItem getToolBarItem() {
		return toolBarItem;
	}
	
}