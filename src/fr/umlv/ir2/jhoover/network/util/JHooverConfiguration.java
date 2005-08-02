/**
 * jHoover - UMLV IR2
 * UI Project
 */
package fr.umlv.ir2.jhoover.network.util;

import java.io.File;
import java.util.NoSuchElementException;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.XMLConfiguration;

/**
 * Configuration and Save of jHoover
 * 
 * @author Romain Papuchon
 */
public class JHooverConfiguration {
    // Configuration
    private XMLConfiguration configuration;
    // Singleton
    private static JHooverConfiguration INSTANCE = null;
    // Configuration File
    private static final String CONFIG_FILE = "params.xml";

    private static final int DEFAULT_NB_THREAD = 6;
    private static final int DEFAULT_DEPTH = 3;
    private static final String DEFAULT_BLANK_STRING = "";

    /**
     * Create an instance of jHooverConfiguration
     * 
     * @param configFile the singleton
     */
    private JHooverConfiguration(String configFile) {
        try {
            configuration = new XMLConfiguration(new File(configFile));
            configuration.load();
        } catch (ConfigurationException e) {
            System.err.println(e.getMessage());
        }
    }

    /**
     * Returns the singleton
     * 
     * @return the instance
     */
    public static JHooverConfiguration getInstance() {
        if (INSTANCE == null)
            INSTANCE = new JHooverConfiguration(CONFIG_FILE);
        return INSTANCE;
    }

    /**
     * Save the project name in the configuration file
     * 
     * @param projectName the property to save
     */
    public void setProjectName(String projectName) {
        if (projectName != null) {
            configuration.setProperty("project.name", projectName);
        } else {
            configuration.setProperty("project.name", DEFAULT_BLANK_STRING);
        }
    }

    /**
     * Save the url in the configuration file
     * 
     * @param url the property to save
     */
    public void setUrl(String url) {
        if (url != null) {
            configuration.setProperty("url", url);
        } else {
            configuration.setProperty("url", DEFAULT_BLANK_STRING);
        }
    }

    /**
     * Save the depth in the configuration file
     * 
     * @param depth the property to save
     */
    public void setDepth(Integer depth) {
        if (depth > 0) {
            configuration.setProperty("depth", depth);
        } else {
            configuration.setProperty("depth", DEFAULT_DEPTH);
        }
    }

    /**
     * Save the regular expression in the configuration file
     * 
     * @param regExp the property to save
     */
    public void setRegExp(String regExp) {
        if (regExp != null) {
            configuration.setProperty("regexp", regExp);
        } else {
            configuration.setProperty("regexp", DEFAULT_BLANK_STRING);
        }
    }

    /**
     * Save the number of html thread in the configuration file
     * 
     * @param nbHtmlThread the property to save
     */
    public void setNbHtmlThread(Integer nbHtmlThread) {
        if (nbHtmlThread > 0) {
            configuration.setProperty("nb.html.thread", nbHtmlThread);
        } else {
            configuration.setProperty("nb.html.thread", DEFAULT_NB_THREAD);
        }
    }

    /**
     * Save the number of linked thread in the configuration file
     * 
     * @param nbLinkedThread the property to save
     */
    public void setNbLinkedThread(Integer nbLinkedThread) {
        if (nbLinkedThread > 0) {
            configuration.setProperty("nb.linked.thread", nbLinkedThread);
        } else {
            configuration.setProperty("nb.linked.thread", DEFAULT_NB_THREAD);
        }
    }

    /**
     * Save the destination directory in the configuration file
     * 
     * @param destDirectory the property to save
     */
    public void setDestDirectory(String destDirectory) {
        if (destDirectory != null) {
            configuration.setProperty("destination.directory", destDirectory);
        } else {
            configuration.setProperty("destination.directory", DEFAULT_BLANK_STRING);
        }
    }

    /**
     * Get the project name from the configuration file
     * 
     * @return the property to get
     */
    public String getProjectName() {
        try {
            String projectName = configuration.getString("project.name");
            if (projectName != null) {
                return projectName;
            }
        } catch (NoSuchElementException e) {
        }
        return DEFAULT_BLANK_STRING;
    }

    /**
     * Get the url from the configuration file
     * 
     * @return the property to get
     */
    public String getUrl() {
        try {
            String url = configuration.getString("url");
            if (url != null) {
                return url;
            }
        } catch (NoSuchElementException e) {
        }
        return DEFAULT_BLANK_STRING;
    }

    /**
     * Get the depth from the configuration file
     * 
     * @return the property to get
     */
    public Integer getDepth() {
        try {
            Integer depth = configuration.getInt("depth");
            if (depth != null) {
                return depth;
            }
        } catch (NoSuchElementException e) {
        }
        return DEFAULT_DEPTH;
    }

    /**
     * Get the regular expression from the configuration file
     * 
     * @return the property to get
     */
    public String getRegExp() {
        try {
            String regExp = configuration.getString("regexp");
            if (regExp != null) {
                return regExp;
            }
        } catch (NoSuchElementException e) {
        }
        return DEFAULT_BLANK_STRING;
    }

    /**
     * Get the number of html thread from the configuration file
     * 
     * @return the property to get
     */
    public Integer getNbHtmlThread() {
        try {
            Integer nbHtmlThread = configuration.getInt("nb.html.thread");
            if (nbHtmlThread != null) {
                return nbHtmlThread;
            }
        } catch (NoSuchElementException e) {
        }
        return DEFAULT_NB_THREAD;
    }

    /**
     * Get the number of linked thread from the configuration file
     * 
     * @return the property to get
     */
    public Integer getNbLinkedThread() {
        try {
            Integer nbLinkedThread = configuration.getInt("nb.linked.thread");
            if (nbLinkedThread != null) {
                return nbLinkedThread;
            }
        } catch (NoSuchElementException e) {
        }
        return DEFAULT_NB_THREAD;
    }

    /**
     * Get the destination directory from the configuration file
     * 
     * @return the property to get
     */
    public String getDestDirectory() {
        try {
            String destDirectory = configuration.getString("destination.directory");
            if (destDirectory != null) {
                return destDirectory;
            }
        } catch (NoSuchElementException e) {
        }
        return DEFAULT_BLANK_STRING;
    }

    /**
     * Save the parameters
     */
    public void save() {
        try {
            configuration.save();
        } catch (ConfigurationException e) {
            e.printStackTrace();
        }
    }
}
