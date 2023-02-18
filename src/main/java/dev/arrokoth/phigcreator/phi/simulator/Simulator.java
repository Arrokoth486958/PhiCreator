package dev.arrokoth.phigcreator.phi.simulator;

import dev.arrokoth.phigcreator.phi.chart.Chart;
import dev.arrokoth.phigcreator.phi.level.Level;
import dev.arrokoth.phigcreator.util.Utils;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * @author Arrokoth
 * @project PhigCreator
 * @copyright Copyright © 2023 Arrokoth All Rights Reserved.
 */
public class Simulator {
    public static boolean running = false;
    private static SimulatorClient client = null;

    public static void start(Chart chart) throws IOException {
        if (running) {
            JOptionPane.showMessageDialog(null, "The simulator is still running!");
            return;
        }
        if (client != null) {
            JOptionPane.showMessageDialog(null, "The simulator isn't closed yet!");
            return;
        }

        running = true;

        JFrame frame = new JFrame();
        frame.setIconImage(ImageIO.read(ClassLoader.getSystemResource("textures/icon_simulator.png")));
        frame.setSize(new Dimension(1000, 700));
        frame.setResizable(false);
        frame.setVisible(true);
        Utils.centerOnScreen(frame);

        frame.setContentPane(new JPanel());
        frame.getContentPane().setBackground(Color.BLACK);
        frame.getContentPane().setVisible(true);

        client = new SimulatorClient(new Level(chart), 1000, 700, (Graphics2D) frame.getContentPane().getGraphics());
        client.start();

        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                if (!client.isInterrupted()) {
                    client.interrupt();
                    client = null;
                    running = false;
                }
            }
        });
    }

//    public static void run(Level level, int renderWidth, int renderHeight, Graphics2D parentGraphics) {
//    }
}
