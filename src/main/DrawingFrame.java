package main;

import javax.swing.JFrame;

public class DrawingFrame extends JFrame{
	
	public static String DEFUALT_TITLE = "Austin's Paint App";
	
	public DrawingFrame(String title){
		super(title); // title for the application
		setExtendedState(MAXIMIZED_BOTH); // make jframe fullscreen
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public void load() {
		add(new PaintPanel());
		setVisible(true);
	}
		
}
