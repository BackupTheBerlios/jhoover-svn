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
	/** JHOOVER_ICON */
	public static final Icon JHOOVER_ICON = new ImageIcon (getURL ("jHoover_125x123.jpg"));
	/** JHOOVER_ICON */
	public static final Icon JHOOVER_JWINDOW = new ImageIcon (getURL ("jHoover_JWindow.png"));
	/** NEW_ICON */
	public static final Icon NEW_ICON = new ImageIcon (getURL ("new_16x16.png"));
	/** STOP_ICON */
	public static final Icon STOP_ICON = new ImageIcon (getURL ("stop_16x16.png"));
	/** PAUSE_ICON */
	public static final Icon PAUSE_ICON = new ImageIcon (getURL ("pause_16x16.png"));
	/** RESUME_ICON */
	public static final Icon RESUME_ICON = new ImageIcon (getURL ("play_16x16.png"));
	/** CLOSE_ICON */
	public static final Icon CLOSE_ICON = new ImageIcon (getURL ("close_16x16.png"));
	/** ADD_FILTER_ICON */
	public static final Icon ADD_FILTER_ICON = new ImageIcon (getURL ("filter_add_16x16.png"));
	/** HELP_ICON */
	public static final Icon HELP_ICON = new ImageIcon (getURL ("help_16x16.png"));
	/** MODIFY_FILTER_ICON */
	public static final Icon MODIFY_FILTER_ICON = new ImageIcon (getURL ("filter_mod_16x16.png"));
	/** DELETE_FILTER_ICON */
	public static final Icon DELETE_FILTER_ICON = new ImageIcon (getURL ("filter_del_16x16.png"));
	/** COLORS_ICON */
	public static final Icon COLORS_ICON = new ImageIcon (getURL ("colors_16x16.png"));
	/** FIND_ICON */
	public static final Icon FIND_ICON = new ImageIcon (getURL ("search_16x16.png"));
	/** CONFIGURATION_ICON */
	public static final Icon CONFIGURATION_ICON = new ImageIcon (getURL ("configuration_16x16.png"));
	/** EXIT_ICON */
	public static final Icon EXIT_ICON = new ImageIcon (getURL ("exit_16x16.png"));
	/** APPLICATION_ICON */
	public static final Icon APPLICATION_ICON = new ImageIcon (getURL ("application_16x16.png"));
	/** LOGO_URL */
	public static final URL LOGO_URL = getURL ("jHoover_125x123.jpg");
	/** ABOUT_ICON */
	public static final Icon ABOUT_ICON = new ImageIcon (getURL ("about_16x16.png"));
	/** LEAF_ICON */
	public static final Icon LEAF_ICON = new ImageIcon (getURL ("leaf_16x16.png"));
	
	/** DOWNLOAD_DISCOVERED_ICON */
	public static final Icon DOWNLOAD_DISCOVERED_ICON = new ImageIcon (getURL ("grey_ball_16x16.png"));
	/** DOWNLOAD_IN_PROGRESS_ICON */
	public static final Icon DOWNLOAD_IN_PROGRESS_ICON = new ImageIcon (getURL ("yellow_ball_16x16.png"));
	/** DOWNLOAD_STOPPED_ICON */
	public static final Icon DOWNLOAD_STOPPED_ICON = new ImageIcon (getURL ("red_ball_16x16.png"));
	/** DOWNLOAD_SUCCESS_ICON */
	public static final Icon DOWNLOAD_SUCCESS_ICON = new ImageIcon (getURL ("green_ball_16x16.png"));
	/** DOWNLOAD_UNKNOWED_ICON */
	public static final Icon DOWNLOAD_UNKNOWED_ICON = new ImageIcon (getURL ("grey_ball_16x16.png"));
	
	/** IMAGE_ICON */
	public static final Icon IMAGE_ICON = new ImageIcon (getURL ("image_16x16.png"));
	/** MUSIC_ICON */
	public static final Icon MUSIC_ICON = new ImageIcon (getURL ("music_16x16.png"));
	/** VIDEO_ICON */
	public static final Icon VIDEO_ICON = new ImageIcon (getURL ("video_16x16.png"));
	/** DOCUMENT_ICON */
	public static final Icon DOCUMENT_ICON = new ImageIcon (getURL ("document_16x16.png"));
	/** WEB_ICON */
	public static final Icon WEB_ICON = new ImageIcon (getURL ("web_16x16.png"));
	/** UNKNOWN_ICON */
	public static final Icon UNKNOWN_ICON = new ImageIcon (getURL ("unknown_16x16.png"));
	/** FOLDER_ICON */
	public static final Icon FOLDER_ICON = new ImageIcon (getURL ("folder_16x16.png"));
	
	
	/**
	 * Return the URL from the image passed in parameter
	 * @param image the image to get the url from
	 * @return the URL of the image
	 */
	public static URL getURL (final String image) {
		URL url = JHMainFrame.class.getResource (IMAGE_PATH + image);
		if (url == null) {
			throw new RuntimeException ("Image " + IMAGE_PATH + image + " not found");
		}
		return url;
	}
}