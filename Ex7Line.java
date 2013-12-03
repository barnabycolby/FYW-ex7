package ex7;

import java.awt.Color;
import java.awt.Graphics;

public class Ex7Line extends Ex7DrawingElement {

	/**
	 * Constructor.
	 * 
	 * @param color
	 *            The color of the drawing element.
	 * @param left
	 *            The left co-ordinate of the element.
	 * @param top
	 *            The top co-ordinate of the element.
	 * @param width
	 *            The width of the element.
	 * @param height
	 *            The height of the element.
	 */
	protected Ex7Line(Color color, int left, int top, int width, int height) {
		super(color, left, top, width, height);
	}

	@Override
	public void draw(Graphics graphic) {
		// Sets colour
		super.draw(graphic);
		
		graphic.drawLine(this.getLeft(), this.getTop(),
				this.getLeft() + this.getWidth(),
				this.getTop() + this.getHeight());
	}
}
