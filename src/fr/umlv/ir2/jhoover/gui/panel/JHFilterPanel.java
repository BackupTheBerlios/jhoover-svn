/**
 * jHoover - UMLV IR2
 * UI Project
 */
package fr.umlv.ir2.jhoover.gui.panel;

import java.awt.Dimension;
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
import fr.umlv.ir2.jhoover.gui.tool.GuiUtils;
import fr.umlv.ir2.jhoover.gui.tool.Labels;

/**
 * Represents the filter panel
 * @author Romain Papuchon
 */
public class JHFilterPanel extends JPanel {

	private JTextField regexpTextField;
	
	/**
	 * Create a JHFilterPanel
	 */
	public JHFilterPanel() {
		super();
		add(createPanel());
	}
	
	
	/**
	 * Create the regexp panel
	 * @return the panel
	 */
	private JPanel createPanel() {
		FormLayout regexpLayout = new FormLayout(
			    "p, 9dlu, p, 9dlu, p, 9dlu, p", // columns
			    "p");      						// rows
		PanelBuilder regexpBuilder = new PanelBuilder(regexpLayout);
		regexpBuilder.setDefaultDialogBorder();
		
		JLabel rexexpLabel = new JLabel("Regular Expression");
		
		regexpTextField = new JTextField(20);
		regexpTextField.setSize(new Dimension(10,10));
		
		JButton addButton = new JButton("Add a filter");
		addButton.addActionListener(addAction());
		
		JButton helpButton = new JButton("Help");
		helpButton.addActionListener(helpAction());
		
		//Adding the Fields to the builder
		CellConstraints ccHeader = new CellConstraints();
		regexpBuilder.add(rexexpLabel,				ccHeader.xy (1,  1));
		regexpBuilder.add(regexpTextField,			ccHeader.xy (3,  1));
		regexpBuilder.add(addButton,				ccHeader.xy (5,  1));
		regexpBuilder.add(helpButton,				ccHeader.xy (7,  1));

		return regexpBuilder.getPanel();
	}
	
	
	/**
	 * Add a filter in the DetailledPanel
	 * @return the action
	 */
	private ActionListener addAction() {
		//TODO: add this action in ActionManager (cf. AddFilterDialog, same methode)
		return new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int index = 0;
				if (!regexpTextField.getText().equals("")) {
					try {
						new RE(regexpTextField.getText());
						index = GuiUtils.createNewTable(DetailledModel.getInstance(), regexpTextField.getText(), regexpTextField.getText());
						JHDetailledPanel.getInstance().setSelectedIndex(index);
					} catch (RESyntaxException e) {
						JOptionPane.showMessageDialog(JHMainFrame.getInstance(), Labels.REGEXP_NOT_CORRECT_LABEL + ": " + regexpTextField.getText(), Labels.REGEXP_NOT_CORRECT_LABEL, JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		};
	}
	
	
	/**
	 * Print the help
	 * @return the action
	 */
	private ActionListener helpAction() {
		return new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane.showMessageDialog(JHMainFrame.getInstance(), Labels.NOT_IMPLEMENTED_LABEL, Labels.NOT_IMPLEMENTED_LABEL, JOptionPane.ERROR_MESSAGE);
			}
		};
	}
}
