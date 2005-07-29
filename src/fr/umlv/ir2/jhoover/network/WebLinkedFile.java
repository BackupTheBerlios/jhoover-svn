/**
 * jHoover - UMLV IR2
 * UI Project
 */
package fr.umlv.ir2.jhoover.network;

import java.net.URI;

/**
 * Represents an html file 
 * @author Romain Papuchon
 */
public class WebLinkedFile extends WebFile {

	private WebFile parent;
	
	/**
	 * Create an linked WebFile with an uri, a depth and its parent
	 * @param uri the uri
	 * @param depth the depth
	 * @param parent the linkedWebFile parent
	 */
	public WebLinkedFile(URI uri, int depth, WebFile parent) {
		super(uri, depth);
		this.parent = parent; 
	}

	/**
	 * Return the li,ked WebFile parent
	 * @return the parent
	 */
	public WebFile getParent() {
		return this.parent;
	}
}
