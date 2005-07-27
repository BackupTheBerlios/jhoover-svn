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

import org.htmlparser.Node;
import org.htmlparser.Parser;
import org.htmlparser.tags.ImageTag;
import org.htmlparser.tags.LinkTag;
import org.htmlparser.util.ParserException;
import org.htmlparser.visitors.ObjectFindingVisitor;

import fr.umlv.ir2.jhoover.gui.detailled.DetailledModel;
import fr.umlv.ir2.jhoover.network.util.HtmlConstants;

/**
 * @author Romain Papuchon
 *
 */
public class DownloadAndParseFile extends Thread {
	private DownloadManager downloadManager;
	private WebFile webFile;
	private String defaultHost;
	private String destDirectory;
	private boolean pauseThread = false; //to pause the Thread
	

	/**
	 * Creates a DownloadAndParseFile
	 * @param downloadManager to manage the Download
	 * @param webFile webFile to parse
	 * @param defaultHost url given in the configuration
	 * @param destDirectory directory on the hard drive to save the distant webSite
	 */
	public DownloadAndParseFile(DownloadManager downloadManager, WebFile webFile, String defaultHost, String destDirectory) {
		this.downloadManager = downloadManager;
		this.webFile = webFile;
		this.defaultHost = defaultHost;
		this.destDirectory = destDirectory;
	}

	
	public void run() {
		URI webFileUri = this.webFile.getURI();
		String webFileHostURI;
		
		if (webFileUri.getPort() > 0) {
			webFileHostURI = webFileUri.getScheme() + HtmlConstants.SCHEME_AND_AUTHORITY_SEPARATOR + webFileUri.getHost() + ":" + webFileUri.getPort();
		} else {
			webFileHostURI = webFileUri.getScheme() + HtmlConstants.SCHEME_AND_AUTHORITY_SEPARATOR + webFileUri.getHost();
		}
		testIfPaused();
		//download the webFile
			System.out.println("--Downloading: " + webFileHostURI + webFileUri.getPath() + " - depth: " + this.webFile.getDepth());
		
		//filter the links which have not the same host
		if (webFileHostURI.equals(this.defaultHost)) {
			boolean downloadedFile = false;
			testIfPaused();
			try {
				downloadedFile = download();
			} catch (IOException e1) {
				System.err.println(e1);
			}
			testIfPaused();
			if (downloadedFile) {
				//parsing of the webFile if it is a HTML document
				if (this.webFile.getContentType().contains(HtmlConstants.TEXT_HTML)) {
					System.out.println("------parsing: " + this.destDirectory + webFileUri.getPath());
					testIfPaused();
					parseHtml(this.webFile);
				}
			} else {
				//error during download, set the progression to (-1)
				this.webFile.setProgression(-2);
				//do a fire in the detailledModel
				int indexInModel = DetailledModel.getInstance().getIndexWebFile(this.webFile);
				DetailledModel.getInstance().fireTableRowsUpdated(indexInModel, indexInModel);
			}
		} else {
			//it is not the same web site or the same protocol
			System.err.println(webFileHostURI + " / " + this.defaultHost + ": NOT THE SAME HOST OR THE SAME PROTOCOL");
		}
		testIfPaused();
		this.downloadManager.endDownload(this.webFile);
	}
	
	
	/**
	 * Download the file and return the status
	 * @return true if the file has been downloaded, false else
	 * @throws IOException
	 */
	public boolean download() throws IOException {
		HttpURLConnection connection = null;
		FileOutputStream fileOutputStream = null;
		InputStream inputStream = null;
		byte[] buffer = new byte[4096];
		int nbBytes = 0;
		String localPath = this.destDirectory + this.webFile.getURI().getPath();
		
		
		if (!isCancelled()) {
			
			//Open the connexion
			try {
				connection = (HttpURLConnection) this.webFile.getURI().toURL().openConnection();
				connection.connect();
			} catch (IOException e) {			
				System.err.println(e);
			}
			testIfPaused();
			//tests the response code
			if (canBeDownloaded(connection)) {
				try {
					inputStream = connection.getInputStream();
				} catch (IOException e2) {
					System.err.println(e2);
				}
				testIfPaused();
				//parameters from the file
				this.webFile.setContentType(connection.getContentType());
				//we cannot know the realSize (certainly blocked by server)
				if (connection.getContentLength() == -1) {
					this.webFile.setRealSize(-2);
				} else {
					this.webFile.setRealSize(connection.getContentLength());	
				}
				testIfPaused();
				//Opening File for writing
				File file = new File(localPath);
				//creates the directory if it doesn't yet exist
				if (!file.getParentFile().exists()) {
					if (!file.getParentFile().mkdirs()) {
						throw new IOException("Cannot create ancestor directories: '" + localPath + "'");
						//TODO: voir si on quitte le programme ou pas
					}
				}
				try {
					fileOutputStream = new FileOutputStream(file);
				} catch (FileNotFoundException e1) {
					System.err.println(e1);
				}
				testIfPaused();
				//Writing the data in the File
				try {
					nbBytes = inputStream.read(buffer);
				} catch (IOException e) {
					System.err.println(e);
				}
				int downloadedSize = 0;
				while (nbBytes > 0) {
					if (!isCancelled()) {
						testIfPaused();
						try {
							fileOutputStream.write(buffer, 0, nbBytes);
							downloadedSize += nbBytes;
							//progression in percent
							if (this.webFile.getRealSize() == -2) {
								//cannot kwnow the progression
								this.webFile.setProgression(-2);
							} else {
								this.webFile.setProgression(downloadedSize * 100 / this.webFile.getRealSize());
							}
							//do a fire in the detailledModel
							int indexInModel = DetailledModel.getInstance().getIndexWebFile(this.webFile);
							DetailledModel.getInstance().fireTableRowsUpdated(indexInModel, indexInModel);
						} catch (IOException e) {
							System.err.println(e);
						}
						try {
							nbBytes = inputStream.read(buffer);
						} catch (IOException e) {
							System.err.println(e);
						}
					}
				}
				
				//closing the streams
				try {
					inputStream.close();
					fileOutputStream.close();
				} catch (IOException e) {
					System.err.println(e);
				}
				return true;
			}
		}
		return false;
	}

	
	/**
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
		//TODO: voir s'il faut ajouter des codes de retour ou pas
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

	

	/**
	 * Parse the WebFile: extract linked files and html files 
	 * @param parent parent of the webFile
	 */
	public void parseHtml(WebFile parent) {
		int depthParent = parent.getDepth();
		Parser parser;
		Node [] images;
		try {
			parser = new Parser(this.defaultHost + this.webFile.getURI().getPath());
			images = parser.extractAllNodesThatAre(ImageTag.class);
			for (int i = 0; i < images.length; i++)	{
				if (!isCancelled()) {
					ImageTag imageTag = (ImageTag)images[i];
					//System.out.println (imageTag.getImageURI ());
					try {
						this.downloadManager.addLinkedFile(new URI(imageTag.getImageURL()), depthParent+1, parent);
					} catch (URISyntaxException e) {
						System.err.println("INVILID URI: " + imageTag.getImageURL());
					}
				} else {
					break;
				}
			}

			if (!isCancelled()) {
				parser = new Parser(this.defaultHost + this.webFile.getURI().getPath());
				ObjectFindingVisitor visitor = new ObjectFindingVisitor(LinkTag.class);
				parser.visitAllNodesWith(visitor);
				Node[] links = visitor.getTags ();
				for (int i = 0; i < links.length; i++) {
					if (!isCancelled()) {
						LinkTag linkTag = (LinkTag)links[i];
//						System.out.print ("\"" + linkTag.getLinkText () + "\" => ");
//						System.out.println (linkTag.getLink ());
						try {
							if (linkTag.isHTTPLikeLink()) {
								//TODO: faire un filtre ici sur linkTag.getLink()
								this.downloadManager.addHtmlFile(new URI(linkTag.getLink()), depthParent+1);
							}
						} catch (URISyntaxException e) {
							System.err.println("INVILID URI: " + linkTag.getLink());
						}
					} else {
						break;
					}
				}
			}
		} catch (ParserException e) {
			System.err.println(e);
		}
	}
	
	
	/**
	 * Check if the Thread has been paused
	 */
	private void testIfPaused() {
        synchronized (this) {
            while (pauseThread) {
                try {
                    wait();
                } catch (Exception e) {
                }
            }
        }
	}
	
	
	/**
	 * Check if the Thread has been cancelled
	 * @return true if the Thread has been cancelled, false else
	 */
	private boolean isCancelled() {
		if (Thread.interrupted()) {
			interrupt();
			return true;
		}
		return false;
	}
	
	
	/**
	 * Pause or Resume the Thread
	 * @param pause true to pause the thread or false to resume it
	 */
	public void setPauseStatus(boolean pause) {
		this.pauseThread = pause;
	}
	
	
	/**
	 * Return the WebFile of the DownloadAndParseFile
	 * @return the webFile
	 */
	public WebFile getWebFile() {
		return webFile;
	}
}
