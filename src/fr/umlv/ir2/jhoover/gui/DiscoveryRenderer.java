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
		//TODO: comprendre ce qu'est value afin de ne pas faire d'Exception
//		if (leaf && Utils.isImage(value)) {
//			setIcon(Icons.IMAGE_ICON);
//			setToolTipText(((DiscoveryTreeNode)value).getWebFile().getCompletePath());
//		} else if (leaf && Utils.isMusic(value)) {
//			setIcon(Icons.MUSIC_ICON);
//			setToolTipText(((DiscoveryTreeNode)value).getWebFile().getCompletePath());
//		} else if (leaf && Utils.isVideo(value)) {
//			setIcon(Icons.VIDEO_ICON);
//			setToolTipText(((DiscoveryTreeNode)value).getWebFile().getCompletePath());
//		}
		if (!leaf) {
			setIcon(Icons.FOLDER_ICON);
			setToolTipText(null);
		}
		return this;
	}
}
