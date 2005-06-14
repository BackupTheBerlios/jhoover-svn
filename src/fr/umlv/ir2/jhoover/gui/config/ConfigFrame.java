/**
 * jHoover - UMLV IR2
 * UI Project
 */
package fr.umlv.ir2.jhoover.gui.config;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URI;
import java.net.URISyntaxException;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;

import com.jgoodies.forms.builder.PanelBuilder;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;

import fr.umlv.ir2.jhoover.network.DownloadManager;
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
		addPanels();
		this.setVisible(true);
	}

	private void setdesign() {
		setSize(new Dimension(800, 600));
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	private void addPanels() {
		/*
		 * Config Panel
		 */
		Container container = getContentPane();
		FormLayout configLayout = new FormLayout(
			    "center:p", 							// columns
			    "center:p, center:p, center:p");    	// rows
		PanelBuilder configFields = new PanelBuilder(configLayout);
		configFields.setDefaultDialogBorder();

		
		
		/*
		 * Header Panel
		 */
		FormLayout headerLayout = new FormLayout(
			    "p, 9dlu, p", // columns
			    "p, p");      // rows
		PanelBuilder headerFields = new PanelBuilder(headerLayout);
		headerFields.setDefaultDialogBorder();
		Icon logo = new ImageIcon("src/fr/umlv/ir2/jhoover/gui/ressources/jHoover_125x123.jpg");
		//TODO: voir pour mettre le répertoire autrement que (presque) absolu
		JLabel jLogo = new JLabel(logo, SwingConstants.CENTER);
		JLabel hooverLabel = new JLabel("jHoover - Web Site Downloader");
		
		//Adding the Header to the builder
		CellConstraints ccHeader = new CellConstraints();
		headerFields.add(jLogo,         ccHeader.xy (1,  1));
		headerFields.add(hooverLabel,   ccHeader.xy (3,  1));
		
		
		
		
		/*
		 * Fields Panel
		 */
		FormLayout fieldsLayout = new FormLayout(
			    "right:80dlu, 3dlu, p, 100dlu, 3dlu, p", // columns
			    //TODO: voir pour mettre left:p dans le 1er (ajouter une colone) et ne pas mettre 100dlu (4ème)
			    "p, 3dlu, p, 3dlu, p, 9dlu, p, 3dlu, p, 3dlu, p, 3dlu, p, 9dlu, p, 3dlu, p, 3dlu, p");      // rows
		PanelBuilder builderFields = new PanelBuilder(fieldsLayout);
		builderFields.setDefaultDialogBorder();
		
		final JTextField rProjectName = new JTextField(configuration.getProjectName());
		final JTextField rUrl = new JTextField(configuration.getUrl());
		final JSpinner rDepth = new JSpinner(new SpinnerNumberModel((int)configuration.getDepth(), 1, 50, 1));
		final JTextField rRegexp = new JTextField(configuration.getRegExp());
		final JSpinner rNbHtmlThread = new JSpinner(new SpinnerNumberModel((int)configuration.getNbHtmlThread(), 1, 50, 1));
		final JSpinner rNbLinkedThread = new JSpinner(new SpinnerNumberModel((int)configuration.getNbLinkedThread(), 1, 50, 1));
		final JTextField rDestDirectory = new JTextField(configuration.getDestDirectory());
		JButton chooseDestDirectory = new JButton("Choose...");
		chooseDestDirectory.addActionListener (new ActionListener() {
			public void actionPerformed (ActionEvent e) {
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				fileChooser.setApproveButtonText("Select");
				fileChooser.setDialogTitle("Destination Directory");
				int returnVal = fileChooser.showDialog (ConfigFrame.this , null);
				if(returnVal == JFileChooser.APPROVE_OPTION) {
					rDestDirectory.setText(fileChooser.getSelectedFile().getPath());
				}
			}
		});
		JButton helpButton = new JButton("Help"); //TODO: mettre une action à Help
		
		
		
		//Adding the Fields to the builder
		CellConstraints cc = new CellConstraints();
		builderFields.addSeparator("General",   			cc.xyw(1,  1, 6));
		builderFields.addLabel("Project Name",  			cc.xy (1,  3));
		builderFields.add(rProjectName,         			cc.xyw(3,  3, 2));
		builderFields.addLabel("Destination Directory",     cc.xy (1,  5));
		builderFields.add(rDestDirectory,				    cc.xyw(3,  5, 2));
		builderFields.add(chooseDestDirectory, 				cc.xy (6,  5));	
		
		builderFields.addSeparator("Web Site",			    cc.xyw(1,  7, 6));
		builderFields.addLabel("URL",						cc.xy (1,  9));
		builderFields.add(rUrl,  						    cc.xyw(3,  9, 4));
		builderFields.addLabel("Depth",						cc.xy (1,  11));
		builderFields.add(rDepth,  					    	cc.xy (3,  11));
		builderFields.addLabel("Filter",					cc.xy (1,  13));
		builderFields.add(rRegexp,  					    cc.xyw(3,  13, 2));
		builderFields.add(helpButton, 						cc.xy (6,  13));	
		
		builderFields.addSeparator("Parameters",  			cc.xyw(1,  15, 6));
		builderFields.addLabel("Simultaneous HTML",  		cc.xy (1,  17));
		builderFields.add(rNbHtmlThread,         			cc.xy (3,  17));
		builderFields.addLabel("Simultaneous Linked",  		cc.xy (1,  19));
		builderFields.add(rNbLinkedThread,       			cc.xy (3,  19));
		
		
		

		/*
		 * Buttons
		 */
		FormLayout buttonLayout = new FormLayout(
			    "p, 3dlu, p, 3dlu, p", // columns
			    "p");      // rows
		PanelBuilder buttonFields = new PanelBuilder(buttonLayout);
		buttonFields.setDefaultDialogBorder();
		
		//Ok Button
		JButton buttonOk = new JButton("OK");
		buttonOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("Saving configuration...");
				String projectName = rProjectName.getText(); //TODO: verifier que le projectName ne contient pas de caracteres spéciaux
				String url = rUrl.getText(); //TODO: verifier la syntaxe de l'URL, ajouter index.html, ...
				Integer depth = (Integer)rDepth.getValue();
				String regExp = rRegexp.getText();
				Integer nbHtmlThread = (Integer)rNbHtmlThread.getValue();
				Integer nbLinkedThread = (Integer)rNbLinkedThread.getValue();
				String destDirectory = validDestDirectory(rDestDirectory.getText());
				configuration.setProjectName(projectName);
				configuration.setUrl(url);
				configuration.setDepth(depth);
				configuration.setRegExp(regExp);
				configuration.setNbHtmlThread(nbHtmlThread);
				configuration.setNbLinkedThread(nbLinkedThread);
				configuration.setDestDirectory(destDirectory);
				configuration.save();
				startDownload(projectName, url, destDirectory, depth, nbHtmlThread, nbLinkedThread, regExp);
				dispose();
			}
		});
		
		//Cancel Button
		JButton buttonCancel = new JButton("Cancel");
		buttonCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("Cancelling...");
				dispose();
			}
		});
		
		//Reset Button
		JButton buttonReset = new JButton("Reset");
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
		
		//Adding the buttons to the builder
		CellConstraints ccButton = new CellConstraints();
		buttonFields.add(buttonOk,  		ccButton.xy (1,  1));
		buttonFields.add(buttonCancel,  	ccButton.xy (3,  1));
		buttonFields.add(buttonReset,  		ccButton.xy (5,  1));
		
		
		
		/*
		 * Adding the panels to the container
		 */
		CellConstraints ccFields = new CellConstraints();
		configFields.add(headerFields.getPanel(),    ccFields.xy (1,  1));
		configFields.add(builderFields.getPanel(),   ccFields.xy (1,  2));
		configFields.add(buttonFields.getPanel(),  	 ccFields.xy (1,  3));
		container.add(configFields.getPanel());
	}
	
	
	/*
	 * Return the destDirectory with an "/" at the end
	 */
	private String validDestDirectory(String destDirectory) {		
		if (destDirectory.endsWith("/")) {
			return destDirectory; 
		}
		return destDirectory = destDirectory + "/"; 
	}
	
	
	private void startDownload(String projectName, String startURIString, String destDirectory, int maxDepth, int maxDLHtml, int maxDLLink, String regExp) {
		//TODO: utiliser la RegExp
		URI startURI = null;
		
		//Adds the project name in the path
		destDirectory = destDirectory + projectName;
		
		try {
			startURI = new URI(startURIString);
		} catch (URISyntaxException e) {
			System.err.println(e);
			System.out.println("Exiting the program...");
			System.exit(0);
		}			
		
		DownloadManager downloadManager = new DownloadManager(maxDLHtml, maxDLLink, maxDepth, startURI, destDirectory);
		//add the file pointed by startURI in the downloadList from the downloadManager
		downloadManager.addHtmlFile(startURI, 0);
		Thread downloadManagerThread = new Thread(downloadManager);
		downloadManagerThread.start();
	}
}
