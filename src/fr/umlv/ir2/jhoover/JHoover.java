package fr.umlv.ir2.jhoover;

import java.net.URI;
import java.net.URISyntaxException;

import fr.umlv.ir2.jhoover.gui.config.ConfigFrame;
import fr.umlv.ir2.jhoover.network.DownloadManager;

/** 
 * Main Class
 * 
 * @author Romain Papuchon 
 */
public class JHoover {
	private static DownloadManager downloadManager;
	
	public JHoover(int maxDLHtml, int maxDLLink, int maxDepth, URI startURI, String destDirectory) {		
		downloadManager = new DownloadManager(maxDLHtml, maxDLLink, maxDepth, startURI, destDirectory);
	}

	/**
	 * Main class
	 * @param args
	 */
	public static void main(String[] args) {
		//Configuration Frame
		ConfigFrame configFrame = new ConfigFrame("Configuration of jHoover");
	}

	public static void startDownload(String projectName, String startURIString, String destDirectory, int maxDepth, int maxDLHtml, int maxDLLink, String regExp) {
		//TODO: utiliser la RegExp
		URI startURI = null;
		//adds the project name in the path
		if (destDirectory.endsWith("/")) {
			destDirectory = destDirectory + projectName; 
		} else {
			destDirectory = destDirectory + "/" + projectName; 
		}
		
		try {
			startURI = new URI(startURIString);
		} catch (URISyntaxException e) {
			System.err.println(e);
			System.out.println("Exiting the program...");
			System.exit(0);
		}			
		
		new JHoover(maxDLHtml, maxDLLink, maxDepth, startURI, destDirectory);
		//add the file pointed by startURI in the downloadList from the downloadManager
		downloadManager.addHtmlFile(startURI, 0);
		Thread downloadManagerThread = new Thread(downloadManager);
		downloadManagerThread.start();
	}
}
