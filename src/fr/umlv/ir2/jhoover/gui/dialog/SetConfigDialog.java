/**
 * jHoover - UMLV IR2
 * UI Project
 */
package fr.umlv.ir2.jhoover.gui.dialog;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

import fr.umlv.ir2.jhoover.JHoover;
import fr.umlv.ir2.jhoover.gui.JHMainFrame;
import fr.umlv.ir2.jhoover.gui.tool.Labels;

/**
 * Set the Configuration of jHoover Dialog
 * @author Romain Papuchon
 *
 */
public class SetConfigDialog extends AbstractConfigDialog {
		
	
	/**
	 * Create a SetConfigDialog
	 * @param title the title of the dialog
	 */
	public SetConfigDialog(String title) {
		super(JHMainFrame.getInstance(), title);
		validButton.setText(Labels.OK_LABEL);
		//Add the panels to the JDialog
		buildPanel(new JPanel[]{createFieldPanel(), createButtonPanel()});
	}
	
		
	/**
	 * @see fr.umlv.ir2.jhoover.gui.dialog.AbstractDialog#validButtonAction()
	 */
	ActionListener validButtonAction() {
		//TODO: mettre dans ActionManager si possible
		return new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("Saving configuration...");
				String projectName = rProjectName.getText(); //TODO: verifier que le projectName ne contient pas de caracteres spéciaux
				String url = rUrl.getText(); //TODO: verifier la syntaxe de l'URL, ajouter index.html, ...
				Integer depth = (Integer)rDepth.getValue();
				String regExp = rRegexp.getText();
				Integer nbHtmlThread = (Integer)rNbHtmlThread.getValue();
				Integer nbLinkedThread = (Integer)rNbLinkedThread.getValue();
				String destDirectory = JHoover.validDestDirectory(rDestDirectory.getText());
				configuration.setProjectName(projectName);
				configuration.setUrl(url);
				configuration.setDepth(depth);
				configuration.setRegExp(regExp);
				configuration.setNbHtmlThread(nbHtmlThread);
				configuration.setNbLinkedThread(nbLinkedThread);
				configuration.setDestDirectory(destDirectory);
				configuration.save();
				dispose();
			}
		};
	}
}
