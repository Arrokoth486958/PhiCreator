package dev.arrokoth.phicreator.editor.icon;

import dev.arrokoth.phicreator.util.Utils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

/**
 * @author Arrokoth
 * @project PhiCreator
 * @copyright Copyright © 2023 Arrokoth All Rights Reserved.
 */
public class Icons {
    public static final Image EDITOR_ICON = Utils.readAssetImage("icon.png");
    public static final Image SIMULATOR_ICON = Utils.readAssetImage("icon_simulator.png");

//    static {
//        try {
//            EDITOR_ICON = ImageIO.read(ClassLoader.getSystemResource("textures/icon.png"));
//            SIMULATOR_ICON = ImageIO.read(ClassLoader.getSystemResource("textures/icon_simulator.png"));
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }
}
