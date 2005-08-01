/**
 * jHoover - UMLV IR2
 * UI Project
 */
package fr.umlv.ir2.jhoover.gui.dialog;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;

import org.apache.regexp.RE;
import org.apache.regexp.RESyntaxException;

import com.jgoodies.forms.builder.PanelBuilder;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;

import fr.umlv.ir2.jhoover.gui.ActionManager;
import fr.umlv.ir2.jhoover.gui.JHMainFrame;
import fr.umlv.ir2.jhoover.gui.tool.Labels;
import fr.umlv.ir2.jhoover.network.util.JHooverConfiguration;
import fr.umlv.ir2.jhoover.network.util.Utils;

/**
 * Abstract Class for Configuration of jHoover Dialog
 * @author Romain Papuchon
 */
public abstract class AbstractConfigDialog extends AbstractDialog {
	
	//Configuration of jHoover
	protected JHooverConfiguration configuration;
	
	//fields
	protected JTextField rProjectName;
	protected JTextField rUrl;
	protected JSpinner rDepth;
	protected JTextField rRegexp;
	protected JSpinner rNbHtmlThread;
	protected JSpinner rNbLinkedThread;
	protected JTextField rDestDirectory;
	
	
	/**
	 * Create an AbstractConfigDialog
	 * @param parent the jFrame parent of the Dialog
	 * @param title the title of the Dialog
	 */
	public AbstractConfigDialog(JFrame parent, String title) {
		super(parent, title);
		configuration = JHooverConfiguration.getInstance();
		
		//fill in the fields by the configuration file
		rProjectName = new JTextField(configuration.getProjectName());
		rUrl = new JTextField(configuration.getUrl());
		rDepth = new JSpinner(new SpinnerNumberModel((int)configuration.getDepth(), 1, 50, 1));
		rRegexp = new JTextField(configuration.getRegExp());
		rNbHtmlThread = new JSpinner(new SpinnerNumberModel((int)configuration.getNbHtmlThread(), 1, 50, 1));
		rNbLinkedThread = new JSpinner(new SpinnerNumberModel((int)configuration.getNbLinkedThread(), 1, 50, 1));
		rDestDirectory = new JTextField(configuration.getDestDirectory());
	}
	
	
	
	/**
	 * Create the main panel of the configuration
	 * @return the panel
	 */
	protected JPanel createFieldPanel() {
		FormLayout fieldsLayout = new FormLayout(
			    "right:80dlu, 3dlu, p, 100dlu, 3dlu, p", // columns
			    "p, 3dlu, p, 3dlu, p, 9dlu, p, 3dlu, p, 3dlu, p, 3dlu, p, 9dlu, p, 3dlu, p, 3dlu, p");      // rows
		PanelBuilder builderFields = new PanelBuilder(fieldsLayout);
		builderFields.setDefaultDialogBorder();
		
		JButton chooseDestDirectory = new JButton("Choose...");
		chooseDestDirectory.addActionListener (new ActionListener() {
			public void actionPerformed (ActionEvent e) {
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				fileChooser.setApproveButtonText("Select");
				fileChooser.setDialogTitle("Destination Directory");
				int returnVal = fileChooser.showDialog(AbstractConfigDialog.this , null);
				if(returnVal == JFileChooser.APPROVE_OPTION) {
					rDestDirectory.setText(fileChooser.getSelectedFile().getPath());
				}
			}
		});
		
		JButton helpButton = new JButton("Help");
		helpButton.addActionListener(ActionManager.helpAction);
		
		//Adding the Fields to the builder
		CellConstraints cc = new CellConstraints();
		builderFields.addSeparator("General",   			cc.xyw(1,  1, 6));
		builderFields.addLabel("Project Name",  			cc.xy (1,  3));
		builderFields.add(rProjectName,         			cc.xyw(3,  3, 2));
		builderFields.addLabel("Destination Directory",     cc.xy (1,  5));
		builderFields.add(rDestDirectory,				    cc.xyw(3,  5, 2));
		builderFields.add(chooseDestDirectory, 				cc.xy (6,  5));	
		builderFields.addSeparator("Web Site",			    cc.xyw(1,  7, 6));
		builderFields.addLabel("URL",						cc.xy (1,  9));
		builderFields.add(rUrl,  						    cc.xyw(3,  9, 4));
		builderFields.addLabel("Depth",						cc.xy (1,  11));
		builderFields.add(rDepth,  					    	cc.xy (3,  11));
		builderFields.addLabel("Filter",					cc.xy (1,  13));
		builderFields.add(rRegexp,  					    cc.xyw(3,  13, 2));
		builderFields.add(helpButton, 						cc.xy (6,  13));	
		builderFields.addSeparator("Parameters",  			cc.xyw(1,  15, 6));
		builderFields.addLabel("Simultaneous HTML",  		cc.xy (1,  17));
		builderFields.add(rNbHtmlThread,         			cc.xy (3,  17));
		builderFields.addLabel("Simultaneous Linked",  		cc.xy (1,  19));
		builderFields.add(rNbLinkedThread,       			cc.xy (3,  19));
		
		return builderFields.getPanel();
	}
	
	
	
