package ex7;

import java.awt.Graphics;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Ex7JPanel extends JPanel {
	private Ex7Drawing drawing;

	/**
	 * Constructor.
	 */
	public Ex7JPanel(){
		this.drawing = new Ex7Drawing();
		this.setDrawing(this.drawing);
	}

	/**
	 * Gets the Ex7Drawing containing the drawing.
	 * @return The drawing.
	 */
	public Ex7Drawing getDrawing() {
		return drawing;
	}

	/**
	 * Sets the Ex7Drawing containing the drawing.
	 * @param drawing The new drawing.
	 */
	public void setDrawing(Ex7Drawing drawing) {
		this.drawing = drawing;
	}
	
	@Override
	public void paintComponent(Graphics graphic){
		super.paintComponent(graphic);
		
		// Ensure that our graphics get drawn
		this.getDrawing().draw(graphic);
	}
}
