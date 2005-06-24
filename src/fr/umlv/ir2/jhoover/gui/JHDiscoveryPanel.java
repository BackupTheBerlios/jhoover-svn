package fr.umlv.ir2.jhoover.gui;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class JHDiscoveryPanel extends JPanel {
	public JHDiscoveryPanel() {
		super(new BorderLayout ());
		
		JPanel panel = new JPanel();
		for (int i=0; i<100; i++){
			panel.add(new JButton("" + i));
		}
		add(new JScrollPane(panel), BorderLayout.CENTER);
	}
}
