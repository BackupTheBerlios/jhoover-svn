package fr.umlv.ir2.jhoover.gui.detailled;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

import fr.umlv.ir2.jhoover.network.DownloadAndParseFile;
import fr.umlv.ir2.jhoover.network.DownloadManager;
import fr.umlv.ir2.jhoover.network.WebFile;

public class DetailledJButtonRenderer extends JButton implements TableCellRenderer{

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
