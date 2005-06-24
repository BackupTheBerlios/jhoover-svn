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
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

import fr.umlv.ir2.jhoover.gui.tool.Icons;


/**
 * @author Romain Papuchon
 *
 */
public final class JHMainFrame extends JFrame
{
	private static JHMainFrame INSTANCE = null;

	private static JHrMenuBar menuBar;
	private static JHToolBar toolBar;
	private static JHMainPanel mainPanel;

	private ActionManager actions;
	
	private final static String	WINDOW_TITLE = "JHoover";

	
	private JHMainFrame ()
	{
		super (WINDOW_TITLE);
		
		this.actions = new ActionManager();
		
		// Modifie l'icone a gauche du titre
		setIcon (Icons.LOGO_URL);
		
		// Surcharge le traitement de la fermeture de la fenêtre
		setDefaultCloseOperation (WindowConstants.DO_NOTHING_ON_CLOSE);
		addWindowListener(new WindowAdapter ()
		{
			public void windowClosing (WindowEvent e)
			{
				//TODO: faire un traitement (actionManager)
				System.exit(0);
			}
		});
		
		build();
		pack();
		setExtendedState(Frame.MAXIMIZED_BOTH);
		setVisible(true);
	}

	
	/**
	 * Method very useful to get the instance of the main Frame of the
	 * application
	 * 
	 * @return BossMainFrame the instance of the main frame
	 */
	public static JHMainFrame getInstance ()
	{
		if (INSTANCE == null)
			INSTANCE = new JHMainFrame();
		
		return INSTANCE;
	}

	
	/**
	 * Returns the main TabPane of the application
	 * 
	 * @return Returns the MainTabPane.
	 */
	public JHMainPanel getMainPanel ()
	{
		return mainPanel;
	}

	/**
	 * Returns the actions manager of the application
	 * 
	 * @return Returns the actions.
	 */
	public ActionManager getActions ()
	{
		return this.actions;
	}

	/**
	 * Returns the menuBar of the application
	 * 
	 * @return Returns the BossMenuBar.
	 */
	public JHrMenuBar getMyMenuBar()
	{
		return menuBar;
	}

	/**
	 * Returns the ToolBar of the application
	 * 
	 * @return Returns the Boss ToolBar.
	 */
	public JHToolBar getMyToolBar()
	{
		return toolBar;
	}

	
	
	/**
	 * To build the frame
	 */
	public void build ()
	{
		mainPanel = new JHMainPanel();
		toolBar 	= new JHToolBar();
		menuBar 	= new JHrMenuBar();

		// Add the menu bar
		setJMenuBar(menuBar);

		// Add the toolbar
		getContentPane().add(toolBar, BorderLayout.NORTH);

		// Add the main panel
		getContentPane().add(mainPanel, BorderLayout.CENTER);
	}
	
	/**
	 * This method enables to set the Icon of the Frame and to put the icon
	 * passed in parameter
	 * @param logoFile LOGO to place in the title bar
	 */
	private void setIcon (final URL logoFile)
	{
		Image icone = Toolkit.getDefaultToolkit().getImage(logoFile);
		setIconImage(icone);
	}
	

	/**
	 * 
	 * 
	 * @param args
	 */
	public static void main (final String [] args)
	{
		JHMainFrame.getInstance();	
	}
}