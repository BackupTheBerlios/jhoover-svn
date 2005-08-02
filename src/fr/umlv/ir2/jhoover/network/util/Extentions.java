/**
 * jHoover - UMLV IR2
 * UI Project
 */
package fr.umlv.ir2.jhoover.network.util;

/**
 * Different methods to test a fileName
 * 
 * @author Romain Papuchon
 * 
 */
public class Extentions {

    /**
     * Test if the path is an image dowument or not
     * 
     * @param path the path to test
     * @return true if it is an image dowument, false else
     */
    public static boolean isImage(String path) {
        if (path != null) {
            path = path.toLowerCase();
            if (path.endsWith(".bmp") || path.endsWith(".jpg") || path.endsWith(".jpeg") || path.endsWith(".gif") || path.endsWith(".png")) {
                return true;
            }
        }
        return false;
    }

    /**
     * Test if the path is a music dowument or not
     * 
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
     * 
     * @param path the path to test
     * @return true if it is a video dowument, false else
     */
    public static boolean isVideo(String path) {
        if (path != null) {
            path = path.toLowerCase();
            if (path.endsWith(".avi") || path.endsWith(".mpg") || path.endsWith(".mpeg") || path.endsWith(".divx")) {
                return true;
            }
        }
        return false;
    }

    /**
     * Test if the path is a dowument or not
     * 
     * @param path the path to test
     * @return true if it is a dowument, false else
     */
    public static boolean isDocument(String path) {
        if (path != null) {
            path = path.toLowerCase();
            if (path.endsWith(".doc") || path.endsWith(".txt") || path.endsWith(".pdf") || path.endsWith(".sxw") || path.endsWith(".ppt") || path.endsWith(".xls") || path.endsWith(".zip") || path.endsWith(".rar")) {
                return true;
            }
        }
        return false;
    }

    /**
     * Test if the path is an application or not
     * 
     * @param path the path to test
     * @return true if it is an application, false else
     */
    public static boolean isApplication(String path) {
        if (path != null) {
            path = path.toLowerCase();
            if (path.endsWith(".exe") || path.endsWith(".bat") || path.endsWith(".sh") || path.endsWith(".cmd")) {
                return true;
            }
        }
        return false;
    }

    /**
     * Test if the path is a web dowument or not
     * 
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
