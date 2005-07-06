/**
 * jHoover - UMLV IR2
 * UI Project
 */
package fr.umlv.ir2.jhoover.network;

import java.net.URI;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTable;
import javax.swing.JTree;
import javax.swing.tree.DefaultTreeModel;

import fr.umlv.ir2.jhoover.gui.DetailledJButtonRenderer;
import fr.umlv.ir2.jhoover.gui.DetailledJProgressBarRenderer;
import fr.umlv.ir2.jhoover.gui.DetailledModel;
import fr.umlv.ir2.jhoover.gui.DiscoveryRenderer;
import fr.umlv.ir2.jhoover.gui.DiscoveryTreeNode;
import fr.umlv.ir2.jhoover.gui.panel.JHDetailledPanel;
import fr.umlv.ir2.jhoover.gui.panel.JHMainPanel;

/**
 * @author Romain Papuchon
 *
 * Manage the download
 */
public class DownloadManager implements Runnable {
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
	
	//for the graphic
	private DiscoveryTreeNode treeRoot;
	private DefaultTreeModel treeModel;
	private DetailledModel detailledModel;
	private JTable allTable;
	
	
	/*
	 * Creates a DownloadManager
	 */
	public DownloadManager(int maxDLHtml ,int maxDLLink, int maxDepth, URI startURI, String destDirectory) {
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

		
		//for the JTree
		this.treeRoot = new DiscoveryTreeNode(null, null);
		this.treeModel = new DefaultTreeModel(this.treeRoot);
		//TODO: regler les parametres pour que le jTree soit beau
		DiscoveryRenderer treeRenderer = new DiscoveryRenderer();
		JTree tree = new JTree(this.treeModel);
		tree.setCellRenderer(treeRenderer);
		JHMainPanel.getDiscoveryPanel().getScrollablePanel().add(tree);
		
		
		//for the JTable
		this.detailledModel = new DetailledModel();		
		this.allTable = new JTable(this.detailledModel);
		DetailledJProgressBarRenderer jProgressBarRenderer = new DetailledJProgressBarRenderer();
		DetailledJButtonRenderer jButtonRenderer = new DetailledJButtonRenderer();
		this.allTable.setDefaultRenderer(JButton.class, jButtonRenderer);
//		this.allTable.setDefaultEditor(JButton.class, new DetailledJButtonEditor());
		this.allTable.setDefaultRenderer(JProgressBar.class, jProgressBarRenderer);
		JHDetailledPanel.getInstance().addTabPanel("ALL", this.allTable);
		//TODO: voir pour HTML
		JHDetailledPanel.getInstance().addTabPanel("HTML", new JPanel());

	}


	/*
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
		
		while(this.htmlFileToDownload.isEmpty() == false || this.linkedFileToDownload.isEmpty() == false || this.htmlFileInDownloading.isEmpty() == false || this.linkedFileInDownloading.isEmpty() == false) {
//			System.out.println("...NEW...");
//			System.out.println("currentDLHtml: " + currentDLHtml);
//			System.out.println("currentDLLink: " + currentDLLink);
			
			if (this.currentDLHtml < this.maxDLHtml && this.htmlFileToDownload.isEmpty() == false) {
				WebHtmlFile webHtmlFile;
				webHtmlFile = this.htmlFileToDownload.get(0);
				this.htmlFileToDownload.remove(0);
				this.htmlFileInDownloading.add(webHtmlFile);
				//start a new download of Html File in a new Thread
				downloadFile = new DownloadAndParseFile(this, webHtmlFile, defaultHost, this.destDirectory, detailledModel);
				Thread htmlThread = new Thread(downloadFile);
				htmlThread.start();
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
				this.currentDLLink++;
			}
			
			//TODO: tmp, a voir
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
//		System.out.println("...FIN...");
//		System.out.println("currentDLHtml: " + currentDLHtml);
//		System.out.println("currentDLLink: " + currentDLLink);
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
	
//	public void removeHtmlFileFromInDownloading(WebHtmlFile webHtmlFile) {
//		this.htmlFileInDownloading.remove(webHtmlFile);
//	}
//	
//	public void removeLinkedWebFileFromInDownloading(WebLinkedFile webLinkedFile) {
//		this.htmlFileInDownloading.remove(webLinkedFile);
//	}
	
//	public void endDownloadHtml(WebHtmlFile webHtmlFile) {
//		this.currentDLHtml--;
//		this.htmlFileInDownloading.remove(webHtmlFile);
////		this.htmlFileDownloaded.add(webHtmlFile);
//	}
//	
//	public void endDownloadLink(WebLinkedFile webLinkedFile) {
//		this.currentDLLink--;
//		this.linkedFileInDownloading.remove(webLinkedFile);
////		this.linkedFileDownloaded.add(webLinkedFile);
//	}
	
	
	public void endDownload(WebFile webFile) {
		if (webFile instanceof WebHtmlFile) {
			this.currentDLHtml--;
			this.htmlFileInDownloading.remove(webFile);
//			this.htmlFileDownloaded.add(webHtmlFile);
		} else if (webFile instanceof WebLinkedFile){
			this.currentDLLink--;
			this.linkedFileInDownloading.remove(webFile);
//			this.linkedFileDownloaded.add(webLinkedFile);
		} else {
			//TODO: voir si c'est bon ici
			System.err.println("ERROR in function DownloadManager=>endDownload()");
		}
	}
	
	

//	/*
//	 * Tests if the webFile is a linked File or not
//	 */
//	public boolean isALinkedFile(WebFile webFile) {
//		if (this.linkedFileInDownloading.contains(webFile)) {
//			return true;
//		}
//		return false;
//	}
}
