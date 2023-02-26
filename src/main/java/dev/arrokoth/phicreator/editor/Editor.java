package dev.arrokoth.phicreator.editor;

import com.formdev.flatlaf.FlatDarculaLaf;
import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.FlatLightLaf;
import dev.arrokoth.phicreator.config.Configuration;
import dev.arrokoth.phicreator.phi.editor.Project;

import javax.swing.*;
import java.awt.*;

/**
 * @author Arrokoth
 * @project PhiCreator
 * @copyright Copyright © 2023 Arrokoth All Rights Reserved.
 */
public class Editor implements Runnable {
    public static final Editor INSTANCE = new Editor();
    public static Project project;

    private final EditorWindow window;

    private Editor() {
        // TODO: Theme selection
        FlatLaf laf = new FlatLightLaf();
        FlatLaf.setup(laf);

        window = new EditorWindow();
    }

    @Override
    public void run() {
        this.window.setVisible(true);
    }
}
