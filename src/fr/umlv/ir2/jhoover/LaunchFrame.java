/**
 * jHoover - UMLV IR2
 * UI Project
 */
package fr.umlv.ir2.jhoover;

import fr.umlv.ir2.jhoover.gui.config.ConfigFrame;
import fr.umlv.ir2.jhoover.util.JHooverConfiguration;

/**
 * @author Romain Papuchon
 *
 */
public class LaunchFrame {
	
	public static void main(String[] args) {
								
		ConfigFrame configFrame = new ConfigFrame("Configuration of JHoover");
		configFrame.setVisible(true);
		
//		configuration.setDepth(3);
//		configuration.setDestDirectory("C:/TEMP");
//		configuration.setNbHtmlThread(6);
//		configuration.setNbLinkedThread(6);
//		configuration.setRegExp("*.*");
//		configuration.setUrl("http://www.meteofrance.fr");
//		System.out.println(configuration.getDepth());
//		System.out.println(configuration.getDestDirectory());
//		System.out.println(configuration.getNbHtmlThread());
//		System.out.println(configuration.getNbLinkedThread());
//		System.out.println(configuration.getRegExp());
//		System.out.println(configuration.getUrl());
//		configuration.save();
	}
}
