package main;

import javax.swing.JFrame;

public class DrawingBoard extends JFrame{
	
	private static String K_TITLE = "Austin's Paint App";
	
	public static void main(String args[]){
		DrawingBoard db = new DrawingBoard(K_TITLE);//runs the program
		db.start();
	}
	
	public DrawingBoard(String title){
		super(title); // title for the application
		setExtendedState(MAXIMIZED_BOTH); // make jframe fullscreen
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
	}
	
	public void start() {
		add(new PaintPanel());
		setVisible(true);
	}
		
}
	
	



