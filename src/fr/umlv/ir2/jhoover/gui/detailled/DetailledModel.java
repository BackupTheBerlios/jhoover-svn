package fr.umlv.ir2.jhoover.gui.detailled;

import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.table.AbstractTableModel;

import fr.umlv.ir2.jhoover.gui.JHMainFrame;
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
		switch (colIndex) {
		case 0:
			//returns the path of the webFile
			return webFiles.get(rowIndex).getPath();
			
		case 1:
			int progression = webFiles.get(rowIndex).getProgression();
			if (progression < -2) {
				//cannot know: HTML File
				return Icons.DOWNLOAD_DISCOVERED_ICON;
			}
			if (progression == -2) {
				//we cannot know the progression
				return Icons.DOWNLOAD_UNKNOWED_ICON;
			}
			if (progression == -1) {
				//error during dowload
				return Icons.DOWNLOAD_STOPPED_ICON;
			}
			if (progression == 0) {
				//download not started
				return Icons.DOWNLOAD_DISCOVERED_ICON;
			}
			if (progression < 100) {
				//download in progress
				return Icons.DOWNLOAD_IN_PROGRESS_ICON;
			}
			//download finished
			return Icons.DOWNLOAD_SUCCESS_ICON;
			
		case 2:
			//returns the progression of the webFile
			return new Integer(webFiles.get(rowIndex).getProgression());
			
		case 3:
			//returns the webFile
			return webFiles.get(rowIndex);
			
		default:
			return null;
		}
		
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
}
