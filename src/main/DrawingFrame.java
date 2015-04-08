package main;

import java.awt.Dimension;

import javax.swing.JFrame;

public class DrawingFrame extends JFrame{
	
	/**
	 * Default title for frame.
	 */
	public static String DEFUALT_TITLE = "Paint App";
	
	/**
	 * Constructor.
	 * @param title Starting title.
	 */
	public DrawingFrame(String title){
		super(title); // title for the application
		setExtendedState(MAXIMIZED_BOTH); // make jframe fullscreen
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setMinimumSize(new Dimension(1000,700));//setting the minimum size that the frame can be
		setLocationRelativeTo(null);

	}
	
	/**
	 * Load the GUI.
	 */
	public void load() {
		add(new PaintPanel());
		setVisible(true);
	}
		
}
