/**
 * jHoover - UMLV IR2
 * UI Project
 */
package fr.umlv.ir2.jhoover.network;

import java.net.URI;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeSelectionModel;

import fr.umlv.ir2.jhoover.gui.JHMainFrame;
import fr.umlv.ir2.jhoover.gui.detailled.DetailledModel;
import fr.umlv.ir2.jhoover.gui.discovery.DiscoveryRenderer;
import fr.umlv.ir2.jhoover.gui.discovery.DiscoveryTreeNode;
import fr.umlv.ir2.jhoover.gui.panel.JHMainPanel;
import fr.umlv.ir2.jhoover.gui.tool.Labels;
import fr.umlv.ir2.jhoover.gui.tool.Utils;
import fr.umlv.ir2.jhoover.network.util.HtmlConstants;

/**
 * @author Romain Papuchon
 *
 * Manage the download
 */
public class DownloadManager implements Runnable {	
	private ArrayList<Thread> threadList = new ArrayList<Thread>();
	private ArrayList<WebHtmlFile> htmlFileToDownload;
	private ArrayList<WebLinkedFile> linkedFileToDownload;
//	private ArrayList<WebHtmlFile> htmlFileDownloaded;
//	private ArrayList<WebLinkedFile> linkedFileDownloaded;
	private ArrayList<WebHtmlFile> htmlFileInDownloading;
	private ArrayList<WebLinkedFile> linkedFileInDownloading;
	private ArrayList<String> discoveredURI; 
	private int maxDLHtml;
	private int maxDLLink;
	private int currentDLHtml;
	private int currentDLLink;
	private int maxDepth;
	private URI startURI;
	private String destDirectory;
	
	private static DownloadManager INSTANCE = null;
	
	//for the graphic
	private JTree tree;
	private DiscoveryTreeNode treeRoot;
	private DefaultTreeModel treeModel;
	private DetailledModel detailledModel;
	
	
	/*
	 * Creates a DownloadManager
	 */
	private DownloadManager(int maxDLHtml ,int maxDLLink, int maxDepth, URI startURI, String destDirectory) {
		this.htmlFileToDownload = new ArrayList<WebHtmlFile>();
		this.linkedFileToDownload = new ArrayList<WebLinkedFile>();
		this.htmlFileInDownloading = new ArrayList<WebHtmlFile>();
		this.linkedFileInDownloading = new ArrayList<WebLinkedFile>();
//		this.htmlFileDownloaded = new ArrayList<WebHtmlFile>();
//		this.linkedFileDownloaded = new ArrayList<WebLinkedFile>();
		this.discoveredURI = new ArrayList<String>();
		this.maxDLHtml = maxDLHtml;
		this.maxDLLink = maxDLLink;
		this.currentDLHtml = 0;
		this.currentDLLink = 0;
		this.maxDepth = maxDepth;
		this.startURI = startURI;
		this.destDirectory = destDirectory;

		//init the discoveryPanel: remove the JTree
		JHMainPanel.initDiscoveryPanel();
		
		//init the detailled model: init the model and fire
		if (!DetailledModel.getInstance().getWebFiles().isEmpty()) {
			DetailledModel.getInstance().initModel();
		}
		this.detailledModel = DetailledModel.getInstance();
		
		
		//creates the JTree in the discovery part
		createJTree();
		//creates the JTable "ALL" in the detailled part
		Utils.createNewTable(detailledModel, "ALL", Labels.ALL_REGEXP_LABEL);
		//creates the JTable "HTML" in the detailled part
		Utils.createNewTable(detailledModel, "HTML", Labels.HTML_REGEXP_LABEL);
		//creates the JTable "FILES" in the detailled part
		Utils.createNewTable(detailledModel, "FILES", Labels.FILES_REGEXP_LABEL);
	}
	
	
	public static DownloadManager getInstance(int maxDLHtml ,int maxDLLink, int maxDepth, URI startURI, String destDirectory) {
		if (INSTANCE == null) {
			INSTANCE = new DownloadManager(maxDLHtml ,maxDLLink, maxDepth, startURI, destDirectory);
		}
		return INSTANCE;
	}
	
	
	/**
	 * Creates the JTree in the discovery part
	 */
	private void createJTree() {
		//TODO: regler les parametres pour que le jTree soit beau
		this.treeRoot = new DiscoveryTreeNode(null, null);
		this.treeModel = new DefaultTreeModel(this.treeRoot);
		this.tree = new JTree(this.treeModel);
		this.tree.setCellRenderer(new DiscoveryRenderer());
		this.tree.addTreeSelectionListener(new TreeSelectionListener() {
			public void valueChanged(TreeSelectionEvent arg0) {
				//TODO: faire l'action pour aller selectionner la ligne dans la JTable
				System.out.println("JTREE ACTION: [TODO] Selectionner dans la JTable");
			}
		});
		JHMainPanel.getInstance().getDiscoveryPanel().getScrollablePanel().add(this.tree);
		this.tree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
//		this.tree.setRootVisible(false);
//		this.tree.setShowsRootHandles(true);
//		this.tree.setSelectionRow(0);
//		this.tree.setExpandsSelectedPaths(true);
//		this.tree.setRootVisible(false);
//		this.tree.expandRow(0);
//		this.tree.setEnabled(true);
//		this.tree.setShowsRootHandles(true);
	}

	
	
