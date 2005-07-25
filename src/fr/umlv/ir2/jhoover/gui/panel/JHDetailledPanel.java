/**
 * jHoover - UMLV IR2
 * UI Project
 */
package fr.umlv.ir2.jhoover.gui.panel;

import java.util.ArrayList;

import javax.swing.JComponent;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;

/**
 * @author Romain Papuchon
 *
 * Represents the detailed vue of the download
 */
public class JHDetailledPanel extends JTabbedPane {

	private static JHDetailledPanel INSTANCE = null;
	
	private ArrayList<String> tabbedList;
	
	/*
	 * Default Constructor
	 */
	private JHDetailledPanel() {
		super();
		this.tabbedList = new ArrayList<String>();
	}
	
	/*
	 * Returns the singleton of JHDetailledPanel
	 */
	public static JHDetailledPanel getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new JHDetailledPanel();
		}
		return INSTANCE;
	}
	

	/*
	 * Add a new Tab in the Panel
	 */
	public void addTabPanel(String label, JComponent component) {
		if (label != "") {
			if (!tabbedList.contains(label)) {
				addTab(label, new JScrollPane(component));
				this.tabbedList.add(label);
			} else {
				//tab already exist
				//TODO: gerer ce cas
			}
		} else {
			//label empty
			//TODO: gerer ce cas
		}
	}
	
	/**
	 * Delete the tab corresponding to the Label
	 * @param label
	 */
	public void removeTabPanel(String label) {
		for (int i=0; i<tabbedList.size();i++) {
			if (tabbedList.get(i) == label) {
				remove(i);
				break;
			}
		}
		this.tabbedList.remove(label);
	}

	
	/*
	 * Returns the list of the tabs
	 */
	public ArrayList<String> getTabbedList() {
		return this.tabbedList;
	}
	
	
	public int getIndex(String label) {
		return tabbedList.indexOf(label);
	}
}
