/**
 * jHoover - UMLV IR2
 * UI Project
 */
package fr.umlv.ir2.jhoover.gui.discovery;

import java.awt.Component;

import javax.swing.JTree;
import javax.swing.tree.DefaultTreeCellRenderer;

import fr.umlv.ir2.jhoover.gui.tool.Icons;
import fr.umlv.ir2.jhoover.network.util.Extentions;
import fr.umlv.ir2.jhoover.network.util.Utils;

/**
 * Renderer for the tree
 * 
 * @author Romain Papuchon
 */
public class DiscoveryRenderer extends DefaultTreeCellRenderer {

    /**
     * Create a DiscoveryRenderer
     */
    public DiscoveryRenderer() {
        setEnabled(true);
        setAutoscrolls(true);
    }

    /**
     * @see javax.swing.tree.DefaultTreeCellRenderer#getTreeCellRendererComponent(javax.swing.JTree,
     * java.lang.Object, boolean, boolean, boolean, int, boolean)
     */
    public Component getTreeCellRendererComponent(JTree tree, Object value, boolean sel, boolean expanded, boolean leaf, int row, boolean focus) {
        super.getTreeCellRendererComponent(tree, value, sel, expanded, leaf, row, hasFocus);
        if (value instanceof DiscoveryTreeNode) {
            if (((DiscoveryTreeNode) value).getWebFile() != null) {
                String path = ((DiscoveryTreeNode) value).getWebFile().getFileName();
                if (leaf && Extentions.isImage(path)) {
                    setIcon(Icons.IMAGE_ICON);
                } else if (leaf && Extentions.isMusic(path)) {
                    setIcon(Icons.MUSIC_ICON);
                } else if (leaf && Extentions.isVideo(path)) {
                    setIcon(Icons.VIDEO_ICON);
                } else if (leaf && Extentions.isDocument(path)) {
                    setIcon(Icons.DOCUMENT_ICON);
                } else if (leaf && Extentions.isApplication(path)) {
                    setIcon(Icons.APPLICATION_ICON);
                } else if (leaf && Extentions.isWeb(path)) {
                    setIcon(Icons.WEB_ICON);
                } else if (leaf) {
                    setIcon(Icons.UNKNOWN_ICON);
                } else {
                    setIcon(Icons.FOLDER_ICON);
                }
                setToolTipText(Utils.getCompletePath(((DiscoveryTreeNode) value).getWebFile().getURI()));
            } else {
                if (leaf) {
                    setIcon(Icons.UNKNOWN_ICON);
                } else {
                    setIcon(Icons.FOLDER_ICON);
                }
                setToolTipText(null);
            }
        }
        return this;
    }
}
