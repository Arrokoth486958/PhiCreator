package dev.arrokoth.phigcreator.frame;

import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.icons.*;
import dev.arrokoth.phigcreator.PhigCreator;
import dev.arrokoth.phigcreator.config.Configuration;
import dev.arrokoth.phigcreator.frame.icon.FlatPlayIcon;
import dev.arrokoth.phigcreator.i18n.LocalizationManager;
import dev.arrokoth.phigcreator.phi.chart.Note;
import dev.arrokoth.phigcreator.phi.editor.Project;
import dev.arrokoth.phigcreator.phi.level.Level;
import dev.arrokoth.phigcreator.phi.simulator.Simulator;
import dev.arrokoth.phigcreator.util.Utils;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

/**
 * @author Arrokoth
 * @project PhigCreator
 * @copyright Copyright © 2023 Arrokoth All Rights Reserved.
 */
public class EditorWindow {
    public static Project project = Configuration.CONFIG.getProject();
    public final JFrame frame;

    public EditorWindow() {
        try {
            FlatDarkLaf.setup();
            frame = new JFrame();
            frame.setIconImage(ImageIO.read(ClassLoader.getSystemResource("textures/icon.png")));
            frame.setTitle(String.format(LocalizationManager.getString("TITLE_MAIN"), PhigCreator.VERSION));
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//            frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
            frame.setSize(new Dimension(1200, 800));
//            frame.setMinimumSize(new Dimension(800, 500));
            frame.setJMenuBar(new JMenuBar());
            Utils.centerOnScreen(frame);

            createMenuBar();
            createEditor();

            frame.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    Configuration.CONFIG.setProject(project);
                    Configuration.CONFIG.save();
                }
            });
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    protected void createMenuBar() {
        frame.setJMenuBar(new JMenuBar());

        JMenu fileMenu = new JMenu(LocalizationManager.getString("MENU_FILE"));
        frame.getJMenuBar().add(fileMenu);

        // Files
        JMenuItem fileMenuCreate = new JMenuItem(LocalizationManager.getString("MENU_FILE_CREATE"));
        fileMenuCreate.addActionListener(e -> {
            CreateProjectWindow wnd = new CreateProjectWindow();
            wnd.setVisible(true);
            Utils.centerOnScreen(wnd);
        });
        fileMenu.add(fileMenuCreate);

        JMenuItem fileMenuOpen = new JMenuItem(LocalizationManager.getString("MENU_FILE_OPEN"));
        fileMenu.add(fileMenuOpen);

        JMenuItem fileMenuRecent = new JMenuItem(LocalizationManager.getString("MENU_RECENT_PROJECT"));
        fileMenu.add(fileMenuRecent);

        JMenuItem fileMenuImport = new JMenuItem(LocalizationManager.getString("MENU_FILE_IMPORT"));
        fileMenu.add(fileMenuImport);

        JMenuItem fileMenuExport = new JMenuItem(LocalizationManager.getString("MENU_FILE_EXPORT"));
        fileMenu.add(fileMenuExport);

        JSeparator separator = new JSeparator();
        fileMenu.add(separator);

        // Exit
        JMenuItem fileMenuExit = new JMenuItem(LocalizationManager.getString("MENU_FILE_EXIT"));
        fileMenuExit.addActionListener(e -> exit());
        fileMenu.add(fileMenuExit);
    }

    protected void createEditor() throws Exception {
        JPanel rootPane = new JPanel();
        rootPane.setLayout(new BorderLayout());
        frame.getContentPane().add(rootPane);

        JToolBar topBar = new JToolBar();
        topBar.setFloatable(false);
        topBar.setPreferredSize(new Dimension(0, 30));
        rootPane.add(topBar, BorderLayout.NORTH);

        JButton projectSettings = new JButton();
        projectSettings.setIcon(new ImageIcon(Utils.createFlatIcon(FlatFileChooserDetailsViewIcon.class)));
        projectSettings.setPreferredSize(new Dimension(24, 24));
        projectSettings.setToolTipText(LocalizationManager.getString("TOOLTIP_PROJECT_SETTINGS"));
        topBar.add(projectSettings);

        topBar.add(new JToolBar.Separator());

        JButton projectRun = new JButton();
        projectRun.setIcon(new ImageIcon(Utils.createFlatIcon(FlatPlayIcon.class)));
        projectRun.setPreferredSize(new Dimension(24, 24));
        projectRun.setToolTipText(LocalizationManager.getString("TOOLTIP_PROJECT_RUN"));
        topBar.add(projectRun);

        // Editor
        JSplitPane splitPane = new JSplitPane();
        rootPane.add(splitPane, BorderLayout.CENTER);

        splitPane.setDividerLocation((int) (this.frame.getWidth() * 0.8f));
        splitPane.setResizeWeight(1);

        EditorPane simulatorPane = new EditorPane();
        splitPane.setLeftComponent(simulatorPane);

        JPanel sideBar = new JPanel();

        splitPane.setRightComponent(sideBar);

        projectRun.addActionListener(e -> {
            new Thread(() -> {
                try {
                    project.chart.notes.add(new Note(0, 0, 5000));
                    Simulator.start(project.chart);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
//                simulatorPane.run(new Level(project.chart));
            }).start();
        });
    }

    public void show() {
        frame.setVisible(true);
    }

    public void exit() {
        frame.setVisible(false);
        frame.dispose();
        System.exit(0);
    }
}
