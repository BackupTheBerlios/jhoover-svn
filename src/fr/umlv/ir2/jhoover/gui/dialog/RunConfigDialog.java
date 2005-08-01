/**
 * jHoover - UMLV IR2
 * UI Project
 */
package fr.umlv.ir2.jhoover.gui.dialog;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
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
		return new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {	
				if (checkFields()) {
					JHoover.getInstance().startDownload(rProjectName.getText(), Utils.addFirstFile(rUrl.getText()), rDestDirectory.getText(), (Integer)rDepth.getValue(), (Integer)rNbHtmlThread.getValue(), (Integer)rNbLinkedThread.getValue(), rRegexp.getText());
					dispose();
				}
			}
		};
	}
}
