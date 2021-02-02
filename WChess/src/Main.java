import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.JFrame;

/**
 * 
 * @author Max (Smugless)
 * 
 *
 */

public class Main extends Canvas implements Runnable {

	private static final long serialVersionUID = 1L;
	private static int DEFAULT_WIDTH = 32 * 32, DEFAULT_HEIGHT = 32 * 26;
	private Image dbImage;
	private Graphics dbg;
	private Board board = new Board();
	
	public static void main(String args[]) {
		JFrame frame = new JFrame("Wizard's Chess Beta");
		frame.setIconImage(artist.quickLoad("icon"));
		Main game = new Main();
		frame.pack();
		frame.setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(game);
		frame.setVisible(true);
		game.start();
	}

	public void update(Graphics g) {
		if (dbImage == null) {
			dbImage = createImage(this.getSize().width, this.getSize().height);
			dbg = dbImage.getGraphics();
		}
		dbg.setColor(getBackground());
		dbg.fillRect(0, 0, this.getSize().width, this.getSize().height);
		dbg.setColor(getForeground());
		paint(dbg);
		g.drawImage(dbImage, 0, 0, this);
		
		boolean test = false;
	}
	
	public void paint(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		
		board.draw(g2d);
	}


	public void run() {
		while (true) {
			try {
				Thread.sleep(20);
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("THREAD ERROR");
			}
			repaint();
		}
	}
	private void start() {
		this.requestFocus();
		run();
	}
}
