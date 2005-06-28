/**
 * jHoover - UMLV IR2
 * UI Project
 */
package fr.umlv.ir2.jhoover.gui.dialog;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionListener;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import com.jgoodies.forms.builder.PanelBuilder;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;

/**
 * @author Romain Papuchon
 *
 */
public abstract class AbstractDialog extends JDialog {
	
	/*
	 * Constructor
	 */
	public AbstractDialog(JFrame parent, String title) {
		super(parent, title, true);
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
	protected void buildPanel(JPanel builderPanel, JPanel buttonPanel) {
		Container container = getContentPane();
		FormLayout configLayout = new FormLayout(
			    "center:p", 							// columns
			    "center:p, center:p");    	// rows
		PanelBuilder configFields = new PanelBuilder(configLayout);
		configFields.setDefaultDialogBorder();
		
		
		//Adding the panels to the contentpane
		CellConstraints ccFields = new CellConstraints();
		configFields.add(builderPanel,   ccFields.xy (1,  1));
		configFields.add(buttonPanel,  	 ccFields.xy (1,  2));
		setContentPane(configFields.getPanel());
		
		pack();
		center();
		setVisible(true);
	}
	
	
	
	/*
	 * Add the Panels to the content Pane of the JDialog
	 */
	protected void buildPanel(JPanel headerPanel, JPanel builderPanel, JPanel buttonPanel) {
		Container container = getContentPane();
		FormLayout configLayout = new FormLayout(
			    "center:p", 							// columns
			    "center:p, center:p, center:p");    	// rows
		PanelBuilder configFields = new PanelBuilder(configLayout);
		configFields.setDefaultDialogBorder();
		
		
		//Adding the panels to the contentpane
		CellConstraints ccFields = new CellConstraints();
		configFields.add(headerPanel,    ccFields.xy (1,  1));
		configFields.add(builderPanel,   ccFields.xy (1,  2));
		configFields.add(buttonPanel,  	 ccFields.xy (1,  3));
		setContentPane(configFields.getPanel());
		
		
//		//Escape => cancel Button
//		ActionListener actionListenerEscape = new ActionListener() {
//			public void actionPerformed (ActionEvent actionEvent) {
//				cancelButtonAction();
//			}
//		};
//		KeyStroke strokeEscape = KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE , 0);
//		((JComponent)getContentPane()).registerKeyboardAction(actionListenerEscape , strokeEscape , JComponent.WHEN_IN_FOCUSED_WINDOW);
//
//
//		//Enter => OK Button
//		ActionListener actionListenerValidate = new ActionListener() {
//			public void actionPerformed (ActionEvent actionEvent) {
//				okButtonAction();
//			}
//		};
//		KeyStroke strokeEnter = KeyStroke.getKeyStroke(KeyEvent.VK_ENTER , 0);
//		((JComponent)getContentPane()).registerKeyboardAction(actionListenerValidate , strokeEnter , JComponent.WHEN_IN_FOCUSED_WINDOW);

		pack();
		center();
		setVisible(true);
	}


	protected abstract ActionListener okButtonAction();
	
	protected abstract ActionListener cancelButtonAction();
}
