import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * 
 * @author Max (Smugless)
 * 
 *
 */

public class artist {
	public static BufferedImage quickLoad(String quickSource) {
		BufferedImage image = null;
		try {
			image = ImageIO.read(artist.class.getResourceAsStream("/images/" + quickSource+".png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return image;
	}
}
