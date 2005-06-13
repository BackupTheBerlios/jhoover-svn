/**
 * jHoover - UMLV IR2
 * UI Project
 */
package fr.umlv.ir2.jhoover.gui.config;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;

import fr.umlv.ir2.jhoover.util.JHooverConfiguration;

/**
 * @author Romain Papuchon
 *
 */
public class ConfigFrame_old extends JFrame{

	//Configuration of jHoover
	JHooverConfiguration configuration;
	
	public ConfigFrame_old(String title) {
		super(title);
		setdesign();
		configuration = JHooverConfiguration.getInstance();
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
		 * Declaration
		 */
		final JLabel lUrl;
		final JLabel lDepth;
		final JLabel lRegexp;
		final JLabel lNbHtmlThread;
		final JLabel lNbLinkedThread;
		final JLabel lDestDirectory;
		final JTextField rUrl;
		final JTextField rRegexp;
		final JTextField rDestDirectory;
		final JSpinner rDepth;
		final JSpinner rNbHtmlThread;
		final JSpinner rNbLinkedThread;
		JButton buttonOk;
		JButton buttonCancel;
		JButton buttonReset;
		
		

	
		/*
		 * Left Column
		 */
//		constraints.anchor = GridBagConstraints.EAST;
//		constraints.insets = new Insets(5,15,5,15);
//		constraints.gridx = 0;
//		constraints.gridy = 0;
		
		lUrl = new JLabel("URL:");
//		gridBagLayout.setConstraints(lUrl, constraints);
		container.add(lUrl);
		
//		constraints.gridy++;
		lDepth = new JLabel("depth:");
//		gridBagLayout.setConstraints(lDepth, constraints);
		container.add(lDepth);
		
//		constraints.gridy++;
		lRegexp = new JLabel("Regular Expression:");
//		gridBagLayout.setConstraints(lRegexp, constraints);
		container.add(lRegexp);
		
//		constraints.gridy++;
		lNbHtmlThread = new JLabel("Simultaneous Html download:");
//		gridBagLayout.setConstraints(lNbHtmlThread, constraints);
		container.add(lNbHtmlThread);
		
//		constraints.gridy++;
		lNbLinkedThread = new JLabel("Simultaneous Linked download:");
//		gridBagLayout.setConstraints(lNbLinkedThread, constraints);
		container.add(lNbLinkedThread);
		
//		constraints.gridy++;
		lDestDirectory = new JLabel("Destination Directory:");
//		gridBagLayout.setConstraints(lDestDirectory, constraints);
		container.add(lDestDirectory);
		
		
		
		
		
		
		/*
		 * Right Column
		 */		 
//		constraints.anchor = GridBagConstraints.WEST;
//		constraints.gridx = 1;
//		constraints.gridwidth = GridBagConstraints.RELATIVE;
//		constraints.fill = GridBagConstraints.HORIZONTAL;
//		constraints.weightx = 1;

		//jTextField url
//		constraints.gridy = 0;
		rUrl = new JTextField(configuration.getUrl());
//		gridBagLayout.setConstraints(rUrl, constraints);
		container.add(rUrl);

		//jTextField regular expression
//		constraints.gridy = 2;
		rRegexp = new JTextField(configuration.getRegExp());
//		gridBagLayout.setConstraints(rRegexp, constraints);
		container.add(rRegexp);
		
		//jTextField destination Directory
//		constraints.gridy = 5;
		rDestDirectory = new JTextField(configuration.getDestDirectory());
//		JFileChooser rDestDirectory = new JFileChooser(new File("C:\\TMP"));
		//TODO: voir pour mettre le JFileChooser
//		gridBagLayout.setConstraints(rDestDirectory, constraints);
		container.add(rDestDirectory);
		
//		constraints.gridwidth = GridBagConstraints.NONE;
//		constraints.fill = GridBagConstraints.NONE;
		
		//spinner depth
//		constraints.gridy = 1;		
		rDepth = new JSpinner(new SpinnerNumberModel((int)configuration.getDepth(), 1, 50, 1));
//		gridBagLayout.setConstraints(rDepth, constraints);
		container.add(rDepth);		

		//spinner nbHtmlThread
//		constraints.gridy = 3;
		rNbHtmlThread = new JSpinner(new SpinnerNumberModel((int)configuration.getNbHtmlThread(), 1, 50, 1));
//		gridBagLayout.setConstraints(rNbHtmlThread, constraints);
		container.add(rNbHtmlThread);		
		
		//spinner nbLinkedThread
//		constraints.gridy = 4;
		rNbLinkedThread = new JSpinner(new SpinnerNumberModel((int)configuration.getNbLinkedThread(), 1, 50, 1));
//		gridBagLayout.setConstraints(rNbLinkedThread, constraints);
		container.add(rNbLinkedThread);
		
		
		
		
		/*
		 * Buttons
		 */
		
		//Ok Button
		buttonOk = new JButton("OK");
		buttonOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
//				System.out.println("url: " + rUrl.getText());
//				System.out.println("depth: " + rDepth.getValue());
//				System.out.println("regExp: " + rRegexp.getText());
//				System.out.println("nbHtmlThread: " + rNbHtmlThread.getValue());
//				System.out.println("nbLinkedThread: " + rNbLinkedThread.getValue());
//				System.out.println("dest directory: " + rDestDirectory.getText());
				System.out.println("Saving configuration...");
				configuration.setUrl(rUrl.getText());
				configuration.setDepth((Integer)rDepth.getValue());
				configuration.setRegExp(rRegexp.getText());
				configuration.setNbHtmlThread((Integer)rNbHtmlThread.getValue());
				configuration.setNbLinkedThread((Integer)rNbLinkedThread.getValue());
				configuration.setDestDirectory(rDestDirectory.getText());
				configuration.save();
			}
		});
		container.add(buttonOk);
		
		
		//Cancel Button
		buttonCancel = new JButton("Cancel");
		buttonCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("Cancelling...");
			}
		});
		container.add(buttonCancel);

		
		//Reset Button
		buttonReset = new JButton("Reset");
		buttonReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("Resetting fields...");
				rUrl.setText(configuration.getUrl());
				rDepth.setValue(configuration.getDepth());
				rRegexp.setText(configuration.getRegExp());
				rNbHtmlThread.setValue(configuration.getNbHtmlThread());
				rNbLinkedThread.setValue(configuration.getNbLinkedThread());
				rDestDirectory.setText(configuration.getDestDirectory());
			}
		});
		container.add(buttonCancel);
	}
}
