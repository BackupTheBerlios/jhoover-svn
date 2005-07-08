/**
 * jHoover - UMLV IR2
 * UI Project
 */
package fr.umlv.ir2.jhoover.gui;

import java.awt.Component;

import javax.swing.JTree;
import javax.swing.tree.DefaultTreeCellRenderer;

import fr.umlv.ir2.jhoover.gui.tool.Icons;
import fr.umlv.ir2.jhoover.gui.tool.Utils;
import fr.umlv.ir2.jhoover.network.WebFile;

/**
 * @author Romain Papuchon
 *
 */
public class DiscoveryRenderer extends DefaultTreeCellRenderer{
	public DiscoveryRenderer() {
		setEnabled(true);
		setAutoscrolls(true);
//		setClosedIcon(Icons.STOP_ICON);
//		setDisabledIcon(Icons.PAUSE_ICON);
//		setIcon(Icons.ABOUT_ICON);
//		setLeafIcon(Icons.LEAF_ICON);
//		setOpenIcon(Icons.CONFIGURATION_ICON);
//		setHorizontalAlignment(SwingConstants.LEFT);
//		setVerticalAlignment(SwingConstants.TOP);
	}
	
	
	
	public Component getTreeCellRendererComponent(JTree tree, Object value, boolean sel, boolean expanded, boolean leaf, int row, boolean hasFocus) {
		super.getTreeCellRendererComponent(tree, value, sel, expanded, leaf, row, hasFocus);
		//TODO: faire marcher le toolTipText
		if (value instanceof DiscoveryTreeNode) {
			if (leaf && Utils.isImage((DiscoveryTreeNode)value)) {
				setIcon(Icons.IMAGE_ICON);
				setToolTipText(((DiscoveryTreeNode)value).getWebFile().getCompletePath());
			} else if (leaf && Utils.isMusic((DiscoveryTreeNode)value)) {
				setIcon(Icons.MUSIC_ICON);
				setToolTipText(((DiscoveryTreeNode)value).getWebFile().getCompletePath());
			} else if (leaf && Utils.isVideo((DiscoveryTreeNode)value)) {
				setIcon(Icons.VIDEO_ICON);
				setToolTipText(((DiscoveryTreeNode)value).getWebFile().getCompletePath());
			} else if (leaf && Utils.isDocument((DiscoveryTreeNode)value)) {
				setIcon(Icons.DOCUMENT_ICON);
				setToolTipText(((DiscoveryTreeNode)value).getWebFile().getCompletePath());
			} else if (leaf && Utils.isWeb((DiscoveryTreeNode)value)) {
				setIcon(Icons.WEB_ICON);
				setToolTipText(((DiscoveryTreeNode)value).getWebFile().getCompletePath());
			} else if (leaf) {
				setIcon(Icons.UNKNOWN_ICON);
				WebFile webFile = ((DiscoveryTreeNode)value).getWebFile();
				if (webFile != null) {
					setToolTipText(webFile.getCompletePath());
				} else {
					setToolTipText(null);
				}
			} else {
				setIcon(Icons.FOLDER_ICON);
				setToolTipText(null);
			}
		}
		return this;
	}
}
