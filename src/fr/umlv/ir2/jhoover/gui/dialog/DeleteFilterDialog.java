/**
 * jHoover - UMLV IR2
 * UI Project
 */
package fr.umlv.ir2.jhoover.gui.dialog;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.jgoodies.forms.builder.PanelBuilder;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;

import fr.umlv.ir2.jhoover.gui.JHMainFrame;
import fr.umlv.ir2.jhoover.gui.panel.JHDetailledPanel;
import fr.umlv.ir2.jhoover.gui.tool.Labels;
import fr.umlv.ir2.jhoover.gui.tool.GuiUtils;

/**
 * Delete a filter Dialog
 * @author Romain Papuchon
 */
public class DeleteFilterDialog extends AbstractDialog {
	
	JComboBox filterList;
	
	/**
	 * Create a DeleteFilterDialog
	 */
	public DeleteFilterDialog() {
		super(JHMainFrame.getInstance(), Labels.DELETE_FILTER_LABEL);
		validButton.setText(Labels.DELETE_FILTER_LABEL);
		Object[] tabList = GuiUtils.removeFixedTabFromList();
		if (tabList == null) {
			JOptionPane.showMessageDialog(JHMainFrame.getInstance(), Labels.NO_FILTER_TO_DELETE_LABEL, Labels.NO_FILTER_TO_DELETE_LABEL, JOptionPane.ERROR_MESSAGE);
		} else {
			buildPanel(new JPanel[]{createPanel(), createButtonPanel()});
		}
	}
	
	
	/**
	 * Create the delete regexp panel
	 * @return the panel
	 */
	private JPanel createPanel() {
		FormLayout regexpLayout = new FormLayout(
			    "p, 9dlu, p",		// columns
			    "p");				// rows
		PanelBuilder regexpBuilder = new PanelBuilder(regexpLayout);
		regexpBuilder.setDefaultDialogBorder();
		
		JLabel selectFilterLabel = new JLabel("Select Filter");
		
		filterList = new JComboBox(GuiUtils.removeFixedTabFromList());
		
		//Adding the Fields to the builder
		CellConstraints ccHeader = new CellConstraints();
		regexpBuilder.add(selectFilterLabel,		ccHeader.xy (1,  1));
		regexpBuilder.add(filterList,				ccHeader.xy (3,  1));
		
		return regexpBuilder.getPanel();
	}

	
	

	/**
	 * @see fr.umlv.ir2.jhoover.gui.dialog.AbstractDialog#validButtonAction()
	 */
	protected ActionListener validButtonAction() {
		return new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//Delete the Filter
				JHDetailledPanel.getInstance().removeTabPanel((String)filterList.getSelectedItem());
				dispose();
			}
		};
	}

	
	
	/**
	 * @see fr.umlv.ir2.jhoover.gui.dialog.AbstractDialog#createButtonPanel()
	 */
	protected JPanel createButtonPanel() {
		FormLayout buttonLayout = new FormLayout(
			    "p, 3dlu, p", 		// columns
			    "p");     					// rows
		PanelBuilder buttonFields = new PanelBuilder(buttonLayout);
		buttonFields.setDefaultDialogBorder();
				
		//Adding the buttons to the builder
		CellConstraints ccButton = new CellConstraints();
		buttonFields.add(validButton,  		ccButton.xy (1,  1));
		buttonFields.add(cancelButton,  	ccButton.xy (3,  1));
				
		return buttonFields.getPanel();
	}
	
	
}
