/**
 * jHoover - UMLV IR2
 * UI Project
 */
package fr.umlv.ir2.jhoover.network;

import java.net.URL;
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
	private ArrayList<WebFile> webFileInDownloading;
	private ArrayList<URL> discoveredURL; 
	private int maxDLHtml;
	private int maxDLLink;
	private int currentDLHtml;
	private int currentDLLink;
	private int maxDepth;
	
	
	/*
	 * Creates a DownloadManager
	 */
	public DownloadManager(int maxDLHtml ,int maxDLLink, int maxDepth) {
		webFileToDownload = new ArrayList<WebFile>();
		webFileToDownload = new ArrayList<WebFile>();
		webFileInDownloading = new ArrayList<WebFile>();
		discoveredURL = new ArrayList<URL>();
		this.maxDLHtml = maxDLHtml;
		this.maxDLLink = maxDLLink;
		currentDLHtml = 0;
		currentDLLink = 0;
		this.maxDepth = maxDepth;
	}
	
	/*
	 * Thread to download files
	 */
	public void run() {
		// TODO Gerer les listes (ToDL, InDL, DLed)
		for (int i=0;i<webFileToDownload.size();i++) {  //TODO: certainement un while(webFileToDownload.size() > 0)
			WebFile webFile1 = webFileToDownload.get(i);
			
			//download the webFile and bring back the links contained in the webFile
			HashMap<URL,Integer> links = webFile1.download();
			System.out.println("[DownloadManager]: DL terminé, analyse des liens");
			
			Iterator<URL> it = links.keySet().iterator();
			while (it.hasNext()) {
				URL key = it.next();
				int value = links.get(key);
				//add with addURL in the DownloadManager queue
				System.out.println("URL: " + key + " - value: " + value);
				addURL(key, value);
			}
		}
	}
	
	/*
	 * Add a webFile in the queue if it hasn't been already downloaded 
	 */
	public void addURL(URL url, int depth) {
		//creates the webFile according to the url 
		WebFile webFile = new WebFile(url, depth);
		if (!isFileAlreadyDownloaded(webFile)) {
			//verification if the depth is good
			if (webFile.getDepth() < maxDepth) {
				discoveredURL.add(webFile.getUrl());
				webFileToDownload.add(webFile);
			}
		}
	}
	
	/*
	 * Return true if the WebFile has been already downloaded, else false
	 */
	public boolean isFileAlreadyDownloaded(WebFile webFile) {
		for (int i=0; i<discoveredURL.size(); i++) {
			if (webFile.getUrl().getPath().equals(discoveredURL.get(i).getPath())){
				return true;
			}			
		}
		return false;		
	}
}
