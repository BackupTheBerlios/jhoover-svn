/**
 * jHoover - UMLV IR2
 * UI Project
 */
package fr.umlv.ir2.jhoover.network;

import java.awt.BorderLayout;
import java.awt.Color;
import java.net.URI;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTree;
import javax.swing.ToolTipManager;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeSelectionModel;

import org.apache.regexp.RE;

import fr.umlv.ir2.jhoover.gui.ActionManager;
import fr.umlv.ir2.jhoover.gui.JHMainFrame;
import fr.umlv.ir2.jhoover.gui.detailled.DetailledModel;
import fr.umlv.ir2.jhoover.gui.discovery.DiscoveryRenderer;
import fr.umlv.ir2.jhoover.gui.discovery.DiscoveryTreeNode;
import fr.umlv.ir2.jhoover.gui.panel.JHMainPanel;
import fr.umlv.ir2.jhoover.gui.tool.GuiUtils;
import fr.umlv.ir2.jhoover.gui.tool.Labels;
import fr.umlv.ir2.jhoover.network.util.HtmlConstants;
import fr.umlv.ir2.jhoover.network.util.Utils;

/**
 * Manage the download
 * @author Romain Papuchon
 */
public class DownloadManager extends Thread {	
	private ArrayList<DownloadAndParseFile> threadList;
	private ArrayList<WebHtmlFile> htmlFileToDownload, htmlFileInDownloading;
	private ArrayList<WebLinkedFile> linkedFileToDownload, linkedFileInDownloading;
	private ArrayList<String> discoveredURI; 
	private int maxDLHtml, maxDLLink, currentDLHtml, currentDLLink, maxDepth;
	private String destDirectory;
	private String regExp;
	private String defaultHost;
	
	private static DownloadManager INSTANCE = null;
	private boolean pauseThread = false; //to pause the Thread
	
