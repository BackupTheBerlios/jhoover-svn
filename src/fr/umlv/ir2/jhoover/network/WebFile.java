/**
 * jHoover - UMLV IR2
 * UI Project
 */
package fr.umlv.ir2.jhoover.network;

import java.net.URI;

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
//	private int downloadedSize;
//	private Date beginDate;

	
	/*
	 * Creates a WebFile
	 */
	protected WebFile(URI uri, int depth) {
		this.uri = uri;
		this.depth = depth;
		this.contentType = "";
		this.realSize = -1;
		progression = 0;
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
	
	public String getPath() {
		return getURI().getPath();
	}
	
	
	public int getProgression() {
		return progression;
	}
	
	public void setProgression(int progression) {
		this.progression = progression;
	}


	public String getCompletePath() {
		//TODO: voir si cette methode renvoie completement ce qu'il faut (bonne syntaxe et bon resultat)
		if (this.uri.getPort() > 0) {
			return this.uri.getScheme() + HtmlConstants.SCHEME_AND_AUTHORITY_SEPARATOR + this.uri.getHost() + ":" + this.uri.getPort() + this.uri.getPath();
		}
		return this.uri.getScheme() + HtmlConstants.SCHEME_AND_AUTHORITY_SEPARATOR + this.uri.getHost() + this.uri.getPath();
	}
	
//	public String getDefaultHost() {
//		//TODO: voir si cette methode renvoie completement ce qu'il faut (bonne syntaxe et bon resultat)
//		if (this.uri.getPort() > 0) {
//			return this.uri.getScheme() + HtmlConstants.SCHEME_AND_AUTHORITY_SEPARATOR + this.uri.getHost() + ":" + this.uri.getPort();
//		}
//		return this.uri.getScheme() + HtmlConstants.SCHEME_AND_AUTHORITY_SEPARATOR + this.uri.getHost();
//	}
}

