/**
 * jHoover - UMLV IR2
 * UI Project
 */
package fr.umlv.ir2.jhoover.gui.dialog;

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
 * Abstract Class to creates the other Dialog
 * @author Romain Papuchon
 */
public abstract class AbstractDialog extends JDialog {
	protected JButton cancelButton;
	protected JButton validButton;

	/**
	 * Create an AnstractDialog
	 * @param parent
	 * @param title
	 */
	public AbstractDialog(JFrame parent, String title) {
		super(parent, title, true);
		cancelButton = new JButton(Labels.CANCEL_LABEL);
		validButton = new JButton();
		initButtonAction();
		setdesign();		
	}
	
	
	/**
	 * Set the graphical parameters from the JDialog
	 */
	private void setdesign() {
		setResizable(false);
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
	}
	
	
	/**
	 * Center the JDialog
	 */
	private void center() {
		Dimension dim = Toolkit.getDefaultToolkit ().getScreenSize ();
		setLocation(dim.width / 2 - getWidth() / 2 , dim.height / 2 - getHeight() / 2);
	}
	
	
	
	/**
	 * Add the Panels to the content Pane of the JDialog
	 * @param allJPanel the panels to add
	 */
	protected void buildPanel(JPanel[] allJPanel) {
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
	
	
	
	/**
	 * Init the button action
	 */
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

		//TODO: add the actions "enter" and "escape" here
		
		ActionListener actionListenerValidate = validButtonAction();
		
		//add the valid action to the validate button
		validButton.addActionListener(actionListenerValidate);
		
		//add the valid action to the enter key
		KeyStroke strokeEnter = KeyStroke.getKeyStroke(KeyEvent.VK_ENTER , 0);
		((JComponent)getContentPane()).registerKeyboardAction(actionListenerValidate , strokeEnter , JComponent.WHEN_IN_FOCUSED_WINDOW);
	}

	
	/**
	 * Action of validation
	 * @return the action
	 */
	abstract ActionListener validButtonAction();
	
	
	/**
	 * Create the button panel 
	 * @return the panel
	 */
	abstract JPanel createButtonPanel();
}
