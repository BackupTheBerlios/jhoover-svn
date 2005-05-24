package fr.umlv.ir2.jhoover;

import java.net.URI;
import java.net.URISyntaxException;

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
		JHoover jHoover;
		//String startURIString = "http://membres.lycos.fr/huchon/index.html";
		String startURIString = "http://www.univ-mlv.fr/index.php";
		URI startURI = null;
		int maxDepth = 3;
		int maxDLHtml = 4;
		int maxDLLink = 4;
		String destDirectory = "C:/TEMP";
		//TODO: creer un repertoire avec le nom du projet
		
		try {
			startURI = new URI(startURIString);
		} catch (URISyntaxException e) {
			System.err.println(e);
			//TODO: générer une erreur et quitter programme ici
		}			
		
		jHoover = new JHoover(maxDLHtml, maxDLLink, maxDepth, startURI, destDirectory);
		//add the file pointed by startURI in the downloadList from the downloadManager
		downloadManager.addURI(startURI, 0);
		downloadManager.run();
		
		
//		JFrame frame = new JFrame("JHoover -- The Papuch's Web Hoover");
//		//initialisation of the Frame
//		initFrame(frame);
//		frame.setVisible(true);
	}

//	private static void initFrame(JFrame frame) {
//		frame.setSize(new Dimension(800, 600));
//		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//	}
}
