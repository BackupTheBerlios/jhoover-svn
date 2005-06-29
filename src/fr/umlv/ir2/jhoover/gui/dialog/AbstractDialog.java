/**
 * jHoover - UMLV IR2
 * UI Project
 */
package fr.umlv.ir2.jhoover.gui.dialog;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.WindowConstants;

import com.jgoodies.forms.builder.PanelBuilder;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;

import fr.umlv.ir2.jhoover.gui.tool.Labels;

/**
 * @author Romain Papuchon
 *
 */
public abstract class AbstractDialog extends JDialog {
	
	protected JButton cancelButton;
	protected JButton validButton;
	
	
	/*
	 * Constructor
	 */
	public AbstractDialog(JFrame parent, String title) {
		super(parent, title, true);
		cancelButton = new JButton(Labels.CANCEL_LABEL);
		validButton = new JButton();
		initButtonAction();
		setdesign();		
	}
	
	
	/*
	 * Set the graphical parameters from the JDialog
	 */
	private void setdesign() {
		setResizable(false);
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
	}
	
	
	/*
	 * Center the JDialog
	 */
	private void center() {
		Dimension dim = Toolkit.getDefaultToolkit ().getScreenSize ();
		setLocation(dim.width / 2 - getWidth() / 2 , dim.height / 2 - getHeight() / 2);
	}
	
	
	
	/*
	 * Add the Panels to the content Pane of the JDialog
	 */
	protected void buildPanel(JPanel[] allJPanel) {
		Container container = getContentPane();
		int nbPanel = allJPanel.length;
		String rows = "";
		String single = "center:p";		
		
		for (int i=0; i<nbPanel; i++) {
			if (i!=0) {
				rows = rows + ", ";
			}
			rows = rows + single;
		}

		FormLayout configLayout = new FormLayout(
				single, 		// columns
			    rows);    		// rows
		PanelBuilder configFields = new PanelBuilder(configLayout);
		configFields.setDefaultDialogBorder();
		
		//Adding the panels to the contentpane
		CellConstraints ccFields = new CellConstraints();
		for (int i=0; i<nbPanel; i++) {
			configFields.add(allJPanel[i], ccFields.xy (1, i+1));
		}
		setContentPane(configFields.getPanel());
		pack();
		center();
		setVisible(true);
	}
	
	
	
	
	private void initButtonAction() {
		ActionListener actionListenerEscape = new ActionListener() {
			public void actionPerformed (ActionEvent actionEvent) {
				dispose();
			}
		};
		//add the cancel action to the cancel button
		cancelButton.addActionListener(actionListenerEscape);
		
		//add the cancel action to the escape key
		KeyStroke strokeEscape = KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE , 0);
		((JComponent)getContentPane()).registerKeyboardAction(actionListenerEscape , strokeEscape , JComponent.WHEN_IN_FOCUSED_WINDOW);

		//TODO: voir pourquoi les actions avec les touches Enter et Escape ne fonctionnent pas
		
		
		ActionListener actionListenerValidate = validButtonAction();
		
		//add the valid action to the validate button
		validButton.addActionListener(actionListenerValidate);
		
		//add the valid action to the enter key
		KeyStroke strokeEnter = KeyStroke.getKeyStroke(KeyEvent.VK_ENTER , 0);
		((JComponent)getContentPane()).registerKeyboardAction(actionListenerValidate , strokeEnter , JComponent.WHEN_IN_FOCUSED_WINDOW);
	}

	abstract ActionListener validButtonAction();
	
	abstract JPanel createButtonPanel();
}
