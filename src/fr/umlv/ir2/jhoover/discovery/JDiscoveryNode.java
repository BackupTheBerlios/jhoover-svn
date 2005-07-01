package fr.umlv.ir2.jhoover.discovery;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;

import javax.swing.tree.TreeNode;

import fr.umlv.ir2.jhoover.network.WebFile;

public class JDiscoveryNode implements TreeNode {
	
	private List<JDiscoveryNode> childrens = new ArrayList<JDiscoveryNode>();
	private JDiscoveryNode parent;
	private WebFile webFile;  //data
	
	
	public JDiscoveryNode(JDiscoveryNode parent, WebFile webFile) {
		super();
		this.parent = parent;
		this.webFile = webFile;
	}
		
	public TreeNode getChildAt(int index) {
		return childrens.get(index);
	}

	public int getChildCount() {
		return childrens.size();
	}

	public TreeNode getParent() {
		return parent;
	}

	public int getIndex(TreeNode node) {
		return childrens.indexOf(node);
	}

	public boolean getAllowsChildren() {
		// TODO voir cette methode
		return false;
	}

	public boolean isLeaf() {
		if (childrens.isEmpty()) {
			return true;
		}
		return false;
	}

	public Enumeration children() {
		// TODO voir cette methode
		return Collections.enumeration(childrens);
	}
	
	public WebFile getWebFile() {
		return webFile;
	}
	
	public JDiscoveryNode add(WebFile webFile) {
		if (webFile != null) {
			JDiscoveryNode child = new JDiscoveryNode(this, webFile);
			childrens.add(child);
			return child;
		}
		throw new IllegalArgumentException("Can't add this webFile : " + webFile);
	}
	
	public String toString() {
		if (webFile != null) {
			return webFile.getPath();
		}
		return "";
	}
}
