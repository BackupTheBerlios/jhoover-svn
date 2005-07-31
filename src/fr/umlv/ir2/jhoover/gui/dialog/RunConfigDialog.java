/**
 * jHoover - UMLV IR2
 * UI Project
 */
package fr.umlv.ir2.jhoover.gui.dialog;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import com.jgoodies.forms.builder.PanelBuilder;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;

import fr.umlv.ir2.jhoover.JHoover;
import fr.umlv.ir2.jhoover.gui.JHMainFrame;
import fr.umlv.ir2.jhoover.gui.tool.Icons;
import fr.umlv.ir2.jhoover.gui.tool.Labels;
import fr.umlv.ir2.jhoover.network.util.Utils;

/**
 * Run the Configuration of jHoover Dialog
 * @author Romain Papuchon
 */
public class RunConfigDialog extends AbstractConfigDialog {
		
	
	/**
	 * Create a RunConfigDialog
	 * @param title the title of the dialog
	 */
	public RunConfigDialog(String title) {
		super(JHMainFrame.getInstance(), title);
		validButton.setText(Labels.RUN_JHOOVER_LABEL);
		//Add the panels to the JDialog
		buildPanel(new JPanel[]{createHeaderPanel(), createFieldPanel(), createButtonPanel()});
	}
	
	
	/**
	 * Create the header panel
	 * @return the panel
	 */
	private JPanel createHeaderPanel() {
		FormLayout headerLayout = new FormLayout(
			    "p, 9dlu, p", // columns
			    "p, p");      // rows
		PanelBuilder headerFields = new PanelBuilder(headerLayout);
		headerFields.setDefaultDialogBorder();
		JLabel jLogo = new JLabel(Icons.JHOOVER_ICON, SwingConstants.CENTER);
		JLabel hooverLabel = new JLabel("jHoover - Web Site Downloader");
		
		//Adding the Header to the builder
		CellConstraints ccHeader = new CellConstraints();
		headerFields.add(jLogo,         ccHeader.xy (1,  1));
		headerFields.add(hooverLabel,   ccHeader.xy (3,  1));		
		return headerFields.getPanel();
	}

	
		
	/**
	 * @see fr.umlv.ir2.jhoover.gui.dialog.AbstractDialog#validButtonAction()
	 */
	ActionListener validButtonAction() {
		//TODO: put this action un the ActionManager(cf. SetConfigDialog)
		return new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {	
				boolean canSave = true;
				String projectName = rProjectName.getText();
				if (projectName.contains("/") || projectName.contains("\\") || projectName.contains(":") || projectName.contains("*") || projectName.contains("?") || projectName.contains("\"") || projectName.contains("<") || projectName.contains(">") || projectName.contains("|")) {
					//path contains not authorized character
					System.err.println("PATH CONTAINS NOT AUTHORIZED CHARAcTERS: " + projectName);
					JOptionPane.showMessageDialog(JHMainFrame.getInstance(), Labels.PROJECT_NAME_NOT_CORRECT_LABEL + ": " + projectName, Labels.PROJECT_NAME_NOT_CORRECT_LABEL, JOptionPane.ERROR_MESSAGE);
					canSave = false;
				}
				String url = rUrl.getText();
				String oldUrl = url;
				String newUrl;
				if ((newUrl = Utils.addFirstFile(url)) != null) {
					url = newUrl;
				} else {
					//url not good
					System.err.println("URL NOT CORRECT: " + url);
					JOptionPane.showMessageDialog(JHMainFrame.getInstance(), Labels.URL_NOT_CORRECT_LABEL + ": " + url, Labels.URL_NOT_CORRECT_LABEL, JOptionPane.ERROR_MESSAGE);
					canSave = false;
				}
				Integer depth = (Integer)rDepth.getValue();
				String regExp = rRegexp.getText();
				Integer nbHtmlThread = (Integer)rNbHtmlThread.getValue();
				Integer nbLinkedThread = (Integer)rNbLinkedThread.getValue();
				String destDirectory = JHoover.validDestDirectory(rDestDirectory.getText());
				if (canSave) {
					System.out.println("Saving configuration...");
					configuration.setProjectName(projectName);
					configuration.setUrl(oldUrl);
					configuration.setDepth(depth);
					configuration.setRegExp(regExp);
					configuration.setNbHtmlThread(nbHtmlThread);
					configuration.setNbLinkedThread(nbLinkedThread);
					configuration.setDestDirectory(destDirectory);
					configuration.save();
					JHoover.getInstance().startDownload(projectName, url, destDirectory, depth, nbHtmlThread, nbLinkedThread, regExp);
					dispose();
				}
			}
		};
	}
}
