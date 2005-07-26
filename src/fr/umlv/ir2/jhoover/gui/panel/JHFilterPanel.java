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
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import com.jgoodies.forms.builder.PanelBuilder;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;

import fr.umlv.ir2.jhoover.gui.detailled.DetailledModel;
import fr.umlv.ir2.jhoover.gui.tool.Utils;

/**
 * @author Romain Papuchon
 *
 */
public class JHFilterPanel extends JPanel {

	private JTextField regexpTextField;
	
	/*
	 * Constructor
	 */
	public JHFilterPanel() {
		super();
		add(createPanel());
	}
	
	
	/*
	 * Create the regexp panel
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
	};
	
	
	/*
	 * Add a filter in the DetailledPanel
	 */
	private ActionListener addAction() {
		//TODO: voir pour mettre cette action dans ActionManager (cf. AddFilterDialog, meme methode)
		return new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int index = 0;
				if (!regexpTextField.getText().equals("")) {
					index = Utils.createNewTable(DetailledModel.getInstance(), regexpTextField.getText(), regexpTextField.getText());
				} else {
					//TODO: gerer ce cas
				}
				regexpTextField.setText("");
				JHDetailledPanel.getInstance().setSelectedIndex(index);
				//TODO: faire marcher le dispose pour fermer la fenetre
//				dispose();
			}
		};
	}
	
	
	/*
	 * Print the help
	 */
	private ActionListener helpAction() {
		return new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//TODO: afficher l'aide
				System.out.println("Afficher l'aide");
			}
		};
	}
}
