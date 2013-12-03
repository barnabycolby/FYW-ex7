package ex7;

import java.awt.Graphics;
import java.util.ArrayList;

public class Ex7Drawing {
	private ArrayList<Ex7DrawingElement> elementList;
	private ArrayList<Ex7DrawingElement> deletedElementList;

	/**
	 * Adds a new drawing element to the drawing.
	 * @param element The new element to add.
	 */
	public void addDrawingElement(Ex7DrawingElement element) {
		this.getElementList().add(element);
	}

	/**
	 * Deletes the last element stored by the drawing.
	 */
	public void deleteElement() {
		if (this.getElementList().size() > 0) {
			Ex7DrawingElement deletedElement = this.getElementList().remove(
					this.getElementList().size() - 1);
			this.getDeletedElementList().add(deletedElement);
		}
	}

	/**
	 * Restores the most recently deleted element to the drawing.
	 */
	public void unDeleteElement() {
		if (this.getDeletedElementList().size() > 0) {
			this.addDrawingElement(this.getDeletedElementList().remove(
					this.getDeletedElementList().size() - 1));
		}
	}

	/**
	 * Draws the entire drawing onto a graphic.
	 * @param graphic The graphic to draw on.
	 */
	public void draw(Graphics graphic) {
		for (int i = 0; i < this.getElementList().size(); i++) {
			this.getElementList().get(i).draw(graphic);
		}
	}

	/**
	 * Gets the list of drawing elements stored by the drawing.
	 * @return An ArrayList of Ex7DrawingElements stored by the drawing.
	 */
	public ArrayList<Ex7DrawingElement> getElementList() {
		if (elementList == null) {
			this.elementList = new ArrayList<Ex7DrawingElement>();
		}

		return elementList;
	}

	/**
	 * Gets the list of deleted elements.
	 * @return The list of deleted elements.
	 */
	public ArrayList<Ex7DrawingElement> getDeletedElementList() {
		if (deletedElementList == null){
			this.deletedElementList = new ArrayList<Ex7DrawingElement>();
		}
		
		return deletedElementList;
	}

	/**
	 * Sets the list of deleted elements.
	 * @param deletedElementList The list of elements.
	 */
	public void setDeletedElementList(
			ArrayList<Ex7DrawingElement> deletedElementList) {
		this.deletedElementList = deletedElementList;
	}
}
