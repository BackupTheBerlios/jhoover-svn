package fr.umlv.ir2.jhoover.gui;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractCellEditor;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;

import fr.umlv.ir2.jhoover.network.WebFile;

public class DetailledJButtonEditor extends AbstractCellEditor implements TableCellEditor {

	JButton button;
	
	public DetailledJButtonEditor() {
		super();
		button = new JButton();
	}
	
	public Component getTableCellEditorComponent(JTable table, final Object webFile, boolean isSelected, int row, int column) {
		button.setText("Cancel");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//TODO: faire une action ici:
				//	- Annuler le téléchargement du webFile en cours
				//	- Mettre l'icone en rouge dans le model (setProgression à -1) -- ((WebFile)webFile).setProgression(-1);
				//	- Mettre la JProgressBar à 'Cancelled'  (setProgression à -1)
				//	- Desactiver le JButton (jusqu'a la fin) -- button.setEnabled(false);
				System.err.println("Cancelling the: " + ((WebFile)webFile).getPath());
			}
		});
		return button;
	}

	public Object getCellEditorValue() {
		return button.getText();
	}
}
