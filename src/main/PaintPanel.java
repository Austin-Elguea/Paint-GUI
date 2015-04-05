package main;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import main.PaintSurface.ImplementedShape;


public class PaintPanel extends JPanel {

	// Shape choosing buttons.
	private JButton rectangleBtn, ellipseBtn;
	
	private PaintSurface surface;
	
	public PaintPanel() {
		super();
		this.setLayout(new BorderLayout());
		
		surface = new PaintSurface();
		add(surface);
		
		// Create buttons.
		ellipseBtn = new JButton("Ellipse");
		rectangleBtn = new JButton("Rectangle");
		
		// Add action listeners to the buttons.
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
		
		// Add buttons. Probably should make this a bit prettier than
		// one button on the left and one on the right, but it works.
		add(BorderLayout.WEST, rectangleBtn);
		add(BorderLayout.EAST, ellipseBtn);
	}
}
