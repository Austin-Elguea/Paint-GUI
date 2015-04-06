package main;

import java.awt.Color;
import java.awt.Shape;

public class ColoredShape {
	
	private Shape shape;
	private Color color;
	
	public Shape getShape() {
		return shape;
	}
	public void setShape(Shape shape) {
		this.shape = shape;
	}
	public Color getColor() {
		return color;
	}
	public void setColor(Color color) {
		this.color = color;
	}
	
	public ColoredShape(Color color, Shape shape) {
		this.color = color;
		this.shape = shape;
	}
	

}
