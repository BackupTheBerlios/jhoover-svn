/**
 * jHoover - UMLV IR2
 * UI Project
 */

package fr.umlv.ir2.jhoover.gui;

import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.URL;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

import fr.umlv.ir2.jhoover.gui.panel.JHMainPanel;
import fr.umlv.ir2.jhoover.gui.tool.Icons;
import fr.umlv.ir2.jhoover.gui.tool.Labels;


/**
 * Main Frame of jHoover
 * @author Romain Papuchon
 *
 */
public final class JHMainFrame extends JFrame {
	private static JHMainFrame INSTANCE = null;
	private static JHMenuBar menuBar;
	private static JHToolBar toolBar;
	private static JHMainPanel mainPanel;

	/**
	 * Create the JHMainFrame
	 */
	private JHMainFrame () {
		super (Labels.MAINFRAME_WINDOW_TITLE_LABEL);
		//set the icon from the frame
		setIcon (Icons.LOGO_URL);
		
		//perform actions before exiting the application
		setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		addWindowListener(new WindowAdapter () {
			public void windowClosing (WindowEvent e) {
				ActionManager.exitAction();
			}
		});
		
		//build the Mainframe
		build();
		
		pack();
		//full screen
		setExtendedState(Frame.MAXIMIZED_BOTH);
		setVisible(true);
	}

	

	/**
	 * Return the singleton for the JHMainFrame
	 * @return the singleton
	 */
	public static JHMainFrame getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new JHMainFrame();
		}
		return INSTANCE;
	}

	

	/**
	 * Return the mainPanel
	 * @return the mainPanel
	 */
	public JHMainPanel getMainPanel() {
		return mainPanel;
	}


	/**
	 * Return the menu bar
	 * @return the menuBar
	 */
	public JHMenuBar getJHMenuBar() {
		return menuBar;
	}


	/**
	 * Return the tool bar
	 * @return the toolBar
	 */
	public JHToolBar getJHToolBar() {
		return toolBar;
	}

	
	/**
	 * Build the mainframe
	 */
	private void build() {
		mainPanel = JHMainPanel.getInstance();
		toolBar = new JHToolBar();
		menuBar = new JHMenuBar();
		// Add the menu bar
		setJMenuBar(menuBar);
		// Add the toolbar
		getContentPane().add(toolBar, BorderLayout.NORTH);
		// Add the main panel
		getContentPane().add(mainPanel, BorderLayout.CENTER);
	}
	
	/**
	 * Set an icone for the mainframe
	 * @param logoFile the url of the icon
	 */
	private void setIcon(final URL logoFile) {
		Image icone = Toolkit.getDefaultToolkit().getImage(logoFile);
		setIconImage(icone);
	}
}