/**
 * jHoover - UMLV IR2
 * UI Project
 */
package fr.umlv.ir2.jhoover.network;

import java.net.URI;

/**
 * @author Romain Papuchon
 *
 */
public class WebLinkedFile extends WebFile {

	private WebFile parent;
	
	public WebLinkedFile(URI uri, int depth, WebFile parent) {
		super(uri, depth);
		this.parent = parent; 
	}

	public WebFile getParent() {
		return this.parent;
	}
}
