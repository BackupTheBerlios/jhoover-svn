package fr.umlv.ir2.jhoover.gui.tool;

import javax.swing.JTable;

import fr.umlv.ir2.jhoover.gui.detailled.DetailledAdapter;
import fr.umlv.ir2.jhoover.gui.detailled.DetailledJButtonEditor;
import fr.umlv.ir2.jhoover.gui.detailled.DetailledJButtonRenderer;
import fr.umlv.ir2.jhoover.gui.detailled.DetailledJProgressBarRenderer;
import fr.umlv.ir2.jhoover.gui.detailled.DetailledModel;
import fr.umlv.ir2.jhoover.gui.panel.JHDetailledPanel;

public class Utils {
	
	public static int createNewTable(String label, String regexp) {
		//creates the adapter
		DetailledAdapter detailledAdapter = new DetailledAdapter(DetailledModel.getInstance(), regexp);
		//creates the JTable
		JTable table = new JTable(detailledAdapter);
		table.setCellSelectionEnabled(true);
		//JButton
		table.getColumnModel().getColumn(3).setCellEditor(new DetailledJButtonEditor());
		table.getColumnModel().getColumn(3).setCellRenderer(new DetailledJButtonRenderer());
		//JProgressBar
		table.getColumnModel().getColumn(2).setCellRenderer(new DetailledJProgressBarRenderer());
		JHDetailledPanel.getInstance().addTabPanel(label, table);
		return JHDetailledPanel.getInstance().getIndex(label);
	}

}
