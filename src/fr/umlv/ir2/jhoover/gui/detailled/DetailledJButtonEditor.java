/**
 * jHoover - UMLV IR2
 * UI Project
 */
package fr.umlv.ir2.jhoover.gui.detailled;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.AbstractCellEditor;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;

import fr.umlv.ir2.jhoover.network.DownloadAndParseFile;
import fr.umlv.ir2.jhoover.network.DownloadManager;
import fr.umlv.ir2.jhoover.network.WebFile;

/** 
 * Editor for the JTable button
 * @author Romain Papuchon 
 */
public class DetailledJButtonEditor extends AbstractCellEditor implements TableCellEditor {

	private JButton button;
	
	/**
	 * Create a DetailledJButtonEditor
	 */
	public DetailledJButtonEditor() {
		super();
		button = new JButton("Cancel");
	}
	
	/**
	 * @see javax.swing.table.TableCellEditor#getTableCellEditorComponent(javax.swing.JTable, java.lang.Object, boolean, int, int)
	 */
	public Component getTableCellEditorComponent(JTable table, final Object webFile, boolean isSelected, int row, int col) {		
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int n = ((WebFile)webFile).getProgression();
				if (n != -1 && n != 100) {  //-1: cancelled; 100: finished
					//set "cancel" to the Progression Bar and put the icon in red
					((WebFile)webFile).setProgression(-1);					
					if (n>=0) {
						//stop the download of the WebFile in progress
						ArrayList<DownloadAndParseFile> threadList = DownloadManager.getInstance().getThreadList();
						for (int i=0; i<threadList.size(); i++) {
							DownloadAndParseFile downloadAndParseFile = threadList.get(i);
							WebFile web = downloadAndParseFile.getWebFile();
							if (web.equals(webFile)) {
								downloadAndParseFile.interrupt();							
								break;
							}
						}
					}
				}
				fireEditingStopped();				
			}
		});
		return button;
	}

	/**
	 * @see javax.swing.CellEditor#getCellEditorValue()
	 */
	public Object getCellEditorValue() {
		return button.getText();
	}
}
