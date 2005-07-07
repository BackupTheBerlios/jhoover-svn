package fr.umlv.ir2.jhoover.gui;

import java.awt.Component;

import javax.swing.JProgressBar;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

public class DetailledJProgressBarRenderer extends JProgressBar implements TableCellRenderer {

	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int rowIndex, int colIndex) {
		
		if ((Integer)value == -1) {
			//download cancelled
			//TODO: the action
			this.setIndeterminate(true);
			this.setValue((Integer)value);
			this.setMinimum(0);
			this.setMaximum(100);
			this.setStringPainted(true);
			this.setBorderPainted(true);
			this.setString("Cancelled");
		} else if ((Integer)value == -2) {
			//cannot know
			//TODO: the action
//			this.setString("Unknown");
		} else {
			this.setValue((Integer)value);
			this.setMinimum(0);
			this.setMaximum(100);
			this.setStringPainted(true);
			this.setBorderPainted(true);
			//TODO: voir pour changer les couleurs de la barre ici
//			this.setForeground(new Color(10,20,30));
//			this.setBackground(new Color(60,10,80));
		}
		return this;
	}
}
