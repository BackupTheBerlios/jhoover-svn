/**
 * jHoover - UMLV IR2
 * UI Project
 */
package fr.umlv.ir2.jhoover.network;

import java.net.URI;

/**
 * Represents a web File
 * @author Romain Papuchon
 */
public abstract class WebFile {
	private URI uri;
	private int depth;
	private String contentType;
	private int realSize;
	private int progression;

	
	/**
	 * Creates a WebFile with an uri an the depth on the webSite
	 * @param uri the uri
	 * @param depth the depth
	 */
	protected WebFile(URI uri, int depth) {
		this.uri = uri;
		this.depth = depth;
		this.contentType = "";
		this.realSize = -1;
		progression = 0;
	}
			
	
	/**
	 * Return the real size
	 * @return the real size
	 */
	public int getRealSize() {
		return this.realSize;
	}
	
	/**
	 * Set the real Size
	 * @param realSize the size to set
	 */
	public void setRealSize(int realSize) {
		this.realSize = realSize;
	}
	
	/**
	 * Return the uri of the webFile
	 * @return the uri
	 */
	public URI getURI() {
		return this.uri;
	}
	
	/**
	 * Return the depth of the WebFile on the webSite
	 * @return the depth
	 */
	public int getDepth() {
		return this.depth;
	}	
	
	/**
	 * Return the content type of the WebFile
	 * @return the content type
	 */
	public String getContentType() {
		return this.contentType;
	}
	
	/**
	 * Set the content type of the WebFile
	 * @param contentType the content type
	 */
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	
	/**
	 * Return the path of the WebFile
	 * @return the path
	 */
	public String getPath() {
		return getURI().getPath();
	}
	
	
	/**
	 * Return the progression of the WebFile
	 * @return the progression
	 */
	public int getProgression() {
		return progression;
	}
	
	/**
	 * Set the progression if the WebFile
	 * @param progression the progression
	 */
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

