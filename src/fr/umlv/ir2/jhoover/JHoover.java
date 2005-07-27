/**
 * jHoover - UMLV IR2
 * UI Project
 */
package fr.umlv.ir2.jhoover;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.net.URI;
import java.net.URISyntaxException;

import javax.swing.JLabel;
import javax.swing.JWindow;

import fr.umlv.ir2.jhoover.gui.dialog.RunConfigDialog;
import fr.umlv.ir2.jhoover.gui.tool.Icons;
import fr.umlv.ir2.jhoover.gui.tool.Labels;
import fr.umlv.ir2.jhoover.network.DownloadManager;

/** 
 * @author Romain Papuchon 
 * 
 * 
 */
public class JHoover {
	
	private DownloadManager downloadManager;
	private static JHoover INSTANCE = null;
	
	
	public static JHoover getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new JHoover();
		}
		return INSTANCE;
	}
	
	
	/**
	 * Main class
	 * @param args
	 */
	public static void main(String[] args) {
		//TODO: voir si cette methode est OK pour le lancement de l'application
		
		//create the SplashScreen
		JWindow window = createJHooverSplashScreen();
		
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
				//Running JHoover Frame
				new RunConfigDialog(Labels.RUN_JHOOVER_LABEL);
            }
        });

		//close the SplashScreen
		window.dispose();
	}
	
	private static JWindow createJHooverSplashScreen() {
		//TODO: mettre une bordure pour faire mieux
		JWindow window = new JWindow();
		window.getContentPane().add(new JLabel(Icons.JHOOVER_JWINDOW));
		window.setAlwaysOnTop(true);
		window.setVisible(true);
		window.pack();
		//center the JWindow
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		window.setLocation(dim.width / 2 - window.getWidth() / 2 , dim.height / 2 - window.getHeight() / 2);
		return window;
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
	
	
	public void startDownload(String projectName, String startURIString, String destDirectory, int maxDepth, int maxDLHtml, int maxDLLink, String regExp) {
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
		
		downloadManager = DownloadManager.getInstance(maxDLHtml, maxDLLink, maxDepth, startURI, destDirectory);
		//add the file pointed by startURI in the downloadList from the downloadManager
		downloadManager.addHtmlFile(startURI, 0);
		downloadManager.start();
	}
	
	
	public DownloadManager getDownloadManager() {
		return downloadManager;
	}
}
