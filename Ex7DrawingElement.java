package ex7;

import java.awt.Color;
import java.awt.Graphics;

public abstract class Ex7DrawingElement {
	private int left;
	private int top;
	private int width;
	private int height;
	private final Color drawingColor;

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
	protected Ex7DrawingElement(Color color, int left, int top, int width,
			int height) {
		this.setLeft(left);
		this.setTop(top);
		this.setWidth(width);
		this.setHeight(height);
		this.drawingColor = color;
	}

	/**
	 * Draws the object. Actually just sets the color because we don't have
	 * enough information to draw the element.
	 * 
	 * @param graphic The graphic to draw on.
	 */
	public void draw(Graphics graphic) {
		graphic.setColor(this.getDrawingColor());
	}

	/**
	 * Gets the height of the element.
	 * @return The height.
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * Sets the height of the element.
	 * @param height The new height.
	 */
	public void setHeight(int height) {
		this.height = height;
	}

	/**
	 * Gets the width of the element.
	 * @return The width.
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * Sets the width of the element.
	 * @param width The new width.
	 */
	public void setWidth(int width) {
		this.width = width;
	}

	/**
	 * Gets the top co-ordinate of the element.
	 * @return The top co-ordinate.
	 */
	public int getTop() {
		return top;
	}

	/**
	 * Sets the top co-ordinate of the element.
	 * @param top The new top co-ordinate.
	 */
	public void setTop(int top) {
		this.top = top;
	}

	/**
	 * Gets the left co-ordinate of the element.
	 * @return The left co-ordinate.
	 */
	public int getLeft() {
		return left;
	}

	/**
	 * Sets the left co-ordinate of the element.
	 * @param left The new left co-ordinate.
	 */
	public void setLeft(int left) {
		this.left = left;
	}

	/**
	 * Gets the colour of the element.
	 * @return The colour.
	 */
	public Color getDrawingColor() {
		return drawingColor;
	}

}
