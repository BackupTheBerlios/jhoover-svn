/**
 * jHoover - UMLV IR2
 * UI Project
 */
package fr.umlv.ir2.jhoover.gui.dialog;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.apache.regexp.RE;
import org.apache.regexp.RESyntaxException;

import com.jgoodies.forms.builder.PanelBuilder;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;

import fr.umlv.ir2.jhoover.gui.JHMainFrame;
import fr.umlv.ir2.jhoover.gui.detailled.DetailledModel;
import fr.umlv.ir2.jhoover.gui.panel.JHDetailledPanel;
import fr.umlv.ir2.jhoover.gui.tool.GuiUtils;
import fr.umlv.ir2.jhoover.gui.tool.Labels;

/**
 * Add a filter Dialog
 * @author Romain Papuchon
 */
public class AddFilterDialog extends AbstractDialog {
	private JTextField regexpTextField;
	
	/**
	 * Create an AddFilterDialog
	 */
	public AddFilterDialog() {
		super(JHMainFrame.getInstance(), "Add a filter");
		buildPanel(new JPanel[]{createPanel(), createButtonPanel()});
	}
	
	/**
	 * Create the regexp panel
	 * @return  the panel
	 */
	private JPanel createPanel() {
		FormLayout regexpLayout = new FormLayout(
			    "p, 9dlu, p", // columns
			    "p");         // rows
		PanelBuilder regexpBuilder = new PanelBuilder(regexpLayout);
		regexpBuilder.setDefaultDialogBorder();
		
		JLabel rexexpLabel = new JLabel("Regular Expression");
		
		regexpTextField = new JTextField(20);
		
		//Adding the Fields to the builder
		CellConstraints ccHeader = new CellConstraints();
		regexpBuilder.add(rexexpLabel,				ccHeader.xy (1,  1));
		regexpBuilder.add(regexpTextField,			ccHeader.xy (3,  1));

		return regexpBuilder.getPanel();
	}


	
	/**
	 * Help Button action
	 * @return the help action
	 */
	private ActionListener helpAction() {
		return new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane.showMessageDialog(JHMainFrame.getInstance(), Labels.NOT_IMPLEMENTED_LABEL, Labels.NOT_IMPLEMENTED_LABEL, JOptionPane.ERROR_MESSAGE);
			}
		};
	}

	/**
	 * Valid Button action
	 * @return the valid action
	 */
	protected ActionListener validButtonAction() {
		//TODO: add this action in ActionManager (cf. JHFilterPanel, same methode)
		return new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				int index = 0;
				if (!regexpTextField.getText().equals("")) {
					try {
						new RE(regexpTextField.getText());
						index = GuiUtils.createNewTable(DetailledModel.getInstance(), regexpTextField.getText(), regexpTextField.getText());
						JHDetailledPanel.getInstance().setSelectedIndex(index);
						dispose();
					} catch (RESyntaxException e) {						
						JOptionPane.showMessageDialog(JHMainFrame.getInstance(), Labels.REGEXP_NOT_CORRECT_LABEL + ": " + regexpTextField.getText(), Labels.REGEXP_NOT_CORRECT_LABEL, JOptionPane.ERROR_MESSAGE);
					}					
				}
			}
		};
	}


	/**
	 * Create the button panel 
	 * @return the panel
	 */
	protected JPanel createButtonPanel() {
		FormLayout buttonLayout = new FormLayout(
			    "p, 9dlu, p, 9dlu, p",	 // columns
			    "p");			// rows
		PanelBuilder buttonBuilder = new PanelBuilder(buttonLayout);
		buttonBuilder.setDefaultDialogBorder();
		
		validButton.setText(Labels.ADD_FILTER_LABEL);
		
		JButton helpButton = new JButton("Help");
		helpButton.addActionListener(helpAction());
		
		//Adding the Buttons to the builder
		CellConstraints ccHeader = new CellConstraints();
		buttonBuilder.add(validButton,		ccHeader.xy (1,  1));
		buttonBuilder.add(cancelButton,		ccHeader.xy (3,  1));
		buttonBuilder.add(helpButton,		ccHeader.xy (5,  1));
		
		return buttonBuilder.getPanel();
	}
}
