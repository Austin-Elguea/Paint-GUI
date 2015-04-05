package main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SpringLayout;

import main.PaintSurface.ImplementedShape;


public class PaintPanel extends JPanel {

	// Shape choosing buttons.
	private JButton rectangleBtn, ellipseBtn;
	
	// Clear button.
	private JButton clearBtn;
	
	private PaintSurface surface;
	
	public PaintPanel() {
		super();
		
		surface = new PaintSurface();
		
		// Create buttons.
		clearBtn = new JButton("Clear");
		ellipseBtn = new JButton("Ellipse");
		rectangleBtn = new JButton("Rectangle");
		
		// Add action listeners to the buttons.
		
		clearBtn.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				surface.clearShapes();
			}
			
		});
		
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
		
		// Make layout.
		SpringLayout layout = new SpringLayout();
		
		layout.putConstraint(SpringLayout.WEST, rectangleBtn, 5, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, rectangleBtn, 5, SpringLayout.NORTH, this);
		
		layout.putConstraint(SpringLayout.WEST, ellipseBtn, 5, SpringLayout.EAST, rectangleBtn);
		layout.putConstraint(SpringLayout.NORTH, ellipseBtn, 5, SpringLayout.NORTH, this);
		
		layout.putConstraint(SpringLayout.WEST, clearBtn, 5, SpringLayout.EAST, ellipseBtn);
		layout.putConstraint(SpringLayout.NORTH, clearBtn, 5, SpringLayout.NORTH, this);
		
		layout.putConstraint(SpringLayout.WEST, surface, 0, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, surface, 0, SpringLayout.NORTH, this);
		layout.putConstraint(SpringLayout.EAST, surface, 0, SpringLayout.EAST, this);
		layout.putConstraint(SpringLayout.SOUTH, surface, 0, SpringLayout.SOUTH, this);

		this.setLayout(layout);
		
		// Add buttons.
		add(rectangleBtn);
		add(clearBtn);
		add(ellipseBtn);
		add(surface);

	}
}
