/**
 * jHoover - UMLV IR2
 * UI Project
 */
package fr.umlv.ir2.jhoover.gui.panel;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;

/**
 * @author Romain Papuchon
 *
 * Represents the detailed vue of the download
 */
public class JHDetailledPanel extends JTabbedPane {

	private static JHDetailledPanel INSTANCE = null;
	
	
	public JHDetailledPanel() {
		super();
		addTabPanel("ALL", new JButton("ALL"));
		addTabPanel("HTML", new JButton("HTML"));
		
		JPanel panel = new JPanel();
		for (int i=0; i<100; i++){
			panel.add(new JButton("" + i));
		}
		addTabPanel("SCROLL", new JScrollPane (panel));
	}
	
	
	public static JHDetailledPanel getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new JHDetailledPanel();
		}
		return INSTANCE;
	}
	

	/*
	 * Add a Tab in the Panel
	 */
	public void addTabPanel(String label, JComponent component) {
		addTab(label, component);
	}
	
	
}
