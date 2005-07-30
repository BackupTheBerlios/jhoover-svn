/**
 * jHoover - UMLV IR2
 * UI Project
 */
package fr.umlv.ir2.jhoover.network.util;

import java.net.URI;

/** 
 * Utils Class
 * @author Romain Papuchon 
 */
public class Utils {
	
	/**
	 * Return the complete path of the uri
	 * @param uri the uri to extract path from 
	 * @return the complete path of the uri
	 */
	public static String getCompletePath(URI uri) {
		if (uri.getPort() > 0) {
			return uri.getScheme() + HtmlConstants.SCHEME_AND_AUTHORITY_SEPARATOR + uri.getHost() + ":" + uri.getPort() + uri.getPath();
		}
		return uri.getScheme() + HtmlConstants.SCHEME_AND_AUTHORITY_SEPARATOR + uri.getHost() + uri.getPath();
	}
	
	
	
	/**
	 * Return the complete host of the uri
	 * @param uri the uri to extract host from 
	 * @return the complete host of the uri
	 */
	public static String getCompleteHost(URI uri) {
		if (uri.getPort() > 0) {
			return uri.getScheme() + HtmlConstants.SCHEME_AND_AUTHORITY_SEPARATOR + uri.getHost() + ":" + uri.getPort();
		}
		return uri.getScheme() + HtmlConstants.SCHEME_AND_AUTHORITY_SEPARATOR + uri.getHost();
	}
	
	
	/**
	 * Add the first file to the path (index.html) if necessary
	 * @param path the path to test
	 * @return the path with the file if necessary or null if the path end with a document
	 */
	public static String addFirstFile(String path) {
		if (Extentions.isDocument(path)) {
			return null;
		} else if (Extentions.isImage(path)) {
			return null;
		} else if (Extentions.isMusic(path)) {
			return null;
		} else if (Extentions.isVideo(path)) {
			return null;
		} else if (!Extentions.isWeb(path)) {
			//add the 'index.hml' file
			String newUri;
			if (path.endsWith("/")) {
				newUri = path + "index.html";
			} else {
				newUri = path + "/index.html";
			}
			return newUri;
		} 
		return path;
	}
}
