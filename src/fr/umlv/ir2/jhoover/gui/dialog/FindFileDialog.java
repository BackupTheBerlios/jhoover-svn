/**
 * jHoover - UMLV IR2
 * UI Project
 */
package fr.umlv.ir2.jhoover.gui.dialog;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.jgoodies.forms.builder.PanelBuilder;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;

import fr.umlv.ir2.jhoover.gui.JHMainFrame;
import fr.umlv.ir2.jhoover.gui.panel.JHDetailledPanel;
import fr.umlv.ir2.jhoover.gui.tool.Labels;

/**
 * @author Romain Papuchon
 *
 */
public class FindFileDialog extends AbstractDialog {
	
	JTextField searchFile;
	
	public FindFileDialog() {
		super(JHMainFrame.getInstance(), Labels.SEARCH_FILE_LABEL);
		validButton.setText(Labels.SEARCH_FILE_LABEL);
		buildPanel(new JPanel[]{createPanel(), createButtonPanel()});
	}
	
	
	/*
	 * Create the search file panel
	 */
	private JPanel createPanel() {
		FormLayout regexpLayout = new FormLayout(
			    "p, 9dlu, p",		// columns
			    "p");				// rows
		PanelBuilder regexpBuilder = new PanelBuilder(regexpLayout);
		regexpBuilder.setDefaultDialogBorder();
		
		JLabel searchFileLabel = new JLabel("Search a File");
		searchFile = new JTextField(20);
		
		//Adding the Fields to the builder
		CellConstraints ccHeader = new CellConstraints();
		regexpBuilder.add(searchFileLabel,		ccHeader.xy (1,  1));
		regexpBuilder.add(searchFile,			ccHeader.xy (3,  1));
		
		return regexpBuilder.getPanel();
	}

	
	protected ActionListener validButtonAction() {
		// TODO: faire l'action necessaire
		return new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("faire l'action necessaire pour rechercher le fichier");		
				dispose();
			}
		};
	}

	
	
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
