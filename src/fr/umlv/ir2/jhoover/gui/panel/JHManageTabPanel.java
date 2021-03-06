/**
 * jHoover - UMLV IR2
 * UI Project
 */
package fr.umlv.ir2.jhoover.gui.panel;

import java.awt.BorderLayout;

import javax.swing.JPanel;

/**
 * Contains the filterPanel and the regExpPanel
 * 
 * @author Romain Papuchon
 * 
 */
public class JHManageTabPanel extends JPanel {
    private final JHFilterPanel regexpPanel;

    /**
     * Creates a JHManageTabPanel
     */
    public JHManageTabPanel() {
        regexpPanel = new JHFilterPanel();
        setLayout(new BorderLayout());
        add(this.regexpPanel, BorderLayout.NORTH);
        add(JHDetailledPanel.getInstance(), BorderLayout.CENTER);
    }
}
