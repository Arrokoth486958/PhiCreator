package dev.arrokoth.phigcreator;

import dev.arrokoth.phigcreator.frame.EditorWindow;
import dev.arrokoth.phigcreator.util.Utils;

import javax.swing.*;

/**
 * @author Arrokoth
 * @project PhigCreator
 * @copyright Copyright © 2023 Arrokoth All Rights Reserved.
 */
public class PhigCreator implements Runnable {
    public static final boolean DEBUG = true;
    public static final String VERSION = "v0.1.0-dev";

    private static PhigCreator INSTANCE = null;

    public PhigCreator() {
        INSTANCE = this;
    }

    @Override
    public void run() {
        if (DEBUG) {
            JOptionPane.showMessageDialog(null, "Your Class Version: " + Utils.CLASS_VERSION, "Hey don't forget your Class version!", JOptionPane.INFORMATION_MESSAGE);
        }
        EditorWindow window = new EditorWindow();
        window.show();
    }

    public static PhigCreator getInstance() {
        return INSTANCE;
    }

    public static void main(String[] args) {
        new PhigCreator().run();
    }
}
