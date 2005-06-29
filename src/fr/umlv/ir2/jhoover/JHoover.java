/**
 * jHoover - UMLV IR2
 * UI Project
 */
package fr.umlv.ir2.jhoover;

import java.net.URI;
import java.net.URISyntaxException;

import fr.umlv.ir2.jhoover.gui.dialog.RunConfigDialog;
import fr.umlv.ir2.jhoover.gui.tool.Labels;
import fr.umlv.ir2.jhoover.network.DownloadManager;

/** 
 * @author Romain Papuchon 
 * 
 * 
 */
public class JHoover {
	/**
	 * Main class
	 * @param args
	 */
	public static void main(String[] args) {
		//Running JHoover Frame
		RunConfigDialog configDialog = new RunConfigDialog(Labels.RUN_JHOOVER_LABEL);
	}
	
	
	/*
	 * Return the destDirectory with an "/" at the end
	 */
	public static String validDestDirectory(String destDirectory) {		
		if (destDirectory.endsWith("/")) {
			return destDirectory; 
		}
		return destDirectory = destDirectory + "/"; 
	}
	
	
	public static void startDownload(String projectName, String startURIString, String destDirectory, int maxDepth, int maxDLHtml, int maxDLLink, String regExp) {
		//TODO: utiliser la RegExp
		URI startURI = null;
		
		//Adds the project name in the path
		destDirectory = destDirectory + projectName;
		
		try {
			startURI = new URI(startURIString);
		} catch (URISyntaxException e) {
			System.err.println(e);
			System.out.println("Exiting the program...");
			System.exit(0);
		}			
		
		DownloadManager downloadManager = new DownloadManager(maxDLHtml, maxDLLink, maxDepth, startURI, destDirectory);
		//add the file pointed by startURI in the downloadList from the downloadManager
		downloadManager.addHtmlFile(startURI, 0);
		Thread downloadManagerThread = new Thread(downloadManager);
		downloadManagerThread.start();
	}
}
