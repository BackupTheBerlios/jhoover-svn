package fr.umlv.ir2.jhoover;

import java.awt.Dimension;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import javax.swing.JFrame;

/** 
 * Main Class
 * 
 * @author Romain Papuchon 
 */
public class JHoover {

	/**
	 * Main class
	 * @param args
	 */
	public static void main(String[] args) {
		
		
		
		URL url = null;
		try {
			url = new URL("http://www.google.fr");
		} catch (MalformedURLException e4) {
			// TODO Auto-generated catch block
			e4.printStackTrace();
		}
		URLConnection connection = null;
		try {
			connection = url.openConnection();
		} catch (IOException e3) {
			// TODO Auto-generated catch block
			e3.printStackTrace();
		}
		try {
			connection.connect();
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}

		// Recuperation des données
		InputStream is = null;
		try {
			is = connection.getInputStream();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		// create the fileoutputstream to write the index file
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream("C:/temp/google.html");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		byte[] buffer = new byte[4096];
		
		while (true) {
			int count = 0;
			try {
				count = is.read(buffer);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (count > 0) {
				// write out the data buffer (only the #bytes read)
				try {
					fos.write(buffer, 0, count);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {			
				// we are done reading bytes
				break;
			}
		}
		/*
		JFrame frame = new JFrame("JHoover -- The Papuch's Web Hoover");
		
		//initialisation of the Frame
		initFrame(frame);
		
		frame.setVisible(true);
		*/
		
	}

	private static void initFrame(JFrame frame) {
		frame.setSize(new Dimension(800, 600));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