	//for the graphic
	private JTree tree;
	private DiscoveryTreeNode treeRoot;
	private DefaultTreeModel treeModel;
	private DetailledModel detailledModel;

	
	/**
	 * Create a DownloadManager
	 * @param maxDLHtml number of maximum simultaneous html download file
	 * @param maxDLLink number of maximum simultaneous linked download file
	 * @param maxDepth depth of the download in the WebSite
	 * @param startURI URI from which the download begin
	 * @param destDirectory directory where the datas will be stored
	 * @param regExp the filter of the linked WebFiles
	 */
	private DownloadManager(int maxDLHtml ,int maxDLLink, int maxDepth, URI startURI, String destDirectory, String regExp) {
		htmlFileToDownload = new ArrayList<WebHtmlFile>();
		linkedFileToDownload = new ArrayList<WebLinkedFile>();
		htmlFileInDownloading = new ArrayList<WebHtmlFile>();
		linkedFileInDownloading = new ArrayList<WebLinkedFile>();
		discoveredURI = new ArrayList<String>();
		this.maxDLHtml = maxDLHtml;
		this.maxDLLink = maxDLLink;
		currentDLHtml = 0;
		currentDLLink = 0;
		this.maxDepth = maxDepth;
		this.destDirectory = destDirectory;
		this.regExp = regExp;
		threadList = new ArrayList<DownloadAndParseFile>();
		defaultHost = Utils.getCompleteHost(startURI);
		
		//raz the current jHoover project
		ActionManager.initAction();
		
		detailledModel = DetailledModel.getInstance();
		
		//creates the JTree in the discovery part
		createJTree();
		//creates the JTable "ALL" in the detailled part
		GuiUtils.createNewTable(detailledModel, Labels.ALL_LABEL, Labels.ALL_REGEXP_LABEL);
		//creates the JTable "HTML" in the detailled part
		GuiUtils.createNewTable(detailledModel, Labels.HTML_LABEL, Labels.HTML_REGEXP_LABEL);
		//creates the JTable "FILES" in the detailled part
		GuiUtils.createNewTable(detailledModel, Labels.FILES_LABEL, Labels.FILES_REGEXP_LABEL);
	}
	
	
	/**
	 * Create a DownloadManager and return it
	 * @param maxDLHtml number of maximum simultaneous html download file
	 * @param maxDLLink number of maximum simultaneous linked download file
	 * @param maxDepth depth of the download in the WebSite
	 * @param startURI URI from which the download begin
	 * @param destDirectory directory where the datas will be stored
	 * @param regExp the filter of the linked WebFiles
	 * @return an instance of DownloadManager
	 */
	public static DownloadManager createInstance(int maxDLHtml ,int maxDLLink, int maxDepth, URI startURI, String destDirectory, String regExp) {
		if (INSTANCE == null) {
			INSTANCE = new DownloadManager(maxDLHtml ,maxDLLink, maxDepth, startURI, destDirectory, regExp);
		}
		return INSTANCE;
	}
	
	
	/**
	 * Return the instance of DownloadManager 
	 * @return the instance
	 */
	public static DownloadManager getInstance() {
		return INSTANCE;
	}
	
	
	/**
	 * Thread to download files
	 * Take all the webLinkedFile contained in the htmlFileToDownload list and download them  
	 */
	public void run() {			
		testIfPaused();
		DownloadAndParseFile downloadFile;
		boolean isInterrupted = false;
		while(!Thread.interrupted() && (htmlFileToDownload.isEmpty() == false || linkedFileToDownload.isEmpty() == false || htmlFileInDownloading.isEmpty() == false || linkedFileInDownloading.isEmpty() == false)) {
			if (currentDLHtml < maxDLHtml && htmlFileToDownload.isEmpty() == false) {
				WebHtmlFile webHtmlFile;
				webHtmlFile = htmlFileToDownload.get(0);
				this.htmlFileToDownload.remove(0);
				if (webHtmlFile.getProgression() != -1) {  //was not cancelled
					htmlFileInDownloading.add(webHtmlFile);
					//start a new download of Html File in a new Thread
					downloadFile = new DownloadAndParseFile(this, webHtmlFile, defaultHost, destDirectory);
					downloadFile.start();
					threadList.add(downloadFile);
					currentDLHtml++;
				}
			}
			testIfPaused();
			if (currentDLLink < maxDLLink && linkedFileToDownload.isEmpty() == false) {
				WebLinkedFile webLinkedFile;
				webLinkedFile = linkedFileToDownload.get(0);
				this.linkedFileToDownload.remove(0);
				if (webLinkedFile.getProgression() != -1) {  //was not cancelled
					linkedFileInDownloading.add(webLinkedFile);
					//start a new download of Linked File in a new Thread
					downloadFile = new DownloadAndParseFile(this, webLinkedFile, defaultHost, destDirectory);
					downloadFile.start();
					threadList.add(downloadFile);
					currentDLLink++;
				}
			}
			try {
				Thread.sleep(HtmlConstants.TIME_BETWEEN_EACH_DOWNLOAD);
			} catch (InterruptedException e) {
				interrupt();
				isInterrupted = true;
			}
		}
		if (!isInterrupted) {
			//End of Download (successfull)
			JOptionPane.showMessageDialog(JHMainFrame.getInstance(), Labels.DOWNLOAD_FINISHED_LABEL, Labels.DOWNLOAD_STATUS_LABEL, JOptionPane.INFORMATION_MESSAGE);	
		}
		//free the DownloadManager
		freeDownloadManager();
	}
	
	
	/**
	 * Free the DownloadManager (free memory)
	 */
	private void freeDownloadManager() {
		threadList = new ArrayList<DownloadAndParseFile>();
		htmlFileToDownload = null;
		linkedFileToDownload = null;
		htmlFileInDownloading = null;
		linkedFileInDownloading = null;
		discoveredURI = null;
		maxDLHtml = 0;
		maxDLLink = 0;
		currentDLHtml = 0;
		currentDLLink = 0;
		maxDepth = 0;
		destDirectory = null;
		INSTANCE = null;
		pauseThread = false;
		System.gc();
	}


