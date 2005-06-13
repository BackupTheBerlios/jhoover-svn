/**
 * jHoover - UMLV IR2
 * UI Project
 */
package fr.umlv.ir2.jhoover.gui.config;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
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

import fr.umlv.ir2.jhoover.JHoover;
import fr.umlv.ir2.jhoover.util.JHooverConfiguration;

/**
 * @author Romain Papuchon
 *
 */
public class ConfigFrame extends JFrame{

	//Configuration of jHoover
	JHooverConfiguration configuration;
	
	
	public ConfigFrame(String title) {
		super(title);
		setdesign();
		configuration = JHooverConfiguration.getInstance();
		configuration.load();
		addFields();
		this.setVisible(true);
	}

	private void setdesign() {
		setSize(new Dimension(800, 600));
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	private void addFields() {
		//TODO: revoir la position des objets
		GridLayout gridLayout = new GridLayout(3,1,1,1);
		Container container = getContentPane();
		container.setLayout(gridLayout);
		
		/*
		 * Declaration
		 */
		final JLabel lProjectName;
		final JLabel lUrl;
		final JLabel lDepth;
		final JLabel lRegexp;
		final JLabel lNbHtmlThread;
		final JLabel lNbLinkedThread;
		final JLabel lDestDirectory;
		final JTextField rProjectName;
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
		 * Logo Panel
		 */
		JPanel logoPanel = new JPanel();
		GridLayout logoLayout = new GridLayout(1,1,1,1);
		logoPanel.setLayout(logoLayout);
		//TODO: faire marcher ce logo
		Icon logo = new ImageIcon("C:/Romain/Workspace/jHoover/src/fr/umlv/ir2/jhoover/gui/ressources/jHoover.jpg");
		JLabel jLogo = new JLabel(logo, SwingConstants.CENTER);
		logoPanel.add(jLogo);
		
		
		
		/*
		 * Fields Panel
		 */
		JPanel fieldsPanel = new JPanel();
		GridLayout fieldsLayout = new GridLayout(7,2,1,1);
		fieldsPanel.setLayout(fieldsLayout);
		
		lProjectName = new JLabel("Project Name:");
		fieldsPanel.add(lProjectName);
		rProjectName = new JTextField(configuration.getProjectName());
		fieldsPanel.add(rProjectName);
		
		lUrl = new JLabel("URL:");
		fieldsPanel.add(lUrl);
		rUrl = new JTextField(configuration.getUrl());
		fieldsPanel.add(rUrl);
		
		lDepth = new JLabel("depth:");
		fieldsPanel.add(lDepth);
		rDepth = new JSpinner(new SpinnerNumberModel((int)configuration.getDepth(), 1, 50, 1));
		fieldsPanel.add(rDepth);
		
		lRegexp = new JLabel("Regular Expression:");
		fieldsPanel.add(lRegexp);
		rRegexp = new JTextField(configuration.getRegExp());
		fieldsPanel.add(rRegexp);
		
		lNbHtmlThread = new JLabel("Simultaneous Html download:");
		fieldsPanel.add(lNbHtmlThread);
		rNbHtmlThread = new JSpinner(new SpinnerNumberModel((int)configuration.getNbHtmlThread(), 1, 50, 1));
		fieldsPanel.add(rNbHtmlThread);
		
		lNbLinkedThread = new JLabel("Simultaneous Linked download:");
		fieldsPanel.add(lNbLinkedThread);
		rNbLinkedThread = new JSpinner(new SpinnerNumberModel((int)configuration.getNbLinkedThread(), 1, 50, 1));
		fieldsPanel.add(rNbLinkedThread);
		
		lDestDirectory = new JLabel("Destination Directory:");
		fieldsPanel.add(lDestDirectory);
		rDestDirectory = new JTextField(configuration.getDestDirectory());
		//JFileChooser rDestDirectory = new JFileChooser();
		//TODO: voir pour mettre le JFileChooser
		fieldsPanel.add(rDestDirectory);		
		

		/*
		 * Buttons
		 */
		JPanel buttonPanel = new JPanel(); 
		GridLayout buttonLayout = new GridLayout(1,3,1,1);
		buttonPanel.setLayout(buttonLayout);
		
		//Ok Button
		buttonOk = new JButton("OK");
		buttonOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("Saving configuration...");
				String projectName = rProjectName.getText();
				String url = rUrl.getText();
				Integer depth = (Integer)rDepth.getValue();
				String regExp = rRegexp.getText();
				Integer nbHtmlThread = (Integer)rNbHtmlThread.getValue();
				Integer nbLinkedThread = (Integer)rNbLinkedThread.getValue();
				String destDirectory = rDestDirectory.getText();
				configuration.setProjectName(projectName);
				configuration.setUrl(url);
				configuration.setDepth(depth);
				configuration.setRegExp(regExp);
				configuration.setNbHtmlThread(nbHtmlThread);
				configuration.setNbLinkedThread(nbLinkedThread);
				configuration.setDestDirectory(destDirectory);
				configuration.save();
				JHoover.startDownload(projectName, url, destDirectory, depth, nbHtmlThread, nbLinkedThread, regExp);
				dispose();
			}
		});
		buttonPanel.add(buttonOk);
		
		//Cancel Button
		buttonCancel = new JButton("Cancel");
		buttonCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("Cancelling...");
				dispose();
			}
		});
		buttonPanel.add(buttonCancel);
		
		//Reset Button
		buttonReset = new JButton("Reset");
		buttonReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("Resetting fields...");
				rUrl.setText(configuration.getUrl());
				rProjectName.setText(configuration.getProjectName());
				rDepth.setValue(configuration.getDepth());
				rRegexp.setText(configuration.getRegExp());
				rNbHtmlThread.setValue(configuration.getNbHtmlThread());
				rNbLinkedThread.setValue(configuration.getNbLinkedThread());
				rDestDirectory.setText(configuration.getDestDirectory());
			}
		});
		buttonPanel.add(buttonReset);
		
		
		/*
		 * Adding the panels to the container
		 */
		container.add(logoPanel);
		container.add(fieldsPanel);
		container.add(buttonPanel);
	}
}
