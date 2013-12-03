package ex7;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class Ex7MouseListener implements MouseListener, MouseMotionListener {

	private final Ex7JFrame frame;
	private Ex7DrawingElement currentElement;
	private int startx;
	private int starty;
	private Graphics graphic;

	/**
	 * Constructor.
	 * @param frame The frame that uses the mouse listener.
	 */
	public Ex7MouseListener(Ex7JFrame frame) {
		this.frame = frame;
	}

	@Override
	public void mousePressed(MouseEvent event) {
		// Store the current mouse position
		this.setStartx(event.getX());
		this.setStarty(event.getY());

		// Drawing element type based on Drawing mode selection
		createNewDrawingElement();

		this.getFrame().getPanel().getDrawing()
				.addDrawingElement(this.getCurrentElement());

		// Avoids temporarily erasing old shapes
		this.refreshGraphic();

		// Draw the element so that it can get erased by the mouseDragged
		// function
		this.getCurrentElement().draw(this.getGraphic());
	}

	@Override
	public void mouseDragged(MouseEvent event) {
		// Erase the old drawing
		this.getGraphic()
				.setXORMode(this.getFrame().getPanel().getBackground());

		this.getCurrentElement().draw(this.getGraphic());

		int currentx = event.getX();
		int currenty = event.getY();

		int newWidth = currentx - this.getStartx();
		int newHeight = currenty - this.getStarty();

		this.getCurrentElement().setWidth(newWidth);
		this.getCurrentElement().setHeight(newHeight);

		// Draw the new drawing
		this.getCurrentElement().draw(this.getGraphic());

		// Not allowed to do this :(
		// this.getFrame().getPanel().repaint();
	}

	private void createNewDrawingElement() {
		if (this.getFrame().getDrawingMode() == Ex7DrawingMode.LINE) {
			this.setCurrentElement(new Ex7Line(this.getFrame()
					.getDrawingColour(), this.getStartx(), this.getStarty(), 0,
					0));
		} else if (this.getFrame().getDrawingMode() == Ex7DrawingMode.ELLIPSE) {
			this.setCurrentElement(new Ex7Ellipse(this.getFrame()
					.getDrawingColour(), this.getStartx(), this.getStarty(), 0,
					0));
		} else if (this.getFrame().getDrawingMode() == Ex7DrawingMode.RECTANGLE) {
			this.setCurrentElement(new Ex7Rectangle(this.getFrame()
					.getDrawingColour(), this.getStartx(), this.getStarty(), 0,
					0));
		}
	}

	private void refreshGraphic() {
		// Get a new graphic
		this.graphic = this.getFrame().getPanel().getGraphics();

	}

	@Override
	public void mouseReleased(MouseEvent event) {
		this.getFrame().getPanel().repaint();

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	/**
	 * Gets the frame using the mouse listener.
	 * @return The frame.
	 */
	public Ex7JFrame getFrame() {
		return frame;
	}

	/**
	 * Gets the current element being drawn by the frame.
	 * @return The current element.
	 */
	public Ex7DrawingElement getCurrentElement() {
		return currentElement;
	}

	/**
	 * Sets the current element being drawn by the frame.
	 * @param currentElement The new current element.
	 */
	public void setCurrentElement(Ex7DrawingElement currentElement) {
		this.currentElement = currentElement;
	}

	/**
	 * Gets the starting x co-ordinate of the current element.
	 */
	public int getStartx() {
		return startx;
	}

	/**
	 * Sets the starting x co-ordinate of the current element.
	 * @param startx The new starting x co-ordinate.
	 */
	public void setStartx(int startx) {
		this.startx = startx;
	}

	/**
	 * Gets the starting y co-ordinate.
	 * @return
	 */
	public int getStarty() {
		return starty;
	}

	/**
	 * Sets the starting y co-ordinate.
	 * @param starty The new starting y co-ordinate.
	 */
	public void setStarty(int starty) {
		this.starty = starty;
	}

	/**
	 * Gets the graphic to draw on.
	 * @return The graphic
	 */
	public Graphics getGraphic() {
		return graphic;
	}

}
