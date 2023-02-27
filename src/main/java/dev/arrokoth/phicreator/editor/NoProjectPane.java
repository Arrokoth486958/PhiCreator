package dev.arrokoth.phicreator.editor;

import dev.arrokoth.phicreator.i18n.LocalizationManager;

import javax.swing.*;
import java.awt.*;

/**
 * @author Arrokoth
 * @project PhiCreator
 * @copyright Copyright © 2023 Arrokoth All Rights Reserved.
 */
public class NoProjectPane extends JPanel {
    public NoProjectPane() {
        super();

        setLayout(new BorderLayout(90, 90));
        add(new JLabel(LocalizationManager.getString("BACKGROUND_NO_PROJECT")), BorderLayout.EAST);
    }
}
