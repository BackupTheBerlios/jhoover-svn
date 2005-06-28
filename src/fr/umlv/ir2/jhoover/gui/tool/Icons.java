/**
 * jHoover - UMLV IR2
 * UI Project
 */
package fr.umlv.ir2.jhoover.gui.tool;

import java.net.URL;

import javax.swing.Icon;
import javax.swing.ImageIcon;

import fr.umlv.ir2.jhoover.gui.JHMainFrame;


/**
 * @author Romain Papuchon
 *
 */
public final class Icons {
	private static final String	IMAGE_PATH	= "ressources/";
	public static final Icon NEW_ICON = new ImageIcon (getURL ("help_24x24.png"));
	public static final Icon STOP_ICON = new ImageIcon (getURL ("stop_24x24.png"));
	public static final Icon PAUSE_ICON = new ImageIcon (getURL ("pause_24x24.png"));
	public static final Icon RESUME_ICON = new ImageIcon (getURL ("play_24x24.png"));
	public static final Icon CLOSE_ICON = new ImageIcon (getURL ("close_24x24.png"));
	public static final Icon ADD_FILTER_ICON = new ImageIcon (getURL ("help_24x24.png"));
	public static final Icon HELP_ICON	  = new ImageIcon (getURL ("help_24x24.png"));
	public static final Icon MODIFY_FILTER_ICON = new ImageIcon (getURL ("help_24x24.png"));
	public static final Icon DELETE_FILTER_ICON = new ImageIcon (getURL ("help_24x24.png"));
	public static final Icon COLORS_ICON	  = new ImageIcon (getURL ("colors_24x24.png"));
	public static final Icon FIND_ICON = new ImageIcon (getURL ("search_24x24.png"));
	public static final Icon CONFIGURATION_ICON = new ImageIcon (getURL ("configuration_24x24.png"));
	public static final Icon EXIT_ICON = new ImageIcon (getURL ("exit_24x24.png"));
	public static final URL LOGO_URL	= getURL ("jHoover_125x123.jpg");
	public static final Icon ABOUT_ICON = new ImageIcon (getURL ("about_24x24.png"));
	

	public static URL getURL (final String image) {
		URL url = JHMainFrame.class.getResource (IMAGE_PATH + image);
		if (url == null) {
			throw new RuntimeException ("Image " + IMAGE_PATH + image + " not found");
		}
		return url;
	}
}