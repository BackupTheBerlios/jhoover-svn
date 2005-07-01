package fr.umlv.ir2.jhoover.discovery;

import java.net.URI;
import java.net.URISyntaxException;

import javax.swing.JFrame;
import javax.swing.JTree;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeNode;

import fr.umlv.ir2.jhoover.network.WebFile;

public class LaunchJTree {
	public static void main(String[] args) {
		JDiscoveryNode root = new JDiscoveryNode(null, null);
		DefaultTreeModel model = new DefaultTreeModel(root);
		
		
		
		JTree tree = new JTree(model);
//		tree.setRootVisible(false);
//		tree.setShowsRootHandles(true);
//		tree.setExpandsSelectedPaths(true);
		JFrame frame = new JFrame("test");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(tree);
		frame.pack();
		frame.setVisible(true);
		
		
		try {
			JDiscoveryNode htlm1 = root.add(new WebFile(new URI("http://www.google.fr/bonjour.html"), 0));
			JDiscoveryNode htlm2 = root.add(new WebFile(new URI("http://www.google.fr/coucou.html"), 0));
			JDiscoveryNode htlm3 = root.add(new WebFile(new URI("http://www.google.fr/lol.html"), 0));
			JDiscoveryNode htlm4 = root.add(new WebFile(new URI("http://www.google.fr/fumier.html"), 0));
			JDiscoveryNode htlm5 = root.add(new WebFile(new URI("http://www.google.fr/bestiau.html"), 0));
			JDiscoveryNode linked1 = htlm1.add(new WebFile(new URI("http://www.google.fr/image1.html"), 0));
			JDiscoveryNode linked2 = htlm1.add(new WebFile(new URI("http://www.google.fr/image2.html"), 0));
			JDiscoveryNode linked3 = htlm3.add(new WebFile(new URI("http://www.google.fr/image3.html"), 0));
			JDiscoveryNode linked4 = htlm4.add(new WebFile(new URI("http://www.google.fr/image4.html"), 0));
			
			
			model.nodesWereInserted((TreeNode)root, new int[] {root.getIndex((TreeNode)htlm1)});
			model.nodesWereInserted((TreeNode)root, new int[] {root.getIndex((TreeNode)htlm2)});
//			model.nodeStructureChanged(root);  //recharge tout d'un coup
			
			
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		

		
		
		
		
		
	}
}
