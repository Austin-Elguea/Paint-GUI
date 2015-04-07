package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SpringLayout;

import main.PaintSurface.ImplementedShape;


public class PaintPanel extends JPanel {

	// Shape choosing buttons.
	private JButton rectangleBtn, ellipseBtn, lineBtn, clearBtn;
	
	private JLabel backgroundColorLabel, shapeColorLabel;
	
	private PaintSurface surface;
	
	private JComboBox backgroundColorPicker, shapeColorPicker;
	
	public PaintPanel() {
		super();
		
		surface = new PaintSurface();
		
		// Create buttons.
		lineBtn = new JButton("Line");
		clearBtn = new JButton("Clear");
		ellipseBtn = new JButton("Oval");
		rectangleBtn = new JButton("Rectangle");
		
		backgroundColorLabel = new JLabel("Background Color: ");
		backgroundColorLabel.setFont(new Font("Verdana", Font.BOLD, 16));
		
		
		//create background color chooser
		String[] colorsNames = {"White","Red", "Blue", "Green", "Pink"};
		final Color[] colors = {Color.WHITE,Color.RED, Color.BLUE, Color.GREEN, Color.pink};
		
		backgroundColorPicker = new JComboBox<String>(colorsNames);
		backgroundColorPicker.addItemListener(new ItemListener(){

			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange()==ItemEvent.SELECTED){
					surface.setBackgroundColor(colors[backgroundColorPicker.getSelectedIndex()]);
				}
			}
			
			
		});
		
		//create shape color chooser
		shapeColorPicker = new JComboBox<String>(colorsNames);
		shapeColorLabel = new JLabel("Shape Color: ");
		
		shapeColorPicker.addItemListener(new ItemListener(){

			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange()==ItemEvent.SELECTED){
					surface.setShapeColor(colors[shapeColorPicker.getSelectedIndex()]);
				}
			}
			
			
		});
		
		// Add action listeners to the buttons.
		
		clearBtn.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				surface.clearShapes();//clears shapes
				surface.clearBackground();//clears background color
			}
			
		});
		
		//depending on which button is pressed, selected shape will be used for drawing
		ellipseBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				surface.setCurrentShape(ImplementedShape.Ellipse);
			}
		});
		
		rectangleBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				surface.setCurrentShape(ImplementedShape.Rectangle);
			}
		});
		
		lineBtn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				surface.setCurrentShape(ImplementedShape.Line);
			}
		});
		
		// Make layout.
		SpringLayout layout = new SpringLayout();

		makeLayout(layout);

		this.setLayout(layout);
		
		
		// Add components.
		add(shapeColorLabel);
		add(shapeColorPicker);
		add(backgroundColorLabel);
		add(backgroundColorPicker);
		add(lineBtn);
		add(rectangleBtn);
		add(clearBtn);
		add(ellipseBtn);
		add(surface);

	}
	
	public void makeLayout(SpringLayout layout) {
		
		
		//layout configuration below for components
		
		layout.putConstraint(SpringLayout.WEST, rectangleBtn, 5, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, rectangleBtn, 5, SpringLayout.NORTH, this);
		
		layout.putConstraint(SpringLayout.WEST, ellipseBtn, 5, SpringLayout.EAST, rectangleBtn);
		layout.putConstraint(SpringLayout.NORTH, ellipseBtn, 5, SpringLayout.NORTH, this);
		
		layout.putConstraint(SpringLayout.WEST, lineBtn, 5, SpringLayout.EAST, ellipseBtn);
		layout.putConstraint(SpringLayout.NORTH, lineBtn, 5, SpringLayout.NORTH, this);
		

		layout.putConstraint(SpringLayout.WEST, backgroundColorLabel, 5, SpringLayout.EAST, lineBtn);
		layout.putConstraint(SpringLayout.NORTH, backgroundColorLabel, 5, SpringLayout.NORTH, this);
		
		
		layout.putConstraint(SpringLayout.WEST, backgroundColorPicker, 5, SpringLayout.EAST, backgroundColorLabel);
		layout.putConstraint(SpringLayout.NORTH,backgroundColorPicker, 5, SpringLayout.NORTH, this);
		

		layout.putConstraint(SpringLayout.WEST, shapeColorLabel, 5, SpringLayout.EAST, backgroundColorPicker);
		layout.putConstraint(SpringLayout.NORTH, shapeColorLabel, 5, SpringLayout.NORTH, this);
		
		layout.putConstraint(SpringLayout.WEST, shapeColorPicker, 5, SpringLayout.EAST, shapeColorLabel);
		layout.putConstraint(SpringLayout.NORTH, shapeColorPicker, 5, SpringLayout.NORTH, this);
		

		layout.putConstraint(SpringLayout.WEST, clearBtn, 5, SpringLayout.EAST, shapeColorPicker);
		layout.putConstraint(SpringLayout.NORTH, clearBtn, 5, SpringLayout.NORTH, this);
		
		layout.putConstraint(SpringLayout.WEST, surface, 0, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, surface, 0, SpringLayout.NORTH, this);
		layout.putConstraint(SpringLayout.EAST, surface, 0, SpringLayout.EAST, this);
		layout.putConstraint(SpringLayout.SOUTH, surface, 0, SpringLayout.SOUTH, this);
	}
}