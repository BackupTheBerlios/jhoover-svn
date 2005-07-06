/**
 * jHoover - UMLV IR2
 * UI Project
 */
package fr.umlv.ir2.jhoover.gui.tool;

import javax.swing.tree.TreeNode;

import fr.umlv.ir2.jhoover.gui.DiscoveryTreeNode;

/**
 * @author Romain Papuchon
 *
 */
public class Utils {

	public static boolean isImage(Object node) {
		//TODO: completer cette methode
		String path = ((DiscoveryTreeNode)node).getWebFile().getPath();
		if (path.endsWith(".bmp") || path.endsWith(".jpg") || path.endsWith(".jpeg") || path.endsWith(".gif")) {
			return true;
		}
		return false;
	}

	public static boolean isMusic(Object node) {
		//TODO: completer cette methode
		String path = ((DiscoveryTreeNode)node).getWebFile().getPath();
		if (path.endsWith(".wav") || path.endsWith(".mp3")) {
			return true;
		}
		return false;
	}

	public static boolean isVideo(Object node) {
		//TODO: completer cette methode
		String path = ((DiscoveryTreeNode)node).getWebFile().getPath();
		if (path.endsWith(".avi") || path.endsWith(".mpg")) {
			return true;
		}
		return false;
	}

}
