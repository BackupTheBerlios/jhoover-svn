/**
 * jHoover - UMLV IR2
 * UI Project
 */
package fr.umlv.ir2.jhoover.gui.panel;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 * Represents the discovery vue of the download
 * @author Romain Papuchon
 */
public class JHDiscoveryPanel extends JPanel {
	private JPanel scrollablePanel;
	
	/**
	 * Create a JHDiscoveryPanel
	 */
	public JHDiscoveryPanel() {
		super(new BorderLayout());
		scrollablePanel = new JPanel();
		scrollablePanel.setLayout(new BorderLayout());
		JScrollPane scrollPane = new JScrollPane(scrollablePanel);
		scrollPane.getVerticalScrollBar().setUnitIncrement(10);
		add(scrollPane, BorderLayout.CENTER);
	}
	
	
	/**
	 * @return the scrollablePanel
	 */
	public JPanel getScrollablePanel() {
		return scrollablePanel;
	}
}
