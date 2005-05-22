/**
 * jHoover - UMLV IR2
 * UI Project
 */
package fr.umlv.ir2.jhoover.network;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;

/**
 * @author Romain Papuchon
 *
 * Represents a file
 */
public class WebFile {
	private URL url;
	private int realSize;
	private int downloadedSize;
	private int depth;
	
	
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
	public HashMap<URL,Integer> download() {
		URLConnection connection = null;
		InputStream inputStream = null;
		FileOutputStream fileOutputStream = null;
		byte[] buffer = new byte[4096];
		int nbBytes = 0;
		
		
		System.out.println("--Downloading the webFile: " + url.getFile() + "--");

		try {
			//open the connexion
			connection = url.openConnection();
			connection.connect();
			//Opening the InputStream
			inputStream = connection.getInputStream();
		} catch (IOException e) {			
			System.err.println(e);
		}

		try {
			//Opening File for writing
			fileOutputStream = new FileOutputStream("C:/temp/" + url.getFile());
		} catch (FileNotFoundException e) {
			System.err.println(e);
		}
		
		
		
		
		try {
			nbBytes = inputStream.read(buffer);
		} catch (IOException e) {
			System.err.println(e);
		}
				
		while (nbBytes > 0) {
			try {
				//writes the datas in the file
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
		
		
		//TODO: voir la taille du fichier
		realSize = 512;
		downloadedSize = realSize;
		
		
				
		System.out.println("--parsing of the webFile--");
		//parsing of the webFile
		HashMap<URL,Integer> urlLinks = parseHtml(this, depth);
		
		return urlLinks;
	}
	
	
	private HashMap<URL,Integer> parseHtml(WebFile fileToParse, int depthParent) {
		// TODO: changer cela, temporaire pour le moment
		HashMap<URL,Integer> linkList = new HashMap<URL,Integer>();
		try {
			linkList.put(new URL("http://www.google.fr/webhp.html"), depthParent+1);
			linkList.put(new URL("http://www.google.fr/ads/index.html"), depthParent+1);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
