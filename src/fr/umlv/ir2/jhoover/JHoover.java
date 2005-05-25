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
		String startURIString = "http://membres.lycos.fr/huchon/index.html";
//		String startURIString = "http://www.univ-mlv.fr/index.php";
//		String startURIString = "http://amb-mfg-intra2k:8399/index.asp";
//		String startURIString = "http://amb-mfg-intra01:8011/exploit/default.asp";
		URI startURI = null;
		int maxDepth = 5;
		int maxDLHtml = 4;
		int maxDLLink = 4;
		String destDirectory = "C:/TEMP";
//		String projectName = "univ";  //TODO: verifier eventuellement que le nom du projet ne finisse pas par "/"
		String projectName = "huchon";
//		String projectName = "amb";
//		String projectName = "cdcatalog";

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
