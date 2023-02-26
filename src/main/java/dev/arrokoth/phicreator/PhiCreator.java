package dev.arrokoth.phicreator;

import com.formdev.flatlaf.FlatDarculaLaf;
import dev.arrokoth.phicreator.editor.Editor;
import dev.arrokoth.phicreator.editor.EditorWindow;
import dev.arrokoth.phicreator.util.Utils;

import javax.swing.*;

/**
 * @author Arrokoth
 * @project PhiCreator
 * @copyright Copyright © 2023 Arrokoth All Rights Reserved.
 */
public class PhiCreator implements Runnable {
    public static final boolean DEBUG = true;
    public static final String VERSION = "v0.1.0-dev";

    private static PhiCreator INSTANCE = null;

    public PhiCreator() {
        INSTANCE = this;
    }

    @Override
    public void run() {
        if (DEBUG) {
            JOptionPane.showMessageDialog(null, "Your Class Version: " + Utils.CLASS_VERSION, "Hey don't forget your Class version!", JOptionPane.INFORMATION_MESSAGE);
        }

        Editor.INSTANCE.run();
    }

    public static PhiCreator getInstance() {
        return INSTANCE;
    }

    public static void main(String[] args) {
        new PhiCreator().run();
    }
}
