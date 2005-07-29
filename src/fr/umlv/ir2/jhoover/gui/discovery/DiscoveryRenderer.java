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
 * Renderer for the tree
 * @author Romain Papuchon
 */
public class DiscoveryRenderer extends DefaultTreeCellRenderer{
	
	/**
	 * Create a DiscoveryRenderer
	 */
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
	
	
	
	/**
	 * @see javax.swing.tree.DefaultTreeCellRenderer#getTreeCellRendererComponent(javax.swing.JTree, java.lang.Object, boolean, boolean, boolean, int, boolean)
	 */
	public Component getTreeCellRendererComponent(JTree tree, Object value, boolean sel, boolean expanded, boolean leaf, int row, boolean focus) {
		super.getTreeCellRendererComponent(tree, value, sel, expanded, leaf, row, hasFocus);
		//TODO: faire marcher le toolTipText
		if (value instanceof DiscoveryTreeNode) {
			if (((DiscoveryTreeNode)value).getWebFile() != null) {
				String path = ((DiscoveryTreeNode)value).getWebFile().getFileName();	
				if (leaf && Extentions.isImage(path)) {
					setIcon(Icons.IMAGE_ICON);
					setToolTipText(Utils.getCompletePath(((DiscoveryTreeNode)value).getWebFile().getURI()));
				} else if (leaf && Extentions.isMusic(path)) {
					setIcon(Icons.MUSIC_ICON);
					setToolTipText(Utils.getCompletePath(((DiscoveryTreeNode)value).getWebFile().getURI()));
				} else if (leaf && Extentions.isVideo(path)) {
					setIcon(Icons.VIDEO_ICON);
					setToolTipText(Utils.getCompletePath(((DiscoveryTreeNode)value).getWebFile().getURI()));
				} else if (leaf && Extentions.isDocument(path)) {
					setIcon(Icons.DOCUMENT_ICON);
					setToolTipText(Utils.getCompletePath(((DiscoveryTreeNode)value).getWebFile().getURI()));
				} else if (leaf && Extentions.isWeb(path)) {
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
			} else {
				 if (leaf) {
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
		}
		return this;
	}
}
