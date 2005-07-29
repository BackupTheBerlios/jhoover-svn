/**
 * jHoover - UMLV IR2
 * UI Project
 */
package fr.umlv.ir2.jhoover.gui;

import java.awt.event.ActionEvent;
import java.util.ArrayList;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JOptionPane;

import fr.umlv.ir2.jhoover.JHoover;
import fr.umlv.ir2.jhoover.gui.detailled.DetailledModel;
import fr.umlv.ir2.jhoover.gui.dialog.AddFilterDialog;
import fr.umlv.ir2.jhoover.gui.dialog.DeleteFilterDialog;
import fr.umlv.ir2.jhoover.gui.dialog.FindFileDialog;
import fr.umlv.ir2.jhoover.gui.dialog.ModifyFilterDialog;
import fr.umlv.ir2.jhoover.gui.dialog.RunConfigDialog;
import fr.umlv.ir2.jhoover.gui.dialog.SetConfigDialog;
import fr.umlv.ir2.jhoover.gui.panel.JHMainPanel;
import fr.umlv.ir2.jhoover.gui.tool.Icons;
import fr.umlv.ir2.jhoover.gui.tool.Labels;
import fr.umlv.ir2.jhoover.network.DownloadAndParseFile;
import fr.umlv.ir2.jhoover.network.DownloadManager;

/**
 * @author Romain Papuchon
 *
 */
public final class ActionManager
{
	/**
	 * New action
	 */
	public static final Action newAction = new AbstractAction(Labels.NEW_LABEL, Icons.NEW_ICON) {
		public void actionPerformed (ActionEvent e) {
			if (DownloadManager.isRunning()) {
				Object[] options = {"Yes", "No"};			
				int n = JOptionPane.showOptionDialog(JHMainFrame.getInstance(), Labels.CANCEL_THE_DOWNLOAD_AND_NEW_QUESTION_LABEL, Labels.DOWNLOAD_STATUS_LABEL, JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[1]);
				if (n==0) {
					//YES
					stopAction();
					new RunConfigDialog(Labels.RUN_JHOOVER_LABEL);					
				}
			} else {
				new RunConfigDialog(Labels.RUN_JHOOVER_LABEL);
			}
		}
	};

	/**
	 * Stop action
	 */
	public static final Action stopAction = new AbstractAction(Labels.STOP_LABEL, Icons.STOP_ICON) {
		public void actionPerformed (ActionEvent e) {
			if (DownloadManager.isRunning()) {
				Object[] options = {"Yes", "No"};			
				int n = JOptionPane.showOptionDialog(JHMainFrame.getInstance(), Labels.CANCEL_THE_DOWNLOAD_QUESTION_LABEL, Labels.DOWNLOAD_STATUS_LABEL, JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[1]);
				if (n==0) {
					//YES
					stopAction();					
				}
			}
		}
	};
	
