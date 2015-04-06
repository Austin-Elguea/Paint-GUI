package main;

import javax.swing.JFrame;

public class DrawingFrame extends JFrame{
	
	private static String K_TITLE = "Austin's Paint App";
	
	public static void main(String args[]){
		DrawingFrame db = new DrawingFrame(K_TITLE);//runs the program
		db.start();
	}
	
	public DrawingFrame(String title){
		super(title); // title for the application
		setExtendedState(MAXIMIZED_BOTH); // make jframe fullscreen
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public void start() {
		add(new PaintPanel());
		setVisible(true);
	}
		
}
