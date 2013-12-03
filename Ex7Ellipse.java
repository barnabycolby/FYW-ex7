package ex7;

import java.awt.Color;
import java.awt.Graphics;

public class Ex7Ellipse extends Ex7ReversibleDrawingElement {

	/**
	 * Constructor.
	 * @param color The color of the element.
	 * @param left The left co-ordinate of the element.
	 * @param top The top co-ordinate of the element.
	 * @param width The width of the element.
	 * @param height The height of the element.
	 */
	protected Ex7Ellipse(Color color, int left, int top, int width, int height) {
		super(color, left, top, width, height);
		// TODO Auto-generated constructor stub
	}

	@Override
	/**
	 * Draws the element on the graphic.
	 */
	public void draw(Graphics graphic) {
		// Sets colour
		super.draw(graphic);
		
		graphic.drawOval(this.getLeft(), this.getTop(), this.getWidth(),
				this.getHeight());
	}

}