	/**
	 * Pause action
	 */
	public static final Action pauseAction = new AbstractAction(Labels.PAUSE_LABEL, Icons.PAUSE_ICON) {
		public void actionPerformed (ActionEvent e) {	
			if (DownloadManager.isRunning()) {
				if (!DownloadManager.getInstance(0, 0, 0, null, null, null).isPaused()) {
					//pause the downloadManager Thread
					JHoover.getInstance().getDownloadManager().setPauseStatus(true);
					//pause all the Download Threads
					ArrayList<DownloadAndParseFile> threadList = DownloadManager.getInstance(0, 0, 0, null, null, null).getThreadList();
					for (int i=0; i<threadList.size(); i++) {
						synchronized (threadList.get(i)) {
							threadList.get(i).setPauseStatus(true);
						}
					}
					JOptionPane.showMessageDialog(JHMainFrame.getInstance(), Labels.DOWNLOAD_PAUSED_LABEL, Labels.DOWNLOAD_STATUS_LABEL, JOptionPane.INFORMATION_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(JHMainFrame.getInstance(), Labels.DOWNLOAD_ALREADY_PAUSED_LABEL, Labels.DOWNLOAD_STATUS_LABEL, JOptionPane.INFORMATION_MESSAGE);
				}
			}
		}
	};

	/**
	 * Resume action
	 */
	public static final Action resumeAction = new AbstractAction(Labels.RESUME_LABEL, Icons.RESUME_ICON) {
		public void actionPerformed (ActionEvent e) {
			if (DownloadManager.isRunning()) {
				if (DownloadManager.getInstance(0, 0, 0, null, null, null).isPaused()) {
					resumeAction();
					JOptionPane.showMessageDialog(JHMainFrame.getInstance(), Labels.DOWNLOAD_RESUMED_LABEL, Labels.DOWNLOAD_STATUS_LABEL, JOptionPane.INFORMATION_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(JHMainFrame.getInstance(), Labels.DOWNLOAD_ALREADY_RESUMED_LABEL, Labels.DOWNLOAD_STATUS_LABEL, JOptionPane.INFORMATION_MESSAGE);
				}
			}
		}	
	};
	
	/**
	 * Close action
	 */
	public static final Action closeAction = new AbstractAction(Labels.CLOSE_LABEL, Icons.CLOSE_ICON) {
		public void actionPerformed (ActionEvent e) {
			if (DownloadManager.isRunning()) {
				Object[] options = {"Yes", "No"};			
				int n = JOptionPane.showOptionDialog(JHMainFrame.getInstance(), Labels.CANCEL_THE_DOWNLOAD_QUESTION_LABEL, Labels.DOWNLOAD_STATUS_LABEL, JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[1]);
				if (n==0) {
					//YES
					stopAction();
					initAction();					
				}
			} else {
				initAction();
			}
		}
	};
	
	/**
	 * Exit action
	 */
	public static final Action exitAction = new AbstractAction(Labels.EXIT_LABEL, Icons.EXIT_ICON) {
		public void actionPerformed (ActionEvent e) {
			if (DownloadManager.isRunning()) {
				Object[] options = {"Yes", "No"};			
				int n = JOptionPane.showOptionDialog(JHMainFrame.getInstance(), Labels.CANCEL_THE_DOWNLOAD_QUESTION_LABEL, Labels.DOWNLOAD_STATUS_LABEL, JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[1]);
				if (n==0) {
					//YES
					stopAction();
					initAction();
					System.exit(0);
				}
			} else {
				initAction();
				System.exit(0);
			}
		}
	};

	/**
	 * Add a filter action
	 */
	public static final Action addFilterAction = new AbstractAction(Labels.ADD_FILTER_LABEL, Icons.ADD_FILTER_ICON) {
		public void actionPerformed (ActionEvent e) {
			new AddFilterDialog();
		}
	};
	
	/**
	 * Modify a filter action
	 */
	public static final Action modifyFilterAction = new AbstractAction(Labels.MODIFY_FILTER_LABEL, Icons.MODIFY_FILTER_ICON) {
		public void actionPerformed (ActionEvent e) {
			new ModifyFilterDialog();
		}
	};
	
	/**
	 * Delete a filter action
	 */
	public static final Action deleteFilterAction = new AbstractAction(Labels.DELETE_FILTER_LABEL, Icons.DELETE_FILTER_ICON) {
		public void actionPerformed (ActionEvent e) {
			new DeleteFilterDialog();
		}
	};
	
	/**
	 * Find a file action
	 */
	public static final Action findAction = new AbstractAction(Labels.FIND_LABEL, Icons.FIND_ICON) {
		public void actionPerformed (ActionEvent e) {
			new FindFileDialog();
		}
	};
	
	/**
	 * Configuration action
	 */
	public static final Action configurationAction = new AbstractAction(Labels.CONFIGURATION_LABEL, Icons.CONFIGURATION_ICON) {
		public void actionPerformed (ActionEvent e) {
			new SetConfigDialog(Labels.CONFIGURATION_OF_JHOOVER_LABEL);
		}
	};
	
	/**
	 * Change the color action
	 */
	public static final Action colorsAction = new AbstractAction(Labels.COLORS_LABEL, Icons.COLORS_ICON) {
		public void actionPerformed (ActionEvent e) {
			JOptionPane.showMessageDialog(JHMainFrame.getInstance(), Labels.NOT_IMPLEMENTED_LABEL, Labels.NOT_IMPLEMENTED_LABEL, JOptionPane.ERROR_MESSAGE);
		}
	};
	
	/**
	 * Print the Help
	 */
	public static final Action helpAction = new AbstractAction (Labels.HELP_LABEL, Icons.HELP_ICON) {
		public void actionPerformed (ActionEvent e) {
			JOptionPane.showMessageDialog(JHMainFrame.getInstance(), Labels.NOT_IMPLEMENTED_LABEL, Labels.NOT_IMPLEMENTED_LABEL, JOptionPane.ERROR_MESSAGE);
		}
	};

	/**
	 * Print the About
	 */
	public static final Action aboutAction = new AbstractAction (Labels.ABOUT_LABEL, Icons.ABOUT_ICON) {
		public void actionPerformed (ActionEvent e) {
			JOptionPane.showMessageDialog(JHMainFrame.getInstance(), Labels.NOT_IMPLEMENTED_LABEL, Labels.NOT_IMPLEMENTED_LABEL, JOptionPane.ERROR_MESSAGE);
		}
	};

	
	/**
	 * Set visible or unvisible the toolbar
	 */
	public static final Action visibleToolBar = new AbstractAction () {
		public void actionPerformed (ActionEvent arg0) {
			final JHToolBar toolBar = JHMainFrame.getInstance().getJHToolBar ();
			if (!JHMainFrame.getInstance().getJHMenuBar().getToolBarItem().isSelected ()) {
				toolBar.setVisible(false);
			}
			else {
				toolBar.setVisible(true);
			}
		}
	};
	
	
	/**
	 * To stop the download
	 */
	private static void stopAction() {
		ArrayList<DownloadAndParseFile> threadList = DownloadManager.getInstance(0, 0, 0, null, null, null).getThreadList();
		//stop the downloadManager Thread
		JHoover.getInstance().getDownloadManager().interrupt();
		//stop all the Download Threads
		for (int i=0; i<threadList.size(); i++) {
			threadList.get(i).interrupt();
		}
		JOptionPane.showMessageDialog(JHMainFrame.getInstance(), Labels.DOWNLOAD_CANCELLED_LABEL, Labels.DOWNLOAD_STATUS_LABEL, JOptionPane.ERROR_MESSAGE);
	}
	
	/**
	 * To resume the download 
	 */
	private static void resumeAction() {
		//resume the downloadManager Thread
		JHoover.getInstance().getDownloadManager().setPauseStatus(false);
		JHoover.getInstance().getDownloadManager().interrupt();
		//resume all the Download Threads
		ArrayList<DownloadAndParseFile> threadList = DownloadManager.getInstance(0, 0, 0, null, null, null).getThreadList();
		for (int i=0; i<threadList.size(); i++) {
			synchronized (threadList.get(i)) {
				threadList.get(i).setPauseStatus(false);
				threadList.get(i).notify();
			}
		}
	}
	
	/**
	 * Raz the current jHoover project
	 */
	public static void initAction() {
		//init the discoveryPanel: remove the JTree
		JHMainPanel.initDiscoveryPanel();
		//init the detailled model: init the model and fire
		if (!DetailledModel.getInstance().getWebFiles().isEmpty()) {
			DetailledModel.getInstance().initModel();
		}
	}
}