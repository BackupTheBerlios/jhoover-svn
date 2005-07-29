/**
 * jHoover - UMLV IR2
 * UI Project
 */
package fr.umlv.ir2.jhoover.gui.detailled;

import java.awt.Component;

import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

import fr.umlv.ir2.jhoover.network.WebFile;

/** 
 * Renderer for the JTable Button
 * @author Romain Papuchon 
 */
public class DetailledJButtonRenderer extends JButton implements TableCellRenderer{

	/**
	 * @see javax.swing.table.TableCellRenderer#getTableCellRendererComponent(javax.swing.JTable, java.lang.Object, boolean, boolean, int, int)
	 */
	public Component getTableCellRendererComponent(JTable table, final Object webFile, boolean isSelected, boolean hasFocus, int rowIndex, int colIndex) {
		//TODO: rendre le bouton joli ici
		this.setText("Cancel");
		
		int n = ((WebFile)webFile).getProgression();
		if (n == -1 || n == 100) {  //-1: cancelled; 100: finished
			this.setEnabled(false);
		} else {
			this.setEnabled(true);
		}

		return this;
	}
}
