//package dev.arrokoth.phicreator.editor;

import dev.arrokoth.phicreator.phi.chart.Note;
import dev.arrokoth.phicreator.phi.level.Level;

import javax.swing.*;
import java.awt.*;

/**
 * @author Arrokoth
 * @project PhiCreator
 * @copyright Copyright © 2023 Arrokoth All Rights Reserved.
 */
public class EditorPane extends JPanel {
    public static final Color BACKGROUND_COLOR = new Color(17, 19, 17);
    public static final Color LINE_COLOR = new Color(21, 112, 21);
    public Level level;
    public boolean running = false;

    public EditorPane() {
        this.setBackground(BACKGROUND_COLOR);
    }
}
