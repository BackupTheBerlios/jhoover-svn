package fr.umlv.ir2.jhoover.gui;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;

import javax.swing.tree.TreeNode;

import fr.umlv.ir2.jhoover.network.WebFile;

public class DiscoveryTreeNode implements TreeNode {
	
	private List<DiscoveryTreeNode> childrens = new ArrayList<DiscoveryTreeNode>();
	private DiscoveryTreeNode parent;
	private WebFile webFile;  //data
	
	
	public DiscoveryTreeNode(DiscoveryTreeNode parent, WebFile webFile) {
		super();
		this.parent = parent;
		this.webFile = webFile;
	}
		
	public TreeNode getChildAt(int index) {
		return this.childrens.get(index);
	}

	public int getChildCount() {
		return this.childrens.size();
	}

	public TreeNode getParent() {
		return this.parent;
	}

	public int getIndex(TreeNode node) {
		return this.childrens.indexOf(node);
	}

	public boolean getAllowsChildren() {
		// TODO voir cette methode
		return false;
	}

	public boolean isLeaf() {
		if (this.childrens.isEmpty()) {
			return true;
		}
		return false;
	}

	public Enumeration children() {
		return Collections.enumeration(this.childrens);
	}
	
	public WebFile getWebFile() {
		return this.webFile;
	}
	
	public DiscoveryTreeNode add(WebFile webFile) {
		if (webFile != null) {
			DiscoveryTreeNode child = new DiscoveryTreeNode(this, webFile);
			this.childrens.add(child);
			return child;
		}
		throw new IllegalArgumentException("Can't add this webFile : " + webFile);
	}
	
	public TreeNode getChild(WebFile webFile) {
		for (int i=0;i<this.childrens.size();i++) {
			if (this.childrens.get(i).getWebFile().equals(webFile)) {
				return this.childrens.get(i);
			}
		}
		return null;
	}
	
	public String toString() {
		if (this.webFile != null) {
			return this.webFile.getPath();
		}
		return "";
	}
}
