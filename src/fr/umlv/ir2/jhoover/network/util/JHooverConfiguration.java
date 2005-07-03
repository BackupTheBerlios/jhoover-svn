/**
 * jHoover - UMLV IR2
 * UI Project
 */
package fr.umlv.ir2.jhoover.network.util;

import java.io.File;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.XMLConfiguration;


/**
 * @author Romain Papuchon
 *
 * Configuration and Save of jHoover
 */
public class JHooverConfiguration {
	//Configuration
	private XMLConfiguration configuration;
	//Singleton
	private static JHooverConfiguration INSTANCE = null;
	//Configuration File
	private static final String CONFIG_FILE = "params.xml";

	private static final int DEFAULT_NB_THREAD = 6;
	private static final int DEFAULT_DEPTH = 3;
	private static final String DEFAULT_BLANK_STRING = "";
	
	/*
	 * Constructor (private for the Singleton)
	 */
	private JHooverConfiguration(String configFile) {
		try {
			this.configuration = new XMLConfiguration(new File(configFile));
			this.configuration.load();
		} catch (ConfigurationException e) {
			System.err.println(e.getMessage());
		}
	}

	/*
	 * Returns the singleton 
	 */
	public static JHooverConfiguration getInstance ()
	{
		if (INSTANCE == null)
			INSTANCE = new JHooverConfiguration(CONFIG_FILE);
		return INSTANCE;
	}

	
	
	public void setProjectName(String projectName) {
		if (projectName != null) {
			this.configuration.setProperty("project.name", projectName);
		} else {
			this.configuration.setProperty("project.name", DEFAULT_BLANK_STRING);
		}
	}
	
	public void setUrl(String url) {
		if (url != null) {
			this.configuration.setProperty("url", url);
		} else {
			this.configuration.setProperty("url", DEFAULT_BLANK_STRING);
		}
	}

	public void setDepth(Integer depth) {
		if (depth > 0) {
			this.configuration.setProperty("depth", depth);
		} else {
			this.configuration.setProperty("depth", DEFAULT_DEPTH);
		}
	}
	
	public void setRegExp(String regExp) {
		if (regExp != null) {
			this.configuration.setProperty("regexp", regExp);
		} else {
			this.configuration.setProperty("regexp", DEFAULT_BLANK_STRING);
		}
	}
	
	public void setNbHtmlThread(Integer nbHtmlThread) {
		if (nbHtmlThread > 0) {
			this.configuration.setProperty("nb.html.thread", nbHtmlThread);
		} else {
			this.configuration.setProperty("nb.html.thread", DEFAULT_NB_THREAD);
		}
	}

	public void setNbLinkedThread(Integer nbLinkedThread) {
		if (nbLinkedThread > 0) {
			this.configuration.setProperty("nb.linked.thread", nbLinkedThread);
		} else {
			this.configuration.setProperty("nb.linked.thread", DEFAULT_NB_THREAD);	
		}
	}
	
	public void setDestDirectory(String destDirectory) {
		if (destDirectory != null) {
			this.configuration.setProperty("destination.directory", destDirectory);
		} else {
			this.configuration.setProperty("destination.directory", DEFAULT_BLANK_STRING);
		}
	}
	
	
	
	public String getProjectName() {
		String projectName = this.configuration.getString("project.name");
		if (projectName != null) {
			return projectName;
		}
		return DEFAULT_BLANK_STRING;
	}
	
	public String getUrl() {
		String url = this.configuration.getString("url");
		if (url != null) {
			return url;
		}
		return DEFAULT_BLANK_STRING;
	}

	public Integer getDepth() {
		Integer depth = this.configuration.getInt("depth");
		if (depth != null) {
			return depth;
		}
		return DEFAULT_DEPTH;
	}
	
	public String getRegExp() {
		String regExp = this.configuration.getString("regexp");
		if (regExp != null) {
			return regExp;
		}
		return DEFAULT_BLANK_STRING;
	}
	
	public Integer getNbHtmlThread() {
		Integer nbHtmlThread = this.configuration.getInt("nb.html.thread");
		if (nbHtmlThread != null) {
			return nbHtmlThread;
		}
		return DEFAULT_NB_THREAD;
	}

	public Integer getNbLinkedThread() {
		Integer nbLinkedThread = this.configuration.getInt("nb.linked.thread");
		if (nbLinkedThread != null) {
			return nbLinkedThread;
		}
		return DEFAULT_NB_THREAD;
	}
	
	public String getDestDirectory() {
		String destDirectory = this.configuration.getString("destination.directory");
		if (destDirectory != null) {
			return destDirectory;
		}
		return DEFAULT_BLANK_STRING;
	}

	public void save() {
		try {
			this.configuration.save();
		} catch (ConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
