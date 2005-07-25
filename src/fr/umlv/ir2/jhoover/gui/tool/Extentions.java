/**
 * jHoover - UMLV IR2
 * UI Project
 */
package fr.umlv.ir2.jhoover.gui.tool;

import fr.umlv.ir2.jhoover.gui.discovery.DiscoveryTreeNode;
import fr.umlv.ir2.jhoover.network.WebFile;

/**
 * @author Romain Papuchon
 *
 */
public class Extentions {

	public static boolean isImage(DiscoveryTreeNode node) {
		//TODO: completer cette methode
		WebFile webFile = node.getWebFile();
		if (webFile != null) {
			String path = webFile.getPath();
			path = path.toLowerCase();
			if (path.endsWith(".bmp") || path.endsWith(".jpg") || path.endsWith(".jpeg") || path.endsWith(".gif")) {
				return true;
			}
		}
		return false;
	}

	public static boolean isMusic(DiscoveryTreeNode node) {
		//TODO: completer cette methode
		WebFile webFile = node.getWebFile();
		if (webFile != null) {
			String path = webFile.getPath();
			path = path.toLowerCase();
			if (path.endsWith(".wav") || path.endsWith(".mp3")) {
				return true;
			}
		}
		return false;
	}

	public static boolean isVideo(DiscoveryTreeNode node) {
		//TODO: completer cette methode
		WebFile webFile = node.getWebFile();
		if (webFile != null) {
			String path = webFile.getPath();
			path = path.toLowerCase();
			if (path.endsWith(".avi") || path.endsWith(".mpg")) {
				return true;
			}
		}
		return false;
	}

	
	public static boolean isDocument(DiscoveryTreeNode node) {
		//TODO: completer cette methode
		WebFile webFile = node.getWebFile();
		if (webFile != null) {
			String path = webFile.getPath();
			path = path.toLowerCase();
			if (path.endsWith(".doc") || path.endsWith(".txt") || path.endsWith(".pdf")) {
				return true;
			}
		}
		return false;
	}
	
	public static boolean isWeb(DiscoveryTreeNode node) {
		//TODO: completer cette methode
		WebFile webFile = node.getWebFile();
		if (webFile != null) {
			String path = webFile.getPath();
			path = path.toLowerCase();
			if (path.endsWith(".htm") || path.endsWith(".html") || path.endsWith(".asp") || path.endsWith(".php")) {
				return true;
			}
		}
		return false;
	}
	
}
