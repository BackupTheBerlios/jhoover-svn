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
	
	
	public JHoover(URL startURL, int maxDLHtml, int maxDLLink, int maxDepth, URL defaultUrl, String defaultPathString) {		
		downloadManager = new DownloadManager(maxDLHtml, maxDLLink, maxDepth, defaultUrl, defaultPathString);
	}

	/**
	 * Main class
	 * @param args
	 */
	public static void main(String[] args) {
		JHoover jHoover;
		URL defaultUrl = null;
		int maxDLHtml = 4;
		int maxDLLink = 4;
		int maxDepth = 8;
		String defaultUrlString = "http://membres.lycos.fr/huchon/index.html";
		String defaultPathString = "C:/TEMP/";
		
		
		try {
			defaultUrl = new URL(defaultUrlString);			
		} catch (MalformedURLException e) {		
			System.err.println(e);
		}
				
		
		jHoover = new JHoover(defaultUrl, maxDLHtml, maxDLLink, maxDepth, defaultUrl, defaultPathString);
		//add the file pointed by startURL in the downloadList from the downloadManager
		downloadManager.addURL(defaultUrl, 0);
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
