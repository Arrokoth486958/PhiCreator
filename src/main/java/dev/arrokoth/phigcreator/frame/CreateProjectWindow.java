package dev.arrokoth.phigcreator.frame;

import com.formdev.flatlaf.icons.FlatFileViewDirectoryIcon;
import dev.arrokoth.phigcreator.PhigCreator;
import dev.arrokoth.phigcreator.i18n.LocalizationManager;
import dev.arrokoth.phigcreator.phi.editor.Project;
import dev.arrokoth.phigcreator.util.Utils;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Objects;

/**
 * @author Arrokoth
 * @project PhigCreator
 * @copyright Copyright © 2023 Arrokoth All Rights Reserved.
 */
public class CreateProjectWindow extends JDialog {
    private JTextField nameInput;
    private JTextField pathInput;
    private JTextField authorInput;

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

            setupNameTab(tabbedPane);
//            setupExtendTab(tabbedPane);

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
                        EditorWindow.project = new Project(file, 120);
                        this.dispose();
                    } else {
                        System.err.printf("Could not create a file \"%s\"\n", file.getAbsolutePath());
                    }
                }
            });

            JButton returnButton = new JButton(LocalizationManager.getString("BUTTON_RETURN"));
            returnButton.addActionListener(e -> {
                this.dispose();
            });
            bottomPane.add(returnButton);

            pane.add(bottomPane, BorderLayout.SOUTH);

            this.setContentPane(pane);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

//    private Image createFileChooserIconImage() {
//        BufferedImage image = new BufferedImage(24, 24, BufferedImage.TYPE_INT_ARGB);
//        new FlatFileViewDirectoryIcon().paintIcon(null, image.createGraphics(), 4, 4);
//
//        return image;
//    }

    private void setupNameTab(JTabbedPane pane) throws Exception {
        // Name
        JPanel contentPane = new JPanel();
        contentPane.setLayout(new FlowLayout(FlowLayout.LEFT, 24, 8));

        JPanel namePane = new JPanel();
        namePane.setPreferredSize(new Dimension(380, 24));
        namePane.setLayout(new BorderLayout());

        JLabel nameLabel = new JLabel(LocalizationManager.getString("PROJECT_NAME_INPUT"));
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

        JLabel pathLabel = new JLabel(LocalizationManager.getString("PROJECT_PATH_INPUT"));
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

        JLabel authorLabel = new JLabel(LocalizationManager.getString("PROJECT_AUTHOR_INPUT"));
        authorPane.add(authorLabel, BorderLayout.WEST);

        authorInput = new JTextField("Arrokoth233");
        authorInput.setPreferredSize(new Dimension(240, 24));
        authorPane.add(authorInput, BorderLayout.EAST);

        contentPane.add(authorPane);

        pane.addTab(LocalizationManager.getString("TAB_PROJECT_SETTINGS"), contentPane);
    }

    private void setupExtendTab(JTabbedPane pane) {
        // Name
        JPanel contentPane = new JPanel();
        contentPane.setLayout(new FlowLayout(FlowLayout.LEFT, 24, 8));

        JPanel musicPane = new JPanel();
        musicPane.setPreferredSize(new Dimension(380, 24));
        musicPane.setLayout(new BorderLayout());

        JLabel musicLabel = new JLabel("Audio: ");
        musicPane.add(musicLabel, BorderLayout.WEST);

        JTextField musicInput = new JTextField();
        musicInput.setPreferredSize(new Dimension(240, 24));
        musicPane.add(musicInput, BorderLayout.EAST);

        contentPane.add(musicPane);

        pane.addTab("Extended settings", contentPane);
    }
}
