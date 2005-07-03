/**
 * jHoover - UMLV IR2
 * UI Project
 */
package fr.umlv.ir2.jhoover.gui.panel;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 * @author Romain Papuchon
 *
 */
public class JHDiscoveryPanel extends JPanel {
	private JPanel scrollablePanel;
	public JHDiscoveryPanel() {
		super(new BorderLayout());
		this.scrollablePanel = new JPanel();
		add(new JScrollPane(this.scrollablePanel), BorderLayout.CENTER);
	}
	
	public JPanel getScrollablePanel() {
		return this.scrollablePanel;
	}
}
