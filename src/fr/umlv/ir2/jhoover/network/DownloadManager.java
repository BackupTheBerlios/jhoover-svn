/**
 * jHoover - UMLV IR2
 * UI Project
 */
package fr.umlv.ir2.jhoover.network;

import java.io.IOException;
import java.net.MalformedURLException;
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
	//private ArrayList<WebFile> webFileInDownloading;
	private ArrayList<URL> discoveredURL; 
	private int maxDLHtml;
	private int maxDLLink;
	private int currentDLHtml;
	private int currentDLLink;
	private int maxDepth;
	private URL defaultUrl;
	private String defaultPathString;
	
	
	/*
	 * Creates a DownloadManager
	 */
	public DownloadManager(int maxDLHtml ,int maxDLLink, int maxDepth, URL defaultUrl, String defaultPathString) {
		webFileToDownload = new ArrayList<WebFile>();
		webFileDownloaded = new ArrayList<WebFile>();
		//webFileInDownloading = new ArrayList<WebFile>();
		discoveredURL = new ArrayList<URL>();
		this.maxDLHtml = maxDLHtml;
		this.maxDLLink = maxDLLink;
		currentDLHtml = 0;
		currentDLLink = 0;
		this.maxDepth = maxDepth;
		this.defaultUrl = defaultUrl;
		this.defaultPathString = defaultPathString;
	}
	
	/*
	 * Thread to download files
	 */
	public void run() {
		// TODO Gerer les listes (ToDL, InDL, DLed)
		while(webFileToDownload.size() > 0) {
			WebFile webFile1 = webFileToDownload.get(0);
			
			//download the webFile
			//System.out.println(webFile1.getUrl());
			System.out.println("--Downloading the webFile: " + webFile1.getUrl().getPath() + "--");
			try {
				webFile1.download(defaultPathString, defaultUrl.getProtocol() + "://" +defaultUrl.getHost());
				//TODO: voir pour mettre la partie de droite dans une variable, utilisée plusieurs fois
			} catch (IOException e1) {
				System.err.println(e1);
			}
			
			//delete the webFile from the list to download
			webFileToDownload.remove(webFile1);
			
			//add the webFile in the list of downloaded 
			webFileDownloaded.add(webFile1); //TODO: est ce je vais m'en servir?

			//parsing of the webFile
			System.out.println("--parsing of the webFile: " + webFile1.getUrl().getFile() + "--");
			HashMap<String,Integer> links = webFile1.parseHtml(webFile1.getDepth(), defaultUrl.getProtocol() + "://" +defaultUrl.getHost()); 
		
			
			Iterator<String> it = links.keySet().iterator();
			while (it.hasNext()) {
				String key;
				try {
					key = it.next();
					int value = links.get(key);
					//add with addURL in the DownloadManager queue
					System.out.println("URL: " + key + " - value: " + value);
					addURL(new URL(key), value);
				} catch (MalformedURLException e) {
					System.err.println(e);
				}
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
			if (webFile.getDepth() <= maxDepth) {
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
