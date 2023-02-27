package dev.arrokoth.phicreator.editor;

import dev.arrokoth.phicreator.PhiCreator;
import dev.arrokoth.phicreator.config.Configuration;
import dev.arrokoth.phicreator.editor.icon.Icons;
import dev.arrokoth.phicreator.i18n.LocalizationManager;
import dev.arrokoth.phicreator.util.Utils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * @author Arrokoth
 * @project PhiCreator
 * @copyright Copyright © 2023 Arrokoth All Rights Reserved.
 */
public class EditorWindow extends JFrame {
    public EditorWindow() {
        setTitle(String.format(LocalizationManager.getString("TITLE_EDITOR"), PhiCreator.VERSION));
        setIconImage(Icons.EDITOR_ICON);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        if (Configuration.CONFIG.isWindowMaximized()) {
            setSize(new Dimension(1600, 900));
            setExtendedState(MAXIMIZED_BOTH);
        } else {
            setSize(Configuration.CONFIG.getWindowSize());
            setExtendedState(NORMAL);
        }

        layoutWindow();

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                Configuration.CONFIG.saveWindowState(EditorWindow.this);
                Configuration.CONFIG.save();
            }
        });
    }

    private void layoutWindow() {

        // Layout
        JMenuBar menuBar = new JMenuBar();

        JMenu fileMenu = new JMenu(LocalizationManager.getString("MENU_FILE"));
        menuBar.add(fileMenu);

        JMenuItem fileMenuCreate = new JMenuItem(LocalizationManager.getString("MENU_FILE_CREATE"));
        fileMenu.add(fileMenuCreate);

        setJMenuBar(menuBar);

        // Actions
        fileMenuCreate.addActionListener(e -> {
            CreateProjectWindow wnd = new CreateProjectWindow();
            wnd.setVisible(true);
            Utils.centerOnScreen(wnd);
        });
        setContentPane(new NoProjectPane());
    }

    @Override
    public void setVisible(boolean b) {
        super.setVisible(b);
        if (b) {
            if (getExtendedState() == NORMAL) {
                Utils.centerOnScreen(this);
            }
        }
    }
}
