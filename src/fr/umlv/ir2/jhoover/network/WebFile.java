/**
 * jHoover - UMLV IR2
 * UI Project
 */
package fr.umlv.ir2.jhoover.network;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Date;
import java.util.HashMap;

import org.htmlparser.Node;
import org.htmlparser.Parser;
import org.htmlparser.tags.ImageTag;
import org.htmlparser.tags.LinkTag;
import org.htmlparser.util.ParserException;
import org.htmlparser.visitors.ObjectFindingVisitor;

/**
 * @author Romain Papuchon
 *
 * Represents a file
 */
public class WebFile {
	private URI uri;
	private int depth;
	private String contentType;
	private int realSize;
	private int downloadedSize;
	private Date beginDate;

	
	/*
	 * Creates a WebFile
	 */
	public WebFile(URI uri, int depth) {
		this.uri = uri;		
		downloadedSize = 0;
		realSize = -1;
		this.depth = depth;
	}
	
	
	/*
	 * Download the file and return the links
	 * @return the list of links contained in the webFile
	 */
	public boolean download(String destDirectory) throws IOException {
		HttpURLConnection connection = null;
		FileOutputStream fileOutputStream = null;
		InputStream inputStream = null;
		byte[] buffer = new byte[4096];
		int nbBytes = 0;
		String localPath = destDirectory + uri.getPath();
		
		
		/*
		 * Open the connexion
		 */
		try {
			connection = (HttpURLConnection) uri.toURL().openConnection();  //TODO: porc, peut-on faire autrement?
			connection.connect();
		} catch (IOException e) {			
			System.err.println(e);
		}

		//tests the response code
		if (canBeDownloaded(connection)) {
			inputStream = connection.getInputStream();
			/*
			 * parameters from the file
			 */
			realSize = connection.getContentLength();
			contentType = connection.getContentType();
			beginDate = new Date(connection.getDate());
//			System.out.println("realSize: " + realSize);
//			System.out.println("contentType: " + contentType);
//			System.out.println("beginDate: " + beginDate);
			
			
			/*
			 * Opening File for writing 
			 */
			File file = new File(localPath);
			//creates the directory if it doesn't yet exist
			if (!file.getParentFile().exists()) {
				if (!file.getParentFile().mkdirs()) {
					throw new IOException("Cannot create ancestor directories: '" + localPath + "'");
				}
			}
			
			try {
				fileOutputStream = new FileOutputStream(file);
			} catch (FileNotFoundException e1) {
				System.err.println(e1);
			}
			
			
			/*
			 * Writing the data in the File
			 */
			try {
				nbBytes = inputStream.read(buffer);
			} catch (IOException e) {
				System.err.println(e);
			}
			while (nbBytes > 0) {
				try {
					fileOutputStream.write(buffer, 0, nbBytes);
				} catch (IOException e) {
					System.err.println(e);
				}
				try {
					nbBytes = inputStream.read(buffer);
				} catch (IOException e) {
					System.err.println(e);
				}
			}
			
			
			/*
			 * closing the streams
			 */
			try {
				inputStream.close();
				fileOutputStream.close();
			} catch (IOException e) {
				System.err.println(e);
			}
			//TODO: voir pour la taille du fichier et pour les pourcentages de progression
			downloadedSize = realSize;
			return true;
		}
		return false;
	}
	
	
	/*
	 * Tests the response code returned by the connection
	 * 
	 * 200 - Ok (always considered 'good')
	 * 201 - Created
	 * 202 - Accepted
	 * 204 - No Content
	 * 301 - Moved Permanently (re-direct)
	 * 302 - Found (re-direct)
	 * 304 - Not Modified (should only be returned if you set a HTTP request header date)
	 * The following codes will certainly indicate failure:
	 * 400 - Any response code in the 400 series
	 * The following codes probably indicate failure (though it could also simply indicate a server is down, although the link is still valid):
	 * 500 - Any response code in the 500 series
	 * Response codes in the 100 series should be considered unexpected (though not an error) from a HEAD request.
	 * 
	 * @return true if the download can begin, false else
	 */
	private boolean canBeDownloaded(HttpURLConnection connection) {
		//TODO: voir s'il faut ajouter des codes ou pas
		try {
			switch (connection.getResponseCode()) {
			case 200:
				return true;
			case 201:
				return true;
			case 202:
				return true;
			default:
				return false;
			}
		} catch (IOException e) {		
			System.err.println(e);
		}
		return false;
	}


	public HashMap<String,Integer> parseHtml(int depthParent, String defaultHost) {
		HashMap<String,Integer> linkList = new HashMap<String,Integer>();
		
		Parser parser;
		Node [] images;
		try {
			//TODO: voir pour le port
			//parser = new Parser (defaultHost + ":" + uri.getPort() + uri.getPath());
			parser = new Parser (defaultHost + uri.getPath());
			images = parser.extractAllNodesThatAre (ImageTag.class);
			for (int i = 0; i < images.length; i++)
			{
				ImageTag imageTag = (ImageTag)images[i];
				//System.out.println (imageTag.getImageURI ());
				try {
					new URI(imageTag.getImageURL());	
					linkList.put(imageTag.getImageURL(), depthParent+1);
				} catch (URISyntaxException e) {
					System.err.println("Lien non valide: " + imageTag.getImageURL());
					//TODO: voir ce que l'on fait ce cette erreur
				}
			}
			parser = new Parser (defaultHost + uri.getPath());
			ObjectFindingVisitor visitor = new ObjectFindingVisitor (LinkTag.class);
			parser.visitAllNodesWith (visitor);
			Node[] links = visitor.getTags ();
			for (int i = 0; i < links.length; i++)
			{
				LinkTag linkTag = (LinkTag)links[i];
				//System.out.print ("\"" + linkTag.getLinkText () + "\" => ");
				//System.out.println (linkTag.getLink ());
				try {
					URI u = new URI(linkTag.getLink());
					linkList.put(linkTag.getLink(), depthParent+1);
				} catch (URISyntaxException e) {
					System.err.println("Lien non valide: " + linkTag.getLink());
					//TODO: voir ce que l'on fait ce cette erreur
				}
			}
		} catch (ParserException e) {
			System.err.println(e);
		}
		return linkList;
	}
	

//	/*
//	 * Returns true if the webFile is completely downloaded, false else
//	 */
//	public boolean isDownloaded() {
//		if (downloadedSize == realSize) return true;
//		return false;
//	}
	
//	/*
//	 * Returns true if the download of the webFile is in progress, false else
//	 */
//	public boolean isInProgress() {
//		if (downloadedSize != 0 && downloadedSize < realSize) return true;
//		return false;
//	}
	
//	/*
//	 * Returns true if the download of the webFile is not in progress, false else
//	 */
//	public boolean isNotInProgress() {
//		if (downloadedSize == 0 && realSize != 0) return true;
//		return false;
//	}
	
//	public int getDownloadedSize() {
//		return downloadedSize;
//	}
	
//	public void setDownloadedSize(int downloadedSize) {
//		this.downloadedSize = downloadedSize;
//	}
	
//	public int getRealSize() {
//		return realSize;
//	}
	
//	public void setRealSize(int realSize) {
//		this.realSize = realSize;
//	}
	
	public URI getURI() {
		return uri;
	}
	
	public int getDepth() {
		return depth;
	}	
	
	public String getContentType() {
		return contentType;
	}
}
