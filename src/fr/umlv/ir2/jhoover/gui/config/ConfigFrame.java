/**
 * jHoover - UMLV IR2
 * UI Project
 */
package fr.umlv.ir2.jhoover.gui.config;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;

/**
 * @author Romain Papuchon
 *
 */
public class ConfigFrame extends JFrame{

	public ConfigFrame(String title) {
		super(title);
		setdesign();
		addFields();
	}

	private void setdesign() {
		setSize(new Dimension(800, 600));
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	private void addFields() {
		//TODO: revoir la position des objets
		GridBagLayout gridBagLayout = new GridBagLayout();
		Container container = getContentPane();
		container.setLayout(gridBagLayout);	
		GridBagConstraints constraints = new GridBagConstraints();

		
		/*
		 * Logo
		 */
		//TODO: faire marcher ce logo
//		Icon logo = new ImageIcon("fr/umlv/ir2/jhoover/gui/ressources/jHoover.bmp", "jHoover");
//		JLabel jLogo = new JLabel("Image and Text", logo, SwingConstants.CENTER);
//		container.add(jLogo);
		
		
		/*
		 * Left Column
		 */
		constraints.anchor = GridBagConstraints.EAST;
		constraints.insets = new Insets(5,15,5,15);
		constraints.gridx = 0;
		constraints.gridy = 0;
		
		JLabel lUrl = new JLabel("URL:");
		gridBagLayout.setConstraints(lUrl, constraints);
		container.add(lUrl);
		
		constraints.gridy++;
		JLabel lDepth = new JLabel("depth:");
		gridBagLayout.setConstraints(lDepth, constraints);
		container.add(lDepth);
		
		constraints.gridy++;
		JLabel lRegexp = new JLabel("Regular Expression:");
		gridBagLayout.setConstraints(lRegexp, constraints);
		container.add(lRegexp);
		
		constraints.gridy++;
		JLabel lNbHtmlThread = new JLabel("Simultaneous Html download:");
		gridBagLayout.setConstraints(lNbHtmlThread, constraints);
		container.add(lNbHtmlThread);
		
		constraints.gridy++;
		JLabel lNbLinkedThread = new JLabel("Simultaneous Linked download:");
		gridBagLayout.setConstraints(lNbLinkedThread, constraints);
		container.add(lNbLinkedThread);
		
		constraints.gridy++;
		JLabel lDestDirectory = new JLabel("Destination Directory:");
		gridBagLayout.setConstraints(lDestDirectory, constraints);
		container.add(lDestDirectory);
		
		
		/*
		 * Right Column
		 */		 
		constraints.anchor = GridBagConstraints.WEST;
		constraints.gridx = 1;
		constraints.gridwidth = GridBagConstraints.RELATIVE;
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.weightx = 1;

		
		constraints.gridy = 0;
		JTextField rUrl = new JTextField("TMP - http://www.google.fr");
		gridBagLayout.setConstraints(rUrl, constraints);
		container.add(rUrl);
		
		constraints.gridy = 2;
		JTextField rRegexp = new JTextField("TMP - *.*");
		gridBagLayout.setConstraints(rRegexp, constraints);
		container.add(rRegexp);
		
		constraints.gridy = 5;
		JTextField rDestDirectory = new JTextField("C:\\TMP");
//		JFileChooser rDestDirectory = new JFileChooser(new File("C:\\TMP"));
		//TODO: voir pour mettre le JFileChooser
		gridBagLayout.setConstraints(rDestDirectory, constraints);
		container.add(rDestDirectory);
		
		
		
		constraints.gridwidth = GridBagConstraints.NONE;
		constraints.fill = GridBagConstraints.NONE;
		
		constraints.gridy = 1;		
		JSpinner rDepth = new JSpinner(new SpinnerNumberModel(5, 0, 50, 1));
		gridBagLayout.setConstraints(rDepth, constraints);
		container.add(rDepth);		

		constraints.gridy = 3;
		JSpinner rNbHtmlThread = new JSpinner(new SpinnerNumberModel(5, 0, 50, 1));
		gridBagLayout.setConstraints(rNbHtmlThread, constraints);
		container.add(rNbHtmlThread);		
		
		constraints.gridy = 4;
		JSpinner rNbLinkedThread = new JSpinner(new SpinnerNumberModel(5, 0, 50, 1));
		gridBagLayout.setConstraints(rNbLinkedThread, constraints);
		container.add(rNbLinkedThread);		
	}

	
}
