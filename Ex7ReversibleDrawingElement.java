package ex7;

import java.awt.Color;

public abstract class Ex7ReversibleDrawingElement extends Ex7DrawingElement {

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
	protected Ex7ReversibleDrawingElement(Color color, int left, int top, int width,
			int height) {
		super(color, left, top, width, height);
		// TODO Auto-generated constructor stub
	}

	@Override
	public int getLeft() {
		if (super.getWidth() < 0) {
			return super.getLeft() + super.getWidth();
		}

		return super.getLeft();
	}

	@Override
	public int getTop() {
		if (super.getHeight() < 0) {
			return super.getTop() + super.getHeight();
		}

		return super.getTop();
	}

	@Override
	public int getWidth() {
		return Math.abs(super.getWidth());
	}

	@Override
	public int getHeight() {
		return Math.abs(super.getHeight());
	}
}
