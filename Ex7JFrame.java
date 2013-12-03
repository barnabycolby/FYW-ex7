package ex7;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JToolBar;

@SuppressWarnings("serial")
public class Ex7JFrame extends JFrame {

	private Ex7JPanel panel;
	private JPanel toolbarPanel;
	private JToolBar toolbar;
	private Ex7DrawingMode drawingMode;
	private Color drawingColour = Color.BLACK;

	/**
	 * Constructor.
	 * 
	 * @param title
	 *            The title of the JFrame.
	 */
	public Ex7JFrame(String title) {
		// Create the JFrame
		super(title);

		// Initialise the drawing mode
		this.setDrawingMode(Ex7DrawingMode.LINE);

		configureFrame();
		createDrawingPanel();
		createToolbar();

		this.setVisible(true);
	}

	private void configureFrame() {
		this.setSize(750, 500);
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		this.setLayout(new BorderLayout());
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	private void createDrawingPanel() {
		this.panel = new Ex7JPanel();
		this.getPanel().setSize(this.getPanel().getMaximumSize());
		Ex7MouseListener mouseListener = new Ex7MouseListener(this);
		this.getPanel().addMouseListener(mouseListener);
		this.getPanel().addMouseMotionListener(mouseListener);
		this.add(this.getPanel(), BorderLayout.CENTER);
	}

	private void createToolbar() {
		this.toolbarPanel = new JPanel();
		this.toolbar = new JToolBar();
		Ex7ButtonListener listener = new Ex7ButtonListener(this);
		JButton[] buttons = { new JButton("Load"), new JButton("Save"),
				new JButton("Undo"), new JButton("Redo"), new JButton("Line"),
				new JButton("Ellipse"), new JButton("Rectangle"),
				new JButton("Colour"), new JButton("Exit") };

		for (int i = 0; i < buttons.length; i++) {
			buttons[i].addActionListener(listener);
			this.getToolbar().add(buttons[i]);
		}

		this.getToolbarPanel().add(this.getToolbar());
		this.add(this.getToolbarPanel(), BorderLayout.NORTH);
	}

	/**
	 * Gets the panel stored by the frame.
	 * 
	 * @return The panel.
	 */
	public Ex7JPanel getPanel() {
		return panel;
	}

	/**
	 * Gets the toolbar stored by the toolbar panel.
	 * 
	 * @return The toolbar.
	 */
	public JToolBar getToolbar() {
		return toolbar;
	}

	/**
	 * Gets the toolbar panel stored by the frame.
	 * 
	 * @return The toolbar panel.
	 */
	public JPanel getToolbarPanel() {
		return toolbarPanel;
	}

	/**
	 * Gets the current drawing mode.
	 * 
	 * @return The drawing mode.
	 */
	public Ex7DrawingMode getDrawingMode() {
		return drawingMode;
	}

	/**
	 * Sets the drawing mode of the frame.
	 * 
	 * @param drawingMode
	 *            The new drawing mode.
	 */
	public void setDrawingMode(Ex7DrawingMode drawingMode) {
		this.drawingMode = drawingMode;
	}

	/**
	 * @param args
	 *            N/A
	 */
	public static void main(String[] args) {
		// EXTENSIONS
		// =============
		// - Drawing in colour
		// - Saving and loading of files in custom format
		// - Allow pictures to be exported as png files
		// - Allow undo and redo

		new Ex7JFrame("Ex7Paint");
	}

	/**
	 * Gets the drawing colour.
	 * 
	 * @return The drawing colour.
	 */
	public Color getDrawingColour() {
		return drawingColour;
	}

	/**
	 * Sets the drawing colour.
	 * 
	 * @param drawingColour
	 *            The new drawing colour.
	 */
	public void setDrawingColour(Color drawingColour) {
		this.drawingColour = drawingColour;
	}

}
