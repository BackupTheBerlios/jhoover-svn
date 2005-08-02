/**
 * jHoover - UMLV IR2
 * UI Project
 */
package fr.umlv.ir2.jhoover.gui.tool;

import javax.swing.JTable;
import javax.swing.table.TableColumn;

import fr.umlv.ir2.jhoover.gui.detailled.DetailledAdapter;
import fr.umlv.ir2.jhoover.gui.detailled.DetailledJButtonEditor;
import fr.umlv.ir2.jhoover.gui.detailled.DetailledJButtonRenderer;
import fr.umlv.ir2.jhoover.gui.detailled.DetailledJProgressBarRenderer;
import fr.umlv.ir2.jhoover.gui.detailled.DetailledModel;
import fr.umlv.ir2.jhoover.gui.panel.JHDetailledPanel;

/**
 * Utils functions for GUI
 * 
 * @author Romain Papuchon
 */
public class GuiUtils {

    /**
     * Creates a new JTable in a new TabPanel in the detailled view
     * 
     * @param model the model to give to the tab
     * @param label the label of the Tab
     * @param regexp the filter in a Regular Expression
     * @return the index of the Tab
     */
    public static int createNewTable(DetailledModel model, String label, String regexp) {
        // creates the adapter
        DetailledAdapter detailledAdapter = new DetailledAdapter(model, regexp);

        // creates the JTable
        JTable table = new JTable(detailledAdapter);
        table.setCellSelectionEnabled(true);
        TableColumn column = null;
        for (int i = 0; i < 4; i++) {
            column = table.getColumnModel().getColumn(i);
            switch (i) {
            case 0:
                // name column
                column.setPreferredWidth(400);
                break;
            case 1:
                // status column
                column.setPreferredWidth(8);
                break;
            case 2:
                // progression column
                column.setPreferredWidth(200);
                break;
            default:
                // cancel column
                column.setPreferredWidth(80);
                break;
            }
        }
        // JButton
        DetailledJButtonRenderer detailledJButtonRenderer = new DetailledJButtonRenderer();
        table.getColumnModel().getColumn(3).setCellRenderer(detailledJButtonRenderer);
        table.getColumnModel().getColumn(3).setCellEditor(new DetailledJButtonEditor());

        // JProgressBar
        table.getColumnModel().getColumn(2).setCellRenderer(new DetailledJProgressBarRenderer());
        JHDetailledPanel.getInstance().addTabPanel(label, table);
        return JHDetailledPanel.getInstance().getIndex(label);
    }

    /**
     * Remove the tabs "HTML", "ALL" and "Files" from the list of tab
     * 
     * @return the new List of tab without "HTML", "ALL" and "Files" or null if
     * there is no new filter
     */
    public static Object[] removeFixedTabFromList() {
        int nbFixedTab = 3;
        Object[] oldTabList = JHDetailledPanel.getInstance().getTabbedList().toArray();
        if (oldTabList.length - nbFixedTab > 0) {
            Object[] newTabList = new String[oldTabList.length - nbFixedTab];
            for (int i = 0; i < oldTabList.length - nbFixedTab; i++) {
                newTabList[i] = oldTabList[i + nbFixedTab];
            }
            return newTabList;
        }
        return null;
    }
}
