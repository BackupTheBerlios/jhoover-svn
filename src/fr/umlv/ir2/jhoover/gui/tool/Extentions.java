/**
 * jHoover - UMLV IR2
 * UI Project
 */
package fr.umlv.ir2.jhoover.gui.tool;


/**
 * Different methods to test a fileName
 * @author Romain Papuchon
 *
 */
public class Extentions {

	
	//TODO: ajouter des extentions dans ces methodes
	/**
	 * Test if the path is an image dowument or not
	 * @param path the path to test
	 * @return true if it is an image dowument, false else
	 */
	public static boolean isImage(String path) {
		if (path != null) {
			path = path.toLowerCase();
			if (path.endsWith(".bmp") || path.endsWith(".jpg") || path.endsWith(".jpeg") || path.endsWith(".gif")) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Test if the path is a music dowument or not
	 * @param path the path to test
	 * @return true if it is a music dowument, false else
	 */
	public static boolean isMusic(String path) {
		if (path != null) {
			path = path.toLowerCase();
			if (path.endsWith(".wav") || path.endsWith(".mp3")) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Test if the path is a video dowument or not
	 * @param path the path to test
	 * @return true if it is a video dowument, false else
	 */
	public static boolean isVideo(String path) {
		if (path != null) {
			path = path.toLowerCase();
			if (path.endsWith(".avi") || path.endsWith(".mpg")) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Test if the path is a dowument or not
	 * @param path the path to test
	 * @return true if it is a dowument, false else
	 */
	public static boolean isDocument(String path) {
		if (path != null) {
			path = path.toLowerCase();
			if (path.endsWith(".doc") || path.endsWith(".txt") || path.endsWith(".pdf")) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Test if the path is a web dowument or not
	 * @param path the path to test
	 * @return true if it is a web dowument, false else
	 */
	public static boolean isWeb(String path) {
		if (path != null) {
			path = path.toLowerCase();
			if (path.endsWith(".htm") || path.endsWith(".html") || path.endsWith(".asp") || path.endsWith(".php")) {
				return true;
			}
		}
		return false;
	}
	
}
