/**
 * jHoover - UMLV IR2
 * UI Project
 */
package fr.umlv.ir2.jhoover.gui.detailled;

import java.awt.Component;

import javax.swing.JProgressBar;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

/** 
 * Renderer for the JTable progression bar
 * @author Romain Papuchon 
 */
public class DetailledJProgressBarRenderer extends JProgressBar implements TableCellRenderer {

	/**
	 * @see javax.swing.table.TableCellRenderer#getTableCellRendererComponent(javax.swing.JTable, java.lang.Object, boolean, boolean, int, int)
	 */
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int rowIndex, int colIndex) {
		
		if ((Integer)value == -1) {
			//download cancelled
			this.setIndeterminate(true);
			this.setValue((Integer)value);
			this.setMinimum(0);
			this.setMaximum(100);
			this.setStringPainted(true);
			this.setBorderPainted(true);
			this.setString("Cancelled");
		} else if ((Integer)value == -2) {
			//cannot know
			this.setString("Unknown");
		} else {
			this.setValue((Integer)value);
			this.setMinimum(0);
			this.setMaximum(100);
			this.setStringPainted(true);
			this.setBorderPainted(true);
		}
		return this;
	}
}
