import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.util.*;

import javax.swing.*;

public class DrawingBoard extends JFrame{
	
	public static void main(String args[]){
		new DrawingBoard();//runs the program
	}
	
	public DrawingBoard(){
		super("Austin's Paint App");//title for the application
		setExtendedState(MAXIMIZED_BOTH);//make jframe fullscreen
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		add(new PaintSurface());
		setVisible(true);
	}
	
	private class PaintSurface extends JComponent {
		
		ArrayList<Shape> shapes = new ArrayList<Shape>();
		Point startDrag, endDrag;
		
		public PaintSurface(){
			
			addMouseListener(new MouseAdapter(){
				
				public void mousePressed(MouseEvent e){
					startDrag = new Point(e.getX(), e.getY());//gets the xy coordinate when 
										  //mouse is pressed
					endDrag = startDrag;
					repaint();
				}
				
				public void mouseReleased(MouseEvent e){//when mouse is released, adds the rectangle 
									//made to the array of shapes for drawing later on
					Shape r = makeRectangle(startDrag.x, startDrag.y, e.getX(), e.getY());
					shapes.add(r);
					startDrag = null;
					endDrag = null;
					repaint();
				}
				
			});
			
			addMouseMotionListener(new MouseMotionAdapter(){
				
				public void mouseDragged(MouseEvent e){
					endDrag = new Point(e.getX(), e.getY());
					repaint();
				}
				
			});
			
		}
		
	
		
		//draw grid, then draw shapes
		public void paint(Graphics g){
			Graphics2D g2 = (Graphics2D)g;
			g2.setPaint(Color.LIGHT_GRAY);
			
			//lines 66-75 make the grid background for the jframe
			for(int i = 0; i < getSize().width; i+=20){
				Shape line = new Line2D.Float(i, 0, i, getSize().height);
				g2.draw(line);
			}
			
			for(int i = 0; i < getSize().width; i+=20){
				Shape line = new Line2D.Float(0, i, getSize().width, i);
				g2.draw(line);
			}
			
			//just some colors to make rectangles look pretty
			Color[] colors = {Color.RED, Color.BLUE, Color.GREEN, Color.YELLOW, Color.MAGENTA,
								Color.PINK, Color.white};
			
			int colorIndex = 0;
			
			//lines 84-89 paint the array of rectangles on the screen, changing colors each time
			for(Shape s : shapes){
				g2.setPaint(Color.black);
				g2.draw(s);
				g2.setPaint(colors[(colorIndex++)%colors.length]);
				g2.fill(s);
			}
			
			if(startDrag != null && endDrag != null){//the light gray temporary rectangle when dragging mouse
								//to create shape
				g2.setPaint(Color.LIGHT_GRAY);
				Shape r = makeRectangle(startDrag.x, startDrag.y, endDrag.x, endDrag.y);
				g2.draw(r);
			}
		}
	}
		
		
		//makes rectangle based on the minimum x and y values with height and width equal to absolute 
		//value of y's and x's
		public Rectangle2D.Float makeRectangle(int x1, int y1, int x2, int y2){
			int x = Math.min(x1, x2);
			int y = Math.min(y1, y2);
			int height = Math.abs(y1 - y2);
			int width = Math.abs(x1 - x2);
			return new Rectangle2D.Float(x, y, width, height);//returns the rectangle made with the above
		}
		
		
	}
	
	



