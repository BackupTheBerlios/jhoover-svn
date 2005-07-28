package fr.umlv.ir2.jhoover.gui.detailled;

import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.table.AbstractTableModel;

import fr.umlv.ir2.jhoover.gui.tool.Icons;
import fr.umlv.ir2.jhoover.gui.tool.Labels;
import fr.umlv.ir2.jhoover.network.WebFile;

public class DetailledModel extends AbstractTableModel {
	private String[] columns = Labels.COLUMS_LABELS;   
	private ArrayList<WebFile> webFiles;
	private static DetailledModel INSTANCE = null;
	
	private DetailledModel() {
		webFiles = new ArrayList<WebFile>();
	}
	
	public static DetailledModel getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new DetailledModel();
		}
		return INSTANCE;
	}
	
	
	public int getRowCount() {
		return webFiles.size();
	}
	
	public int getColumnCount() {
		return columns.length;
	}
	
	public Object getValueAt(final int rowIndex, int colIndex) {
		return null;
	}
	
	public String getColumnName(int colIndex) {
		return columns[colIndex];
	}
	
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
	
	public boolean isCellEditable(int row, int col) {
		if (col == 3)
			return true;
		return false;
	}
	
	public void addElement(WebFile webFile) {
		webFiles.add(webFile);
		fireTableRowsInserted(webFiles.size()-1, webFiles.size()-1);
	}
	
	public int getIndexWebFile(WebFile webFile) {
		return webFiles.indexOf(webFile);
	}
	
	public ArrayList<WebFile> getWebFiles() {
		return webFiles;
	}
	
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
