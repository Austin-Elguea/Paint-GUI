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
	
		/**
		 * Implemented shapes.
		 */
		public enum ImplementedShape {//all shapes; add future shapes here
			Rectangle, Ellipse, Line, Text;
		}
		
		/**
		 * ArrayList to store current shapes.
		 */
		private ArrayList<ColoredShape> shapes = new ArrayList<ColoredShape>();
		
		/**
		 * Start and end drag points.
		 */
		private Point startDrag, endDrag;
		
		/**
		 * Stores current shape.
		 */
		private ImplementedShape currentShape;
		
		/**
		 * Current shape color.
		 */
		private Color shapeColor;
		
		/**
		 * Current background color.
		 */
		private Color backgroundColor;
		
		/**
		 * Shapes getter.
		 * @return All shapes.
		 */
		public ArrayList<ColoredShape> getShapes() {
			return shapes;
		}
		
		/**
		 * Clears background.
		 */
		public void clearBackground(){//clears background of color
			setBackgroundColor(Color.WHITE);
		}
		
		/**
		 * Clears shapes.
		 */
		public void clearShapes() {//erases shapes from paintsurface
			shapes = new ArrayList<ColoredShape>();
			repaint();
		}
		
		/**
		 * Removes the last added shape.
		 */
		public void undoShapeAdd() {
			shapes.remove(shapes.size() - 1);
		}

		/**
		 * Current shape getter.
		 * @return current shape.
		 */
		public ImplementedShape getCurrentShape() {//returns current shape
			return currentShape;
		}

		/**
		 * Current shape setter.
		 * @param currentShape new shape.
		 */
		public void setCurrentShape(ImplementedShape currentShape) {//sets the current shape to selected one via button
			this.currentShape = currentShape;
		}
		
		/**
		 * Background color setter.
		 * @param c new color.
		 */
		public void setBackgroundColor(Color c){//sets the background color
			this.backgroundColor = c;
		}
		
		/**
		 * Shape color setter.
		 * @param c new color.
		 */
		public void setShapeColor(Color c){//sets the background color
			this.shapeColor = c;
		}
		
		/**
		 * Constructor.		
		 */
		public PaintSurface(){
			
			
			currentShape = ImplementedShape.Rectangle;
			
			addMouseListener(new MouseAdapter() {
				
				public void mousePressed(MouseEvent e) {
					startDrag = new Point(e.getX(), e.getY());	// gets the xy coordinate when 
										  		// mouse is pressed
					endDrag = startDrag;
					repaint();
				}
				
				public void mouseReleased(MouseEvent e){//when mouse is released, adds the rectangle 
									//made to the array of shapes for drawing later on
					
					// No 1D shapes.
					if (startDrag.x == e.getX() && startDrag.y == e.getY()) {
						startDrag = null;
						return;
					}
					
					Shape s;
					
					switch (currentShape) {
					case Rectangle://if rectangle is selected
						s = makeRectangle(startDrag.x, startDrag.y, e.getX(), e.getY());
						shapes.add(new ColoredShape(shapeColor, s));
						break;
						
					case Ellipse://if ellipse is selected
						s = makeEllipse(startDrag.x, startDrag.y, e.getX(), e.getY());
						shapes.add(new ColoredShape(shapeColor, s));
						break;
						
					case Line://if line is selected
						s = makeLine(startDrag.x, startDrag.y, e.getX(), e.getY());
						shapes.add(new ColoredShape(shapeColor, s));
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
		
		/**
		 * Helper constructor.
		 * @param currentShape shape to start with.
		 */
		public PaintSurface(ImplementedShape currentShape) {
			this();
			this.currentShape = currentShape;
		}
		
		/**
		 * Helper constructor.
		 * @param color color to start with.
		 */
		public PaintSurface(Color color) {
			this();
			this.shapeColor = color;
		}
		
		/**
		 * Helper constructor.
		 * @param currentShape shape to start with.
		 * @param color color to start with.
		 */
		public PaintSurface(ImplementedShape currentShape, Color color) {
			this(currentShape);
			this.shapeColor = color;
		}		
		
		/**
		 * Draw shapes.
		 */
		public void paint(Graphics g){
			Graphics2D g2 = (Graphics2D)g;
			
			g2.setStroke(new BasicStroke(4)); //makes graphics more appealing.
			g2.setPaint(Color.WHITE);
			
			Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();//get the dimensions of the screen.
			int width = (int)screenSize.getWidth();
			int height = (int)screenSize.getHeight();
		
			Shape background = makeRectangle(0,0,(width+200),(height+200));//make rectangle that represents the background.
			
			g2.draw(background);//draw background
			
			g2.setPaint(backgroundColor); // NOTE: You can use the variable background color here instead of
											//  	calling the getBackgroundColor() method bc it is within the class.
			g2.fill(background);
			repaint();//every time new color is selected, repaint the background
						
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
				
				Shape s;
				
				switch (currentShape) {
				case Rectangle://if rectangle is selected
					s = makeRectangle(startDrag.x, startDrag.y, endDrag.x, endDrag.y);
					g2.draw(s);
					break;
				
				case Ellipse://if ellipse is selected
					s = makeEllipse(startDrag.x, startDrag.y, endDrag.x, endDrag.y);
					g2.draw(s);
					break;
					
				case Line://if line is selected
					s = makeLine(startDrag.x, startDrag.y, endDrag.x, endDrag.y);
					g2.draw(s);
					break;
					
				}
			}
		}
		
		/**
		 * makes rectangle based on the minimum x and y values with height and width equal to absolute 
		 * value of y's and x's
		 * @param x1
		 * @param y1
		 * @param x2
		 * @param y2
		 * @return
		 */
		private Rectangle2D makeRectangle(int x1, int y1, int x2, int y2){
			int x = Math.min(x1, x2);
			int y = Math.min(y1, y2);
			int height = Math.abs(y1 - y2);
			int width = Math.abs(x1 - x2);
			return new Rectangle2D.Double(x, y, width, height);//returns the rectangle made with the above
		}
		
		/**
		 * makes ellipse based on the minimum x and y values with height and width equal to absolute 
		 * value of y's and x's
		 * @param x1
		 * @param y1
		 * @param x2
		 * @param y2
		 * @return
		 */
		private Ellipse2D makeEllipse(int x1, int y1, int x2, int y2){
			int x = Math.min(x1, x2);
			int y = Math.min(y1, y2);
			int height = Math.abs(y1 - y2);
			int width = Math.abs(x1 - x2);
			return new Ellipse2D.Double(x, y, width, height);//returns the ellipse made with the above
		}
		
		/**
		 * draws line based on starting x and y values and ending x and y values (from dragging mouse)
		 * @param x1
		 * @param y1
		 * @param x2
		 * @param y2
		 * @return
		 */
		private Line2D makeLine(int x1, int y1, int x2, int y2){
			return new Line2D.Double(x1, y1, x2, y2);
		}
		
		
	}