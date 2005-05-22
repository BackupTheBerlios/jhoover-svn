package fr.umlv.ir2.jhoover;

import java.awt.Dimension;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.JFrame;

import fr.umlv.ir2.jhoover.network.DownloadManager;

/** 
 * Main Class
 * 
 * @author Romain Papuchon 
 */
public class JHoover {
	private static DownloadManager downloadManager;
	
	
	public JHoover(URL startURL, int maxDLHtml, int maxDLLink, int maxDepth) {		
		downloadManager = new DownloadManager(maxDLHtml, maxDLLink, maxDepth);
	}

	/**
	 * Main class
	 * @param args
	 */
	public static void main(String[] args) {
		JHoover jHoover;
		URL startURL = null;
		int maxDLHtml = 4;
		int maxDLLink = 4;
		int maxDepth = 4;
		String urlString = "http://www.google.fr/index.html";
		
		try {
			startURL = new URL(urlString);			
		} catch (MalformedURLException e) {		
			System.err.println(e);
		}
				
		
		jHoover = new JHoover(startURL, maxDLHtml, maxDLLink, maxDepth);
		//add the file pointed by startURL in the downloadList from the downloadManager
		downloadManager.addURL(startURL, 0);
		downloadManager.run();
		
		/*
		JFrame frame = new JFrame("JHoover -- The Papuch's Web Hoover");
		//initialisation of the Frame
		initFrame(frame);
		frame.setVisible(true);
		*/
		
	}

	private static void initFrame(JFrame frame) {
		frame.setSize(new Dimension(800, 600));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
