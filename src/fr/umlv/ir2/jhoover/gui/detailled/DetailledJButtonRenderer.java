package fr.umlv.ir2.jhoover.gui.detailled;

import java.awt.Component;

import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

public class DetailledJButtonRenderer extends JButton implements TableCellRenderer{

	public Component getTableCellRendererComponent(JTable table, Object webFile, boolean isSelected, boolean hasFocus, int rowIndex, int colIndex) {
		//TODO: rendre le bouton joli ici
		this.setText("Cancel");
		return this;
	}
}
