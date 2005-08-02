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
 * Represents the detailed vue of the download
 * 
 * @author Romain Papuchon
 */
public class JHDetailledPanel extends JTabbedPane {
    private static JHDetailledPanel INSTANCE = null;
    private ArrayList<String> tabbedList;

    /**
     * Creates a JHDetailledPanel
     */
    private JHDetailledPanel() {
        super();
        this.tabbedList = new ArrayList<String>();
    }

    /**
     * Returns the singleton of JHDetailledPanel
     * 
     * @return the singleton
     */
    public static JHDetailledPanel getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new JHDetailledPanel();
        }
        return INSTANCE;
    }

    /**
     * Add a new Tab in the Panel
     * 
     * @param label the label of the tab
     * @param component the component to add in the tab
     */
    public void addTabPanel(String label, JComponent component) {
        if (label != "") {
            if (!tabbedList.contains(label)) {
                addTab(label, new JScrollPane(component));
                this.tabbedList.add(label);
            }
        }
    }

    /**
     * Delete the tab corresponding to the Label
     * 
     * @param label
     */
    public void removeTabPanel(String label) {
        for (int i = 0; i < tabbedList.size(); i++) {
            if (tabbedList.get(i) == label) {
                remove(i);
                break;
            }
        }
        this.tabbedList.remove(label);
    }

    /**
     * Returns the list of tab
     * 
     * @return the list of Tab
     */
    public ArrayList<String> getTabbedList() {
        return tabbedList;
    }

    /**
     * Return the index of the tab corresponding to the label
     * 
     * @param label the label
     * @return the index
     */
    public int getIndex(String label) {
        return tabbedList.indexOf(label);
    }
}
