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
import java.net.URL;
import java.net.URLConnection;
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
	private URL url;
	private int depth;
	private String contentType;
	private int realSize;
	private int downloadedSize;
	private Date beginDate;
	
	
	
	/*
	 * Creates a WebFile
	 */
	public WebFile(URL url, int depth) {
		this.url = url;		
		downloadedSize = 0;
		realSize = -1;
		this.depth = depth;
	}
	
	
	/*
	 * Download the file and return the links
	 * @return the list of links contained in the webFile
	 */
	public void download(String defaultPathString, String defaultHost) throws IOException {
		URLConnection connection = null;
		FileOutputStream fileOutputStream = null;
		InputStream inputStream = null;		
		byte[] buffer = new byte[4096];
		int nbBytes = 0;
		
		//TODO: filter ici pour ne pas downloader les liens qui n'ont pas le même host
		if ((this.getUrl().getProtocol() + "://" + this.getUrl().getHost()).equals(defaultHost)) {
			/*
			 * Open the connexion
			 */
			try {
				connection = url.openConnection();
				connection.connect();
				inputStream = connection.getInputStream();
			} catch (IOException e) {			
				System.err.println(e);
			}

			
			/*
			 * parameters from the file
			 */
			realSize = connection.getContentLength();
			contentType = connection.getContentType();
			beginDate = new Date(connection.getDate());
			
			System.out.println("AFFICHAGE DES PARAMETRES DU FICHIER:");
			System.out.println("------------------------------------");
			System.out.println("realSize: " + realSize);
			System.out.println("contentType: " + contentType);
			System.out.println("beginDate: " + beginDate);
			System.out.println("");
			
			
			/*
			 * Opening File for writing 
			 */
			try {
				String localPath = defaultPathString + url.getFile();			
				File file = new File(localPath);
				//creates the directory if it doesn't yet exist
				if (!file.getParentFile().exists()) {
					if (!file.getParentFile().mkdirs()) {
						throw new IOException("Cannot create ancestor directories: '" + localPath + "'");
					}
				}
				fileOutputStream = new FileOutputStream(file);
			} catch (FileNotFoundException e) {
				System.err.println(e);
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
			
			//TODO: voir pour la taille du fichier
			downloadedSize = realSize;
		} else {
			//it is not the same web site or the same protocol
			contentType = new String();  //TODO: voir ca.
		}
	}
	
	
	public HashMap<String,Integer> parseHtml(int depthParent, String defaultHost) {
		// TODO: reprendre le code proprement
		HashMap<String,Integer> linkList = new HashMap<String,Integer>();
		 
		if (this != null) {  //TODO: voir si c'est bien de faire ca...
			//if it is a HTML document
			if (contentType.equals(Constants.TEXT_HTML)) {
				Parser parser;
				Node [] images;
				try {
					parser = new Parser (defaultHost + url.getPath());
					images = parser.extractAllNodesThatAre (ImageTag.class);
					for (int i = 0; i < images.length; i++)
					{
						ImageTag imageTag = (ImageTag)images[i];
						//System.out.println (imageTag.getImageURL ());					
						linkList.put(imageTag.getImageURL(), depthParent+1);
					}
					parser = new Parser (defaultHost + url.getPath());
					ObjectFindingVisitor visitor = new ObjectFindingVisitor (LinkTag.class);
					parser.visitAllNodesWith (visitor);
					Node[] links = visitor.getTags ();
					for (int i = 0; i < links.length; i++)
					{
						LinkTag linkTag = (LinkTag)links[i];
						//System.out.print ("\"" + linkTag.getLinkText () + "\" => ");
						//System.out.println (linkTag.getLink ());
						linkList.put(linkTag.getLink(), depthParent+1);
					}
				} catch (ParserException e) {
					System.out.println(e);
				}
			}
		}
		return linkList;
	}
	

	/*
	 * Returns true if the webFile is completely downloaded, false else
	 */
	public boolean isDownloaded() {
		if (downloadedSize == realSize) return true;
		return false;
	}
	
	/*
	 * Returns true if the download of the webFile is in progress, false else
	 */
	public boolean isInProgress() {
		if (downloadedSize != 0 && downloadedSize < realSize) return true;
		return false;
	}
	
	/*
	 * Returns true if the download of the webFile is not in progress, false else
	 */
	public boolean isNotInProgress() {
		if (downloadedSize == 0 && realSize != 0) return true;
		return false;
	}
	
	public int getDownloadedSize() {
		return downloadedSize;
	}
	
	public void setDownloadedSize(int downloadedSize) {
		this.downloadedSize = downloadedSize;
	}
	
	public int getRealSize() {
		return realSize;
	}
	
	public void setRealSize(int realSize) {
		this.realSize = realSize;
	}
	
	public URL getUrl() {
		return url;
	}
	
	public int getDepth() {
		return depth;
	}	
}
