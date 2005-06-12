/**
 * jHoover - UMLV IR2
 * UI Project
 */
package fr.umlv.ir2.jhoover;

import fr.umlv.ir2.jhoover.gui.config.ConfigFrame;

/**
 * @author Romain Papuchon
 *
 */
public class LaunchFrame {
	
	public static void main(String[] args) {
		ConfigFrame configFrame = new ConfigFrame("Configuration of JHoover");
		configFrame.setVisible(true);
		
		
//		XMLConfiguration configuration; 
//		
//		try
//		{
//			configuration = new XMLConfiguration(new File("C:\\TEMP\\params.conf"));
//		}
//		catch (ConfigurationException e)
//		{
//			throw new RuntimeException (e);
//		}
		
		
//		configuration.setProperty("url", "http://www.google.fr");
//		configuration.setProperty("depth", 5);
//		configuration.save();
	}
}
