/**
 * jHoover - UMLV IR2
 * UI Project
 */
package fr.umlv.ir2.jhoover.network;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

/**
 * @author Romain Papuchon
 *
 * Manage the download
 */
public class DownloadManager implements Runnable {
	private ArrayList<WebFile> webFileToDownload;
	private ArrayList<WebFile> webFileDownloaded;
	//private ArrayList<WebFile> webFileInDownloading;
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
		webFileToDownload = new ArrayList<WebFile>();
		webFileDownloaded = new ArrayList<WebFile>();
		//webFileInDownloading = new ArrayList<WebFile>();
		discoveredURI = new ArrayList<String>();
		this.maxDLHtml = maxDLHtml;
		this.maxDLLink = maxDLLink;
		currentDLHtml = 0;
		currentDLLink = 0;
		this.maxDepth = maxDepth;
		this.startURI = startURI;
		this.destDirectory = destDirectory;
	}
	
	/*
	 * Thread to download files
	 * 
	 * Take all the webFile contained in the webFileToDownload list and download them  
	 */
	public void run() {
		// TODO Voir s'il faut ajouter la liste webFileInDownloading
		String defaultHost;
		
		
		if (startURI.getPort() > 0) {
			defaultHost = startURI.getScheme() + HtmlConstants.SCHEME_AND_AUTHORITY_SEPARATOR + startURI.getHost() + ":" + startURI.getPort();
		} else {
			defaultHost = startURI.getScheme() + HtmlConstants.SCHEME_AND_AUTHORITY_SEPARATOR + startURI.getHost();
		}
		
		
		while(webFileToDownload.size() > 0) {
			boolean downloadedFile = false;
			WebFile webFile = webFileToDownload.get(0);
			URI webFileUri = webFile.getURI();
			String webFileHostURI;
			
			if (webFileUri.getPort() > 0) {
				webFileHostURI = webFileUri.getScheme() + HtmlConstants.SCHEME_AND_AUTHORITY_SEPARATOR + webFileUri.getHost() + ":" + webFileUri.getPort();
			} else {
				webFileHostURI = webFileUri.getScheme() + HtmlConstants.SCHEME_AND_AUTHORITY_SEPARATOR + webFileUri.getHost();
			}
			
			//download the webFile
			System.out.println("--Downloading: " + webFileHostURI + webFileUri.getPath() + " - depth: " + webFile.getDepth());
			
			//filter the links which have not the same host
			//TODO: voir si on dl les liens exterieurs (peut etre une option dans la config) => je pense que non
			if (webFileHostURI.equals(defaultHost)) {
				try {
					downloadedFile = webFile.download(destDirectory);
				} catch (IOException e1) {
					System.err.println(e1);
				}
				
				//delete the webFile from the list to download
				webFileToDownload.remove(webFile);
				
				if (downloadedFile) {
					//add the webFile in the list of downloaded 
					webFileDownloaded.add(webFile); //TODO: est ce je vais m'en servir?
					//parsing of the webFile if it is a HTML document
					if (webFile.getContentType().contains(HtmlConstants.TEXT_HTML)) {
						System.out.println("------parsing: " + destDirectory + webFileUri.getPath());
						HashMap<String,Integer> links = webFile.parseHtml(webFile.getDepth(), defaultHost);
						Iterator<String> it = links.keySet().iterator();
						while (it.hasNext()) {
							String keyURI;
							keyURI = it.next();
							int valueDepth = links.get(keyURI);
							//add with addURI in the DownloadManager queue
							URI u = null;
							try {
								u = new URI(keyURI);
								//TODO: voir pour le port: je ne pense pas que ce soit necessaire ici...
								if (u.getScheme() != null && u.getHost() != null && u.getPath() != null) {
									addURI(u, valueDepth);
								} else {
									if (u.getPort() > 0) {
										System.err.println("INVALID URI: " + u.getScheme() + HtmlConstants.SCHEME_AND_AUTHORITY_SEPARATOR + u.getHost() + ":" + u.getPort() + u.getPath());
									} else {
										System.err.println("INVALID URI: " + u.getScheme() + HtmlConstants.SCHEME_AND_AUTHORITY_SEPARATOR + u.getHost() + u.getPath());
									}
									//TODO: essayer de corriger le path plutot que de ne pas telecharger le fichier ?
								}
							} catch (URISyntaxException e) {						
								System.err.println(e);
							}
						}
					}
				}
			} else {
				//it is not the same web site or the same protocol
				System.err.println(webFileHostURI + " / " + defaultHost + ": NOT THE SAME HOST OR THE SAME PROTOCOL");
				//TODO: voir ce que l'on fait de cet affichage sur stderr
				//delete the webFile from the list to download
				webFileToDownload.remove(webFile);
			}
		}
	}

	/*
	 * Add a webFile in the queue if it hasn't been already downloaded 
	 */
	public void addURI(URI uri, int depth) {
		//creates the webFile according to the URI 
		if (!isUriAlreadyDownloaded(uri)) {
			//verification if the depth is good
			if (depth <= maxDepth) {
				if (uri.getPort() > 0) {
					discoveredURI.add(uri.getScheme() + HtmlConstants.SCHEME_AND_AUTHORITY_SEPARATOR + uri.getHost() + ":" + uri.getPort() + uri.getPath());
				} else {
					discoveredURI.add(uri.getScheme() + HtmlConstants.SCHEME_AND_AUTHORITY_SEPARATOR + uri.getHost() + uri.getPath());
				}
				webFileToDownload.add(new WebFile(uri, depth));
			}
		}
	}
	
	/*
	 * Return true if the WebFile has been already downloaded or if we shoutd not try to download it, else false
	 */
	public boolean isUriAlreadyDownloaded(URI uri) {
		String discoveredURIString;
		for (int i=0; i<discoveredURI.size(); i++) {
			discoveredURIString = discoveredURI.get(i);//.getPath();
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
}
