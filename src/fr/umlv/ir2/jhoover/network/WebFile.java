/**
 * jHoover - UMLV IR2
 * UI Project
 */
package fr.umlv.ir2.jhoover.network;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.Date;
import java.util.HashMap;

import javax.swing.text.Document;
import javax.swing.text.EditorKit;
import javax.swing.text.Element;
import javax.swing.text.ElementIterator;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.html.HTML;
import javax.swing.text.html.HTMLEditorKit;

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
		downloadedSize = realSize;
		
				
		System.out.println("--parsing of the webFile--");
		//parsing of the webFile
		HashMap<URL,Integer> urlLinks = parseHtml(this, depth, inputStream);
		
		return urlLinks;
	}
	
	
	private HashMap<URL,Integer> parseHtml(WebFile fileToParse, int depthParent, InputStream inputStream) {
		// TODO: reprendre le code proprement
		HashMap<URL,Integer> linkList = new HashMap<URL,Integer>();
		
		
		//if it is a HTML document
		if (contentType.equals(Constants.TEXT_HTML)) {
			EditorKit editorKit = new HTMLEditorKit();
			Document document = editorKit.createDefaultDocument();
			//document.putProperty("IgnoreCharsetDirective", Boolean.TRUE);
			try {
				if(inputStream != null) {
					editorKit.read(inputStream, document, 0);
					ElementIterator it = new ElementIterator(document);
					Element elem;
					
					while ((elem = it.next()) != null) {
						
						
						
						SimpleAttributeSet simpleAttributeSet;
						//search for a "a href" tag
						if((simpleAttributeSet = (SimpleAttributeSet)elem.getAttributes().getAttribute(HTML.Tag.A)) != null) {
							Object o;
							if((o = simpleAttributeSet.getAttribute(HTML.Attribute.HREF)) != null) {
								System.err.println(o);
								linkList.put(new URL(o.toString()), depthParent+1);							
							}
						}
						
						//TODO: voir pour les images...
						/*
						if((simpleAttributeSet = (SimpleAttributeSet)elem.getAttributes().getAttribute(HTML.Tag.IMG)) != null) {
							if((o = simpleAttributeSet.getAttribute(HTML.Attribute.SRC)) != null) {
								url = o.toString();							
								if(linkValid(url)) {
									links.add(getAbsolutePath(url));						
								}
							}
						}
						*/
					}		
					
				} else {
					System.out.println("InputStream vide!!!");
				}
			} 
			catch (Exception e) {
				System.err.println(e);
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
