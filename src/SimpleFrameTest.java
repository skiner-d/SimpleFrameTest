import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class SimpleFrameTest {

	public static void main(String[] args) {

		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				createGui();
			}
		});
	}

	static public void createGui() {
		SimpleFrame ourFrame = new SimpleFrame("Another title");
		ourFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ourFrame.setVisible(true);
	}
}

class SimpleFrame extends JFrame {

	private String title = "Default title";

	JPanel pane

	private void constructFrame() {
		setTitle(title);
		setSize(800, 600);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		double width = screenSize.getWidth();
		double height = screenSize.getHeight();
		setLocation((int) width / 2 - getWidth() / 2, (int) height / 2 - getHeight() / 2);
		setResizable(true);
		panel = new JPanel();
		panel.setBorder(BorderFactory.createLineBorder(Color.RED, 5));
		panel.setBackground(Color.WHITE);
		panel.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				new BallThread(panel, e.getX(), e.getY()).start();
				repaint();
			}
		});
		this.add(panel, BorderLayout.CENTER);
	}

	SimpleFrame() {
		super();
		constructFrame();
	}

	SimpleFrame(String t) {
		super();

		title = t;
		constructFrame();
	}
}

class BallThread extends Thread {
	JPanel panel;
	private int posX, posY;
	private int dx = 1;
	private int dy = 1;

	BallThread(JPanel p, int posX, int posY) {
		this.panel = p;
		this.posX = posX;
		this.posY = posY;
	}

	public void run() {
		posX += dx;
		posY += dy;
		while (true) {
			posX += dx;
			posY += dy;
			if (posX >= panel.getWidth() - 35) {
				dx = -dx;
				panel.repaint();
			} else if (posX <= 35) {
				dx = -dx;
				panel.repaint();
			}
			if (posY >= panel.getHeight() - 35) {
				dy = -dy;
				panel.repaint();
			}

			else if (posY <= 35) {
				dy = -dy;
				panel.repaint();
			}

			paint(panel.getGraphics());
			try {
				sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public void paint(Graphics g) {
		g.setColor(Color.WHITE);
		g.drawOval(posX - 25 - dx, posY - 25 - dy, 50, 50);
		g.setColor(Color.BLACK);
		g.drawOval(posX - 25, posY - 25, 50, 50);
	}

}
