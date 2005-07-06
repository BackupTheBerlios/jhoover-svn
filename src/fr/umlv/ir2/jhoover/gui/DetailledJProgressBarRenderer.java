package fr.umlv.ir2.jhoover.gui;

import java.awt.Component;

import javax.swing.JProgressBar;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

public class DetailledJProgressBarRenderer extends JProgressBar implements TableCellRenderer {

	public Component getTableCellRendererComponent(JTable table, Object progressBar, boolean isSelected, boolean hasFocus, int rowIndex, int colIndex) {
		this.setValue(((JProgressBar)progressBar).getValue());
        return this;
	}
}
