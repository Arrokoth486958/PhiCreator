package dev.arrokoth.phicreator.player;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * @author Arrokoth
 * @project PhiCreator
 * @copyright Copyright © 2023 Arrokoth All Rights Reserved.
 */
public class Textures {
    public static final Image TAP = load("textures/tap.png");
    public static final Image TAP_HIGHLIGHT = load("textures/tap_highlight.png");
    public static final Image DRAG = load("textures/drag.png");
    public static final Image DRAG_HIGHLIGHT = load("textures/drag_highlight.png");
    public static final Image HOLD = load("textures/hold.png");
    public static final Image HOLD_HIGHLIGHT = load("textures/hold_highlight.png");
    public static final Image FLICK = load("textures/flick.png");
    public static final Image FLICK_HIGHLIGHT = load("textures/flick_highlight.png");

    private static Image load(String path) {
        try {
            BufferedImage source = ImageIO.read(ClassLoader.getSystemResource(path));

            BufferedImage image = new BufferedImage(source.getWidth(), source.getHeight(), BufferedImage.TYPE_INT_ARGB);
            Graphics2D graphics = image.createGraphics();
            graphics.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
            graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            graphics.drawImage(source, 0, 0, null);
            graphics.dispose();

            return image;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
