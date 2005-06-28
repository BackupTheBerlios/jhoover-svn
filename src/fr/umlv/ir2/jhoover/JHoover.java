/**
 * jHoover - UMLV IR2
 * UI Project
 */
package fr.umlv.ir2.jhoover;

import fr.umlv.ir2.jhoover.gui.dialog.ConfigDialog;
import fr.umlv.ir2.jhoover.gui.tool.Labels;

/** 
 * @author Romain Papuchon 
 * 
 * 
 */
public class JHoover {
	/**
	 * Main class
	 * @param args
	 */
	public static void main(String[] args) {
		//Configuration Frame
		ConfigDialog configFrame = new ConfigDialog(Labels.CONFIG_JHOOVER_LABEL);
	}
}
