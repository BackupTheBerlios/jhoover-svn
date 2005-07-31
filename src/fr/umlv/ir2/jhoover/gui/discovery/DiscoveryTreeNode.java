/**
 * jHoover - UMLV IR2
 * UI Project
 */
package fr.umlv.ir2.jhoover.gui.discovery;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;

import javax.swing.tree.TreeNode;

import fr.umlv.ir2.jhoover.network.WebFile;
import fr.umlv.ir2.jhoover.network.util.Utils;

/** 
 * Node for the JTree
 * @author Romain Papuchon 
 */
public class DiscoveryTreeNode implements TreeNode {
	
	private List<DiscoveryTreeNode> childrens = new ArrayList<DiscoveryTreeNode>();
	private DiscoveryTreeNode parent;
	private WebFile webFile;  //data
	
	
	/**
	 * Create a DiscoveryTreeNode
	 * @param parent parent node
	 * @param webFile the webFile to add in the node (data)
	 */
	public DiscoveryTreeNode(DiscoveryTreeNode parent, WebFile webFile) {
		super();
		this.parent = parent;
		this.webFile = webFile;
	}
		
	/**
	 * @see javax.swing.tree.TreeNode#getChildAt(int)
	 */
	public TreeNode getChildAt(int index) {
		return this.childrens.get(index);
	}

	/**
	 * @see javax.swing.tree.TreeNode#getChildCount()
	 */
	public int getChildCount() {
		return this.childrens.size();
	}

	/**
	 * @see javax.swing.tree.TreeNode#getParent()
	 */
	public TreeNode getParent() {
		return this.parent;
	}

	/**
	 * @see javax.swing.tree.TreeNode#getIndex(javax.swing.tree.TreeNode)
	 */
	public int getIndex(TreeNode node) {
		return this.childrens.indexOf(node);
	}

	/**
	 * @see javax.swing.tree.TreeNode#getAllowsChildren()
	 */
	public boolean getAllowsChildren() {
		return false;
	}

	/**
	 * @see javax.swing.tree.TreeNode#isLeaf()
	 */
	public boolean isLeaf() {
		if (this.childrens.isEmpty()) {
			return true;
		}
		return false;
	}

	/**
	 * @see javax.swing.tree.TreeNode#children()
	 */
	public Enumeration children() {
		return Collections.enumeration(this.childrens);
	}
	
	/**
	 * Return the webFile of the node
	 * @return the webFile
	 */
	public WebFile getWebFile() {
		return webFile;
	}
	
	/**
	 * Add the webFile in the a new node an return it 
	 * @param webFile the webFile to add
	 * @return the node created
	 */
	public DiscoveryTreeNode add(WebFile webFile) {
		if (webFile != null) {
			DiscoveryTreeNode child = new DiscoveryTreeNode(this, webFile);
			this.childrens.add(child);
			return child;
		}
		throw new IllegalArgumentException("Can't add this webFile : " + webFile);
	}
	
	/**
	 * Return the node corresponding to the webFile
	 * @param webFile the webFile to get the node from
	 * @return the node
	 */
	public TreeNode getChild(WebFile webFile) {
		for (int i=0;i<this.childrens.size();i++) {
			if (this.childrens.get(i).getWebFile().equals(webFile)) {
				return this.childrens.get(i);
			}
		}
		return null;
	}
	
	
	/**
	 * Return only the fileName of the webFile
	 * @return the filename
	 */
	public String toString() {
		if (this.webFile != null) {
			return this.webFile.getFileName();
		}
		return "";
	}
	
	
	/**
	 * Return the complete path to the toolTipText of the selected webFile on the tree
	 * @return the complete path
	 */
	public String getToolTipText() {
		return Utils.getCompletePath(webFile.getURI());		
	}
}
