/**
 * jHoover - UMLV IR2
 * UI Project
 */
package fr.umlv.ir2.jhoover.gui.panel;

import javax.swing.JSplitPane;


/**
 * @author Romain Papuchon
 *
 */
public class JHMainPanel extends JSplitPane {

	private final static JHDiscoveryPanel discoveryPanel = new JHDiscoveryPanel();
	private final static JHManageTabPanel manageTabPanel = new JHManageTabPanel();
	
	public JHMainPanel() {
		super(JSplitPane.HORIZONTAL_SPLIT, discoveryPanel, manageTabPanel);
		setOneTouchExpandable(true);
	}
}
