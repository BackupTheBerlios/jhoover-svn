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
		ConfigFrame configFrame = new ConfigFrame("Configuration of jHoover");
		configFrame.setVisible(true);
	}
}
