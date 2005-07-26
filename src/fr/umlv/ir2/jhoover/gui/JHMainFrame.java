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
 * @author Romain Papuchon
 *
 */
public final class JHMainFrame extends JFrame {
	private static JHMainFrame INSTANCE = null;
	private static JHMenuBar menuBar;
	private static JHToolBar toolBar;
	private static JHMainPanel mainPanel;
	private ActionManager actions;

	
	private JHMainFrame () {
		super (Labels.MAINFRAME_WINDOW_TITLE_LABEL);
		this.actions = new ActionManager();
		//set the icon from the frame
		setIcon (Icons.LOGO_URL);
		
		//perform actions before exiting the application
		setDefaultCloseOperation (WindowConstants.DO_NOTHING_ON_CLOSE);
		addWindowListener(new WindowAdapter () {
			public void windowClosing (WindowEvent e) {
				//TODO: faire un traitement (actionManager)
				System.exit(0);
			}
		});
		
		//build the Mainframe
		build();
		
		pack();
		//full screen
		setExtendedState(Frame.MAXIMIZED_BOTH);
		setVisible(true);
	}

	

	public static JHMainFrame getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new JHMainFrame();
		}
		return INSTANCE;
	}

	

	public JHMainPanel getMainPanel() {
		return mainPanel;
	}


	public ActionManager getActions() {
		return this.actions;
	}


	public JHMenuBar getJHMenuBar() {
		return menuBar;
	}


	public JHToolBar getJHToolBar() {
		return toolBar;
	}

	
	public void build() {
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
	

	private void setIcon(final URL logoFile) {
		Image icone = Toolkit.getDefaultToolkit().getImage(logoFile);
		setIconImage(icone);
	}
}