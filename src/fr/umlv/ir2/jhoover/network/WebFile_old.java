/**
// * jHoover - UMLV IR2
// * UI Project
// */
//package fr.umlv.ir2.jhoover.network;
//
//import java.io.FileInputStream;
//import java.io.FileNotFoundException;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.io.InputStream;
//import java.net.MalformedURLException;
//import java.net.URL;
//import java.net.URLConnection;
//import java.util.Date;
//import java.util.HashMap;
//
//import javax.swing.text.BadLocationException;
//import javax.swing.text.Document;
//import javax.swing.text.EditorKit;
//import javax.swing.text.ElementIterator;
//import javax.swing.text.SimpleAttributeSet;
//import javax.swing.text.html.HTML;
//import javax.swing.text.html.HTMLEditorKit;
//
///**
// * @author Romain Papuchon
// *
// * Represents a file
// */
//public class WebFile_old {
//	private URL url;
//	private int depth;
//	private String contentType;
//	private int realSize;
//	private int downloadedSize;
//	private Date beginDate;
//	private String localPath;
//	
//	
//	
//	/*
//	 * Creates a WebFile
//	 */
//	public WebFile_old(URL url, int depth) {
//		this.url = url;		
//		downloadedSize = 0;
//		realSize = -1;
//		this.depth = depth;
//	}
//	
//	
//	/*
//	 * Download the file and return the links
//	 * @return the list of links contained in the webFile
//	 */
//	public void download() {
//		URLConnection connection = null;
//		FileOutputStream fileOutputStream = null;
//		InputStream inputStream = null;		
//		byte[] buffer = new byte[4096];
//		int nbBytes = 0;
//		
//		
//		/*
//		 * Open the connexion
//		 */
//		try {
//			connection = url.openConnection();
//			connection.connect();
//			inputStream = connection.getInputStream();
//		} catch (IOException e) {			
//			System.err.println(e);
//		}
//
//		
//		/*
//		 * parameters from the file
//		 */
//		realSize = connection.getContentLength();
//		contentType = connection.getContentType();
//		beginDate = new Date(connection.getDate());
//		
//		System.out.println("AFFICHAGE DES PARAMETRES DU FICHIER:");
//		System.out.println("------------------------------------");
//		System.out.println("realSize: " + realSize);
//		System.out.println("contentType: " + contentType);
//		System.out.println("beginDate: " + beginDate);
//		System.out.println("");
//		
//		
//		/*
//		 * Opening File for writing 
//		 */
//		try {
//			localPath = "C:/temp/" + url.getFile();  //TODO: changer le localPath
//			fileOutputStream = new FileOutputStream(localPath);
//		} catch (FileNotFoundException e) {
//			System.err.println(e);
//		}
//
//		
//		/*
//		 * Writing the data in the File
//		 */
//		try {
//			nbBytes = inputStream.read(buffer);
//		} catch (IOException e) {
//			System.err.println(e);
//		}
//		while (nbBytes > 0) {
//			try {
//				fileOutputStream.write(buffer, 0, nbBytes);
//			} catch (IOException e) {
//				System.err.println(e);
//			}
//			try {
//				nbBytes = inputStream.read(buffer);
//			} catch (IOException e) {
//				System.err.println(e);
//			}
//		}
//		
//		
//		/*
//		 * closing the streams
//		 */
//		try {
//			inputStream.close();
//			fileOutputStream.close();
//		} catch (IOException e) {
//			System.err.println(e);
//		}
//		
//		
//		
//		//TODO: voir pour la taille du fichier
//		downloadedSize = realSize;
//	}
//	
//	
//	public HashMap<URL,Integer> parseHtml(int depthParent, String defaultHost) {
//		// TODO: reprendre le code proprement
//		HashMap<URL,Integer> linkList = new HashMap<URL,Integer>();
//		
//		InputStream inputStream = null;
//		
//		try {
//			inputStream = new FileInputStream(localPath);
//		} catch (FileNotFoundException e) {
//			System.out.println(e);
//		}
//		
//		
//		
////		if it is a HTML document
//		if (contentType.equals(Constants.TEXT_HTML)) {
//			//if it is a HTML document
//			if (contentType.equals(Constants.TEXT_HTML)) {
//				
//				EditorKit editorKit = new HTMLEditorKit();
//				Document document = editorKit.createDefaultDocument();
//				//document.putProperty("IgnoreCharsetDirective", Boolean.TRUE);
//				
//				if(inputStream != null) {
//					try {
//						editorKit.read(inputStream, document, 0);
//					} catch (IOException e) {
//						System.err.println(e);
//					} catch (BadLocationException e) {
//						System.err.println(e);
//					}
//					ElementIterator it = new ElementIterator(document);
//					javax.swing.text.Element elem;
//					while ((elem = it.next()) != null) {
//						SimpleAttributeSet simpleAttributeSet = new SimpleAttributeSet(elem.getAttributes());
//						
//						Object o;
//						//search for a "a href" tag
//						if((o = simpleAttributeSet.getAttribute(HTML.Attribute.HREF)) != null) {						
//							try {							
//								linkList.put(new URL(defaultHost + o.toString()), depthParent+1);
//							} catch (MalformedURLException e) {
//								System.out.println(e);
//							}							
//						}
//						
//						//TODO: voir pour les images...
//						/*
//						 if((simpleAttributeSet = (SimpleAttributeSet)elem.getAttributes().getAttribute(HTML.Tag.IMG)) != null) {
//						 if((o = simpleAttributeSet.getAttribute(HTML.Attribute.SRC)) != null) {
//						 url = o.toString();							
//						 if(linkValid(url)) {
//						 links.add(getAbsolutePath(url));						
//						 }
//						 }
//						 }
//						 */
//					}	
//				} else {
//					System.out.println("InputStream vide!!!");
//				}
//			} 
//			return linkList;
//		} }
//		
//
//	/*
//	 * Returns true if the webFile is completely downloaded, false else
//	 */
//	public boolean isDownloaded() {
//		if (downloadedSize == realSize) return true;
//		return false;
//	}
//	
//	/*
//	 * Returns true if the download of the webFile is in progress, false else
//	 */
//	public boolean isInProgress() {
//		if (downloadedSize != 0 && downloadedSize < realSize) return true;
//		return false;
//	}
//	
//	/*
//	 * Returns true if the download of the webFile is not in progress, false else
//	 */
//	public boolean isNotInProgress() {
//		if (downloadedSize == 0 && realSize != 0) return true;
//		return false;
//	}
//	
//	public int getDownloadedSize() {
//		return downloadedSize;
//	}
//	
//	public void setDownloadedSize(int downloadedSize) {
//		this.downloadedSize = downloadedSize;
//	}
//	
//	public int getRealSize() {
//		return realSize;
//	}
//	
//	public void setRealSize(int realSize) {
//		this.realSize = realSize;
//	}
//	
//	public URL getUrl() {
//		return url;
//	}
//	
//	public int getDepth() {
//		return depth;
//	}	
//}
