package fr.umlv.ir2.jhoover.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JProgressBar;
import javax.swing.table.AbstractTableModel;

import fr.umlv.ir2.jhoover.gui.tool.Icons;
import fr.umlv.ir2.jhoover.network.WebFile;

public class DetailledModel extends AbstractTableModel {
	
	String[] columns = {"Name", "State", "Progression", "Cancel The Download"};   
	ArrayList<WebFile> webFiles;
	
	public DetailledModel() {
		webFiles = new ArrayList<WebFile>();
	}
	
	public int getRowCount() {
		return webFiles.size();
	}
	
	public int getColumnCount() {
		return columns.length;
	}
	
	public Object getValueAt(final int rowIndex, int colIndex) {
		//TODO: change the icons
		switch (colIndex) {
		
		case 0:
			return webFiles.get(rowIndex).getPath();
			
		case 1:
			int progression = webFiles.get(rowIndex).getProgression();
			if (progression < -1) {
				//cannot know: HTML File
				return Icons.DOWNLOAD_DISCOVERED_ICON;
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
			JProgressBar progressBar = new JProgressBar(0, 100);
			progressBar.setVisible(false);
			if (webFiles.get(rowIndex).getProgression() <= 100 && webFiles.get(rowIndex).getProgression() >= 0) {
				progressBar.setValue(webFiles.get(rowIndex).getProgression());
			} else {
				progressBar.setIndeterminate(true);
			}
			progressBar.setStringPainted(true);
			return progressBar;
			
		case 3:
			JButton cancelButton = new JButton("Cancel");
			cancelButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					//TODO: faire l'action. La mettre ici ou pas
					//TODO: faire marcher l'action (ne fonctionne pas)
					System.err.println("Cancel the Download of: " + webFiles.get(rowIndex).getPath());
				}
			});
			return cancelButton;
			
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
			return JProgressBar.class;
		case 3:
			return JButton.class;
		default:
			return null;
		}
	}
	
	public boolean isCellEditable(int row, int col) {
		return false;
	}
	
	public void addElement(WebFile webFile) {
		webFiles.add(webFile);
		fireTableRowsInserted(webFiles.size()-1, webFiles.size()-1);
	}
	
	public int getIndexWebFile(WebFile webFile) {
		return webFiles.indexOf(webFile);
	}
	
//	public WebFile getWebFile(int index) {
//		return webFiles.get(index);
//	}
}
