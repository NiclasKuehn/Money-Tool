import java.awt.*;
import java.util.ArrayList;

public class QualitySettings {
	private QualitySettings() {
	}
	
	public static void set(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		ArrayList<RenderingHints> hints = new ArrayList<RenderingHints>();
		hints.add(new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON));
		hints.add(new RenderingHints(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY));
		hints.add(new RenderingHints(RenderingHints.KEY_RESOLUTION_VARIANT, RenderingHints.VALUE_RESOLUTION_VARIANT_DPI_FIT));
		hints.add(new RenderingHints(RenderingHints.KEY_DITHERING, RenderingHints.VALUE_DITHER_ENABLE));
		hints.add(new RenderingHints(RenderingHints.KEY_DITHERING, RenderingHints.VALUE_DITHER_ENABLE));
		hints.add(new RenderingHints(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON));
		
		for (RenderingHints h :
				hints) {
			g2.addRenderingHints(h);
			
		}
		
		
	}
}
