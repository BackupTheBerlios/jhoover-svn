/**
 * jHoover - UMLV IR2
 * UI Project
 */
package fr.umlv.ir2.jhoover.network;

import java.net.URI;

/**
 * Represents an html file
 * 
 * @author Romain Papuchon
 */
public class WebHtmlFile extends WebFile {

    /**
     * Create an html WebFile with an uri and a depth
     * 
     * @param uri the uri
     * @param depth the depth
     */
    public WebHtmlFile(URI uri, int depth) {
        super(uri, depth);
    }
}
