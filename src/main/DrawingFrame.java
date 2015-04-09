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
		setSize(1000,700);//starting size is in middle and small
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setMaximumSize(new Dimension((PaintSurface.WIDTH+200), (PaintSurface.HEIGHT+200)));;//setting the maximum size that the frame can be
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