	/**
	 * Reset the fields of the configuration
	 * @return the ActionListener
	 */
	private ActionListener resetButtonAction() {
		return new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("Resetting fields...");
				rUrl.setText(configuration.getUrl());
				rProjectName.setText(configuration.getProjectName());
				rDepth.setValue(configuration.getDepth());
				rRegexp.setText(configuration.getRegExp());
				rNbHtmlThread.setValue(configuration.getNbHtmlThread());
				rNbLinkedThread.setValue(configuration.getNbLinkedThread());
				rDestDirectory.setText(configuration.getDestDirectory());
			}
		};
	}
	
	
	/**
	 * Creates the Button Panel
	 * @return the Panel
	 */
	protected JPanel createButtonPanel() {
		FormLayout buttonLayout = new FormLayout(
			    "p, 3dlu, p, 3dlu, p", 		// columns
			    "p");     					// rows
		PanelBuilder buttonFields = new PanelBuilder(buttonLayout);
		buttonFields.setDefaultDialogBorder();
		
		//Reset Button
		JButton buttonReset = new JButton(Labels.RESET_LABEL);
		buttonReset.addActionListener(resetButtonAction());
		
		//Adding the buttons to the builder
		CellConstraints ccButton = new CellConstraints();
		buttonFields.add(validButton,  		ccButton.xy (1,  1));
		buttonFields.add(cancelButton,  	ccButton.xy (3,  1));
		buttonFields.add(buttonReset,  		ccButton.xy (5,  1));
		
		return buttonFields.getPanel();
	}

	
	/**
	 * Check if the fields are correct or not
	 * @return true if the fields are correct, false else
	 */
	protected boolean checkFields() {
		StringBuffer errorString = new StringBuffer();
		
		//projectName
		if (rProjectName.getText().contains("/") || rProjectName.getText().contains("\\") || rProjectName.getText().contains(":") || rProjectName.getText().contains("*") || rProjectName.getText().contains("?") || rProjectName.getText().contains("\"") || rProjectName.getText().contains("<") || rProjectName.getText().contains(">") || rProjectName.getText().contains("|")) {
			//path contains not authorized character
			System.err.println("PATH CONTAINS NOT AUTHORIZED CHARACTERS: " + rProjectName.getText());
			errorString.append(Labels.PROJECT_NAME_NOT_CORRECT_LABEL + ": " + rProjectName.getText());
		}
		
		//url
		if (Utils.addFirstFile(rUrl.getText()) != null) {
		} else {
			//url not good
			System.err.println("URL NOT CORRECT: " + rUrl.getText());
			errorString.append("\n" + Labels.URL_NOT_CORRECT_LABEL + ": " + rUrl.getText());
		}
		
		//regexp
		try {
			new RE(rRegexp.getText());
		} catch (RESyntaxException e) {
			//error syntax in the regexp
			System.err.println("REGEX NOT CORRECT: " + rRegexp.getText());
			errorString.append("\n" + Labels.REGEXP_NOT_CORRECT_LABEL + ": " + rRegexp.getText());
		}
		
		//save
		if (errorString.length() == 0) {
			System.out.println("Saving configuration...");
			configuration.setProjectName(rProjectName.getText());
			configuration.setUrl(rUrl.getText());
			configuration.setDepth((Integer)rDepth.getValue());
			configuration.setRegExp(rRegexp.getText());
			configuration.setNbHtmlThread((Integer)rNbHtmlThread.getValue());
			configuration.setNbLinkedThread((Integer)rNbLinkedThread.getValue());
			configuration.setDestDirectory(validDestDirectory(rDestDirectory.getText()));
			configuration.save();
			return true;
		}
		JOptionPane.showMessageDialog(JHMainFrame.getInstance(), errorString, Labels.SYNTAX_ERROR_LABEL, JOptionPane.ERROR_MESSAGE);
		return false;
	}
	
	
	
	
	/**
	 * Test the destDirectory and return it with an '/' at the end
	 * @param destDirectory the directory
	 * @return the directory with an '/' at the end
	 */
	private String validDestDirectory(String destDirectory) {		
		if (destDirectory.endsWith("/")) {
			return destDirectory; 
		}
		return destDirectory = destDirectory + "/"; 
	}
	
}
