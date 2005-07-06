package fr.umlv.ir2.jhoover.gui;

import java.awt.Component;

import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

public class DetailledJButtonRenderer extends JButton implements TableCellRenderer{

//	public DetailledJButtonRenderer() {
//		setOpaque(true);
//	}
	
	public Component getTableCellRendererComponent(JTable table, Object button, boolean isSelected, boolean hasFocus, int rowIndex, int colIndex) {
		this.setText(((JButton)button).getText());
		return this;
	}

}
