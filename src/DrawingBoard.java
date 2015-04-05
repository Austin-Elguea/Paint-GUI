import javax.swing.JFrame;

public class DrawingBoard extends JFrame{
	
	public static void main(String args[]){
		DrawingBoard db = new DrawingBoard();//runs the program
		db.start();
	}
	
	public DrawingBoard(){
		super("Austin's Paint App");//title for the application
		setExtendedState(MAXIMIZED_BOTH);//make jframe fullscreen
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
	}
	
	public void start() {
		add(new PaintSurface());
		setVisible(true);
	}
		
}
	
	



