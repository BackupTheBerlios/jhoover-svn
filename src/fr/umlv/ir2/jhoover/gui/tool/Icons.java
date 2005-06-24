/* 
 * File    : Icons.java
 * Created : 1 janv. 2005
 * 
 * =======================================
 * BOSS PROJECT ("http://boss.berlios.de")
 * =======================================
 *
 */
package fr.umlv.ir2.jhoover.gui.tool;

import java.net.URL;

import javax.swing.Icon;
import javax.swing.ImageIcon;

import fr.umlv.ir2.jhoover.gui.JHMainFrame;


/**
 * Class defining all the icons of the application
 * 
 * @author bricou
 */
public final class Icons
{
	/** The image path * */
	private static final String	IMAGE_PATH	= "ressources/icones/";

	
	/** URL of the Logo Icon */
	public static final URL		LOGO_URL	= getURL ("Logo16.jpg");

	/** The calendar icon **/
	public static final Icon    CALENDAR_ICON = new ImageIcon (getURL ("Calendar.png"));
	/** Logo Image */
	public static final Icon	LOGO_IMAGE	  = new ImageIcon (getURL ("Logo.jpg"));
	/** Logo Icon */
	public static final Icon	LOGO_ICON	  = new ImageIcon (LOGO_URL);
	/** Icon for add action */
	public static final Icon	ADD_ICON	  = new ImageIcon (getURL ("Add24.png"));
	/** Icon for edit action */
	public static final Icon	EDIT_ICON	  = new ImageIcon (getURL ("Edit24.png"));
	/** Icon for delete action */
	public static final Icon	DEL_ICON	  = new ImageIcon (getURL ("Delete24.png"));
	/** Icon for sending a mail */
	public static final Icon	MAIL_ICON	  = new ImageIcon (getURL ("Mail24.png"));
	/** Icon for export action */
	public static final Icon	EXPORT_ICON	  = new ImageIcon (getURL ("Export24.png"));
	/** Icon for print action */
	public static final Icon	PRINT_ICON	  = new ImageIcon (getURL ("Print24.png"));
	/** Icon for displaying the common disponibilitis */
	public static final Icon	DISPO_ICON	  = new ImageIcon (getURL ("History24.png"));
	/** Icon for help */
	public static final Icon	HELP_ICON	  = new ImageIcon (getURL ("Help24.png"));
	/** Icon for Logout action */
	public static final Icon	LOGOUT_ICON	  = new ImageIcon (getURL ("Disconnect24.png"));
	/** Icon for displaying the color panel */
	public static final Icon	COLOR_ICON	  = new ImageIcon (getURL ("Theme24.png"));
	/** Icon for copy action */
	public static final Icon	COPY_ICON	  = new ImageIcon (getURL ("Copy24.png"));
	/** Icon for cut action */
	public static final Icon	CUT_ICON	  = new ImageIcon (getURL ("Cut24_fire.png"));
	/** Icon for paste action */ 
	public static final Icon	PASTE_ICON	  = new ImageIcon (getURL ("Paste24.png"));
	/** Icon for undo action */
	public static final Icon	UNDO_ICON	= new ImageIcon (getURL ("Undo24.png"));
	/** Icon for redo action */
	public static final Icon	REDO_ICON	= new ImageIcon (getURL ("Redo24.png"));
	/** Icon for quit action */
	public static final Icon	QUIT_ICON	  = new ImageIcon (getURL ("Quit24.png"));

	/** Icon for Next period */
	public static final Icon	NEXT_ICON	  = new ImageIcon (getURL ("Next30.png"));
	/** Icon for Last period */
	public static final Icon	LAST_ICON	= new ImageIcon (getURL ("Back30.png"));
	/** Icon for representing Students */
	public static final Icon	STUDENTS_ICON	= new ImageIcon (getURL ("Person30.png"));
	/** Icon for representing Users */
	public static final Icon	USERS_ICON	= new ImageIcon (getURL ("Users30.png"));
	/** Icon for representing Subjects */
	public static final Icon	SUBJECTS_ICON	= new ImageIcon (getURL ("Subject30.png"));
	/** Icon for representing Rooms */
	public static final Icon	ROOMS_ICON	= new ImageIcon (getURL ("Room30.png"));
	/** Icon for representing Materials */
	public static final Icon	MATERIALS_ICON	= new ImageIcon (getURL ("Print30.png"));
	/** Icon for representing System configuration */
	public static final Icon	SYSTEM_ICON	= new ImageIcon (getURL ("System30.png"));
	/** Icon for representing other configuration */
	public static final Icon	OTHER_ICON	= new ImageIcon (getURL ("Wand30.png"));
	
	/** Icon for students groups in trees */
	public static final Icon GROUPS_SMALL_ICON = new ImageIcon(getURL("Person12.png"));
	/** Icon for students years in trees */
	public static final Icon YEARS_SMALL_ICON = new ImageIcon(getURL("Groups12.png"));
	
	/** Icon for materiams in trees */
	public static final Icon MATERIALS_SMALL_ICON = new ImageIcon(getURL("Print12.png"));
	/** Icon for rooms in trees */
	public static final Icon ROOMS_SMALL_ICON = new ImageIcon(getURL("Room12.png"));
	
	/** Icon for tab of students */
	public static final Icon STUDENTS_MEDIUM_ICON = new ImageIcon(getURL("Person15.png"));
	/** Icon for tab of quotas */
	public static final Icon QUOTAS_MEDIUM_ICON = new ImageIcon(getURL("Options15.png"));
	/** Icon for tab of rooms */
	public static final Icon ROOMS_MEDIUM_ICON = new ImageIcon(getURL("Room15.png"));
	/** Icon for tab of teachers */
	public static final Icon TEACHERS_MEDIUM_ICON = new ImageIcon(getURL("Users15.png"));
	/** Icon for tab of materials */
	public static final Icon MATERIALS_MEDIUM_ICON = new ImageIcon(getURL("Print15.png"));
	/** Icon for tab of configuration */
	public static final Icon CONFIG_MEDIUM_ICON = new ImageIcon(getURL("System15.png"));
	

	//----------------------------------------------------------//
	//------------------ PRIVATE METHODS -----------------------//
	//----------------------------------------------------------//

	/**
	 * To get the image URL
	 * 
	 * @param szImg the image name
	 * @return the image URL
	 */
	public static URL getURL (final String szImg)
	{
		//URL url = Icons.class.getResource (IMAGE_PATH + szImg);
		URL url = JHMainFrame.class.getResource (IMAGE_PATH + szImg);

		if (url == null)
			throw new RuntimeException ("The image " + IMAGE_PATH + szImg + " wasn't found");

		return url;
	}
}