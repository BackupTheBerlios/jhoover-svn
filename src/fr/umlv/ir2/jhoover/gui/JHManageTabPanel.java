package fr.umlv.ir2.jhoover.gui;

import java.awt.BorderLayout;

import javax.swing.JPanel;

public class JHManageTabPanel extends JPanel {

	private final JHRegexpPanel regexpPanel = new JHRegexpPanel();
	private final JHDetailledPanel detailledPanel = new JHDetailledPanel();
	
	public JHManageTabPanel() {
		setLayout(new BorderLayout());
		add(regexpPanel, BorderLayout.NORTH);
		add(detailledPanel, BorderLayout.CENTER);
	}
}
