package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SpringLayout;

import main.PaintSurface.ImplementedShape;


public class PaintPanel extends JPanel {
	
	/**
	 * Main surface.
	 */
	private PaintSurface surface;
	
	/**
	 * Clear button.
	 */
	private JButton clearBtn;
	
	/**
	 * Undo button
	 */
	private JButton undoBtn;

	/**
	 * Shape choosing buttons.
	 */
	private JButton rectangleBtn, ellipseBtn, lineBtn;
	
	/**
	 * Labels.
	 */
	private JLabel backgroundColorLabel, shapeColorLabel;
	
	/**
	 * Color pickers.
	 */
	private JComboBox<String> backgroundColorPicker, shapeColorPicker;
	
	/**
	 * Map names to Color objects.
	 */
	HashMap<String, Color> colorMap;
	
	/**
	 * Only constructor.
	 */
	public PaintPanel() {
		super();
		
		
		surface = new PaintSurface();
		
		// Create buttons.
		lineBtn = new JButton("Line");
		clearBtn = new JButton("Clear");
		undoBtn = new JButton("Undo");
		ellipseBtn = new JButton("Oval");
		rectangleBtn = new JButton("Rectangle");
		
		// Make labels.
		Font colorLabelFont = new Font("Verdana", Font.BOLD, 16);
		backgroundColorLabel = new JLabel("Background Color: ");
		shapeColorLabel = new JLabel("Shape Color: ");
		backgroundColorLabel.setFont(colorLabelFont);
		shapeColorLabel.setFont(colorLabelFont);
		
		// Create background color chooser.	
		// Make a dictionary mapping the JComboBox values to a color object.
		colorMap = new HashMap<String, Color>();
		fillColors(colorMap);
		String[] colors = new String[colorMap.keySet().size()];
		
		int i = 0;
		for (String key: colorMap.keySet()) {
			colors[i++] = key;
		}
		
		backgroundColorPicker = new JComboBox<String>(colors);
		backgroundColorPicker.setSelectedItem("White");
		backgroundColorPicker.addItemListener(new ItemListener(){

			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange()==ItemEvent.SELECTED){
					surface.setBackgroundColor(colorMap.get((String)backgroundColorPicker.getSelectedItem()));
				}
			}			
			
		});
		
		//create shape color chooser
		shapeColorPicker = new JComboBox<String>(colors);
		shapeColorPicker.setSelectedItem("Red");
		surface.setShapeColor(colorMap.get((String)shapeColorPicker.getSelectedItem()));
		
		shapeColorPicker.addItemListener(new ItemListener(){

			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange()==ItemEvent.SELECTED){
					surface.setShapeColor(colorMap.get((String)shapeColorPicker.getSelectedItem()));
				}
			}
			
			
		});
		
		// Add action listeners to the buttons.
		
		clearBtn.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				surface.clearShapes();//clears shapes
				surface.clearBackground();//clears background color
				
				// Set color pickers back to white and red.
				backgroundColorPicker.setSelectedItem("White");
				shapeColorPicker.setSelectedItem("Red");
			}
			
		});
		
		undoBtn.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				surface.undoShapeAdd();
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
		
		
		this.addKeyListener(new KeyAdapter(){//if ctrl and z button pressed, undo

			public void keyPressed(KeyEvent e) {
				if((e.getKeyCode()==KeyEvent.VK_Z) && ((e.getModifiers() & ActionEvent.CTRL_MASK) ==ActionEvent.CTRL_MASK)){
					surface.undoShapeAdd();
				}
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
		add(undoBtn);
		add(ellipseBtn);
		add(surface);
		setFocusable(true);
		requestFocusInWindow();

	}
	
	/**
	 * Make the SpringLayout.
	 * @param layout SpringLayout to add the constraints to.
	 */
	private void makeLayout(SpringLayout layout) {
		
		
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
		
		layout.putConstraint(SpringLayout.WEST, undoBtn, 5, SpringLayout.EAST, clearBtn);
		layout.putConstraint(SpringLayout.NORTH, undoBtn, 5, SpringLayout.NORTH, this);
		
		layout.putConstraint(SpringLayout.WEST, surface, 0, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, surface, 0, SpringLayout.NORTH, this);
		layout.putConstraint(SpringLayout.EAST, surface, 0, SpringLayout.EAST, this);
		layout.putConstraint(SpringLayout.SOUTH, surface, 0, SpringLayout.SOUTH, this);
	}
	
	/**
	 * Fill colors into map.
	 * @param colorMap HashMap to fill.
	 */
	public void fillColors(HashMap<String, Color> colorMap) {
		colorMap.put("White", Color.WHITE);
		colorMap.put("Red", Color.RED);
		colorMap.put("Blue", Color.decode("#00B2EE"));
		colorMap.put("Green", Color.decode("#00EE00"));
		colorMap.put("Pink", Color.MAGENTA);
		colorMap.put("Yellow", Color.YELLOW);
		colorMap.put("Purple", new Color(100, 0, 200));
		colorMap.put("Black", Color.BLACK);
	}
}