package dev.arrokoth.phigcreator.util;

import com.formdev.flatlaf.icons.FlatAbstractIcon;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Objects;

/**
 * @author Arrokoth
 * @project PhigCreator
 * @copyright Copyright © 2023 Arrokoth All Rights Reserved.
 */
public class Utils {
    public static final double CLASS_VERSION = Double.parseDouble(System.getProperty("java.class.version"));
    public static final File CURRENT_DIRECTORY = new File("").getAbsoluteFile();

    public static Image createFlatIcon(Class<? extends FlatAbstractIcon> iconClass) throws Exception {
        return createFlatIcon(iconClass, 18, 18, 1, 1);
    }

    public static Image createFlatIcon(Class<? extends FlatAbstractIcon> iconClass, int width, int height, int x, int y) throws Exception {
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
//        new FlatFileViewDirectoryIcon().paintIcon(null, image.createGraphics(), x, y);
        iconClass.getConstructor().newInstance().paintIcon(null, image.createGraphics(), x, y);

        return image;
    }

    public static void centerOnScreen(JFrame frame) {
        frame.setLocation((Toolkit.getDefaultToolkit().getScreenSize().width / 2) - (frame.getWidth() / 2), (Toolkit.getDefaultToolkit().getScreenSize().height / 2) - (frame.getHeight() / 2));
    }

    public static void centerOnScreen(JDialog dialog) {
        dialog.setLocation((Toolkit.getDefaultToolkit().getScreenSize().width / 2) - (dialog.getWidth() / 2), (Toolkit.getDefaultToolkit().getScreenSize().height / 2) - (dialog.getHeight() / 2));
    }

    public static byte[] getResource(String path) {
        return readStream(Objects.requireNonNull(ClassLoader.getSystemResourceAsStream(path)));
    }

    public static byte[] readStream(InputStream stream) {
        try {
            if (CLASS_VERSION <= 52) {
                ByteArrayOutputStream buffer = new ByteArrayOutputStream();
                int read;
                byte[] bytes = new byte[4096];

                while ((read = stream.read(bytes, 0, bytes.length)) != -1) {
                    buffer.write(bytes, 0, read);
                }
                byte[] out = buffer.toByteArray();
                buffer.flush();
                buffer.close();

                return out;
            } else {
                return stream.readAllBytes();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static byte[] readURL(URL url) {
        try {
            return readStream(url.openStream());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
