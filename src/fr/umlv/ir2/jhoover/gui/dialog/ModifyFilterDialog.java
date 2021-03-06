/**
 * jHoover - UMLV IR2
 * UI Project
 */
package fr.umlv.ir2.jhoover.gui.dialog;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.apache.regexp.RE;
import org.apache.regexp.RESyntaxException;

import com.jgoodies.forms.builder.PanelBuilder;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;

import fr.umlv.ir2.jhoover.gui.ActionManager;
import fr.umlv.ir2.jhoover.gui.JHMainFrame;
import fr.umlv.ir2.jhoover.gui.detailled.DetailledModel;
import fr.umlv.ir2.jhoover.gui.panel.JHDetailledPanel;
import fr.umlv.ir2.jhoover.gui.tool.GuiUtils;
import fr.umlv.ir2.jhoover.gui.tool.Labels;

/**
 * Modify a filter Dialog
 * 
 * @author Romain Papuchon
 */
public class ModifyFilterDialog extends AbstractDialog {
    JTextField newRegexpTextField;
    JComboBox filterList;

    /**
     * Create a ModifyFilterDialog
     */
    public ModifyFilterDialog() {
        super(JHMainFrame.getInstance(), Labels.MODIFY_FILTER_LABEL);
        validButton.setText(Labels.MODIFY_FILTER_LABEL);
        Object[] tabList = GuiUtils.removeFixedTabFromList();
        if (tabList == null) {
            JOptionPane.showMessageDialog(JHMainFrame.getInstance(), Labels.NO_FILTER_TO_MODIFY_LABEL, Labels.NO_FILTER_TO_MODIFY_LABEL, JOptionPane.ERROR_MESSAGE);
        } else {
            buildPanel(new JPanel[] { createPanel(), createButtonPanel() });
        }
    }

    /**
     * Create the modify regexp panel
     * 
     * @return the panel
     */
    private JPanel createPanel() {
        FormLayout regexpLayout = new FormLayout("p, 9dlu, p", // columns
                "p, 9dlu, p"); // rows
        PanelBuilder regexpBuilder = new PanelBuilder(regexpLayout);
        regexpBuilder.setDefaultDialogBorder();

        JLabel selectFilterLabel = new JLabel("Select Filter");
        JLabel newRexexpLabel = new JLabel("New Regular Expression");
        newRegexpTextField = new JTextField(20);

        filterList = new JComboBox(GuiUtils.removeFixedTabFromList());

        // Adding the Fields to the builder
        CellConstraints ccHeader = new CellConstraints();
        regexpBuilder.add(selectFilterLabel, ccHeader.xy(1, 1));
        regexpBuilder.add(filterList, ccHeader.xy(3, 1));
        regexpBuilder.add(newRexexpLabel, ccHeader.xy(1, 3));
        regexpBuilder.add(newRegexpTextField, ccHeader.xy(3, 3));

        return regexpBuilder.getPanel();
    }

    /**
     * @see fr.umlv.ir2.jhoover.gui.dialog.AbstractDialog#validButtonAction()
     */
    protected ActionListener validButtonAction() {
        return new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                try {
                    new RE(newRegexpTextField.getText());
                    // Delete the Filter
                    JHDetailledPanel.getInstance().removeTabPanel((String) filterList.getSelectedItem());
                    // Add a new one
                    int index = GuiUtils.createNewTable(DetailledModel.getInstance(), newRegexpTextField.getText(), newRegexpTextField.getText());
                    JHDetailledPanel.getInstance().setSelectedIndex(index);
                    dispose();
                } catch (RESyntaxException e) {
                    JOptionPane.showMessageDialog(JHMainFrame.getInstance(), Labels.REGEXP_NOT_CORRECT_LABEL + ": " + newRegexpTextField.getText(), Labels.REGEXP_NOT_CORRECT_LABEL, JOptionPane.ERROR_MESSAGE);
                }
            }
        };
    }

    /**
     * @see fr.umlv.ir2.jhoover.gui.dialog.AbstractDialog#createButtonPanel()
     */
    protected JPanel createButtonPanel() {
        FormLayout buttonLayout = new FormLayout("p, 3dlu, p, 3dlu, p", // columns
                "p"); // rows
        PanelBuilder buttonFields = new PanelBuilder(buttonLayout);
        buttonFields.setDefaultDialogBorder();

        // Help Button
        JButton helpButton = new JButton(Labels.HELP_LABEL);
        helpButton.addActionListener(ActionManager.helpAction);

        // Adding the buttons to the builder
        CellConstraints ccButton = new CellConstraints();
        buttonFields.add(validButton, ccButton.xy(1, 1));
        buttonFields.add(cancelButton, ccButton.xy(3, 1));
        buttonFields.add(helpButton, ccButton.xy(5, 1));

        return buttonFields.getPanel();
    }

}