	/**
	 * Thread to download files
	 * 
	 * Take all the webLinkedFile contained in the htmlFileToDownload list and download them  
	 */
	public void run() {
		String defaultHost;		
	
		if (this.startURI.getPort() > 0) {
			defaultHost = this.startURI.getScheme() + HtmlConstants.SCHEME_AND_AUTHORITY_SEPARATOR + this.startURI.getHost() + ":" + this.startURI.getPort();
		} else {
			defaultHost = this.startURI.getScheme() + HtmlConstants.SCHEME_AND_AUTHORITY_SEPARATOR + this.startURI.getHost();
		}
		
		DownloadAndParseFile downloadFile;
		boolean isInterrupted = false;
		
		while(!Thread.interrupted() && (this.htmlFileToDownload.isEmpty() == false || this.linkedFileToDownload.isEmpty() == false || this.htmlFileInDownloading.isEmpty() == false || this.linkedFileInDownloading.isEmpty() == false)) {
			
			if (this.currentDLHtml < this.maxDLHtml && this.htmlFileToDownload.isEmpty() == false) {
				WebHtmlFile webHtmlFile;
				webHtmlFile = this.htmlFileToDownload.get(0);
				this.htmlFileToDownload.remove(0);
				this.htmlFileInDownloading.add(webHtmlFile);
				//start a new download of Html File in a new Thread
				downloadFile = new DownloadAndParseFile(this, webHtmlFile, defaultHost, this.destDirectory, detailledModel);
				Thread htmlThread = new Thread(downloadFile);
				htmlThread.start();
				threadList.add(htmlThread);
				this.currentDLHtml++;
			}
			
			if (this.currentDLLink < this.maxDLLink && this.linkedFileToDownload.isEmpty() == false) {
				WebLinkedFile webLinkedFile;
				webLinkedFile = this.linkedFileToDownload.get(0);
				this.linkedFileToDownload.remove(0);
				this.linkedFileInDownloading.add(webLinkedFile);
				//start a new download of Linked File in a new Thread
				downloadFile = new DownloadAndParseFile(this, webLinkedFile, defaultHost, this.destDirectory, detailledModel);
				Thread linkThread = new Thread(downloadFile);
				linkThread.start();
				threadList.add(linkThread);
				this.currentDLLink++;
			}
			
			//TODO: tmp, a voir
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
				isInterrupted = true;
			}
		}
		
