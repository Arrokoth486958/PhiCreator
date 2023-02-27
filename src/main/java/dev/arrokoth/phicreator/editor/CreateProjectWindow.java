package dev.arrokoth.phicreator.editor;

import com.formdev.flatlaf.icons.FlatFileViewDirectoryIcon;
import dev.arrokoth.phicreator.editor.controls.JNumberInput;
import dev.arrokoth.phicreator.i18n.LocalizationManager;
import dev.arrokoth.phicreator.phi.editor.Project;
import dev.arrokoth.phicreator.util.Utils;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

/**
 * @author Arrokoth
 * @project PhiCreator
 * @copyright Copyright © 2023 Arrokoth All Rights Reserved.
 */
public class CreateProjectWindow extends JDialog {
    private JTextField nameInput;
    private JTextField pathInput;
    private JTextField authorInput;
    private JTextField musicInput;
    private JTextField bpsInput;

    public CreateProjectWindow() {
        super((Dialog) null);
        try {
            this.setIconImage(ImageIO.read(ClassLoader.getSystemResource("textures/icon.png")));
            this.setTitle(LocalizationManager.getString("TITLE_CREATE_PROJECT"));
            this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            this.setSize(500, 300);
            this.setResizable(false);
            this.setModal(true);
            Utils.centerOnScreen(this);

            JPanel pane = new JPanel();
            pane.setLayout(new BorderLayout());

            JTabbedPane tabbedPane = new JTabbedPane();
            pane.add(tabbedPane, BorderLayout.CENTER);

            setupBasicTab(tabbedPane);
            setupChartTab(tabbedPane);

            // Bottom bar
            JPanel bottomPane = new JPanel();
            bottomPane.setLayout(new FlowLayout(FlowLayout.RIGHT, 8, 8));

            JButton createButton = new JButton(LocalizationManager.getString("BUTTON_CREATE"));
            bottomPane.add(createButton);
            createButton.addActionListener(e -> {
                File file = new File(pathInput.getText());
                if (file.exists() && (!file.isDirectory() || Objects.requireNonNull(file.listFiles()).length > 0)) {
                    try {
                        JOptionPane optionPane = new JOptionPane();
                        optionPane.setMessage(String.format(LocalizationManager.getString("MESSAGE_UNAVAILABLE_PATH"), pathInput.getText()));
                        optionPane.setMessageType(JOptionPane.ERROR_MESSAGE);

                        JDialog dialog = optionPane.createDialog(LocalizationManager.getString("MESSAGE_TITLE_ERROR"));
                        dialog.setIconImage(ImageIO.read(ClassLoader.getSystemResource("textures/icon.png")));
                        dialog.setModal(true);
                        dialog.setVisible(true);
                        Utils.centerOnScreen(dialog);
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                } else {
                    if (file.exists() || file.mkdirs()) {
                        System.out.println(nameInput.getText());
                        System.out.println(pathInput.getText());
                        System.out.println(authorInput.getText());
                        // TODO: Dps selection
                        Editor.INSTANCE.setProject(new Project(file, 120));
                        this.dispose();
                    } else {
                        System.err.printf("Could not create a file \"%s\"\n", file.getAbsolutePath());
                    }
                }
            });

            JButton returnButton = new JButton(LocalizationManager.getString("BUTTON_RETURN"));
            returnButton.addActionListener(e -> this.dispose());
            bottomPane.add(returnButton);

            pane.add(bottomPane, BorderLayout.SOUTH);

            this.setContentPane(pane);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void setupBasicTab(JTabbedPane pane) throws Exception {
        // Name
        JPanel contentPane = new JPanel();
        contentPane.setLayout(new FlowLayout(FlowLayout.LEFT, 24, 8));

        JPanel namePane = new JPanel();
        namePane.setPreferredSize(new Dimension(380, 24));
        namePane.setLayout(new BorderLayout());

        JLabel nameLabel = new JLabel(LocalizationManager.getString("PROJECT_BASIC_NAME"));
        namePane.add(nameLabel, BorderLayout.WEST);

        nameInput = new JTextField("New Project");
        nameInput.setPreferredSize(new Dimension(240, 24));
        nameInput.transferFocus();
        namePane.add(nameInput, BorderLayout.EAST);

        contentPane.add(namePane);

        // Path
        JPanel pathPane = new JPanel();
        pathPane.setPreferredSize(new Dimension(380, 24));
        pathPane.setLayout(new BorderLayout());

        JLabel pathLabel = new JLabel(LocalizationManager.getString("PROJECT_BASIC_PATH"));
        pathPane.add(pathLabel, BorderLayout.WEST);

        JPanel pathInputPane = new JPanel();
        pathInputPane.setLayout(new FlowLayout(FlowLayout.RIGHT, 0, 0));

        pathInput = new JTextField(new File(Utils.CURRENT_DIRECTORY, "New Project").getAbsolutePath());
        pathInput.setPreferredSize(new Dimension(208, 24));
        pathInputPane.add(pathInput);
        pathInputPane.add(Box.createRigidArea(new Dimension(8, 1)));

        JButton pathSelector = new JButton();
        pathSelector.setIcon(new ImageIcon(Utils.createFlatIcon(FlatFileViewDirectoryIcon.class)));
        pathSelector.setPreferredSize(new Dimension(24, 24));
        pathInputPane.add(pathSelector);
        pathSelector.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser(new File(pathInput.getText()).exists() ? new File(pathInput.getText()) : Utils.CURRENT_DIRECTORY);
            fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            if (fileChooser.showOpenDialog(new JPanel()) == JFileChooser.APPROVE_OPTION) {
                pathInput.setText(fileChooser.getSelectedFile().getAbsolutePath());
            }
        });

        pathPane.add(pathInputPane, BorderLayout.EAST);

        contentPane.add(pathPane);

        // Author
        JPanel authorPane = new JPanel();
        authorPane.setPreferredSize(new Dimension(380, 24));
        authorPane.setLayout(new BorderLayout());

        JLabel authorLabel = new JLabel(LocalizationManager.getString("PROJECT_BASIC_AUTHOR"));
        authorPane.add(authorLabel, BorderLayout.WEST);

        authorInput = new JTextField("Arrokoth233");
        authorInput.setPreferredSize(new Dimension(240, 24));
        authorPane.add(authorInput, BorderLayout.EAST);

        contentPane.add(authorPane);

        pane.addTab(LocalizationManager.getString("TAB_PROJECT_BASIC"), contentPane);
    }

    private void setupChartTab(JTabbedPane pane) throws Exception {
        // Name
        JPanel contentPane = new JPanel();
        contentPane.setLayout(new FlowLayout(FlowLayout.LEFT, 24, 8));

        // Music
        JPanel musicPane = new JPanel();
        musicPane.setPreferredSize(new Dimension(380, 24));
        musicPane.setLayout(new BorderLayout());

        JLabel musicLabel = new JLabel(LocalizationManager.getString("PROJECT_CHART_MUSIC"));
        musicPane.add(musicLabel, BorderLayout.WEST);

        JPanel musicInputPane = new JPanel();
        musicInputPane.setLayout(new FlowLayout(FlowLayout.RIGHT, 0, 0));

        musicInput = new JTextField("");
        musicInput.setPreferredSize(new Dimension(208, 24));
        musicInputPane.add(musicInput);
        musicInputPane.add(Box.createRigidArea(new Dimension(8, 1)));

        JButton musicSelector = new JButton();
        musicSelector.setIcon(new ImageIcon(Utils.createFlatIcon(FlatFileViewDirectoryIcon.class)));
        musicSelector.setPreferredSize(new Dimension(24, 24));
        musicInputPane.add(musicSelector);
        musicSelector.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser(new File(musicInput.getText()).exists() ? new File(musicInput.getText()) : Utils.CURRENT_DIRECTORY);
            fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
            fileChooser.setFileFilter(new FileNameExtensionFilter(LocalizationManager.getString("FILE_CHOOSER_TYPE_MUSIC"),"ogg","mp3","wav"));
            if (fileChooser.showOpenDialog(new JPanel()) == JFileChooser.APPROVE_OPTION) {
                musicInput.setText(fileChooser.getSelectedFile().getAbsolutePath());
            }
        });

        musicPane.add(musicInputPane, BorderLayout.EAST);

        contentPane.add(musicPane);

        // Bps
        JPanel bpsPane = new JPanel();
        bpsPane.setPreferredSize(new Dimension(380, 24));
        bpsPane.setLayout(new BorderLayout());

        JLabel bpsLabel = new JLabel(LocalizationManager.getString("PROJECT_CHART_BPS"));
        bpsPane.add(bpsLabel, BorderLayout.WEST);

        bpsInput = new JNumberInput("120.0");
        bpsInput.setPreferredSize(new Dimension(240, 24));
        bpsPane.add(bpsInput, BorderLayout.EAST);

        contentPane.add(bpsPane);

        pane.addTab(LocalizationManager.getString("TAB_PROJECT_CHART"), contentPane);
    }
}
