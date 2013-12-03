package ex7;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class Ex7ButtonListener implements ActionListener {

	private final Ex7JFrame frame;

	/**
	 * Constructor
	 * 
	 * @param frame
	 *            The Ex7JFrame containing the buttons which are using this
	 *            listener.
	 */
	public Ex7ButtonListener(Ex7JFrame frame) {
		this.frame = frame;
	}

	@Override
	/**
	 * Overrides the action performed method of ActionListener
	 */
	public void actionPerformed(ActionEvent event) {
		if (event.getSource() instanceof JButton) {
			JButton source = (JButton) event.getSource();

			if (source.getActionCommand().equals("Load")) {
				try {
					this.loadDrawing();
				} catch (Ex7InvalidFileException e) {
					JOptionPane.showMessageDialog(this.getFrame(),
							"File was invalid.");
				}
			} else if (source.getActionCommand().equals("Save")) {
				this.saveDrawing();
			} else if (source.getActionCommand().equals("Undo")) {
				this.getFrame().getPanel().getDrawing().deleteElement();
				this.getFrame().getPanel().repaint();
			} else if (source.getActionCommand().equals("Redo")) {
				this.getFrame().getPanel().getDrawing().unDeleteElement();
				this.getFrame().getPanel().repaint();
			} else if (source.getActionCommand().equals("Line")) {
				this.getFrame().setDrawingMode(Ex7DrawingMode.LINE);
			} else if (source.getActionCommand().equals("Ellipse")) {
				this.getFrame().setDrawingMode(Ex7DrawingMode.ELLIPSE);
			} else if (source.getActionCommand().equals("Rectangle")) {
				this.getFrame().setDrawingMode(Ex7DrawingMode.RECTANGLE);
			} else if (source.getActionCommand().equals("Colour")) {
				Color newColor = JColorChooser.showDialog(this.getFrame(),
						"Choose a new colour", this.getFrame()
								.getDrawingColour());

				if (newColor != null) {
					this.getFrame().setDrawingColour(newColor);
				}
			} else if (source.getActionCommand().equals("Exit")) {
				System.exit(0);
			}
		}
	}

	private void loadDrawing() throws Ex7InvalidFileException {
		// Show a filename chooser
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setFileFilter(new Ex7FileFilterLoad());
		if (fileChooser.showOpenDialog(this.getFrame()) != JFileChooser.APPROVE_OPTION) {
			return;
		}

		try {
			File file = fileChooser.getSelectedFile();
			BufferedReader br = new BufferedReader(new FileReader(file));
			String line = br.readLine();

			Ex7Drawing newDrawing = new Ex7Drawing();

			if (line == null || !line.equals("<Ex7Drawing>")) {
				br.close();
				throw new Ex7InvalidFileException();
			}

			while ((line = br.readLine()) != null) {
				Ex7DrawingElementType type;
				int left = 0, top = 0, width = 0, height = 0, color = 0;

				if (line.equals("</Ex7Drawing>")) {
					break;
				}

				if (line.equals("<Ex7Line>")) {
					type = Ex7DrawingElementType.LINE;
				} else if (line.equals("<Ex7Rectangle>")) {
					type = Ex7DrawingElementType.RECTANGLE;
				} else if (line.equals("<Ex7Ellipse>")) {
					type = Ex7DrawingElementType.ELLIPSE;
				} else {
					br.close();
					throw new Ex7InvalidFileException();
				}

				String[] lines = new String[5];
				for (int i = 0; i < 5; i++) {
					lines[i] = br.readLine();

					if (lines[i] == null) {
						br.close();
						throw new Ex7InvalidFileException();
					}
				}

				String tag = null;
				int value = 0;

				tag = this.getTag(lines[0]);
				value = this.getValue(lines[0]);

				if (tag == null) {
					br.close();
					throw new Ex7InvalidFileException();
				}

				if (!tag.equals("left")) {
					br.close();
					throw new Ex7InvalidFileException();
				}
				left = value;

				tag = this.getTag(lines[1]);
				value = this.getValue(lines[1]);
				if (!tag.equals("top")) {
					br.close();
					throw new Ex7InvalidFileException();
				}
				top = value;

				tag = this.getTag(lines[2]);
				value = this.getValue(lines[2]);
				if (!tag.equals("width")) {
					br.close();
					throw new Ex7InvalidFileException();
				}
				width = value;

				tag = this.getTag(lines[3]);
				value = this.getValue(lines[3]);
				if (!tag.equals("height")) {
					br.close();
					throw new Ex7InvalidFileException();
				}
				height = value;

				tag = this.getTag(lines[4]);
				value = this.getValue(lines[4]);
				if (!tag.equals("color")) {
					br.close();
					throw new Ex7InvalidFileException();
				}
				color = value;

				// Next line should equal the end tag of the tag type
				line = br.readLine();
				if (!line.equals(this.getClosingTagString(type))) {
					br.close();
					throw new Ex7InvalidFileException();
				}

				if (type == Ex7DrawingElementType.LINE) {
					newDrawing.addDrawingElement(new Ex7Line(new Color(color),
							left, top, width, height));
				} else if (type == Ex7DrawingElementType.RECTANGLE) {
					newDrawing.addDrawingElement(new Ex7Rectangle(new Color(
							color), left, top, width, height));
				} else if (type == Ex7DrawingElementType.ELLIPSE) {
					newDrawing.addDrawingElement(new Ex7Ellipse(
							new Color(color), left, top, width, height));
				}

				br.close();
			}

			this.getFrame().getPanel().setDrawing(newDrawing);
			this.getFrame().getPanel().repaint();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private String getClosingTagString(Ex7DrawingElementType type) {
		if (type == Ex7DrawingElementType.LINE) {
			return "</Ex7Line>";
		}

		if (type == Ex7DrawingElementType.RECTANGLE) {
			return "</Ex7Rectangle>";
		}

		if (type == Ex7DrawingElementType.ELLIPSE) {
			return "</Ex7Ellipse>";
		}

		return null;
	}

	private String getTag(String line) throws Ex7InvalidFileException {
		int openingTagStart = line.indexOf("<");
		int openingTagEnd = line.indexOf(">");
		int closingTagStart = line.indexOf("</");
		int closingTagEnd = line.indexOf(">", openingTagEnd + 1);

		if (openingTagStart == -1 || openingTagEnd == -1
				|| closingTagStart == -1 || closingTagEnd == -1) {
			throw new Ex7InvalidFileException();
		}

		return line.substring(openingTagStart + 1, openingTagEnd);
	}

	private int getValue(String line) throws Ex7InvalidFileException {
		int openingTagStart = line.indexOf("<");
		int openingTagEnd = line.indexOf(">");
		int closingTagStart = line.indexOf("</");
		int closingTagEnd = line.indexOf(">", openingTagEnd + 1);

		if (openingTagStart == -1 || openingTagEnd == -1
				|| closingTagStart == -1 || closingTagEnd == -1) {
			throw new Ex7InvalidFileException();
		}

		return Integer.parseInt(line.substring(openingTagEnd + 1,
				closingTagStart));
	}

	private void saveDrawing() {
		// Show a filename chooser
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setFileFilter(new Ex7FileFilterSave());
		if (fileChooser.showSaveDialog(this.getFrame()) != JFileChooser.APPROVE_OPTION) {
			return;
		}

		File file = fileChooser.getSelectedFile();
		BufferedWriter bw;
		try {
			bw = new BufferedWriter(new FileWriter(file));

			if (file.getName().toLowerCase().endsWith(".ex7")) {
				this.saveEx7(bw);
			} else if (file.getName().toLowerCase().endsWith(".png")) {
				this.savePng(file);
			} else if (file.getName().toLowerCase().endsWith(".bmp")) {
				// BROKEN //
				this.saveBmp(file);
				// BROKEN //
			} else {
				this.saveEx7(bw);
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void savePng(File file) throws IOException {
		BufferedImage bi = new BufferedImage(this.getFrame().getPanel()
				.getSize().width, this.getFrame().getPanel().getSize().height,
				BufferedImage.TYPE_INT_ARGB);
		Graphics graphic = bi.createGraphics();
		this.getFrame().getPanel().paint(graphic);
		graphic.dispose();
		ImageIO.write(bi, "png", file);
	}

	private void saveBmp(File file) throws IOException {
		BufferedImage bi = new BufferedImage(this.getFrame().getPanel()
				.getSize().width, this.getFrame().getPanel().getSize().height,
				BufferedImage.TYPE_INT_ARGB);
		Graphics graphic = bi.createGraphics();
		this.getFrame().getPanel().paint(graphic);
		graphic.dispose();
		ImageIO.write(bi, "BMP", file);
	}

	private void saveEx7(BufferedWriter bw) {
		try {
			ArrayList<Ex7DrawingElement> drawingElements = this.getFrame()
					.getPanel().getDrawing().getElementList();

			writeOpeningTag(bw, "Ex7Drawing");
			for (int i = 0; i < drawingElements.size(); i++) {
				String type = "Ex7Line";

				if (drawingElements.get(i) instanceof Ex7Line) {
					type = "Ex7Line";
				} else if (drawingElements.get(i) instanceof Ex7Ellipse) {
					type = "Ex7Ellipse";
				} else if (drawingElements.get(i) instanceof Ex7Rectangle) {
					type = "Ex7Rectangle";
				}

				writeOpeningTag(bw, type);
				writeTagAndValue(bw, "left", ""
						+ drawingElements.get(i).getLeft());
				writeTagAndValue(bw, "top", ""
						+ drawingElements.get(i).getTop());
				writeTagAndValue(bw, "width", ""
						+ drawingElements.get(i).getWidth());
				writeTagAndValue(bw, "height", ""
						+ drawingElements.get(i).getHeight());
				writeTagAndValue(bw, "color", ""
						+ drawingElements.get(i).getDrawingColor().getRGB());
				writeClosingTag(bw, type);
			}
			writeClosingTag(bw, "Ex7Drawing");

			bw.flush();

			if (bw != null) {
				bw.close();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void writeClosingTag(BufferedWriter bw, String tag)
			throws IOException {
		bw.write("</" + tag + ">");
		bw.newLine();
	}

	private void writeOpeningTag(BufferedWriter bw, String tag)
			throws IOException {
		bw.write("<" + tag + ">");
		bw.newLine();
	}

	private void writeTagAndValue(BufferedWriter bw, String tag, String value)
			throws IOException {
		bw.write("<" + tag + ">" + value + "</" + tag + ">");
		bw.newLine();
	}

	/**
	 * Getter for the frame
	 * 
	 * @return A pointer to the Ex7Frame object storing the buttons using this
	 *         action listener.
	 */
	public Ex7JFrame getFrame() {
		return frame;
	}

}
