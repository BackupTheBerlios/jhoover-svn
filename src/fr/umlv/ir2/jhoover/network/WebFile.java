/**
 * jHoover - UMLV IR2
 * UI Project
 */
package fr.umlv.ir2.jhoover.network;

import java.net.URI;

/**
 * @author Romain Papuchon
 *
 * Represents a file
 */
public class WebFile {
	private URI uri;
	private int depth;
	private String contentType;
	private int realSize;
//	private int downloadedSize;
//	private Date beginDate;

	
	/*
	 * Creates a WebFile
	 */
	public WebFile(URI uri, int depth) {
		this.uri = uri;
		this.depth = depth;
		this.contentType = "";
		this.realSize = -1;
//		downloadedSize = 0;
//		this.beginDate = null;
	}
			

//	/*
//	 * Returns true if the webFile is completely downloaded, false else
//	 */
//	public boolean isDownloaded() {
//		if (downloadedSize == realSize) return true;
//		return false;
//	}
	
//	/*
//	 * Returns true if the download of the webFile is in progress, false else
//	 */
//	public boolean isInProgress() {
//		if (downloadedSize != 0 && downloadedSize < realSize) return true;
//		return false;
//	}
	
//	/*
//	 * Returns true if the download of the webFile is not in progress, false else
//	 */
//	public boolean isNotInProgress() {
//		if (downloadedSize == 0 && realSize != 0) return true;
//		return false;
//	}
	
//	public int getDownloadedSize() {
//		return downloadedSize;
//	}
	
//	public void setDownloadedSize(int downloadedSize) {
//		this.downloadedSize = downloadedSize;
//	}
	
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
	
//	public void setBeginDate(Date beginDate) {
//		this.beginDate = beginDate;
//	}
}

