/**
 * jHoover - UMLV IR2
 * UI Project
 */
package fr.umlv.ir2.jhoover.gui.panel;

import javax.swing.JSplitPane;


/**
 * MainPanel of JHoover
 * @author Romain Papuchon
 */
public class JHMainPanel extends JSplitPane {
	private static JHDiscoveryPanel discoveryPanel = new JHDiscoveryPanel();
	private static JHManageTabPanel manageTabPanel = new JHManageTabPanel();
	private static JHMainPanel INSTANCE = null;
	
	
	/**
	 * Creates a JHMainPanel
	 */
	private JHMainPanel() {
		super(JSplitPane.HORIZONTAL_SPLIT, discoveryPanel, manageTabPanel);
		//put the divider location to 200 pixel
		setDividerLocation(200);
		setOneTouchExpandable(true);
	}
	
	
	/**
	 * Get an instance of JHMainPanel
	 * @return a singleton for JHMainPanel
	 */
	public static JHMainPanel getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new JHMainPanel();
		}
		return INSTANCE;
	}
	
	
	/**
	 * Return the discoveryPanel
	 * @return the discoveryPanel
	 */
	public JHDiscoveryPanel getDiscoveryPanel() {
		return discoveryPanel;
	}
	
	
	/**
	 * Delete the discoveryPanel and add a new One
	 */
	public static void initDiscoveryPanel() {
		JHMainPanel.getInstance().remove(discoveryPanel);
		discoveryPanel = new JHDiscoveryPanel();
		JHMainPanel.getInstance().add(discoveryPanel);
		//put the divider location to 200 pixel
		JHMainPanel.getInstance().setDividerLocation(200);
		JHMainPanel.getInstance().setOneTouchExpandable(true);
	}
	
	
	/**
	 * Return the manageTabPanel
	 * @return the manageTabPanel
	 */
	public static JHManageTabPanel getManageTabPanel() {
		return manageTabPanel;
	}
}
