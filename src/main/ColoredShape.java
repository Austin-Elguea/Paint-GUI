package main;

import java.awt.Color;
import java.awt.Shape;

public class ColoredShape {
	
	/**
	 * Shape
	 */
	private Shape shape;
	
	/**
	 * Color
	 */
	private Color color;
	
	/**
	 * Shape getter.
	 * @return shape
	 */
	public Shape getShape() {
		return shape;
	}
	
	/**
	 * Shape setter.
	 * @param shape New shape
	 */
	public void setShape(Shape shape) {
		this.shape = shape;
	}
	
	/**
	 * Color getter.
	 * @return color
	 */
	public Color getColor() {
		return color;
	}
	
	/**
	 * Color setter.
	 * @param color New color
	 */
	public void setColor(Color color) {
		this.color = color;
	}
	
	/**
	 * Constructor.
	 * @param color Start color
	 * @param shape Start shape
	 */
	public ColoredShape(Color color, Shape shape) {
		this.color = color;
		this.shape = shape;
	}
	

}
