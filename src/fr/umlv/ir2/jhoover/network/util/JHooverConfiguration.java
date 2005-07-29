/**
 * jHoover - UMLV IR2
 * UI Project
 */
package fr.umlv.ir2.jhoover.network.util;

import java.io.File;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.XMLConfiguration;


/**
 * Configuration and Save of jHoover
 * @author Romain Papuchon
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
	
	/**
	 * Create an instance of jHooverConfiguration
	 * @param configFile the singleton
	 */
	private JHooverConfiguration(String configFile) {
		try {
			this.configuration = new XMLConfiguration(new File(configFile));
			this.configuration.load();
		} catch (ConfigurationException e) {
			System.err.println(e.getMessage());
		}
	}

	/**
	 * Returns the singleton 
	 * @return the instance
	 */
	public static JHooverConfiguration getInstance()
	{
		if (INSTANCE == null)
			INSTANCE = new JHooverConfiguration(CONFIG_FILE);
		return INSTANCE;
	}

	
	
	/**
	 * Save the project name in the configuration file
	 * @param projectName the property to save
	 */
	public void setProjectName(String projectName) {
		if (projectName != null) {
			this.configuration.setProperty("project.name", projectName);
		} else {
			this.configuration.setProperty("project.name", DEFAULT_BLANK_STRING);
		}
	}
	
	/**
	 * Save the url in the configuration file
	 * @param url the property to save
	 */
	public void setUrl(String url) {
		if (url != null) {
			this.configuration.setProperty("url", url);
		} else {
			this.configuration.setProperty("url", DEFAULT_BLANK_STRING);
		}
	}

	/**
	 * Save the depth in the configuration file
	 * @param depth the property to save
	 */
	public void setDepth(Integer depth) {
		if (depth > 0) {
			this.configuration.setProperty("depth", depth);
		} else {
			this.configuration.setProperty("depth", DEFAULT_DEPTH);
		}
	}
	
	/**
	 * Save the regular expression in the configuration file
	 * @param regExp the property to save
	 */
	public void setRegExp(String regExp) {
		if (regExp != null) {
			this.configuration.setProperty("regexp", regExp);
		} else {
			this.configuration.setProperty("regexp", DEFAULT_BLANK_STRING);
		}
	}
	
	/**
	 * Save the number of html thread in the configuration file
	 * @param nbHtmlThread the property to save
	 */
	public void setNbHtmlThread(Integer nbHtmlThread) {
		if (nbHtmlThread > 0) {
			this.configuration.setProperty("nb.html.thread", nbHtmlThread);
		} else {
			this.configuration.setProperty("nb.html.thread", DEFAULT_NB_THREAD);
		}
	}

	/**
	 * Save the number of linked thread in the configuration file
	 * @param nbLinkedThread the property to save
	 */
	public void setNbLinkedThread(Integer nbLinkedThread) {
		if (nbLinkedThread > 0) {
			this.configuration.setProperty("nb.linked.thread", nbLinkedThread);
		} else {
			this.configuration.setProperty("nb.linked.thread", DEFAULT_NB_THREAD);	
		}
	}
	
	/**
	 * Save the destination directory in the configuration file
	 * @param destDirectory the property to save
	 */
	public void setDestDirectory(String destDirectory) {
		if (destDirectory != null) {
			this.configuration.setProperty("destination.directory", destDirectory);
		} else {
			this.configuration.setProperty("destination.directory", DEFAULT_BLANK_STRING);
		}
	}
	
	
	/**
	 * Get the project name from the configuration file
	 * @return the property to get 
	 */
	public String getProjectName() {
		String projectName = this.configuration.getString("project.name");
		if (projectName != null) {
			return projectName;
		}
		return DEFAULT_BLANK_STRING;
	}
	
	/**
	 * Get the url from the configuration file
	 * @return the property to get
	 */
	public String getUrl() {
		String url = this.configuration.getString("url");
		if (url != null) {
			return url;
		}
		return DEFAULT_BLANK_STRING;
	}

	/**
	 * Get the depth from the configuration file
	 * @return the property to get
	 */
	public Integer getDepth() {
		Integer depth = this.configuration.getInt("depth");
		if (depth != null) {
			return depth;
		}
		return DEFAULT_DEPTH;
	}
	
	/**
	 * Get the regular expression from the configuration file
	 * @return the property to get
	 */
	public String getRegExp() {
		String regExp = this.configuration.getString("regexp");
		if (regExp != null) {
			return regExp;
		}
		return DEFAULT_BLANK_STRING;
	}
	
	/**
	 * Get the number of html thread from the configuration file
	 * @return the property to get
	 */
	public Integer getNbHtmlThread() {
		Integer nbHtmlThread = this.configuration.getInt("nb.html.thread");
		if (nbHtmlThread != null) {
			return nbHtmlThread;
		}
		return DEFAULT_NB_THREAD;
	}

	/**
	 * Get the number of linked thread from the configuration file
	 * @return the property to get
	 */
	public Integer getNbLinkedThread() {
		Integer nbLinkedThread = this.configuration.getInt("nb.linked.thread");
		if (nbLinkedThread != null) {
			return nbLinkedThread;
		}
		return DEFAULT_NB_THREAD;
	}
	
	/**
	 * Get the destination directory from the configuration file
	 * @return the property to get
	 */
	public String getDestDirectory() {
		String destDirectory = this.configuration.getString("destination.directory");
		if (destDirectory != null) {
			return destDirectory;
		}
		return DEFAULT_BLANK_STRING;
	}

	/**
	 * Save the parameters
	 */
	public void save() {
		try {
			this.configuration.save();
		} catch (ConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
