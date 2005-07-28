/**
 * jHoover - UMLV IR2
 * UI Project
 */
package fr.umlv.ir2.jhoover.gui.discovery;

import java.awt.Component;

import javax.swing.JTree;
import javax.swing.tree.DefaultTreeCellRenderer;

import fr.umlv.ir2.jhoover.gui.tool.Icons;
import fr.umlv.ir2.jhoover.gui.tool.Extentions;
import fr.umlv.ir2.jhoover.network.WebFile;
import fr.umlv.ir2.jhoover.network.util.Utils;

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
			if (leaf && Extentions.isImage((DiscoveryTreeNode)value)) {
				setIcon(Icons.IMAGE_ICON);
				setToolTipText(Utils.getCompletePath(((DiscoveryTreeNode)value).getWebFile().getURI()));
			} else if (leaf && Extentions.isMusic((DiscoveryTreeNode)value)) {
				setIcon(Icons.MUSIC_ICON);
				setToolTipText(Utils.getCompletePath(((DiscoveryTreeNode)value).getWebFile().getURI()));
			} else if (leaf && Extentions.isVideo((DiscoveryTreeNode)value)) {
				setIcon(Icons.VIDEO_ICON);
				setToolTipText(Utils.getCompletePath(((DiscoveryTreeNode)value).getWebFile().getURI()));
			} else if (leaf && Extentions.isDocument((DiscoveryTreeNode)value)) {
				setIcon(Icons.DOCUMENT_ICON);
				setToolTipText(Utils.getCompletePath(((DiscoveryTreeNode)value).getWebFile().getURI()));
			} else if (leaf && Extentions.isWeb((DiscoveryTreeNode)value)) {
				setIcon(Icons.WEB_ICON);
				setToolTipText(Utils.getCompletePath(((DiscoveryTreeNode)value).getWebFile().getURI()));
			} else if (leaf) {
				setIcon(Icons.UNKNOWN_ICON);
				WebFile webFile = ((DiscoveryTreeNode)value).getWebFile();
				if (webFile != null) {
					setToolTipText(Utils.getCompletePath(webFile.getURI()));
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
