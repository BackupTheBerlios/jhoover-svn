/**
 * jHoover - UMLV IR2
 * UI Project
 */
package fr.umlv.ir2.jhoover.gui.detailled;

import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.table.AbstractTableModel;

import fr.umlv.ir2.jhoover.gui.tool.Labels;
import fr.umlv.ir2.jhoover.network.WebFile;

/**
 * Model for the detailled part (JTable)
 * 
 * @author Romain Papuchon
 */
public class DetailledModel extends AbstractTableModel {
    private String[] columns = Labels.COLUMS_LABELS;
    private ArrayList<WebFile> webFiles;
    private static DetailledModel INSTANCE = null;

    private DetailledModel() {
        webFiles = new ArrayList<WebFile>();
    }

    /**
     * Return a singleton for DetailledModel
     * 
     * @return an instance of DetailledModel
     */
    public static DetailledModel getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new DetailledModel();
        }
        return INSTANCE;
    }

    /**
     * @see javax.swing.table.TableModel#getRowCount()
     */
    public int getRowCount() {
        return webFiles.size();
    }

    /**
     * @see javax.swing.table.TableModel#getColumnCount()
     */
    public int getColumnCount() {
        return columns.length;
    }

    /**
     * @see javax.swing.table.TableModel#getValueAt(int, int)
     */
    public Object getValueAt(final int rowIndex, int colIndex) {
        return null;
    }

    /**
     * @see javax.swing.table.AbstractTableModel#getColumnName(int)
     */
    public String getColumnName(int colIndex) {
        return columns[colIndex];
    }

    /**
     * @see javax.swing.table.AbstractTableModel#getColumnClass(int)
     */
    public Class<?> getColumnClass(int col) {
        switch (col) {
        case 0:
            return String.class;
        case 1:
            return ImageIcon.class;
        case 2:
            return Integer.class;
        case 3:
            return WebFile.class;
        default:
            return null;
        }
    }

    /**
     * @see javax.swing.table.AbstractTableModel#isCellEditable(int, int)
     */
    public boolean isCellEditable(int row, int col) {
        if (col == 3)
            return true;
        return false;
    }

    /**
     * Add an element in the model
     * 
     * @param webFile the webFile to add
     */
    public void addElement(WebFile webFile) {
        webFiles.add(webFile);
        fireTableRowsInserted(webFiles.size() - 1, webFiles.size() - 1);
    }

    /**
     * Return the index of the webFile in the model
     * 
     * @param webFile the webFile to get the index from
     * @return the index
     */
    public int getIndexWebFile(WebFile webFile) {
        return webFiles.indexOf(webFile);
    }

    /**
     * Return the model data
     * 
     * @return the list of webFile
     */
    public ArrayList<WebFile> getWebFiles() {
        return webFiles;
    }

    /**
     * Return the WebFile corresponding to the index
     * 
     * @param index the index in the list of webFiles
     * @return the WebFile
     */
    public WebFile getWebFile(int index) {
        return webFiles.get(index);
    }

    /**
     * Init the model in deleting all the WebFiles
     */
    public void initModel() {
        int max = webFiles.size();
        webFiles = new ArrayList<WebFile>();
        fireTableRowsDeleted(0, max);
    }
}
