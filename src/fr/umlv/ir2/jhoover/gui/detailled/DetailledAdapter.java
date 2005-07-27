package fr.umlv.ir2.jhoover.gui.detailled;

import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;

import org.apache.regexp.RE;

import fr.umlv.ir2.jhoover.gui.tool.Icons;
import fr.umlv.ir2.jhoover.gui.tool.Labels;
import fr.umlv.ir2.jhoover.network.WebFile;
import fr.umlv.ir2.jhoover.network.WebHtmlFile;
import fr.umlv.ir2.jhoover.network.WebLinkedFile;

public class DetailledAdapter extends AbstractTableModel {
	private DetailledModel model;
	private String regexpRequest;
	private ArrayList<WebFile> matchedWebFiles;
	
	
	/**
	 * Creates a new DetailledAdapter
	 * @param model the full model of data
	 * @param regexpRequest the filter in a regular Expression
	 */
	public DetailledAdapter(DetailledModel model, String regexpRequest) {
		this.model = model;
		this.regexpRequest = regexpRequest;
		matchedWebFiles = new ArrayList<WebFile>();
		
		//add a listener on the model
		model.addTableModelListener(new TableModelListener() {
			public void tableChanged(TableModelEvent event) {
				switch (event.getType()){
				case TableModelEvent.INSERT:
					//add the datas in the list matchedWebFiles
					if (DetailledAdapter.this.regexpRequest == "HTML" || DetailledAdapter.this.regexpRequest == "FILES") {
						updateDataWithoutRegex(event.getFirstRow(), event.getLastRow(), DetailledAdapter.this.regexpRequest);
					} else {
						updateDataWithRegex(event.getFirstRow(), event.getLastRow(), DetailledAdapter.this.regexpRequest);
					}
					fireTableRowsInserted(event.getFirstRow(), event.getLastRow());
					break;
					
				case TableModelEvent.DELETE:					
					deleteAllData();
					fireTableRowsDeleted(event.getFirstRow(), event.getLastRow());
					break;
					
				default:
					fireTableDataChanged();
					break;
				}
			}
		});
		
		//adding the datas in the list matchedWebFiles
		if (regexpRequest == "HTML" || regexpRequest == "FILES") {
			updateDataWithoutRegex(0, this.model.getWebFiles().size()-1, regexpRequest);
		} else {
			updateDataWithRegex(0, this.model.getWebFiles().size()-1, regexpRequest);
		}
		fireTableRowsInserted(0, this.model.getWebFiles().size()-1);
	}
	
	public int getRowCount() {
		return matchedWebFiles.size();
	}

	
	public int getColumnCount() {
		return model.getColumnCount();
	}

	
	public Object getValueAt(int rowIndex, int colIndex) {
		switch (colIndex) {
		case 0:
			//returns the path of the webFile
			return matchedWebFiles.get(rowIndex).getPath();
			
		case 1:
			int progression = matchedWebFiles.get(rowIndex).getProgression();
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
			return new Integer(matchedWebFiles.get(rowIndex).getProgression());
			
		case 3:
			//returns the webFile
			return matchedWebFiles.get(rowIndex);
			
		default:
			return null;
		}
	}
	
	
	public String getColumnName(int colIndex) {
		return Labels.COLUMS_LABELS[colIndex];
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
	
	
	
	/**
	 * Add the datas from fistRow to lastRow in the matchedWebFiles list (filter with regular expression)
	 * @param firstRow the first index to load
	 * @param lastRow the last index to load
	 */
	private void updateDataWithRegex(int firstRow, int lastRow, String regex) {
		ArrayList<WebFile> webFiles = model.getWebFiles();
		if (regex != null) {
			RE r = new RE(regex);
			for (int i=firstRow; i<=lastRow; i++) {
				WebFile webFile = webFiles.get(i);
				if ((!matchedWebFiles.contains(webFile)) && r.match(webFile.getPath())) {
					matchedWebFiles.add(webFile);
				}
			}
		} else {
			//ALL
			for (int i=firstRow; i<=lastRow; i++) {
				WebFile webFile = webFiles.get(i);
				if (!matchedWebFiles.contains(webFile)) {
					matchedWebFiles.add(webFile);
				}
			}
		}
	}
	
	
	
	/**
	 * Add the datas from fistRow to lastRow in the matchedWebFiles list (filter without regular expression)
	 * @param firstRow the first index to load
	 * @param lastRow the last index to load
	 */
	private void updateDataWithoutRegex(int firstRow, int lastRow, String regex) {
		ArrayList<WebFile> webFiles = model.getWebFiles();
		if (regex == "HTML") {
			for (int i=firstRow; i<=lastRow; i++) {
				WebFile webFile = webFiles.get(i);
				if ((!matchedWebFiles.contains(webFile)) && (webFile instanceof WebHtmlFile)) {
					matchedWebFiles.add(webFile);
				}
			}
		} else if (regex == "FILES") {
			for (int i=firstRow; i<=lastRow; i++) {
				WebFile webFile = webFiles.get(i);
				if ((!matchedWebFiles.contains(webFile)) && (webFile instanceof WebLinkedFile)) {
					matchedWebFiles.add(webFile);
				}
			}
		}
	}
	
	
	/**
	 * Delete all the datas from the matchedWebFiles list
	 */
	private void deleteAllData() {
		matchedWebFiles = new ArrayList<WebFile>();
	}
}
