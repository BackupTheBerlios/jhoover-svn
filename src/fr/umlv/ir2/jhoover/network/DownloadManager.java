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
	private ArrayList<URI> discoveredURI; 
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
		discoveredURI = new ArrayList<URI>();
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
		
		String defaultHost = startURI.getScheme() + HtmlConstants.SCHEME_AND_AUTHORITY_SEPARATOR + startURI.getHost();
		
		while(webFileToDownload.size() > 0) {
			boolean downloadedFile = false;
			WebFile webFile = webFileToDownload.get(0);
			String webFileHostURI = (webFile.getURI().getScheme() + HtmlConstants.SCHEME_AND_AUTHORITY_SEPARATOR + webFile.getURI().getHost());
			
			//download the webFile
			System.out.println("--Downloading: " + webFileHostURI + webFile.getURI().getPath() + " - depth: " + webFile.getDepth());
			
			//filter the links which have not the same host
			//TODO: voir si on dl les liens exterieurs (peut etre une option dans la config)
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
					//TODO: voir si le contains() fonctionne dans tous les coups, apparemment oui
					if (webFile.getContentType().contains(HtmlConstants.TEXT_HTML)) {
						System.out.println("------parsing: " + destDirectory + webFile.getURI().getPath());
						HashMap<String,Integer> links = webFile.parseHtml(webFile.getDepth(), defaultHost);
						Iterator<String> it = links.keySet().iterator();
						while (it.hasNext()) {
							String keyURI;
							keyURI = it.next();
							int valueDepth = links.get(keyURI);
							//add with addURI in the DownloadManager queue
							try {
								addURI(new URI(keyURI), valueDepth);
							} catch (URISyntaxException e) {						
								System.err.println(e);
								//TODO: essayer de corriger le path plutot que de ne pas telecharger le fichier
							}
						}
					}
				}
			} else {
				//it is not the same web site or the same protocol
				System.err.println(webFileHostURI + " / " + defaultHost + ": NOT THE SAME HOST OR THE SAME PROTOCOL");
				//TODO: voir pour cet affichage sur stderr
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
			WebFile webFile = new WebFile(uri, depth);
			//verification if the depth is good
			if (webFile.getDepth() <= maxDepth) {
				discoveredURI.add(webFile.getURI());
				webFileToDownload.add(webFile);
			}
		}
	}
	
	/*
	 * Return true if the WebFile has been already downloaded, else false
	 */
	public boolean isUriAlreadyDownloaded(URI uri) {
		String pathWebFile = uri.getPath();
		String discoveredURIString;
		for (int i=0; i<discoveredURI.size(); i++) {
			discoveredURIString = discoveredURI.get(i).getPath();
			System.out.println("discoveredURIString: " + discoveredURIString + "\npathWebFile: " + pathWebFile);
			//if (pathWebFile.compareTo(discoveredURI.get(i).getPath()) == 0){
			if (pathWebFile.compareTo(discoveredURIString) == 0){
				return true;
			}			
		}
		return false;		
	}
}
