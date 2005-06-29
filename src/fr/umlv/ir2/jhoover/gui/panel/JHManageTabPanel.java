/**
 * jHoover - UMLV IR2
 * UI Project
 */
package fr.umlv.ir2.jhoover.gui.panel;

import java.awt.BorderLayout;

import javax.swing.JPanel;


/**
 * @author Romain Papuchon
 *
 */
public class JHManageTabPanel extends JPanel {

	private final JHFilterPanel regexpPanel = new JHFilterPanel();
	private final JHDetailledPanel detailledPanel = new JHDetailledPanel();
	
	public JHManageTabPanel() {
		setLayout(new BorderLayout());
		add(regexpPanel, BorderLayout.NORTH);
		add(detailledPanel, BorderLayout.CENTER);
	}
}
