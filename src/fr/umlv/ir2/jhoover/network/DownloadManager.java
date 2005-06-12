/**
 * jHoover - UMLV IR2
 * UI Project
 */
package fr.umlv.ir2.jhoover.network;

import java.net.URI;
import java.util.ArrayList;

/**
 * @author Romain Papuchon
 *
 * Manage the download
 */
public class DownloadManager implements Runnable {
	private ArrayList<WebFile> htmlFileToDownload;
	private ArrayList<WebFile> linkedFileToDownload;
//	private ArrayList<WebFile> htmlFileDownloaded;
//	private ArrayList<WebFile> linkedFileDownloaded;
	private ArrayList<WebFile> htmlFileInDownloading;
	private ArrayList<WebFile> linkedFileInDownloading;
	private ArrayList<String> discoveredURI; 
	private int maxDLHtml;
	private int maxDLLink;
	private int currentDLHtml;
	private int currentDLLink;
	private int maxDepth;
	private URI startURI;
	private String destDirectory;
	
	
	/*
	 * Creates a DownloadManager
	 */
	public DownloadManager(int maxDLHtml ,int maxDLLink, int maxDepth, URI startURI, String destDirectory) {
		this.htmlFileToDownload = new ArrayList<WebFile>();
		this.linkedFileToDownload = new ArrayList<WebFile>();
		this.htmlFileInDownloading = new ArrayList<WebFile>();
		this.linkedFileInDownloading = new ArrayList<WebFile>();
//		this.htmlFileDownloaded = new ArrayList<WebFile>();
//		this.linkedFileDownloaded = new ArrayList<WebFile>();
		this.discoveredURI = new ArrayList<String>();
		this.maxDLHtml = maxDLHtml;
		this.maxDLLink = maxDLLink;
		this.currentDLHtml = 0;
		this.currentDLLink = 0;
		this.maxDepth = maxDepth;
		this.startURI = startURI;
		this.destDirectory = destDirectory;
	}
	
	
	/*
	 * Thread to download files
	 * 
	 * Take all the webFile contained in the htmlFileToDownload list and download them  
	 */
	public void run() {
		String defaultHost;
	
		if (this.startURI.getPort() > 0) {
			defaultHost = this.startURI.getScheme() + HtmlConstants.SCHEME_AND_AUTHORITY_SEPARATOR + this.startURI.getHost() + ":" + this.startURI.getPort();
		} else {
			defaultHost = this.startURI.getScheme() + HtmlConstants.SCHEME_AND_AUTHORITY_SEPARATOR + this.startURI.getHost();
		}
		
		DownloadAndParseFile downloadFile;
		WebFile webFile;
		while(this.htmlFileToDownload.isEmpty() == false || this.linkedFileToDownload.isEmpty() == false || this.htmlFileInDownloading.isEmpty() == false || this.linkedFileInDownloading.isEmpty() == false) {
//			System.out.println("...NEW...");
//			System.out.println("currentDLHtml: " + currentDLHtml);
//			System.out.println("currentDLLink: " + currentDLLink);
			
			if (this.currentDLHtml < this.maxDLHtml && this.htmlFileToDownload.isEmpty() == false) {
				webFile = this.htmlFileToDownload.get(0);
				this.htmlFileToDownload.remove(0);
				this.htmlFileInDownloading.add(webFile);
				//start a new download of Html File in a new Thread
				downloadFile = new DownloadAndParseFile(this, webFile, defaultHost, this.destDirectory);
				Thread htmlThread = new Thread(downloadFile);
				htmlThread.start();
				this.currentDLHtml++;
			}
			
			if (this.currentDLLink < this.maxDLLink && this.linkedFileToDownload.isEmpty() == false) {
				webFile = this.linkedFileToDownload.get(0);
				this.linkedFileToDownload.remove(0);
				this.linkedFileInDownloading.add(webFile);
				//start a new download of Linked File in a new Thread
				downloadFile = new DownloadAndParseFile(this, webFile, defaultHost, this.destDirectory);
				Thread linkThread = new Thread(downloadFile);
				linkThread.start();
				this.currentDLLink++;
			}
			
			//TODO: tmp, a voir
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
//		System.out.println("...FIN...");
//		System.out.println("currentDLHtml: " + currentDLHtml);
//		System.out.println("currentDLLink: " + currentDLLink);
	}
	
	
	/*
	 * Add a webFile in the queue if it hasn't been already downloaded 
	 */
	public void addHtmlFile(URI uri, int depth) {
		//creates the webFile according to the URI 
		if (!isUriAlreadyDownloaded(uri)) {
			//verification if the depth is good
			if (depth <= this.maxDepth) {
				if (uri.getPort() > 0) {
					this.discoveredURI.add(uri.getScheme() + HtmlConstants.SCHEME_AND_AUTHORITY_SEPARATOR + uri.getHost() + ":" + uri.getPort() + uri.getPath());
				} else {
					this.discoveredURI.add(uri.getScheme() + HtmlConstants.SCHEME_AND_AUTHORITY_SEPARATOR + uri.getHost() + uri.getPath());
				}
				this.htmlFileToDownload.add(new WebFile(uri, depth));
			}
		}
	}
	
	
	/*
	 * Add a webFile in the queue if it hasn't been already downloaded 
	 */
	public void addLinkedFile(URI uri, int depth) {
		//creates the webFile according to the URI 
		if (!isUriAlreadyDownloaded(uri)) {
			//verification if the depth is good
			if (depth <= this.maxDepth) {
				if (uri.getPort() > 0) {
					this.discoveredURI.add(uri.getScheme() + HtmlConstants.SCHEME_AND_AUTHORITY_SEPARATOR + uri.getHost() + ":" + uri.getPort() + uri.getPath());
				} else {
					this.discoveredURI.add(uri.getScheme() + HtmlConstants.SCHEME_AND_AUTHORITY_SEPARATOR + uri.getHost() + uri.getPath());
				}
				this.linkedFileToDownload.add(new WebFile(uri, depth));
			}
		}
	}
	
	
	/*
	 * Return true if the WebFile has been already downloaded or if we shoutd not try to download it, else false
	 */
	public boolean isUriAlreadyDownloaded(URI uri) {
		String discoveredURIString;
		for (int i=0; i<this.discoveredURI.size(); i++) {
			discoveredURIString = this.discoveredURI.get(i);
			if (uri.getPort() > 0) {
				if (discoveredURIString.compareTo(uri.getScheme() + HtmlConstants.SCHEME_AND_AUTHORITY_SEPARATOR + uri.getHost() + ":" + uri.getPort() + uri.getPath()) == 0){
					return true;
				}
			} else {
				if (discoveredURIString.compareTo(uri.getScheme() + HtmlConstants.SCHEME_AND_AUTHORITY_SEPARATOR + uri.getHost() + uri.getPath()) == 0){
					return true;
				}
			}
		}
		return false;
	}
	
	public void removeHtmlFileFromInDownloading(WebFile webFile) {
		this.htmlFileInDownloading.remove(webFile);
	}
	
	public void removeLinkedWebFileFromInDownloading(WebFile webFile) {
		this.htmlFileInDownloading.remove(webFile);
	}
	
	public void endDownloadHtml(WebFile webFile) {
		this.currentDLHtml--;
		this.htmlFileInDownloading.remove(webFile);
//		this.htmlFileDownloaded.add(webFile);
	}
	
	public void endDownloadLink(WebFile webFile) {
		this.currentDLLink--;
		this.linkedFileInDownloading.remove(webFile);
//		this.linkedFileDownloaded.add(webFile);
	}

	/*
	 * Tests if the webFile is a linked File or not
	 */
	public boolean isALinkedFile(WebFile webFile) {
		if (this.linkedFileInDownloading.contains(webFile)) {
			return true;
		}
		return false;
	}
}
