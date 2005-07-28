/**
 * jHoover - UMLV IR2
 * UI Project
 */
package fr.umlv.ir2.jhoover.network;

import java.net.URI;
import java.util.StringTokenizer;

import fr.umlv.ir2.jhoover.network.util.HtmlConstants;

/**
 * @author Romain Papuchon
 *
 */
public abstract class WebFile {
	private URI uri;
	private int depth;
	private String contentType;
	private int realSize;
	private int progression;

	
	/*
	 * Creates a WebFile
	 */
	protected WebFile(URI uri, int depth) {
		this.uri = uri;
		this.depth = depth;
		this.contentType = "";
		this.realSize = -1;
		progression = 0;
	}
			
	
	public int getRealSize() {
		return this.realSize;
	}
	
	public void setRealSize(int realSize) {
		this.realSize = realSize;
	}
	
	public URI getURI() {
		return this.uri;
	}
	
	public int getDepth() {
		return this.depth;
	}	
	
	public String getContentType() {
		return this.contentType;
	}
	
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	
	public String getPath() {
		return getURI().getPath();
	}
	
	
	public int getProgression() {
		return progression;
	}
	
	public void setProgression(int progression) {
		this.progression = progression;
	}


	/**
	 * Return the file name of the WebFile
	 * @return the file name
	 */
	public String getFileName() {
		String path = getPath();
		StringBuffer fileName = new StringBuffer();
		for (int i=path.length()-1; i>=0; i--) {
			if (path.charAt(i) == '/') {
				if (i == path.length()-1) {
					//the last slash
					
				} else {
					//not the last slash
					//string de i+1 a path.length()
					for (int j=i+1; j<path.length(); j++) {
						fileName.append(path.charAt(j));						
					}
					break;
				}
			}			
		}
		return fileName.toString();		
	}
}

