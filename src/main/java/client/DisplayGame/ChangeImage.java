package client.DisplayGame;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;

/**
 * Contains methods to change an image (mainly recoloring an image) and scaling them
 */
public class ChangeImage {
    /**
     * Turns an image icon into a buffered image.
     *
     * @param icon - The image icon to transform.
     * @return - The image icon as a bufferedImage.
     */
    public static BufferedImage intoBuffer(ImageIcon icon) {
        BufferedImage bi = new BufferedImage(
                icon.getIconWidth(),
                icon.getIconHeight(),
                BufferedImage.TYPE_INT_ARGB);
        Graphics g = bi.createGraphics();

        icon.paintIcon(null, g, 0, 0);
        g.dispose();
        return bi;
    }

    /**
     * Changes all the colored pixels in an BufferedImage to another color.
     *
     * @param image - The image to recolor.
     * @param color - The color to recolor.
     * @return - The recolored image.
     */
    static ImageIcon colorImage(BufferedImage image, Color color) {
        int width = image.getWidth();
        int height = image.getHeight();
        WritableRaster raster = image.getRaster();

        // Changes the color of the image to the specified color
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                int[] pixels = raster.getPixel(x, y, (int[]) null);
                pixels[0] = color.getRed();
                pixels[1] = color.getGreen();
                pixels[2] = color.getBlue();
                raster.setPixel(x, y, pixels);
            }
        }
        return new ImageIcon(image);
    }
}
