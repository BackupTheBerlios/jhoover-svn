/**
 * jHoover - UMLV IR2
 * UI Project
 */
package fr.umlv.ir2.jhoover.gui.dialog;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

import fr.umlv.ir2.jhoover.gui.JHMainFrame;
import fr.umlv.ir2.jhoover.gui.tool.Labels;

/**
 * Set the Configuration of jHoover Dialog
 * 
 * @author Romain Papuchon
 * 
 */
public class SetConfigDialog extends AbstractConfigDialog {

    /**
     * Create a SetConfigDialog
     * 
     * @param title the title of the dialog
     */
    public SetConfigDialog(String title) {
        super(JHMainFrame.getInstance(), title);
        validButton.setText(Labels.OK_LABEL);
        // Add the panels to the JDialog
        buildPanel(new JPanel[] { createFieldPanel(), createButtonPanel() });
    }

    /**
     * @see fr.umlv.ir2.jhoover.gui.dialog.AbstractDialog#validButtonAction()
     */
    ActionListener validButtonAction() {
        // TODO: put this action un the ActionManager(cf. RunConfigDialog)
        return new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                if (checkFields()) {
                    dispose();
                }
            }
        };
    }
}
