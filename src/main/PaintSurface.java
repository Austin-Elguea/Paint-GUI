package main;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Shape;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

import javax.swing.JComponent;

public class PaintSurface extends JComponent {
	
		// Implemented shapes.
		public enum ImplementedShape {
			Rectangle, Ellipse, Line;
		}
		
		private ArrayList<ColoredShape> shapes = new ArrayList<ColoredShape>();
		private Point startDrag, endDrag;
		private ImplementedShape currentShape;
		private Color color;
		private Color backgroundColor;
		
		public ArrayList<ColoredShape> getShapes() {
			return shapes;
		}
		
		public void clearShapes() {
			shapes = new ArrayList<ColoredShape>();
			repaint();
		}

		public ImplementedShape getCurrentShape() {
			return currentShape;
		}

		public void setCurrentShape(ImplementedShape currentShape) {
			this.currentShape = currentShape;
		}

		public PaintSurface(){
			
			
			currentShape = ImplementedShape.Rectangle;
			color = Color.RED;
			
			addMouseListener(new MouseAdapter() {
				
				public void mousePressed(MouseEvent e) {
					startDrag = new Point(e.getX(), e.getY());	// gets the xy coordinate when 
										  						// mouse is pressed
					endDrag = startDrag;
					repaint();
				}
				
				public void mouseReleased(MouseEvent e){//when mouse is released, adds the rectangle 
									//made to the array of shapes for drawing later on
					
					// No 0D or 1D shapes.
					if (startDrag.x == e.getX() || startDrag.y == e.getY()) return;
					
					switch (currentShape) {
					case Rectangle:
						Shape r = makeRectangle(startDrag.x, startDrag.y, e.getX(), e.getY());
						shapes.add(new ColoredShape(color, r));
						break;
						
					case Ellipse:
						Shape c = makeEllipse(startDrag.x, startDrag.y, e.getX(), e.getY());
						shapes.add(new ColoredShape(color, c));
						break;
						
					case Line:
						Shape l = makeLine(startDrag.x, startDrag.y, e.getX(), e.getY());
						shapes.add(new ColoredShape(color, l));
						break;
						
					}
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
		
		public PaintSurface(ImplementedShape currentShape) {
			this();
			this.currentShape = currentShape;
		}
		
		public PaintSurface(Color color) {
			this();
			this.color = color;
		}
		
		public PaintSurface(ImplementedShape currentShape, Color color) {
			this();
			this.currentShape = currentShape;
			this.color = color;
		}
		
		public void setCurrentBackgroundColor(Color c){
			this.backgroundColor = c;
		}
		
		public Color getCurrentBackgroundColor(){
			return backgroundColor;
		}
		
		
		// draw grid, then draw shapes
		public void paint(Graphics g){
			Graphics2D g2 = (Graphics2D)g;
			
			g2.setStroke(new BasicStroke(4)); //makes graphics more appealing
			g2.setPaint(Color.WHITE);
			
			Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
			int width = (int)screenSize.getWidth();
			int height = (int)screenSize.getHeight();
		
			Shape background = makeRectangle(0,0,width,height);
			g2.draw(background);
			g2.setPaint(getCurrentBackgroundColor());
			g2.fill(background);
			repaint();
						
			// lines 84-89 paint the array of rectangles on the screen, changing colors each time
			for(ColoredShape s : shapes) {
				g2.setPaint(s.getColor());
				g2.draw(s.getShape());
				g2.fill(s.getShape());
			}
			
			if(startDrag != null && endDrag != null){//the light gray temporary shape when dragging mouse
								//to create shape
				g2.setPaint(Color.LIGHT_GRAY);
				
				
				// You can add more shapes here.
				switch (currentShape) {
				case Rectangle:
					Shape r = makeRectangle(startDrag.x, startDrag.y, endDrag.x, endDrag.y);
					g2.draw(r);
					break;
				
				case Ellipse:
					Shape c = makeEllipse(startDrag.x, startDrag.y, endDrag.x, endDrag.y);
					g2.draw(c);
					break;
					
				case Line:
					Shape l = makeLine(startDrag.x, startDrag.y, endDrag.x, endDrag.y);
					g2.draw(l);
					break;
					
				}
			}
		}
		
		//makes rectangle based on the minimum x and y values with height and width equal to absolute 
		//value of y's and x's
		private Rectangle2D makeRectangle(int x1, int y1, int x2, int y2){
			int x = Math.min(x1, x2);
			int y = Math.min(y1, y2);
			int height = Math.abs(y1 - y2);
			int width = Math.abs(x1 - x2);
			return new Rectangle2D.Double(x, y, width, height);//returns the rectangle made with the above
		}
		
		//makes ellipse based on the minimum x and y values with height and width equal to absolute 
		//value of y's and x's
		private Ellipse2D makeEllipse(int x1, int y1, int x2, int y2){
			int x = Math.min(x1, x2);
			int y = Math.min(y1, y2);
			int height = Math.abs(y1 - y2);
			int width = Math.abs(x1 - x2);
			return new Ellipse2D.Double(x, y, width, height);//returns the ellipse made with the above
		}
		
		//draws line
		private Line2D makeLine(int x1, int y1, int x2, int y2){
			return new Line2D.Double(x1, y1, x2, y2);
		}
		
	}