		//TODO: mettre en rouge tous les downloads dans la JTable
		if (isInterrupted) {
			//Download was cancelled
			JOptionPane.showMessageDialog(JHMainFrame.getInstance(), Labels.DOWNLOAD_CANCELLED_LABEL, Labels.DOWNLOAD_STATUS_LABEL, JOptionPane.ERROR_MESSAGE);
		} else {	
			//End of Download
			JOptionPane.showMessageDialog(JHMainFrame.getInstance(), Labels.DOWNLOAD_FINISHED_LABEL, Labels.DOWNLOAD_STATUS_LABEL, JOptionPane.INFORMATION_MESSAGE);	
		}
	}
	
	
	/*
	 * Add a webHtmlFile in the queue if it hasn't been already downloaded 
	 */
	public void addHtmlFile(URI uri, int depth) {
		//creates the webHtmlFile according to the URI 
		if (!isUriAlreadyDownloaded(uri)) {
			//verification if the depth is good
			if (depth <= this.maxDepth) {
				if (uri.getPort() > 0) {
					this.discoveredURI.add(uri.getScheme() + HtmlConstants.SCHEME_AND_AUTHORITY_SEPARATOR + uri.getHost() + ":" + uri.getPort() + uri.getPath());
				} else {
					this.discoveredURI.add(uri.getScheme() + HtmlConstants.SCHEME_AND_AUTHORITY_SEPARATOR + uri.getHost() + uri.getPath());
				}
				WebHtmlFile webHtmlFile = new WebHtmlFile(uri, depth);
				this.htmlFileToDownload.add(webHtmlFile);
				
				//add the webHtmlFile in the treeModel
				DiscoveryTreeNode node = this.treeRoot.add(webHtmlFile);
				this.treeModel.nodesWereInserted(this.treeRoot, new int[] {this.treeRoot.getIndex(node)});
				
				//add the webHtmlFile in the detailledModel
				this.detailledModel.addElement(webHtmlFile);
			}
		}
	}
	
	
	/*
	 * Add a webLinkedFile in the queue if it hasn't been already downloaded 
	 */
	public void addLinkedFile(URI uri, int depth, WebFile parent) {
		//creates the webLinkedFile according to the URI 
		if (!isUriAlreadyDownloaded(uri)) {
			//verification if the depth is good
			if (depth <= this.maxDepth) {
				if (uri.getPort() > 0) {
					this.discoveredURI.add(uri.getScheme() + HtmlConstants.SCHEME_AND_AUTHORITY_SEPARATOR + uri.getHost() + ":" + uri.getPort() + uri.getPath());
				} else {
					this.discoveredURI.add(uri.getScheme() + HtmlConstants.SCHEME_AND_AUTHORITY_SEPARATOR + uri.getHost() + uri.getPath());
				}
				WebLinkedFile webLinkedFile = new WebLinkedFile(uri, depth, parent);
				this.linkedFileToDownload.add(webLinkedFile);
				
				//add the webHtmlFile in the treeModel
				DiscoveryTreeNode parentNode = (DiscoveryTreeNode) this.treeRoot.getChild(parent);
				DiscoveryTreeNode node = parentNode.add(webLinkedFile);
				this.treeModel.nodesWereInserted(parentNode, new int[] {parentNode.getIndex(node)});
				
				//add the webHtmlFile in the detailledModel
				this.detailledModel.addElement(webLinkedFile);
			}
		}
	}
	
	
	/*
	 * Return true if the file has already been downloaded or if we shoutd not try to download it, else false
	 */
	public boolean isUriAlreadyDownloaded(URI uri) {
		String discoveredURIString;
		for (int i=0; i<this.discoveredURI.size(); i++) {
			discoveredURIString = this.discoveredURI.get(i);
			if (uri.getPort() > 0) {
				if (discoveredURIString.compareTo(uri.getScheme() + HtmlConstants.SCHEME_AND_AUTHORITY_SEPARATOR + uri.getHost() + ":" + uri.getPort() + uri.getPath()) == 0){
					return true;
				}
			} else {
				if (discoveredURIString.compareTo(uri.getScheme() + HtmlConstants.SCHEME_AND_AUTHORITY_SEPARATOR + uri.getHost() + uri.getPath()) == 0){
					return true;
				}
			}
		}
		return false;
	}
	
	
	/*
	 * Permiss to organize the download threads
	 */
	public void endDownload(WebFile webFile) {
		if (webFile instanceof WebHtmlFile) {
			this.currentDLHtml--;
			this.htmlFileInDownloading.remove(webFile);
//			this.htmlFileDownloaded.add(webHtmlFile);
		} else if (webFile instanceof WebLinkedFile){
			this.currentDLLink--;
			this.linkedFileInDownloading.remove(webFile);
//			this.linkedFileDownloaded.add(webLinkedFile);
		}
	}
	
	
	public ArrayList<Thread> getThreadList() {
		return threadList;
	}
}