	/**
	 * Add a webHtmlFile in the queue if it hasn't been already downloaded and if it respect the good host or protocol
	 * @param uri the URI to add
	 * @param depth the depth of the URI
	 */
	public void addHtmlFile(URI uri, int depth) {
		//creates the webHtmlFile according to the URI 
		if (!isUriAlreadyDownloaded(uri)) {
			//verification if the depth is good
			if (depth <= maxDepth) {
				discoveredURI.add(Utils.getCompletePath(uri));
				WebHtmlFile webHtmlFile = new WebHtmlFile(uri, depth);
				String webFileHost = Utils.getCompleteHost(webHtmlFile.getURI());
				
				if (webFileHost.equals(defaultHost)) {
					htmlFileToDownload.add(webHtmlFile);
					//add the webHtmlFile in the treeModel
					DiscoveryTreeNode node = treeRoot.add(webHtmlFile);				
					treeModel.nodesWereInserted(treeRoot, new int[] {treeRoot.getIndex(node)});
					if (tree.isRootVisible() && !tree.isExpanded(0)) {
						tree.expandRow(0);
						tree.setRootVisible(false);
					}
					//add the webHtmlFile in the detailledModel
					detailledModel.addElement(webHtmlFile);
				} else {
					//it is not the same web site or the same protocol
					System.err.println(webHtmlFile.getURI() + " / " + defaultHost + ": NOT THE SAME HOST OR THE SAME PROTOCOL");
				}
			}
		}
	}
	
	
	/**
	 * Add a webLinkedFile in the queue if it hasn't been already downloaded and if it respect the good host or protocol
	 * @param uri the URI to add
	 * @param depth the depth of the URI
	 * @param parent the webHtmlFile parent
	 */
	public void addLinkedFile(URI uri, int depth, WebFile parent) {
		//creates the webLinkedFile according to the URI 
		if (!isUriAlreadyDownloaded(uri)) {
			//verification if the depth is good
			if (depth <= maxDepth) {
				discoveredURI.add(Utils.getCompletePath(uri));
				WebLinkedFile webLinkedFile = new WebLinkedFile(uri, depth, parent);
				String webFileHost = Utils.getCompleteHost(webLinkedFile.getURI());
				
				//filter the linked files with the Filter
				RE r = new RE(regExp);
				if (r.match(webLinkedFile.getFileName())) {
					if (webFileHost.equals(defaultHost)) {
						linkedFileToDownload.add(webLinkedFile);
						//add the webHtmlFile in the treeModel
						DiscoveryTreeNode parentNode = (DiscoveryTreeNode) treeRoot.getChild(parent);
						DiscoveryTreeNode node = parentNode.add(webLinkedFile);
						treeModel.nodesWereInserted(parentNode, new int[] {parentNode.getIndex(node)});
						//add the webHtmlFile in the detailledModel
						detailledModel.addElement(webLinkedFile);
					} else {
						//it is not the same web site or the same protocol
						System.err.println(webLinkedFile.getURI() + " / " + defaultHost + ": NOT THE SAME HOST OR THE SAME PROTOCOL");
					}
				}
			}
		}
	}
	
	
	/**
	 * Return if the URI has already been downloaded 
	 * @param uri the URI to test
	 * @return true if the file has already been downloaded or if we should not try to download it, else false
	 */
	private boolean isUriAlreadyDownloaded(URI uri) {
		if (INSTANCE != null) {
			String discoveredURIString;
			for (int i=0; i<discoveredURI.size(); i++) {
				discoveredURIString = discoveredURI.get(i);
				String uriPathToCompare = Utils.getCompletePath(uri);
				if (discoveredURIString.compareTo(uriPathToCompare) == 0){
					return true;
				}
			}
			return false;
		}
		return true;
	}
	
	
	/**
	 * Permiss to organize the download threads
	 * @param webFile the webFile which is finished to download
	 */
	public void endDownload(WebFile webFile) {
		if (webFile instanceof WebHtmlFile) {
			if (INSTANCE != null) {
				this.currentDLHtml--;
				this.htmlFileInDownloading.remove(webFile);
			}
		} else if (webFile instanceof WebLinkedFile){
			if (INSTANCE != null) {
				this.currentDLLink--;
				this.linkedFileInDownloading.remove(webFile);
			}
		}
	}
	
	
	/**
	 * Creates the JTree in the discovery part
	 */
	private void createJTree() {
		treeRoot = new DiscoveryTreeNode(null, null);
		treeModel = new DefaultTreeModel(treeRoot);
		tree = new JTree(treeModel);
		tree.setCellRenderer(new DiscoveryRenderer());
		tree.addTreeSelectionListener(new TreeSelectionListener() {
			public void valueChanged(TreeSelectionEvent arg0) {
				//TODO: faire l'action pour aller selectionner la ligne dans la JTable
				System.out.println("JTREE ACTION: [TODO] Selectin the JTable");
			}
		});
		JPanel discoveryScrollablePanel = JHMainPanel.getInstance().getDiscoveryPanel().getScrollablePanel();
		discoveryScrollablePanel.setBackground(new Color(255,255,255));
		discoveryScrollablePanel.add(tree, BorderLayout.WEST);
		tree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
		ToolTipManager.sharedInstance().registerComponent(tree);
	}
	
	
	/**
	 * Returns the list of download Threads
	 * @return the list of Threads
	 */
	public ArrayList<DownloadAndParseFile> getThreadList() {
		return threadList;
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
	 * Pause or Resume the Thread
	 * @param pause true to pause the thread or false to resume it
	 */
	public void setPauseStatus(boolean pause) {
		this.pauseThread = pause;
	}
	
	
	/**
	 * Status of the Thread
	 * @return true if the Thread is paused, false else
	 */
	public boolean isPaused() {
		return pauseThread;
	}
	
	
	/**
	 * Tests if the DownloadManager is running
	 * @return true if the downloadManager is running, false else
	 */
	public static boolean isRunning() {
		if (INSTANCE == null) {
			return false;
		}
		return true;
	}
}
