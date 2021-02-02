import java.awt.Graphics2D;
import java.awt.Image;

/**
 * 
 * @author Max (Smugless)
 * 
 *
 */

public class Board {
	Image image;
	
	
	public Board() {
		image = artist.quickLoad("board");
	}

	public void draw(Graphics2D g) {
		g.drawImage(image, 256, 64, null);
	}
}
