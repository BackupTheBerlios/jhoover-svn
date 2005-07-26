/**
 * jHoover - UMLV IR2
 * UI Project
 */
package fr.umlv.ir2.jhoover.gui;

import java.awt.event.ActionEvent;
import java.util.ArrayList;

import javax.swing.AbstractAction;
import javax.swing.Action;

import fr.umlv.ir2.jhoover.JHoover;
import fr.umlv.ir2.jhoover.gui.dialog.AddFilterDialog;
import fr.umlv.ir2.jhoover.gui.dialog.DeleteFilterDialog;
import fr.umlv.ir2.jhoover.gui.dialog.FindFileDialog;
import fr.umlv.ir2.jhoover.gui.dialog.ModifyFilterDialog;
import fr.umlv.ir2.jhoover.gui.dialog.RunConfigDialog;
import fr.umlv.ir2.jhoover.gui.dialog.SetConfigDialog;
import fr.umlv.ir2.jhoover.gui.tool.Icons;
import fr.umlv.ir2.jhoover.gui.tool.Labels;
import fr.umlv.ir2.jhoover.network.DownloadManager;

/**
 * @author Romain Papuchon
 *
 */
public final class ActionManager
{
	public static final Action newAction = new AbstractAction(Labels.NEW_LABEL, Icons.NEW_ICON) {
		public void actionPerformed (ActionEvent e) {
			new RunConfigDialog(Labels.RUN_JHOOVER_LABEL);
		}
	};

	public static final Action stopAction = new AbstractAction(Labels.STOP_LABEL, Icons.STOP_ICON) {
		public void actionPerformed (ActionEvent e) {
			//stop the downloadManager Thread
			JHoover.getDownloadManagerThread().interrupt();
			//stop all the Threads
			ArrayList<Thread> threadList = DownloadManager.getInstance(0, 0, 0, null, null).getThreadList();
			for (int i=0; i<threadList.size(); i++) {
				//TODO: voir si cela foncionne
				threadList.get(i).interrupt();
			}
		}
	};
	
	public static final Action pauseAction = new AbstractAction(Labels.PAUSE_LABEL, Icons.PAUSE_ICON) {
		public void actionPerformed (ActionEvent e) {			
			//stop the downloadManager Thread
//			try {
//				JHoover.getDownloadManagerThread().join();
//			} catch (InterruptedException e1) {
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//			}
//			//stop all the Threads
//			ArrayList<Thread> threadList = DownloadManager.getInstance(0, 0, 0, null, null).getThreadList();
//			for (int i=0; i<threadList.size(); i++) {
//				//TODO: voir si cela foncionne
//				try {
//					threadList.get(i).join();
//				} catch (InterruptedException e1) {
//					// TODO Auto-generated catch block
//					e1.printStackTrace();
//				}
//			}
		}
	};
	
	public static final Action resumeAction = new AbstractAction(Labels.RESUME_LABEL, Icons.RESUME_ICON) {
		public void actionPerformed (ActionEvent e) {			
			System.out.println("Resume action");
		}
	};
	
	public static final Action closeAction = new AbstractAction(Labels.CLOSE_LABEL, Icons.CLOSE_ICON) {
		public void actionPerformed (ActionEvent e) {			
			System.out.println("Close action");
		}
	};
	
	public static final Action exitAction = new AbstractAction(Labels.EXIT_LABEL, Icons.EXIT_ICON) {
		public void actionPerformed (ActionEvent e) {
			System.out.println("Exit action");
		}
	};

	public static final Action addFilterAction = new AbstractAction(Labels.ADD_FILTER_LABEL, Icons.ADD_FILTER_ICON) {
		public void actionPerformed (ActionEvent e) {
			new AddFilterDialog();
		}
	};
	
	public static final Action modifyFilterAction = new AbstractAction(Labels.MODIFY_FILTER_LABEL, Icons.MODIFY_FILTER_ICON) {
		public void actionPerformed (ActionEvent e) {
			new ModifyFilterDialog();
		}
	};
	
	public static final Action deleteFilterAction = new AbstractAction(Labels.DELETE_FILTER_LABEL, Icons.DELETE_FILTER_ICON) {
		public void actionPerformed (ActionEvent e) {
			new DeleteFilterDialog();
		}
	};
	
	public static final Action findAction = new AbstractAction(Labels.FIND_LABEL, Icons.FIND_ICON) {
		public void actionPerformed (ActionEvent e) {
			new FindFileDialog();
		}
	};
	
	public static final Action configurationAction = new AbstractAction(Labels.CONFIGURATION_LABEL, Icons.CONFIGURATION_ICON) {
		public void actionPerformed (ActionEvent e) {
			new SetConfigDialog(Labels.CONFIGURATION_OF_JHOOVER_LABEL);
		}
	};
	
	public static final Action colorsAction = new AbstractAction(Labels.COLORS_LABEL, Icons.COLORS_ICON) {
		public void actionPerformed (ActionEvent e) {
			System.out.println("Colors action");
		}
	};
	
	public static final Action helpAction = new AbstractAction (Labels.HELP_LABEL, Icons.HELP_ICON) {
		public void actionPerformed (ActionEvent e) {
			System.out.println("Help action");
		}
	};

	public static final Action aboutAction = new AbstractAction (Labels.ABOUT_LABEL, Icons.ABOUT_ICON) {
		public void actionPerformed (ActionEvent e) {
			System.out.println("About action");
		}
	};

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
}