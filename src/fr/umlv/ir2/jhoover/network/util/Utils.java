package fr.umlv.ir2.jhoover.network.util;

import java.net.URI;

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
}
