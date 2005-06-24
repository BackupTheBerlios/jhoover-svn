/**
 * jHoover - UMLV IR2
 * UI Project
 */
package fr.umlv.ir2.jhoover.gui;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;

/**
 * @author Romain Papuchon
 *
 * Represents the detailed vue of the download
 */
public class JHDetailledPanel extends JTabbedPane {

	public JHDetailledPanel() {
		super();
		addTab("ALL", new JButton("ALL"));
		addTab("HTML", new JButton("HTML"));
		
		JPanel panel = new JPanel();
		for (int i=0; i<100; i++){
			panel.add(new JButton("" + i));
		}
		addTab("SCROLL", new JScrollPane (panel));
	}
	
}
